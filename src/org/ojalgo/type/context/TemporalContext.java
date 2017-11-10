/*
 * Copyright 1997-2017 Optimatika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.ojalgo.type.context;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public final class TemporalContext<T extends Temporal> implements TypeContext<T>, TemporalAdjuster {

    private final TemporalAdjuster myAdjuster;
    private final DateTimeFormatter myFormatter;

    TemporalContext(final DateTimeFormatter formatter) {
        super();
        myFormatter = formatter;
        myAdjuster = null;
    }

    TemporalContext(final DateTimeFormatter formatter, final TemporalAdjuster adjuster) {
        super();
        myFormatter = formatter;
        myAdjuster = adjuster;
    }

    public Temporal adjustInto(final Temporal temporal) {
        if (myAdjuster != null) {
            return myAdjuster.adjustInto(temporal);
        } else {
            return temporal;
        }
    }

    @SuppressWarnings("unchecked")
    public T enforce(final T object) {
        return (T) this.adjustInto(object);
    }

    public String format(final Object object) {
        return myFormatter.format((Temporal) object);
    }

    @SuppressWarnings("unchecked")
    public T parse(final CharSequence text) {
        return (T) myFormatter.parse(text);
    }

}
