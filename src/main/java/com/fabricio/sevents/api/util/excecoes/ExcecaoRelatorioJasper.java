/*
 * Created on 23/06/2005
 */
package com.fabricio.sevents.api.util.excecoes;

public class ExcecaoRelatorioJasper extends ExcecaoRelatorio {
  private static final long serialVersionUID = 3546641005827600436L;

  public ExcecaoRelatorioJasper() {
    super();
  }

  public ExcecaoRelatorioJasper(String message) {
    super(message);
  }

  public ExcecaoRelatorioJasper(Throwable cause) {
    super(cause);
  }

  public ExcecaoRelatorioJasper(String message, Throwable cause) {
    super(message, cause);
  }
}
