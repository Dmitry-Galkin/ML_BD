package preprocessing

import breeze.linalg._
import breeze.stats._


class Scaler {
  // Стандартизация данных.

  private var mean_ = DenseVector.zeros[Double](0)
  private var std_ = DenseVector.zeros[Double](0)

  def fit(data: DenseMatrix[Double]): Unit = {
    mean_ = mean(data(::, *)).inner
    std_ = stddev(data(::, *)).inner
  }

  def transform(data: DenseMatrix[Double]): DenseMatrix[Double] = {
    val dataNorm = DenseMatrix.zeros[Double](data.rows, data.cols)
    for( i <- 0 to data.cols - 1) {
      dataNorm(::, i) := (data(::, i) - mean_(i)) /:/ std_(i)
    }
    dataNorm
  }

}
