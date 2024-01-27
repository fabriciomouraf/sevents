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

    GLOBAL_EXPRESSAO_SORT_INVALIDO(new BadRequestAlertException("Ordenação inválida", MacroEvent.class, ErrorCodeEnum.GLOBAL)),

    /*
    * Evento Ancora
    * */
    EVENTO_ANCORA_NOT_FOUNT(new NotFoundAlertException("Evento ancora não encontrado.", MacroEvent.class, ErrorCodeEnum.EVENTO_ANCORA)),
    EVENTO_ANCORA_NO_CONTENT(new NoContentAlertException("Eventos ancora inexistentes.", MacroEvent.class, ErrorCodeEnum.EVENTO_ANCORA))
    
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