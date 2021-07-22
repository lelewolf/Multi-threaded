package com.helele;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @version 1.0.0
 * @description: 通过管道进行线程间的通信
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest39 {
    public static void main(String[] args) throws InterruptedException {
        try {
            final PipedInputStream inputStream = new PipedInputStream();
            final PipedOutputStream outputStream = new PipedOutputStream();
            final WriteData writeData = new WriteData();
            final ReadData readData = new ReadData();
            final ReadThread readThread = new ReadThread(inputStream, readData);
            final WriteThread writeThread = new WriteThread(outputStream, writeData);
            outputStream.connect(inputStream);
            readThread.start();
            writeThread.start();
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static class WriteData {
        public void writeMethod(PipedOutputStream outputStream) {
            try {
                System.out.println("write :");
                for (int i = 0; i < 300; i++) {
                    String outData = "" + (i + 1);
                    outputStream.write(outData.getBytes());
                    System.out.print(outData);
                }
                System.out.println();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static class ReadData {
        public void readMethod(PipedInputStream inputStream) {

            try {
                System.out.println("read :");
                byte[] bytes = new byte[20];
                int readLength = inputStream.read(bytes);
                while (readLength != -1) {
                    String newData = new String(bytes, 0, readLength);
                    System.out.print(newData);
                    readLength = inputStream.read(bytes);
                }
                System.out.println();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ReadThread extends Thread {
        private PipedInputStream inputStream;
        private ReadData readData;

        public ReadThread(PipedInputStream inputStream, ReadData readData) {
            this.inputStream = inputStream;
            this.readData = readData;
        }

        @Override
        public void run() {
            readData.readMethod(inputStream);
        }
    }

    private static class WriteThread extends Thread {
        private PipedOutputStream outputStream;
        private WriteData writeData;

        public WriteThread(PipedOutputStream outputStream, WriteData writeData) {
            this.outputStream = outputStream;
            this.writeData = writeData;
        }

        @Override
        public void run() {
            writeData.writeMethod(outputStream);
        }
    }
}


