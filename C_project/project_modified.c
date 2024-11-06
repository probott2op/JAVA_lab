#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define LENGTH 545 // longest word is 45 + 500 words is the longest meaning
#define MAX_TRIES 8 // Maximum number of incorrect guesses
#define MAX_NAME_LENGTH 100 // Max length for player name

int segmentation_preventor = 0;

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
} node;

// Represents a player score in a queue
typedef struct
{
    int scores[LENGTH];
    char* names[LENGTH];
    int front;
    int rear;
}queue;

// Hash table size
const int N = 702;
node *table[N] = {NULL};

// Initializing score queue
queue* initqueue()
{
    queue *q;
    q = malloc(sizeof(queue));
    q->front = 0;
    q->rear = -1;
    return q;
}

// Hashes word to a number (based on the first two letters of the word)
unsigned int hash(const char *word)
{
    int index1 = toupper(word[0]) - 'A';
    if (word[1] == '\0' || word[1] == ' ')
        return index1 * 26;
    int index2 = toupper(word[1]) - 'A';
    return (index1 * 26) + index2;
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    FILE *dict = fopen(dictionary, "r");
    if (dict == NULL)
    {
        printf("Couldn't load dictionary file\n");
        return false;
    }
    char buffer[LENGTH + 1];
    while (fscanf(dict, "%[^\n]\n", buffer) == 1)
    {
        node *n = malloc(sizeof(node));
        strcpy(n->word, buffer);
        n->next = NULL;
        int index = hash(buffer);
        if (table[index] == NULL)
        {
            table[index] = n;
        }
        else
        {
            node *temp = table[index];
            while (temp->next != NULL)
                temp = temp->next;
            temp->next = n;
        }
    }
    fclose(dict);
    return true;
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    for (int i = 0; i < N; i++)
    {
        node *temp = table[i];
        while (temp != NULL)
        {
            node *temp1 = temp;
            temp = temp->next;
            free(temp1);
        }
    }
    return true;
}

// Generates a random word and its meaning from the hash table
void generateRandomWord(char *word, char *meaning)
{
    srand(time(NULL));
    int random = rand() % N; // Random bucket
    int random2 = rand() % 10; // Random traversal in the bucket

    node *temp = table[random];
    for (int i = 0; i < random2 && temp->next != NULL && temp != NULL; i++)
    {
        temp = temp->next;
    }
    if (segmentation_preventor == 15 && temp == NULL)
        temp = table[15];
    if (temp != NULL)
    {
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
    else
    {
        generateRandomWord(word, meaning);
        segmentation_preventor++;
    }
}

// Displays the word with underscores for unguessed letters
void displayWord(const char *word, const int *guessed, const char *meaning)
{
    printf("%s\n", meaning);
    for (int i = 0; word[i] != '\0'; i++)
    {
        if (guessed[i])
        {
            printf("%c ", word[i]);  // Display guessed letter
        }
        else
        {
            printf("_ ");  // Display underscore for unguessed letter
        }
    }
    printf("\n");
}

// Checks if the guessed letter is correct
int checkGuess(char guess, const char *word, int *guessed)
{
    int correct = 0;
    for (int i = 0; word[i] != '\0'; i++)
    {
        if (tolower(word[i]) == tolower(guess) && !guessed[i])
        {
            guessed[i] = 1;
            correct = 1;
        }
    }
    return correct;
}

// Checks if the word has been fully guessed
int isWordGuessed(const int *guessed, int length)
{
    for (int i = 0; i < length; i++)
    {
        if (!guessed[i])
        {
            return 0; // If there's any letter not guessed
        }
    }
    return 1; // Word fully guessed
}

// Enqueues a player's score to the queue
void enqueue(queue *q, char* name, int score)
{
    for (int i = 0; i < q->rear; i++)
    {
        if(strcmp(q->names[i],name) == 0)
        {
            q->scores[i] += score;
            return;
        }
    }
    q->names[++(q->rear)] = name;
    q->scores[q->rear] = score;
}

// Dequeues a player's score from the queue
int dequeue_score(queue *q)
{
    if (q->front > q->rear )
        return -1;
    return q->scores[q->front];
}
char* dequeue_name(queue *q)
{
    if (q->front > q->rear)
        return NULL;
    return q->names[(q->front)++];
}

// Main game loop
int main()
{
    load("words_with_meanings.txt");
    char word[LENGTH], meaning[1000];
    queue *q = initqueue();

    while (1)
    {
        generateRandomWord(word, meaning);
        int wordLength = strlen(word);
        int guessed[wordLength]; // Array to track which letters are guessed
        int incorrectTries = 0;
        int score = 0;
        char* name;
        name = malloc(MAX_NAME_LENGTH*sizeof(char));
        char guess;

        for (int i = 0; i < wordLength; i++)
        {
            guessed[i] = 0;
        }

        printf("Enter your name: ");
        scanf("%s", name);

        printf("\nWelcome to Hangman, %s! Guess the word:\n", name);

        // Game loop
        while (incorrectTries < MAX_TRIES)
        {
            displayWord(word, guessed, meaning);
            printf("\nEnter a letter to guess: ");
            scanf(" %c", &guess);

            // Validate input (only letters)
            if (!isalpha(guess))
            {
                printf("\nInvalid input! Please enter a valid letter.\n");
                continue;
            }

            if (checkGuess(guess, word, guessed))
            {
                printf("\nGood guess!\n");
            }
            else
            {
                incorrectTries++;
                printf("\nWrong guess! You have %d tries left.\n", MAX_TRIES - incorrectTries);
            }

            if (isWordGuessed(guessed, wordLength))
            {
                printf("\nCongratulations! You've guessed the word '%s'.\n", word);
                score += 10; // Add points for correct guess
                break;
            }
        }

        if (incorrectTries == MAX_TRIES)
        {
            printf("\nGame over! You've been hanged. The word was '%s'.\n", word);
            score -= 5; // Deduct points for failure
        }

        enqueue(q,name,score); // Store the score in the queue

        // Ask if the player wants to continue
        char choice;
        printf("\nDo you want to play again? (y/n): ");
        scanf(" %c", &choice);
        if (choice == 'n' || choice == 'N')
        {
            break; // Exit the game loop
        }
    }

    // Display the scores
    printf("\nScores of all players:\n");
    int scr = 0;
    char* player_name;
    scr = dequeue_score(q);
    while (scr != -1)
    {
        player_name = dequeue_name(q);
        printf("Name: %s Score: %d\n", player_name, scr);
        scr = dequeue_score(q);
    }

    unload(); // Unload dictionary and free memory
    return 0;
}
