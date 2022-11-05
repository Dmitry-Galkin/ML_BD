package metrics

import breeze.linalg._
import breeze.numerics._
import breeze.stats._


class Metrics {
  // Метрики.

  def r2_score(yTrue: DenseVector[Double], yPred: DenseVector[Double]): Double = {
    // R2 метрика.
    val yMean = mean(yTrue)
    val r2 = 1 - sum((yTrue - yPred) *:* (yTrue - yPred)) / sum((yTrue - yMean) *:* (yTrue - yMean))
    r2
  }

  def root_mean_squared_error(yTrue: DenseVector[Double], yPred: DenseVector[Double]): Double = {
    // Среднеквадратическая ошибка.
    val rmse_score = sqrt(sum((yTrue - yPred) *:* (yTrue - yPred)) / yTrue.size)
    rmse_score
  }

}
