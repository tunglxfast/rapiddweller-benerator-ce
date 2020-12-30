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

package com.rapiddweller.benerator.distribution.sequence;

import java.math.BigDecimal;

import com.rapiddweller.benerator.NonNullGenerator;
import com.rapiddweller.benerator.distribution.Sequence;
import com.rapiddweller.benerator.wrapper.WrapperFactory;
import com.rapiddweller.common.BeanUtil;
import com.rapiddweller.common.NumberUtil;

import static com.rapiddweller.common.NumberUtil.*;

/**
 * {@link Sequence} implementation that implements a 'shuffle' behavior, 
 * by continuously incrementing a base value by a constant value and, 
 * when iterated through the number range, restarts with a value incremented by one.
 * The numbers generated by a related generator instance is unique as long as the 
 * generator is not reset.<br/>
 * <br/>
 * Created at 30.06.2009 06:57:35
 * @since 0.6.0
 * @author Volker Bergmann
 */

public class ShuffleSequence extends Sequence {
	
	private BigDecimal increment;

    public ShuffleSequence() {
	    this(null);
    }

    public ShuffleSequence(BigDecimal increment) {
	    this.increment = null;
    }

    @Override
	public <T extends Number> NonNullGenerator<T> createNumberGenerator(
    		Class<T> numberType, T min, T max, T granularity, boolean unique) {
    	if (increment == null)
    		increment = BigDecimal.valueOf(2);
    	if (max == null)
    		max = NumberUtil.maxValue(numberType);
		NonNullGenerator<? extends Number> base;
		if (BeanUtil.isIntegralNumberType(numberType))
			base = new ShuffleLongGenerator(
					toLong(min), toLong(max), toLong(granularity), toLong(increment));
		else
			base = new ShuffleDoubleGenerator(
					toDouble(min), toDouble(max), toDouble(granularity), toDouble(increment));
		return WrapperFactory.asNonNullNumberGeneratorOfType(numberType, base, min, granularity);
    }
    
}
