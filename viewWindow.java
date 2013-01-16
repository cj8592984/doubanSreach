package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewWindow {
  


	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("豆瓣图书搜索");
		f.setLocation(300, 300);
		f.setSize(400,500);
		f.setVisible(true);
		
		Container contentPane = f.getContentPane();//定义一个容器
		contentPane.setLayout(new GridLayout(2,1));//设置窗口布局
		JPanel p1 = new JPanel();//创建一个面板对象p1
		p1.setPreferredSize(new Dimension(400,100));
		p1.setLayout(new GridLayout(1,3));
		p1.setBorder(BorderFactory.createTitledBorder("操作面板"));
		
		//建立输入图书ISBN码项
		final TextField id = new TextField(5);
		p1.add(new Label("输入图书ISBN:",Label.CENTER));
		p1.add(id);
		Button butt = new Button("搜索");
		p1.add(butt);
		//
		JPanel p2 = new JPanel();//创建一个面板对象p2
		p1.setPreferredSize(new Dimension(400,400));
		p2.setLayout(new GridLayout(11,3));
	    p2.setBorder(BorderFactory.createTitledBorder("显示信息"));
	    //
		final TextField findOk = new TextField(5);
		p2.add(new Label("搜索结果:",Label.CENTER));
		p2.add(findOk);
		//
		final TextField bookName = new TextField(5);
		p2.add(new Label("图书名:",Label.CENTER));
		p2.add(bookName);
		//
		final TextField authorName = new TextField(5);
		p2.add(new Label("图书作者:",Label.CENTER));
		p2.add(authorName);
		//
		final TextField bookIsbn10 = new TextField(5);
		p2.add(new Label("图书ISBN10码:",Label.CENTER));
		p2.add(bookIsbn10);
		//
		final TextField bookIsbn13 = new TextField(5);
		p2.add(new Label("图书ISBN13码:",Label.CENTER));
		p2.add(bookIsbn13);
		//
		final TextField bookPage = new TextField(5);
		p2.add(new Label("图书页数:",Label.CENTER));
		p2.add(bookPage);
		//
		final TextField bookPrice = new TextField(5);
		p2.add(new Label("图书价格:",Label.CENTER));
		p2.add(bookPrice);
		//
		final TextField bookBinding = new TextField(5);
		p2.add(new Label("图书装订方式:",Label.CENTER));
		p2.add(bookBinding);
		//
		final TextField publisher = new TextField(5);
		p2.add(new Label("图书出版社:",Label.CENTER));
		p2.add(publisher);
		//
		final TextField pubdate = new TextField(5);
		p2.add(new Label("图书出版日期:",Label.CENTER));
		p2.add(pubdate);
		//
		final JTextArea bookDetail = new JTextArea(10,20);
		p2.add(new Label("图书内容概要:",Label.CENTER));
		p2.add(new JScrollPane(bookDetail));
		//
		f.add(p1);
		f.add(p2);
		//设置按钮监听
		butt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Books book;
				ISBN isbnTest = new ISBN();
				String isbn = id.getText();//获取输入的图书ISBN码
				String bookjson;
				try {
					bookjson = isbnTest.fetchBookInfoByXML(isbn);
					JSONObject jsonobj=isbnTest.stringToJson(bookjson);				    
				    book = (Books)isbnTest.setBookData();
				    findOk.setText("搜索到此书!");
				    bookName.setText(book.getTitle());
				    authorName.setText(book.getAuthor());
				    bookIsbn10.setText(book.getIsbn10());
				    bookIsbn13.setText(book.getIsbn13());
				    bookBinding.setText(book.getBinding());
				    bookDetail.setText(book.getSummary());
				    bookPage.setText(book.getPage());
				    bookPrice.setText(book.getPrice());
				    publisher.setText(book.getPublisher());
				    pubdate.setText(book.getPubdate());
				    
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					findOk.setText("查无此书!");
					book = null;
					
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					findOk.setText("查无此书!");
					book = null;
				}
			    
			}
		});
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent args) {
			   System.exit(0);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
