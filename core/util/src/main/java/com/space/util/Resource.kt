package com.space.util

/**
 * A sealed class that encapsulates successful outcome or a failure with string message
 */
sealed class Resource {
    object Success : Resource()
    data class Error(val message: Int) : Resource()
}
