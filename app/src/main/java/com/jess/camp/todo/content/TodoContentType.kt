package com.jess.camp.todo.content

// ENUM : 특정한 패턴을 가진 열거형, 타입을 나열하는 클래스, 상수랑 비슷한 개념
enum class TodoContentType {
    ADD, EDIT, DELETE
    ;

    companion object {
        fun from(name: String?) : TodoContentType? {
            return TodoContentType.values().find {
                it.name.uppercase() == name?.uppercase()
            }
        }
    }
}


enum class Account(val id: String, val pwd: String) {
    NETFLIX("kimhany11@naver.com","rkwkrkwksptvmf01"),
    DISNEY("choco5732@gmail.com","chicken231!"),
    YOUTUBE("choco5732@gmail.com", "Chicken231!!"),
}

fun test() {
    Account.NETFLIX.name // "NETFLIX"
    Account.DISNEY.ordinal // 1
}