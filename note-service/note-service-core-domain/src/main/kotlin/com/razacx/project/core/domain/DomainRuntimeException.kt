package com.razacx.project.core.domain

import java.lang.RuntimeException

class DomainRuntimeException: RuntimeException {

    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)

}
