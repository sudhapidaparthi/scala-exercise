package com.techsophy.training

import java.io.File

import org.slf4j.LoggerFactory

class Exercise {

  val logger = LoggerFactory.getLogger(this.getClass)


  def show(f: Int, n: Int): List[Int] = {
    val flattenList = for (i <- 1 to n) yield i
    val returnList = flattenList flatMap { number: Int =>
      fill(number)(f)
    }
    returnList.toList

  }

  def fill(element: Int)(noOfTimes: Int): List[Int] = {
    if (noOfTimes == 0) List()
    else
      element :: fill(element)(noOfTimes - 1)
  }

  def reverse(list: List[Int]): List[Int] = {
    if (list.isEmpty) List()
    else
      reverse(list.tail) :+ list.head
  }

  def rotate(a: Array[Int], r: Int): Array[Int] = {
    var rList = Array[Int]()
    for (i <- 1 to r) {
      if (i == 1)
        rList = a.tail :+ a.head
      else
        rList = rList.tail :+ rList.head
    }
    rList
  }

  def sum(opt1: Option[Int], opt2: Option[Int]): Option[Int] = {
    if (opt1.nonEmpty && opt2.nonEmpty) Some(opt1.get + opt2.get)
    else
      None
  }

  def concat(list1: List[Int], list2: List[Int]): List[Int] = {
    list1.foldRight(list2) { (element, acc) => element :: acc }
  }

  def wordCount(str: String): Map[String, Int] = {
    str.split("\\s+").toList.groupBy(w => w).mapValues(_.length)
  }

  def dedupe(list: List[Int]): List[Int] = {
    list.foldRight(List(list.last))((element, result) => if (element == result.head) result else element :: result)
  }

  def removeOdd(map: Map[String, Int]): Map[String, Int] = {
    map.foldRight(Map[String, Int]())((element, result) => if (element._2 % 2 != 0) result else Map(result + element._1 -> element._2))
  }

  def concatenate(map1: Map[String, Int], map2: Map[String, Int]): Map[String, Int] = {
    val list = map1.toList ++ map2.toList
    val merged = list.groupBy(_._1).map { case (k, v) => k -> v.map(_._2).sum }
    merged
  }

  def removeKeys(keys: List[String], map: Map[String, Int]): Map[String, Int] = {
    var rMap = map
    for (key <- keys) {
      if (rMap.contains(key)) {
        rMap = rMap - key
      }
    }
    rMap
  }

  def zip(list1: List[Int], list2: List[String]): List[(Int, String)] = {
    (list1, list2) match {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (x :: list1, y :: list2) => (x, y) :: zip(list1, list2)
    }

  }

  def merge(list1: List[Int], list2: List[Int]): List[Int] = {
    (list1, list2) match {
      case (Nil, Nil) => Nil
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        logger.info(s"x = $x ,$xs1 and y=$y ,  $ys1")
        if (x < y) x :: merge(xs1, list2)
        else y :: merge(list1, ys1)
    }
  }

  def appraisal(employees: List[Employee]): List[Employee] = {
    var hra = 0
    employees.map { emp =>
      val salary1 = if (emp.age > 50) {
        hra = 1
        emp.salary.hra + (0.2 * emp.salary.hra)
      }
      else emp.salary.basic + (0.1 * emp.salary.basic)
      if (hra == 1) emp.copy(salary = Salary(emp.salary.basic, salary1, emp.salary.ta))
      else emp.copy(salary = Salary(salary1, emp.salary.hra, emp.salary.ta))
    }
  }

  def spiltByBranch(list: List[Student]): (List[Student], List[Student], List[Student], List[Student]) = {
    var a = (List[Student](), List[Student](), List[Student](), List[Student]())
    list.map { s =>
      (s.branch) match {
        case "CS" => a = (a._1 :+ (s), a._2, a._3, a._4)
        case "IT" => a = (a._1, a._2 :+ (s), a._3, a._4)
        case "EC" => a = (a._1, a._2, a._3 :+ (s), a._4)
        case "ME" => a = (a._1, a._2, a._3, a._4 :+ (s))
      }
    }
    a
  }

  def getCompanyValue(company: Company): Int = {
    val valuesList: List[Int] = for {
      branch <- company.branches
      consultant <- branch.consultants
      customer <- consultant.portfolio
    } yield customer.value
    valuesList.foldLeft(0) { (acc, elem) => (acc + elem) }
  }

  def countFiles(dir: String): Option[Int] = {
    val directory = new File(dir)
    val list = directory.listFiles()
    if (list.length > 0) {
      Some(list.length)
    }
    else None
  }
}

case class Employee(id: Int, email: String, salary: Salary, age: Int)

case class Salary(basic: Double, hra: Double, ta: Double)

case class Student(id: Int, name: String, age: Int, branch: String)

case class Customer(value: Int)

case class Consultant(portfolio: List[Customer])

case class Branch(consultants: List[Consultant])

case class Company(branches: List[Branch])
