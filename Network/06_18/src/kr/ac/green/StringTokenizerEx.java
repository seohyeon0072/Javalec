package kr.ac.green;

import java.util.StringTokenizer;

public class StringTokenizerEx {
	public static void main(String[] args) {
		String str = "동해물과!백두산이@마르고,닳도록 하느님이 보우하사 우리 나라 만세";
		
		// String낱말 분석
		StringTokenizer st = new StringTokenizer(str, ",!@");
		// 토큰 존재 여부
		while(st.hasMoreTokens()){
			// 스페이스, 엔터, 탭을 기준으로 끊어줌(구분)
			System.out.println(st.nextToken());
		}
	}
}
