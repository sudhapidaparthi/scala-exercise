package com.techsophy.training

import org.scalatest.FunSuite

class ExerciseTest extends FunSuite {

  val exercise = new Exercise()

  test("Show function test") {
    val result = exercise.show(3, 4)
    val expectedResult = List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4)
    assert(result === expectedResult)
  }
  test("Show the fill operation") {
    val result = exercise.fill(3)(2)
    val expectedResult = List(3, 3)
    assert(result === expectedResult)
  }
  test("Show the reverse operation") {
    val result = exercise.reverse(List(1, 2, 3, 4, 5))
    val expectedResult = List(5, 4, 3, 2, 1)
    assert(result === expectedResult)
  }
  test("Show the rotation") {
    val result = exercise.rotate(Array(1, 2, 3, 4, 5), 2)
    val expectedResult = Array(3, 4, 5, 1, 2)
    assert(result === expectedResult)
  }
  test("Work with option") {
    val result = exercise.sum(Some(1), Some(2))
    val expectedResult = Some(3)
    val resultNone = exercise.sum(None, None)
    val expectedNone = None
    assert(result === expectedResult)
    assert(resultNone === expectedNone)
  }
  test("concatenate list") {
    val result = exercise.concat(List(1, 2, 3), List(4, 5))
    val expectedResult = List(1, 2, 3, 4, 5)
    assert(result === expectedResult)
  }
  test("Concate the maps") {
    val caseSalary = Salary(10000, 5000, 1050)
    val caseEmployee = Employee(1, "s@gmail.com", caseSalary, 21)
    val caseEmployee1 = Employee(2, "s1@gmail.com", caseSalary, 55)
    val result = exercise.appraisal(List(caseEmployee, caseEmployee1))
    val expectedResult = List(Employee(1, "s@gmail.com", Salary(11000.0, 5000.0, 1050.0), 21), Employee(2, "s1@gmail.com", Salary(10000.0, 6000.0, 1050.0), 55))
    assert(result === expectedResult)
  }
  test("word count") {
    val result = exercise.wordCount("Hello how Hello")
    val expectedResult = Map("Hello" -> 2, "how" -> 1)
    assert(result === expectedResult)
  }
  test("dedupe") {
    val result = exercise.dedupe(List(1, 2, 2, 3, 4, 4))
    val expectedResult = List(1, 2, 3, 4)
    assert(result === expectedResult)
  }
  test("remove odd keys") {
    val result = exercise.removeOdd(Map("hello" -> 1, "hi" -> 2, "how" -> 3, "when" -> 4))
    val expectedResult = Map("Map(Map()when -> 4)hi" -> 2)
    assert(result === expectedResult)
  }
  test("remove list of keys") {
    val result = exercise.removeKeys(List("bye"), Map("hello" -> 1, "hi" -> 2, "bye" -> 3))
    val expectedResult = Map("hello" -> 1, "hi" -> 2)
    assert(result === expectedResult)
  }
  test("concatenate maps ") {
    val result = exercise.concatenate(Map("hello" -> 1, "hi" -> 2), Map("hi" -> 2, "bye" -> 3))
    val expectedResult = Map("hello" -> 1, "hi" -> 4, "bye" -> 3)
    assert(result === expectedResult)
  }
  test("students by department") {
    val student1 = Student(1, "abc", 22, "IT")
    val student2 = Student(2, "def", 21, "IT")
    val student3 = Student(3, "ghi", 22, "CS")
    val student4 = Student(4, "jkl", 23, "ME")
    val student5 = Student(5, "mno", 24, "EC")
    val result = exercise.spiltByBranch(List(student1, student2, student3, student4, student5))
    val expected = (List(Student(3, "ghi", 22, "CS")), List(Student(1, "abc", 22, "IT"), Student(2, "def", 21, "IT")), List(Student(5, "mno", 24, "EC")), List(Student(4, "jkl", 23, "ME")))
    assert(result === expected)
  }
  test("zip lists ") {
    val result = exercise.zip(List(1, 2, 3), List("one", "two"))
    val expectedResult = List((1, "one"), (2, "two"))
    var nilResult = exercise.zip(Nil, List())
    var expectedNull = Nil
    assert(result === expectedResult)
    assert(nilResult === expectedNull)
  }
  test("merge sorted lists") {
    val result = exercise.merge(List(1, 3, 5), List(2, 6, 10))
    val expectedResult = List(1, 2, 3, 5, 6, 10)
    val nilMerge = exercise.merge(Nil, Nil)
    val nilExpected = Nil
    assert(result === expectedResult)
    assert(nilMerge === nilExpected)
  }

  test("company value") {
    val customer1 = Customer(45)
    val customer2 = Customer(22)
    val customer3 = Customer(8)
    val customer4 = Customer(17)
    val consultant1 = Consultant(List(customer1, customer2))
    val consultant2 = Consultant(List(customer3, customer4))
    val consultant3 = Consultant(List(customer1, customer4))
    val consultant4 = Consultant(List(customer2, customer3))
    val branch1 = Branch(List(consultant1, consultant2))
    val branch2 = Branch(List(consultant3, consultant4))
    val company = Company(List(branch1, branch2))
    val result = exercise.getCompanyValue(company)
    val expected = 184
    assert(result === expected)
  }
  test("count files in directory") {
    val result = exercise.countFiles("/home/sudha/Downloads")
    val expectedResult = Some(8)
    val nilResult = exercise.countFiles("")
    val nilExpected = None
    var noFiles = exercise.countFiles("/home/sudha/Desktop/sam")
    var noneExpected = None
    assert(result === expectedResult)
    assert(nilResult === nilExpected)
    assert(noFiles === noneExpected)
  }

}
