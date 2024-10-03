package com.course.structure;
public class School extends Building
{
	int Classes;
	String GradeLevel;
	public void display()
	{
		super.display();
		System.out.println("The number of classrooms are: "+Classes);
		System.out.println("The grade level is "+GradeLevel);
	}
	public School (int SquareFoot, int Stories, int Classes, String GradeLevel)
	{
		super(SquareFoot,Stories);
		this.Classes = Classes;
		this.GradeLevel = GradeLevel;
	}
}