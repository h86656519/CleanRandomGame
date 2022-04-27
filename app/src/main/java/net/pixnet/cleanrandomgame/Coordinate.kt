package net.pixnet.cleanrandomgame

data class Coordinate(
    var x: Int,
    var y: Int,
    var inputX: Int,
    var index: Int = ((y - 1) * inputX) + x -1
)
//index 不能小於0