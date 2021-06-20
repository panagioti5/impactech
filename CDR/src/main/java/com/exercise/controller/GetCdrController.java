package com.exercise.controller;

import com.exercise.constants.Constants;
import com.exercise.core.utils.CoreUtils;
import com.exercise.entities.*;
import com.exercise.repository.CdrHistoryRepository;
import com.exercise.repository.CdrRepository;
import com.exercise.repository.CdrSyncHistoryRepository;
import com.exercise.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GetCdrController {

    @Autowired
    private CoreUtils coreUtils;

    @Autowired
    private CdrWrapper cdrWrapper;

    private final CdrRepository cdrRepository;
    private final CdrHistoryRepository cdrHistoryRepository;
    private final CdrSyncHistoryRepository cdrSyncHistoryRepository;


    @Autowired
    public GetCdrController(CdrRepository cdrRepository, CdrHistoryRepository cdrHistoryRepository, CdrSyncHistoryRepository cdrSyncHistoryRepository) {
        this.cdrRepository = cdrRepository;
        this.cdrHistoryRepository = cdrHistoryRepository;
        this.cdrSyncHistoryRepository = cdrSyncHistoryRepository;
    }

    @PostMapping("cdr/get_cdr")
    public void retrieveCdrsFromGetCdrApi(@RequestBody GetCdrUrl cdrUrl) throws JSONException, IOException {
        List<Cdr> cdrs = new ArrayList<>();
        System.out.println(cdrUrl.getUri() + Constants.GET_CDR);
        JSONObject jsonObject = new JSONObject(coreUtils.callMicroserviceAPI(null, cdrUrl.getUri() + Constants.GET_CDR, RequestMethod.GET)); //Call get_cdr API to Fetch unmatched calls, Synchronise
        JSONArray data = jsonObject.getJSONArray(Constants.DATA);
        Gson gson = new GsonBuilder().create();
        for(int i=0; i<data.length(); i++){
            cdrs.add(gson.fromJson(data.getJSONObject(i).toString(), Cdr.class));
        }
        cdrWrapper.setCdrs(cdrs); // Wrap JSON Objects
        saveAllCdrs(cdrWrapper);
    }

    private void saveAllCdrs(CdrWrapper cdrs){
        CdrSyncHistory cdrSyncHistory = new CdrSyncHistory();
        cdrRepository.saveAll(cdrs.getCdrs());
        cdrs.getCdrs().forEach(cdr -> cdrHistoryRepository.save(new CdrHistory(cdr)));
        cdrSyncHistory.setUnmatchedCalls(cdrs.getCdrs().size());
        cdrSyncHistoryRepository.save(cdrSyncHistory);
    }

}
