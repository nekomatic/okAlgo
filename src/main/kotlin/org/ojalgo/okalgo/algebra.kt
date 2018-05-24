package org.ojalgo.okalgo

import org.ojalgo.algebra.Operation
import org.ojalgo.algebra.ScalarOperation
import org.ojalgo.matrix.BasicMatrix
import org.ojalgo.matrix.ComplexMatrix
import org.ojalgo.matrix.PrimitiveMatrix
import org.ojalgo.matrix.RationalMatrix
import java.math.BigDecimal



fun <T, N: Number> Sequence<T>.toPrimitiveMatrix(vararg selectors: (T) -> N): PrimitiveMatrix {
    val items = toList()

    return primitivematrix(items.count(), selectors.count()) {
        populate { row, col ->
            selectors[col.toInt()](items[row.toInt()])
        }
    }
}

fun <T, N: Number> Iterable<T>.toPrimitiveMatrix(vararg selectors: (T) -> N): PrimitiveMatrix {
    val items = toList()

    return primitivematrix(items.count(), selectors.count()) {
        populate { row, col ->
            selectors[col.toInt()](items[row.toInt()])
        }
    }
}


fun <T, N: Number> Sequence<T>.toComplexMatrix(vararg selectors: (T) -> N): ComplexMatrix {
    val items = toList()

    return complexmatrix(items.count(), selectors.count()) {
        populate { row, col ->
            selectors[col.toInt()](items[row.toInt()])
        }
    }
}
fun <T, N: Number> Iterable<T>.toComplexMatrix(vararg selectors: (T) -> N): ComplexMatrix {
    val items = toList()

    return complexmatrix(items.count(), selectors.count()) {
        populate { row, col ->
            selectors[col.toInt()](items[row.toInt()])
        }
    }
}


fun <T, N: Number> Sequence<T>.toRationalmAtrix(vararg selectors: (T) -> N): RationalMatrix {
    val items = toList()

    return rationalmatrix(items.count(), selectors.count()) {
        populate { row, col ->
            selectors[col.toInt()](items[row.toInt()])
        }
    }
}
fun <T, N: Number> Iterable<T>.toRationalmAtrix(vararg selectors: (T) -> N): RationalMatrix {
    val items = toList()

    return rationalmatrix(items.count(), selectors.count()) {
        populate { row, col ->
            selectors[col.toInt()](items[row.toInt()])
        }
    }
}


fun vectorOf(vararg values: Int) = primitivematrix(values.count(), 1) {
    populate { row, col -> values[row.toInt()]  }
}

fun vectorOf(vararg values: Double) = primitivematrix(values.count(), 1) {
    populate { row, col -> values[row.toInt()]  }
}

fun vectorOf(vararg values: Long) = primitivematrix(values.count(), 1) {
    populate { row, col -> values[row.toInt()]  }
}

fun vectorOf(vararg values: BigDecimal) = rationalmatrix(values.count(), 1) {
    populate { row, col -> values[row.toInt()]  }
}


fun primitivematrix(rows: Int, cols: Int, op: (BasicMatrix.Builder<PrimitiveMatrix>.() -> Unit)? = null) =
        PrimitiveMatrix.FACTORY.getBuilder(rows,cols).also {
            if (op != null) op(it)
        }.build()


fun complexmatrix(rows: Int, cols: Int, op: (BasicMatrix.Builder<ComplexMatrix>.() -> Unit)? = null) =
        ComplexMatrix.FACTORY.getBuilder(rows,cols).also {
            if (op != null) op(it)
        }.build()

fun rationalmatrix(rows: Int, cols: Int, op: (BasicMatrix.Builder<RationalMatrix>.() -> Unit)? = null) =
        RationalMatrix.FACTORY.getBuilder(rows,cols).also {
            if (op != null) op(it)
        }.build()

fun <I: BasicMatrix> BasicMatrix.Builder<I>.populate(op: (Long,Long) -> Number) =
        loopAll { row, col -> set(row, col, op(row,col))  }


operator fun <T> Operation.Addition<T>.plus(t: T) = add(t)
operator fun <T> Operation.Division<T>.div(t: T) = divide(t)
operator fun <T> Operation.Multiplication<T>.times(t: T) = multiply(t)
operator fun <T> Operation.Subtraction<T>.minus(t: T) = subtract(t)

operator fun <T, N: Number> ScalarOperation.Addition<T,N>.plus(number: N) = add(number)
operator fun <T, N: Number> ScalarOperation.Division<T,N>.div(number: N) = divide(number)
operator fun <T, N: Number> ScalarOperation.Multiplication<T,N>.times(number: N) = multiply(number)
operator fun <T, N: Number> ScalarOperation.Subtraction<T,N>.minus(number: N) = subtract(number)



