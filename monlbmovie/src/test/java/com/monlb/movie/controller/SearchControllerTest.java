package com.monlb.movie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monlb.movie.model.MovieHeader;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class SearchControllerTest {
    // testクラス
    @Autowired
    SearchController searchController;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private WebApplicationContext context;

    /**
     * 初期データ
     * 「/resources/data.sql」参照
     */

    @Test
    @DisplayName("「/favorites」テスト")
    void favorites() throws Exception {
        // given 「/resources/data.sql」参照

        // when
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = mockMvc.perform(
                        get("/favorites")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        List<MovieHeader> resultBody = new ObjectMapper().readValue(body, ArrayList.class);

        // then
        Assert.assertEquals(2, resultBody.size());
    }

    @Test
    @DisplayName("「/favorite/id」テスト、3001追加後、/favoritesで取得して確認")
    void favorite() throws Exception {
        // given 「/resources/data.sql」参照

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // when
        // post 3001追加
        mockMvc.perform(
                        post("/favorite/3001")
                                .contentType("application/json")
                ).andDo(print())
                .andExpect(status().isOk());

        // 取得して、2->3に変更されたのを確認
        MvcResult result = mockMvc.perform(
                        get("/favorites")
                )
                .andExpect(status().isOk())
                .andReturn();


        // then
        String body = result.getResponse().getContentAsString();
        List<MovieHeader> resultBody = new ObjectMapper().readValue(body, ArrayList.class);
        Assert.assertEquals(3, resultBody.size());
    }

    @Test
    @DisplayName("(正常)「/movies?search={A}テスト」")
    void movies_like_search() throws Exception {
        // given 「/resources/data.sql」参照

        // when
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = mockMvc.perform(
                        get("/movies?search=A")
                                .header("API-KEY", "TEST-SECRET-KEY")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // then
        String body = result.getResponse().getContentAsString();
        List<MovieHeader> resultBody = new ObjectMapper().readValue(body, ArrayList.class);
        Assert.assertEquals(1, resultBody.size());
    }

    @Test
    @DisplayName("(正常)「/movies?search={title}テスト」")
    void movies_like_search2() throws Exception {
        // given 「/resources/data.sql」参照

        // when
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = mockMvc.perform(
                        get("/movies?search=title")
                                .header("API-KEY", "TEST-SECRET-KEY")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // then
        String body = result.getResponse().getContentAsString();
        List<MovieHeader> resultBody = new ObjectMapper().readValue(body, ArrayList.class);
        Assert.assertEquals(6, resultBody.size());
    }

    @Test
    @DisplayName("(正常)「/movies?search」テスト")
    void movies_popular() throws Exception {
        // given 「/resources/data.sql」参照

        // when
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = mockMvc.perform(
                        get("/movies")
                                .header("API-KEY", "TEST-SECRET-KEY")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // then
        String body = result.getResponse().getContentAsString();
        List<MovieHeader> resultBody = new ObjectMapper().readValue(body, ArrayList.class);
        Assert.assertEquals(4, resultBody.size());
    }

//    @Test
//    @DisplayName("(異常)「/movies?search={search}」のAPI_KEY未指定")
//    void movies_not_apikey_search() throws Exception {
//        // given 「/resources/data.sql」参照
//
//        // when, then
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        MvcResult result = mockMvc.perform(
//                        get("/movies?search=A")
//                )
//                .andDo(print())
//                .andExpect(status().is(403))
//                .andReturn();
//    }

//    @Test
//    @DisplayName("(異常)「/movies?search」のAPI_KEY間違い設定")
//    void movies_not_apikey_populer() throws Exception {
//        // given 「/resources/data.sql」参照
//
//        // when, then
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        MvcResult result = mockMvc.perform(
//                        get("/movies")
//                                .header("API-KEY", "AAA-BBBB-AAA")
//                )
//                .andDo(print())
//                .andExpect(status().is(403))
//                .andReturn();
//    }

    @Test
    @DisplayName("「/movies/id」テスト")
    void movie() throws Exception {
        // given 「/resources/data.sql」参照

        // when
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = mockMvc.perform(
                        get("/movies/4001")
                                .header("API-KEY", "TEST-SECRET-AAA")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // then
        String body = result.getResponse().getContentAsString();
        MovieHeader resultBody = new ObjectMapper().readValue(body, MovieHeader.class);
        Assert.assertEquals((long) 4001, (long) resultBody.getId());
    }

}