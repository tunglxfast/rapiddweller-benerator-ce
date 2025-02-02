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

package com.rapiddweller.domain.person;

import com.rapiddweller.benerator.IllegalGeneratorStateException;
import com.rapiddweller.benerator.InvalidGeneratorSetupException;
import com.rapiddweller.benerator.test.GeneratorClassTest;
import com.rapiddweller.common.collection.ObjectCounter;
import com.rapiddweller.domain.address.Country;
import org.junit.Test;

/**
 * Tests the {@link FamilyNameGenerator}.<br/><br/>
 * Created: 09.06.2006 22:16:06
 * @author Volker Bergmann
 * @since 0.1
 */
public class FamilyNameGeneratorTest extends GeneratorClassTest {

  public FamilyNameGeneratorTest() {
    super(FamilyNameGenerator.class);
  }

  @Test
  public void test_default_us() throws IllegalGeneratorStateException {
    Country defaultCountry = Country.getDefault();
    try {
      Country.setDefault(Country.US);
      ObjectCounter<String> samples = checkDiversity(new FamilyNameGenerator(), 5000, 20);
      assertContains(samples, "Smith", "Johnson");
    } finally {
      Country.setDefault(defaultCountry);
    }
  }

  @Test
  public void test_us() throws IllegalGeneratorStateException {
    FamilyNameGenerator generator = new FamilyNameGenerator("US");
    checkDiversity(generator, 100, 20);
  }

  @Test
  public void test_de() throws IllegalGeneratorStateException {
    FamilyNameGenerator generator = new FamilyNameGenerator("DE");
    checkDiversity(generator, 1000, 100);
  }

  @Test(expected = InvalidGeneratorSetupException.class)
  public void test_xy() throws IllegalGeneratorStateException {
    FamilyNameGenerator generator = new FamilyNameGenerator("XY");
    checkDiversity(generator, 1000, 100);
  }

}
