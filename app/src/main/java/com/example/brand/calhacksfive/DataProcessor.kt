package com.example.brand.calhacksfive
// TODO Add list support for Data Processor operations
// TODO Make sure repetitions do not get added into the lists
// TODO If size = 0. remove it
// TODO If points overlap, must offset each other and not overlap by a random offset; offset shouldn't overlap again
class DataProcessor {
    private val storage = HashMap<String, List<Coordinate>>()
    private val colorstore = HashMap<String, Float>()
    private val keys = mutableListOf<String>()

    fun setColor(location : String, color : Float) {
        colorstore[location] = color
    }
    fun getColor(location : String) : Float {
        return colorstore[location] as Float
    }
    /*Returns coordinates at given location if available, empty string if not*/
    fun getCoordinates(place : String) : List<Coordinate> {
        if (storage.contains(place)) {
            return storage[place] as List<Coordinate>
        } else {
            return mutableListOf()
        }
    }
    fun getAllCoordinates() : List<Coordinate> {
        var list = mutableListOf<Coordinate>()
        for (x in getLocations()) {
            list.plus(storage.get(x) as List<Coordinate>)
        }
        return list
    }
    /*Returns all strings available in the data processor*/
    fun getLocations() : List<String> {
        return keys
    }
    /*Check if storage contains location*/
    fun containsLocation(place : String) : Boolean {
        return storage.containsKey(place)
    }
    /*If storage already contains key, get list stored at key, add given coordinate to key,
    and then set key to updated list. Else if storage key does not yet contain key, turn coordinate
    into list and add into storage with associated key.
     */
    fun addLocation(place : String, coords : Coordinate) {
        if (storage.containsKey(place)) {
            var temp : List<Coordinate> = storage[place] as List<Coordinate>
            if (!temp.contains(coords)) {
                temp.plus(coords)
            }
            storage[place] = temp
        } else {
            keys.add(place)
            storage[place] = mutableListOf(coords)
        }
    }
    /*If storage already contains key, get list at key, append given list to previous list, and
    update key to new list
     */
    fun addLocation(place : String, coords : List<Coordinate>) {
        if (storage.containsKey(place)) {
            var temp : List<Coordinate> = storage[place] as List<Coordinate>
            for (x in coords) {
                if (!temp.contains(x)) {
                    temp.plus(x)
                }
            }
            storage[place] = temp
        } else {
            keys.add(place)
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
            if (temp.isEmpty()) {
                removeLocation(place)
                keys.remove(place)
            } else {
                storage[place] = temp
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
            if (temp.isEmpty()) {
                removeLocation(place)
                keys.remove(place)
            } else {
                storage[place] = temp
            }

        }
    }
    /*If storage has key, remove entire key and its coordinates*/
    fun removeLocation(place : String) {
        if (storage.contains(place)) {
            storage.remove(place)
            keys.remove(place)
        }
    }
}

class Coordinate (xValue : Double, yValue : Double, description : String = "No extra information available.") : Comparable<Coordinate>{
    private var xval = xValue
    private var yval = yValue
    private var descrip = description

    override fun compareTo(other: Coordinate) : Int {
        if (this.xval == other.xval && this.xval == other.yval) {
            return 1
        } else {
            return -1
        }
    }

    fun getxValue() : Double {
        return xval
    }

    fun getyValue() : Double {
        return yval
    }

    fun getDescription() : String {
        return descrip
    }

    fun setxValue(inputX : Double) {
        xval = inputX
    }

    fun setyValue(inputY : Double) {
        yval = inputY
    }

    fun setDescription(newdescrip : String) {
        descrip = newdescrip
    }
}