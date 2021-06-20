package me.zhoukaiwen.library


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
