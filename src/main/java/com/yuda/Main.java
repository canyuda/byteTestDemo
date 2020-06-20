package com.yuda;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import java.io.File;
import java.io.IOException;

/**
 * CreateUser: canyuda
 * CreateTime: 2020/6/19 11:08
 * Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\study\\testdemo\\src\\main\\java\\HelloWorld.class")); // MyMain.class 文件的字节数组
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM4, cw) {

            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                System.out.println("Main.visit");
                super.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public void visitSource(String source, String debug) {
                System.out.println("Main.visitSource");
                super.visitSource(source, debug);
            }

            @Override
            public void visitOuterClass(String owner, String name, String desc) {
                System.out.println("Main.visitOuterClass");
                super.visitOuterClass(owner, name, desc);
            }

            @Override
            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                System.out.println("Main.visitAnnotation");
                return super.visitAnnotation(desc, visible);
            }

            @Override
            public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
                System.out.println("Main.visitTypeAnnotation");
                return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
            }

            @Override
            public void visitAttribute(Attribute attr) {
                System.out.println("Main.visitAttribute");
                super.visitAttribute(attr);
            }

            @Override
            public void visitInnerClass(String name, String outerName, String innerName, int access) {
                System.out.println("Main.visitInnerClass");
                super.visitInnerClass(name, outerName, innerName, access);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                System.out.println("Main.visitField");
                if (name.equals("AVC")) {
                    return null;
                }
                if (name.equals("a")) {
                    return null;
                }
                return super.visitField(access, name, desc, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                System.out.println("Main.visitMethod");
                StringBuilder sb = new StringBuilder();
                sb.append(access).append(" ").append(name).append(" ").append(desc).append(" ").append(signature).append(" ");
                System.out.println(sb.toString());
                if (name.equals("test2")) {
                    return null;
                }
                if (name.equals("test")) {
                    return null;
                }
                if (name.equals("license")) {
                    return null;
                }
                return super.visitMethod(access, name, desc, signature, exceptions);
            }

            @Override
            public void visitEnd() {
                System.out.println("Main.visitEnd");
                MethodVisitor mv = cv.visitMethod(Opcodes.ACC_STATIC | Opcodes.ACC_PUBLIC, "license", "()Z", null, null);
                mv.visitCode();
                mv.visitInsn(Opcodes.ICONST_0);
                mv.visitInsn(Opcodes.IRETURN);
                mv.visitMaxs(0, 0);
                mv.visitEnd();
            }
        };
        cr.accept(cv, 0);
        byte[] bytes2 = cw.toByteArray();
        FileUtils.writeByteArrayToFile(new File("D:\\study\\testdemo\\src\\main\\java\\test\\HelloWorld.class"), bytes2);
    }
}
