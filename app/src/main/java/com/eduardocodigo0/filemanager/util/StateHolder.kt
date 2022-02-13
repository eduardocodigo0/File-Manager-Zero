package com.eduardocodigo0.filemanager.util

sealed class StateHolder() {

    class Success() : StateHolder()
    class Fail() : StateHolder()
    class None() : StateHolder()

}
