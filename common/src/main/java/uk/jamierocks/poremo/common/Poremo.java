/*
 * This file is part of Poremo, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016, Jamie Mansfield <https://www.jamierocks.uk/>
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package uk.jamierocks.poremo.common;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents either the Poremo Client / Server.
 *
 * @author Jamie Mansfield
 */
public abstract class Poremo {

    /**
     * The instance of Poremo.
     * In implementation this will be overridden by reflection.
     */
    private static final Poremo INSTANCE = null;

    /**
     * Gets the instance of Poremo.
     *
     * @return The instance, if available
     * @throws NullPointerException if the instance is not available
     */
    public static Poremo getInstance() {
        checkNotNull(INSTANCE, "Poremo has yet to be initialised!");
        return INSTANCE;
    }

    /**
     * Gets the side of which Poremo is running on.
     *
     * @return The side
     */
    public abstract Side getSide();

}
