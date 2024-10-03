package com.course.structure;
public class House extends Building
{
	int Bedrooms;
	int Baths;
	public void display()
	{
		super.display();
		System.out.println("The number of bathrooms are "+Baths);
		System.out.println("The number of bedrooms are "+Bedrooms);
	}
	public House (int SquareFoot, int Stories, int Bedrooms, int Baths)
	{
		super(SquareFoot,Stories);
		this.Bedrooms = Bedrooms;
		this.Baths = Baths;
	}

}