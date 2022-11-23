package pooplendar.community

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.jboss.logging.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import pooplendar.support.ui.ApiResponse

@RestControllerAdvice
class ControllerExceptionHandler {
    private val logger = Logger.getLogger(javaClass)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchExHandler(e: NoSuchElementException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error(e.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgExHandler(e: IllegalArgumentException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error(e.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException::class)
    fun invalidFormatExHandler(e: InvalidFormatException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error("'${e.path.last().fieldName}'에 잘못된 형식의 값(${e.value})을 전달했습니다.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingKotlinParameterException::class)
    fun missingKtParamExHandler(e: MissingKotlinParameterException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error("Body의 JSON 형식이 잘못되었거나, 파라미터 '${e.parameter.name}'의 값이 누락되었습니다.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingReqParamExHandler(e: MissingServletRequestParameterException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error("필수 요청 파라미터인 '${e.parameterName}'가 없습니다.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun argTypeMismatchExHandler(e: MethodArgumentTypeMismatchException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error("'${e.value}'의 타입은 파라미터 '${e.parameter.parameterName}'에 맞지 않습니다.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun argNotValidExHandler(e: MethodArgumentNotValidException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error("${e.fieldError?.field} ${e.fieldError?.defaultMessage}")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun mismatchExHandler(e: MismatchedInputException): ApiResponse<Unit> {
        logger.error("message", e)
        return ApiResponse.error("다음 필드의 타입이 잘못되었습니다: ${e.path.map { it.fieldName }}")
    }
}
