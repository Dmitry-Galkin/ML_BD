package split

import scala.util.Random
import breeze.linalg._


class TrainTestSplit {
  // Разбиение данных на трейн и тест (или X и y).

  def split(data: DenseMatrix[Double], targetNumber: Int, trainSize: Double): (
    DenseMatrix[Double], DenseMatrix[Double], DenseVector[Double], DenseVector[Double]
    ) = {
    // Разбиение на обучающую и валидационную выборки.

    val y = data(::, targetNumber).toDenseVector
    val x = data.delete(col = targetNumber, Axis._1)

    val idsR = Random.shuffle(List.range(0, x.rows))
    val (idsTrain, idsTest) = idsR.splitAt((x.rows * trainSize).toInt)

    val xTrain = x(idsTrain, ::).toDenseMatrix
    val xTest = x(idsTest, ::).toDenseMatrix
    val yTrain = y(idsTrain).toDenseVector
    val yTest = y(idsTest).toDenseVector

    (xTrain, xTest, yTrain, yTest)
  }

  def split(data: DenseMatrix[Double], targetNumber: Int): (
    DenseMatrix[Double], DenseVector[Double]
    ) = {
    // Разбиение на X и y.

    val y = data(::, targetNumber).toDenseVector
    val x = data.delete(col = targetNumber, Axis._1)

    (x, y)
  }

}
