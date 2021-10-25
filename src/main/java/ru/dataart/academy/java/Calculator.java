package ru.dataart.academy.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int result = 0;
        try(ZipFile file = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                try(InputStream inputStream = file.getInputStream(entry)) {
                    int i;
                    while ((i = inputStream.read()) != -1) {
                        if (i == character) {
                            result++;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */
    public Integer getMaxWordLength(String zipFilePath) {
        int result = 0;
        try(ZipFile file = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                try(InputStream inputStream = file.getInputStream(entry)) {
                    int i;
                    int temp = 0;
                    while ((i = inputStream.read()) != -1) {
                        while (i != ' ' && i != '\n' && i != '\r' && i != -1) {
                            temp++;
                            i = inputStream.read();
                        }
                        if (temp > result) {
                            result = temp;
                        }
                        temp = 0;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
