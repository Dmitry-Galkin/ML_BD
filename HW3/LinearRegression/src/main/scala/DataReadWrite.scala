package data

import breeze.io._
import breeze.linalg._
import java.io._


class DataReadWrite {
  // Чтение и запись данных.

  def readFileCsv(filePath: String): breeze.linalg.DenseMatrix[Double] = {
    // Чтение файла csv и преобразование в матрицу.
    val data = CSVReader.read(
      new FileReader(new File(filePath)),
      separator = ',',
      skipLines = 1
    )
    DenseMatrix.tabulate(
      rows = data.size,
      cols = data(0).size
    ){(i, j) => data(i)(j).toDouble}
  }

  def writeFileCsv(filePath: String, data: DenseVector[Double]): Unit = {
    // Запись результатов в csv файл.
    csvwrite(
      new File(filePath),
      data.toDenseMatrix,
      separator = '\n'
    )
  }

}
