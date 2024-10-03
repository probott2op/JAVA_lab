package com.course.structure;
public class Building
{
	int SquareFoot;
	int Stories;
	public void display()
	{
		System.out.println("The Building has squarefoot of "+SquareFoot);
		System.out.println("The number of Stories of the building are "+Stories);
	}
	public Building (int SquareFoot, int Stories)
	{
		this.SquareFoot = SquareFoot;
		this.Stories = Stories;
	}
}

