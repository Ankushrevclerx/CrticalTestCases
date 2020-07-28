package com.selenium.mindmatrix.project.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFolder extends ExtentManager{
  
	 public static void zipper() throws Exception {
		 
	        ZipFolder zf = new ZipFolder();
	       
	        String folderToZip = System.getProperty("user.dir")+"\\Reports\\"+folderName;
	        String zipName = System.getProperty("user.dir")+"\\Reports\\"+folderName+".zip";
	        zf.zipFolder(Paths.get(folderToZip), Paths.get(zipName));
	    }
	 
	    private void zipFolder(final Path sourceFolderPath, Path zipPath) throws Exception {
	      final  ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
	        Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {
	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	                zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
	                Files.copy(file, zos);
	                zos.closeEntry();
	                return FileVisitResult.CONTINUE;
	            }
	        });
	        zos.close();
	    }
	

}
