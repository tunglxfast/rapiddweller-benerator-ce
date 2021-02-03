/*
 * (c) Copyright 2006-2020 by rapiddweller GmbH & Volker Bergmann. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, is permitted under the terms of the
 * GNU General Public License.
 *
 * For redistributing this software or a derivative work under a license other
 * than the GPL-compatible Free Software License as defined by the Free
 * Software Foundation or approved by OSI, you must first obtain a commercial
 * license to this software product from rapiddweller GmbH & Volker Bergmann.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * WITHOUT A WARRANTY OF ANY KIND. ALL EXPRESS OR IMPLIED CONDITIONS,
 * REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE
 * HEREBY EXCLUDED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.rapiddweller.domain.br;

import com.rapiddweller.benerator.util.ThreadSafeNonNullGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generates Brazilian CPF numbers. CPF stands for 'Cadastro de Pessoa Fisica'
 * and is a tax payer number assigned to an individual person (Pessoa Fisica).
 *
 * @author Eric Chaves
 * @author Volker Bergmann
 * @since 0.6.0
 */
public class CPFGenerator extends ThreadSafeNonNullGenerator<String> {

  /**
   * flag indicating should return CPF in numeric or formatted form.
   * defaults to true
   */
  private final boolean formatted;
  private final Random random;

  /**
   * Instantiates a new Cpf generator.
   */
  public CPFGenerator() {
    this(false);
  }

  /**
   * Instantiates a new Cpf generator.
   *
   * @param formatted the formatted
   */
  public CPFGenerator(boolean formatted) {
    this.random = new Random();
    this.formatted = formatted;
  }

  private static void addDigit(ArrayList<Integer> digits) {
    int sum = 0;
    for (int i = 0, j = digits.size() + 1; i < digits.size(); i++, j--) {
      sum += digits.get(i) * j;
    }
    digits.add((sum % 11 < 2) ? 0 : 11 - (sum % 11));
  }

  @Override
  public String generate() {
    StringBuilder buf = new StringBuilder();
    ArrayList<Integer> digits = new ArrayList<>();

    for (int i = 0; i < 9; i++) {
      digits.add(random.nextInt(9));
    }
    addDigit(digits);
    addDigit(digits);

    for (Integer digit : digits) {
      buf.append(digit);
    }
    if (this.formatted) {
      buf.insert(3, '.');
      buf.insert(7, '.');
      buf.insert(11, '-');
    }
    return buf.toString();
  }

  @Override
  public Class<String> getGeneratedType() {
    return String.class;
  }

}
