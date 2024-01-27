/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fabricio.sevents.api.util.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitï¿½ria para Emails.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Email {

	private Email() {
	}

	/**
	 * Verificar se um e-mail ï¿½ vï¿½lido.
	 * 
	 * @param email E-mail a ser validado.
	 * @return Verdadeiro caso seja vï¿½lido. Falso, caso contrï¿½rio.
	 */
	public static boolean isValido(String email) {

	     Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	     
	     Matcher m = pattern.matcher(email);
		
		return m.matches();
	}
}