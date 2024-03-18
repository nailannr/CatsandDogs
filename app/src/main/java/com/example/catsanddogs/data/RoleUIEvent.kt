package com.example.catsanddogs.data

sealed class RoleUIEvent {
    data class VeterinarianButtonClicked(val vet: Boolean): RoleUIEvent()
}