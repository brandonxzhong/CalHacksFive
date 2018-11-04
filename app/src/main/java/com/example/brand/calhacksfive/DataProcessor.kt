package com.example.brand.calhacksfive
// TODO Add list support for Data Processor operations
// TODO Make sure repetitions do not get added into the lists
// TODO If size = 0. remove it
class DataProcessor {
    val storage = HashMap<String, List<Coordinate>>()
    /*If storage already contains key, get list stored at key, add given coordinate to key,
    and then set key to updated list. Else if storage key does not yet contain key, turn coordinate
    into list and add into storage with associated key.
     */
    fun addLocation(place : String, coords : Coordinate) {
        if (storage.containsKey(place)) {
            var temp : List<Coordinate> = storage[place] as List<Coordinate>
            temp.plus(coords)
            storage[place] = temp
        } else {
            storage[place] = mutableListOf(coords)
        }
    }
    /*If storage already contains key, get list at key, append given list to previous list, and
    update key to new list
     */
    fun addLocation(place : String, coords : List<Coordinate>) {
        if (storage.containsKey(place)) {
            var temp : List<Coordinate> = storage[place] as List<Coordinate>
            temp.plus(coords)
            storage[place] = temp
        } else {
            storage[place] = coords
        }
    }
    /*If storage has key, remove specified coordinate. If key does not exist, do nothing*/
    fun removeLocation(place : String, coords : Coordinate) {
        if (storage.contains(place)) {
            var temp : List<Coordinate> = storage[place] as List<Coordinate>
            var index : Int = temp.indexOf(coords)
            if (index != -1) {
                temp.minus(coords)
            }
        }
    }
    /*If storage has key, remove coordinates at given locations if possible*/
    fun removeLocation(place : String, coords : List<Coordinate>) {
        if (storage.contains(place)) {
            var temp: List<Coordinate> = storage[place] as List<Coordinate>
            for (x in coords) {
                if (temp.contains(x)) {
                    temp.minus(x)
                }
            }
            storage[place] = temp

        }
    }
    /*If storage has key, remove entire key and its coordinates*/
    fun removeLocation(place : String) {
        if (storage.contains(place)) {
            storage.remove(place)
        }
    }
}

class Coordinate (xValue : Int, yValue : Int){
    private var xval = xValue
    private var yval = yValue

    fun getxValue() : Int {
        return xval
    }

    fun getyValue() : Int {
        return yval
    }

    fun setxValue(inputX : Int) {
        xval = inputX
    }

    fun setyValue(inputY : Int) {
       yval = inputY
    }
}