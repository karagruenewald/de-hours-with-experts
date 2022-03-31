package com.labs1904

import java.io.PrintWriter
import scala.collection.immutable.HashMap
import scala.io.Source
import scala.reflect.io.File

/**
 * An ingredient has an amount and a description.
 * @param amount For example, "1 cup"
 * @param description For example, "butter"
 */
case class Ingredient(amount: String, description: String)

object SecretRecipeDecoder {
  val ENCODING: Map[String, String] = HashMap[String, String](
    "y" -> "a",
    "h" -> "b",
    "v" -> "c",
    "x" -> "d",
    "k" -> "e",
    "p" -> "f",
    "z" -> "g",
    "s" -> "h",
    "a" -> "i",
    "b" -> "j",
    "e" -> "k",
    "w" -> "l",
    "u" -> "m",
    "q" -> "n",
    "n" -> "o",
    "l" -> "p",
    "m" -> "q",
    "f" -> "r",
    "o" -> "s",
    "i" -> "t",
    "g" -> "u",
    "j" -> "v",
    "t" -> "w",
    "d" -> "x",
    "r" -> "y",
    "c" -> "z",
    "3" -> "0",
    "8" -> "1",
    "4" -> "2",
    "0" -> "3",
    "2" -> "4",
    "7" -> "5",
    "5" -> "6",
    "9" -> "7",
    "1" -> "8",
    "6" -> "9"
  )

  /**
   * Given a string named str, use the Caeser encoding above to return the decoded string.
   * @param str A caesar-encoded string.
   * @return
   */
  def decodeString(str: String): String  = {
    val listOfChars = str.toCharArray
    var decodedString : String = ""
    listOfChars.foreach(c =>
      if(SecretRecipeDecoder.ENCODING.get(c.toString) != None){
        val decodedLetter = SecretRecipeDecoder.ENCODING.get(c.toString)
        decodedString += decodedLetter.get
      }
      else {
        decodedString += " "
      })
    decodedString
  }


  /**
   * Given an ingredient, decode the amount and description, and return a new Ingredient
   * @param line An encoded ingredient.
   * @return
   */
  def decodeIngredient(line: String): Ingredient = {
    val encoded = line.split("#")
    val ingredient = new Ingredient(decodeString(encoded(0)), decodeString(encoded(1)))
    ingredient
  }


  /**
   * A program that decodes a secret recipe
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val filename = "src/main/resources/secret_recipe.txt"
    val pw = new PrintWriter(new File("decoded_recipe.txt"))

    for (line <- Source.fromFile(filename).getLines) {
      val ingredient = decodeIngredient(line)
      pw.write(s"${ingredient.amount} ${ingredient.description}")
    }
    pw.close
  }
}
