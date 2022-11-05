package linearmodel

import breeze.linalg._

import scala.util.Random


class LinearRegression {
  // Линейная регрессия.

  private var w = DenseVector.zeros[Double](0)

  def addBias(x: DenseMatrix[Double]): DenseMatrix[Double] = {
    // Добавление свободного члена.
    var xb = DenseMatrix.ones[Double](x.rows,x.cols + 1)
    for (i <- 0 to x.cols - 1) {
      xb(::, i) := x(::, i)
    }
    xb
  }

  def fitAnalytical(x: DenseMatrix[Double], y: DenseVector[Double]): Unit = {
    // Решение задачи линейной регрессии аналитическим путем.
    val xb = addBias(x)
    w = inv(xb.t * xb) * xb.t * y
  }

  def fitSGD(x: DenseMatrix[Double], y: DenseVector[Double], n_epoch: Int, alpha: Double): Unit = {
    // Решение задачи с помощью SGD.

    val xb = addBias(x)
    var idsR = List.empty[Int]

    w = DenseVector.zeros[Double](xb.cols)

    for (_ <- 1 to n_epoch) {
      idsR = Random.shuffle(List.range(0, xb.rows))
      for (i <- 0 to xb.rows - 1) {
        val gradW = -2 * (y(i) - xb(i, ::) * w) *:* xb(i, ::).t
        w -= alpha * gradW
      }
    }

  }

  def predict(x: DenseMatrix[Double]): DenseVector[Double] = {
    val xb = addBias(x)
    val pred = xb * w
    pred
  }

}
