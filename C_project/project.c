#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define LENGTH 545 // longest word is 45 + 500 words is the longest meaning
#define MAX_TRIES 8 // Maximum number of incorrect guesses

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
} node;

// TODO: Choose number of buckets in hash table
// 26*26 + 26 ( single words)
const int N = 702;

// Hash table
node *table[N] = {NULL};

// Hashes word to a number
unsigned int hash(const char *word)
{
    // considering the first 2 letters for assigning a bucket
    int index1 = toupper(word[0]) - 'A';
    // incase second letter doesnt exist
    if (word[1] == '\0' || word[1] == ' ')
        return index1 * 26;
    int index2 = toupper(word[1]) - 'A';
    return (index1 * 26) + index2 + (index1 + 1);
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    FILE *dict = fopen(dictionary, "r");
    if (dict == NULL)
    {
        printf("Couldnt load dict file\n");
        return false;
    }
    char buffer[LENGTH + 1];
    while (fscanf(dict, "%[^\n]\n", buffer) == 1)
    {
        node *n = malloc(sizeof(node));
        strcpy(n->word, buffer);
        n->next = NULL;
        int index = hash(buffer);
        // they were all initially set to NULL
        if (table[index] == NULL)
        {
            table[index] = n;
            continue;
        }
        node *temp = table[index];
        while (temp->next != NULL)
            temp = temp->next;
        temp->next = n;
    }
    fclose(dict);
    return true;
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    int i;
    for (i = 0; i < N; i++)
    {
        node *temp = table[i];
        while (temp != NULL)
        {
            node *temp1 = temp;
            temp = temp->next;
            free(temp1);
        }
    }
    if (i == N)
        return true;
    else
        return false;
}

int randomInRange(int min, int max)
{
    return rand() % (max - min + 1) + min; // Generate random number between min and max
}

// 1st random is for the bucket
// 2nd random is for how many nodes to traverse along that particular bucket
void generateRandomWord(char* word, char* meaning)
{
    node* temp;
    srand(time(NULL));
    load("words_with_meanings.txt");
    int random = randomInRange(0,701);
    int random2 = randomInRange(0,200);
    temp = table[random];
    while (temp->next != NULL && random2 > 0)
    {
        temp = temp->next;
        random2--;
    }
    char temp_string[LENGTH + 500]; // Adjusted to accommodate longer strings if needed
    strcpy(temp_string, temp->word);

    // Separate word and meaning using hyphen as the delimiter
    int i = 0;
    while (temp_string[i] != ' ' && temp_string[i] != '\0') {
        word[i] = temp_string[i];
        i++;
    }
    i++;
    word[i] = '\0';  // Terminate the word string

    if (temp_string[i] == '-') {
        i++; // Move past the hyphen
    }

    int j = 0;
    while (temp_string[i] != '\0') {
        meaning[j++] = temp_string[i++];
    }
    meaning[j] = '\0'; // Terminate the meaning string
}

void displayWord(const char* word, const int* guessed, const char* meaning)
{
    printf("%s\n",meaning);
    for (int i = 0; word[i] != '\0'; i++) {
        if (guessed[i]) {
            printf("%c ", word[i]);  // Display guessed letter
        } else {
            printf("_ ");  // Display underscore for unguessed letter
        }
    }
    printf("\n");
}

int checkGuess(char guess, const char* word, int* guessed) {
    int correct = 0;
    for (int i = 0; word[i] != '\0'; i++) {
        if (tolower(word[i]) == tolower(guess) && !guessed[i]) {
            guessed[i] = 1;
            correct = 1;
        }
    }
    return correct;
}

int isWordGuessed(const int* guessed, int length) {
    for (int i = 0; i < length; i++) {
        if (!guessed[i]) {
            return 0;  // If there's any letter not guessed
        }
    }
    return 1;  // Word fully guessed
}
int main() {
    char word[LENGTH];
    char meaning[1000];
    generateRandomWord(word,meaning);
    // const char word[] = "programming";  // The word to guess
    int wordLength = strlen(word);
    int guessed[wordLength];  // Array to track which letters are guessed
    int incorrectTries = 0;
    char guess;

    // Initialize the guessed array to 0 (false)
    for (int i = 0; i < wordLength; i++) {
        guessed[i] = 0;
    }

    printf("Welcome to Hangman! Guess the word:\n");

    // Main game loop
    while (incorrectTries < MAX_TRIES) {
        displayWord(word, guessed, meaning);

        printf("\nEnter a letter to guess: ");
        scanf(" %c", &guess);

        if (checkGuess(guess, word, guessed)) {
            printf("\nGood guess!\n");
        } else {
            incorrectTries++;
            printf("\nWrong guess! You have %d tries left.\n", MAX_TRIES - incorrectTries);
        }

        // Check if the whole word has been guessed
        if (isWordGuessed(guessed, wordLength)) {
            printf("\nCongratulations! You've guessed the word '%s'.\n", word);
            break;
        }
    }

    if (incorrectTries == MAX_TRIES) {
        printf("\nGame over! You've been hanged. The word was '%s'.\n", word);
    }

    return 0;
}
