//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pmmlParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import org.dmg.pmml.FieldName;
import pmmlParser.ModelInvoker;

public class ModelCalc {
    public ModelCalc() {
    }

    public static void main(String[] args) throws IOException {
        Scanner sb = new Scanner(System.in);
        String pmmlPath = "./Try_score_GBM.pmml"; //模型用相对路径
        String modelArgsFilePath = "F:\\tdata.csv";//数据用绝对路径，字段名不能有""


        PrintStream ps = new PrintStream("D:\\result_sy2.txt");
        System.setOut(ps);
        ModelInvoker invoker = new ModelInvoker(pmmlPath);
        List paramList = readInParams(modelArgsFilePath);
        int lineNum = 0;
        Iterator var8 = paramList.iterator();

        while(var8.hasNext()) {
            Map param = (Map)var8.next();
            ++lineNum;
            System.out.println("======当前行： " + lineNum + "=======");
            Map result = invoker.invoke(param);
            Set keySet = result.keySet();
            Iterator var12 = keySet.iterator();

            while(var12.hasNext()) {
                FieldName fn = (FieldName)var12.next();
                System.out.println(result.get(fn).toString());
            }
        }

    }

    private static List<Map<FieldName, Object>> readInParams(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String[] nameArr = br.readLine().split(",");
        ArrayList list = new ArrayList();
        String paramLine = null;

        while((paramLine = br.readLine()) != null) {
            HashMap map = new HashMap();
            String[] paramLineArr = paramLine.split(",");

            for(int i = 0; i < paramLineArr.length; ++i) {
                map.put(new FieldName(nameArr[i]), paramLineArr[i]);
            }

            list.add(map);
        }

        return list;
    }
}
