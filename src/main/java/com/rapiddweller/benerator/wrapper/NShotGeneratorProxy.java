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

package com.rapiddweller.benerator.wrapper;

import com.rapiddweller.benerator.Generator;

/**
 * Generator proxy which forwards a limited number of products from another generator.<br/><br/>
 * Created: 23.04.2010 17:59:14
 * @since 0.6.1
 * @author Volker Bergmann
 */
public class NShotGeneratorProxy<E> extends GeneratorProxy<E> {

    private final long shots;

    private long remainingShots;

    public NShotGeneratorProxy(Generator<E> source, long shots) {
        super(source);
        this.shots = shots;
        this.remainingShots = shots;
    }

    @Override
    public ProductWrapper<E> generate(ProductWrapper<E> wrapper) {
        if (remainingShots <= 0 || (wrapper = super.generate(wrapper)) == null)
            return null;
        this.remainingShots--;
        return wrapper;
    }

    @Override
    public void reset() {
        super.reset();
        remainingShots = shots;
    }

    @Override
    public void close() {
        super.close();
        remainingShots = 0;
    }
    
    @Override
    public String toString() {
    	return getClass().getSimpleName() + '[' + (shots - remainingShots) + '/' + shots + ", " + getSource() + ']';
    }
    
}

