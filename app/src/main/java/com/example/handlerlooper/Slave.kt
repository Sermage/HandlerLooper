package com.example.handlerlooper

import java.util.concurrent.ConcurrentLinkedDeque

private const val NAME = "Slave"

class Slave : Thread(NAME) {

    private val taskQueue = ConcurrentLinkedDeque<Runnable>()
    private var alive = true

    init {
        start()
    }

    override fun run() {
        super.run()
        while (alive) {
            taskQueue.poll()?.run()
        }
    }

    fun execute(task: Runnable): Slave {
        taskQueue.add(task)
        return this
    }

    fun quit() {
        alive = false
    }
}