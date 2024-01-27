package com.fabricio.sevents.api.exceptionhandler.enumeration;

import com.fabricio.sevents.api.exceptionhandler.errors.BadRequestAlertException;
import com.fabricio.sevents.api.exceptionhandler.errors.NotFoundAlertException;
import com.fabricio.sevents.api.exceptionhandler.errors.NoContentAlertException;
import com.fabricio.sevents.api.model.domain.MacroEvent;
import com.fabricio.sevents.api.util.object.Objeto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    GLOBAL_EXPRESSION_SORT_INVALID(new BadRequestAlertException("Ordenação inválida", MacroEvent.class, ErrorCodeEnum.GLOBAL)),

    /*
    * Evento Ancora
    * */
    MACRO_EVENT_NOT_FOUNT(new NotFoundAlertException("Evento macro não encontrado.", MacroEvent.class, ErrorCodeEnum.MACRO_EVENT)),
    MACRO_EVENT_NO_CONTENT(new NoContentAlertException("Eventos macro inexistentes.", MacroEvent.class, ErrorCodeEnum.MACRO_EVENT))
    
    ;

    private final AbstractThrowableProblem ex;


    public void launch() {

        throw ex;

    }

    public static void checkThrow(boolean expression, ExceptionEnum exceptionEnum) {

        if (expression) {
            exceptionEnum.launch();
        }

    }

    public static void checkThrowIsBlank(Object object, ExceptionEnum exceptionEnum) {

        checkThrow(Objeto.isBlank(object), exceptionEnum);

    }

}