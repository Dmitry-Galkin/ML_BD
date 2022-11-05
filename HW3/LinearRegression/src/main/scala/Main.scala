import data.DataReadWrite
import split.TrainTestSplit
import preprocessing.Scaler
import linearmodel.LinearRegression
import metrics.Metrics


object Main extends App
{
  val dataReadWrite = new DataReadWrite
  val split = new TrainTestSplit
  val scaler = new Scaler
  val linearRegression = new LinearRegression
  val metrics = new Metrics

  // Пути для чтения и сохранения файлов.
  val filePathTrainData = "src/main/data/trainData.csv"
  val filePathTestData = "src/main/data/testData.csv"
  val filePathValPred = "src/main/data/valPred.csv"
  val filePathTestPred = "src/main/data/testPred.csv"

  // Чтение данных
  val trainData = dataReadWrite.readFileCsv(filePath = filePathTrainData)
  val testData = dataReadWrite.readFileCsv(filePath = filePathTestData)

  // ОБУЧЕНИЕ И ВАЛИДАЦИЯ.

  // Разбиение на train и val.
  val (xTrain, xVal, yTrain, yVal) = split.split(
    data = trainData, targetNumber = trainData.cols - 1, trainSize = 0.8
  )
  // Стандартизация данных.
  scaler.fit(data = xTrain)
  val xTrainNorm = scaler.transform(data = xTrain)
  val xValNorm = scaler.transform(data = xVal)
  // Линейная регрессия.
  // linearRegression.fitAnalytical(x = xTrainNorm, y = yTrain)
  linearRegression.fitSGD(x = xTrainNorm, y = yTrain, n_epoch = 100, alpha = 0.001)
  val yValPred = linearRegression.predict(x = xValNorm)
  // Метрики.
  val r2Val = metrics.r2_score(yVal, yValPred)
  val rmseVal = metrics.root_mean_squared_error(yVal, yValPred)
  println("Val metrics:")
  println(s"* R2 = $r2Val")
  println(s"* rmse = $rmseVal")
  // Сохранение результатов.
  dataReadWrite.writeFileCsv(filePath = filePathValPred, data = yValPred)


  // ПРЕДСКАЗАНИЕ НА ТЕСТОВЫХ ДАННЫХ.
  val (xTest, yTest) = split.split(data = testData, targetNumber = testData.cols - 1)
  val xTestNorm = scaler.transform(data = xTest)
  val yTestPred = linearRegression.predict(x = xTestNorm)
  val r2Test = metrics.r2_score(yTest, yTestPred)
  val rmseTest = metrics.root_mean_squared_error(yTest, yTestPred)
  println("Test metrics:")
  println(s"* R2 = $r2Test")
  println(s"* rmse = $rmseTest")
  dataReadWrite.writeFileCsv(filePath = filePathTestPred, data = yTestPred)

}
