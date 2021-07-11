package com.baich.bigdata.design_patterns.factory;

import com.baich.bigdata.design_patterns.factory.sub.Circle;
import com.baich.bigdata.design_patterns.factory.sub.Rectangle;
import com.baich.bigdata.design_patterns.factory.sub.Square;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-11
 * Time : 18:21
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ShapeFactory {
    // 使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        }
        return null;
    }
}
