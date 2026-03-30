package ch.bbzw.smartfridgebackend

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<SmartFridgeBackendApplication>().with(TestcontainersConfiguration::class).run(*args)
}
