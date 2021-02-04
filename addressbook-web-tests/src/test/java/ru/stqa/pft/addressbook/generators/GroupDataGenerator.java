package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.stqa.pft.addressbook.model.GroupDate;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class GroupDataGenerator {

    @Parameter(names = "-c", description =  "Group count")
    public  int count;
    @Parameter(names = "-f", description =  "Target file")
    public  String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;
    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex) {
            jCommander.usage();
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupDate> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveCsv(groups, new File(file));
        } else if(format.equals("xml")){
           saveXml(groups, new File(file));
        } else {
            System.out.println("Неизвесный формат" + format);
        }
    }

    private void saveCsv(List<GroupDate> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupDate group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }
    private void saveXml(List<GroupDate> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupDate.class);
        String xml = xStream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<GroupDate> generateGroups(int count) {
        List<GroupDate> groups = new ArrayList<GroupDate>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupDate().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}