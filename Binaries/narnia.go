package main

import "fmt"
import "os"

func main() {

const testOne = "Treasure"
const rick = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
const testTwo = "_"
const potato = "You found a potato!"
const testThree = "Planet"
const help = "Available commands are testOne, rick, testTwo, potato, testThree"

if len(os.Args) <= 1 {
    fmt.Println("A parameter is needed!")
    fmt.Println(os.Args)
    return
}

outputToString := os.Args[1]

if (outputToString == "--help") {
    fmt.Println(help)
}

if (outputToString == "testThree") {
    fmt.Println(testThree)
}

if (outputToString == "potato") {
    fmt.Println(potato)
}

if (outputToString == "testTwo") {
    fmt.Println(testTwo)
}

if (outputToString == "rick") {
   fmt.Println(rick)
}

if (outputToString == "testOne") {
   fmt.Println(testOne)
}

}
