<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGE_SecondOrder 
		(
			SecondPrizeID,
			UserID,
			Status,
			Quantity,
			IpAddress,
			MobileNo,
			HippoId,
			AddDate
		)
		VALUES
		(
			#data.secondPrizeId#,
			#data.userId#,
			#data.status#,
			#data.quantity#,
			#data.ipAddress#,
			#data.mobileNo#,
			#data.hippoId#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_SecondPrize
        (
        Name,
        BeginDate,
        EndDate,
        BannerImage,
        Status,
        Priority,
        Type,
        TypeReferID,
        Price,
        MarketPrice,
        MaxJoin,
        CurrentJoin,
        AddDate,
        UpdateDate
        )
        VALUES
        (
        #data.name#,
        #data.beginDate#,
        #data.endDate#,
        #data.bannerImage#,
        #data.status#,
        #data.priority#,
        #data.type#,
        #data.typeReferId#,
        #data.price#,
        #data.marketPrice#,
        #data.maxJoin#,
        #data.currentJoin#,
        NOW(),
        NOW()
        )
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
		    DP_AdItem(KID, CityID, PlaceID, ContentID, TimeRange, Status, AddTime)
		VALUES 
			(#kid#, #cityId#, #placeId#, #contentId#, #timeRange#, #status#, now())
		   <selectKey keyProperty="id" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GPA_OfflineActivityBlackList
        (UserID,
        BlackListType,
        BeginTime,
        EndTime,
        ValidDays,
        AdminID,
        AddTime,
        UpdateTime)
        VALUES
           <iterate conjunction="," property="blackLists"/>

        ON DUPLICATE KEY UPDATE
        BeginTime=VALUES(BeginTime),
        EndTime=VALUES(EndTime),
        ValidDays=VALUES(ValidDays),
        AdminID=VALUES(AdminID),
        UpdateTime=NOW()
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        	GPA_OfflineActivityPushTask
        (PushID,AddTime,UpdateTime)
        VALUES
        (#pushId#,
        now(),
        now())
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DianPingMC.MC_MemberCardProductConfirmInfo 
			(ShopConfirmInfoID, 
			ProductType, 
			ProductName, 
			ProductDiscountRate, 
			ProductDesc, 
			BeginDate, 
			EndDate, 
			AuthAdminID, 
			AuthAdminName, 
			CreateTime, 
			UpdateTime
			)
			VALUES
			(#shopConfirmInfoID#, 
			#productType#, 
			#productName#, 
			#productDiscountRate#, 
			#productDesc#, 
			#beginDate#, 
			#endDate#, 
			#authAdminID#, 
			#authAdminName#,
			Now(), 
			Now()
			);
			
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO MAT_RoleAuthorityRelation
		(roleId, authorityId, status, addTime)
		VALUES
		(#roleAuthority.roleId#, #roleAuthority.authorityId#, #roleAuthority.status#, now())
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 
		INSERT INTO MAT_RoleAuthorityRelation
		(roleId, authorityId, status, addTime)
		VALUES
		
		   <iterate conjunction="," property="roleAuthorityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
        	GPA_WeixinCouponRecord
        	(EventID,OpenID,UserID,CouponID,StartTime,EndTime,AddTime,UpdateTime)
        VALUES
            (
            #couponRecord.eventId#,
            #couponRecord.openId#,
            #couponRecord.userId#,
            #couponRecord.couponId#,
            #couponRecord.startTime#,
            #couponRecord.endTime#,
            NOW(),
            NOW()
            )
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_UserResetPasswordLog 
    		(UserID,
    		ResetType,
    		VerifyCode,
    		IsValid,
    		AddTime,
    		UpdateTime) 
   		VALUES 
   			(#userId#,
   			#resetType#,
   			#verifyCode#,
   			1,
   			NOW(),
   			NOW())
   		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_User (UserEmail,UserNickName,UserPW,UserAddDate,UserLastDate,
			UserPower,MediaSource,UserSource,UserCity,UserIP,EmailVerifyStatus) 
		VALUES (#userEmail# ,#userNickname# ,#password# ,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP ,
			1 ,252 ,252 ,#cityID# ,#userIP# ,0)
		   <selectKey keyProperty="UserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GPA_OfflineActivityExtract
		(
			OfflineActivityID,
			Status,
			ValidDays,
			ApplyEndTime,
			AddTime,
			UpdateTime
		)
		VALUES
		(
		#extractVO.offlineActivityId#,
		#extractVO.status#,
		#extractVO.validDays#,
		#extractVO.applyEndTime#,
		NOW(),
		NOW())
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GP_EventFollowNote
        VALUES
        (NULL,
        #followNote.offlineActivityId#,
        #followNote.userId#,
        #followNote.noteBody#,
        #followNote.addDate#,
        #followNote.updateDate#,
        #followNote.ip#,
        #followNote.origUserId#,
        #followNote.toUserId#,
        #followNote.title#,
        #followNote.status#)
           <selectKey keyProperty="followNoteId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GPA_OfflineActivityPush(CityID,OfflineActivityID,Title,TYPE,PHASE,PushUrl,PushTime,STATUS,ADDTIME,UpdateTime)
        VALUES
           <iterate conjunction="," property="activityPushVOs"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GPA_OfflineActivityPush(CityID,OfflineActivityID,Title,TYPE,PHASE,PushUrl,PushTime,STATUS,ADDTIME,UpdateTime)
        VALUES
        (
            #activityPushVO.cityId#,
            #activityPushVO.offlineActivityId#,
            #activityPushVO.title#,
            #activityPushVO.type#,
            #activityPushVO.phase#,
            #activityPushVO.pushUrl#,
            #activityPushVO.pushTime#,
            0,
            now(),
            now()
         )
            <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GPA_OfflineActivitySerialNosPoolBatchID (
		Memo,
		AddTime
		)
		VALUES (
		#memo#,
		NOW()
		)
		   <selectKey keyProperty="BatchID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCard(MemberCardType, Source, Status, BgImageID, LogoID ,AuthAdminID,AuthAdminName, AddTime, Title, SubTitle)
			VALUES(#cardType#, #source#, #status#,0,0,#authAdminId#, #authAdminName#, #addTime#, #shopName#, #branchName#)
			
		   <selectKey keyProperty="MemberCardID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardBgImage(IsCustom, Name, PicPath, PicType, AddTime)
			VALUES(#isCustom#, #name#, #picPath#, #picType#, #addTime#)
			
		   <selectKey keyProperty="BgImageID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardLiteralRecom(Title, Url, Status, AddTime, BeginDate, EndDate,RecomType,PicPath,RecomOrder,EventDesc)
			VALUES(#mcl.title#, #mcl.url#, #mcl.status#, #mcl.addTime#, #mcl.beginDate#, #mcl.endDate#,#mcl.recomType#,#mcl.picPath#,#mcl.recomOrder#,#mcl.eventDesc#)
			
		   <selectKey keyProperty="RecomID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardLiteralRecomCity(RecomID, CityID)
			VALUES(#recomID#, #cityID#)
			
		   <selectKey keyProperty="RecomCityID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			INSERT INTO MC_MemberCardLiteralRecomCity(RecomID, CityID) VALUES
 		   <iterate conjunction="," property="cityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardScoreLog(MemberCardID, LogType, Score, Comment, ReferLogID, Status, IsActive, AddTime, AdminID, AdminName)
			VALUES(#memberCardId#, #logType#, #score#, #comment#, #referLogId#, #status#, #isActive#, #addTime#, #adminId#, #adminName#)
			
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardCompanyConfirmInfo 
			(CrmID,
			 CitySelected,
			 CardType,
			 CreateTime,
			 AuthAdminID,
			 AuthAdminName) 
		VALUES 
			(#crmID#, 
			 #citySelected#,
			 #cardType#,
			 #createTime#,
			 #authAdminID#,
			 #authAdminName#)
		   <selectKey keyProperty="companyConfirmInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardShopConfirmInfo 
			(CrmID,
			 CompanyConfirmInfoID,
			 ShopID,
			 ShopName,
			 CreateTime,
			 AuthAdminID,
			 AuthAdminName) 
		VALUES 
			(#crmID#, 
			 #companyConfirmInfoID#,
			 #shopID#,
			 #shopName#,
			 #createTime#,
			 #authAdminID#,
			 #authAdminName#)
		   <selectKey keyProperty="shopConfirmInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	  	INSERT INTO CV_Content
			(Content)
		VALUES 
			(#content#)
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_Account_LastLoginRecord (
        `AddDate`,
        `UpdateDate`,
        `LoginId`,
        `System`,
        `LastLoginTime`
        ) VALUES (
        #lastLoginRecord.addDate#,
        #lastLoginRecord.updateDate#,
        #lastLoginRecord.loginId#,
        #lastLoginRecord.system#,
        #lastLoginRecord.lastLoginTime#
        )

           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MAT_Authority(code, description, type, status, addtime)
        SELECT #authority.code#, #authority.description#, #authority.type#, #authority.status#, now()
        FROM DUAL
        WHERE
        NOT EXISTS( SELECT authorityId FROM MAT_Authority WHERE code = #authority.code#)
        
           <selectKey keyProperty="authorityID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			DP_AdItemContent(Creative, Content, AddTime, UpdateTime)
		VALUES 
			(#creative#, #content#, Now(), Now())
		   <selectKey keyProperty="id" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO CI_CheckIn 
    	(UserID, ShopID, Lng, Lat, PostionRange, Shared, FeedShared, CheckInType, Star, Tips, UserIP, ClientID, UserAgent,PicCenterUrl,PicID, photos, DeviceId, CityID, ShopType, Status, LocalFilter)
        VALUES 
        (#userId#, #shopId#, #lng#, #lat#, #postionRange#, #shared#, #feedShared#, #checkInType#, #star#, #tips#, #userIp#, #clientId#, #userAgent#, #picCenterUrl#, #picId#, #photoUrls#, #deviceId#, #cityId#, #shopType#, #status#, #localFilter#);
    	   <selectKey keyProperty="CheckInID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>


    	
		INSERT INTO CI_HoneyInvitation 
		(InviteUserID, UserID, LastTime, UserIP, Memo)
		VALUES 
		(#InviteUserID#, #UserID#, CURRENT_TIMESTAMP, #UserIP#, #Memo#)		
		   <selectKey keyProperty="InviteUserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
    	
		INSERT INTO CI_Shake 
		(UserID, Lat, Lng, PostionRange) 
		VALUES 
		(#UserID#, #Lat#, #Lng#, #PostionRange#)
    	   <selectKey keyProperty="ShakeID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			GPA_OfflineActivitySubscribe 
			(Type,Email,CityID,ADDTIME,UpdateTime,UserType)
		VALUES
		(#subcribe.type#,#subcribe.email#,#subcribe.cityId#,NOW(),NOW(),#subcribe.userType#)
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GP_VIPEventConfig
        VALUES
        (NULL,
        #cityId#,
        #configType#,
        #configInfo#,
        0,
        NOW(),
        NOW())
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_QQFriendShip
		(OpenID, FriendID, FriendDPUid, FriendFace, FriendNickName, ADDDATE)
		VALUES
		   <iterate conjunction="," property="friendList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardShop(MemberCardID,ShopID,CityID,ShopName,BranchName,Status, AddTime, ShopGroupId, CardGroupID, SecondCatgory, Region, Lat, Lng, POWER)
			VALUES(#memberCardId#, #shopId#, #cityId#, #shopName#, #branchName#, #status#, #addTime#, #shopGroupId#, #cardGroupId#,#category#,#region#,#lat#,#lng#,#power#)
			
		   <selectKey keyProperty="MemberCardShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardShopConfirmInfo 
				(CrmID, 
				CompanyConfirmInfoID, 
				ShopID, 
				ShopName, 
				ShopManager, 
				ShopManagerTel, 
				ProductSelected, 
				OtherMemberCard, 
				AuthAdminID, 
				AuthAdminName, 
				CreateTime, 
				UpdateTime,
				CheckedForBossAccount,
				)
				VALUES
				(#crmID#, 
				#companyConfirmInfoID#, 
				#shopID#, 
				#shopName#, 
				#shopManager#, 
				#shopManagerTel#, 
				#productSelected#, 
				#otherMemberCard#, 
				#authAdminID#, 
				#authAdminName#, 
				Now(), 
				Now(),
				0);
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GPA_ThirdPartyActivity
		VALUES
			(NULL,
			#thirdPartyActivity.sourceType#,
			#thirdPartyActivity.businessId#,
			#thirdPartyActivity.title#,
			#thirdPartyActivity.detailLink#,
			#thirdPartyActivity.body#,
			#thirdPartyActivity.cityName#,
			#thirdPartyActivity.venues#,
			#thirdPartyActivity.dateInfo#,
			#thirdPartyActivity.regionName#,
			#thirdPartyActivity.detailAddress#,
			#thirdPartyActivity.beginDate#,
			#thirdPartyActivity.endDate#,
			#thirdPartyActivity.costMemo#,
			#thirdPartyActivity.typeName#,
			#thirdPartyActivity.poster#,
			#thirdPartyActivity.ticketNum#,
			#thirdPartyActivity.actors#,
			false,
			false,
			NOW(),
			NOW())
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO MAT_User
		(externalId, userType, description, status, addtime)
		VALUES
		(#user.externalId#, #user.userType#, #user.description#,#user.status#,NOW())
		
		   <selectKey keyProperty="UserId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_UserResetPasswordLog 
    		(UserID,
    		ResetType,
    		VerifyCode,
    		IsValid,
    		AddTime,
    		UpdateTime) 
   		VALUES 
   			(#userId#,
   			#resetType#,
   			#verifyCode#,
   			1,
   			NOW(),
   			NOW())
   		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
		    DP_AdItemExt(KID, ShopType, Level, CateogryID, AddTime)
		VALUES 
			(#kid#, #shopType#, #level#, #categoryId#, now())
		   <selectKey keyProperty="id" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DPAdwords.DP_AdwordsTemplate(TemplateTitle, TemplateContent, AddDate)
			VALUES (#templateTitle#, #templateContent#, Now())
			
		   <selectKey keyProperty="templateId" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Comments 
			(UserID, ReplyToUserID, ReplyToCommentID, RootCheckInID, Content, UserIP)
		VALUES 
			(#userID#, #replyToUserID#, #replyToCommentID#, #rootCheckInID#, #content#, #userIP#)
		   <selectKey keyProperty="CommentID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Comments 
			(UserID, ReplyToUserID, ReplyToCommentID, RootCheckInID, Content, UserIP)
		VALUES 
			(#userID#, #replyToUserID#, 0, #rootCheckInID#, #content#, #userIP#)
		   <selectKey keyProperty="CommentID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GPA_OfflineActivityTicket
        (TicketID,
        OfflineActivityID,
        UserID,
        SerialNumber,
        RewardBody,
        BeginTime,
        EndTime,
        SendType,
        PhoneNo,
        Status,
        AddTime,
        VerifyTime,
        VerifyShopID,
        TicketType,
        RewardID)
        VALUES
        (#offlineActivityTicket.ticketId#,
        #offlineActivityTicket.offlineActivityId#,
        #offlineActivityTicket.userId#,
        #offlineActivityTicket.serialNumber#,
        #offlineActivityTicket.rewardBody#,
        #offlineActivityTicket.beginTime#,
        #offlineActivityTicket.endTime#,
        #offlineActivityTicket.sendType#,
        #offlineActivityTicket.phoneNo#,
        #offlineActivityTicket.status#,
        NOW(),
        "0001-01-01",
        0,
        #offlineActivityTicket.ticketType#,
        #offlineActivityTicket.rewardId#)
           <selectKey keyProperty="ticketId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_UserResetPasswordLog 
    		(UserID,
    		ResetType,
    		VerifyCode,
    		IsValid,
    		AddTime,
    		UpdateTime) 
   		VALUES 
   			(#userId#,
   			#resetType#,
   			#verifyCode#,
   			1,
   			NOW(),
   			NOW())
   		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GPA_ThirdPartyActivity
		VALUES
			(NULL,
			#thirdPartyActivity.sourceType#,
			#thirdPartyActivity.businessId#,
			#thirdPartyActivity.title#,
			#thirdPartyActivity.detailLink#,
			#thirdPartyActivity.body#,
			#thirdPartyActivity.cityName#,
			#thirdPartyActivity.venues#,
			#thirdPartyActivity.dateInfo#,
			#thirdPartyActivity.regionName#,
			#thirdPartyActivity.detailAddress#,
			#thirdPartyActivity.beginDate#,
			#thirdPartyActivity.endDate#,
			#thirdPartyActivity.costMemo#,
			#thirdPartyActivity.typeName#,
			#thirdPartyActivity.poster#,
			#thirdPartyActivity.ticketNum#,
			#thirdPartyActivity.actors#,
			false,
			false,
			NOW(),
			NOW())
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_UserResetPasswordLog 
    		(UserID,
    		ResetType,
    		VerifyCode,
    		IsValid,
    		AddTime,
    		UpdateTime) 
   		VALUES 
   			(#userId#,
   			#resetType#,
   			#verifyCode#,
   			1,
   			NOW(),
   			NOW())
   		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO SMS_Queue 
			(MobileNo, Message, Rank) 
		VALUES 
			(#MobileNo#, #Message#, 255)	
		   <selectKey keyProperty="MsgID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingMobile.CI_SceneryOrderDetail 
			(SerialID, UserID, Uuid, TotalPrice, PickUpPlace, OrderDate, PolicyName, EnableCancel, Status, ShopID, 
				Count, TravelDate, Guest, OtherGuest, Notices, ServicePhoneNo, ClientType)
		VALUES
			(#serialId#, #userId#, #uuid#, #totalPrice#, #pickUpPlace#, #orderDate#, #policyName#, #enableCancel#, #status#,
				#shopId#, #count#, #travelDate#, #guest#, #otherGuest#, #notices#, #servicePhoneNo#, #clientType#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GPA_ActivityOperateLog
        VALUES
        (NULL,
        #log.operateType#,
        #log.logInfo#,
        #log.operatePlatform#,
        #log.operaterID#,
        now())
           <selectKey keyProperty="logId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GPA_OfflineActivityJobLog
		(
			Type,
			ActivityID,
			JobStatus,
			ExceptionType,
			FailureInfo,
			SuccessInfo,
			Content,
			OperaterID,
			AddTime,
			UpdateTime
		)
		VALUES
		(
		#log.type#,
		#log.activityId#,
		#log.jobStatus#,
		#log.exceptionType#,
		#log.failureInfo#,
		#log.successInfo#,
		#log.content#,
		#log.operaterId#,
		NOW(),
		NOW())
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GP_VIPClub(UserID,UserRealName,CityID,CityName,Gender,Birthday,Phone,AddtionalInfo,ApplyReason,Prerogative,Status,StartTime,EndTime,Remark,AddTime,UpdateTime,Year)
        VALUES
        (
        #apply.userId#,
        #apply.userRealName#,
        #apply.cityId#,
        #apply.cityName#,
        #apply.gender#,
        #apply.birthday#,
        #apply.phone#,
        #apply.addtionalInfo#,
        #apply.applyReason#,
        #apply.prerogative#,
        1,
        '0001-01-01 00:00:00',
        '0001-01-01 00:00:00',
        #apply.remark#,
        NOW(),
        NOW(),
        #apply.year#)
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GP_VIPClub(UserID,UserRealName,CityID,CityName,Gender,Birthday,Phone,AddtionalInfo,ApplyReason,Prerogative,Status,StartTime,EndTime,Remark,AddTime,UpdateTime,Year)
        VALUES
        (
        #apply.userId#,
        #apply.userRealName#,
        #apply.cityId#,
        #apply.cityName#,
        #apply.gender#,
        #apply.birthday#,
        #apply.phone#,
        #apply.addtionalInfo#,
        #apply.applyReason#,
        #apply.prerogative#,
        #apply.status.status#,
        '0001-01-01 00:00:00',
        '0001-01-01 00:00:00',
        #apply.remark#,
        NOW(),
        NOW(),
        #apply.year#)
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert into GP_VIPWeekStar(CityID,MemberID,Description,ShowStartTime,AddTime,UpdateTime)
        VALUES
           <iterate conjunction="," property="vipWeekStarVOs"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MC_MemberCardBossAccountInfo(CrmID,CompanyName,CompanyManager,CompanyManagerTel,CompanyManagerPosition,CompanyManagerMail,BossAccountCreateStatus,BossAccountCreateAuditInfo,BossAccountCreateTime,BossAccountUpdateTime,AuthAdminID,AuthAdminName) 
        VALUES(#bossData.crmID#,#bossData.companyName#,#bossData.companyManagerName#,#bossData.companyManagerTel#,#bossData.companyManagerPosition#,#bossData.companyManagerMail#,#bossData.bossAccountCreateStatus#,#bossData.bossAccountCreateAuditInfo#,#bossData.bossAccountCreateTime#,#bossData.bossAccountUpdateTime#,#bossData.authAdminId#,#bossData.authAdminName#)
         
           <selectKey keyProperty="companyInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProduct 
			(MemberCardID,
			 AddAccountID,
			 ProductName,
			 ProductDesc,
			 ProductType,
			 BeginDate,
			 EndDate,
			 Status,
			 AddTime,
			 ProductDraftId,
			 CrmID) 
		VALUES 
			(#memberCardId#, 
			 #addAccountId#,
			 #productName#, 
			 #productDesc#, 
			 #productType#, 
			 #beginDate#,
			 #endDate#,
			 #status#,
			 #addTime#,
			 #productDraftId#,
			 #crmId#)
		   <selectKey keyProperty="ProductID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductDiscount 
			(ProductID,
			 DiscountRate) 
		VALUES 
			(#productId#, 
			 #discount#)
		   <selectKey keyProperty="ProductDiscountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductShop 
			(ProductID,
			 ShopID,
			 MemberCardID,
			 Status,
			 AddTime) 
		VALUES 
			(#productId#, 
			 #shopId#,
			 #memberCardId#,
			 #status#,
			 #addTime#)
		   <selectKey keyProperty="ProductShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductMallPromo
			(ProductID,
			 PromoTitle,
			 PromoDesc,
			 Tel)
		VALUES 
			(#productId#,
			 #promoTitle#,
			 #promoDesc#,
			 #tel#)
		   <selectKey keyProperty="PromoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductMallShopPromo
			(ProductID,
			 ShopType,
			 Floor,
			 Building,
			 PromoTitle,
			 PromoDesc,
			 Tel)
		VALUES 
			(#productId#,
			 #shopType#,
			 #floor#,
			 #building#,
			 #promoTitle#,
			 #promoDesc#,
			 #tel#)
		   <selectKey keyProperty="promoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProduct_Draft
			(ProductID,
			 MemberCardID,
			 AddAccountID,
			 ProductName,
			 ProductDesc,
			 ProductType,
			 BeginDate,
			 EndDate,
			 ActionType,
			 Reason,
			 Status,
			 AddTime,
			 CrmID) 
		VALUES 
			(#productID#,
			 #memberCardID#, 
			 #addAccountID#,
			 #productName#, 
			 #productDesc#, 
			 #productType#, 
			 #beginDate#,
			 #endDate#,
			 #actionType#,
			 #reason#,
			 #status#,
			 #addTime#,
			 #crmId#)
		   <selectKey keyProperty="ProductDraftID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductShop_Draft 
			(ProductDraftID,
			 ShopID,
			 MemberCardID,
			 Status,
			 AddTime) 
		VALUES 
			(#productDraftId#, 
			 #shopID#, 
			 #memberCardID#, 
			 #status.value#, 
			 #addTime#)
		   <selectKey keyProperty="ProductShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductDiscount_Draft
			(ProductDraftID,
			 DiscountRate) 
		VALUES 
			(#productDraftId#, 
			 #discountRate#)
		   <selectKey keyProperty="ProductDiscountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CV_ContentVerify 
			(ID, ContentType, UserID, ContentID, IP, DPVerifyStatus, 
			DPVerifyTips, HLVerifyStatus, HLVerifyTips, HLVerifyTime, 
			NeedAdmin, SysVerifyTime, ManVerifyResult, AdminID, ReverifyResult, ReverifyAdminID, ContentCreateTime, BizID, ReferID)
		VALUES
			(#cv.contentVerifyId#, #cv.contentType#, #cv.userId#, #cv.contentId#, #cv.ip#, #cv.dpVerifyStatus#, 
			#cv.dpVerifyTips#, #cv.hlVerifyStatus#, #cv.hlVerifyTips#, #cv.hlVerifyTime#, 
			#cv.needAdmin#, #cv.sysVerifyTime#, #cv.manVerifyResult#, #cv.adminId#, #cv.reverifyResult#, #cv.reverifyAdminId#, #cv.contentCreateTime#,
			#cv.bizId#, #cv.referId#)
		   <selectKey keyProperty="contentVerifyId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO MAT_UserRoleRelation
		(userId, roleId, status, addTime)
		VALUES
		(#userRole.userId#, #userRole.roleId#, #userRole.status#, now())
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 
		INSERT INTO MAT_UserRoleRelation
		(userId, roleId, status, addTime)
		VALUES
		
		   <iterate conjunction="," property="userRoleList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MAT_Role(description, type, status, addtime)
        VALUES (#role.description#, #role.type#, #role.status#, now())
        
           <selectKey keyProperty="roleID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        GPA_OfflineActivity(
           <isNotEmpty property="title"/>
   <isNotEmpty prepend="," property="beginTime"/>
   <isNotEmpty prepend="," property="endTime"/>
   <isNotEmpty prepend="," property="applyBeginTime"/>
   <isNotEmpty prepend="," property="applyEndTime"/>
   <isNotEqual compareValue="0" prepend="," property="cityId"/>

        ,RegionID
           <isNotEmpty prepend="," property="address"/>
   <isNotEqual compareValue="0" prepend="," property="createUserId"/>
   <isNotEqual compareValue="0" prepend="," property="createAdminId"/>
   <isNotEqual compareValue="0" prepend="," property="property"/>
   <isNotEqual compareValue="0" prepend="," property="type"/>
   <isNotEqual compareValue="0" prepend="," property="mode"/>

        ,Body,`Desc`
           <isNotEqual compareValue="0" prepend="," property="cost"/>
   <isNotEmpty prepend="," property="costMemo"/>
   <isNotEqual compareValue="0" prepend="," property="joinCount"/>
   <isNotEmpty prepend="," property="poster"/>
   <isNotEmpty prepend="," property="extraLimit"/>
   <isNotEmpty prepend="," property="applyExtendInfo1"/>
   <isNotEmpty prepend="," property="applyExtendInfo2"/>
   <isNotEmpty prepend="," property="applyExtendInfo3"/>

        ,AddTime,UpdateTime,IsShowInGroup
           <isNotEmpty prepend="," property="scheduledReleaseTime"/>
   <isNotEmpty prepend="," property="saleEmails"/>

        )
        VALUES(
           <isNotEmpty property="title"/>
   <isNotEmpty prepend="," property="beginTime"/>
   <isNotEmpty prepend="," property="endTime"/>
   <isNotEmpty prepend="," property="applyBeginTime"/>
   <isNotEmpty prepend="," property="applyEndTime"/>
   <isNotEqual compareValue="0" prepend="," property="cityId"/>

        ,#regionId#
           <isNotEmpty prepend="," property="address"/>
   <isNotEqual compareValue="0" prepend="," property="createUserId"/>
   <isNotEqual compareValue="0" prepend="," property="createAdminId"/>
   <isNotEqual compareValue="0" prepend="," property="property"/>
   <isNotEqual compareValue="0" prepend="," property="type"/>
   <isNotEqual compareValue="0" prepend="," property="mode"/>

        ,#body#,#desc#
           <isNotEqual compareValue="0" prepend="," property="cost"/>
   <isNotEmpty prepend="," property="costMemo"/>
   <isNotEqual compareValue="0" prepend="," property="joinCount"/>
   <isNotEmpty prepend="," property="poster"/>
   <isNotEmpty prepend="," property="extraLimit"/>
   <isNotEmpty prepend="," property="applyExtendInfo1"/>
   <isNotEmpty prepend="," property="applyExtendInfo2"/>
   <isNotEmpty prepend="," property="applyExtendInfo3"/>

        ,now(),now(),1
           <isNotEmpty prepend="," property="scheduledReleaseTime"/>
   <isNotEmpty prepend="," property="saleEmails"/>

        )
           <selectKey keyProperty="offlineActivityId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO ECP_DEAL 
			(TITLE,
			DESCRIPTION, 
			DEAL_PRICE, 
			PARTNER_DEAL_ID, 
			PUBLISH_DEAL_ID, 
			DEAL_GROUP_ID,
			MARKET_PRICE,
			COST,
			MAX_JOIN,
			BEGIN_DATE,
			END_DATE,
			TRAFFIC_INFO,
			CONTACT_INFO,
			MEMO,
			CREATE_TIME,
			LAST_UPDATE_TIME
			)
			VALUES
			(#title#,
			#description#,
			#price#,
			#partnerDealId#, 
			#publishDealId#, 
			#dealGroupId#, 
			#marketPrice#,
			#cost#,
			#maxJoin#,
			#validBeginDate#,
			#validEndDate#,
			#trafficInfo#,
			#contactInfo#,
			#memo#,
			#createTime#,
			#lastUpdateTime#
			);
		
           <selectKey keyProperty="dealId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into FS_ATRecord
		            (AccountID,
		             Amount,
		             RecordStatus,
		             AddDate,
		             UpdateDate,
		             ATType)
			values (
			        #aTRecordData.accountId#,
			        #aTRecordData.amount#,
			        #aTRecordData.recordStatus#,
			        NOW(),
			        NOW(),
			        #aTRecordData.aTType#
			       );
 		
           <selectKey keyProperty="ATRecordID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into FS_ATRecordDetail
		            (ATRecordID,
		             ContractGlobalID,
		             DealID,
		             ShopID,
		             Amount)
			values (
			        #aTRecordDetailData.aTRecordId#,
			        #aTRecordDetailData.contractGlobalId#,
			        #aTRecordDetailData.dealId#,
			        #aTRecordDetailData.shopId#,
			        #aTRecordDetailData.amount#
			       );
 		
           <selectKey keyProperty="ATRecordDetailID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_ADMISSION(DEAL_GROUP_ID,IS_ADMISSION_REQUIRED,AMOUNT,STATUS_ID,CREATE_TIME,LAST_UPDATE_TIME,CREATOR_ID,LAST_UPDATOR_ID) VALUES
		(#entity.dealGroupId#,#entity.isAdmissionRequired#,#entity.amount#,#entity.statusId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.creatorId#,#entity.lastUpdatorId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_EDITOR
                (DEAL_GROUP_ID,
                EDITOR_ID,
                EDITOR_NAME,
                DEAL_GROUP_PRODUCE_STATUS,
                CREATOR_ID,
                LAST_UPDATOR_ID,
                CREATE_TIME,
                LAST_UPDATE_TIME,
                VERSION_ID)
        VALUES(#entity.dealGroupId#,
              #entity.editorId#,
              #entity.editorName#,
              #entity.dealGroupProduceStatus,handler=dealGroupProduceStatusEnumTypeHandler#,
              #entity.creatorId#,
              #entity.lastUpdatorId#,
              #entity.createTime#,
              #entity.lastUpdateTime#,
              #entity.versionId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.templateId#,'ImageTextComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_MESSAGE_QUEUE
            (COMMENT,
            TOPIC,
            CONTENT,
            MAX_RETRY_TIMES,
            CURRENT_RETRY_TIMES,
            STATUS_ID,
            CREATE_TIME,
            LAST_EXECUTE_TIME,
            NEXT_EXECUTE_TIME
            )
        VALUES
            (#entity.comment#,
            #entity.topic#,
            #entity.content#,
            #entity.maxRetryTimes#,
            #entity.currentRetryTimes#,
            #entity.statusId#,
            #entity.createTime#,
            #entity.lastExecuteTime#,
            #entity.nextExecuteTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_FILE_ATTACHMENT 
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		FILE_NAME,
		RELATIVE_PATH,
		DEAL_GROUP_ID,
		DTYPE)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.fileName#,
		#entity.relativePath#,
		#entity.dealGroup.id#,
		'QualifiedAttachment'
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_RESOURCE_ROLE_AUTHORITY_CONFIG
            (
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            SOURCE_SYSTEM,
            ROLE_ID,
            POWER_CODE
            )
        VALUES
            (
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.sourceSystem#,
            #entity.roleId#,
            #entity.powerCode#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_SALES_TEAM
            (TEAM_NAME,
            TEAM_CITY_ID,
            TEAM_CITY_NAME,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME,
            VERSION_ID
            )
        VALUES
            (#entity.teamName#,
            #entity.teamCityId#,
            #entity.teamCityName#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#,
            #entity.versionId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_LIST_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VISUAL_COMPONENT_ID,SEQ,CONTENT,IS_READ_ONLY,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.visualComponent.id#,#entity.sequence#,#entity.content#,#entity.isReadOnly#,#entity.templateId#,'TextItem')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_COMPOSITABLE_STATEMENT_TEMPLATE_ASSN
			(CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			COMPOSITABLE_TEMPLATE_ID,
			STATEMENT_TEMPLATE_ID)
		VALUES
			(#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.compositableTemplate.id#,
			#entity.statementTemplateId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_TEMPLATE_ENTRY 
			(DOCUMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			IS_MANDATORY,
			AREA_TYPE_ID,
			SEQUENCE,
			DTYPE)
		VALUES
			(#entity.documentTemplate.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.isMandatory#,
			#entity.areaTypeId#,
			#entity.sequence#,
			'CompositableTemplate'
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DOCUMENT_TEMPLATE 
			(DOCUMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VERSION_ID, 
			NAME, 
			IS_ACTIVE,
			IS_OFFLINE)
		VALUES
			(#entity.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.versionId#,
			#entity.name#,
			#entity.isActive#,
			#entity.isOffline#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN
			(CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			DOCUMENT_TEMPLATE_ID,
			NAV_CATEGORY_ID)
		VALUES
			(#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.documentTemplate.id#,
			#entity.categoryId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT 
			(DOCUMENT_BUILDER_ID, 
			STATEMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME)
		VALUES
			(#entity.documentBuilder.id#,
			#entity.statementTemplateId#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_CONFIGURABLE_BLOCK 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,TYPE_ID,VISUAL_VIEW_ID,TITLE,TEMPLATE_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.typeId#,#entity.visualView.id#,#entity.title#,#entity.templateId#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO 
		CSC_CaseComment
		(
			AddTime,
			LoginId,
			CaseId,
			CommentContent
		)
		VALUES
		(
			now(),
			#cscCaseComment.loginId#,
			#cscCaseComment.caseId#,
			#cscCaseComment.commentContent#
		)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			CSC_CaseHistory
			(
				AddTime,
				CaseId,
				LoginId,
				LoginName,
				FieldName,
				BeforeValue,
				AfterValue,
				Memo
			)
		VALUES
			(
				now(),
				#cscCaseHistory.caseId#,
				#cscCaseHistory.loginId#,
				#cscCaseHistory.loginName#,
				#cscCaseHistory.fieldName#,
				#cscCaseHistory.beforeValue#,
				#cscCaseHistory.afterValue#,
				#cscCaseHistory.memo#
			)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO 
		CSC_CaseOperation
		(
			AddTime,
			LoginId,
			LoginName,
			CaseId,
			ReferId,
			OperationType,
			OperationValue
			
		)
		VALUES
		(
			now(),
			#caseOperation.loginId#,
			#caseOperation.loginName#,
			#caseOperation.caseId#,
			#caseOperation.referId#,
			#caseOperation.operationType#,
			#caseOperation.operationValue#
		)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO 
		CSC_CaseOtherBusiness
		(
			AddTime,
			ShopId,
			ShopName,
			ReservationName,
			ContactWay,
			ReservationTime,
			IsNeedInvoice,
			IsReceiveRebate,
			DeliveryAddress,
			InvoiceAmount,
			InvoiceTitle,
			cityName,
			DealGroupId,
			DealGroupName,
			CaseId,
			HotelOrderId,
			ThirdPartyName
		)
		VALUES
		(
			now(),
			#cscCaseOtherBusiness.shopId#,
			#cscCaseOtherBusiness.shopName#,
			#cscCaseOtherBusiness.reservationName#,
			#cscCaseOtherBusiness.contactWay#,
			#cscCaseOtherBusiness.reservationTime#,
			#cscCaseOtherBusiness.isNeedInvoice#,
			#cscCaseOtherBusiness.isReceiveRebate#,
			#cscCaseOtherBusiness.deliveryAddress#,
			#cscCaseOtherBusiness.invoiceAmount#,
			#cscCaseOtherBusiness.invoiceTitle#,
			#cscCaseOtherBusiness.cityName#,
			#cscCaseOtherBusiness.dealGroupId#,
			#cscCaseOtherBusiness.dealGroupName#,
			#cscCaseOtherBusiness.caseId#,
			#cscCaseOtherBusiness.hotelOrderId#,
			#cscCaseOtherBusiness.thirdPartyName#
		)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO CSC_RefundRemind (
				Type,
				ReferID,
				TgOrderID,
				DealGroupID,
				ContactName,
				Email,
				EmailTitle,
				EmailInfo,
				MobileNo,
				SmsInfo,
				DailyMobileNo,
				DailySmsInfo,
				Status,
				Memo,
				AddDate
			)
			VALUES
				(
					#cscRefundRemind.type#,
					#cscRefundRemind.referId#,
					#cscRefundRemind.tgOrderId#,
					#cscRefundRemind.dealGroupId#,
					#cscRefundRemind.contactName#,
					#cscRefundRemind.email#,
					#cscRefundRemind.emailTitle#,
					#cscRefundRemind.emailInfo#,
					#cscRefundRemind.mobileNo#,
					#cscRefundRemind.smsInfo#,
					#cscRefundRemind.dailyMobileNo#,
					#cscRefundRemind.dailySmsInfo#,
					#cscRefundRemind.status#,
					#cscRefundRemind.memo#,
					now()
				)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CTI_PhoneNumberList (
        PhoneNumber,
        Code,
        City,
        CardType,
        AddTime
        )
        VALUES
        (
        #phoneNumber#,
        #code#,
        #city#,
        #cardType#,
        now()
        );
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into FS_ShopFundAccountFlow
		            (FAID,
		             FlowAmount,
		             FlowType,
		             SourceType,
		             ExchangeOrderID,
		             AddDate,
		             LastUpdateDate,
		             Sequence,
		             Memo,
		             AddLoginID
		             )
			values (#shopFundAccountFlowData.fundAccountId#,
			         #shopFundAccountFlowData.flowAmount#,
			         #shopFundAccountFlowData.flowType#,
			         #shopFundAccountFlowData.sourceType#,
			         #shopFundAccountFlowData.exchangeOrderId#,
			         NOW(),
			         NOW(),
			         #shopFundAccountFlowData.sequence#,
			         #shopFundAccountFlowData.memo#,
			         #shopFundAccountFlowData.addLoginId#
		            );
 		
           <selectKey keyProperty="fundAccountFlowId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO FS_MonitorException
        (PPID,
        ExceptionType,
        AddDate,
        Status)
        VALUES (
        #exceptionData.ppId#,
        #exceptionData.exceptionType#,
        #exceptionData.addDate#,
        #exceptionData.status#)
           <selectKey keyProperty="ExceptionID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO FS_MonitorTodo
        (PPID,
        AddDate,
        Status)
        VALUES (
        #todoData.ppId#,
        #todoData.addDate#,
        #todoData.status#)
           <selectKey keyProperty="todoId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into PC_BalanceRelation
		            (BPID,
		             BRID,
		             FSPPID,
		             FSRPID,
		             RelationType,
		             BalanceAmount,
		             AddDate
		             )
			values (#pcBalanceRelationData.bpId#,
			        #pcBalanceRelationData.brId#,
			        #pcBalanceRelationData.fsPPId#,
			        #pcBalanceRelationData.fsRPId#,
			        #pcBalanceRelationData.relationType#,
			        #pcBalanceRelationData.balanceAmount#,
			        now()
			        );
 		
           <selectKey keyProperty="balanceId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO PC_BillingPayable
        (PlanDate,
        PlanAmount,
        CustomerGlobalID,
        CompanyGlobalID,
        ContractGlobalID,
        DealGroupID,
        DealID,
        ShopID,
        Status,
        AddDate,
        LastUpdateDate,
        SettleLevel,
        BalanceType
        )
        VALUES (#pcBillingPayableData.planDate#,
        #pcBillingPayableData.planAmount#,
        #pcBillingPayableData.customerGlobalId#,
        #pcBillingPayableData.companyGlobalId#,
        #pcBillingPayableData.contractGlobalId#,
        #pcBillingPayableData.dealGroupId#,
        #pcBillingPayableData.dealId#,
        #pcBillingPayableData.shopId#,
        #pcBillingPayableData.status#,
        now(),
        now(),
        #pcBillingPayableData.settleLevel#,
        #pcBillingPayableData.balanceType#
        )
        ON DUPLICATE KEY UPDATE
        BPID = LAST_INSERT_ID(BPID),
        PlanAmount = PlanAmount + #pcBillingPayableData.planAmount#,
        LastUpdateDate = NOW();
           <selectKey keyProperty="bpId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into PC_ActivityVoucher
		            (SerialNumber,
		             VoucherType,
		             OrderID,
		             UserID,
		             ShopID,
		             DealGroupID,
		             DealID,
		             ShopAccountID,
		             CardProductID,
		             CardName,
		             ReceiptAccountID,
		             Details,
		             Commission,
		             Quantity,
		             Price,
		             TotalPrice,
		             Cost,
		             TotalCost,
		             DealPrice,
		             DealCost,
		             DealMarketPrice,
		             DealBeginDate,
		             DealEndDate,
		             VoucherDate,
		             AddDate,
		             APStatus,
		             InvoiceStatus,
		             RevenueStatus,
		             CostStatus)
			values (#pcActivityData.serialNumber#,
			        #pcActivityData.voucherType#,
			        #pcActivityData.orderId#,
			        #pcActivityData.userId#,
			        #pcActivityData.shopId#,
			        #pcActivityData.dealGroupId#,
			        #pcActivityData.dealId#,
			        #pcActivityData.shopAccountId#,
			        #pcActivityData.cardProductId#,
			        #pcActivityData.cardName#,
		            #pcActivityData.receiptAccountId#,
		            #pcActivityData.details#,
		            #pcActivityData.commission#,
		            #pcActivityData.quantity#,
		            #pcActivityData.price#,
		            #pcActivityData.totalPrice#,
		            #pcActivityData.cost#,
		            #pcActivityData.totalCost#,
		            #pcActivityData.dealPrice#,
		            #pcActivityData.dealCost#,
		            #pcActivityData.dealMarketPrice#,
		            #pcActivityData.dealBeginDate#,
		            #pcActivityData.dealEndDate#,
		            #pcActivityData.voucherDate#,
		            now(),
		            #pcActivityData.apStatus#,
		            #pcActivityData.invoiceStatus#,
		            #pcActivityData.revenueStatus#,
		            #pcActivityData.costStatus#);
 		
           <selectKey keyProperty="voucherId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_BillingReceivable
        (PlanDate,
        PlanAmount,
        CustomerGlobalID,
        CompanyGlobalID,
        ContractGlobalID,
        DealGroupID,
        DealID,
        ShopID,
        Status,
        AddDate,
        LastUpdateDate,
        SettleLevel,
        BalanceType,
        ReceivableType,
        DataSource,
        CalParameters,
        AddType,
        AddLoginId,
        LastUpdateLoginId,
        ReferId,
        PaidAmount
        )
        VALUES (#tgBillingReceivableData.planDate#,
        #tgBillingReceivableData.planAmount#,
        #tgBillingReceivableData.customerGlobalId#,
        #tgBillingReceivableData.companyGlobalId#,
        #tgBillingReceivableData.contractGlobalId#,
        #tgBillingReceivableData.dealGroupId#,
        #tgBillingReceivableData.dealId#,
        #tgBillingReceivableData.shopId#,
        #tgBillingReceivableData.status#,
        #tgBillingReceivableData.addDate#,
        #tgBillingReceivableData.addDate#,
        #tgBillingReceivableData.settleLevel#,
        #tgBillingReceivableData.balanceType#,
        #tgBillingReceivableData.receivableType#,
        #tgBillingReceivableData.dataSource#,
        #tgBillingReceivableData.calParameters#,
        #tgBillingReceivableData.addType#,
        #tgBillingReceivableData.addLoginId#,
        #tgBillingReceivableData.lastUpdateLoginId#,
        #tgBillingReceivableData.referId#,
        #tgBillingReceivableData.paidAmount#
        )
        ON DUPLICATE KEY UPDATE
        BRID = LAST_INSERT_ID(BRID),
        PlanAmount = PlanAmount + #tgBillingReceivableData.planAmount#,
        LastUpdateDate = #tgBillingReceivableData.addDate#,
        LastUpdateLoginId = #tgBillingReceivableData.lastUpdateLoginId#,
        ReceivableType = #tgBillingReceivableData.receivableType#;
           <selectKey keyProperty="brId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_PurchaseAccumulate
        (PAAmount,
        SettleLevel,
        CustomerGlobalID,
        CompanyGlobalID,
        ContractGlobalID,
        DealGroupID,
        DealID,
        BalanceType,
        AddDate,
        LastUpdateDate,
        DataSource,
        CalParameters,
        AddType,
        AddLoginID,
        LastUpdateLoginID
        )
        VALUES (#tgPurchaseAccumulateData.paAmount#,
        #tgPurchaseAccumulateData.settleLevel#,
        #tgPurchaseAccumulateData.customerGlobalId#,
        #tgPurchaseAccumulateData.companyGlobalId#,
        #tgPurchaseAccumulateData.contractGlobalId#,
        #tgPurchaseAccumulateData.dealGroupId#,
        #tgPurchaseAccumulateData.dealId#,
        #tgPurchaseAccumulateData.balanceType#,
        #tgPurchaseAccumulateData.addDate#,
        #tgPurchaseAccumulateData.addDate#,
        #tgPurchaseAccumulateData.dataSource#,
        #tgPurchaseAccumulateData.calParameters#,
        #tgPurchaseAccumulateData.addType#,
        #tgPurchaseAccumulateData.addLoginId#,
        #tgPurchaseAccumulateData.lastUpdateLoginId#
        )
        ON DUPLICATE KEY UPDATE
        PAID = LAST_INSERT_ID(PAID),
        PAAmount = #tgPurchaseAccumulateData.paAmount#,
        LastUpdateDate = #tgPurchaseAccumulateData.addDate#,
        AddLoginID = #tgPurchaseAccumulateData.addLoginId#,
        LastUpdateLoginID = #tgPurchaseAccumulateData.lastUpdateLoginId#;
           <selectKey keyProperty="paId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into TG_PurchaseDetail
		            (PDDate,
		             PDAmount,
		             CustomerGlobalID,
		             ContractGlobalID,
		             CompanyGlobalID,
		             DealGroupID,
		             DealID,
		             VoucherID,
		             PAID,
		             Status,
		             BalanceType,
		             AddType,
		             AddLoginID,
		             AddDate,
		             LastUpdateLoginID,
		             LastUpdateDate,
		             Memo,
		             CalParameters
		             )
			values (#tgPurchaseDetailData.pdDate#,
			        #tgPurchaseDetailData.pdAmount#,
			        #tgPurchaseDetailData.customerGlobalId#,
			        #tgPurchaseDetailData.contractGlobalId#,
			        #tgPurchaseDetailData.companyGlobalId#,
			        #tgPurchaseDetailData.dealGroupId#,
			        #tgPurchaseDetailData.dealId#,
			        #tgPurchaseDetailData.voucherId#,
			        #tgPurchaseDetailData.paId#,
			        #tgPurchaseDetailData.status#,
			        #tgPurchaseDetailData.balanceType#,
			        #tgPurchaseDetailData.addType#,
			        #tgPurchaseDetailData.addLoginId#,
			        #tgPurchaseDetailData.addDate#,
			        #tgPurchaseDetailData.lastUpdateLoginId#,
			        #tgPurchaseDetailData.addDate#,
			        #tgPurchaseDetailData.memo#,
			        #tgPurchaseDetailData.calParameters#
			        );
 		
           <selectKey keyProperty="pdId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO `TG_ReceiptAccumulate`
            (
            `DealID`,
            `ShopID`,
            `DealGroupID`,
            `UnusedCount`,
            `UsedCount`,
            `RefundingCount`,
            `RefundedCount`,
            `UnusedRevenue`,
            `UsedRevenue`,
            `RefundingRevenue`,
            `UnusedCost`,
            `UsedCost`,
            `RefundingCost`,
            `AddDate`,
            `UpdateDate`,
            `DayID`)
            VALUES
            (
            #receiptAccumulateData.dealId#,
            #receiptAccumulateData.shopId#,
            #receiptAccumulateData.dealGroupId#,
            #receiptAccumulateData.unusedCount#,
            #receiptAccumulateData.usedCount#,
            #receiptAccumulateData.refundingCount#,
            #receiptAccumulateData.refundedCount#,
            #receiptAccumulateData.unusedRevenue#,
            #receiptAccumulateData.usedRevenue#,
            #receiptAccumulateData.refundingRevenue#,
            #receiptAccumulateData.unusedCost#,
            #receiptAccumulateData.usedCost#,
            #receiptAccumulateData.refundingCost#,
            NOW(),
            NOW(),
            #receiptAccumulateData.dayId#);
 		
           <selectKey keyProperty="AccumulateID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_Notification(
        `MicroblogId`,
        `InvolvedUserType`,
        `LoginId`,
        `EventType`,
        `Status`,
        `ReadTime`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #notification.microblogId#,
        #notification.involvedUserType#,
        #notification.loginId#,
        #notification.eventType#,
        #notification.status#,
        #notification.readTime#,
        #notification.addTime#,
        #notification.updateTime#
        )

        
           <selectKey keyProperty="notificationId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_EMAIL_INFO
            ( EMAIL_ID,
            EMAIL_TITLE,
            EMAIL_CONTENT,
            EMAIL_ADDRESS,
            TYPE,
            STATUS,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID
            )
        VALUES
            (
            #entity.id#,
            #entity.emailTitle#,
            #entity.emailContent#,
            #entity.emailAddress#,
            #entity.type#,
            #entity.status#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO 
			CSC_CaseCallingPhone
			(
				AddTime,
				CaseId,
				UcId,
				CallingPhoneNo,
				UserId,
				IsDpUser,
				Dnis,
				SourceType,
				CallType
			)
			VALUES
			(
				now(),
				#cscCaseCallingPhone.caseId#,
				#cscCaseCallingPhone.ucId#,
				#cscCaseCallingPhone.callingPhoneNo#,
				#cscCaseCallingPhone.userId#,
				#cscCaseCallingPhone.isDpUser#,
				#cscCaseCallingPhone.dnis#,
				#cscCaseCallingPhone.sourceType#,
				#cscCaseCallingPhone.callType#
			)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_PARTNER_DATA
            (PARTNER_DATA_ID,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            PARTNER_DEAL_GROUP_ID,
            PARTNER_DATA,
            STATUS,
            PARTNER_ID,
            DEAL_GROUP_ID
            )
        VALUES
            (#entity.id#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.partnerDealGroupId#,
            #entity.partnerData#,
            #entity.status#,
            #entity.partnerId#,
            #entity.dealGroupId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO RBAC_Limitation(
        `BusinessId`,
        `BusinessType`,
        `RolePermissionId`,
        `AddTime`,
        `UpdateTime`,
        `Status`
        ) VALUES (
        #limitation.businessId#,
        #limitation.businessType#,
        #limitation.rolePermissionId#,
        #limitation.addTime#,
        #limitation.updateTime#,
        #limitation.status#
        )

        
           <selectKey keyProperty="limitationId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO RBAC_Role(
        `Name`,
        `Description`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #role.name#,
        #role.description#,
        #role.addTime#,
        #role.updateTime#
        )

        
           <selectKey keyProperty="roleId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO ECP_DEAL_GROUP
            (PARTNER_DEAL_GROUP_ID,
            PUBLISH_DEAL_GROUP_ID,
            PUBLISH_CITY_IDS,
            BEGIN_DATE,
            END_DATE,
            EXCEPT_DATE,
            MAX_PER_USER,
            MIN_PER_USER,
            HOTEL_TYPE,
            IS_REFUNDABLE,
            TITLE,
            DESCRIPTION,
            SUMMARY,
            DEAL_GROUP_INFO,
            MOBILE_DEAL_GROUP_INFO,
            SERVICE_REGULATION,
            PRODUCT_INFO,
            CREATE_TIME,
            LAST_UPDATE_TIME,
            PARTNER_ID,
            PICTURES,
            STATUS,
            SHOP_DESCRIPTION,
            PARTNER_DATA_ID
            )
            VALUES
            (#partnerDealGroupId#,
            #publishDealGroupId#,
            #publishCityIds#,
            #beginDate#,
            #endDate#,
            #exceptDate#,
            #maxPerUser#,
            #minPerUser#,
            #hotelType#,
            #refundable#,
            #title#,
            #description#,
            #summary#,
            #dealGroupInfo#,
            #mobileDealGroupInfo#,
            #serviceRegulation#,
            #productInfo#,
            #createTime#,
            #lastUpdateTime#,
            #partnerId#,
            #pictures#,
            #status#,
            #shopDescription#,
            #partnerDataId#
            );
        
           <selectKey keyProperty="dealGroupId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO ECP_DEAL_GROUP_PROMOTION
            (DEAL_GROUP_ID,
             TYPE,
             PARAMETER,
             BEGIN_DATE,
             END_DATE,
             CREATE_TIME,
             LAST_UPDATE_TIME,
             STATUS)
        VALUES (#dealGroupId#,
                #type#,
                #parameter#,
                #beginDate#,
                #endDate#,
                #createTime#,
                #lastUpdateTime#,
                #status#);
		
           <selectKey keyProperty="dealGroupId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TG_MobileVerifyRecord (MobileNO,VerifyNum,ShopAccountID,LoginAccountID,DealID,DealGroupID,ConsumeDate,VerifyWay)
        VALUES (#mobileNo#, #verifyNum#,#shopAccountId#,#loginAccountId#,#dealId#,#dealGroupId#,#consumeDate#,#verifyWay#)
        
           <selectKey keyProperty="RecordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptMobileVerifyCode (VerifyCode,MobileNO,ShopAccountID,AddTime)
		VALUES(#verifyCode#,#mobileNO#,#shopAccountID#,Now())
		   <selectKey keyProperty="verifyCodeID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_BusinessCenterValidateCode (ValidateCode,MobileNO,ShopAccountID,AddTime,ValidateType)
		VALUES(#validateCode#,#mobileNO#,#shopAccountID#,Now(),#validateType#)
		   <selectKey keyProperty="validateCode" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealReceiptOriginalEndDate (DealGroupID, DealID, EndDate, AddTime)
		VALUES(#dealGroupId#, #dealId#, #endDate#, NOW())
		   <selectKey keyProperty="DealReceiptOriginalEndDateID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupDelayApply (DealGroupID, Memo, ShopAccountID, Status, AddTime, UpdateTime)
		VALUES(#dealGroupId#, "", #shopAccountId#, #status#, NOW(), NOW())
		   <selectKey keyProperty="DealGroupDelayApplyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealDelayApply (DealGroupDelayApplyID, DealGroupID, DealID, EndDate, OldEndDate, Memo, AddTime)
		VALUES(#dealGroupDelayApplyId#, #dealGroupId#, #dealId#, #endDate#, #oldEndDate#, "", NOW())
		   <selectKey keyProperty="DealDelayApplyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT_ATTRIBUTE_OPTION 
			(STATEMENT_ATTRIBUTE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VALUE)
		VALUES
			(#entity.statementAttribute.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.value#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT_ATTRIBUTE_VALUE 
			(STATEMENT_ID, 
			STATEMENT_ATTRIBUTE_ID,
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VALUE)
		VALUES
			(#entity.statement.id#,
			#entity.statementAttributeId#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.value#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_DEAL_GROUP_MAINTAINER
		(DEAL_GROUP_ID,MAINTAINER_ID,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,
		MAINTAINER_NAME,TRAIN_STATUS,VERSION_ID,CONTACT_MP,CONTACT_EMAIL,IS_SENDED)
		VALUES
		(#entity.dealGroupId#,#entity.maintainerId#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
		#entity.maintainerName#,#entity.trainStatus#,#entity.versionId#
		,#entity.contactMP#,#entity.contactEmail#,#entity.isSended#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_DEAL_GROUP_WORKFLOW
            (DEAL_GROUP_ID,
            PROCESS_INSTANCE_ID,
            PROCESS_CODE,
            WORKFLOW_STATUS,
            CREATOR_ID,
            LAST_UPDATOR_ID,
            CREATE_TIME,
            LAST_UPDATE_TIME
            )
        VALUES
            (#entity.dealGroupId#,
            #entity.processInstanceId#,
            #entity.processCode#,
            #entity.workflowStatus,handler=workflowStatusEnumTypeHandler#,
            #entity.creatorId#,
            #entity.lastUpdatorId#,
            #entity.createTime#,
            #entity.lastUpdateTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_EDITOR
            (EDITOR_ID,
             EDITOR_NAME,
             EDITOR_TEAM_ID,
             WORKLOAD,
             WORKLOAD_TODAY,
             WORKLOAD_ASSIGNED,
             IS_ACTIVE,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             CREATE_TIME,
             LAST_UPDATE_TIME)
        VALUES (#entity.editorId#,
            #entity.editorName#,
            #entity.editorTeamId#,
            #entity.workload#,
            #entity.workloadToday#,
            #entity.workloadAssigned#,
            #entity.isActive#,
            #entity.creatorId#,
            #entity.lastUpdatorId#,
            #entity.createTime#,
            #entity.lastUpdateTime#);
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_FILE_ATTACHMENT 
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		FILE_NAME,
		RELATIVE_PATH,
		DEAL_GROUP_ID,
		DTYPE)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.fileName#,
		#entity.relativePath#,
		#entity.dealGroup.id#,
		'FileAttachment'
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_PRODUCT
         (
         CREATOR_ID,
         LAST_UPDATOR_ID,
         CREATE_TIME,
         LAST_UPDATE_TIME,
         VERSION_ID)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.versionId#)
		
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_RECEIPT_VERIFY_HISTORY
            (DEAL_GROUP_ID,
            POI_SHOP_ID,
            SUCCESS_SERIAL_NUMBER,
            FAILURE_SERIAL_NUMBER,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME,
            MEMO,
            RECALL_STATUS
            )
        VALUES
            (#entity.dealGroupId#,
            #entity.poiShopId#,
            #entity.successSerialNumber#,
            #entity.failureSerialNumber#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#,
            #entity.memo#,
            #entity.recallStatus#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,RICH_TEXT_CONTENT,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.content#,#entity.templateId#,'RichTextComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_SALES
            (SALES_ID,
            SALES_TEAM_ID,
            SALES_NAME,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME,
            VERSION_ID
            )
            VALUES
            (#entity.salesId#,
            #entity.salesTeamId#,
            #entity.salesName#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#,
            #entity.versionId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_SHOP_CITY_GROUP
         (
		CREATOR_ID,
		LAST_UPDATOR_ID,
		CREATE_TIME,
		LAST_UPDATE_TIME,
		CITY_ID,
		SEQUENCE,
		VISUAL_VIEW_ID)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
         #entity.cityId#,#entity.sequence#,#entity.visualView.id#)
		
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.templateId#,'TextListComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	INSERT INTO CSC_Case
	            (AddTime,           
	             FirstTypeCode,
	             SecondTypeCode,
	             ThirdTypeCode,
	             CaseSource,
	             IsNeedFollow,
	             IsComplain,
	             Status,
	             CaseContent,
	             CustomerId,
	             CustomerName,
	             CustomerRequire,
	             AcceptId,
	             AcceptName,
	             ClosedId,
	             ClosedName,
	             ClosedTime,
	             FollowTime,
	             SFCaseId,
	             SFCaseNumber,
	             ReferUrl,
	             OwnerId,
	             OwnerName,
	             Priority,
	             FeedBackUserName,
	             FeedBackWay,
	             CustomerType,
	             AdditionTypeCode,
	             FourthTypeCode)
	VALUES      ( now(),
	              #cscCase.firstTypeCode#,
	              #cscCase.secondTypeCode#,
	              #cscCase.thirdTypeCode#,
	              #cscCase.caseSource#,
	              #cscCase.isNeedFollow#,
	              #cscCase.isComplain#,
	              #cscCase.status#,
	              #cscCase.caseContent#,
	              #cscCase.customerId#,
	              #cscCase.customerName#,
	              #cscCase.customerRequire#,
	              #cscCase.acceptId#,
	              #cscCase.acceptName#,
	              #cscCase.closedId#,
	              #cscCase.closedName#,
	              #cscCase.closedTime#,
	              #cscCase.followTime#,
	              #cscCase.sfCaseId#,
	              #cscCase.sfCaseNumber#,
	              #cscCase.referUrl#,
	              #cscCase.ownerId#,
	              #cscCase.ownerName#,
	              #cscCase.priority#,
	              #cscCase.feedBackUserName#,
	              #cscCase.feedBackWay#,
	              #cscCase.customerType#,
	              #cscCase.additionTypeCode#,
	              #cscCase.fourthTypeCode#)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_DealGroupAnnounce
		(
			AddTime,
			DealGroupId,
			Content,
			AddLoginId
		)
		VALUES
		(
			now(),
			#cscDealGroupAnnounce.dealGroupId#,
			#cscDealGroupAnnounce.content#,
			#cscDealGroupAnnounce.addLoginId#
		)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_OnlineCaseCount(
			AddTime,
			OnlineDate,
			OnlineCaseCount)
		VALUES (
			now(),
			#onlineDate#,
			#onlineCaseCount#)
		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_TGOrder (
			AddTime,
			OrderId,
			UserId,
			DealGroupId,
			DealGroupName,
			DealId,
			DealName,
			MobileNo,
			Status,
			OrderAddDate,
			OrderSuccessDate,
			AutoRefundAble,
			NeedDeliver,
			ThirdProviderId,
			CaseId
		)
		VALUES (
			now(),
			#cscTGOrder.orderID#,
			#cscTGOrder.userID#,
			#cscTGOrder.dealGroupID#,
			#cscTGOrder.dealGroupName#,
			#cscTGOrder.dealID#,
			#cscTGOrder.dealName#,
			#cscTGOrder.mobileNo#,
			#cscTGOrder.status#,
			#cscTGOrder.addDate#,
			#cscTGOrder.successDate#,
			#cscTGOrder.autoRefundAble#,
			#cscTGOrder.needDeliver#,
			#cscTGOrder.thirdProviderID#,
			#cscTGOrder.caseId#
		)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO CSC_BatchRefundTimer
	            (AddTime,
				 LoginId,
				 DealGroupId,
				 DealId,
				 CityIds,
				 ExecuteTime)
			VALUES
				(now(),
	             #cscBatchRefundTimer.loginId#,
	             #cscBatchRefundTimer.dealGroupId#,
	             #cscBatchRefundTimer.dealId#,
	             #cscBatchRefundTimer.cityIds#,
	             #cscBatchRefundTimer.executeTime#
	            )
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardApply 
            (ShopID,
             ShopUrl,
             Email,
             AccountID,
             Contactor,
             Tel,
             QQ,
             Status,
             AddTime) 
        VALUES 
            (#shopID#,
             #shopUrl#,
             #email#, 
             #accountID#, 
             #contactor#, 
             #tel#, 
             #qq#,
             #status.status#,
             #addTime#)
           <selectKey keyProperty="ApplyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProduct 
            (MemberCardID,
             AddAccountID,
             ProductName,
             ProductDesc,
             ProductType,
             BeginDate,
             EndDate,
             Status,
             AddTime) 
        VALUES 
            (#memberCardID#, 
             #addAccountID#,
             #productName#, 
             #productDesc#, 
             #productType.type#, 
             #beginDate#,
             #endDate#,
             #status.status#,
             #addTime#)
           <selectKey keyProperty="ProductID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO MC_MemberCard(MemberCardType, Source, Status, BgImageID, LogoID ,AuthAdminID,AuthAdminName, AddTime)
            VALUES(#cardType#, #source#, 1,0,0,#authAdminId#, #authAdminName#, #addTime#)
         

           <selectKey keyProperty="MemberCardID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProduct_Draft
            (ProductID,
             MemberCardID,
             AddAccountID,
             ProductName,
             ProductDesc,
             ProductType,
             BeginDate,
             EndDate,
             ActionType,
             Reason,
             Status,
             AddTime) 
        VALUES 
            (#productID#,
             #memberCardID#, 
             #addAccountID#,
             #productName#, 
             #productDesc#, 
             #productType.type#, 
             #beginDate#,
             #endDate#,
             #actionType.type#,
             #reason#,
             #status.status#,
             #addTime#)
           <selectKey keyProperty="ProductDraftID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductShop 
            (ProductID,
             ShopID,
             MemberCardID,
             Status,
             AddTime) 
        VALUES 
            (#productID#, 
             #shopID#, 
             #memberCardID#, 
             #status.status#, 
             #addTime#)
           <selectKey keyProperty="ProductShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductShop_Draft 
            (ProductDraftID,
             ShopID,
             MemberCardID,
             Status,
             AddTime) 
        VALUES 
            (#productDraftId#, 
             #shopID#, 
             #memberCardID#, 
             #status.status#, 
             #addTime#)
           <selectKey keyProperty="ProductShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductDiscount 
            (ProductID,
             DiscountRate) 
        VALUES 
            (#productID#, 
             #discountRate#)
           <selectKey keyProperty="ProductDiscountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductDiscount_Draft
            (ProductDraftID,
             DiscountRate) 
        VALUES 
            (#productDraftId#, 
             #discountRate#)
           <selectKey keyProperty="ProductDiscountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardConsume
            (MemberCardID,
             UserID,
             CardNO,
             ShopID,
             ProductID,
             ConsumePrice,
             ConsumeDate,
             AddTime)
        VALUES
            (#memberCardId#,
             #userId#,
             #cardNO#,
             #shopId#,
             #productId#,
             #consumePrice#,
             #consumeDate#,
             #addTime#)
           <selectKey keyProperty="MemberCardConsumeID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductMallPromo
            (ProductID,
             PromoTitle,
             PromoDesc,
             Tel)
        VALUES 
            (#productId#,
             #promoTitle#,
             #promoDesc#,
             #tel#)
           <selectKey keyProperty="promoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductMallPromo_Draft
            (ProductDraftID,
             PromoTitle,
             PromoDesc,
             Tel)
        VALUES 
            (#productDraftId#,
             #promoTitle#,
             #promoDesc#,
             #tel#)
           <selectKey keyProperty="promoDraftId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductMallShopPromo
            (ProductID,
             ShopType,
             Floor,
             Building,
             PromoTitle,
             PromoDesc,
             Tel)
        VALUES 
            (#productId#,
             #shopType#,
             #floor#,
             #building#,
             #promoTitle#,
             #promoDesc#,
             #tel#)
           <selectKey keyProperty="promoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardProductMallShopPromo_Draft
            (ProductDraftID,
             ShopType,
             Floor,
             Building,
             PromoTitle,
             PromoDesc,
             Tel)
        VALUES 
            (#productDraftId#,
             #shopType#,
             #floor#,
             #building#,
             #promoTitle#,
             #promoDesc#,
             #tel#)
           <selectKey keyProperty="promoDraftId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MC_MemberCardFeed
            (FeedType,
             Category,
             ShopAccountID,
             MemberCardID,
             Title,
             PicPath,
             PicType,
             Content,
             BeginDate,
             EndDate,
             Status,
             TotalLikeNum,
             AddTime,
             UpdateTime)
        VALUES 
            (#feedType.feedType#,
             #feedCategory.feedCategory#,
             #shopAccountID#,
             #memberCardID#,
             #title#,
             #picPath#,
             #picType#,
             #content#,
             #beginDate#,
             #endDate#,
             #feedStatus.feedStatus#,
             #totalLikeNum#,
             #addTime#,
             #updateTime#)
           <selectKey keyProperty="feedID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

            INSERT INTO DianPingMC.MC_MemberCardScoreDeductCancelApply 
                (LogID, 
                ApplyContent, 
                AuditAdminID, 
                AuditContent, 
                STATUS, 
                ADDTIME, 
                UpdateTime
                )
                VALUES
                (#logID#, 
                #applyContent#, 
                #auditAdminID#, 
                #auditContent#, 
                #status#, 
                Now(), 
                Now());
           <selectKey keyProperty="applyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

            INSERT INTO DianPingMC.MC_MemberCardMaterialApply 
                (MemberCardID, 
                TableNum, 
                TipNum, 
                ContractPerson, 
                ContractMobile, 
                STATUS, 
                ADDTIME, 
                UpdateTime
                )
                VALUES
                (#memberCardID#, 
                #tableNum#, 
                #tipNum#, 
                #contractPerson#, 
                #contractMobile#, 
                #status#, 
                Now(), 
                Now()
                );
           <selectKey keyProperty="ApplyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
  
         
            INSERT INTO MC_MemberCardFeedApplyShop(FeedID, ShopID, AddTime) VALUES 
          

           <iterate conjunction="," property="feedShopList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into FS_ShopFundAccount
		            (CustomerGlobalID,
		             CompanyGlobalID,
		             ShopID,
		             BusinessType,
		             Credit,
		             Debit,
		             BalanceTotal,
		             BalanceFrozen,
		             AddDate,
		             LastUpdateDate,
		             AddLoginID
		             )
			values (#shopFundAccountData.customerGlobalId#,
			         #shopFundAccountData.companyGlobalId#,
			         #shopFundAccountData.shopId#,
			         #shopFundAccountData.businessType#,
			         #shopFundAccountData.credit#,
			         #shopFundAccountData.debit#,
			         #shopFundAccountData.balanceTotal#,
			         #shopFundAccountData.balanceFrozen#,
			         NOW(),
			         NOW(),
			         #shopFundAccountData.addLoginId#
		            );
 		
           <selectKey keyProperty="fundAccountId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	 
        	 INSERT INTO YS_AccountReceivable
			(PlanReceiveDate,
			PlanReceiveAmount,
			ActualReceivedAmount,
			ContractGlobalID, 
			BusinessType, 
			AddType, 
			Status,
			ProcessStatus,
			AddDate,
			AddLoginID, 
			UpdateDate, 
			UpdateLoginID, 
			Memo
			)
			VALUES
			(#accountReceivableBean.planReceiveDate#,
			#accountReceivableBean.planReceiveAmount#,
			#accountReceivableBean.actualReceiveAmount#,
			#accountReceivableBean.contractGlobalID#,
			#accountReceivableBean.businessType#,
			#accountReceivableBean.addType#,
			#accountReceivableBean.status#,
			#accountReceivableBean.processStatus#,
			NOW(), 
			#accountReceivableBean.addLoginId#,
			NOW(), 
			#accountReceivableBean.updateLoginId#,
			#accountReceivableBean.memo#
			);
 		
		   <selectKey keyProperty="ARID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	 
        	INSERT INTO YS_ARForWedding
			(ARID,
			ShopID,
			ContractNo,
			ARType,
			BizId,
			AddDate,
			AddLoginID, 
			UpdateDate,
			UpdateLoginID
			)
			VALUES
			(#arForWeddingBean.aRID#,
			#arForWeddingBean.shopID#,
			#arForWeddingBean.contractNo#,
			#arForWeddingBean.aRType#,
			#arForWeddingBean.bizId#,
			NOW(), 
			#arForWeddingBean.addLoginId#,
		    NOW(),
			#arForWeddingBean.updateLoginId#
			);
 		
		   <selectKey keyProperty="ARWeddingID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO FS_MonitorTime
        (MonitorTime)
        VALUES (#monitorTimeData.monitorTime#)
           <selectKey keyProperty="MonitorID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO PC_MonitorTodo
        (VoucherID,
        APID,
        BPID,
        MonitorID,
        AddDate,
        Status)
        VALUES (#monitorTodoData.voucherId#,
        #monitorTodoData.apId#,
        #monitorTodoData.bpId#,
        #monitorTodoData.monitorId#,
        #monitorTodoData.addDate#,
        #monitorTodoData.status#)
           <selectKey keyProperty="TodoID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into TG_RefundDetail
		            (RDDate,
		             RDAmount,
		             CustomerGlobalID,
		             ContractGlobalID,
		             CompanyGlobalID,
		             DealGroupID,
		             DealID,
		             VoucherID,
		             RAID,
		             Status,
		             BalanceType,
		             AddType,
		             AddLoginID,
		             AddDate,
		             LastUpdateLoginID,
		             LastUpdateDate,
		             Memo,
		             CalParameters
		             )
			values (#tgRefundDetailData.rdDate#,
			        #tgRefundDetailData.rdAmount#,
			        #tgRefundDetailData.customerGlobalId#,
			        #tgRefundDetailData.contractGlobalId#,
			        #tgRefundDetailData.companyGlobalId#,
			        #tgRefundDetailData.dealGroupId#,
			        #tgRefundDetailData.dealId#,
			        #tgRefundDetailData.voucherId#,
			        #tgRefundDetailData.raId#,
			        #tgRefundDetailData.status#,
			        #tgRefundDetailData.balanceType#,
			        #tgRefundDetailData.addType#,
			        #tgRefundDetailData.addLoginId#,
			        #tgRefundDetailData.addDate#,
			        #tgRefundDetailData.lastUpdateLoginId#,
			        #tgRefundDetailData.addDate#,
			        #tgRefundDetailData.memo#,
			        #tgRefundDetailData.calParameters#
			        );
 		
           <selectKey keyProperty="rdId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	 
            INSERT INTO `TG_DealDetail`
            (`DealID`,
                `DealGroupID`,
                `ContractGlobalID`,
                `CurrentJoin`,
                `Price`,
                `Cost`,
                `ReceiptType`,
                `DeliverType`,
                `DealBeginDate`,
                `DealEndDate`,
                `ReceiptBeginDate`,
                `ReceiptEndDate`,
                `DealShortTitle`,
                `ContractID`)
            VALUES
            (
            #dealData.dealId#,
            #dealData.dealGroupId#,
            #dealData.contractGlobalId#,
            #dealData.currentJoin#,
            #dealData.price#,
            #dealData.cost#,
            #dealData.receiptType#,
            #dealData.deliverType#,
            #dealData.dealBeginDate#,
            #dealData.dealEndDate#,
            #dealData.receiptBeginDate#,
            #dealData.receiptEndDate#,
            #dealData.dealShortTitle#,
            #dealData.contractId#
            );
 		
		   <selectKey keyProperty="DealID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_MicroblogAndTopicRelation(
        `MicroblogId`,
        `TopicId`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #microblogAndTopicRelation.microblogId#,
        #microblogAndTopicRelation.topicId#,
        #microblogAndTopicRelation.addTime#,
        #microblogAndTopicRelation.updateTime#

        
           <selectKey keyProperty="relationId" resultClass="int"/>

        )
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO test
          (id, test)
          VALUES
          (#test.id#,#test.name#);
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO test
          (id, test)
          VALUES
          (#test.id#,#test.name#);
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FC_PayPlan
			(PlanDate,
			PlanAmount,
			PaidAmount,
			BusinessType,
			BizID,
			CustomerID,
			ShopID,
			CustomerBankID,
			AddTime,
			AddType,
			UpdateTime,
			Memo,
			AddLoginID
			)
			VALUES
			(#payPlanData.planDate#,
			#payPlanData.planAmount#,
			#payPlanData.paidAmount#,
			#payPlanData.businessType#,
			#payPlanData.bizID#,
			#payPlanData.customerID#,
			#payPlanData.shopID#,
			#payPlanData.customerBankID#,
			NOW(),
			#payPlanData.addType#,
			NOW(),
			#payPlanData.memo#,
			#payPlanData.addLoginID#
			);
 		
           <selectKey keyProperty="ppId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FC_PayPlanLog
			(LoginID,
			PPID,
			OperateTime,
			Status,
			Memo
			)
			VALUES
			(#payPlanLogData.loginID#,
			#payPlanLogData.ppID#,
			NOW(),
			#payPlanLogData.status#,
			#payPlanLogData.memo#
			);
 		
           <selectKey keyProperty="ppId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO CSC_RefundRecord (
				AddTime,
				OrderId,
				PctOrderRefundId,
				CaseId,
				LoginId,
				ReceiptIds,
				OrderString,
				RefundType,
				BizType,
				Status,
				resultMessage,
				RefundTo,
				Amount,
				IsShopResponsibility,
				OperationSource,
				refundChannel,
				AliAccount,
				AliAccountName,
				BankAccount,
				BankAccountName,
				BankCode,
				BankName,
				BankProvince,
				BankCity,
				BankBranchName,
				Memo,
				DealGroupId,
				UserId,
				RefundReason,
				ProcInstNO,
				ProcInstID,
				ApproveStatus,
				SfCaseId,
				HasDeducted,
				DeductionReason,
				CardProductId,
				CardSerialNumber,
				CardRevokingAmount,
				DeductionAmount,
				DealID
			)
			VALUES
				(
					now(),
					#cscRefundRecord.orderId#,
					#cscRefundRecord.pctOrderRefundId#,
					#cscRefundRecord.caseId#,
					#cscRefundRecord.loginId#,
					#cscRefundRecord.receiptIds#,
					#cscRefundRecord.orderString#,
					#cscRefundRecord.refundType#,
					#cscRefundRecord.bizType#,
					#cscRefundRecord.status#,
					#cscRefundRecord.resultMessage#,
					#cscRefundRecord.refundTo#,
					#cscRefundRecord.amount#,
					#cscRefundRecord.isShopResponsibility#,
					#cscRefundRecord.operationSource#,
					#cscRefundRecord.refundChannel#,
					#cscRefundRecord.aliAccount#,
					#cscRefundRecord.aliAccountName#,
					#cscRefundRecord.bankAccount#,
					#cscRefundRecord.bankAccountName#,
					#cscRefundRecord.bankCode#,
					#cscRefundRecord.bankName#,
					#cscRefundRecord.bankProvince#,
					#cscRefundRecord.bankCity#,
					#cscRefundRecord.bankBranchName#,
					#cscRefundRecord.memo#,					
					#cscRefundRecord.dealGroupId#,
					#cscRefundRecord.userId#,
					#cscRefundRecord.refundReason#,
					#cscRefundRecord.procInstNO#,
					#cscRefundRecord.procInstID#,
					#cscRefundRecord.approveStatus#,
					#cscRefundRecord.sfCaseId#,
					#cscRefundRecord.hasDeducted#,
					#cscRefundRecord.deductionReason#,
					#cscRefundRecord.cardProductId#,
					#cscRefundRecord.cardSerialNumber#,
					#cscRefundRecord.cardRevokingAmount#,
					#cscRefundRecord.deductionAmount#,
					#cscRefundRecord.dealId#
				)
		

           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO RBAC_Permission(
        `Name`,
        `Description`,
        `SystemName`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #permission.name#,
        #permission.description#,
        #permission.systemName#,
        #permission.addTime#,
        #permission.updateTime#
        )

        
           <selectKey keyProperty="permissionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO ECP_PARTNER 
			(PARTNER_ID,
			CONTRACT_GLOBAL_ID, 
			BANK_ACCOUNT_GLOBAL_ID,
			PARTNER_KEY,
			PARTNER_NAME, 
			CREATE_TIME, 
			LAST_UPDATE_TIME,
			MAX_PER_DAY
			)
			VALUES
			(#partnerId#,
			#contractGlobalId#, 
			#bankAccountGlobalId#,
			#partnerKey#,
			#partnerName#, 
			#createTime#, 
			#lastUpdateTime#,
			#maxPerDay#
			);
		
	       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGHT_DeliverExpressBatchDetail (BatchID,OrderID,ExpressNO,Status,AddDate,UpdateDate) VALUES
		   <iterate open="" conjunction="," property="delivers" close=""/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGHT_DeliverExpressBatch 
		(FileName,OperatorID,CompanyID,STATUS,OrderNum,SuccessNum,TryTimes,AddDate,UpdateDate)
		values
		(#fileName#,#operatorID#,#companyID#,0,#orderNum#,0,0,Now(),Now());
		   <selectKey keyProperty="batchId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ValidPhoneList 
		(PhoneNumber,DealId,ShopAccountID,NumberType)
		VALUES
		(#PhoneNumber#,#DealId#,#ShopAccountID#,#NumberType#)
		   <selectKey keyProperty="PhoneId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT_ATTRIBUTE 
			(STATEMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			SEQUENCE,
			FRONT_END_INPUT_TYPE,
			LABEL_CONTENT,
			LENGTH)
		VALUES
			(#entity.statementTemplate.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.sequence#,
			#entity.frontEndInputType#,
			#entity.labelContent#,
			#entity.length#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_CORP_INFO
         (
		CREATOR_ID,
		LAST_UPDATOR_ID,
		CREATE_TIME,
		LAST_UPDATE_TIME,
		NAME,
		DESCRIPTION,
		IS_EXPANDED,
		VISUAL_VIEW_ID)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
         #entity.name#,#entity.description#,#entity.isExpanded#,#entity.visualView.id#)
		
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_DEAL_GROUP_CITY_ASSN 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,CITY_ID,DEAL_GROUP_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.cityId#,#entity.dealGroup.id#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_PRODUCE_VERSION
		(DEAL_GROUP_ID,DESC_INFO,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,IP_ADDRESS,SERVER_IP,DATA)
		VALUES
		(#entity.dealGroupId#,#entity.descInfo#,#entity.creatorId#,#entity.lastUpdatorId#,
		#entity.createTime#,#entity.lastUpdateTime#,#entity.ipAddress#,#entity.serverIp#,#entity.data#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_SALES_TEAM_AE_ASSN
            (SALES_TEAM_ID,
            AE_ID,
            AE_NAME,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME
            )
            VALUES
            (#entity.salesTeam.id#,
            #entity.aeId#,
            #entity.aeName#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_SERIAL_NUM_OPERATION_LOG
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VERSION_ID,
		DEAL_ID,TOTAL_COUNT,DUPLICATED_COUNT,IMPORTED_COUNT,
        BATCH_NAME,
        IS_REMOVED,
        DUPLICATED_SERIAL_NUMBERS,
		DTYPE)
		VALUES
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.versionId#,
		#entity.dealId#,#entity.totalCount#,#entity.duplicatedCount#,#entity.importedCount#,
		#entity.batchName#,
		#entity.isRemoved#,
		#entity.duplicatedSerialNumbers#,
		'SerialNumberImportLog')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_SHOP_INFO
         (
		CREATOR_ID,
		LAST_UPDATOR_ID,
		CREATE_TIME,
		LAST_UPDATE_TIME,
		ADDRESS,
		BUSINESS_HOURS,
		CONTACT_PHONE,
		POI_SHOP_ID,
		SEQUENCE,
		IS_AVG_PRICE_DISPLAYED,
		IS_BUSINESS_HOURS_DISPLAYED,
		IS_CONTACT_PHONE_DISPLAYED,
		IS_MAP_LINK_DISPLAYED,
		IS_STAR_RATE_DISPLAYED,
		SHOP_CITY_GROUP_ID,
		IS_VOTE_QUANTITY_DISPLAYED)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
         #entity.address#,#entity.businessHours#,#entity.contactPhone#,#entity.poiShopId#,#entity.sequence#,
         #entity.isAvgPriceDisplayed#,#entity.isBusinessHoursDisplayed#,#entity.contactPhoneDisplayed#,
         #entity.isMapLinkDisplayed#,#entity.isStarRateDisplayed#,#entity.shopCityGroup.id#,#entity.isVoteQuantityDisplayed#
         )
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_SLIDE_PICTURE 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,URL,VISUAL_VIEW_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.url#,#entity.visualView.id#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_SPECIAL_REMINDER_TEMPLATE_MAP
            (
             NAV_CATEGORY_ID,
             TEMPLATE_URL,
             CREATE_TIME,
             LAST_UPDATE_TIME,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             TEMPLATE_NAME
             )
        VALUES (#entity.categoryId#,
                #entity.templateUrl#,
                #entity.createTime#,
                #entity.lastUpdateTime#,
                #entity.creatorId#,
                #entity.lastUpdatorId#,
                #entity.templateName#
            );
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO 
			CSC_Log
			(
				AddTime,
				CategoryID,
				SubCategory,
				LogType,
				ReferID,
				LogInfo,
				ParamInfo,
				LoginID
			)
			VALUES
			(
				now(),
				#cscLog.categoryID#,
				#cscLog.subCategory#,
				#cscLog.logType#,
				#cscLog.referID#,
				#cscLog.logInfo#,
				#cscLog.paramInfo#,
				#cscLog.loginID#
			)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
			INSERT INTO MC_MemberCardShop(MemberCardID,ShopID,CityID,ShopName,BranchName,Status, AddTime, ShopGroupId, CardGroupID, SecondCatgory, Region, Lat, Lng, POWER)
			VALUES(#memberCardId#, #shopId#, #cityId#, #shopName#, #branchName#, #status#, #addTime#, #shopGroupId#, #cardGroupId#,#category#,#region#,#lat#,#lng#,#power#)
			
		   <selectKey keyProperty="MemberCardShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO FS_BalanceRelation
			(PPID,
			RPID,
			FAFlowID,
			RelationType,
			BalanceAmount,
			AddDate
			)
			VALUES
			(#balanceRelationData.paymentPlanId#,
			#balanceRelationData.receivablePlanId#,
			#balanceRelationData.fundAccountFlowId#,
			#balanceRelationData.relationType#,
			#balanceRelationData.balanceAmount#,
			NOW()
			);
 		
           <selectKey keyProperty="balanceId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO PC_MonitorException
        (VoucherID,
        APID,
        BPID,
        ExceptionType,
        AddDate,
        MonitorID,
        Status)
        VALUES (#monitorExceptionData.voucherId#,
        #monitorExceptionData.apId#,
        #monitorExceptionData.bpId#,
        #monitorExceptionData.exceptionType#,
        #monitorExceptionData.addDate#,
        #monitorExceptionData.monitorId#,
        #monitorExceptionData.status#)
           <selectKey keyProperty="ExceptionID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO PC_MonitorTime
        (LastMonitorTime)
        VALUES (#lastMonitorTime#)
           <selectKey keyProperty="MonitorID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into TG_AccountPayable
		            (APPlanDate,
		             APAmount,
		             CustomerGlobalID,
		             ContractGlobalID,
		             CompanyGlobalID,
		             DealGroupID,
		             DealID,
		             ShopID,
		             VoucherID,
		             Status,
		             AddType,
		             AddLoginID,
		             AddDate,
		             LastUpdateLoginID,
		             LastUpdateDate,
		             Memo,
		             CalParameters
		             )
			values (#tgAccountPayableData.apPlanDate#,
			        #tgAccountPayableData.apAmount#,
			        #tgAccountPayableData.customerGlobalId#,
			        #tgAccountPayableData.contractGlobalId#,
			        #tgAccountPayableData.companyGlobalId#,
			        #tgAccountPayableData.dealGroupId#,
			        #tgAccountPayableData.dealId#,
			        #tgAccountPayableData.shopId#,
			        #tgAccountPayableData.voucherId#,
			        #tgAccountPayableData.status#,
			        #tgAccountPayableData.addType#,
			        #tgAccountPayableData.addLoginId#,
			        now(),
			        #tgAccountPayableData.lastUpdateLoginId#,
			        now(),
			        #tgAccountPayableData.memo#,
			        #tgAccountPayableData.calParameters#
			        );
 		
           <selectKey keyProperty="apId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_BillingPayable
        (PlanDate,
        PlanAmount,
        CustomerGlobalID,
        CompanyGlobalID,
        ContractGlobalID,
        DealGroupID,
        DealID,
        ShopID,
        Status,
        AddDate,
        LastUpdateDate,
        SettleLevel,
        BalanceType,
        PayType,
        DataSource,
        CalParameters,
        AddType,
        AddLoginId,
        LastUpdateLoginId
        )
        VALUES (#tgBillingPayableData.planDate#,
        #tgBillingPayableData.planAmount#,
        #tgBillingPayableData.customerGlobalId#,
        #tgBillingPayableData.companyGlobalId#,
        #tgBillingPayableData.contractGlobalId#,
        #tgBillingPayableData.dealGroupId#,
        #tgBillingPayableData.dealId#,
        #tgBillingPayableData.shopId#,
        #tgBillingPayableData.status#,
        #tgBillingPayableData.addDate#,
        #tgBillingPayableData.addDate#,
        #tgBillingPayableData.settleLevel#,
        #tgBillingPayableData.balanceType#,
        #tgBillingPayableData.payType#,
        #tgBillingPayableData.dataSource#,
        #tgBillingPayableData.calParameters#,
        #tgBillingPayableData.addType#,
        #tgBillingPayableData.addLoginId#,
        #tgBillingPayableData.lastUpdateLoginId#
        );
           <selectKey keyProperty="bpId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_BillingPayable
        (PlanDate,
        PlanAmount,
        CustomerGlobalID,
        CompanyGlobalID,
        ContractGlobalID,
        DealGroupID,
        DealID,
        ShopID,
        Status,
        AddDate,
        LastUpdateDate,
        SettleLevel,
        BalanceType,
        PayType,
        DataSource,
        CalParameters,
        AddType,
        AddLoginId,
        LastUpdateLoginId
        )
        VALUES (#tgBillingPayableData.planDate#,
        #tgBillingPayableData.planAmount#,
        #tgBillingPayableData.customerGlobalId#,
        #tgBillingPayableData.companyGlobalId#,
        #tgBillingPayableData.contractGlobalId#,
        #tgBillingPayableData.dealGroupId#,
        #tgBillingPayableData.dealId#,
        #tgBillingPayableData.shopId#,
        #tgBillingPayableData.status#,
        #tgBillingPayableData.addDate#,
        #tgBillingPayableData.addDate#,
        #tgBillingPayableData.settleLevel#,
        #tgBillingPayableData.balanceType#,
        #tgBillingPayableData.payType#,
        #tgBillingPayableData.dataSource#,
        #tgBillingPayableData.calParameters#,
        #tgBillingPayableData.addType#,
        #tgBillingPayableData.addLoginId#,
        #tgBillingPayableData.lastUpdateLoginId#
        )
        ON DUPLICATE KEY UPDATE
        BPID = LAST_INSERT_ID(BPID),
        PlanAmount = PlanAmount + #tgBillingPayableData.planAmount#,
        LastUpdateDate = #tgBillingPayableData.addDate#,
        LastUpdateLoginId = #tgBillingPayableData.lastUpdateLoginId#,
        PayType = #tgBillingPayableData.payType#;
           <selectKey keyProperty="bpId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into TG_ActivityVoucher
		            (SerialNumber,
		             VoucherType,
		             OrderID,
		             UserID,
		             ShopID,
		             DealGroupID,
		             DealID,
		             ShopAccountID,
		             CardProductID,
		             CardName,
		             ReceiptAccountID,
		             Details,
		             Commission,
		             Quantity,
		             Price,
		             TotalPrice,
		             Cost,
		             TotalCost,
		             DealPrice,
		             DealCost,
		             DealMarketPrice,
		             DealBeginDate,
		             DealEndDate,
		             VoucherDate,
		             AddDate,
		             APStatus,
		             InvoiceStatus,
		             RevenueStatus,
		             CostStatus)
			values (#tgActivityData.serialNumber#,
			        #tgActivityData.voucherType#,
			        #tgActivityData.orderId#,
			        #tgActivityData.userId#,
			        #tgActivityData.shopId#,
			        #tgActivityData.dealGroupId#,
			        #tgActivityData.dealId#,
			        #tgActivityData.shopAccountId#,
			        #tgActivityData.cardProductId#,
			        #tgActivityData.cardName#,
		            #tgActivityData.receiptAccountId#,
		            #tgActivityData.details#,
		            #tgActivityData.commission#,
		            #tgActivityData.quantity#,
		            #tgActivityData.price#,
		            #tgActivityData.totalPrice#,
		            #tgActivityData.cost#,
		            #tgActivityData.totalCost#,
		            #tgActivityData.dealPrice#,
		            #tgActivityData.dealCost#,
		            #tgActivityData.dealMarketPrice#,
		            #tgActivityData.dealBeginDate#,
		            #tgActivityData.dealEndDate#,
		            #tgActivityData.voucherDate#,
		            now(),
		            #tgActivityData.apStatus#,
		            #tgActivityData.invoiceStatus#,
		            #tgActivityData.revenueStatus#,
		            #tgActivityData.costStatus#);
 		
           <selectKey keyProperty="voucherId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_BalanceRelation
        (BPID,
        BRID,
        FSPPID,
        RelationType,
        BalanceAmount,
        Status,
        AddDate
        )
        VALUES (#tgBalanceRelationData.bpId#,
        #tgBalanceRelationData.brId#,
        #tgBalanceRelationData.fsPPId#,
        #tgBalanceRelationData.relationType#,
        #tgBalanceRelationData.balanceAmount#,
        #tgBalanceRelationData.status#,
        #tgBalanceRelationData.addDate#
        )
           <selectKey keyProperty="balanceId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_RefundAccumulate
        (RAAmount,
        RADate,
        SettleLevel,
        CustomerGlobalID,
        CompanyGlobalID,
        ContractGlobalID,
        DealGroupID,
        DealID,
        BalanceType,
        AddDate,
        LastUpdateDate,
        AddLoginID,
        LastUpdateLoginID,
        DataSource,
        CalParameters,
        addType
        )
        VALUES (#tgRefundAccumulateData.raAmount#,
        #tgRefundAccumulateData.raDate#,
        #tgRefundAccumulateData.settleLevel#,
        #tgRefundAccumulateData.customerGlobalId#,
        #tgRefundAccumulateData.companyGlobalId#,
        #tgRefundAccumulateData.contractGlobalId#,
        #tgRefundAccumulateData.dealGroupId#,
        #tgRefundAccumulateData.dealId#,
        #tgRefundAccumulateData.balanceType#,
        #tgRefundAccumulateData.addDate#,
        #tgRefundAccumulateData.addDate#,
        #tgRefundAccumulateData.addLoginId#,
        #tgRefundAccumulateData.lastUpdateLoginId#,
        #tgRefundAccumulateData.dataSource#,
        #tgRefundAccumulateData.calParameters#,
        #tgRefundAccumulateData.addType#
        )
        ON DUPLICATE KEY UPDATE
        RAID = LAST_INSERT_ID(RAID),
        RAAmount = RAAmount + #tgRefundAccumulateData.raAmount#,
        LastUpdateDate = #tgRefundAccumulateData.addDate#,
        LastUpdateLoginID = #tgRefundAccumulateData.lastUpdateLoginId#;
           <selectKey keyProperty="raId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FC_PayFLow
                        (BatchId,
                         PPID,
                         PaySequence,
                         Status,
                         AddTime,
                         UpdateTime,
                         Memo)
            VALUES      (#payFlowData.batchId#,
                         #payFlowData.ppId#,
                         #payFlowData.paySequence#,
                         #payFlowData.status#,
                         #payFlowData.addTime#,
                         #payFlowData.updateTime#,
                         #payFlowData.memo#);
 		
           <selectKey keyProperty="flowId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	 
        	 INSERT INTO JS_ReceiveOrder
			(ReceiveAmount, 
			NeedReceiveDate, 
			ConfirmAmount, 
			ShopID, 
			ContractNo, 
			ContractGlobalID, 
			BusinessType, 
			AddType, 
			Status,
			AddDate,
			AddLoginID, 
			UpdateDate, 
			UpdateLoginID, 
			Memo
			)
			VALUES
			(#receiveOrderBean.receiveAmount#, 
			#receiveOrderBean.receiveDate#, 
			#receiveOrderBean.confirmAmount#,
			#receiveOrderBean.shopId#, 
			#receiveOrderBean.contractNo#, 
			#receiveOrderBean.contractGlobalId#, 
			#receiveOrderBean.businessType#, 
			#receiveOrderBean.addType#, 
			#receiveOrderBean.status#, 
			NOW(), 
			#receiveOrderBean.addLoginId#, 
			NOW(), 
			#receiveOrderBean.updateLoginId#, 
			#receiveOrderBean.memo#
			);
 		
		   <selectKey keyProperty="ReceiveOrderID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	 
        	INSERT INTO JS_ReceiveAudit
			(ReceiveOrderID, 
			Amount, 
			ReceiveDate, 
			BankID, 
			ReceiveType, 
			PayType, 
			AddDate,
			AddLoginID, 
			Memo,
			TradeNo,
			BizId
			)
			VALUES
			(#receiveAuditBean.receiveOrderId#, 
			#receiveAuditBean.amount#, 
			#receiveAuditBean.receiveDate#,
			#receiveAuditBean.bankId#,
			#receiveAuditBean.receiveType#, 
			#receiveAuditBean.payType#, 
			NOW(), 
			#receiveAuditBean.addLoginId#, 
			#receiveAuditBean.memo#,
			#receiveAuditBean.tradeNo#,
			#receiveAuditBean.bizId#
			);
 		
		   <selectKey keyProperty="ReceiveAuditID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL 
			(DEAL_GROUP_ID, 
			SHORT_TITLE, 
			ORIGINAL_PRICE, 
			RETAIL_PRICE, 
			COST_PRICE, 
			MAX_STOCK_QTY, 
			MAX_SALE_QTY, 
			MIN_SALE_QTY, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME,
			IS_DEFAULT, 
			SEQ,
			PRODUCT_ID_IN_IMSS,
			DUPLICATED_SERIALNUMBER_COUNT_IN_LASTBATCH,
			TOTAL_IMPORTED_SERIALNUMBER_COUNT,
			RECEIPT_CONTACT_INFO,
			BANK_ACCOUNT_GLOBAL_ID,
			IS_ACTIVE,
			DISPLAY_TYPE,
			DEAL_TYPE,
			SEC_KILL_SALE_RULE)
		VALUES
			(#entity.dealGroup.id#,
			#entity.shortTitle#,
			#entity.originalPrice#,
			#entity.retailPrice#,
			#entity.costPrice#,
			#entity.maxStockQty#,
			#entity.maxSaleQty#,
			#entity.minSaleQty#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.isDefault#,
			#entity.sequence#,
			#entity.productIdInImss#,
			#entity.duplicatedSerialNumberCountInLastBatch#,
			#entity.totalImportedSerialNumberCount#,
			#entity.receiptContactInfo#,
			#entity.bankAccountGlobalId#,
			#entity.isActive#,
			#entity.displayType#,
			#entity.dealType#,
			#entity.secKillSaleRule#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP (BUSINESS_TYPE_ID,CREATE_TIME,CREATOR_ID,DAYS_AFTER_INEFFECTIVE,DAYS_AFTER_ONLINE,
		DAYS_BEFORE_EFFECTIVE,EFFECTIVE_END_DATE,EFFECTIVE_END_TYPE,
		IS_DELIVERY_REQUIRED,IS_REFUNDABLE,IS_VOUCHER_AVAILABLE,LAST_UPDATE_TIME,LAST_UPDATOR_ID,PUBLISH_FROM_DATE,PUBLISH_TO_DATE,
		DISTRIBUTION_PARTY_ID,IS_TAX_INVOICE_AVARIABLE,VERSION_ID,CONTRACT_ID,IS_RELEASED_TO_ALL_CITIES,
		STATUS_ID,IS_HIGH_PROCESS_LEVEL,IS_HIGH_LEVEL_APPLIED_FOR,IS_VOUCHER_SETTED,VOUCHER_FORMAT_ID,
		VERIFICATION_DEVICE_ID,REMARKS,OTHER_EXCEPT_DATE,MAX_SALE_QTY,MIN_SALE_QTY,COMPANY_ID_IN_IMSS,THIRD_PARTY_VERIFY_PROVIDER_ID,EDITOR_ID,MAINTAINER_ID,
		IS_MANUAL_SET_REFUND,IS_AE_ASSIGNED,IS_HIDEN,PUBLISH_STATUS_ID,
		CUSTOMER_SERVICE_QQ,EFFECTIVE_BEGIN_DATE,EFFECTIVE_BEGIN_TYPE,IS_SUBMIT_EDITOR,
		BRIEF_DESC_DETAIL,
		BRIEF_DESC_SPECIAL_REMINDER,
		BRIEF_DESC_PRD_INTRDCTN,
		BRIEF_DESC_SHOP_INFO,
		BRIEF_DESC_SHOP_INTRDCTN,
        BRIEF_DESC_COMMENTS,
        IS_EDITOR_REQUIRED,SUBMIT_FOR_MERCHANT_CONFIRM_DATE,APPROVE_BY_MERCHANT_CONFIRM_DATE,IS_VALID,PARENT_DEAL_GROUP_ID,PUBLISH_TO_TYPE,DAYS_AFTER_ONLINE_OF_PUBLISH_TO_DATE,BRIEF_DESC_PROCESS_FOR_USE,
        IS_AUTO_DELAY, REFUND_REASON)
		VALUES (#entity.businessTypeId#,#entity.createTime#,#entity.creatorId#,#entity.daysAfterIneffective#,
		#entity.daysAfterOnline#,#entity.daysBeforeEffective#,
		#entity.effectiveEndDate#,#entity.effectiveEndType#,#entity.isDeliveryRequired#,#entity.refundableStatus#,
		#entity.isVoucherAvailable#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.publishFromDate#,#entity.publishToDate#,
		#entity.distributionPartyId#,#entity.isTaxInvoiceAvailable#,#entity.versionId#,
		#entity.contractId#,#entity.isReleasedToAllCities#,#entity.statusId#,#entity.isHighProcessLevel#,#entity.isHighLevelAppliedFor#,
		#entity.isVoucherSetted#,#entity.voucherFormatId#,#entity.verificationDeviceId#,
		#entity.remarks#,#entity.otherExceptDate#,#entity.maxSaleQty#,#entity.minSaleQty#,#entity.companyIdInImss#,#entity.thirdPartyVerifyProviderId#,#entity.editorId#,#entity.maintainerId#,
		#entity.isManualSetRefund#,#entity.isAEAssigned#,#entity.isHiden#,#entity.publishStatusId#,
		#entity.customerServiceQQ#,#entity.effectiveBeginDate#,#entity.effectiveBeginType#,#entity.isSubmitEditor#,
		#entity.briefDescription.detailedInformation#,
		#entity.briefDescription.specialReminder#,
		#entity.briefDescription.productIntroduction#,
		#entity.briefDescription.shopInformation#,
		#entity.briefDescription.shopIntroduction#,
		#entity.briefDescription.comments#,
		#entity.isEditorRequired#,#entity.submitForMerchantConfirmDate#,#entity.approveByMerchantConfirmDate#,#entity.isValid#,#entity.parentDealGroupId#,#entity.publishToType#,#entity.daysAfterOnlineOfPublishToDate#,#entity.briefDescription.processForUse#,
		#entity.isAutoDelay#, #entity.refundReason#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO ECP_PARTNER_DATA 
			(PARTNER_DEAL_GROUP_ID, 
			PARTNER_DATA, 
			STATUS, 
			PARTNER_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME,
			EXCEPTION_MESSAGE
			)
			VALUES
			(#partnerDealGroupId#, 
			#partnerData#, 
			#status#, 
			#partnerId#, 
			#createTime#, 
			#lastUpdateTime#,
			#exceptionMessage#
			);
		
	       <selectKey keyProperty="partnerDataId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_DEAL_GROUP_PROMOTION
        (DEAL_GROUP_PROMOTION_ID,
        CREATE_TIME,
        CREATOR_ID,
        LAST_UPDATE_TIME,
        LAST_UPDATOR_ID,
        DEAL_GROUP_ID,
        TYPE,
        PARAMETER,
        BEGIN_DATE,
        END_DATE,
        STATUS)
        VALUES
            (#entity.id#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.dealGroupId#,
            #entity.type#,
            #entity.parameter#,
            #entity.beginDate#,
            #entity.endDate#,
            #entity.status#
            );
    

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO BC_AccountOperationLog(ShopAccountID, LoginName, UserIP, LogInfo, AddDate, Comment)
			VALUES(#shopAccountId#, #accountName# ,#userIP#, #logInfo#, Now(), #comment#)
			
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO
		TGP_DEAL_GROUP_WORKFLOW_HISTORY
		(DEAL_GROUP_ID,PROCESS_INSTANCE_ID,OPERATION_ID,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME)
		VALUES
		(#entity.dealGroupId#,#entity.processInstanceId#,#entity.operationId#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_HOTEL
		(DEAL_ID,ROOM_TYPE,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME)
		VALUES
		(#entity.dealId#,#entity.roomType#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_EDITOR_TEAM
            (TEAM_NAME,
             TEAM_LEADER_ID,
             TEAM_LEADER_NAME,
             DEAL_GROUP_PRODUCE_TYPE,
             DEAL_GROUP_CATEGORY_ID,
             IS_ECOMMERCE,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             CREATE_TIME,
             LAST_UPDATE_TIME)
        VALUES (#entity.teamName#,
                #entity.teamLeaderId#,
                #entity.teamLeaderName#,
                #entity.dealGroupProduceTypeList,handler=dealGroupProduceTypeEnumsTypeHandler#,
                #entity.dealGroupCategoryId#,
                #entity.isEcommerce#,
                #entity.creatorId#,
                #entity.lastUpdatorId#,
                #entity.createTime#,
                #entity.lastUpdateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_EXCEPT_DATE
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		BEGIN_DATE,
		END_DATE,
		DEAL_GROUP_ID)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.beginDate#,
		#entity.endDate#,
		#entity.dealGroup.id#
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_LIST_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VISUAL_COMPONENT_ID,SEQ,PICTURE_URL,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.visualComponent.id#,#entity.sequence#,#entity.pictureUrl#,#entity.templateId#,'ImageTextItem')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_ACCOUNT
            (CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            ACCOUNT_GLOBAL_ID,
            HAS_DEAL_GROUP_APPROVED,
            HAS_CRM_DEAL_GROUP_APPROVED
            )
        VALUES
            (#entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.accountGlobalId#,
            #entity.hasDealGroupApproved#,
            #entity.hasCRMDealGroupApproved#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_AE
		(DEAL_GROUP_ID,AE_ID,AE_NAME,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,VERSION_ID,AE_PHONE,AE_EMAIL)
		VALUES
		(#entity.dealGroupId#,#entity.aeId#,#entity.aeName#,#entity.creatorId#,#entity.lastUpdatorId#,
		#entity.createTime#,#entity.lastUpdateTime#,#entity.versionId#,#entity.aePhone#,#entity.aeEmail#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_DEAL_GROUP_NAV_CATEGORY_ASSN 
			(DEAL_GROUP_ID,NAV_CATEGORY_ID,CREATE_TIME,LAST_UPDATE_TIME,CREATOR_ID,LAST_UPDATOR_ID,IS_DEFAULT) 
		VALUES 
			(#entity.dealGroup.id#,#entity.categoryId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.isDefault#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_VERSION
		(DEAL_GROUP_ID,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,PUBLISH_FROM_DATE,PUBLISH_TO_DATE,EFFECTIVE_BEGIN_DATE,
		EFFECTIVE_END_DATE,DEAL_GROUP_CONTENT,DOCUMENT_BUILDER_CONTENT,VISUAL_VIEW_CONTENT)
		VALUES
		(#entity.dealGroupId#,#entity.creatorId#,#entity.lastUpdatorId#,
		#entity.createTime#,#entity.lastUpdateTime#,#entity.publishFromDate#,#entity.publishToDate#,#entity.effectiveBeginDate#,#entity.effectiveEndDate#
		,#entity.dealGroupContent#,#entity.documentBuilderContent#,#entity.visualViewContent#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_AdminLogin(
			LoginId,
			AddTime,
			LoginName,
			RealName,
			AgentId)
		VALUES (
			#cscAdminLogin.loginId#,
			now(),
			#cscAdminLogin.loginName#,
			#cscAdminLogin.realName#,
			#cscAdminLogin.agentId#)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_TgProductProblem
		(
			DealGroupId, 
			TypeCode, 
			RootCategoryId,
			RootCategoryName,
			Memo,
			CaseId,
			ConsultationPeriod
		)
		VALUES
		(
			#cscTgProductProblem.dealGroupId#,
			#cscTgProductProblem.typeCode#,
			#cscTgProductProblem.rootCategoryId#,
			#cscTgProductProblem.rootCategoryName#,
			#cscTgProductProblem.memo#,
			#cscTgProductProblem.caseId#,
			#cscTgProductProblem.consultationPeriod#
		)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_WeiXinComplainHistory (
			AddTime,
			ComplainRecordId,
			Source,
			LoginId,
			OpenId,
			Reason,
			XmlData,
			Memo
		)
		VALUES (
			now(),
			#cscWeiXinComplainHistory.complainRecordId#,
			#cscWeiXinComplainHistory.source#,
			#cscWeiXinComplainHistory.loginId#,
			#cscWeiXinComplainHistory.openId#,
			#cscWeiXinComplainHistory.reason#,
			#cscWeiXinComplainHistory.xmlData#,
			#cscWeiXinComplainHistory.memo#
		)
		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_WeiXinComplainRecord (
			AddTime,
			OpenId,
			AppId,
			FeedBackId,
			TransId,
			Reason,
			Solution,
			ExtInfo,
			XmlData,
			DpUserId,
			DpOrderId
		)
		VALUES (
			now(),
			#cscWeiXinComplainRecord.openId#,
			#cscWeiXinComplainRecord.appId#,
			#cscWeiXinComplainRecord.feedBackId#,
			#cscWeiXinComplainRecord.transId#,
			#cscWeiXinComplainRecord.reason#,
			#cscWeiXinComplainRecord.solution#,
			#cscWeiXinComplainRecord.extInfo#,
			#cscWeiXinComplainRecord.xmlData#,
			#cscWeiXinComplainRecord.dpUserId#,
			#cscWeiXinComplainRecord.dpOrderId#
		)
		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO FS_MonitorTime
        (MonitorTime)
        VALUES (#monitorTimeData.monitorTime#)
           <selectKey keyProperty="MonitorID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into PC_Invoice
		            (InvoiceAmount,
		             InvoiceDate,
		             Status,
		             ContractGlobalID,
		             DealID,
		             DealGroupID,
		             ShopID,
		             ReceiptAccountID,
		             CustomerGlobalID,
		             CompanyGlobalID,
		             AddType,
		             AddDate,
		             AddLoginID,
		             LastUpdateDate,
		             FSInvoiceID,
		             VoucherID)
			values (#pcInvoiceData.invoiceAmount#,
			        now(),
			        #pcInvoiceData.status#,
			        #pcInvoiceData.contractGlobalId#,
			        #pcInvoiceData.dealId#,
			        #pcInvoiceData.dealGroupId#,
			        #pcInvoiceData.shopId#,
			        #pcInvoiceData.receiptAccountId#,
			        #pcInvoiceData.customerGlobalId#,
			        #pcInvoiceData.companyGlobalId#,
		            #pcInvoiceData.addType#,
		            now(),
		            #pcInvoiceData.addLoginId#,
		            now(),
		            #pcInvoiceData.fsInvoiceId#,
		            #pcInvoiceData.voucherId#);
 		
           <selectKey keyProperty="invoiceId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	 
            INSERT INTO `TG_ReceiptDetail`
            (`ReceiptID`,
            `DealID`,
            `ShopID`,
            `DealGroupID`,
            `Status`,
            `FinanceStatus`,
            `Price`,
            `Cost`,
            `APID`,
            `AddDate`,
            `UpdateDate`,
            `FinanceAddDate`,
            `FinanceUpdateDate`)
            VALUES
            (
            #receiptDetail.receiptId#,
            #receiptDetail.dealId#,
            #receiptDetail.shopId#,
            #receiptDetail.dealGroupId#,
            #receiptDetail.status#,
            #receiptDetail.financeStatus#,
            #receiptDetail.price#,
            #receiptDetail.cost#,
            #receiptDetail.apId#,
            #receiptDetail.addDate#,
            #receiptDetail.updateDate#,
            NOW(),
            NOW()
            );
 		
		   <selectKey keyProperty="ReceiptID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_Interaction(
        `MicroblogId`,
        `LoginId`,
        `Comment`,
        `Deleted`,
        `Type`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #interaction.microblogId#,
        #interaction.loginId#,
        #interaction.comment#,
        #interaction.deleted#,
        #interaction.type#,
        #interaction.addTime#,
        #interaction.updateTime#
        )

        
           <selectKey keyProperty="interactionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_TempLogin(
        `LoginId`,
        `TypoCount`,
        `LastTypoTime`,
        `TempLoginTime`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #tempLogin.loginId#,
        #tempLogin.typoCount#,
        #tempLogin.lastTypoTime#,
        #tempLogin.tempLoginTime#,
        #tempLogin.addTime#,
        #tempLogin.updateTime#
        )

        
           <selectKey keyProperty="tempLoginId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_Topic(
        `Title`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #topic.title#,
        #topic.addTime#,
        #topic.updateTime#
        )

        
           <selectKey keyProperty="topicId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO FC_PayRequest
                    (BizId,
                     CustomerId,
                     ShopId,
                     CustomerBankId,
                     PlanDate,
                     PlanAmount,
                     RequestDate,
                     BusinessType,
                     Token,
                     Status,
                     AddTime,
                     UpdateTime,
                     Memo)
        VALUES      (#payRequestData.bizId#,
                     #payRequestData.customerId#,
                     #payRequestData.shopId#,
                     #payRequestData.customerBankId#,
                     #payRequestData.planDate#,
                     #payRequestData.planAmount#,
                     #payRequestData.requestDate#,
                     #payRequestData.businessType#,
                     #payRequestData.token#,
                     #payRequestData.status#,
                     NOW(),
                     NOW(),
                     #payRequestData.memo#);
        
           <selectKey keyProperty="requestId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_DEAL_GROUP_THIRD_PARTNER_EXTEND
            (DEAL_GROUP_THIRD_PARTY_ID,
        CREATE_TIME,
        CREATOR_ID,
        LAST_UPDATE_TIME,
        LAST_UPDATOR_ID,
        DEAL_GROUP_ID,
        IS_REAL_NAME_REGISTRATION_NEEDED,
        NEED_CERTIFICATE
            )
        VALUES
            (#entity.id#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.dealGroupId#,
            #entity.isRealNameRegistrationNeeded#,
            #entity.needCertificate#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO RBAC_RolePermission(
        `RoleId`,
        `PermissionId`,
        `DueDate`,
        `Status`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #rolePermission.roleId#,
        #rolePermission.permissionId#,
        #rolePermission.dueDate#,
        #rolePermission.status#,
        #rolePermission.addTime#,
        #rolePermission.updateTime#
        )

        
           <selectKey keyProperty="rolePermissionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO RBAC_UserRole(
        `RoleId`,
        `UserId`,
        `DueDate`,
        `Status`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #userRole.roleId#,
        #userRole.loginId#,
        #userRole.dueDate#,
        #userRole.status#,
        #userRole.addTime#,
        #userRole.updateTime#
        )

        
           <selectKey keyProperty="userRoleId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CSC_HotelOrder (
			AddTime,
            OrderID,
            UserID,
            ShopID,
            ShopName,
            RoomName,
            RoomCount,
            OrderAmount,
            UserName,
            CheckInDate,
            CheckOutDate,
            MobileNo,
            Status,
            OrderAddDate,
            OrderSuccessDate,
            VendorName,
            VendorPhoneNo,
            VendorOrderId,
            Memo,
            CaseId,
            IsDelete
		)
		VALUES (
			now(),
			#cscHotelOrder.orderID#,
			#cscHotelOrder.userID#,
			#cscHotelOrder.shopID#,
			#cscHotelOrder.shopName#,
			#cscHotelOrder.roomName#,
			#cscHotelOrder.roomCount#,
			#cscHotelOrder.orderAmount#,
			#cscHotelOrder.userName#,
            #cscHotelOrder.checkInDate#,
            #cscHotelOrder.checkOutDate#,
            #cscHotelOrder.mobileNo#,
            #cscHotelOrder.status#,
			#cscHotelOrder.orderAddDate#,
			#cscHotelOrder.orderSuccessDate#,
			#cscHotelOrder.vendorName#,
			#cscHotelOrder.vendorPhoneNo#,
            #cscHotelOrder.vendorOrderId#,
			#cscHotelOrder.memo#,
			#cscHotelOrder.caseId#,
            #cscHotelOrder.isDelete#
		)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_SHOP_ASSN 
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		DEAL_ID,
		POI_SHOP_ID,
		SHOP_ID_TYPE)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.dealItem.id#,
		#entity.poiShopId#,
		#entity.shopIdType#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_DESTINATION
            (DEAL_GROUP_ID,
            CITY_ID,
            DISTRICT_ID,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID
            )
        VALUES
            (#entity.dealGroup.id#,
            #entity.cityId#,
            #entity.districtId#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#
            );
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_EDITOR_CITY_ASSN
            (EDITOR_ID,
             CITY_ID,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             CREATE_TIME,
             LAST_UPDATE_TIME)
        VALUES (#entity.editorId#,
            #entity.cityId#,
            #entity.creatorId#,
            #entity.lastUpdatorId#,
            #entity.createTime#,
            #entity.lastUpdateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_IMAGE_TEXT_DESC_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,IMAGE_TEXT_ITEM_ID,SEQ,CONTENT,IS_TITLE,TEMPLATE_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.imageTextItem.id#,#entity.sequence#,#entity.content#,#entity.isTitle#,#entity.templateId#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_LIST_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VISUAL_COMPONENT_ID,SEQ,CONTENT,QUANTITY,SPECIFICATION,UNIT,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.visualComponent.id#,#entity.sequence#,#entity.content#,#entity.quantity#,#entity.specification#,#entity.unit#,#entity.templateId#,'ProductItem')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_RESOURCE_AUTHORITY_CONFIG
            (
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            RESOURCE_CODE,
            ACTION_ID,
            POWER_CODE,
            PUBLISH_STATUS_ID,
            PROCESS_STATUS_ID
            )
        VALUES
            (
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.resourceCode#,
            #entity.actionId#,
            #entity.powerCode#,
            #entity.publishStatusId#,
            #entity.processStatusId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_SERIAL_NUM_OPERATION_LOG
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VERSION_ID,
		DEAL_ID,
		EXPORTED_COUNT,
		DTYPE)
		VALUES
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.versionId#,
		#entity.dealId#,
		#entity.exportedCount#,
		'SerialNumberExportLog')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,TEMPLATE_ID,AREA_TYPE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.templateId#,#entity.areaTypeId#,'TextAreaListComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_TOP_CITY_INFO
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		BEGIN_DATE,
		END_DATE,
		DEAL_GROUP_ID,
		CITY_ID)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.beginDate#,
		#entity.endDate#,
		#entity.dealGroup.id#,
		#entity.cityId#
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_VIEW 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VERSION_ID,JSON_CLOSURE,DEAL_GROUP_ID,TITLE,DESCRIPTION,IS_COMMERICAL_AREA_DISPLAYED,
		CORP_DESCRIPTION,
		CORP_NAME,
		CORP_IS_EXPANDED,
		SHORT_DESC,COMMENTS,
		PARENT_VISUAL_VIEW_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.versionId#,
		#entity.jsonClosure#,#entity.dealGroupId#,#entity.title#,#entity.description#,#entity.isCommericalAreaDisplayed#,
		#entity.corperationInfo.description#,#entity.corperationInfo.name#,#entity.corperationInfo.isExpanded#,
		#entity.shortDescription#,#entity.comments#,#entity.parentVisualViewId#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DOCUMENT_BUILDER 
			(CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VERSION_ID, 
			DEAL_GROUP_ID, 
			DOCUMENT_TEMPLATE_ID)
		VALUES
			(#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.versionId#,
			#entity.dealGroupId#,
			#entity.documentTemplateId#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_TEMPLATE_ENTRY 
			(DOCUMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			IS_MANDATORY,
			AREA_TYPE_ID,
			SEQUENCE,
			DTYPE)
		VALUES
			(#entity.documentTemplate.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.isMandatory#,
			#entity.areaTypeId#,
			#entity.sequence#,
			'StatementTemplate'
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_CARD
            (CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            NAME_TYPE,
            NAME,

            BACKGROUND_PICTURE_PATH,
            CATEGORY,
            CHARGE_AMOUNT,
            GIFT_AMOUNT,
            COMMISSION_RATE,
            DEAL_ID,
            CARD_TYPE,
            DISCOUNT
            )
        VALUES
            (#entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.nameType#,
            #entity.name#,

            #entity.backgroundPicPath#,
            #entity.category#,
            #entity.chargeAmount#,
            #entity.giftAmount#,
            #entity.commissionRate# ,
             #entity.dealId#,
               #entity.cardType#,
                #entity.discount#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_CONTACT
            (DEAL_GROUP_ID,
            CONTACT_NAME,
            CONTACT_EMAIL,
            CONTACT_MP,
            CONTACT_TYPE,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID
            )
        VALUES
            (#entity.dealGroup.id#,
            #entity.contactName#,
            #entity.contactEmail#,
            #entity.contactMP#,
            #entity.contactType#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#
            );
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_CONTRACT
            (CONTRACT_CRM_ID,
            CONTRACT_SERIAL,
            CONTRACT_GLOBAL_ID,
            ACCOUNT_NAME,
            ACCOUNT_GLOBAL_ID,
            FP_SETTLE_TYPE,
            CASH_OUT_TYPE,
            CITY_ID,
            SIGN_ON_SALES_ID,
            SIGN_ON_SALES_NAME,
            OWNER_SALES_ID,
            OWNER_SALES_NAME,
            PAY_TERM_TYPE,
            STATUS,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            ACCOUNT_REGISTER_NAME,
            ACCOUNT_RATING,
            ACCOUNT_ID,
            TYPE,
            SOURCE_TYPE
            )
        VALUES
            (#entity.contractCrmId#,
            #entity.contractSerial#,
            #entity.contractGlobalId#,
            #entity.accountName#,
            #entity.accountGlobalId#,
            #entity.fpSettleType#,
            #entity.cashOutType#,
            #entity.cityId#,
            #entity.signOnSalesId#,
            #entity.signOnSalesName#,
            #entity.ownerSalesId#,
            #entity.ownerSalesName#,
            #entity.payTermType#,
            #entity.status#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.accountRegisterName#,
            #entity.accountRating#,
            #entity.accountId#,
            #entity.type#,
            #entity.sourceType#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,IS_AUTO_CALCUATED,
		TITLE,
		TOTAL_PRICE,
		RETAIL_PRICE_DESC,
		TOTAL_PRICE_LABEL,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,
		#entity.isAutoCalcuated#,
		#entity.title#,
		#entity.totalPrice#,
		#entity.retailPriceDescription#,
		#entity.totalPriceLabel#,
		#entity.templateId#,
		'DealComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO 
		CSC_CaseReviewHide
		(
			AddTime, 
			LoginId, 
			ReviewId, 
			Status, 
			ReasonType, 
			DetailReason,
			CaseId
		)
		VALUES
		(
			now(),
			#loginId#,
			#reviewId#,
			#status#,
			#reasonType#,
			#detailReason#,
			#caseId#
		)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO CSC_Feedback (
                AddTime,
                Title,
                Content,
                FeederLoginId,
                FeederName,
                Status
			)
			VALUES
				(
					now(),
					#cscFeedback.title#,
					#cscFeedback.content#,
					#cscFeedback.feederLoginId#,
					#cscFeedback.feederName#,
					#cscFeedback.status#
				)
		

           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			CSC_GroupAdminLogin
			(
				AddTime,
				LoginId,
				GroupId
			)
		VALUES
			(
				now(),
				#groupAdminLogin.loginId#,
				#groupAdminLogin.groupId#
			)

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO Csc_Temp_ImportRefund (
				RefundID,
				OrderID,
				ReceiptID,
				ReceiptList,
				SerialNumber,
				AddDate,
				RefundDate,
				DealGroupId,
				DealId,
				Amount,
				DealGroupName,
				DealGroupPrice,
				DealPrice,
				City,
				CityName,
				OwnerLoginId,
				OwnerRealName,
				OwnerEmail,
				CustomerName,
				Memo,
				Status
			)
			VALUES
				(
					#tgRefundImportData.refundId#,
					#tgRefundImportData.orderId#,
					#tgRefundImportData.receiptId#,
					#tgRefundImportData.receiptList#,
					#tgRefundImportData.serialNumber#,
					#tgRefundImportData.addDate#,
					#tgRefundImportData.refundDate#,
					#tgRefundImportData.dealGroupId#,
					#tgRefundImportData.dealId#,
					#tgRefundImportData.amount#,
					#tgRefundImportData.dealGroupName#,
					#tgRefundImportData.dealGroupPrice#,
					#tgRefundImportData.dealPrice#,
					#tgRefundImportData.city#,
					#tgRefundImportData.cityName#,
					#tgRefundImportData.ownerLoginId#,
					#tgRefundImportData.ownerRealName#,
					#tgRefundImportData.ownerEmail#,
					#tgRefundImportData.customerName#,
					#tgRefundImportData.memo#,
					#tgRefundImportData.status#
				)
		

           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO CSC_CaseAttachment (
				CaseId,
				FileName,
				FilePath,
				Extention,
				LoginId,
				Memo
			)
			VALUES
				(
					#cscCaseAttachment.caseId#,
					#cscCaseAttachment.fileName#,
					#cscCaseAttachment.filePath#,
					#cscCaseAttachment.extention#,
					#cscCaseAttachment.loginId#,
					#cscCaseAttachment.memo#
				)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO 
		CSC_CaseBiz
		(
			AddTime, 
			CaseId, 
			BizType, 
			BizId
		)
		VALUES
		(
			now(),
			#caseId#,
			#bizType#,
			#bizId#
		)
		

		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CTI_TelephoneCalloutRecord (
        Source,
        AddTime,
        SubSource,
        UniqueID,
        IvrName,
        CallbackUrl,
        CalloutCookie,
        PhoneNumber,
        MaxAttempt,
        Content,
        Description,
        Apikey,
        TryDnis,
        Token,
        CustomData,
        STATUS,
        ResultMsg,
        RequestID,
        Dev,
        NeedRecord
        )
        VALUES
        (
        #source#,
        now(),
        #subsource#,
        #uniqueID#,
        #ivrName#,
        #callbackurl#,
        #calloutCookie#,
        #phonenumber#,
        #maxattempt#,
        #content#,
        #description#,
        #apikey#,
        #trydnis#,
        #token#,
        #customData#,
        #status#,
        #resultMsg#,
        #requestID#,
        #dev#,
        #needRecord#
        );
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CTI_IVRCallBackRecord (
        UniqueID,
        CallbackUrl,
        PhoneNumber,
        CustomData,
        CallbackEventType,
        SubCallbackEventType,
        OrderInfo,
        Input,
        CallbackData
        )
        VALUES
        (
        #uniqueID#,
        #callbackurl#,
        #phonenumber#,
        #customData#,
        #callbackEventType#,
        #subCallbackEventType#,
        #orderInfo#,
        #input#,
        #callbackData#
        );
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO FS_MonitorException
        (EoId,
        ExceptionType,
        AddDate,
        Status)
        VALUES (
        #exceptionData.eoId#,
        #exceptionData.exceptionType#,
        #exceptionData.addDate#,
        #exceptionData.status#)
           <selectKey keyProperty="ExceptionID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO FS_MonitorTodo
        (EOID,
        AddDate,
        Status)
        VALUES (
        #todoData.eoId#,
        #todoData.addDate#,
        #todoData.status#)
           <selectKey keyProperty="todoId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into FP_Invoice
		            (InvoiceDate,
		             InvoiceAmount,
		             InvoiceCode,
		             InvoiceTitle,
		             InvoiceType,
		             InvoiceStatus,
		             BusinessType,
		             ADDDATE,
		             AddLoginID,
		             UpdateDate,
		             UpdateLoginID)
			values (#invoiceApplyBean.invoiceApplyDate#,
			        #invoiceApplyBean.invoiceAmount#,
			        #invoiceApplyBean.invoiceCode#,
			        #invoiceApplyBean.invoiceTitle#,
			        #invoiceApplyBean.invoiceType#,
			        0,
			        #invoiceApplyBean.businessType#,
			        NOW(),
			        #invoiceApplyBean.loginID#,
			        NOW(),
			        #invoiceApplyBean.loginID#);
 		
           <selectKey keyProperty="InvoiceID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FP_InvoiceForBooking
	            (InvoiceID,
	             ShopID,
	             AddDate,
	             AddLoginID,
	             UpdateDate,
	             UpdateLoginID)
			VALUES (#invoiceID#,
			        #invoiceApplyBean.shopId#,
			        NOW(),
			        #invoiceApplyBean.loginID#,
			        NOW(),
			        #invoiceApplyBean.loginID#);
 		
           <selectKey keyProperty="InvoiceBookingID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into PC_AccountPayable
		            (APPlanDate,
		             APAmount,
		             CustomerGlobalID,
		             ContractGlobalID,
		             CompanyGlobalID,
		             DealGroupID,
		             DealID,
		             ShopID,
		             VoucherID,
		             Status,
		             AddType,
		             AddLoginID,
		             AddDate,
		             LastUpdateLoginID,
		             LastUpdateDate,
		             Memo,
		             CalParamters
		             )
			values (#PCAccountPayableData.apPlanDate#,
			        #PCAccountPayableData.apAmount#,
			        #PCAccountPayableData.customerGlobalId#,
			        #PCAccountPayableData.contractGlobalId#,
			        #PCAccountPayableData.companyGlobalId#,
			        #PCAccountPayableData.dealGroupId#,
			        #PCAccountPayableData.dealId#,
			        #PCAccountPayableData.shopId#,
			        #PCAccountPayableData.voucherId#,
			        #PCAccountPayableData.status#,
			        #PCAccountPayableData.addType#,
			        #PCAccountPayableData.addLoginId#,
			        now(),
			        #PCAccountPayableData.lastUpdateLoginId#,
			        now(),
			        #PCAccountPayableData.memo#,
			        #PCAccountPayableData.calParamters#
			        );
 		
           <selectKey keyProperty="apId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into PC_Revenue
		            (RevenueAmount,
		             TaxAmount,
		             RevenueDate,
		             DealID,
		             ShopID,
		             DealGroupID,
		             ReceiptAccountID,
		             CustomerGlobalID,
		             CompanyGlobalID,
		             ContractGlobalID,
		             AddDate,
		             StatisticsType,
		             VoucherID
		            )
			values (#pcRevenueData.revenueAmount#,
			        #pcRevenueData.taxAmount#,
			        now(),
			        #pcRevenueData.dealId#,
			        #pcRevenueData.shopId#,
			        #pcRevenueData.dealGroupId#,
			        #pcRevenueData.receiptAccountId#,
			        #pcRevenueData.customerGlobalId#,
			        #pcRevenueData.companyGlobalId#,
			        #pcRevenueData.contractGlobalId#,
			        now(),
			        #pcRevenueData.statisticsType#,
			        #pcRevenueData.voucherId#
		            );
 		
           <selectKey keyProperty="RevenueID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_Involved(
        `MicroblogId`,
        `LoginId`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #involved.microblogId#,
        #involved.loginId#,
        #involved.addTime#,
        #involved.updateTime#
        )

        
           <selectKey keyProperty="involvedId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MB_Microblog(
        `Content`,
        `LoginId`,
        `CommentCount`,

        `LikeCount`,
        `Deleted`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #microblog.content#,
        #microblog.loginId#,
        #microblog.commentCount#,

        #microblog.likeCount#,
        #microblog.deleted#,
        #microblog.addTime#,
        #microblog.updateTime#

        
           <selectKey keyProperty="microblogId" resultClass="int"/>

        )
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 insert into FS_ExchangeOrder
		            (OrderAmount,
		             OrderType,
		             BankAccountNo,
		             BankAccountName,
		             BankName,
		             BankProvince,
                     BankCity,
		             AddDate,
		             LastUpdateDate,
		             Memo,
		             BizCode,
		             AddLoginID
		             )
			values (#exchangeOrderData.orderAmount#,
			         #exchangeOrderData.orderType#,
			         #exchangeOrderData.bankAccountNo#,
			         #exchangeOrderData.bankAccountName#,
			         #exchangeOrderData.bankName#,
			         #exchangeOrderData.bankProvince#,
			         #exchangeOrderData.bankCity#,
			         NOW(),
			         NOW(),
			         #exchangeOrderData.memo#,
			         #exchangeOrderData.bizCode#,
			         #exchangeOrderData.addLoginId#
			        );
 		
           <selectKey keyProperty="exchangeOrderId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FC_PayOrder
			(PayAmount,
			PaySequence,
			PayCode,
			CustomerID,
			CustomerBankID,
			BankAccountNo,
			BankAccountName,
			BankBranchName,
			BankName,
			BankProvince,
			BankCity,
			BankFullBranchName,
			BankCode,
			BusinessType,
			Status,
			AddTime,
			AddType,
			UpdateTime,
			Memo,
			AddLoginID,
			UpdateLoginID
			)
			VALUES
			(#payOrderData.payAmount#,
			#payOrderData.paySequence#,
			#payOrderData.payCode#,
			#payOrderData.customerId#,
			#payOrderData.customerBankId#,
			#payOrderData.bankAccountNo#,
			#payOrderData.bankAccountName#,
			#payOrderData.bankBranchName#,
			#payOrderData.bankName#,
			#payOrderData.bankProvince#,
			#payOrderData.bankCity#,
			#payOrderData.bankFullBranchName#,
			#payOrderData.bankCode#,
			#payOrderData.businessType#,
			#payOrderData.status#,
			NOW(),
			#payOrderData.addType#,
			NOW(),
			#payOrderData.memo#,
			#payOrderData.addLoginId#,
			#payOrderData.updateLoginId#
			);
 		
           <selectKey keyProperty="poId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO ZH_ShopFundAccount
			(ShopID,AccountType,Credit,Debit,Balance,AddDate,UpdateDate)
			VALUES
			(#shopFundAccountBean.shopId#,
			#shopFundAccountBean.accountType#,
			#shopFundAccountBean.credit#,
			#shopFundAccountBean.debit#,
			#shopFundAccountBean.balance#,
			NOW(),
			NOW()
			);
 		
           <selectKey keyProperty="FundAccountID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO ZH_ShopFundAccountAudit
			(FundAccountID,ShopID,AccountType,Amount,TradeType,IsPositive,AddDate,Memo,ResourceType,ResourceID)
			VALUES
			(#shopFundAccountAuditBean.fundAccountId#,
			#shopFundAccountAuditBean.shopId#,
			#shopFundAccountAuditBean.accountType#,
			#shopFundAccountAuditBean.amount#,
			#shopFundAccountAuditBean.tradeType#,
			#shopFundAccountAuditBean.isPositive#,
			NOW(),
			#shopFundAccountAuditBean.memo#,
			#shopFundAccountAuditBean.resourceType#,
			#shopFundAccountAuditBean.resourceId#
			);
 		
           <selectKey keyProperty="AccountAuditID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FS_PaymentPlan
			(PlanDate,
			PlanAmount,
			PaidAmount,
			ContractGlobalID,
			BusinessType,
			CustomerGlobalID,
			CompanyGlobalID,
			ShopID,
			AddDate,
			LastUpdateDate,
			BalanceType,
			AddType,
			Memo,
			Sequence,
			AddLoginID
			)
			VALUES
			(#paymentPlanData.planDate#,
			#paymentPlanData.planAmount#,
			#paymentPlanData.paidAmount#,
			#paymentPlanData.contractGlobalId#,
			#paymentPlanData.businessType#,
			#paymentPlanData.customerGlobalId#,
			#paymentPlanData.companyGlobalId#,
			#paymentPlanData.shopId#,
			NOW(),
			NOW(),
			#paymentPlanData.balanceType#,
			#paymentPlanData.addType#,
			#paymentPlanData.memo#,
			#paymentPlanData.sequence#,
			#paymentPlanData.addLoginId#
			);
 		
           <selectKey keyProperty="ppId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	 INSERT INTO FS_PaymentPlanAuditLog
			(PPID,
			AuditType,
			AddDate,
			AddLoginID
			)
			VALUES
			(#paymentPlanAuditLogData.ppId#,
			#paymentPlanAuditLogData.auditType#,
			NOW(),
			#paymentPlanAuditLogData.addLoginId#
			);
 		
           <selectKey keyProperty="auditLogId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO CSC_CalloutRecord (
                AddTime,
                CallinPhoneNumber,
                CalloutPhoneNumber,
                CallResultStatus,
                BizType,
                CallinUcId,
                CalloutUcId
			)
			VALUES
				(
					now(),
					#cscCalloutRecord.callinPhoneNumber#,
					#cscCalloutRecord.calloutPhoneNumber#,
					#cscCalloutRecord.callResultStatus#,
					#cscCalloutRecord.bizType#,
					#cscCalloutRecord.callinUcId#,
					#cscCalloutRecord.calloutUcId#
				)
		

           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO RBAC_Manage(
        `ManagerId`,
        `ReferenceId`,
        `ReferenceType`,
        `Status`,
        `AddTime`,
        `UpdateTime`
        ) VALUES (
        #manage.managerId#,
        #manage.referenceId#,
        #manage.referenceType#,
        #manage.status#,
        #manage.addTime#,
        #manage.updateTime#
        )

        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_AttachmentMetaData
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_AttachmentMetaDataFuture
        (
        DisplayName,
        MimeType,
        TargetID,
        FileID,
        FileSkey,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        Owner,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        Status,
        CustomerID,
        TargetType,
        SFAttachmentID,
        SpecialApprovalBy,
        SpecialApprovalValidTo
        )
        values
        (
        #attachmentMetaData.displayName#,
        #attachmentMetaData.mimeType#,
        #attachmentMetaData.targetID#,
        #attachmentMetaData.fileID#,
        #attachmentMetaData.fileSkey#,
        NOW(),
        #attachmentMetaData.createdBy#,
        NOW(),
        #attachmentMetaData.lastModifiedBy#,
        #attachmentMetaData.owner#,
        #attachmentMetaData.approveID#,
        #attachmentMetaData.approveBy#,
        #attachmentMetaData.approveStatus#,
        #attachmentMetaData.approveComment#,
        #attachmentMetaData.eventID#,
        1,
        #attachmentMetaData.customerID#,
        #attachmentMetaData.targetType#,
        #attachmentMetaData.sfAttachmentID#,
        #attachmentMetaData.specialApprovalBy#,
        #attachmentMetaData.specialApprovalValidTo#
        )
           <selectKey keyProperty="AttachID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_AttachmentMetaDataHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_Contact
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_ContactFuture
        (
        SFContactID,
        ContactType,
        ContactStatus,
        Status,
        CustomerID,
        MpUserID,
        CorpRepContactName,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        Owner,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        ContactName,
        ContactMobile,
        ContactEmail,
        ContactTitle,
        ContactPhone,
        ContactComment,
        CCContactID
        )
        values
        (
        #contact.sfContactID#,
        #contact.contactType#,
        #contact.contactStatus#,
        1,
        #contact.customerID#,
        #contact.mpUserID#,
        #contact.corpRepContactName#,
        NOW(),
        #contact.createdBy#,
        NOW(),
        #contact.lastModifiedBy#,
        #contact.owner#,
        #contact.approveID#,
        #contact.approveBy#,
        #contact.approveStatus#,
        #contact.approveComment#,
        #contact.eventID#,
        #contact.contactName#,
        #contact.contactMobile#,
        #contact.contactEmail#,
        #contact.contactTitle#,
        #contact.contactPhone#,
        #contact.contactComment#,
        #contact.ccContactID#
        )
           <selectKey keyProperty="ContactID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_ContactHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerInvoiceTitle
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerInvoiceTitleFuture
        (
        CustomerID,
        SFInvoiceTitleID,
        InvoiceName,
        InvoiceType,
        TaxPayerNum,
        Address,
        Phone,
        BankAccountNum,
        BankName,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy
        )
        values
        (
        #customerInvoiceTitle.customerID#,
        #customerInvoiceTitle.sfInvoiceTitleID#,
        #customerInvoiceTitle.invoiceName#,
        #customerInvoiceTitle.invoiceType#,
        #customerInvoiceTitle.taxPayerNum#,
        #customerInvoiceTitle.address#,
        #customerInvoiceTitle.phone#,
        #customerInvoiceTitle.bankAccountNum#,
        #customerInvoiceTitle.bankName#,
        #customerInvoiceTitle.approveID#,
        #customerInvoiceTitle.approveBy#,
        #customerInvoiceTitle.approveStatus#,
        #customerInvoiceTitle.approveComment#,
        #customerInvoiceTitle.eventID#,
        1,
        NOW(),
        #customerInvoiceTitle.createdBy#,
        NOW(),
        #customerInvoiceTitle.lastModifiedBy#
        )
           <selectKey keyProperty="InvoiceTitleID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerInvoiceTitleHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_UserShopTerritoryFuture
        (UserID,
        NewShopID,
        TerritoryID,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        VALUES
        (#userShopTerritory.userID#,
        #userShopTerritory.shopID#,
        #userShopTerritory.territoryID#,
        1,
        NOW(),
        #userShopTerritory.createdBy#,
        NOW(),
        #userShopTerritory.lastModifiedBy#,
        #userShopTerritory.approveID#,
        #userShopTerritory.approveBy#,
        #userShopTerritory.approveStatus#,
        #userShopTerritory.approveComment#
        );
           <selectKey keyProperty="UserShopTerritoryID" resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_TradingLicenseFuture (
        SFTradingLicenseID,
        CustomerID,
        LicenseType,
        RegisterNum,
        CompanyName,
        Address,
        Name,
        CompanyType,
        RegisteredCapital,
        PaidinCapital,
        ValidFrom,
        ValidTo,
        Issuer,
        IssuingDate,
        CreatedBy,
        CreatedTime,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        LastModifiedTime,
        LastModifiedBy,
        EventID,
        SpecialApprovalBy,
        SpecialApprovalValidTo,
        Status )
        VALUES (
        #license.sfTradingLicenseID#,
        #license.customerID#,
        #license.licenseType#,
        #license.registerNum#,
        #license.companyName#,
        #license.address#,
        #license.name#,
        #license.companyType#,
        #license.registeredCapital#,
        #license.paidinCapital#,
        #license.validFrom#,
        #license.validTo#,
        #license.issuer#,
        #license.issuingDate#,
        #license.createdBy#,
        NOW(),
        #license.approveID#,
        #license.approveBy#,
        #license.approveStatus#,
        #license.approveComment#,
        NOW(),
        #license.lastModifiedBy#,
        #license.eventID#,
        SpecialApprovalBy=#hygienicLicense.specialApprovalBy#,
        SpecialApprovalValidTo=#hygienicLicense.specialApprovalValidTo#,
        1 )
           <selectKey keyProperty="TradingLicenseID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_TradingLicense (
        TradingLicenseID,
        SFTradingLicenseID,
        CustomerID,
        LicenseType,
        RegisterNum,
        CompanyName,
        Address,
        Name,
        CompanyType,
        RegisteredCapital,
        PaidinCapital,
        ValidFrom,
        ValidTo,
        Issuer,
        IssuingDate,
        CreatedBy,
        CreatedTime,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        LastModifiedTime,
        LastModifiedBy,
        EventID,
        SpecialApprovalBy,
        SpecialApprovalValidTo,
        Status )
        VALUES (
        #license.tradingLicenseId#,
        #license.sfTradingLicenseID#,
        #license.customerID#,
        #license.licenseType#,
        #license.registerNum#,
        #license.companyName#,
        #license.address#,
        #license.name#,
        #license.companyType#,
        #license.registeredCapital#,
        #license.paidinCapital#,
        #license.validFrom#,
        #license.validTo#,
        #license.issuer#,
        #license.issuingDate#,
        #license.createdBy#,
        #license.createdTime#,
        #license.approveID#,
        #license.approveBy#,
        #license.approveStatus#,
        #license.approveComment#,
        NOW(),
        #license.lastModifiedBy#,
        #license.eventID#,
        SpecialApprovalBy=#hygienicLicense.specialApprovalBy#,
        SpecialApprovalValidTo=#hygienicLicense.specialApprovalValidTo#,
        1 )
           <selectKey keyProperty="TradingLicenseID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_TradingLicenseHistory (
        TradingLicenseID,
        SFTradingLicenseID,
        CustomerID,
        LicenseType,
        RegisterNum,
        CompanyName,
        Address,
        Name,
        CompanyType,
        RegisteredCapital,
        PaidinCapital,
        ValidFrom,
        ValidTo,
        Issuer,
        IssuingDate,
        CreatedBy,
        CreatedTime,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        LastModifiedTime,
        LastModifiedBy,
        EventID,
        SpecialApprovalBy,
        SpecialApprovalValidTo,
        Status )
        VALUES (
        #license.tradingLicenseId#,
        #license.sfTradingLicenseID#,
        #license.customerID#,
        #license.licenseType#,
        #license.registerNum#,
        #license.companyName#,
        #license.address#,
        #license.name#,
        #license.companyType#,
        #license.registeredCapital#,
        #license.paidinCapital#,
        #license.validFrom#,
        #license.validTo#,
        #license.issuer#,
        #license.issuingDate#,
        #license.createdBy#,
        #license.createdTime#,
        #license.approveID#,
        #license.approveBy#,
        #license.approveStatus#,
        #license.approveComment#,
        NOW(),
        #license.lastModifiedBy#,
        #license.eventID#,
        SpecialApprovalBy=#hygienicLicense.specialApprovalBy#,
        SpecialApprovalValidTo=#hygienicLicense.specialApprovalValidTo#,
        1 )
           <selectKey keyProperty="TradingLicenseID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CT_ContractFuture
        (
        ContractNo,
        AAccountID,
        BAccountID,
        BizType,
        ValidFrom,
        ValidTo,
        ContractStatus,
        Status,
        Source,
        CreatedBy,
        CreatedTime,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
	    ApproveTime,
        HistoryID,
        SFContractID,
        CCContractID,
        ProcessID
        )
        values
        (
        #contract.contractNo#,
        #contract.aAccountID#,
        #contract.bAccountID#,
        #contract.bizType#,
        #contract.validFrom#,
        #contract.validTo#,
        #contract.contractStatus#,
        #contract.status#,
        #contract.source#,
        #contract.createdBy#,
        NOW(),
        NOW(),
        #contract.lastModifiedBy#,
        #contract.approveID#,
        #contract.approveBy#,
        #contract.approveStatus#,
	    #contract.approveComment#,
	    #contract.approveTime#,
        #contract.historyID#,
        #contract.sfContractID#,
        #contract.ccContractID#,
        #contract.processID#
        )
           <selectKey keyProperty="ContractID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CT_ContractHistory
           <include refid="key-value"/>
   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		Insert into Ods_Account
		(ShopID,ShopType,ShopName,BranchName,Address,CrossRoad,PhoneNo,PhoneNo2,
		CityID,Power,ShopGroupID,GroupFlag,AltName,SearchName,Hits,WeeklyHits,
		District,AddDate,LastDate,Score,Score1,Score2,Score3,Score4,ShopTags,
		PrimaryTag,SearchKeyWord,PrevWeeklyHits,WebSite,TodayHits,MonthlyHits,
		Popularity,GLat,GLng,ShopPower,NearByTags,BusinessHours,ClientType,
		City__c,Category__c, CategorySearch__c, StartScore__c, ShopType__c,
		Tag__c, ShopHyperLink__c, ShopStatus__c,District__c,CategoryFull__c,
		RegionFull__c,RegionSearch__c,MainCategory__c,MainRegion__c,BranchTotal
		)
		Values
		   <iterate conjunction="," property="list"/>

		ON DUPLICATE KEY UPDATE isDeleted = 0, BranchTotal = VALUES(BranchTotal)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		Insert into Ods_ShopIdToRetryCategoryRegionFlat
		(shopId, addDate)
		Values
		   <iterate conjunction="," property="list"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_Bank
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CT_AttachmentMetaData
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CT_AttachmentMetaDataFuture
        (
        DisplayName,
        MimeType,
        TargetID,
        FileID,
        FileSkey,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        Owner,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        Status,
        CustomerID,
        TargetType,
        SFAttachmentID,
        SpecialApprovalBy,
        SpecialApprovalValidTo
        )
        values
        (
        #attachmentMetaData.displayName#,
        #attachmentMetaData.mimeType#,
        #attachmentMetaData.targetID#,
        #attachmentMetaData.fileId#,
        #attachmentMetaData.fileSkey#,
        NOW(),
        #attachmentMetaData.createdBy#,
        NOW(),
        #attachmentMetaData.lastModifiedBy#,
        #attachmentMetaData.owner#,
        #attachmentMetaData.approveId#,
        #attachmentMetaData.approveBy#,
        #attachmentMetaData.approveStatus#,
        #attachmentMetaData.approveComment#,
        #attachmentMetaData.eventId#,
        1,
        #attachmentMetaData.customerId#,
        #attachmentMetaData.targetType#,
        #attachmentMetaData.sfAttachmentId#,
        #attachmentMetaData.specialApprovalBy#,
        #attachmentMetaData.specialApprovalValidTo#
        )
           <selectKey keyProperty="AttachID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CT_AttachmentMetaDataHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_Shop
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_ShopFuture
        (
        ShopID,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        values
        (
        #apolloShop.shopId#,
        NOW(),
        #apolloShop.createdBy#,
        NOW(),
        #apolloShop.lastModifiedBy#,
        #apolloShop.approveID#,
        #apolloShop.approveBy#,
        #apolloShop.approveStatus#,
        #apolloShop.approveComment#
        )
           <selectKey keyProperty="NewShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_ShopHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerUser
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerUserFuture
        (
        CustomerID,
        UserID,
        TerritoryID,
        IsPrimary,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        values
        (
        #customerUser.customerID#,
        #customerUser.userID#,
        #customerUser.territoryID#,
        #customerUser.isPrimary#,
        #customerUser.status#,
        NOW(),
        #customerUser.createdBy#,
        NOW(),
        #customerUser.lastModifiedBy#,
        #customerUser.approveID#,
        #customerUser.approveBy#,
        #customerUser.approveStatus#,
        #customerUser.approveComment#
        )
           <selectKey keyProperty="CustomerUserID" resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerUserHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_TerritoryFuture
        (
        TerritoryName,
        TerritoryDisplayName,
        ParentTerritoryID,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        VALUES
        (
        #territory.territoryName#,
        #territory.territoryDisplayName#,
        #territory.parentTerritoryID#,
        1,
        NOW(),
        #territory.createdBy#,
        NOW(),
        #territory.lastModifiedBy#,
        #territory.approveID#,
        #territory.approveBy#,
        #territory.approveStatus#,
        #territory.approveComment#
        );
           <selectKey keyProperty="TerritoryID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_ShopTerritoryFuture
        (NewShopID,
        TerritoryID,
        Kabc,
        TgReleaseDate,
        TgOfflineDate,
        CooperationStatus,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        VALUES
        (
        #shopTerritory.newShopID#,
        #shopTerritory.territoryID#,
        #shopTerritory.kabc#,
        #shopTerritory.tgReleaseDate#,
        #shopTerritory.tgOfflineDate#,
        #shopTerritory.cooperationStatus#,
        1,
        NOW(),
        #shopTerritory.createdBy#,
        NOW(),
        #shopTerritory.lastModifiedBy#,
        #shopTerritory.approveID#,
        #shopTerritory.approveBy#,
        #shopTerritory.approveStatus#,
        #shopTerritory.approveComment#
        );
           <selectKey keyProperty="ShopTerritoryID" resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_HygienicLicenseFuture
        (CustomerID,
        SFHygienicID,
        RegisterNum,
        CompanyName,
        Name,
        Address,
        Permit,
        ValidFrom,
        ValidTo,
        Issuer,
        IssuingDate,
        CreatedBy,
        CreatedTime,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        SpecialApprovalBy,
        SpecialApprovalValidTo,
        Status)
        VALUES (
        #hygienicLicense.customerID#,
        #hygienicLicense.sfHygienicID#,
        #hygienicLicense.registerNum#,
        #hygienicLicense.companyName#,
        #hygienicLicense.name#,
        #hygienicLicense.address#,
        #hygienicLicense.permit#,
        #hygienicLicense.validFrom#,
        #hygienicLicense.validTo#,
        #hygienicLicense.issuer#,
        #hygienicLicense.issuingDate#,
        #hygienicLicense.createdBy#,
        NOW(),
        NOW(),
        #hygienicLicense.lastModifiedBy#,
        #hygienicLicense.approveID#,
        #hygienicLicense.approveBy#,
        #hygienicLicense.approveStatus#,
        #hygienicLicense.approveComment#,
        #hygienicLicense.eventID#,
        #hygienicLicense.specialApprovalBy#,
        #hygienicLicense.specialApprovalValidTo#,
        1
        )
           <selectKey keyProperty="HygienicLicenseID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO CRM_Plus_Mark 
                (score,version,user,memo,date) 
            VALUES 
                (#markEntity.score#,
                 #markEntity.version#,
                 #markEntity.user#,
                 #markEntity.memo#,
                 NOW());
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CT_ShopFAQ(UserID, ShopID, Question, Answer, AnswerDate, Status, IsTop, AddDate)
		VALUES(#userId#, #shopId#, #question#, #answer#, #answerDate#, #status#, #isTop#, #addDate#)
		   <selectKey keyProperty="faqId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO	UC_UserCurrency
			(UserID,
			CurrencyAmount,
			CurrencyType,
			Channel,
			AddDate,
			UpdateDate,
			CityID)
		VALUES
			(#userId#,
			#currencyAmount#,
			#currencyType#,
			#channel#,
			now(),
			now(),
			#cityId#)
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO IN_TopShop_Certificate (
			ShopID,CertificateURL,
			CertificateTime,CreateTime,Type,Comments)
		VALUES (
			#certificate.shopId#,
			#certificate.certificateURL#,
			#certificate.certificateTime#,
			#certificate.createTime#,
			#certificate.type#,
			#certificate.comments#)
		    <selectKey type="post" keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_Customer
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerFuture
        (
        CCCustomerID,
        SFCustomerID,
        CustomerName,
        BizType,
        CustomerType,
        Status,
        CustomerStatus,
        OfficeAddress,
        CityID,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        Owner,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        CustomerClass
        )
        values
        (
        #customer.ccCustomerID#,
        #customer.sfCustomerID#,
        #customer.customerName#,
        #customer.bizType#,
        #customer.customerType#,
        1,
        #customer.customerStatus#,
        #customer.officeAddress#,
        #customer.cityID#,
        NOW(),
        #customer.createdBy#,
        NOW(),
        #customer.lastModifiedBy#,
        #customer.owner#,
        #customer.approveID#,
        #customer.approveBy#,
        #customer.approveStatus#,
        #customer.approveComment#,
        #customer.eventID#,
        #customer.customerClass#
        )
           <selectKey keyProperty="CustomerID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_OrgTerritoryFuture
        (OrgID,
        TerritoryID,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        VALUES
        (#orgTerritory.orgID#,
        #orgTerritory.territoryID#,
        1,
        NOW(),
        #orgTerritory.createdBy#,
        NOW(),
        #orgTerritory.lastModifiedBy#,
        #orgTerritory.approveID#,
        #orgTerritory.approveBy#,
        #orgTerritory.approveStatus#,
        #orgTerritory.approveComment#
        )
           <selectKey keyProperty="OrgTerritoryID" resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO ShopNewShop
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO ShopNewShopFuture
        (
        Status,
        NewShopStatus,
        ShopID,
        EventID,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        CustomerID,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment
        )
        values
        (
        1,
        #shopNewShop.newShopStatus#,
        #shopNewShop.shopID#,
        #shopNewShop.eventID#,
        NOW(),
        #shopNewShop.createdBy#,
        NOW(),
        #shopNewShop.lastModifiedBy#,
        #shopNewShop.customerID#,
        #shopNewShop.approveID#,
        #shopNewShop.approveBy#,
        #shopNewShop.approveStatus#,
        #shopNewShop.approveComment#
        )
           <selectKey keyProperty="ShopNewShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO ShopNewShopHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerShop
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerShopFuture
        (
        CustomerID,
        ShopType,
        NewShopID,
        ValidFrom,
        ValidTo,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        Owner,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        Status,
        ShopName,
        Comment
        )
        values
        (
        #customerShop.customerID#,
        #customerShop.shopType#,
        #customerShop.newShopID#,
        #customerShop.validFrom#,
        #customerShop.validTo#,
        NOW(),
        #customerShop.createdBy#,
        NOW(),
        #customerShop.lastModifiedBy#,
        #customerShop.owner#,
        #customerShop.approveID#,
        #customerShop.approveBy#,
        #customerShop.approveStatus#,
        #customerShop.approveComment#,
        #customerShop.eventID#,
        1,
        #customerShop.shopName#,
        #customerShop.comment#
        )
           <selectKey keyProperty="CustomerShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerShopHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerBankAccount
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerBankAccountFuture
        (
        AccountType,
        SFBankAccountID,
        BankName,
        BankAccountName,
        BankID,
        BankBranch,
        BankAccountNumber,
        Province,
        City,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        CustomerID,
        CCBankAccountID,
        PaymentStatus
        )
        values
        (
        #customerBankAccount.accountType#,
        #customerBankAccount.sfBankAccountID#,
        #customerBankAccount.bankName#,
        #customerBankAccount.bankAccountName#,
        #customerBankAccount.bankID#,
        #customerBankAccount.bankBranch#,
        #customerBankAccount.bankAccountNumber#,
        #customerBankAccount.province#,
        #customerBankAccount.city#,
        #customerBankAccount.approveID#,
        #customerBankAccount.approveBy#,
        #customerBankAccount.approveStatus#,
        #customerBankAccount.approveComment#,
        #customerBankAccount.eventID#,
        1,
        NOW(),
        #customerBankAccount.createdBy#,
        NOW(),
        #customerBankAccount.lastModifiedBy#,
        #customerBankAccount.customerID#,
        #customerBankAccount.ccBankAccountID#,
        #customerBankAccount.paymentStatus#
        )
           <selectKey keyProperty="AccountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerBankAccountHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_CustomerIDCardFuture
        (
        CustomerID,
        SFIDCardID,
        IDNumber,
        Name,
        Sex,
        Nationality,
        Birthday,
        Address,
        ValidFrom,
        ValidTo,
        Issuer,
        IssuingDate,
        CreatedBy,
        CreatedTime,
        LastModifiedBy,
        LastModifiedTime,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        SpecialApprovalBy,
        SpecialApprovalValidTo,
        Status,
        ContactID
        )
        VALUES
        (
        #idCard.customerID#,
        #idCard.sfIDCardID#,
        #idCard.idNumber#,
        #idCard.name#,
        #idCard.sex#,
        #idCard.nationality#,
        #idCard.birthday#,
        #idCard.address#,
        #idCard.validFrom#,
        #idCard.validTo#,
        #idCard.issuer#,
        #idCard.issuingDate#,
        #idCard.createdBy#,
        NOW(),
        #idCard.lastModifiedBy#,
        NOW(),
        #idCard.approveID#,
        #idCard.approveBy#,
        #idCard.approveStatus#,
        #idCard.approveComment#,
        #idCard.eventID#,
        #idCard.specialApprovalBy#,
        #idCard.specialApprovalValidTo#,
        #idCard.status#,
        #idCard.contactID#
        )
           <selectKey keyProperty="IDCardID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_UserCurrencyTrans
		(
		UserID,        
		TYPE,        
		ReferTranID,  
		Channel,     
		OpRole,    
		BizType,    
		OpType,     
		BizID,      
		BizID2,       
		CurrencyAmount, 
		CurrencyType,   
		OpId,           
		Remark,         
		ADDDATE,        
		UpdateDate,     
		CityID       
		)
		VALUES
		(#currencyTransInfo.userId#,
		#currencyTransInfo.transType.type#,
		#currencyTransInfo.referTranid#,
		#currencyTransInfo.channel.channelType#,
		#currencyTransInfo.opRole.type#,
		#currencyTransInfo.bizType.type#,
		#currencyTransInfo.opType.type#,
		#currencyTransInfo.bizId#,
		#currencyTransInfo.bizId2#,
		#currencyTransInfo.currencyAmount#,
		#currencyTransInfo.currencyType.currencyType#,
		#currencyTransInfo.opId#,
		#currencyTransInfo.remark#,
		now(),
		now(),
		#currencyTransInfo.cityId#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_License
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_LicenseFuture
        (
        SFLicenseID,
        CustomerID,
        LicenseType,
        LicenseName,
        RegAddr,
        RegNum,
        Issuer,
        RegCapital,
        PaidinCapital,
        IssuedDate,
        BizScope,
        CorpRepName,
        CorpType,
        EstiblishmentDate,
        ValidFrom,
        ValidTo,
        ApproveID,
        ApproveBy,
        ApproveStatus,
        ApproveComment,
        EventID,
        Status,
        CreatedTime,
        CreatedBy,
        LastModifiedTime,
        LastModifiedBy,
        SpecialApprovalBy,
        SpecialApprovalValidTo
        )
        values
        (
        #license.sfLicenseID#,
        #license.customerID#,
        #license.licenseType#,
        #license.licenseName#,
        #license.regAddr#,
        #license.regNum#,
        #license.issuer#,
        #license.regCapital#,
        #license.paidinCapital#,
        #license.issuedDate#,
        #license.bizScope#,
        #license.corpRepName#,
        #license.corpType#,
        #license.estiblishmentDate#,
        #license.validFrom#,
        #license.validTo#,
        #license.approveID#,
        #license.approveBy#,
        #license.approveStatus#,
        #license.approveComment#,
        #license.eventID#,
        1,
        NOW(),
        #license.createdBy#,
        NOW(),
        #license.lastModifiedBy#,
        #license.specialApprovalBy#,
        #license.specialApprovalValidTo#
        )
           <selectKey keyProperty="LicenseID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MR_LicenseHistory
           <include refid="key-value"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	INSERT INTO AD_ProductCity
		(
		ProductId, 
		CityId, 
		 AddTime,
		 AddUser,
		 UpdateTime,
		 UpdateUser,
		 Status 
		)
		VALUES
		(
		#productCity.productId#, 
		#productCity.cityId#, 
		#productCity.addTime#,
		 #productCity.addUser#,
		 #productCity.updateTime#,
		 #productCity.updateUser#,
		 #productCity.status# 
		);
	
	   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	INSERT INTO AD_ProductCity
		(
		ProductId, 
		CityId, 
		 AddTime,
		 AddUser,
		 UpdateTime,
		 UpdateUser,
		 Status 
		)
		VALUES
	
	   <iterate conjunction="," property="productCityList"/>
   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	INSERT INTO AD_Product
		(ProductCode,
		ProductName, 
		 ProductType, 
		 ProductSubType,
		 TargetPage,
		 CarouselType,
		 IsAllowFree,
		 InventoryLimit,
		 
		 AddTime,
		 AddUser,
		 UpdateTime ,
		 UpdateUser ,
		 Status,
		 BeginDate,
		 EndDate,
		 IsSaleByBrandGroup,
		 CarouselTimesLimit,
		 SalableEndDate,
		 SalableBeginDate
		)
		VALUES
		(#productEntity.productCode#,
		#productEntity.productName#, 
		#productEntity.productType#, 
		#productEntity.productSubType#,
		 #productEntity.targetPage#,
		 #productEntity.carouselType#,
		 #productEntity.isAllowFree#,
		 #productEntity.inventoryLimit#,
		 
		 #productEntity.addTime#,
		 #productEntity.addUser#,
		 #productEntity.updateTime#,
		 #productEntity.updateUser#,
		 
		 #productEntity.status#,
		 #productEntity.beginDate#,
		 #productEntity.endDate#,
		 #productEntity.isSaleByBrandGroup#,
		 #productEntity.carouselTimesLimit#,
		 #productEntity.salableEndDate#,
		  #productEntity.salableBeginDate#
		);
	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_NaviDealTag (DealGroupID, TagID, ItemID)
        VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItemAttribute (NaviTagItemAttributeID, ItemID, Name, Value)
		VALUES (
		#id#, #itemId#, #name#, #value#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_dpQzoneFriendTopic
        (
        TopicType,TopicID,UserID,OpenID,AddTime,UpdateTime

        ) VALUES
        (
        #topicType#,#topicId#,#userId#,#openId#,NOW(),NOW()
        );
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_dpQzoneLike
        (
        FeedID,UserID,Status,AddTime,UpdateTime
        ) VALUES
        (
        #feedId#,#userId#,1,Now(),Now()
        );
           <selectKey keyProperty="likeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_NaviDealTag (DealGroupID, TagID, ItemID)
        VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_DealGroupCity
        ( DealGroupID, CityID )
        VALUES
           <iterate conjunction="," property="dealGroupCities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupExtend
		(
			DealGroupID,
			ADDDATE,
			UpdateDate,
			IsShowDealGroupRegion,
			IsAsyncReceipt,
			ActivationType,
			AheadHours,
			EditorTeam,
			SourceID,
			IsBlockStock
		)
		VALUES
		(
			#dealGroupExtend.id#,
			NOW(),
			NOW(),
			#dealGroupExtend.isShowDealGroupRegion#,
			#dealGroupExtend.isAsyncReceipt#,
			#dealGroupExtend.activationType#,
			#dealGroupExtend.aheadHours#,
			#dealGroupExtend.editorTeam#,
			#dealGroupExtend.sourceId#,
			#dealGroupExtend.isBlockStock#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		REPLACE INTO TG_ShopRegion (ShopID, DistrictID, RegionID) 
		VALUES 
		   <iterate conjunction="," property="shopRegions"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO CQRS_ORDER
		(ORDER_ID,CONTENT)
		VALUES
		(#entity.id#,#entity.content#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_Attribute
        (`Id`,`Name`,`DisplayName`,`NeedIndex`,`IsMapping`,`Status`)
        VALUES
        (#attribute.id#,#attribute.name#,#attribute.displayName#,#attribute.needIndex#,#attribute.isMapping#,#attribute.status#)
           <selectKey keyProperty="Id" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_NaviCategory
        (`ID`,`Name`,`AttributeValue`,`Status`)
        VALUES
        (#naviCategory.id#,#naviCategory.name#,#naviCategory.attributeValue#,#naviCategory.status#)
           <selectKey keyProperty="ID" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_NaviItem
        (`ID`,`TagID`,`Name`,`AttributeValue`,`Status`)
        VALUES
        (#naviItem.id#,#naviItem.tagID#,#naviItem.name#,#naviItem.attributeValue#,#naviItem.status#)
           <selectKey keyProperty="ID" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_NaviTag
        (`ID`,`Name`,`AttributeKey`,`Type`,`Status`)
        VALUES
        (#naviTag.id#,#naviTag.name#,#naviTag.attributeKey#,#naviTag.type#,#naviTag.status#)
           <selectKey keyProperty="ID" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupAttribute
        (DealGroupId, AttrName, AttrValue, Status, AddTime, UpdateTime)
        VALUES
           <iterate conjunction="," property="attrList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TPD_AttributeItem
		(
		Id, Name, AttributeId, DisplayName, Status, MappingRule, AddTime, UpdateTime
		)
		VALUES
		(
		#attributeItem.id#,
		#attributeItem.name#,
		#attributeItem.attributeId#,
		#attributeItem.displayName#,
		#attributeItem.status#,
		#attributeItem.rule#,
		NOW(),
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 
		INSERT INTO AD_ItemKeyword 
			(OrderItemId, 
			ShopId, 
			NewShopId,
			Keyword, 
			InventoryStatus, 
			DisplayStatus, 
			ADDTIME, 
			AddUser, 
			UpdateTime, 
			UpdateUser, 
			STATUS
			)
			VALUES
			(#itemKeyword.orderItemId#, 
			#itemKeyword.shopId#, 
			#itemKeyword.newShopId#,
			#itemKeyword.keyword#, 
			#itemKeyword.inventoryStatus#, 
			#itemKeyword.displayStatus#, 
			#itemKeyword.addTime#, 
			#itemKeyword.addUser#, 
			#itemKeyword.updateTime#, 
			#itemKeyword.updateUser#, 
			#itemKeyword.status#
			);
		  
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 
		INSERT INTO AD_Order
		(
			 ShopType,
			 MainCategoryId,
			 Amount,
			 SalesCityId,
			 PriceLoadTime,
			 SourceType, 
			 AddTime,
			 AddUser,
			 UpdateTime, 
			 UpdateUser,
			 STATUS 
		) 
		VALUES
		(
			#order.shopType#,
			#order.mainCategoryId#,
			#order.amount#,
			#order.salesCityId#,
			#order.priceLoadTime#,
			#order.sourceType#,
			#order.addTime#,
			#order.addUser#,
			#order.updateTime#,
			#order.updateUser#,
			#order.status#
		)
		

		   <selectKey keyProperty="orderId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 
		INSERT INTO AD_OrderItemDuration
		(
		OrderItemId,
		BeginDate,
		EndDate,
		ADDTIME,
		AddUser,
		UpdateTime,
		UpdateUser,
		Status
		) 
		VALUES 
		(
		#OrderItemDuration.orderItemId#,
		#OrderItemDuration.beginDate#,
		#OrderItemDuration.endDate#,
		#OrderItemDuration.addTime#,
		#OrderItemDuration.addUser#,
		#OrderItemDuration.updateTime#,
		#OrderItemDuration.updateUser#,
		1
		)
		  
		   <selectKey keyProperty="orderItemDurationId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	INSERT INTO AD_ProductDisplayTemplate
		(
		  
		ProductId, 
		TemplateName,
		IsCustomSize,
		AddTime,
		AddUser,
		UpdateTime,
		UpdateUser,
		Status,
		isMaster
		)
		VALUES
		(
		#productDisplayTemplate.productId#, 
		#productDisplayTemplate.templateName#, 
		#productDisplayTemplate.isCustomSize#,
		#productDisplayTemplate.addTime#,
		#productDisplayTemplate.addUser#,
	 	#productDisplayTemplate.updateTime#,
		#productDisplayTemplate.updateUser#,
		#productDisplayTemplate.status#,
		#productDisplayTemplate.isMaster#
		
		);
	
	   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO LAB_Comment
			(UserID, AppID, Content, AddDate, UpdateDate)
		VALUES 
			(#userId#, #appId#, #content#, NOW(), NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyText (Content)
		VALUES
            (#content#)
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_dpQzoneFollowNote
        (
        MainNoteID,FromUserID,NoteBody,AddDate,UpdateDate,UserIP,ToUserID,OriginalUserID,GrandpaID,MainNoteTitle,VerifyStatus
           <isNotEmpty prepend="," property="userType"/>

        ) VALUES
        (
        #feedId#,#fromUserID#,#noteBody#,Now(),Now(),#userIP#,#toUserID#,#originalUserID#,#grandpaID#,#mainNoteTitle#,#verifyStatus#
           <isNotEmpty prepend="," property="userType"/>

        );
           <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_DealGroupVerify
        ( VerifyID, DealID, DealGroupID, AddDate, UpdateDate, Status, ThirdPartyDealID, ThirdPartyId, Num, IsspID )
        VALUES
        ( #verify.verifyID#, #verify.dealID#, #verify.dealGroupID#, NOW(), NOW(), #verify.status#,
          #verify.thirdPartyDealID#, #verify.thirdPartyId#, #verify.num#, #verify.isspID# )
           <selectKey keyProperty="VerifyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealRegion
		(
		DealGroupID,
		RegionID,
		CityID,
		District,
		AddDate,
		UpdateDate
		)
		VALUES
		(
		#dealRegion.dealGroupId#,
		#dealRegion.regionId#,
		#dealRegion.cityId#,
		#dealRegion.district#,
		NOW(),
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO CQRS_SEAT_AVAILABILITY
		(ORDER_ID)
		VALUES
		(#entity.order.id#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_NaviRegion
        (`ID`,`TagID`,`Name`,`AttributeValue`,`CityID`,`ParentID`,`Priority`,`Status`)
        VALUES
        (#naviRegion.id#,#naviRegion.tagID#,#naviRegion.name#,#naviRegion.attributeValue#,#naviRegion.cityID#,#naviRegion.parentID#,#naviRegion.priority#,#naviRegion.status#)
           <selectKey keyProperty="ID" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupVersion (`DealGroupID`,`PublishVersion`, AddTime, UpdateTime)
        VALUES ( #dealGroupId# , #version# , NOW(), NOW())
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_TransferJob
        (`Id`,`Detail`,`Status`, AddTime)
        VALUES
        (#transferJob.id#,#transferJob.detail#,#transferJob.status#, NOW())
           <selectKey keyProperty="Id" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 INSERT 
		INTO AD_CategoryPriceLevel 
		(ParentCategoryId,
		 CategoryId,
		 CityId, 
		 SearchPageLevel, 
		 MainPageLevel, 
		 Status, 
		 AddUser,
		 AddTime, 
		 UpdateUser,
		 UpdateTime
		)
		 VALUES
		  (
		  #priceLevel.parentCategoryId#,
		  #priceLevel.categoryId#, 
		  #priceLevel.cityId#, 
		  #priceLevel.searchPageLevel#,
		  #priceLevel.mainPageLevel#,
		  #priceLevel.status#, 
		  #priceLevel.addUser#,
		  #priceLevel.addTime#,
		  #priceLevel.updateUser#, 
		  #priceLevel.updateTime# 
		  ); 
		  
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 INSERT 
		INTO AD_CategoryPriceLevelConfig
		(ParentCategoryId,
		 CategoryId 
		)
		 VALUES
		  (
		  #parentCategoryId#,
		  #categoryId# 
		  ); 
		  
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_FeedBackForPOI
			(UserName,
			UserEmail,
			UserPhone,
			UserID,
			FeedTitle,
			FeedComments,
			FeedAdddate,
			FeedStatus,
			MailTO,
			FeedType,
			ReferID,
			ReferUserID,
			ReferShopID,
			FeedGroupID,
			CauseType,
			ClientType)
		VALUES
			(#userName#,
			#userEmail#,
			#userPhone#,
			#userId#,
			#feedTitle#,
			#feedComments#,
			#feedAdddate#,
			#feedStatus#,
			#mailTo#,
			#feedType#,
			#referId#,
			#referUserId#,
			#referShopId#,
			#feedGroupId#,
			#causeType#,
			#clientType#)
		   <selectKey keyProperty="feedId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (
				#tagId#, #itemName#, #itemEnName#, 1, #priority#
			)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (ItemID, TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (#itemId#, #tagId#, #itemName#, #itemEnName#, 1, #priority#)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (
				#tagId#, #itemName#, #itemEnName#, 1, #priority#
			)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (ItemID, TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (#itemId#, #tagId#, #itemName#, #itemEnName#, 1, #priority#)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItemAttribute (NaviTagItemAttributeID, ItemID, Name, Value)
		VALUES (
		#id#, #itemId#, #name#, #value#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TPD_Attribute
		(
		Id, Name, DisplayName, Status, NeedIndex, IsMapping, AddTime, UpdateTime
		)
		VALUES
		(
		#attribute.id#,
		#attribute.name#,
		#attribute.displayName#,
		#attribute.status#,
		#attribute.needIndex#,
		#attribute.isMapping#,
		NOW(),
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 
		INSERT INTO AD_ItemButton 
		(
			OrderItemId,
			CategoryLevelId,
			ShopType, 
			CategoryId, 
			TemplateId, 
			ADDTIME, 
			AddUser, 
			UpdateTime, 
			UpdateUser, 
			STATUS
		)
		VALUES
		(
			#itemButton.orderItemId#,
			#itemButton.categoryLevelId#,
			#itemButton.shopType#, 
			#itemButton.categoryId#, 
			#itemButton.templateId#, 
			#itemButton.addTime#, 
			#itemButton.addUser#, 
			#itemButton.updateTime#, 
			#itemButton.updateUser#, 
			#itemButton.status#
		);
	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
	    	INSERT INTO AD_Keyword 
	    		(Keyword,
	    		CityId,
	    		PriceLevel,
	    		TopThreeStock,
	    		FirstPageStock,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime,
	    		Status) 
	   		VALUES 
	   			(#keywordEntity.keyword#,
	    		#keywordEntity.cityId#,
	    		#keywordEntity.priceLevel#,
	    		#keywordEntity.topThreeStock#,
	    		#keywordEntity.firstPageStock#,
	    		#keywordEntity.addUser#,
	    		#keywordEntity.updateUser#,
	    		#keywordEntity.addTime#, 
		        #keywordEntity.updateTime#, 
	    		#keywordEntity.status#);
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    	INSERT INTO AD_Keyword
	    		(Keyword,
	    		CityId,
	    		PriceLevel,
	    		TopThreeStock,
	    		FirstPageStock,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime,
	    		Status)
	   		VALUES
           <iterate conjunction="," property="keywordList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 
		INSERT INTO AD_OrderItem
		(OrderId,
		CityId,
		ProductId,
		ProductType,
		ProductSubType,
		OrderItemPrice,
		STATUS,
		ADDTIME,
		AddUser,
		UpdateTime,
		UpdateUser
		) 
		VALUES
		(
		#orderItem.orderId#,
		#orderItem.cityId#,
		#orderItem.productId#,
		#orderItem.productType#,
		#orderItem.productSubType#,
		#orderItem.orderItemPrice#,
		#orderItem.status#,
		#orderItem.addTime#,
		#orderItem.addUser#,
		#orderItem.updateTime#,
		#orderItem.updateUser#
		)
		  

		   <selectKey keyProperty="orderItemId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO AD_TrafficPriceLevel(CityId, ShopType, PriceLevel, `Status`, AddTime, UpdateTime, AddUser, UpdateUser)
        VALUES (#trafficPriceLevel.cityId#, #trafficPriceLevel.shopType#,
          #trafficPriceLevel.priceLevel#, #trafficPriceLevel.status#,
          #trafficPriceLevel.addTime#, #trafficPriceLevel.updateTime#,
          #trafficPriceLevel.addUser#, #trafficPriceLevel.updateUser#
        )
        
           <selectKey keyProperty="ID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupRemind(
		RemindId,
		UserId,
		ShopGroupId,
		ShopGroupName,
		Status,
		AddDate,
		LastDate,
		DealGroupID,
		CityID,
		ReferType
		)VALUES(
		#dealGroupRemind.remindId#,
		#dealGroupRemind.userId#,
		#dealGroupRemind.shopGroupId#,
		#dealGroupRemind.shopGroupName#,
		#dealGroupRemind.status#,
		#dealGroupRemind.addDate#,
		#dealGroupRemind.lastDate#,
		#dealGroupRemind.dealGroupId#,
		#dealGroupRemind.cityId#,
		#dealGroupRemind.referType#);
		   <selectKey keyProperty="remindId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_WishList(DealGroupID, UserID, Status, AddDate, LastDate)
		VALUES(#dealGroupId#, #userId#, 1, NOW(), NOW())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_Dairy (UserID,Title,Visit,Type,Status,Flag,Star,AddDate,UpdateDate,CityID)
		VALUES
            (#userId#, #title#, 0, #type#, #status#, #flag#, #star#, #addDate#, #updateDate#,#cityId#)
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyList (UserID,Title,Visit,Status,AddDate,UpdateDate)
		VALUES
            (#userId#, #title#, 0, #status#, now(), now())
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairySection
			(UserID, 
    		DairyID, 
    		SectionIndex, 
    		SectionType, 
    		Flag, 
    		BizType, 
    		BizID, 
    		Detail, 
    		AddDate, 
    		UpdateDate)
		VALUES
		   <iterate conjunction="," property="sections"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupDetail
		(
			DealGroupID,
			Title,
			Content,
			Type,
			AddDate
		)
		VALUES
		(
			#entity.dealGroupID#,
			#entity.title#,
			#entity.content#,
			#entity.type#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupDetail
		(
			DealGroupID,
			Title,
			Content,
			Type,
			AddDate
		)
		VALUES
		   <iterate conjunction="," property="entityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		REPLACE INTO TG_ShopInfo 
		(	
			ShopID,
			CityID,
			ShopGroupID,
			ShopName,
			BranchName,
			PhoneNo,
			PhoneNo2,
			Address,
			Glat,
			Glng,
			ShopPower,
			Score1,
			Score2,
			Score3,
			BusinessHours,
			MainCategoryID
		) 
		VALUES 
		   <iterate conjunction="," property="shopInfos"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_TransferTask
        (`Id`,`JobId`,`Detail`,`Status`,`RetryTimes`, AddTime)
        VALUES
        (#transferTask.id#,#transferTask.jobId#,#transferTask.detail#,#transferTask.status#,#transferTask.retryTimes#, NOW())
           <selectKey keyProperty="Id" resultClass="int"/>
;
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO AD_CRMRelation 
				(OpportunityId, 
				ContractId, 
				OrderId, 
				AddUser, 
				ADDTIME, 
				UpdateUser, 
				UpdateTime
				)
				VALUES
				(#crmRelation.opportunityId#, 
				#crmRelation.contractId#, 
				#crmRelation.orderId#, 
				#crmRelation.addUser#, 
				#crmRelation.addTime#, 
				#crmRelation.updateUser#, 
				#crmRelation.updateTime#
				);
			  
		  
		    <selectKey keyProperty="crmRelationId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	 
		INSERT INTO AD_ItemCityTone
		(
		OrderItemId,
		ShopId,
		NewShopId,
		ADDTIME,
		AddUser,
		UpdateTime,
		UpdateUser,
		Status
		) 
		VALUES 
		(
		#ItemCityTone.orderItemId#,
		#ItemCityTone.shopId#,
		#ItemCityTone.newShopId#,
		#ItemCityTone.addTime#,
		#ItemCityTone.addUser#,
		#ItemCityTone.updateTime#,
		#ItemCityTone.updateUser#,
		1
		)
		  
		   <selectKey keyProperty="itemCityToneId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
	
	INSERT INTO AD_Price
		(
			Price,
			Unit,
			ProductId,
			CityId,
			PriceLevel,
			BeginDate,
			EndDate,
			DatePeriodKey,
			CityGroupKey,
			AddTime,
			AddUser,
			UpdateTime,
			UpdateUser,
			Status
		)
		VALUES
		(
			#priceEntity.price#,
			#priceEntity.unit#,
			#priceEntity.productId#,
			#priceEntity.cityId#,
			#priceEntity.priceLevel#,
			#priceEntity.beginDate#,
			#priceEntity.endDate#,
			#priceEntity.datePeriodKey#,
			#priceEntity.cityGroupKey#,
			#priceEntity.addTime#,
			#priceEntity.addUser#,
			#priceEntity.updateTime#,
			#priceEntity.updateUser#,
			#priceEntity.status#
		);
	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	INSERT INTO AD_ProductDisplayTemplateItem
		(
		  
		TemplateId, 
		TemplateItemName,
		TemplateItemType,
		
		ImageHeight,
		ImageWidth,
		TextLength,
		MaterialPattern,
		
		
		AddTime,
		AddUser,
		UpdateTime,
		UpdateUser,
		Status 
		)
		VALUES
		
	
	
	   <iterate conjunction="," property="productDisplayTemplateItemList"/>
   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_FeedBack
			(UserName,
			UserEmail,
			UserPhone,
			UserID,
			FeedTitle,
			FeedComments,
			FeedAdddate,
			MailTO,
			FeedType,
			ReferID,
			ReferUserID,
			ReferShopID,
			FeedGroupID,
			CauseType,
			CityID)
		VALUES
			(#userName#,
			#userEmail#,
			#userPhone#,
			#userId#,
			#feedTitle#,
			#feedComments#,
			#feedAdddate#,
			#mailTo#,
			#feedType#,
			#referId#,
			#referUserId#,
			#referShopId#,
			#feedGroupId#,
			#causeType#,
			#cityId#)
		   <selectKey keyProperty="feedId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO LAB_Reply
			(CommentID, Dper, Content)
		VALUES 
			(#commentId#, #dper#, #reply#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_dpQzoneOfficialLike
        (
        FeedID,UserID,SourceType,Status,AddTime,UpdateTime
        ) VALUES
        (
        #feedId#,#userId#,#sourceType#,1,Now(),Now()
        );
           <selectKey keyProperty="likeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_WishList(DealGroupID, UserID, Status, AddDate, LastDate)
		VALUES(#dealGroupId#, #userId#, 1, NOW(), NOW())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupAttribute
        (DealGroupId, AttrName, AttrValue, Status, AddTime, UpdateTime)
        VALUES
           <iterate conjunction="," property="attrList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupAttributeText
        (DealGroupId, AttrName, AttrValue, Status, AddTime, UpdateTime)
        VALUES
           <iterate conjunction="," property="attrList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupShopDetail
		(
			DealGroupID,
			Address,
			ContactPhone,
			BusinessHours,
			IsStarRateDisplayed,
			IsAverageConsumeDisplayed,
			IsReviewDisplayed,
			IsMapDisplayed,
			ShopID,
			AddDate
		)
		VALUES
		(
			#entity.dealGroupID#,
			#entity.address#,
			#entity.contactPhone#,
			#entity.businessHours#,
			#entity.isStarRateDisplayed#,
			#entity.isAverageConsumeDisplayed#,
			#entity.isReviewDisplayed#,
			#entity.isMapDisplayed#,
			#entity.shopID#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupShopDetail
		(
			DealGroupID,
			Address,
			ContactPhone,
			BusinessHours,
			IsStarRateDisplayed,
			IsAverageConsumeDisplayed,
			IsReviewDisplayed,
			IsMapDisplayed,
			ShopID,
			AddDate
		)VALUES
		   <iterate conjunction="," property="entityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealShopInfo
		(
			DealGroupID, 
			ShopID, 
			ShopName, 
			BranchName, 
			PhoneNoAddress
		)
		VALUES
		(
			#dealShopInfo.dealGroupId#,
			#dealShopInfo.shopId#,
			#dealShopInfo.shopName#,
			#dealShopInfo.branchName#,
			#dealShopInfo.phoneNoAddress#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			UC_DairyShare (DairyID,Comment,Title,Summary,Url,Images,Status,FromUrl,AdminName,RegularDate,ActualDate,AddDate,UpdateDate)
		VALUES 
			(#dairyId#,
	        #comment#,
	        #title#,
	        #summary#,
            #url#,
	        #images#,
	        0,
	        #fromUrl#,
	        #adminName#,
            #regularDate#,
	        NOW(),
	        NOW(),
	        NOW())
	       <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_dpQzoneMessage (FromUserID,ToUserID,FeedID,MsgType,MsgContent,AddTime,UpdateTime)
        VALUES (#fromUserId#,#toUserId#,#feedId#,#msgType#,#msgContent#,NOW(),NOW())
           <selectKey keyProperty="msgId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupPublishCategory (`DealGroupID`,`PublishCategoryID`, AddTime, UpdateTime)
        VALUES
        (#dealGroupPublishCategory.dealGroupID#,#dealGroupPublishCategory.publishCategoryID#, NOW(), NOW());
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupAttributeText
        (DealGroupId, AttrName, AttrValue, Status, AddTime, UpdateTime)
        VALUES
           <iterate conjunction="," property="attrList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPD_DealGroupPublishCategory (`DealGroupID`,`PublishCategoryID`, AddTime, UpdateTime)
        VALUES
        (#dealGroupPublishCategory.dealGroupId#,#dealGroupPublishCategory.publishCategoryId#, NOW(), NOW());
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_ExchangeOpLog
			(OperatorName,
			LogInfo,
			BizId,
			Channel,
			CityId,
			OpType,
			AddTime,
			UpdateTime)
		VALUES(
			#opLog.operatorName#,
			#opLog.logInfo#,
			#opLog.bizId#,
			#opLog.channel#,
			#opLog.cityId#,
			#opLog.opType#,
			now(),
			now())		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			PicID,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#album.shopId#, 
			#album.name#,
			#album.picPath#, 
			#album.picType#,
			#album.picCount#,
			#album.isMain#,
			#album.picId#,
			#album.description#,
			#album.addTime#,
			#album.updateTime#,0);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_PrizeGroup
		(
		GroupName,
		BaseCount,
		Memo,
		ChanceNum,
		EventID,
		GroupType,
		StartDate,
		EndDate,
		Status,
		IPLimit
		)
		VALUES
		(
		#groupName#,
		#baseCount#,
		#memo#,
		#chanceNum#,
		#eventId#,
		#groupType#,
		#startDate#,
		#endDate#,
		#status#,
		#ipLimit#
		);
		   <selectKey keyProperty="groupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_Questionnaire(GroupID,QuestionnaireType,QuestionnaireTitle,QuestionOrder,AddTime,Boundary)
		VALUES(#groupId#, #questionnaireType#,#questionnaireTitle#,#questionOrder#,NOW(),#boundary#);
		   <selectKey keyProperty="questionnaireId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_PrizeRecord
		(UserID,
		PrizeID,
		PrizeName,
		IP,
		GroupID,
		CityID,
		EventID) VALUES
		( #userId#,
		#prizeId#,
		#prizeName#,
		#ip#,
		#groupId#,
		#cityId#,
		#eventId#
		);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireGroup(EventID,GroupType,LimitType,Memo,ChanceNum,IPLimit,StartTime,EndTime,State)
		VALUES(#eventId#, #groupType#,#limitType#,#memo#,#chanceNum#,#ipLimit#,#startTime#,#endTime#,#state#);
		   <selectKey keyProperty="groupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_SimplePrize(PrizeName, Msg, GroupType, GroupId,
		PrizeType)
		VALUES (#prizeName#, #msg#, #groupType#, #groupId#,
		#prizeType#);
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO nr_booklendrecord
          (UserID,BookOwnerID,ReadingProgress,Status,AddDate,LendDate,CompleteDate,ReturnDate,UpdateDate)
          VALUES
          (#bookLendRecordData.userId#,#bookLendRecordData.bookOwnerId#,#bookLendRecordData.readingProgress#,#bookLendRecordData.status#,
          now(),#bookLendRecordData.lendDate#,#bookLendRecordData.completeDate#,#bookLendRecordData.returnDate#,NOW());
        
           <selectKey keyProperty="bookLendId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO nr_bookownerinfo (UserID,BookID,Status,LendCount,AddDate,UpdateDate)
          VALUES
          (#bookOwnerInfoData.userId#,#bookOwnerInfoData.bookId#,#bookOwnerInfoData.status#,#bookOwnerInfoData.lendCount#,now(),now());
        
           <selectKey keyProperty="bookOwnerId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	      
		    INSERT INTO EV_SMSFailLog(EventID,ReferedID,UserID,MobileNo,SendContext,Remark,IP,Status)
	        VALUES(
	        #eventSMSFailLog.eventId#,
	        #eventSMSFailLog.referedId#,
	        #eventSMSFailLog.userId#,
	        #eventSMSFailLog.mobileNo#,
	        #eventSMSFailLog.sendContext#,
	        #eventSMSFailLog.remark#,
	        #eventSMSFailLog.ip#,
	        #eventSMSFailLog.status#)
	          
	           <selectKey keyProperty="logId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			PicID,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#album.shopId#, 
			#album.name#,
			#album.picPath#, 
			#album.picType#,
			#album.picCount#,
			#album.isMain#,
			#album.picId#,
			#album.description#,
			#album.addTime#,
			#album.updateTime#,0);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO test
          (id, test)
          VALUES
          (#test.id#,#test.name#);
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		EV_Comment(GroupID,UserID,EventId,CommentText,PicPath,PicType,AddTime,UserIP,VerifyStatus)
		VALUES(#groupId#,#userId#,#eventId#,#commentText#,#picPath#,#picType#,NOW(),#IP#,0);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		EV_Comment(GroupID,UserID,EventIdCommentText,AddTime,UserIP,VerifyStatus)
		VALUES(#groupId#,#userId#,#eventId#,#commentText#,NOW(),#IP#,0);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_Prize
		(
		Rank,
		PrizeName,
		Memo,
		TotalCount,
		ConsumeCount,
		Msg,
		Difficulty,
		GroupID,
		Pic,
		PrizeType,
		`Order`
		) VALUES
		(
		#rank#,
		#prizeName#,
		#memo#,
		#totalCount#,
		0,
		#msg#,
		#difficulty#,
		#groupId#,
		#pic#,
		#prizeType#,
		#order#
		);
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_PrizeGroup
		(
		GroupName,
		BaseCount,
		Memo,
		ChanceNum,
		EventID,
		GroupType,
		StartDate,
		EndDate,
		Status,
		IPLimit
		)
		VALUES
		(
		#groupName#,
		#baseCount#,
		#memo#,
		#chanceNum#,
		#eventId#,
		#groupType#,
		#startDate#,
		#endDate#,
		#status#,
		#ipLimit#
		);
		   <selectKey keyProperty="groupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_PrizeRecordStatus
		(UserID,GroupID,EventID,PrizeID,IP,MobileNo,Status)
		VALUES(#userId#,#groupId#,#eventId#,#prizeId#,#ip#,#mobileNo#,#status#)
		   <selectKey keyProperty="recordId" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO EV_PrizeRecordStatus(RecordID,UserID,GroupID,MobileNo,EventID,IP,PrizeID,Status)
        SELECT  a.RecordID,a.UserID,a.GroupID,a.MobileNo,a.EventID,a.IP,a.PrizeID,9
        FROM EV_PrizeRecord a
        WHERE a.RecordID  not in
        (SELECT RecordID FROM  EV_PrizeRecordStatus)
        AND a.GroupID = #groupId#
        AND PrizeId = #prizeId#
        AND a.Status = 0;
           <selectKey keyProperty="recordId" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO EV_BackLottery(EventID,ReferID,UserID,UserNickName,CityID,MobileNo,Email,Score,IP,LotteryType,Status,LogID)
    	VALUES(#eventId#,#referId#,#userId#,#userNickName#,#cityId#,#mobileNo#,#email#,#score#,#ip#,#lotteryType#,#prizeSize#,#logId#)
    	   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    	INSERT INTO EV_PromoType(Remark,AddDate)
	    	VALUES(#remark#,#currentTime#);
	       <selectKey keyProperty="promoType" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.ACT_Launch
		(
          ActivityID,
          ModType,
          LacunshName,
          ShopID,
          CreateTime,
          UpdateTime,
          Status
		)VALUES
		(
         #activityID#,
         #modType#,
         #lacunshName#,
         #shopID#,
         NOW(),
         NOW(),
         0
		);
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ACT_MaterialDeploy
		(
          LunchID,
          MStandardID,
          PracticalValue,
          `Status`,
         CreateTime,
          UpdateTime
		)VALUES
		(
         #lunchID#,
         #mStandardID#,
         #practicalValue#,
         #status#,
         NOW(),
         NOW()
		);
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.ACT_Module
		(
          ModuleName,
          RawHTML,
          CreateTime,
          UpdateTime,
          `Status`,
          ModType,
          PicUrl,
          Introduce,
          EditJs
		)VALUES
		(
          #moduleName#,
          #rawHTML#,
          NOW(),
          NOW(),
          1,
          #modType#,
          #picUrl#,
          #introduce#,
          #editJs#
		);
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_PrizeGroup
		(
		GroupName,
		BaseCount,
		Memo,
		ChanceNum,
		EventID,
		GroupType,
		StartDate,
		EndDate,
		Status,
		IPLimit
		)
		VALUES
		(
		#groupName#,
		#baseCount#,
		#memo#,
		#chanceNum#,
		#eventId#,
		#groupType#,
		#startDate#,
		#endDate#,
		#status#,
		#ipLimit#
		);
		   <selectKey keyProperty="groupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_PrizeRecord
		(UserID,
		PrizeID,
		PrizeName,
		IP,
		GroupID,
		CityID,
		EventID) VALUES
		( #userId#,
		#prizeId#,
		#prizeName#,
		#ip#,
		#groupId#,
		#cityId#,
		#eventId#
		);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireOption(QuestionnaireID,Type,Label,Text,Point,AddTime)
		VALUES(#questionnaireId#, #type#,#label#,#text#,#point#,NOW());
		   <selectKey keyProperty="optionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_SimplePrize(PrizeName, Msg, GroupType, GroupId,
		PrizeType)
		VALUES (#prizeName#, #msg#, #groupType#, #groupId#,
		#prizeType#);
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_SimplePrizeRecord(UserId, UserName, MobileNo, Address, PrizeId, PrizeName, IP, GroupType,GroupId, CityId, EventId, Status)
		VALUES (#userId#, #userName#, #mobileNo#, #address#, #prizeId#, #prizeName#, #IP#, #groupType#, #groupId#, #cityId#, #eventId#, 1);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EVP_WebModule(EventID,BizData,Status,CustomJS,RawHTML)
		VALUES(#eventId#, #bizData#,#status#,#customJs#,#rawHtml#);
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EventList
		(EventID, EventName, EventURL, EventPic, StartDate, EndDate, VisitCount,
		AttendCount, Introduce, State, OfflineDate, CityID, AdminID)
		VALUES
		(#eventId#, #eventName#, #eventURL#, #eventPic#, #startDate#, #endDate#, 0, 0,
		#introduce#, 1, #offlineDate#, #cityId#, #adminId#);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO EV_BackLotteryLog(EventID,ReferID,LotteryType,StartTime,LotterySize,LotteryNum,UserID)
        VALUES(#eventId#,#referId#,#lotteryType#,#startTime#,#lotterySize#,#lotteryNum#,#userId#)
              <selectKey keyProperty="logId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireOption(QuestionnaireID,Type,Label,Text,Point,AddTime)
		VALUES(#questionnaireId#, #type#,#label#,#text#,#point#,NOW());
		   <selectKey keyProperty="optionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireResultRule(GroupID,BeginScore,EndScore,ContainsBegin,ContainsEnd,Text,AddTime)
		VALUES(#groupId#, #beginScore#,#endScore#,#containsBegin#,#containsEnd#,#text#,NOW());
		   <selectKey keyProperty="resultId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EventList
		(EventID, EventName, EventURL, EventPic, StartDate, EndDate, VisitCount,
		AttendCount, Introduce, State, OfflineDate, CityID, AdminID)
		VALUES
		(#eventId#, #eventName#, #eventURL#, #eventPic#, #startDate#, #endDate#, 0, 0,
		#introduce#, 1, #offlineDate#, #cityId#, #adminId#);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
	
		INSERT INTO DPEvent.EV_PrizeRecord 
			(UserID, 
			PrizeID, 
			PrizeName,
			IP, 
			GroupID,
			CityID,
			EventID) VALUES 
			( #userId#, 
			#prizeId#, 
			#prizeName#, 
			#ip#, 
			#groupId#, 
			#cityId#,
			#eventId#
			);	
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_BookingPaySequence
        ()
        VALUES
        ()
           <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_ExchangeGift
		(GiftName,
		GiftPrice,
		GiftSummary,
		GiftDescripttion,
		GiftPic,AddTime,UpdateTime)
		VALUES(#gift.giftName#,#gift.giftPrice#,#gift.giftSummary#," ",#gift.giftPic#,now(),now())
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_ExchangeGiftItem
		(GiftType,
		GiftID,
		GiftPrice,
		ShopID,
		ShopName,
		CityID,
		CityName,
		ExchangeType,
		SingleMax,
		ExchangeRule,
		DeliverType,
		ADDTIME,
		UpdateTime,
		ExchangeMaxCount,
		RemainCount,
		ExchangePrice,
		ItemType,
		   <isNotNull property="itemRule.beginTime"/>
   <isNotNull property="itemRule.endTime"/>

		GiftTag
		)
		VALUES
		(#itemRule.giftType#,
		#itemRule.giftID#,
		#itemRule.giftPrice#,
		#itemRule.sponsorShopID#,
		#itemRule.sponsorShopName#,
		#itemRule.cityID#,
		#itemRule.cityName#,
		#itemRule.exchangeType#,
		#itemRule.singleMax#,
		   <isNotNull property="itemRule.exchangeRule"/>
   <isNull property="itemRule.exchangeRule"/>

		#itemRule.deliverType#,
		now(),
		now(),
		#itemRule.exchangeMaxCount#,
		#itemRule.remainCount#,
		#itemRule.exchangePrice#,
		#itemRule.itemType#,
		   <isNotNull property="itemRule.beginTime"/>
   <isNotNull property="itemRule.endTime"/>

		#itemRule.giftTag#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO nr_book
          (DouBanID,Title,Author,Translator,ISBN,Publisher,PublishDate,Price,Rating,ImgUrl,LargeImgUrl,AddUserID,AddDate)
          VALUES
          (#bookData.douBanId#,#bookData.title#,#bookData.author#,#bookData.translator#,#bookData.isbn#,#bookData.publisher#,
          #bookData.publishDate#,#bookData.price#,#bookData.rating#,#bookData.imgUrl#,#bookData.largeImgUrl#,#bookData.addUserId#,now());
        
           <selectKey keyProperty="bookId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO Market_App(
            Id,
            AddTime,
            AppName,
            AppVisitUrl)
        VALUES (
            #marketApp.id#,
            now(),
            #marketApp.appName#,
            #marketApp.appVisitUrl#)

           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		EV_Comment(GroupID,UserID,EventId,CommentText,PicPath,PicType,AddTime,UserIP,VerifyStatus)
		VALUES(#groupId#,#userId#,#eventId#,#commentText#,#picPath#,#picType#,NOW(),#IP#,0);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		EV_Comment(GroupID,UserID,EventIdCommentText,AddTime,UserIP,VerifyStatus)
		VALUES(#groupId#,#userId#,#eventId#,#commentText#,NOW(),#IP#,0);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_Prize
			(
				Rank, 
				PrizeName, 
				Memo, 
				TotalCount, 
				ConsumeCount, 
				Msg, 
				Difficulty, 
				GroupID, 
				Pic, 
				PrizeType,
				`Order`
			) VALUES 
			( 
				#rank#, 
				#prizeName#, 
				#memo#, 
				#totalCount#, 
				0, 
				#msg#, 
				#difficulty#, 
				#groupId#, 
				#pic#, 
				#prizeType#,
				#order#
			);	
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    	INSERT INTO EV_PromoDownload(PromoID,UserID,EventID,MobileNo,CityID,Score,IP)
	    	VALUES(#promoId#,#userId#,#eventId#,#mobileNo#,#cityId#,#Score#,#ip#)
	    	   <selectKey keyProperty="logId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DPEvent.EV_PrizeRecordStatus
        (UserID,GroupID,EventID,PrizeID,IP,MobileNo,Status)
        VALUES(#userId#,#groupId#,#eventId#,#prizeId#,#ip#,#mobileNo#,#status#)
           <selectKey keyProperty="recordId" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO EV_PrizeRecordStatus(RecordID,UserID,GroupID,MobileNo,EventID,IP,PrizeID,Status)
        SELECT  a.RecordID,a.UserID,a.GroupID,a.MobileNo,a.EventID,a.IP,a.PrizeID,9
        FROM EV_PrizeRecord a
        WHERE a.RecordID  not in
        (SELECT RecordID FROM  EV_PrizeRecordStatus)
        AND a.GroupID = #groupId#
        AND PrizeId = #prizeId#
        AND a.Status = 0;
           <selectKey keyProperty="recordId" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_Questionnaire(GroupID,QuestionnaireType,QuestionnaireTitle,QuestionOrder,AddTime,Boundary)
		VALUES(#groupId#, #questionnaireType#,#questionnaireTitle#,#questionOrder#,NOW(),#boundary#);
		   <selectKey keyProperty="questionnaireId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_EdmDealGroup (dealGroupId, dealGroupDesc, title, dealGroupPrice, marketPrice, pic, addTime) VALUES
           <iterate conjunction="," property="batchData"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_EdmDealGroupShop (dealGroupId, shopId, addTime ) VALUES
           <iterate conjunction="," property="batchData"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DPEvent.ACT_Activity
        (
        ActivityName,
        ActivityURL,
        ActivityPic,
        StartDate,
        EndDate,
        VisitCount,
        AttendCount,
        Introduce,
        OfflineDate,
        CityID,
        AdminID,
        ActivityType,
        ModuleID,
        MakeDateStart,
        MakeDateEnd,
        CreateTime,
        UpdateTime,
        RawHTML,
        Ext1,
        `Status`
        )VALUES
        (
        #activityName#,
        #activityURL#,
        #activityPic#,
        #startDate#,
        #endDate#,
        #visitCount#,
        #attendCount#,
        #introduce#,
        #offlineDate#,
        #cityID#,
        #adminID#,
        #activityType#,
        #moduleID#,
        #makeDateStart#,
        #makeDateEnd#,
        NOW(),
        NOW(),
        #rawHTML#,
        #ext1#,
        1
        );
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		UC_ExchangeGiftOrder
		(ItemID,
		CityID,
		UserID,
		GiftName,
		ExchangePrice,
		ExchangeType,
		ExchangeDate,
		STATUS,
		DeliverType,
		PhoneNum,
		Remark,
		UserName,
		   <isNotNull property="giftOrder.provinceName"/>
   <isNotNull property="giftOrder.cityName"/>
   <isNotNull property="giftOrder.address"/>
   <isNotNull property="giftOrder.postZip"/>

		ADDTIME,
		UpdateTime)
		VALUES (#giftOrder.itemId#,
		#giftOrder.cityId#,
		#giftOrder.userID#,
		#giftOrder.giftName#,
		#giftOrder.exChangePrice#,
		#giftOrder.exchangeType#,
		now(),
		#giftOrder.status#,
		#giftOrder.deliverType#,
		#giftOrder.phoneNum#,
		#giftOrder.remark#,
		#giftOrder.userName#,
		   <isNotNull property="giftOrder.provinceName"/>
   <isNotNull property="giftOrder.cityName"/>
   <isNotNull property="giftOrder.address"/>
   <isNotNull property="giftOrder.postZip"/>
		
		now(),
		now())

		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		UC_ExchangeGiftOrder
		(ItemID,
		CityID,
		UserID,
		GiftName,
		ExchangePrice,
		ExchangeType,
		ExchangeDate,
		STATUS,
		DeliverType,
		PhoneNum,
		Remark,
		UserName,
		ADDTIME,
		UpdateTime)
		VALUES (#giftOrder.itemId#,
		#giftOrder.cityId#,
		#giftOrder.userID#,
		#giftOrder.giftName#,
		#giftOrder.exChangePrice#,
		#giftOrder.exchangeType#,
		now(),
		#giftOrder.status#,
		#giftOrder.deliverType#,
		#giftOrder.phoneNum#,
		#giftOrder.remark#,
		#giftOrder.userName#,
		now(),
		now())

		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		UC_ExchangeGiftOrder
		(ItemID,
		UserID,
		GiftName,
		ExchangePrice,
		ExchangeType,
		ExchangeDate,
		STATUS,
		DeliverType,
		PhoneNum,
		Remark,
		UserName,
		ADDTIME,
		UpdateTime)
		VALUES (#giftOrder.itemID#,
		#giftOrder.userID#,
		#giftOrder.giftName#,
		#giftOrder.exChangePrice#,
		#giftOrder.exchangeType#,
		now(),
		0,
		#giftOrder.deliverType#,
		#giftOrder.phoneNum#,
		#giftOrder.remark#,
		#giftOrder.userName#,
		now(),
		now())

		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		UC_ExchangeGiftOrder
		(ItemID,
		UserID,
		BussinessID,
		GiftName,
		ExchangePrice,
		ExchangeType,
		ExchangeDate,
		STATUS,
		DeliverType,
		ADDTIME,
		UpdateTime)
		VALUES
		   <iterate conjunction="," property="orderList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireRecord(GroupID,UserID,UserIP,UserAnswer,TotalScore,AddTime)
		VALUES(#groupId#, #userId#,#userIp#,#userAnswer#,#totalScore#,NOW());
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_SimplePrizeRecord(UserId, UserName, MobileNo, Address, PrizeId, PrizeName, IP, GroupType,GroupId, CityId, EventId, Status)
		VALUES (#userId#, #userName#, #mobileNo#, #address#, #prizeId#, #prizeName#, #IP#, #groupType#, #groupId#, #cityId#, #eventId#, 1);
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EVP_WebModule(EventID,BizData,Status,CustomJS,RawHTML)
		VALUES(#eventId#, #bizData#,#status#,#customJs#,#rawHtml#);
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.EV_Prize
		(
		Rank,
		PrizeName,
		Memo,
		TotalCount,
		ConsumeCount,
		Msg,
		Difficulty,
		GroupID,
		Pic,
		PrizeType,
		`Order`
		) VALUES
		(
		#rank#,
		#prizeName#,
		#memo#,
		#totalCount#,
		0,
		#msg#,
		#difficulty#,
		#groupId#,
		#pic#,
		#prizeType#,
		#order#
		);
		   <selectKey keyProperty="prizeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireGroup(EventID,GroupType,LimitType,Memo,ChanceNum,IPLimit,StartTime,EndTime,State)
		VALUES(#eventId#, #groupType#,#limitType#,#memo#,#chanceNum#,#ipLimit#,#startTime#,#endTime#,#state#);
		   <selectKey keyProperty="groupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireRecord(GroupID,UserID,UserIP,UserAnswer,TotalScore,AddTime)
		VALUES(#groupId#, #userId#,#userIp#,#userAnswer#,#totalScore#,NOW());
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EV_QuestionnaireResultRule(GroupID,BeginScore,EndScore,ContainsBegin,ContainsEnd,Text,AddTime)
		VALUES(#groupId#, #beginScore#,#endScore#,#containsBegin#,#containsEnd#,#text#,NOW());
		   <selectKey keyProperty="resultId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    INSERT INTO EV_Notice(NoticeType,SenderID,ReceiverID,RecordID,SendContext,Data1,Remark,IP,EventID)
        VALUES(#noticeType#,#senderId#,#receiverId#,#recordId#,#sendContext#,#data1#,#remark#,#ip#,#eventId#)
              <selectKey keyProperty="noticeId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DPEvent.ACT_MaterialStandard
		(
		  `Explain`,
		  `Limit`,
		  MatType,
		  ModuleID,
          `Name`,
          RelateName,
          `Status`,
          CreateTime,
          UpdateTime
         )VALUES
		(
		  #explain#,
		  #limit#,
		  #matType#,
		  #moduleID#,
		  #name#,
		  #relateName#,
		  #status#,
          NOW(),
          NOW()
         );
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TO_FlashSaleValidCode
        SET MobileNO  = #mobileNo#,
            ValidCode = #validCode#,
            DealGroupID = #dealGroupId#,
            Status = 0,
            AddTime = NOW()
        
           <selectKey keyProperty="ValidCodeID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT TO_FlashSaleDealGroup
            SET DealGroupID = #dealGroup.dealGroupId#,
            DealID          = #dealGroup.dealId#,
            BeginDate       = #dealGroup.beginDate#,
            EndDate         = #dealGroup.endDate#,
            WannaJoin       = 0,
            Status          = #dealGroup.status#,
            AddTime         = NOW()
        
           <selectKey keyProperty="FlashSaleDealGroupID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
	        INSERT INTO TO_FlashSaleDealGroupCity(CityID, DealGroupID, Status, AddTime) VALUES
	    
           <iterate conjunction="," property="cityIds"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TO_FlashSaleWannaJoin
        SET DealGroupID = #dealGroupId#,
        Identification = #identification#,
        AddTime = NOW()
        
           <selectKey keyProperty="WannaJoinID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT TO_FlashSaleWannaJoinUser
        SET UserID 		= #userId#,
            DealGroupID   = #dealGroupId#,
            ReceiverID 	= #receiverId#,
            Type          = #type#,
            Status 		= 0,
            BeginDate   = #beginDate#,
            AddTime   	= NOW()
        
           <selectKey keyProperty="WannaJoinUserID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TO_FlashSaleSms
        SET UserID  = #userId#,
            OrderID = #orderId#,
            MobileNO = #mobileNO#,
            AddTime = NOW()
        
           <selectKey keyProperty="SmsID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_GroupNote
        (GroupID,
        UserID,
        NoteTitle,
        NoteCategory,
        Hits,
        AddTime,
        UpdateTime,
        LastIP,
        LastUser,
        LastNoteTime,
        FollowNoteNo,
        SurveyTotal,
        GroupNoteTypeID,
        HasPic,
        DCashNumber,
        IsHighLight,
        IsGood,
        IsLocked,
        IsTop,
        VerifyStatus,
        Status)
        VALUES
        (#note.groupId#,
        #note.userId#,
        #note.noteTitle#,
        #note.noteCategory#,
        #note.hits#,
        NOW(),
        NOW(),
        #note.lastIP#,
        #note.lastUser#,
        NOW(),
        #note.followNoteNo#,
        #note.surveyTotal#,
        #note.groupNoteTypeId#,
        #note.hasPic#,
        #note.dCashNumber#,
        #note.isHighLight#,
        #note.isGood#,
        #note.isLocked#,
        #note.isTop#,
        #note.verifyStatus#,
        1)
           <selectKey keyProperty="noteId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_GroupNoteScoreLog 
			(UserID, NoteType, NoteID, Score, Comment, AddDate)
		VALUES
			(#log.userId#, #log.noteType#, #log.noteId#, #log.score#, #log.comment#, NOW())
		   <selectKey keyProperty="logId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			DP_RandomDCashActivity
		VALUES
			(NULL,
			#randomDCashActivity.beginDate#,
			#randomDCashActivity.endDate#,
			#randomDCashActivity.dCashNumber#,
			#randomDCashActivity.probabilityNumerator#,
			#randomDCashActivity.probabilityDenominator#,
			#randomDCashActivity.status#,
			#randomDCashActivity.addDate#,
			#randomDCashActivity.updateDate#)
			   <selectKey keyProperty="activityId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
	        INSERT INTO TO_GiftCardOrderNumber(UserID,PayOrderID,GiftCardOrderID, ReceiverContactID, Type, GiftCardNumber,Amount,Password,Status,AddTime) VALUES
	    
		   <iterate conjunction="," property="numberList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TEST
		(NAME, GMT_CREATE)
		VALUES
		(#name:VARCHAR#, now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GP_EventFollowNote
			(EventID, UserID, NoteBody, ADDDATE, UpdateDate, IP, OrigUserID, EventTitle, STATUS)
		VALUES
			(#eventId#, #userId#, #noteBody#, NOW(), NOW(), #ip#, #origUserId#, #eventTitle#, #status#)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_Group 
    		(GroupName,
    		GroupPermaLink,
    		GroupIntro,
    		GroupLogo,
    		CityID,
    		GroupProperty,
    		SearchKeyWord,
    		Status,
    		CreateDate,
    		CreateUser,
    		LastIP,
    		UserTotal,
    		MainNoteTotal,
    		NoteTotal,
    		WeeklyNoteCount,
    		HitsTotal) 
		VALUES 
			(#group.groupName#,
			#group.groupPermaLink#,
			#group.groupIntro#,
			#group.groupLogo#,
			#group.cityId#, 
			#group.groupProperty#,
			#group.searchKeyWord#,
			#group.status#,
			NOW(),
			#group.createUser#,
			#group.lastIP#,
			#group.userTotal#,
			#group.mainNoteTotal#,
			#group.noteTotal#,
			#group.weeklyNoteCount#,
			#group.hitsTotal#)
			   <selectKey keyProperty="groupId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_GroupMedal
    		(MedalName,
    		MedalPicPath,
    		MedalLink,
    		BeginDate,
    		EndDate,
    		AddTime) 
		VALUES 
			(#medal.name#,
			#medal.picPath#,
			#medal.link#,
			#medal.beginDate#,
			#medal.endDate#,
			NOW())
			   <selectKey keyProperty="medalId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_GroupNoteType
    		(GroupNoteTypeName, 
    		GroupID, 
    		OrderNumber)
		VALUES 
			(#noteType.name#,
			#noteType.groupId#,
			#noteType.order#)
		   <selectKey keyProperty="typeId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GP_HotNoteB
		(	CityID,
			NoteID,
			NoteTitle,
			VersionID,
			OrderID
		)
		VALUES
		   <iterate conjunction="," property="noteList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GP_GoldDetail
		VALUES
			(NULL,
			#userGoldDetail.goldNum#,
			#userGoldDetail.goldType#,
			#userGoldDetail.noteId#,
			#userGoldDetail.followNoteId#,
			#userGoldDetail.userId#,
			#userGoldDetail.groupSetId#,
			#userGoldDetail.groupId#,
			#userGoldDetail.orderId#,
			#userGoldDetail.detail#,
			now()
			)
			   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			DP_GroupHonorTitle 
		VALUES 
			(#groupHonorTitle.titleId#, 
			#groupHonorTitle.titleName#,
			#groupHonorTitle.titlePicPath#,
			#groupHonorTitle.titleLink#,
			#groupHonorTitle.beginDate#,
			#groupHonorTitle.endDate#,
			#groupHonorTitle.addTime#)
			   <selectKey keyProperty="titleId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GP_RecommendGroup 
		(	GroupID, 
			CityID, 
			RecommendReason
		)
		VALUES
		   <iterate conjunction="," property="groupList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TO_GiftCardOrder 
		SET PayOrderID	= #giftCardOrder.payOrderId#, 
			UserID 		= #giftCardOrder.userId#, 
			GiftCardID	= #giftCardOrder.giftCardId#, 
			Quantity	= #giftCardOrder.quantity#, 
			Amount		= #giftCardOrder.amount#, 
			paymentAmount	= #giftCardOrder.paymentAmount#, 
			UserName	= #giftCardOrder.userName#,
			DeliveryWay	= #giftCardOrder.deliveryWay#, 
			ReceiverMobileNo= #giftCardOrder.receiverMobileNo#, 
			ReceiverEmail	= #giftCardOrder.receiverEmail#, 
			Greeting		= #giftCardOrder.greeting#, 
			Status		= 0,
			AddTime		= NOW(),
			Type        = #giftCardOrder.type#
		
		   <selectKey keyProperty="GiftCardOrderID" resultClass="Long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TO_GiftCardReceiveLog
		SET UserID = #giftCardReceiveLog.userId#,
			GiftCardOrderNumberID = #giftCardReceiveLog.giftCardOrderNumberId#,
			GiftCardNumber = #giftCardReceiveLog.giftCardNumber#,
			Amount = #giftCardReceiveLog.amount#,
			GiftCardOrderID = #giftCardReceiveLog.giftCardOrderId#,
			UserIP = #giftCardReceiveLog.userIp#,
			AddTime = NOW()
	
		   <selectKey keyProperty="GiftCardReceiveLogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_GroupLink
    		(GroupID,
    		LinkURL,
    		LinkName,
    		AddUserID,
    		AddTime) 
    	VALUES 
		   <iterate conjunction="," property="groupLinkList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GP_GroupPic
		VALUES
		(NULL,
		#groupPic.picType#,
		#groupPic.bizId#,
		#groupPic.referId#,
		#groupPic.userId#,
		#groupPic.userIp#,
		#groupPic.picUrl#,
		#groupPic.status#,
		NOW(),
		NOW())
		   <selectKey keyProperty="picId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_GroupTypeApply 
    		(GroupID,
    		GroupTypeID,
    		ApplyMsg,
    		AddDate,
    		ApplyUserID,
    		GroupTypeFlag) 
		VALUES 
			(#apply.groupId#,
			#apply.groupTypeId#,
			#apply.applyMsg#,
			NOW(),
			#apply.applyUserId#,
			#apply.groupTypeFlag#)
    	   <selectKey keyProperty="applyId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_GroupNoteExtInfo 
		(	NoteID, 
			NoteScore, 
			Recommend	
		) 
		VALUES 
		   <iterate conjunction="," property="ids"/>

		ON DUPLICATE KEY UPDATE Recommend = #recType#
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_GroupNoteScoreLog 
			(UserID, NoteType, NoteID, Score, Comment, AddDate)
		VALUES
			(#log.userId#, #log.noteType#, #log.noteId#, #log.score#, #log.comment#, NOW())
		   <selectKey keyProperty="logId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GP_TopNote 
		(	CityID, 
			CategoryID, 
			NoteID
		)
		VALUES
		   <iterate conjunction="," property="noteList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO etl_load_cfg
        (
        task_id,connectprops,parameter_map,type,condition_column
        )
        VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO etl_load_cfg
        (
        task_id,connectprops,parameter_map,type
        )
        VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		MP_Geocoding(City, DataName, Number, type, GLng, GLat,
		DetailAddress, AddTime, UpdateTime, CityID)
		VALUES
		(#cityName#,
		#dataName#, #houseNum#, #type#, #lng#, #lat#, #detailAddress#, NOW(),
		NOW(), 1)
		   <selectKey keyProperty="PoiId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO GP_RecommendGroup
    		(CityID,
    		GroupID,
    		RecommendReason) 
		VALUES 
		   <iterate conjunction="," property="recommendList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_GroupUserScoreDailyLog
		(UserID, DayID, ConfigID, Score, ADDDATE, Remark)
		VALUES
		(#userId#, #dayId#, #configId#, #score#, NOW(), #remark#)
		   <selectKey keyProperty="logId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into DP_Event
		(UserID,
		EventTitle,
		BeginDate,
		EndDate,
		ShopID,
		Address,
		Cost,
		EventTypeID,
		EventBody,
		GroupID,
		PhoneNoFlag,
		SignUpFlag,
		EventPower,
		CityID,
		UserCount,
		ParticipantLimit,
		AddDate,
		CancelDate,
		UpdateDate,
		ExtFlag,
		IsShowInGroup,
		ExtendOption1,
		ExtendOption2,
		ExtendOption3,
		AuditingDate,
		RecoverDate)
		values
		(#event.userId#,
		#event.eventTitle#,
		#event.beginDate#,
		#event.endDate#,
		#event.shopId#,
		#event.address#,
		#event.cost#,
		#event.eventTypeId#,
		#event.eventBody#,
		#event.groupId#,
		#event.phoneNoFlag#,
		#event.signUpFlag#,
		#event.eventPower#,
		#event.cityId#,
		#event.userCount#,
		#event.participantLimit#,
		#event.addDate#,
		#event.cancelDate#,
		#event.updateDate#,
		#event.extFlag#,
		#event.showInGroup#,
		#event.extendOption1#,
		#event.extendOption2#,
		#event.extendOption3#,
		#event.auditingDate#,
		#event.recoverDate#)
		   <selectKey keyProperty="eventId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			DP_GroupNoteLike 
		VALUES(
			NULL
			   <isNotEqual compareValue="0" prepend="," property="userId"/>
   <isNotEqual compareValue="0" prepend="," property="groupId"/>
   <isNotEqual compareValue="0" prepend="," property="noteId"/>

			,NOW()
			,NOW()
			,1
		)
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_GroupOperateLog
    		(OperateType, UserID, UserIP, SystemID, GroupID, NoteType, NoteID, ReferField, LogInfo, Comment, AddDate) 
    	VALUES 
    	   <iterate conjunction="," property="logList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GP_HotNote
		(	CityID,
			NoteID,
			NoteTitle,
			VersionID,
			OrderID
		)
		VALUES
		   <iterate conjunction="," property="noteList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO GP_RecommendNote
		(	VersionID,
			CityID,
			RecType,
			Url,
			NoteID,
			NoteTitle,
			GroupPermaLink,
			PicUrl,
			Summary,
			TagType,
			TimeDesc,
			ADDDATE)
		VALUES
		   <iterate conjunction="," property="noteList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into
		MP_Geocoding(City,DataName,Number,type,GLng,GLat,DetailAddress,AddTime,UpdateTime,CityID)
		values
		(#cityName#,#dataName#,#houseNum#,#type#,#lng#,#lat#,#detailAddress#,NOW(),NOW(),1)
		   <selectKey keyProperty="PoiId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="pre" keyProperty="table_id" resultClass="java.lang.Integer"/>

		INSERT INTO
		mc_table_info
		(table_id,schema_name,table_name,storage_type,db_name,table_owner,table_desc,table_size,refresh_cycle,refresh_type,refresh_datum_date,refresh_offset,add_time,update_time,is_validate)
		VALUES
		(#table_id#,
		#schema_name#,#table_name#,#storage_type#,#db_name#,#table_owner#,
		#table_desc#,#table_size#,#refresh_cycle#,
		#refresh_type#,#refresh_datum_date#,#refresh_offset#,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,#status#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO mc_column_info
		(column_id,table_id,column_name,column_type,column_desc,column_rn,add_time,update_time,is_partition_column)
		VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_config (id,key1,key2,key3,key4,exception,duration,max,alarm_time,messageMax,messageAlarmTime,messageEnable) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#exception#,#duration#,#max#,#alarmTime#,#messageMax#,#messageAlarmTime#,#messageEnable#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_dynamic_key (id,key_name,key_value,datasource_id,value) values
    	(#id#,#keyName#,#keyValue#,#dsID#,#sumValue#)
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo 
		(
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
		)
		VALUES
		(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#,
			now()
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo 
		(
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
		)
		VALUES
		
          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value) values
    	(#id#,#keyName#,#keyValue#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    
    	insert into loginfo (id,key1,key2,key3,key4,host_name,execution_num,value_min,value_avg,value_max,value_sum,value_last,time_current) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#host_name#,#execution_num#,#value_min#,#value_avg#,#value_max#,#value_sum#,#value_last#,#time_current#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource (id,name,key1,key2,key3,key4,data_type,host_names,step,rrd_path,timespan,is_active,avg_accum_type,projectId,data_choice) values
    	(#id#,#name#,#key1#,#key2#,#key3#,#key4#,#dataType#,#hostNames#,#step#,#rrdPath#,#timespan#,#isActive#,#avgAccumType#,#projectId#,#dataChoice#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_rra (id,name,cf,xff,steps,rows,timespan) values
    	(#id#,#name#,#cf#,#xff#,#steps#,#rows#,#timespan#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef_item (id,cdef_id,type,value) values
    	(#id#,#cdefId#,#type#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource_item (id,datasource_type,heart_beat,max_value,min_value,datasource_id,internal_name) values
    	(#id#,#datasourceType#,#heartBeat#,#maxValue#,#minValue#,#datasourceId#,#internalName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO consumer_rule (
			serviceNameId,
			logType,
			key1,
			key2,
			key3,
			key4,
			projectId,
			hostIp,
			consumeInterval,
			createTime,
			modifyTime
			)
		VALUES(
			#serviceNameId#,
			#logType#,
			#key1#,
			#key2#,
			#key3#,
			#key4#,
			#projectId#,
			#hostIp#,
			#consumeInterval#,
			#createTime#,
			#modifyTime#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO consumer_service (
			serviceName,
			createTime
			)
		VALUES(
			#serviceName#,
			#createTime#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_config (id,key1,key2,key3,key4,exception,duration,max,alarm_time,messageMax,messageAlarmTime,messageEnable) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#exception#,#duration#,#max#,#alarmTime#,#messageMax#,#messageAlarmTime#,#messageEnable#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef (id,name) values
    	(#id#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef (id,name) values
    	(#id#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource_item (id,datasource_type,heart_beat,max_value,min_value,datasource_id,internal_name) values
    	(#id#,#datasourceType#,#heartBeat#,#maxValue#,#minValue#,#datasourceId#,#internalName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph (id,text,name,title,image_format_id,height,width,upper_limit,lower_limit,vertical_label,grid_step,label_factor,projectId) values
    	(#id#,#text#,#name#,#title#,#imageFormateId#,#height#,#width#,#upperLimit#,#lowerLimit#,#verticalLabel#,#gridStep#,#labelFactor#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO HUI_CouponInventory
		(MaxInventory,SoldQuantity,CouponOfferId,CreateTime,LastUpdateTime,Version)
		VALUES
		(#entity.maxInventory#,#entity.soldQuantity#,#entity.couponOfferId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.version#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO HUI_RedeemRule
		(MetricItemId,CouponOfferId,RedeemStrategyExpression,RedeemConditionExpression,RedeemStrategyId,CreateTime,LastUpdateTime)
		VALUES
		(#entity.metricItemId#,#entity.couponOffer.id#,#entity.redeemStrategyExpression#,#entity.redeemConditionExpression#,#entity.redeemStrategyId#,#entity.createTime#,#entity.lastUpdateTime#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_HotelInvoice
        (
        orderPrice,shopName,roomName,checkinDate,checkoutDate,roomNum,recipient,name,contactPhone,address,postCode,addTime,updateTime
        )
        values (
        #hotelInvoice.orderPrice#,
        #hotelInvoice.shopName#,
        #hotelInvoice.roomName#,
        #hotelInvoice.checkinDate#,
        #hotelInvoice.checkoutDate#,
        #hotelInvoice.roomNum#,
        #hotelInvoice.recipient#,
        #hotelInvoice.name#,
        #hotelInvoice.contactPhone#,
        #hotelInvoice.address#,
        #hotelInvoice.postCode#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph (id,text,name,title,image_format_id,height,width,upper_limit,lower_limit,vertical_label,grid_step,label_factor,projectId) values
    	(#id#,#text#,#name#,#title#,#imageFormateId#,#height#,#width#,#upperLimit#,#lowerLimit#,#verticalLabel#,#gridStep#,#labelFactor#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef_item (id,cdef_id,type,value) values
    	(#id#,#cdefId#,#type#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
			)
		VALUES(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#,
			now()
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource_item (id,datasource_type,heart_beat,max_value,min_value,datasource_id,internal_name) values
    	(#id#,#datasourceType#,#heartBeat#,#maxValue#,#minValue#,#datasourceId#,#internalName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    
    	insert into loginfo (id,key1,key2,key3,key4,host_name,execution_num,value_min,value_avg,value_max,value_sum,value_last,time_current) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#host_name#,#execution_num#,#value_min#,#value_avg#,#value_max#,#value_sum#,#value_last#,#time_current#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    
    	insert into loginfo (id,key1,key2,key3,key4,host_name,execution_num,value_min,value_avg,value_max,value_sum,value_last,time_current) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#host_name#,#execution_num#,#value_min#,#value_avg#,#value_max#,#value_sum#,#value_last#,#time_current#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value,des,keyType,projectId) values
    	(#id#,#keyName#,#keyValue#,#des#,#keyType#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO loginfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			value_max,
			value_min,
			value_avg,
			value_last,
			value_sum,
			time_current,
			created
		)
		VALUES
		
          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_color (id,hex) values
    	(#id#,#hex#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_config (id,key1,key2,key3,key4,exception,duration,max,alarm_time) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#exception#,#duration#,#max#,#alarmTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_dynamic_key (id,key_name,key_value,datasource_id,value) values
    	(#id#,#keyName#,#keyValue#,#dsID#,#sumValue#)
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value,des,keyType,projectId) values
    	(#id#,#keyName#,#keyValue#,#des#,#keyType#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_config (id,key1,key2,key3,key4,exception,duration,max,alarm_time) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#exception#,#duration#,#max#,#alarmTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef (id,name) values
    	(#id#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_color (id,hex) values
    	(#id#,#hex#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph_item (id,graph_id,datasource_id,color_id,alpha,graph_item_type,cdef_id,consolidation_id,text_format,sequence,value) values
    	(#id#,#graphId#,#datasourceId#,#colorId#,#alpha#,#graphItemType#,#cdefId#,#consolidationId#,#textFormat#,#sequence#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource (id,name,key1,key2,key3,key4,data_type,host_names,step,rrd_path,timespan,is_active,avg_accum_type,projectId,data_choice) values
    	(#id#,#name#,#key1#,#key2#,#key3#,#key4#,#dataType#,#hostNames#,#step#,#rrdPath#,#timespan#,#isActive#,#avgAccumType#,#projectId#,#dataChoice#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO call_log (
			service_name,
			method_name,
			provider_name,
			consumer_name,
			provider_ip,
			consumer_ip,
			code,
			count,
			value_max,
			value_min,
			value_avg,
			value_last,
			value_sum,
			time_current,
			created
		)
		VALUES
			
		          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO consumer_rule (
			serviceNameId,
			logType,
			key1,
			key2,
			key3,
			key4,
			projectId,
			hostIp,
			consumeInterval,
			createTime,
			modifyTime
			)
		VALUES(
			#serviceNameId#,
			#logType#,
			#key1#,
			#key2#,
			#key3#,
			#key4#,
			#projectId#,
			#hostIp#,
			#consumeInterval#,
			#createTime#,
			#modifyTime#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo 
		(
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
		)
		VALUES
		(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#,
			now()
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo 
		(
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
		)
		VALUES
		
          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef_item (id,cdef_id,type,value) values
    	(#id#,#cdefId#,#type#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO consumer_rule (
			serviceNameId,
			logType,
			key1,
			key2,
			key3,
			key4,
			projectId,
			hostIp,
			consumeInterval,
			createTime,
			modifyTime
			)
		VALUES(
			#serviceNameId#,
			#logType#,
			#key1#,
			#key2#,
			#key3#,
			#key4#,
			#projectId#,
			#hostIp#,
			#consumeInterval#,
			#createTime#,
			#modifyTime#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_config (id,key1,key2,key3,key4,exception,duration,max,alarm_time) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#exception#,#duration#,#max#,#alarmTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph (id,text,name,title,image_format_id,height,width,upper_limit,lower_limit,vertical_label,grid_step,label_factor,projectId) values
    	(#id#,#text#,#name#,#title#,#imageFormateId#,#height#,#width#,#upperLimit#,#lowerLimit#,#verticalLabel#,#gridStep#,#labelFactor#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value,des,keyType,projectId) values
    	(#id#,#keyName#,#keyValue#,#des#,#keyType#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph (id,text,name,title,image_format_id,height,width,upper_limit,lower_limit,vertical_label,grid_step,label_factor,projectId) values
    	(#id#,#text#,#name#,#title#,#imageFormateId#,#height#,#width#,#upperLimit#,#lowerLimit#,#verticalLabel#,#gridStep#,#labelFactor#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph_item (id,graph_id,datasource_id,color_id,alpha,graph_item_type,cdef_id,consolidation_id,text_format,sequence,value) values
    	(#id#,#graphId#,#datasourceId#,#colorId#,#alpha#,#graphItemType#,#cdefId#,#consolidationId#,#textFormat#,#sequence#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO loginfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			value_max,
			value_min,
			value_avg,
			value_last,
			value_sum,
			time_current,
			created
		)
		VALUES
		
          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_config (id,key1,key2,key3,key4,exception,duration,max,alarm_time,messageMax,messageAlarmTime,messageEnable) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#exception#,#duration#,#max#,#alarmTime#,#messageMax#,#messageAlarmTime#,#messageEnable#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_color (id,hex) values
    	(#id#,#hex#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_dynamic_key (id,key_name,key_value,datasource_id,value) values
    	(#id#,#keyName#,#keyValue#,#dsID#,#sumValue#)
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph_item (id,graph_id,datasource_id,color_id,alpha,graph_item_type,cdef_id,consolidation_id,text_format,sequence,value) values
    	(#id#,#graphId#,#datasourceId#,#colorId#,#alpha#,#graphItemType#,#cdefId#,#consolidationId#,#textFormat#,#sequence#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO call_log (
			service_name,
			method_name,
			provider_name,
			consumer_name,
			provider_ip,
			consumer_ip,
			code,
			count,
			value_max,
			value_min,
			value_avg,
			value_last,
			value_sum,
			time_current,
			created
		)
		VALUES
			
		          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO HUI_CouponOfferApplicableShop
		(CouponOfferId,ShopId,CreateTime,LastUpdateTime)
		VALUES
		(#entity.couponOffer.id#,#entity.shopId#,#entity.createTime#,#entity.lastUpdateTime#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_rra (id,name,cf,xff,steps,rows,timespan) values
    	(#id#,#name#,#cf#,#xff#,#steps#,#rows#,#timespan#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value) values
    	(#id#,#keyName#,#keyValue#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
			)
		VALUES(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#,
			now()
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource_item (id,datasource_type,heart_beat,max_value,min_value,datasource_id,internal_name) values
    	(#id#,#datasourceType#,#heartBeat#,#maxValue#,#minValue#,#datasourceId#,#internalName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
			)
		VALUES(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#,
			now()
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource (id,name,key1,key2,key3,key4,data_type,host_names,step,rrd_path,timespan,is_active,avg_accum_type,projectId,data_choice) values
    	(#id#,#name#,#key1#,#key2#,#key3#,#key4#,#dataType#,#hostNames#,#step#,#rrdPath#,#timespan#,#isActive#,#avgAccumType#,#projectId#,#dataChoice#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_rra (id,name,cf,xff,steps,rows,timespan) values
    	(#id#,#name#,#cf#,#xff#,#steps#,#rows#,#timespan#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo 
		(
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
		)
		VALUES
		(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#,
			now()
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo 
		(
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime,
			created
		)
		VALUES
		
          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource_item (id,datasource_type,heart_beat,max_value,min_value,datasource_id,internal_name) values
    	(#id#,#datasourceType#,#heartBeat#,#maxValue#,#minValue#,#datasourceId#,#internalName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_rra (id,name,cf,xff,steps,rows,timespan) values
    	(#id#,#name#,#cf#,#xff#,#steps#,#rows#,#timespan#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO HUI_CouponOffer
		(Title,Description,EffectiveDateExpression,CreateTime,LastUpdateTime,Version)
		VALUES
		(#entity.title#,#entity.description#,#entity.effectiveDateExpression#,#entity.createTime#,#entity.lastUpdateTime#,#entity.version#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_HotelOrder
        (
        HotelID, ShopID, UserId, UID, Status, CheckinDate, CheckoutDate, OrderDate, UpdateDate, OrderPrice,
        CurrencyCode, Platform, RoomCount, ContactName, ContactPhone, OTAOrderID, OTAID,AddTime, UpdateTime,
        OriginStatus, ViewDate, Fee, Note, IsPrepay, PayStatus, PayOrderId , PayTime, CostPrice, CheckinNames,
        RoomID, StrategyID, RoomName, ConfirmDeadline, NeedInvoice, InvoiceId
        )
        values (
        #hotelOrder.hotelId#,
        #hotelOrder.shopId#,
        #hotelOrder.userId#,
        #hotelOrder.uId#,
        #hotelOrder.status#,
        #hotelOrder.checkinDate#,
        #hotelOrder.checkoutDate#,
        #hotelOrder.orderDate#,
        #hotelOrder.updateDate#,
        #hotelOrder.orderPrice#,
        #hotelOrder.currencyCode#,
        #hotelOrder.platform#,
        #hotelOrder.roomCount#,
        #hotelOrder.contactName#,
        #hotelOrder.contactPhone#,
        #hotelOrder.originOrderId#,
        #hotelOrder.otaId#,
        NOW(),
        NOW(),
        #hotelOrder.originStatus#,
        #hotelOrder.viewDate#,
        #hotelOrder.fee#,
        #hotelOrder.note#,
        #hotelOrder.isPrepay#,
        #hotelOrder.payStatus#,
        #hotelOrder.payOrderId#,
        #hotelOrder.payTime#,
        #hotelOrder.costPrice#,
        #hotelOrder.checkinNames#,
        #hotelOrder.roomID#,
        #hotelOrder.strategyID#,
        #hotelOrder.roomName#,
        #hotelOrder.confirmDeadline#,
        #hotelOrder.needInvoice#,
        #hotelOrder.invoiceId#
        )
        ON DUPLICATE KEY UPDATE
        HotelId = #hotelOrder.hotelId#,
        ShopId = #hotelOrder.shopId#,
        UserId = #hotelOrder.userId#,
        UId = #hotelOrder.uId#,
        Status = #hotelOrder.status#,
        CheckinDate = #hotelOrder.checkinDate#,
        CheckoutDate = #hotelOrder.checkoutDate#,
        OrderDate = #hotelOrder.orderDate#,
        UpdateDate = #hotelOrder.updateDate#,
        OrderPrice = #hotelOrder.orderPrice#,
        CurrencyCode = #hotelOrder.currencyCode#,
        Platform = #hotelOrder.platform#,
        RoomCount = #hotelOrder.roomCount#,
        ContactName = #hotelOrder.contactName#,
        ContactPhone = #hotelOrder.contactPhone#,
        OTAOrderID = #hotelOrder.originOrderId#,
        OtaId = #hotelOrder.otaId#,
        UpdateTime = NOW(),
        OriginStatus = #hotelOrder.originStatus#,
        ViewDate = #hotelOrder.viewDate#,
        Fee = #hotelOrder.fee#,
        Note = #hotelOrder.note#,
        IsPrepay = #hotelOrder.isPrepay#,
        PayStatus = #hotelOrder.payStatus#,
        PayOrderId = #hotelOrder.payOrderId#,
        PayTime = #hotelOrder.payTime#,
        CostPrice = #hotelOrder.costPrice#,
        CheckinNames = #hotelOrder.checkinNames#,
        RoomID = #hotelOrder.roomID#,
        StrategyID = #hotelOrder.strategyID#,
        RoomName = #hotelOrder.roomName#,
        ConfirmDeadline = #hotelOrder.confirmDeadline#,
        NeedInvoice = #hotelOrder.needInvoice#,
        InvoiceId = #hotelOrder.invoiceId#

           <selectKey keyProperty="OrderID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO call_log (
			service_name,
			method_name,
			provider_name,
			consumer_name,
			provider_ip,
			consumer_ip,
			code,
			count,
			value_max,
			value_min,
			value_avg,
			value_last,
			value_sum,
			time_current,
			created
		)
		VALUES
			
		          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource (id,name,key1,key2,key3,key4,data_type,host_names,step,rrd_path,timespan,is_active,avg_accum_type,projectId,data_choice) values
    	(#id#,#name#,#key1#,#key2#,#key3#,#key4#,#dataType#,#hostNames#,#step#,#rrdPath#,#timespan#,#isActive#,#avgAccumType#,#projectId#,#dataChoice#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph_item (id,graph_id,datasource_id,color_id,alpha,graph_item_type,cdef_id,consolidation_id,text_format,sequence,value) values
    	(#id#,#graphId#,#datasourceId#,#colorId#,#alpha#,#graphItemType#,#cdefId#,#consolidationId#,#textFormat#,#sequence#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value) values
    	(#id#,#keyName#,#keyValue#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO consumer_service (
			serviceName,
			createTime
			)
		VALUES(
			#serviceName#,
			#createTime#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef (id,name) values
    	(#id#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef (id,name) values
    	(#id#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef_item (id,cdef_id,type,value) values
    	(#id#,#cdefId#,#type#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_color (id,hex) values
    	(#id#,#hex#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph (id,text,name,title,image_format_id,height,width,upper_limit,lower_limit,vertical_label,grid_step,label_factor,projectId) values
    	(#id#,#text#,#name#,#title#,#imageFormateId#,#height#,#width#,#upperLimit#,#lowerLimit#,#verticalLabel#,#gridStep#,#labelFactor#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph_item (id,graph_id,datasource_id,color_id,alpha,graph_item_type,cdef_id,consolidation_id,text_format,sequence,value) values
    	(#id#,#graphId#,#datasourceId#,#colorId#,#alpha#,#graphItemType#,#cdefId#,#consolidationId#,#textFormat#,#sequence#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_dynamic_key (id,key_name,key_value,datasource_id,value) values
    	(#id#,#keyName#,#keyValue#,#dsID#,#sumValue#)
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef_item (id,cdef_id,type,value) values
    	(#id#,#cdefId#,#type#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_color (id,hex) values
    	(#id#,#hex#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_dynamic_key (id,key_name,key_value,datasource_id,value) values
    	(#id#,#keyName#,#keyValue#,#dsID#,#sumValue#)
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value,des,keyType,projectId) values
    	(#id#,#keyName#,#keyValue#,#des#,#keyType#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_rra (id,name,cf,xff,steps,rows,timespan) values
    	(#id#,#name#,#cf#,#xff#,#steps#,#rows#,#timespan#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO consumer_service (
			serviceName,
			createTime
			)
		VALUES(
			#serviceName#,
			#createTime#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource (id,name,key1,key2,key3,key4,data_type,host_names,step,rrd_path,timespan,is_active,avg_accum_type,projectId,data_choice) values
    	(#id#,#name#,#key1#,#key2#,#key3#,#key4#,#dataType#,#hostNames#,#step#,#rrdPath#,#timespan#,#isActive#,#avgAccumType#,#projectId#,#dataChoice#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value,des,keyType,projectId) values
    	(#id#,#keyName#,#keyValue#,#des#,#keyType#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO loginfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			value_max,
			value_min,
			value_avg,
			value_last,
			value_sum,
			time_current,
			created
		)
		VALUES
		
          <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardStopLog 
			(MemberCardID,
			 StopPeriod,
			 BeginDate,
			 EndDate,
			 IsActive,
			 AddTime) 
		VALUES 
			(#memberCardId#, 
			 #stopPeriod#,
			 #beginDate#,
			 #endDate#,
			 #isActive#,
			 #addTime#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardScoreLog(MemberCardID, LogType, Score, Comment, ReferLogID, Status, IsActive, AddTime, AdminID, AdminName)
			VALUES(#memberCardId#, #logType#, #score#, #comment#, #referLogId#, #status#, #isActive#, #addTime#, #adminId#, #adminName#)
			
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
				
		INSERT INTO MC_MemberCardUserFeed(UserID,FeedID,STATUS,IsLike,IsUse,ADDTIME,UpdateTime) 
			SELECT	DISTINCT u.UserID, #feedId#, 1, 0, 0, NOW(), NOW() FROM MC_MemberCardUser u WHERE u.Status=2 AND u.MemberCardID IN
			   <iterate open="(" conjunction="," property="memberCardIdList" close=")"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardConsumeAnalysis 
			(ShopID,
			 ConsumeAmount,
			 ConsumeCount,
			 ConsumeDate,
			 AddTime,
			 UpdateTime) 
		VALUES 
			(#shopID#,
			 #consumeAmount#,
			 #consumeCount#, 
			 #consumeDate#, 
			 #addTime#, 
			 #updateTime#)
		   <selectKey keyProperty="ReportID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT IGNORE INTO TG_PrepaidCardRemindMsg(ReceiptAccountID, CardProductID, UserID, RemindType, ExpiredDate,
		RemindMsg, RemindStatus, RemindDate, AddTime)
		VALUES
		
		   <iterate conjunction="," property="messageList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
  
	     
	        INSERT INTO MC_MemberCardNumber(CardNO,Type,AddTime) VALUES 
	      
	       <iterate conjunction="," property="cardNOList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO MC_MemberCardRecommend
	(MembercardID, CityID,RecommendCards,AddTime, UpdateTime)
	VALUES
	(#membercardId#,#cityId#,#recommendCards#,#addTime#,#updateTime#)
	ON DUPLICATE KEY UPDATE
	RecommendCards = VALUES(RecommendCards),
	UpdateTime = VALUES(UpdateTime)
	   <selectKey keyProperty="MembercardID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO MC_MemberCardRecommend
	(MembercardID, CityID,RecommendCards,AddTime, UpdateTime)
	VALUES
	   <iterate conjunction="," property="reportList"/>

	ON DUPLICATE KEY UPDATE
	RecommendCards = VALUES(RecommendCards),
	UpdateTime = VALUES(UpdateTime)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into
			DP_Word(Word,WordType,SensitiveType,AddTime,BeginDate,EndDate,AddAdminID,LastTime,LastAdminID,Memo)
		values
			(#word#,#wordType#,#sensitiveType#,now(),#beginDate#,#endDate#,#addAdminID#,now(),#lastAdminID#,#memo#)
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO resource(name, code, url)
        VALUES(#name#, #code#, #url#)
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT IGNORE INTO role_privilege(roleId, privilegeId)
    	VALUES
    	   <iterate conjunction="," property="privilegeIds"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO role(name, createUserId, createTime, remark, internal) VALUES(#name#, #createUserId#, NOW(), #remark#, #internal#)
    	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_OrderBaseInfo
        (
          orderId,
          contractNo,
          customerName,
          salesId,
          salesName,
          salesCityId,
          launchAttribute,
          status,
          addTime,
          updateTime
        )
        VALUES
        (
          #orderBaseInfoEntity.orderId#,
          #orderBaseInfoEntity.contractNo#,
          #orderBaseInfoEntity.customerName#,
          #orderBaseInfoEntity.salesId#,
          #orderBaseInfoEntity.salesName#,
          #orderBaseInfoEntity.salesCityId#,
          #orderBaseInfoEntity.launchAttribute#,
          #orderBaseInfoEntity.status#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    	insert into team(name,createTime,modifyTime,seq) 
    	values(#name#,#createTime#,#modifyTime#,#seq#);
		
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		insert into user(loginName, name, email, system, createTime, locked, onlineConfigView, password) 
		values(#loginName#, #name#, #email#, #system#, NOW(), #locked#, #onlineConfigView#, #password#)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_OrderItemRelation
        (
          orderItemId,
          orderId,
          producerId,
          productId,
          cityId,
          shopId,
          shopGroupId,
          timeLimitFlag,
          effectiveDays,
          producedDays,
          validEndTime,
          validTimeData,
          status,
          addTime,
          updateTime
        )
        VALUES
        (
          #orderItemRelationEntity.orderItemId#,
          #orderItemRelationEntity.orderId#,
          #orderItemRelationEntity.producerId#,
          #orderItemRelationEntity.productId#,
          #orderItemRelationEntity.cityId#,
          #orderItemRelationEntity.shopId#,
          #orderItemRelationEntity.shopGroupId#,
          #orderItemRelationEntity.timeLimitFlag#,
          #orderItemRelationEntity.effectiveDays#,
          #orderItemRelationEntity.producedDays#,
          #orderItemRelationEntity.validEndTime#,
          #orderItemRelationEntity.validTimeData#,
          #orderItemRelationEntity.status#,
          now(),
          now()
        )
        

           <selectKey keyProperty="relationId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_ProducerDelegation
        (
          producerId,
          delegatorId,
          status,
          addTime,
          updateTime
        )
        VALUES
        (
          #producerDelegationEntity.producerId#,
          #producerDelegationEntity.delegatorId#,
          #producerDelegationEntity.status#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_ProducerInfo
        (
          producerId,
          cityId,
          status,
          addTime,
          updateTime
        )
        VALUES
        (
          #producerInfoEntity.producerId#,
          #producerInfoEntity.cityId#,
          #producerInfoEntity.status#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    	insert into environment(name,label,ips, online, createUserId, createTime, modifyUserId, modifyTime,seq) 
    	values(#name#,#label#,#ips#, #online#, #createUserId#, NOW(), #modifyUserId#, NOW(),#seq#);
		
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    	insert into job_exec_time(jobName,switcher,failMail) values(#jobName#,#switcher#,#failMail#);
		
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    	insert into product(name,teamId,productLeaderId,createTime,modifyTime,seq) 
    	values(#name#,#teamId#,#productLeaderId#,#createTime#,#modifyTime#,#seq#);
		
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_Comment
        (
          launchId,
          content,
          pictureUrl,
          operatorId,
          addTime,
          updateTime
        )
        VALUES
        (
          #commentEntity.launchId#,
          #commentEntity.content#,
          #commentEntity.pictureUrl#,
          #commentEntity.operatorId#,
          now(),
          now()
        )
        

           <selectKey keyProperty="commentId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_ThirdPartyDetection
        (
          launchId,
          displayTemplateId,
          displayUrl,
          clickUrl,
          detailsDisplayUrl,
          detailsClickUrl,
          status,
          version,
          addTime,
          updateTime
        )
        VALUES
        (
          #detectionEntity.launchId#,
          #detectionEntity.displayTemplateId#,
          #detectionEntity.displayUrl#,
          #detectionEntity.clickUrl#,
          #detectionEntity.detailsDisplayUrl#,
          #detectionEntity.detailsClickUrl#,
          #detectionEntity.status#,
          #detectionEntity.version#,
          now(),
          now()
        )
        

           <selectKey keyProperty="detectionId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_Launch
        (
          relationId,
          planId,
          orderId,
          producerId,
          productId,
          accountName,
          sourceType,
          status,
          version,
          nextVersion,
          onlineTime,
          offlineTime,
          addTime,
          updateTime
        )
        VALUES
        (
          #launchEntity.relationId#,
          #launchEntity.planId#,
          #launchEntity.orderId#,
          #launchEntity.producerId#,
          #launchEntity.productId#,
          #launchEntity.accountName#,
          #launchEntity.sourceType#,
          #launchEntity.status#,
          #launchEntity.version#,
          #launchEntity.nextVersion#,
          #launchEntity.onlineTime#,
          #launchEntity.offlineTime#,
          now(),
          now()
        )
        

           <selectKey keyProperty="launchId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO config(`key`, `desc`, type, projectId, createUserId, modifyUserId, createTime, modifyTime, private, seq)
    	VALUES(#key#, #desc#, #type#, #projectId#, #createUserId#, #modifyUserId#, 
    	   <isNotNull property="createTime"/>
   <isNull property="createTime"/>
, 
    	   <isNotNull property="modifyTime"/>
   <isNull property="modifyTime"/>
, #privatee#, #seq#)
    	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO config_instance(configId, envId, value, refkey, context, contextmd5, createUserId, modifyUserId, createTime, modifyTime, `desc`, seq)
    	VALUES(#configId#, #envId#, #value#, #refkey#, #context#, #contextmd5#, #createUserId#, #modifyUserId#, 
    	   <isNotNull property="createTime"/>
   <isNull property="createTime"/>
, 
    	   <isNotNull property="modifyTime"/>
   <isNull property="modifyTime"/>
, #desc#, #seq#)
    	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO config_status(configId, envId, createUserId, createTime, modifyUserId, modifyTime)
    	VALUES(#configId#, #envId#, #createUserId#, NOW(), #modifyUserId#, NOW())
    	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_Material
        (
          launchId,
          displayTemplateId,
          title,
          content,
          pictureUrl,
          thumbnailUrl,
          price,
          remarks,
          beginValidTime,
          endValidTime,
          validTimeCustom,
          timeInterval,
          url,
          shopId,
          firstLineContent,
          secondLineContent,
          telephone,
          tag,
          status,
          version,
          addTime,
          updateTime
        )
        VALUES
        (
          #materialEntity.launchId#,
          #materialEntity.displayTemplateId#,
          #materialEntity.title#,
          #materialEntity.content#,
          #materialEntity.pictureUrl#,
          #materialEntity.thumbnailUrl#,
          #materialEntity.price#,
          #materialEntity.remarks#,
          #materialEntity.beginValidTime#,
          #materialEntity.endValidTime#,
          #materialEntity.validTimeCustom#,
          #materialEntity.timeInterval#,
          #materialEntity.url#,
          #materialEntity.shopId#,
          #materialEntity.firstLineContent#,
          #materialEntity.secondLineContent#,
          #materialEntity.telephone#,
          #materialEntity.tag#,
          #materialEntity.status#,
          #materialEntity.version#,
          now(),
          now()
        )
        

           <selectKey keyProperty="materialId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_Strategy
        (
          launchId,
          type,
          data,
          status,
          version,
          addTime,
          updateTime
        )
        VALUES
        (
          #strategyEntity.launchId#,
          #strategyEntity.type#,
          #strategyEntity.data#,
          #strategyEntity.status#,
          #strategyEntity.version#,
          now(),
          now()
        )
        

           <selectKey keyProperty="strategyId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MLC_PromoPlanInfo
        (
          promoPlanName,
          accountName,
          budgetTotal,
          budgetPerDay,
          producerId,
          productId,
          status,
          startTime,
          endTime,
          addTime,
          updateTime
        )
        VALUES
        (
          #promoPlanEntity.promoPlanName#,
          #promoPlanEntity.accountName#,
          #promoPlanEntity.budgetTotal#,
          #promoPlanEntity.budgetPerDay#,
          #promoPlanEntity.producerId#,
          #promoPlanEntity.productId#,
          #promoPlanEntity.status#,
          #promoPlanEntity.startTime#,
          #promoPlanEntity.endTime#,
          now(),
          now()
        )
        

           <selectKey keyProperty="promoPlanId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO config_set_task(projectId, envId, feature, `key`, value, context, createTime, `delete`)
		VALUES(#projectId#, #envId#, #feature#, #key#, #value#, #context#, NOW(), #delete#)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO config_snapshot_set(projectId, envId, task, createTime, createUserId)
		VALUES(#projectId#, #envId#, #task#, NOW(), #createUserId#)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO config_snapshot(snapshotSetId, configId, `key`, `desc`, `type`, projectId, createUserId, createTime, modifyUserId, modifyTime, private, seq, valueModifyTime)
		VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO config_instance_snapshot(snapshotSetId, configId, envId, `desc`, value, context, contextmd5, createUserId, modifyUserId, createTime, modifyTime, seq)
		VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO op_log
            (opType,
             opUserId,
             opUserIp,
             envId,
             opTime,
             projectId,
             content,
             key1,
             key2,
             key3,
             key4,
             key5,
             key6)
		VALUES(#opType#, #opUserId#, #opUserIp#, #envId# , NOW() , #projectId#, #content#, #key1#, #key2#, #key3#, #key4#, #key5#, #key6#)
		
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO project(name,  productId,createTime, modifyTime)
    	VALUES(#name#,  #productId#, #createTime#, #modifyTime#)
    	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_Document
                    (DocumentName,
                     DocumentPath,
                     OrderId,
                     AddUser,
                     ADDTIME,
                     UpdateUser,
                     UpdateTime)
            VALUES (#entity.documentName#,
                    #entity.documentPath#,
                    #entity.orderId#,
                    #entity.addUser#,
                    #entity.addTime#,
                    #entity.updateUser#,
                    #entity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_Tags 
	    		(TagContent,
	    		TagGroupId,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#tagEntity.tagContent#,
	   			#tagEntity.tagGroupId#,
	    		#tagEntity.addUser#,
	    		#tagEntity.updateUser#,
	    		#tagEntity.addTime#, 
		        #tagEntity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MMC_AdFlow(adId, type, userId, remark,promoId) VALUES
		(#adUpdateInfo.adId#,
		#adUpdateInfo.adUpdateType.value#,
		#adUpdateInfo.userId#,
		#adUpdateInfo.message#,
		#adUpdateInfo.promoId#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO MMC_AdShopRelation
		(adid, sid)
		VALUES
		(#adId#, #shopId#)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 
		INSERT INTO MMC_AdShopRelation
		(adid, sid)
			VALUES
		
		   <iterate conjunction="," property="shopList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO MMC_UserInfo
			(uid, name, mobile, contacts, customerName, type)
		VALUES
			(#userInfo.userId#,
			#userInfo.userName#,
			#userInfo.mobile#,
			#userInfo.contacts#,
			#userInfo.customerName#,
			#userInfo.userType.value#)
		
		   <selectKey keyProperty="uid" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO Campus_promote_users
			(promoteId, deviceId, client, phoneNo, dpid, state, AddTime, CreateTime, UpdateTime)
		VALUES
			(#promoterId#, #deviceId#, #agent#, #phoneNo#, #dpid#, #state#, now(), now(), now())
		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_ScoreBoardOffline 
			(Source, OldName, FileName, FileSize, Description, Operater, AddTime)
		VALUES
			(#source#, #oldName#, #fileName#, #fileSize#, #description#, #operater#, #addTime#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_tree_node (id,parent_id,name) values
    	(#id#,#parentId#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	insert into serviceReport (   <include refid="ServiceReport.columns"/>
) values
    	(#id#,#providerName#,#providerIp#,#consumerName#,#consumerIp#,#serviceName#,#code#,
    	#reportName#,#type#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource_item (id,datasource_type,heart_beat,max_value,min_value,datasource_id,internal_name) values
    	(#id#,#datasourceType#,#heartBeat#,#maxValue#,#minValue#,#datasourceId#,#internalName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph (id,text,name,title,image_format_id,height,width,upper_limit,lower_limit,vertical_label,grid_step,label_factor,projectId) values
    	(#id#,#text#,#name#,#title#,#imageFormateId#,#height#,#width#,#upperLimit#,#lowerLimit#,#verticalLabel#,#gridStep#,#labelFactor#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_graph_item (id,graph_id,datasource_id,color_id,alpha,graph_item_type,cdef_id,consolidation_id,text_format,sequence,value) values
    	(#id#,#graphId#,#datasourceId#,#colorId#,#alpha#,#graphItemType#,#cdefId#,#consolidationId#,#textFormat#,#sequence#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardUserMobile 
		SET CustomerID 	= #scoreUser.customerId#,
			UserID		= #scoreUser.userId#,
			MobileNo	= #scoreUser.mobileNo#, 
			Status		= #scoreUser.status#,
			AddTime		= NOW()
		   <selectKey keyProperty="UserMobileID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 INSERT INTO MC_MemberCardUserValueLog
		 (UserId,ProductID,ProductGroupID,CardId,Source,CardGroupID,ConsumeNum,VALUEChangeAmount,ValueChangeDesc,LogType,SerialNo,MachineId,OrderID,ADDTIME) 
		 VALUE (#data.userId#,#data.productId#,#data.productGroupId#,
		 #data.memberCardId#,#data.source#,#data.cardGroupId#,#data.consumeNum#,#data.valueChangeAmount#,#data.valueChangeDesc#,#data.logType#,#data.serialNo#,#data.machineId#,#data.orderId#,now());
		 
		    <selectKey keyProperty="MemberCardUserValueLog" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO MC_MemberCardUserValue(ProductID,ProductGroupID,UserId,UserValueUsed,UserValueTotal,ADDTIME,updateTime,ValidDate)
	 VALUE 
	 (#data.productId#,#data.productGroupId#,#data.userId#,#data.userValueUsed#,#data.userValueTotal#,now(),now(),#data.validDate#);		 
		    <selectKey keyProperty="MemberCardUserValueID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
				
		INSERT INTO MC_MemberCardUserFeed(UserID,FeedID,Status,IsLike,IsUse,AddTime,UpdateTime) 
		VALUES(#userFeed.userId#, #userFeed.feedId#, #userFeed.status#, 0, 0, NOW(), NOW())
		   <selectKey keyProperty="userFeedId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
				
		INSERT INTO MC_MemberCardFeed(FeedType,Category,ShopAccountID,MemberCardID,Title,PicPath,PicType,Content,BeginDate,EndDate,Status,AdminID,AuditInfo,TotalLikeNum,AuditTime,PushTime,AddTime,UpdateTime) 
		VALUES(#feed.feedType#, #feed.category#, 1, #feed.memberCardId#, #feed.title#, #feed.picPath#, #feed.picType#, #feed.content#, #feed.beginDate#, #feed.endDate#, 4, 1, '', #feed.totalLikeNum#, #feed.auditTime#, #feed.pushTime#, NOW(), NOW())
		   <selectKey keyProperty="feedId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
				
		INSERT INTO 
			MC_MemberCardUserFeed(UserID,FeedID,Status,IsLike,IsUse,UseTime,AddTime,UpdateTime,IsViewed)
		VALUES
		   <iterate conjunction="," property="feedIds"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MsgSubscriptionList 
		(UserID, 
		 DetailID, 
		 Status,
		 ADDTIME
		)
		VALUES
		   <iterate conjunction="," property="userIdList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_MsgInfo
			(Text,Link,StartTime,name, trainId,FileName,type)
		VALUES
			(#pushTxt#,#url#,#startTime#,#name#,#trainId#,#fileName#,#type#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 
			INSERT INTO OP_TaskLog (TaskId, JobId, Start, Offset, Status, AddTime) VALUES
		
		   <iterate conjunction="," property="taskLogList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO Activity_SamsungS5_groupon 
			(money,userId,createTime)
		VALUES
			(#money#, #userId#,NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingMobile.CI_SceneryOrderDetail 
			(SerialID, UserID, Uuid, TotalPrice, PickUpPlace, OrderDate, PolicyName, EnableCancel, Status, ShopID, 
				Count, TravelDate, Guest, OtherGuest, Notices, ServicePhoneNo, ClientType)
		VALUES
			(#serialId#, #userId#, #uuid#, #totalPrice#, #pickUpPlace#, #orderDate#, #policyName#, #enableCancel#, #status#,
				#shopId#, #count#, #travelDate#, #guest#, #otherGuest#, #notices#, #servicePhoneNo#, #clientType#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			BC_Notice(Title,Content,AddTime,AddAdminID)
		VALUES
			(#title#,#content#,NOW(),#addAdminID#)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_ShopAccountUser(ShopAccountId,UserId,AddTime,UpdateTime)
		VALUES
		(#shopAccountId#,#userId#,#addTime#,NOW())	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Feedback
		(FeedbackId, 
		Content, 
		CreateTime, 
		CreatorId)
		VALUES
		(#entity.feedbackId#,#entity.content#,#entity.createTime#,#entity.creatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_MobileDevice
		(UUID, 
		OSType, 
		OSDetail, 
		DeviceModel, 
		AppVersion, 
		CreateTime)
		VALUES
		(#entity.uuid#,#entity.osTypeId#,#entity.osDetail#,#entity.deviceModel#,#entity.appVersion#,#entity.createTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		DP_OperateLog(OperateType,LogInfo,OperatePlatform,OperaterID,AddTime,UpdateTime)
		VALUES
		(
		#log.operateType#,
		#log.logInfo#,
		#log.operatePlatform#,
		#log.operaterId#,
		NOW(),
		NOW())
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SysPrivateTask
		(Body, FromUserID, ToUserIDs, TotalCount, SuccessCount,Status, AdminId, AddTime, UpdateTime)
		VALUES
		(#body#,#fromUserId#,#toUserIds#,#totalCount#, 0, 0, #adminId#, NOW(), NOW())
		   <selectKey keyProperty="TaskID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SysSubscriptionTask
		(TYPE, Title, Body, CityIDs,
		ToSubscriber, AdditionalCount, ToUserIDs, TotalCount, SuccessCount,
		STATUS, FromTaskID, AdminID, ADDTIME, UpdateTime)
		VALUES
		(#type#,#title#,#body#,#cityIds#,#toSubscriber#,#additionalCount#,#toUserIds#,#totalCount#,0,
		0,#fromTaskId#,#adminId#,NOW(), NOW())
		   <selectKey keyProperty="TaskID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        

		INSERT INTO MP_ShopAccount_Role
		(ShopAccountId,RoleId)
		VALUES
		(#shopAccountId#,#roleId#)

        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MP_Module 
				(ModuleId, 
				ModuleName, 
				ADDTIME,
				priority,
				Remark
				)
				VALUES
				(#module.id#, 
				#module.name#, 
				NOW(),
				#module.priority#
				#module.remark#
				);
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingLog.MP_MerchantOperationLog
		(ShopAccountId,
		CreateTime,
		OperationKey,
		OperationName,
		IP,
		AppVersion,
		OsDetail,
		Context,
		Channel,
		Platform,
		SourceChannel,
		DpId)
		VALUES
		(#entity.shopAccountId#,#entity.createTime#,#entity.operationKey#,#entity.operationName#,
		#entity.ip#,#entity.appVersion#,#entity.osDetail#,
		#entity.context#,#entity.channel#,#entity.platform#,#entity.sourceChannel#,#entity.dpId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Component
		(
		Version,
		Name,
		Type,
		IconUrl,
		SelectedIconUrl,
		NavigateToUrl,
		PrivilegeId,
		Seq,
		Title,
		ViewUrl,
		ParentId,
		Comment,
		ControlId,
		IsActive
		)
		values
		(#entity.version#,#entity.name#,#entity.type#,#entity.iconUrl#,#entity.selectedIconUrl#,#entity.navigateToUrl#,#entity.privilegeId#,
		#entity.seq#,#entity.title#,#entity.viewUrl#,#entity.parentId#,#entity.comment#,#entity.controlId#,#entity.isActive#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_ControlMetadata
		(ComponentId,
		DataKey,
		DataValue)
		VALUES
		(#entity.componentId#,#entity.dataKey#,#entity.dataValue#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO MP_MerchantMessage
		(
            ShopAccountId,
            MessageType,
            BusinessId,
            Content,
            CreateTime,
            EffectiveEndTime,
            IsShake,
            IsSounding,
            SoundType,
            RepeatCount
		)
		VALUES
		(
            #entity.shopAccountId#,
            #entity.messageType#,
            #entity.businessId#,
            #entity.content#,
            #entity.createTime#,
            #entity.effectiveEndTime#,
            #entity.isShake#,
            #entity.isSounding#,
            #entity.soundType#,
            #entity.repeatCount#
		)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.BC_ShopAccount_Token
		(
            ShopAccountId,
            IsLogin,
            OSType,
            OSDetail,
            DeviceModel,
            AppVersion,
            Token,
            IsValid,
            IsBadReviewNeed,
            IsFailReplyNeed,
            IsTakeawayMsgNeed,
            IsBookMsgNeed,
            CreateTime,
            LastLoginTime,
            LastLogoutTime,
            LastUpdateTime
        )
		VALUES
		(
		    #entity.shopAccountId#,
		    #entity.isLogin#,
		    #entity.osTypeId#,
		    #entity.osDetail#,
		    #entity.deviceModel#,
		    #entity.appVersion#,
		    #entity.token#,
		    #entity.isValid#,
		    #entity.isBadReviewNeed#,
		    #entity.isFailReplyNeed#,
		    #entity.isTakeawayMsgNeed#,
		    #entity.isBookMsgNeed#,
		    #entity.createTime#,
		    #entity.lastLoginTime#,
		    #entity.lastLogoutTime#,
		    #entity.lastUpdateTime#
		)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Settle_Shop_Balance (ShopID, Balance, AddTime, UpdateTime)
			VALUES (#shopId#, #balance#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact_Audit (ShopContactID, ActionType, Reason, Memo, AdminID,RejectType,AddTime, UpdateTime)
			VALUES(
			#contactAuditInfo.ShopContactID#,
			#contactAuditInfo.ActionType#,
			#contactAuditInfo.Reason#,
			#contactAuditInfo.Memo#,
			#contactAuditInfo.AdminID#,
			#contactAuditInfo.RejectType#,
			NOW(),
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact_Operation_Trace (ShopContactID, ActionType, Source, ShopID, Operator, OperatorName, Memo, AccessIP, AddTime)
			VALUES(
			#shopContactOperationTraceInfo.ShopContactID#,
			#shopContactOperationTraceInfo.ActionType#,
			#shopContactOperationTraceInfo.Source#,
			#shopContactOperationTraceInfo.ShopID#,
			#shopContactOperationTraceInfo.Operator#,
			#shopContactOperationTraceInfo.OperatorName#,
			#shopContactOperationTraceInfo.Memo#,
			#shopContactOperationTraceInfo.AccessIP#,
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	   
			INSERT INTO BC_Shop_Contact_Operation_Trace_Item (TraceID, TraceKey, ValueBefore, ValueAfter)
			VALUES
		
		   <iterate conjunction="," property="shopContactOperationTraceItems"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO DeviceInfo
        SET type  = #Device.deviceType#,
            Name = #Device.deviceName#,
            deviceSerial = #Device.deviceSerial#,
            version=#Device.osVersion#,
            ip = #Device.ip#,
            status = #Device.status#
        ON DUPLICATE KEY
        UPDATE Name=#Device.deviceName#
        
           <selectKey keyProperty="id" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO CQRS_ORDER
		(ORDER_ID,CONTENT)
		VALUES
		(#entity.id#,#entity.content#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_Role
		(RoleName,CanAuthorise,Owner,AddTime,UpdateTime,Remark)
		VALUES
		(#roleInfo.name#,#roleInfo.canAuthorise#,#roleInfo.ownerId#,#roleInfo.addTime#,#roleInfo.updateTime#,#roleInfo.remark#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_RoleFunctionAssn
		(RoleId,FunctionId,AddTime,UpdateTime)
		VALUES
		(#relation.roleId#,#relation.functionId#, #relation.addTime#,#relation.updateTime#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CI_Gift_V2
        (EventId, DpID, ClientType, Version, EventProperties, MsgOrder, UserAgent)
        VALUES
        (#eventId#, #dpId#, #clientType#, #version#, #eventProperties#, #msgOrder#, #userAgent#);
           <selectKey type="post" keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MOPay_Payment(OrderID,Amount,PayOrderID,Status,RefundStatus,PaymentType,UserIP,AddTime,UpdateTime)
        VALUES (#payment.orderID#, #payment.amount#,
        #payment.payOrderID#,#payment.status#,#payment.refundStatus#,#payment.paymentType#,#payment.userIP#,NOW(),NOW())
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Settle_Transaction(ShopID, TransactionNumber, Amount, Source, Memo, Balance, AddTime)
			VALUES (#shopId#, #transactionNumber#, #amount#, #source#, #memo#, #balance#, NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_FileOrder 
	    		(FileId,
	    		OrderId,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#fileOrderEntity.fileId#,
	    		#fileOrderEntity.orderId#,
	    		#fileOrderEntity.addUser#,
	    		#fileOrderEntity.updateUser#,
	    		#fileOrderEntity.addTime#, 
		        #fileOrderEntity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_FileTag 
	    		(FileId,
	    		TagId,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#fileTagEntity.fileId#,
	    		#fileTagEntity.tagId#,
	    		#fileTagEntity.addUser#,
	    		#fileTagEntity.updateUser#,
	    		#fileTagEntity.addTime#, 
		        #fileTagEntity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO MMC_AdInfo
			(AdID, UID, CID, AdvertisementType, ExhibitionType, Title, 
			Content, BURL, SURL, IsMsg, MSG, Introduce, 
			BValidTime, EValidTime, BReleaseTime, EReleaseTime,
			Budget, Consume, Price, Status, DayBudget, DayConsume,
			IsEven, CreateTime, isUpdate)
		VALUES(
		#adInfo.adId#,
		#adInfo.userId#,
		#adInfo.cityId#,
			#adInfo.advertisementType.value#,
			#adInfo.exhibitionType.value#,
		#adInfo.title#,
		#adInfo.content#,
		#adInfo.bigPicKey#,
		#adInfo.smallPicKey#,
		#adInfo.isMessage#,
		#adInfo.message#,
		#adInfo.introduce#,
		#adInfo.validBeginTime#,
		#adInfo.validEndTime#,
		#adInfo.releaseBeginTime#,
		#adInfo.releaseEndTime#,
		#adInfo.budget#,
		0,
		#adInfo.price#,
			#adInfo.status.value#,
		#adInfo.dayBudget#,
		1,
		1,
			NOW(),
			0)
		
		   <selectKey keyProperty="adId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
		INSERT INTO CI_AppUpload 
			(AppUploadInf, AppUploadFeature, AppDownloadRoute, AppUploadVersion, AppUploadTime)
		VALUES
			(#appUploadDO.appUploadInf#, #appUploadDO.appUploadFeature#, #appUploadDO.appDownloadRoute#, #appUploadDO.appUploadVersion#, #appUploadDO.appUploadTime#)
		   <selectKey keyProperty="AppUploadId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_AutoPush 
			(PushType, Text, Link, CityID, PlatformIDs, PushDate, AddTime, UpdateTime, Status)
		VALUES
			(#autoPushDO.pushType#, #autoPushDO.text#, #autoPushDO.link#, #autoPushDO.cityId#, #autoPushDO.platformIds#, 
			 #autoPushDO.pushDate#, NOW(), NOW(), 0)
		   <selectKey keyProperty="autoPushId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CI_MessageInfo
		(Type,TrainId,MsgText,ShareBody,ShareUrl,Url,Utm,FileName,CommitName,BizID,MsgStatus,StartTime,CreateTime, DeviceNum, MsgType, UnsubscribeNum)
        VALUES(#type#,#trainId#,#msgText#,#shareBody#,#shareUrl#,#url#,#utm#,#fileName#,#commitName#,#bizId#,#status#,#startTime#,NOW(), #deviceNum#, #msgType#, #unsubscribeNum#)
           <selectKey keyProperty="msgInfoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CI_OssUtm
            (Utm, Type, Business, Title, CreateTime, UpdateTime)
        VALUES
            (#utm#, #type#, #business#, #title#, NOW(), NOW())
           <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into server (id,name,ip,port,des) values
    	(#id#,#name#,#ip#,#port#,#des#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into configdetail (id,projectId,projectKey,projectValue,aValue,bValue,cValue,dValue,des,des2,creatTime,creatUser,modifyTime) values
    	(#id#,#projectId#,#key#,#value#,#aValue#,#bValue#,#cValue#,#dValue#,#des#,#des2#,#creatTime#,#creatUser#,#modifyTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into configlog (id,operator,user,time,projectkey,projectvalue,environment,project) values
    	(#id#,#operator#,#userId#,#time#,#projectKey#,#projectValue#,#environment#,#projectId#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into config_public (id,publicKeyName,publicKeyValue,des) values
    	(#id#,#publicKeyName#,#publicKeyValue#,#des#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
     
    	insert into project (   <include refid="Project.columns"/>
) values
    	   
    	(#id#,#name#,#leader#,#opDirector#,#phone#,#email#,#des#,#hawk#,#lion#,#state#,#service#,#parentProject#,#svnAddress#,#DBAEmailAddr#, #isTransited#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into service (id,serviceName,serviceIp,serviceIp1,serviceIp2,serviceIp3,serviceIp4,serviceIp5,des,projectId ) values
    	(#id#,#serviceName#,#serviceIp#,#serviceIp1#,#serviceIp2#,#serviceIp3#,#serviceIp4#,#serviceIp5#,#des#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into user (id,name,email,phone,real_name) values
    	(#id#,#name#,#email#,#phone#,#realName#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into role (id,name) values (#id#,#name#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into resource (id,name,des) values (#id#,#name#,#des#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
     
    	insert into sqlChangeLog (   <include refid="SqlChangeLog.columns"/>
) values
    	   
    	(#id#,#project.name#,#project.id#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_color (id,hex) values
    	(#id#,#hex#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_key (id,key_name,key_value,des,keyType,projectId) values
    	(#id#,#keyName#,#keyValue#,#des#,#keyType#,#projectId#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO
		MC_MemberCardNumberUser
		(MemberCardID, CardGroupID, UserID, CardNumberID,Type,
		ADDTIME)
		values
		(0,#cardGroupId#,#userId#,#id# ,#type#, NOW())
			
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MessageDetail
		(Body,
		FromUserID,
		ToUserID,
		FromUserIP,
		AddTime)
		VALUES
		(#msgBody#,
		#fromUserId#,
		#toUserId#,
		#fromUserIp#,
		#addTime#)
		   <selectKey keyProperty="DetailID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_NoticeList
		(UserID,
		DetailID,
		AddTime,
		Status)
		VALUES
		   <iterate conjunction="," property="userIdList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_User (UserEmail,UserNickName,UserPW,UserAddDate,UserLastDate,
			UserPower,MediaSource,UserSource,UserCity,UserIP,EmailVerifyStatus) 
		VALUES (#userEmail# ,#userNickname# ,#password# ,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP ,
			1 ,252 ,252 ,#cityID# ,#userIP# ,0)
		   <selectKey keyProperty="UserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingMobile.YP_IPhonePush 
			(DeviceID, OldDeviceID, PToken)
		VALUES
			(#DeviceID#, #OldDeviceID#, #PToken#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO edm_mail.Mail_Template
		(TaskID,
		Subject,
		Content
		)
		VALUES
		(#taskId#,
		#subject#,
		#content#
		)
		   <selectKey keyProperty="templateId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ResetPasswordRequest
		(ResetPasswordRequestId,
		ShopAccount,
		ShopAccountId,
		MobileNo,
		LastRequestTime,
		IsReset,
		LastResetTime,
		LastUpdatorId
		)
		VALUES
		(#entity.id#,#entity.shopAccount#,#entity.shopAccountId#,#entity.mobileNo#,#entity.lastRequestTime#,#entity.isReset#,#entity.lastResetTime#,#entity.lastUpdatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Device_Account_Assn
		(DeviceId, 
		ShopAccountId, 
		IsLogin, 
		CreateTime, 
		LastLoginTime, 
		LastLogoutTime)
		VALUES
		(#entity.deviceId#,#entity.shopAccountId#,#entity.isLogin#,#entity.createTime#,#entity.lastLoginTime#,#entity.lastLogoutTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_User (UserEmail,UserNickName,UserPW,UserAddDate,UserLastDate,
			UserPower,MediaSource,UserSource,UserCity,UserIP,EmailVerifyStatus) 
		VALUES (#userEmail# ,#userNickname# ,#password# ,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP ,
			1 ,252 ,252 ,#cityID# ,#userIP# ,0)
		   <selectKey keyProperty="UserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SysNoticeTask
		(Title, Type, Important, Body, FromUserID, ToUserIDs, TotalCount, SuccessCount, Status, AdminId, AddTime, UpdateTime)
		VALUES
		(#title#,#type#,#important#,#body#,#fromUserId#,#toUserIds#,#totalCount#, 0, 0, #adminId#, NOW(), NOW())
		   <selectKey keyProperty="TaskID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO TG_SMS_Restrict
			(id, OrderID, SendTimes, Type, UserID, AddDate, UpdateDate)
			VALUES
			(#id#, #orderId#, #sendTimes#, #type#, #userId#, now(), now() )
		
		   <selectKey resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		insert into MP_Assoc_Account(MasterId, SlaveId, AddTime) values
		(#masterId#,#slaveId#,now())
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_Function
		(FunctionName,ModuleId,IsFixed,AddTime,UpdateTime,priority,forWeb,forApp)
		VALUES
		(#function.functionName#,#function.moduleId#,#function.isFixed#,#function.addTime#,#function.updateTime#,#function.priority#,#function.isForWeb#,#function.isForApp#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		insert into MP_Module(ModuleName,Remark,AddTime,UpdateTime)
		 values(#module.name#,#module.remark#,now(),now())
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			BC_Notice(Title,Content,AddTime,AddAdminID)
		VALUES
			(#title#,#content#,NOW(),#addAdminID#)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ResetPasswordRequest
		(ResetPasswordRequestId,
		ShopAccount,
		ShopAccountId,
		MobileNo,
		LastRequestTime,
		IsReset,
		LastResetTime,
		LastUpdatorId
		)
		VALUES
		(#entity.id#,#entity.shopAccount#,#entity.shopAccountId#,#entity.mobileNo#,#entity.lastRequestTime#,#entity.isReset#,#entity.lastResetTime#,#entity.lastUpdatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO DP_ShopAccountInnerAccessLog
		(ShopAccountId,Ip,AddTime,StaffId,Url)
		VALUES
		(#shopAccountId#,#ip#,#addTime#,#staffId#,#url#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DP_ShopPicModificationLog(
			ID,
			PicID,
			ShopID,
			OldValue,
			OldValueInfo,
			NewValue,
			NewValueInfo,
			ActionType,
			EditorID,
			EditorName,
			UserID,
			UserName,
			CreateTime
		)VALUES(
			#picChangeEvent.id#,
			#picChangeEvent.picId#,
			#picChangeEvent.shopId#,
			#picChangeEvent.oldValue#,
			#picChangeEvent.oldValueInfo#,
			#picChangeEvent.newValue#,
			#picChangeEvent.newValueInfo#,
			#picChangeEvent.actionType#,
			#picChangeEvent.editorId#,
			#picChangeEvent.editorName#,
			#picChangeEvent.userId#,
			#picChangeEvent.userName#,
			#picChangeEvent.createTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert into MOPay_Settle_Lock(LockKey, AddTime, UpdateTime)
        values (#key#, NOW(), NOW())
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TestRunner
        SET deviceSerial  = #deviceSerial#,
            seed = #seed#,
            pkgName = #pkgName#,
            logName = #logName#
        
           <selectKey keyProperty="id" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO CQRS_SEAT_AVAILABILITY
		(ORDER_ID)
		VALUES
		(#entity.order.id#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO DP_Shop_Contact (ShopID, CityID, ShopType, CategoryID, Name, Mobile, Email, Status, Memo, Source, AddTime,
			UpdateTime,AppChannel)
			VALUES(
			#shopContactBasicInfo.shopId#,
			#shopContactBasicInfo.cityId#,
			#shopContactBasicInfo.shopType#,
			#shopContactBasicInfo.categoryId#,
			#shopContactBasicInfo.Name#,
			#shopContactBasicInfo.mobile#,
			#shopContactBasicInfo.email#,
			1,
			#shopContactBasicInfo.memo#,
			#shopContactBasicInfo.source#,
			NOW(),
			NOW(),
			#shopContactBasicInfo.appChannel#);
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO OSS_PowerGroup 
			(NAME, PowerIDs, UserIDs, Flag, ADDTIME, UpdateTime)
		VALUES
			(#powerGroupPO.name#, #powerGroupPO.powerIds#, #powerGroupPO.userIds#, 1, NOW(), NOW())
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO BC_ShopAccount
		(Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,AccountType,PasswordVersion)
		VALUES
		(#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#,1)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO BC_ShopAccount
		(ShopAccount,Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,AccountType,PasswordVersion)
		VALUES
		(#shopAccount#,#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#,1)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		insert into MP_ShpAcnt_BindCode_Assn (ShopAccountId,BindCode) values(#shopAccountId#,#bindCode#);

		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		insert into MP_ShpAcnt_OpenId_Assn(ShopAccountId,OpenId) values(#shopAccountId#,#openId#);

		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		insert into MP_CloudPrintOrder(ShopId,ShopName,ShopAccountId,ShopAccount,TotalAmount,ActualAmount,WithDiscount,DiscountDetail, SerailNo, AddTime,UpdateTime,TerminalNo) values
		(#shopId#,#shopName#,#shopAccountId#,#shopAccount#,#totalAmount#,#actualAmount#,#withDiscount#,#discountDetail#,#serialNo#,now(),now(),#terminalNo#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Order(ShopID, UserID, MobileNO, DPID, UserIP, Amount, Status, AddTime, UpdateTime)
			VALUES (#shopID#, #userID#, #mobileNo#, #dpid#, #userIP#, #amount#, #status#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MOBILE_SCHEME_URL
			(`Name`, 
			Url,
			`Desc`, 
			VersionID,
			AddTime,
			UpdateTime)
		VALUES
			(#name#,
			#url#,
			#desc#,
			#versionID#,
			NOW(),
			NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_Files 
	    		(FileUrl,
	    		FileTitle,
	    		FileDescription,
	    		Width,
	    		Height,
	    		Status,
	    		WithoutWaterMark,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#fileEntity.fileUrl#,
	    		#fileEntity.fileTitle#,
	    		#fileEntity.fileDescription#,
	    		#fileEntity.width#,
	    		#fileEntity.height#,
	    		#fileEntity.status#,
	    		#fileEntity.withoutWaterMark#,
	    		#fileEntity.addUser#,
	    		#fileEntity.updateUser#,
	    		#fileEntity.addTime#, 
		        #fileEntity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_FileAccount 
	    		(FileId,
	    		AccountId,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#fileAccountEntity.fileId#,
	    		#fileAccountEntity.accountId#,
	    		#fileAccountEntity.addUser#,
	    		#fileAccountEntity.updateUser#,
	    		#fileAccountEntity.addTime#, 
		        #fileAccountEntity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_FileAccount 
	    		(FileId,
	    		AccountId,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES
		
		   <iterate conjunction="," property="fileAccounts"/>
   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_TagGroup
	    		(GroupType,
	    		GroupSubType,
	    		GroupDescription,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#tagGroupEntity.groupType#,
	   			#tagGroupEntity.groupSubType#,
	   			#tagGroupEntity.groupDescription#,	   			
	    		#tagGroupEntity.addUser#,
	    		#tagGroupEntity.updateUser#,
	    		#tagGroupEntity.addTime#, 
		        #tagGroupEntity.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MMC_DayConsume
			(adId, date, consume, pv, print, download, PCPV, MobilePV, MobileNearbyPV, MobileOthersPV)
			VALUES(
			#adConsume.adId#,
			#yesterday#,
			#adConsume.consume#,
			#adConsume.pv#,
			#adConsume.print#,
			#adConsume.messageDownload#,
			#adConsume.pcPv#,
			#adConsume.mobilePv#,
			#adConsume.mobileNearbyPv#,
			#adConsume.mobileOthersPv#		
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			
			INSERT INTO MMC_AccountFlow
			(userId, fee, type, balance, updateTime)
			VALUES(
			#userAccountDetail.userId#,
			#userAccountDetail.fee#,
			#userAccountDetail.type#,
			#userAccountDetail.balance#,
			#time#)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO zSurvey_NET.Sales.CS_ContractForceCancel
          (ContractID, CancelType, HasNotified, Memo)
          VALUES(#contractId#, 1, 1, #memo#)
        
           <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO Sales.CS_ContractForceCancel
          (ContractID, CancelType, HasNotified, Memo)
          VALUES(#contractId#, 0, 0, #maxPrePayDate#)
        
           <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

       	INSERT INTO OSS_JobInfo 
			(NAME, OWNER, Description, EmailReceivers, State, DeployIps, ADDTIME, UpdateTime)
		VALUES
			(#NAME#, #OWNER#, #Description#, #EmailReceivers#, 1, '', NOW(), NOW());
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO Cooperation_QQTips
			(name,begTime,endTime,maxNum,title,context,url,createTime)
		VALUES
			(#name#,#begTime#,#endTime#,#maxNum#,#title#,#context#,#url#,now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	insert into exceptionReport (   <include refid="ExceptionReport.columns"/>
) values
    	(#id#,#key1#,#key2#,#key3#,#key4#,#type#,#reportName#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef (id,name) values
    	(#id#,#name#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_cdef_item (id,cdef_id,type,value) values
    	(#id#,#cdefId#,#type#,#value#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_dynamic_key (id,key_name,key_value,datasource_id,value) values
    	(#id#,#keyName#,#keyValue#,#dsID#,#sumValue#)
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO OP_OperateTask (Type, Description, CronExpression, Template, Subs, FilePath, Status, AddTime)
		VALUES(#task.type#, #task.description#, #task.cronExpression#, #task.template#, #task.subs#, #task.filePath#, 0, NOW())
		   <selectKey keyProperty="taskId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ShopAccount
		(Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,accountType,PasswordVersion)
		VALUES
		(#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#,1)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ShopAccount
		(ShopAccount,Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,accountType,PasswordVersion)
		VALUES
		(#shopAccount#,#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#,1)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO AD_OrderItem(OrderItemId,OrderId,CityId,ProductId,ProductType,ProductSubType,
				OrderItemPrice,PriceUnit,STATUS,ADDTIME,AddUser,UpdateTime,UpdateUser)
			SELECT DISTINCT #colInfo.ID.newValue#,#colInfo.OrderID.newValue#,#colInfo.CityID.newValue#,
				#colInfo.ProductID.newValue#,p.ProductType,p.ProductSubType,
				IF(#colInfo.PriceID.newValue#>0,pr.Price,0.00),
				IF(#colInfo.PriceID.newValue#>0,pr.Unit,0),1,NOW(),0, NOW(),0
			FROM AD_Product p, AD_Price pr
			WHERE p.ProductId=#colInfo.ProductID.newValue#
		
		   <dynamic>
      <isGreaterThan compareValue="0" prepend="AND" property="colInfo.PriceID.newValue"/>
   </dynamic>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO AD_ItemKeyword(OrderItemId,ShopId,Keyword,InventoryStatus,DisplayStatus,
				ADDTIME,AddUser,UpdateTime,UpdateUser,STATUS,NewShopId)			
		
		   <isLessEqual compareValue="0" prepend="" property="colInfo.ContentID.newValue"/>
   <isGreaterThan compareValue="0" prepend="" property="colInfo.ContentID.newValue"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO AD_ItemButton (OrderItemId,CategoryLevelId,ShopType,CategoryId,TemplateId,
				ADDTIME,AddUser,UpdateTime,UpdateUser,STATUS)
		
		   <isLessEqual compareValue="0" prepend="" property="colInfo.ContentID.newValue"/>
   <isGreaterThan compareValue="0" prepend="" property="colInfo.ContentID.newValue"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_MovieReservation
		(MovieReservation_ID,ThirdPartyOrderID,ThirdPartyID,OrderID,MovieShowID,
        Status,UserId,MobileNo,SeatInfo,TicketCount,TicketID,TicketNO,ThirdPartyPayNo,AddDate,UpdateDate)
		VALUES
		(#id#,#thirdPartyOrderId#,#thirdPartyId#,#orderId#,#movieShowId#,
		#status#,#userId#,#mobileNo#,#seatInfo#,#ticketCount#,#ticketId#,#ticketNO#,#thirdPartyPayNo#,now(),now())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_AuthorizationRecord
		(`From`, `To`, CanAssignOthers, AddTime, Mark, ActionType)
		VALUES
		(#from#, #to#, #canAssignOthers#, now(), #mark#, 1)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_AuthorizationRecord
		(`From`, `To`, CanAssignOthers, AddTime, Mark, ActionType)
		VALUES
		(#from#, #to#, #canAssignOthers#, now(), #mark#, 2)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_Message
		(Sector,Module,Type,SendTime,Title,SummaryMsg,UpdateTime,URL)
		VALUES
		(#sector#,#module#,#type#,#sendTime#,#title#,#summaryMsg#,#updateTime#,#url#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_ShopAccountUser(ShopAccountId,UserId,AddTime,UpdateTime)
		VALUES
		(#shopAccountId#,#userId#,#addTime#,NOW())	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Settle_Statistic (StatisticType, StatisticDate, Amount, AddTime)
			VALUES (#type#, #date#, #amount#, NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert into MOPay_Settle_Withdraw_Auto_Lock(LockKey, AddTime, UpdateTime)
        values (#key#, NOW(), NOW())
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO CI_CheckIn 
    	(UserID, ShopID, Lng, Lat, PostionRange, Shared, FeedShared, CheckInType, Star, Tips, UserIP, ClientID, UserAgent,PicCenterUrl,PicID, photos, DeviceId, CityID, ShopType, Status, LocalFilter)
        VALUES 
        (#userId#, #shopId#, #lng#, #lat#, #postionRange#, #shared#, #feedShared#, #checkInType#, #star#, #tips#, #userIp#, #clientId#, #userAgent#, #picCenterUrl#, #picId#, #photoUrls#, #deviceId#, #cityId#, #shopType#, #status#, #localFilter#);
    	   <selectKey keyProperty="CheckInID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO OSS_HotAds 
			(Title, SubTitle, PicUrl, UrlSchema, TYPE, Priority, 
				BeginTime, EndTime, CityFlag, CityIDs, Yidi, Utm, ADDTIME, UpdateTime, Flag)
		VALUES
			(#hotAdsPO.title#, #hotAdsPO.subTitle#, #hotAdsPO.picUrl#, #hotAdsPO.link#, #hotAdsPO.type#, #hotAdsPO.priority#, 
				#hotAdsPO.beginTime#, #hotAdsPO.endTime#, #hotAdsPO.cityFlag#, #hotAdsPO.cityIds#, #hotAdsPO.yidi#, #hotAdsPO.utm#, NOW(), NOW(), 2)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO OSS_HotAds_Client 
			(HotAdsID, CLIENT, SourceFlag, Sources, MinVersion, MaxVersion, Flag, ADDTIME, UpdateTime)
		VALUES
			(#hotAdsClientPO.hotAdsId#, #hotAdsClientPO.client#, #hotAdsClientPO.sourceFlag#, #hotAdsClientPO.sources#, #hotAdsClientPO.minVersion#, #hotAdsClientPO.maxVersion#, 1, NOW(), NOW())
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_CustomPush 
			(TargetType, PushText, Link, Utm, BeginTime, EndTime, PlatformIDs, AddNotify, PushSound, OperateName, Status, AddTime, UpdateTime)
		VALUES
			(#customPushDO.targetType#, #customPushDO.pushText#, #customPushDO.link#, #customPushDO.utm#, #customPushDO.beginTime#, #customPushDO.endTime#, 
			 #customPushDO.platformIds#, #customPushDO.addNotify#, #customPushDO.pushSound#, #customPushDO.operateName#, -1, NOW(), NOW())
		   <selectKey keyProperty="customPushId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_CustomPushFileUpload 
			(CustomPushID, ContentType, FileName, TotalNum, SendNum, ArrivalNum)
		VALUES
			(#uploadDO.customPushId#, #uploadDO.contentType#, #uploadDO.fileName#, #uploadDO.totalNum#, #uploadDO.sendNum#, #uploadDO.arrivalNum#)
		   <selectKey keyProperty="customPushId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
		INSERT INTO Mobile_API 
			(APIName, TypeName, APIDesc, PlatformType, ReturnModel, APIStatus, CombinWay, CreateTime, UpdateTime, CreateUser, BeginVer, EndVer)
		VALUES
			(#mobileAPIDo.apiName#, #mobileAPIDo.apiType#, #mobileAPIDo.apiDesc#, #mobileAPIDo.apiPlatform#, #mobileAPIDo.apiReturnRef#,  #mobileAPIDo.apiStatus#, 
			 #mobileAPIDo.apiCombinWay#, #mobileAPIDo.apiCreateTime#, #mobileAPIDo.apiUpdateTime#, #mobileAPIDo.apiCreateUser#, #mobileAPIDo.apiBeginVer#, #mobileAPIDo.apiEndVer#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    INSERT INTO TEMP_Order
            (Id,
             OrderId,
             AccountId,
             AccountIds,
             FilePath,
             UploadedFileCount,
             UploadedFileNames,
             Log,
             Status)
		VALUES (#order.id#,
		        #order.orderId#,
		        #order.accountId#,
		        #order.accountIds#,
		        #order.filePath#,
		        #order.uploadedFileCount#,
		        #order.uploadedFileNames#,
		        #order.log#,
		        #order.status#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ASPNet_zSurvey.DP_Promo
           (PromoTitle
           ,PromoType
           ,TempleteID
           ,BeginDate
           ,EndDate
           ,PromoBeginDate
           ,PromoEndDate
           ,PromoDesc
           ,PromoDemo
           ,Power
           ,CanUpdate
           ,CityID
           ,AddTime
           ,IsTopCoupon
           ,IsNewShop
           ,IsBrand
           ,ShopList
           ,ShopName
           ,CouponShopName
           ,Hits
           ,WeeklyHits
           ,WishTotal
           ,ReplyTotal
           ,RateTotal
           ,RateGoodTotal
           ,RateScore
           ,Priority
           ,PromoTags
           ,PrintCount
           ,ShowCount
           ,PicServer
           ,PicFilePath
           ,PicFileType
           ,PicAddTime
           ,LogoFilePath
           ,LogoFileType
           ,LogoAddTime
           ,SearchKeyword
           ,BLogoFilePath
           ,BLogoFileType
           ,BLogoAddTime
           ,ShopDesc
           ,SMSCount
           ,HasSMS
           ,AdminID
           ,IsMaxDiscount
           ,SMSInfo
           ,IsShow
           ,ItemID
           ,ImageComment
           ,OrderByDate
           ,MonthlyHits
           ,MonthlyDCount
           ,OnlineDays
           ,OldItemID
           ,Memo
           ,ExtendFlag
           ,HasSmallPic
           ,HasMiddlePic
           ,DefaultLogoPath
           ,note
           ,SmallPicPath
           ,SmallPicType
           ,DefaultSmallPicPath)
     SELECT
            PromoTitle
           ,PromoType
           ,TempleteID
           ,getdate()
           ,#releaseendtime#
           ,PromoBeginDate
           ,#validendtime#
           ,PromoDesc
           ,PromoDemo
           ,Power
           ,CanUpdate
           ,CityID
           ,AddTime
           ,IsTopCoupon
           ,IsNewShop
           ,IsBrand
           ,ShopList
           ,ShopName
           ,CouponShopName
           ,Hits
           ,WeeklyHits
           ,WishTotal
           ,ReplyTotal
           ,RateTotal
           ,RateGoodTotal
           ,RateScore
           ,Priority
           ,PromoTags
           ,PrintCount
           ,ShowCount
           ,PicServer
           ,PicFilePath
           ,PicFileType
           ,PicAddTime
           ,LogoFilePath
           ,LogoFileType
           ,LogoAddTime
           ,SearchKeyword
           ,BLogoFilePath
           ,BLogoFileType
           ,BLogoAddTime
           ,ShopDesc
           ,SMSCount
           ,HasSMS
           ,AdminID
           ,IsMaxDiscount
           ,SMSInfo
           ,IsShow
           ,ItemID
           ,#imagecomment#
           ,OrderByDate
           ,MonthlyHits
           ,MonthlyDCount
           ,OnlineDays
           ,OldItemID
           ,Memo
           ,ExtendFlag
           ,HasSmallPic
           ,HasMiddlePic
           ,DefaultLogoPath
           ,note
           ,SmallPicPath
           ,SmallPicType
           ,DefaultSmallPicPath
		FROM ASPNet_zSurvey.DP_Promo
		WHERE PromoID=#promoid#
		   <selectKey keyProperty="PromoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MMC_Account
			(uId, frozen, balance)
			VALUES(
			#userAccountFeeEntity.userId#,
			#userAccountFeeEntity.frozen#,
			#userAccountFeeEntity.balance#)
		
		   <selectKey keyProperty="uId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_BizPush 
			(PushText, Link, Utm, BeginTime, EndTime, PlatformID, RecipientIdType, RecipientId, AddNotify, pushSound, AddName, Status, AddTime, UpdateTime)
		VALUES
			(#bizPushDO.pushText#, #bizPushDO.link#, #bizPushDO.utm#, #bizPushDO.beginTime#, #bizPushDO.endTime#,
			 #bizPushDO.platformId#, #bizPushDO.recipientIdType#, #bizPushDO.recipientId#, #bizPushDO.addNotify#, 
			 #bizPushDO.pushSound#, #bizPushDO.addName#, #bizPushDO.status#, NOW(), NOW())
		   <selectKey keyProperty="bizPushID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO OSS_BizPushApply 
			(ApplyUser, Utm, Description, ADDTIME, UpdateTime, Flag)
		VALUES
			(#bizPushApplyPO.user#, #bizPushApplyPO.utm#, #bizPushApplyPO.desc#, NOW(), NOW(), 1)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO Campus_promote
			(name,parentId,phoneNo,password,area,school,CreateTime)
		VALUES
			(#name#,#parentId#,#phoneNo#,#password#,#area#,#school#,now())
		   <selectKey keyProperty="promoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_HotModule 
			(Title, SubTitle, PicUrl, ModuleUrl, CityIDs, PlatformIDs, Versions, BeginTime, EndTime, Status, AddTime, UpdateTime)
		VALUES
			(#hotModuleDO.title#, #hotModuleDO.subTitle#, #hotModuleDO.picUrl#, #hotModuleDO.moduleUrl#,
			 #hotModuleDO.cityIds#, #hotModuleDO.platformIds#, #hotModuleDO.versions#,
			#hotModuleDO.beginTime#, #hotModuleDO.endTime#, 1, NOW(), NOW())
		   <selectKey keyProperty="moduleId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO CI_Notifications 
    	(UserID, Name, Image, Content, ContentStyle, Uri, AddDate, MessageType) 
    	VALUES
   		   <iterate conjunction="," property="notifyList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into environment (id,name,ip) values
    	(#id#,#name#,#ip#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into middlewareTree (id,parent_id,name) values
    	(#id#,#parentId#,#name#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
     
    	insert into operationLog (   <include refid="OperationLog.columns"/>
) values
    	   
    	(#id#,#type.typeCode#,#operator#,#operation.operationCode#,#content#,#project#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into sqlReview (id,filePath,sqlId,sqlContent,reviewerId,reviewTime) values
    	(#id#,#filePath#,#sqlId#,#sqlContent#,#reviewerId#,#reviewTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		      
    	insert into sqlReview (id,filePath,sqlId,sqlContent,reviewerId,reviewTime,recommendation,state) values
    	(#id#,#filePath#,#sqlId#,#sqlContent#,#reviewerId#,#reviewTime#,#recommendation#,1)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into alarm_record (id,time,alarmTime,content,alarmId,emailState) values
    	(#id#,#time#,#alarmTime#,#content#,#alarmId#,#emailState#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO exceptionInfo (
			key1,
			key2,
			key3,
			key4,
			host_name,
			exception,
			message,
			stack,
			num,
			currentTime
			)
		VALUES(
			#key1Str#,
			#key2Str#,
			#key3Str#,
			#key4Str#,
			#hostNameStr#,
			#exception#,
			#message#,
			#stack#,
			#num#,
			#timeCurrent#
		)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_host (id,name,ip,port,user_name,user_password,path,isFetch,projectId,weight,type,port1,env) values
    	(#id#,#name#,#ip#,#port#,#userName#,#userPsw#,#path#,#isFetch#,#projectId#,#weight#,#type#,#port1#,#env#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO CI_MsgInfo 
    	(TEXT, Link, StartTime, Name, fileName, status, trainId, type)
    	VALUES 
    	(#text#, #url#, #startTime#, #name#, #fileName#, #status#, #trainId#, #type#)
    	   <selectKey keyProperty="msgId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_MsgDeviceId
			(MsgId, DeviceId)
		VALUES			
			   <iterate conjunction="," property="mdList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardOrder(UserID,CityID,CardID,CardGroupID,ProductID,ProductGroupID,Quantity,TotalAmount
			,PaymentAmount,MobileNo,OrderType,Status,RefundStatus,UserAgent,PayOrderID,UserIP,Source,ADDTIME,SerialNo) 
		VALUES(#order.userId#,#order.cityId#,#order.cardId#,#order.cardGroupId#,#order.productId#,#order.productGroupId#,#order.quantity#,#order.totalAmount#
			,#order.paymentAmount#,#order.mobileNo#,#order.orderType#,0,0,#order.userAgent#,#order.payOrderId#,#order.userIp#,#order.source#,NOW(),#order.serialNo#)
		   <selectKey keyProperty="MemberCardOrderID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardOrderValidCode(CardOrderID,ValidCode,ADDTIME) VALUES (#cardOrderId#,#validCode#,NOW())
		   <selectKey keyProperty="OrderValidCodeID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_NoticeDetail
			(Title,
			Body,
			FromUserID,
			NoticeType,
			Important,
			ADDTIME)
		VALUES
			(#title#,
			#body#,
			#fromUserId#,
			#noticeType#,
			#important#,
			#addTime#)
		   <selectKey keyProperty="DetailID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO Mail_Task
		(Description,
		StartDate,
		ExpireDate,
		STATUS
		)
		VALUES
		(#description#,
		NOW(),
		NOW(),
		1
		)
		   <selectKey keyProperty="taskId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Comments 
			(UserID, ReplyToUserID, ReplyToCommentID, RootCheckInID, Content, UserIP)
		VALUES 
			(#userID#, #replyToUserID#, 0, #rootCheckInID#, #content#, #userIP#)
		   <selectKey keyProperty="CommentID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Comments 
			(UserID, ReplyToUserID, ReplyToCommentID, RootCheckInID, Content, UserIP)
		VALUES 
			(#userID#, #replyToUserID#, #ReplyToCommentID#, #rootCheckInID#, #content#, #userIP#)
		   <selectKey keyProperty="CommentID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_MessageToken
		(DeviceId, 
		Token, 
		IsValid, 
		CreateTime)
		VALUES
		(#entity.deviceId#,#entity.token#,#entity.isValid#,#entity.createTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Comments 
			(UserID, ReplyToUserID, ReplyToCommentID, RootCheckInID, Content, UserIP)
		VALUES 
			(#userID#, #replyToUserID#, #replyToCommentID#, #rootCheckInID#, #content#, #userIP#)
		   <selectKey keyProperty="CommentID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Comments 
			(UserID, ReplyToUserID, ReplyToCommentID, RootCheckInID, Content, UserIP)
		VALUES 
			(#userID#, #replyToUserID#, 0, #rootCheckInID#, #content#, #userIP#)
		   <selectKey keyProperty="CommentID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO MP_Operation_Merchant(ParentMerchantId, MerchantName,
        Address, LicenseId, LicenseSnapURL, HolderName, HolderIDNumber, HolderIDSnapURL,
        OperationMode, MainType, Status, CreateTime, UpdateTime, Creator, Updater)
        VALUES (#parentMerchantId#, #merchantName#,
        #address#, #licenseId#, #licenseSnapURL#, #holderName#, #holderIDNumber#,
        #holderIDSnapURL#, #operationMode#, #mainType#, #status#, NOW(),
        NOW(), #creator#, #updater#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(ID,Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#id#,#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Device_Account_Assn
		(DeviceId, 
		ShopAccountId, 
		IsLogin, 
		CreateTime, 
		LastLoginTime, 
		LastLogoutTime)
		VALUES
		(#entity.deviceId#,#entity.shopAccountId#,#entity.isLogin#,#entity.createTime#,#entity.lastLoginTime#,#entity.lastLogoutTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO DianPingBC.MP_MobileDevice
		(UUID, 
		OSType, 
		OSDetail, 
		DeviceModel, 
		AppVersion, 
		CreateTime,IsFailReplyNeed,
		IsBadReviewNeed)
		VALUES
		(#entity.uuid#,#entity.osTypeId#,#entity.osDetail#,#entity.deviceModel#,#entity.appVersion#,#entity.createTime#,#entity.isFailReplyNeed#,#entity.isBadReviewNeed#)
		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Discount(UserID, ShopID, Amount, OrderID, PaymentID, StrategyID, Status,  AddTime, UpdateTime)
			VALUES (#userID#, #shopID#, #amount#, #orderID#, #paymentID#, #strategyID#, #status#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

      INSERT INTO MOPay_Settle_Contract ( ContractID, BankAccountName, BankAccountNumber, BankName, BankProvince, BankCity, FreeStart, FreeEnd, TaxRate, Status, ShopAccountID, AddTime, UpdateTime )
      VALUES ( #contractId#, #bankAccountName#, #bankAccountNumber#, #bankName#, #bankProvince#, #bankCity#, #freeStart#, #freeEnd#, #taxRate#, 1, #shopAccountId#, NOW(), NOW() )
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Settle_Finance
			(FinanceNumber, BankAccountName, BankAccountNumber, BankName, BankProvince, BankCity, Currency,
			SettleWay, PayBankName, PayBankAccountNumber, PayDate, Useage, Amount, Memo, Status, AddTime, UpdateTime)
			VALUES
			(#financeNumber#, #bankAccountName#, #bankAccountNumber#, #bankName#, #bankProvince#, #bankCity#, #currency#,
			#settleWay#, #payBankName#, #payBankAccountNumber#, #payDate#, #useage#, #amount#, #memo#, #status#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MOPay_Settle_Finance_Withdraw(FinanceNumber, WithdrawId)
        VALUES (#financeNumber#, #withdrawId#)
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MOPay_Settle_Contract_Shop (ContractID, ShopID, ShopName, AddTime)
        VALUES
           <iterate conjunction="," property="shopList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO CI_GiftEvent
            (EventId, Source, MessageContent, VersionCondition, CouponGroupId, InvalidCouponGroupId, NewUserLimit,
             MaxCount, MessageType, ShouldHasPhoneNumber, `Value`, BeginDate, EndDate, CreateTime)
        VALUES
            (#dto.eventId#, #dto.source#, #dto.messageContent#, #dto.versionConditions#, #dto.couponGroupId#,
             #dto.invalidCouponGroupId#, #dto.newUserLimit#, #dto.maxCount#, #dto.messageType#, #dto.shouldHasPhoneNumber#,
             #dto.value#, #dto.beginDate#, #dto.endDate#, now())
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Order_CouponOffer(CouponOfferId, OrderId, OriginAmount, DiscountAmount, DiscountRatio,Title,Description, AddTime, UpdateTime)
			VALUES (#entity.couponOfferID#, #entity.orderID#, #entity.originAmount#, #entity.discountAmount#, #entity.discountRatio#,#entity.title#,#entity.description#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_TGNotice
		(UserID,
		DetailID,
        NoticeType,
		AddTime,
        FinishTime,
		Status)
		VALUES
		   <iterate conjunction="," property="userIdList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
		INSERT INTO Data_Model 
			(ModelName, ModelHash, ModelStatus, CreateTime, UpdateTime, CreateUser, BeginVer, EndVer, ModelDesc)
		VALUES
			(#dataModelDo.modelName#, #dataModelDo.modelHash#, #dataModelDo.modelStatus#, #dataModelDo.modelCreateTime#, 
			 #dataModelDo.modelUpdateTime#, #dataModelDo.modelCreator#, #dataModelDo.beginVer#, #dataModelDo.endVer#, #dataModelDo.modelDesc#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MOBILE_VERSION
			(`Name`, 
			LockTime,
			GrayTime,
			PublishTime,
			AddTime,
			UpdateTime,
			VersionInfo)
		VALUES
			(#name#,
			#lockTime#,
			#grayTime#,
			#publishTime#,
			NOW(),
			NOW(),
			#versionInfo#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	    	INSERT INTO MC_FileOrderCopy
	    		(FileOrderId,
	    		FileTitle,
	    		FileDescription,
	    		Mode,
	    		Width,
	    		Height,
	    		RealWidth,
				RealHeight,
	    		AddUser,
	    		UpdateUser,
	    		AddTime,
	    		UpdateTime) 
	   		VALUES 
	   			(#fileOrderCopy.fileOrderId#,
	    		#fileOrderCopy.fileTitle#,
	    		#fileOrderCopy.fileDescription#,
	    		#fileOrderCopy.mode#,
	    		#fileOrderCopy.width#,
	    		#fileOrderCopy.height#,
	    		#fileOrderCopy.realWidth#,
	    		#fileOrderCopy.realHeight#,
	    		#fileOrderCopy.addUser#,
	    		#fileOrderCopy.updateUser#,
	    		#fileOrderCopy.addTime#, 
		        #fileOrderCopy.updateTime#);
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MMC_Consume
			(adId, consume, pv, download, print, remark, consumeTime,pvType)
			VALUES(
			#adConsume.adId#,
			#adConsume.consume#,
			#adConsume.pv#,
			#adConsume.messageDownload#,
			#adConsume.print#,
			#adConsume.remark#,
			NOW(),
			#adConsume.pvType#)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_CommonPush 
			(PushText, PushLink, Utm, PushAction, PushSound, PushTimeType, PushTime, OperatorId, BizID, Status, AddTime, UpdateTime)
		VALUES
			(#commonPushDO.pushText#, #commonPushDO.pushLink#, #commonPushDO.utm#, #commonPushDO.pushAction#, #commonPushDO.pushSound#,
			 #commonPushDO.pushTimeType#, #commonPushDO.pushTime#, #commonPushDO.operatorId#, #commonPushDO.bizId#, 
			 0, NOW(), NOW())
		   <selectKey keyProperty="commonPushId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_CommonPushRecipient 
			(CommonPushID, RecipientType, PlatformIDs, CityIDs, RecipientIDType, RecipientID)
		VALUES
			(#commonPushReDO.commonPushId#, #commonPushReDO.recipientType#, #commonPushReDO.platformIds#, 
			#commonPushReDO.cityIds#, #commonPushReDO.recipientIdType#, #commonPushReDO.recipientId#)
		   <selectKey keyProperty="commonPushRecipientId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_CommonPushStat 
			(CommonPushID, SendNum, ArrivalNum)
		VALUES
			(#commonPushStatDO.commonPushId#, #commonPushStatDO.sendNum#, #commonPushStatDO.arrivalNum#)
		   <selectKey keyProperty="commonPushStatId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_MsgType 
			(Title, OnDesc, OffDesc, Icon, BottomDesc, Parent, DpUser, BenefitCount, BenefitName, Sort, ADDTIME, UpdateTime, Flag)
		VALUES
			(
                #msgType.title#, #msgType.onDesc#, #msgType.offDesc#, #msgType.icon#,
                #msgType.bottomDesc#, #msgType.parent#, #msgType.dpuser#,
                #msgType.benefitCount#, #msgType.benefitName#, #msgType.sort#, NOW(), NOW(), 1
            )
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	insert into reportRecord (   <include refid="Report.columns"/>
) values
    	(#id#,#reportId#,#email#,#title#,#content#,#state#,now(),#reportType#)
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_datasource (id,name,key1,key2,key3,key4,data_type,host_names,step,rrd_path,timespan,is_active,avg_accum_type,projectId,data_choice) values
    	(#id#,#name#,#key1#,#key2#,#key3#,#key4#,#dataType#,#hostNames#,#step#,#rrdPath#,#timespan#,#isActive#,#avgAccumType#,#projectId#,#dataChoice#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    	insert into jrobin_rra (id,name,cf,xff,steps,rows,timespan) values
    	(#id#,#name#,#cf#,#xff#,#steps#,#rows#,#timespan#)
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingMobile.CI_SceneryOrderDetail 
			(SerialID, UserID, Uuid, TotalPrice, PickUpPlace, OrderDate, PolicyName, EnableCancel, Status, ShopID, 
				Count, TravelDate, Guest, OtherGuest, Notices, ServicePhoneNo, ClientType)
		VALUES
			(#serialId#, #userId#, #uuid#, #totalPrice#, #pickUpPlace#, #orderDate#, #policyName#, #enableCancel#, #status#,
				#shopId#, #count#, #travelDate#, #guest#, #otherGuest#, #notices#, #servicePhoneNo#, #clientType#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			MC_MemberCardIVRAccount(ShopTel,ShopID,ShopAccountID,Deleted,AddTime)
		VALUES
			(#shopTel#,#shopId#,#shopAccountId#,0,CURRENT_TIMESTAMP)
		   <selectKey keyProperty="ivrAccountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardUser(MemberCardID, UserID, CardNO,Status, Source, CellPhoneType, UserAgent, ULat, ULng, AddTime)
		VALUES (#cardId#, #userId#, #cardNo#, #status#, #source#, #cellPhoneType#, #UserAgent#, #lat#, #lng#, NOW())
		   <selectKey keyProperty="MemberCardUserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardUserShop(MemberCardID, UserID, ShopID, Source, CellPhoneType, UserAgent, ULat, ULng, Status, AddTime)
		VALUES (#cardId#, #userId#, #shopId#, #source#, #cellPhoneType#, #UserAgent#, #lat#, #lng#, 1, NOW())
		   <selectKey keyProperty="MemberCardUserShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardNUserInfo(UUID, UserName, UserSex, UserBirthDay,AddTime)
		VALUES (#uuid#, #userName#, #gender#, #birthday#,NOW())
		   <selectKey keyProperty="NUserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		  
	    INSERT INTO MC_MemberCardNUserSynLog(MemberCardUserID,NUserID,UserID,CardID,Syn) 
			SELECT MemberCardUserID,#nUserId#,#userId#,MemberCardID,#syn# FROM MC_MemberCardUser WHERE UserID = #nUserId# AND MemberCardID IN 
		
	       <iterate open="(" conjunction="," property="cardIdList" close=")"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardConsume(
						MemberCardID, 
						UserID, 
						CardNO,
						ShopID, 
						ProductID,
						ConsumePrice,
						ConsumeDate, 
						AddTime,
						SerialNo,
						MachineID)
		VALUES(#cardId#, 
			   #userId#, 
			   #cardNo#, 
			   #shopId#, 
			   #productId#, 
			   #consumePrice#, 
			   #consumeDate#, 
			   #addTime#,
			   #serialNo#,
			   #machineId#)
		   <selectKey keyProperty="MemberCardConsumeID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardConsume(
						MemberCardID, 
						UserID, 
						CardNO,
						ShopID, 
						ProductID,
						ConsumeDate, 
						AddTime,
						SerialNo,
						MachineID,
						BuyQuantity
						)
		VALUES(#cardId#, 
			   #userId#, 
			   #cardNo#, 
			   #shopId#, 
			   #productId#, 
			   #consumeDate#, 
			   #addTime#,
			   #serialNo#,
			   #machineId#,
			   #buyQuantity#
			   )
		   <selectKey keyProperty="MemberCardConsumeID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardUser(MemberCardID, UserID, CardNO,Status, Source, CellPhoneType, UserAgent, ULat, ULng, AddTime)	VALUES 
		   <iterate conjunction="," property="cardUsers"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardUserShop(MemberCardID, UserID, ShopID, Source, CellPhoneType, UserAgent, ULat, ULng, Status, AddTime)
		VALUES
		   <iterate conjunction="," property="shops"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
	INSERT INTO DP_MsgSubscriptionDetail 
		(Title, 
		 Body, 
		 FromUserID, 
		 SubType, 
		 ADDTIME
		)
		VALUES
		(#title#,
		#body#,
		#fromUserId#,
		#subType#,
		#addTime#)
		   <selectKey keyProperty="DetailID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SubscriptionList
		(UserID,
		SubType,
		Status,
		CityID,
		AddTIME)
		VALUES
		   <iterate conjunction="," property="typeList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SubscriptionList
		(UserID,
		SubType,
		Status,
		CityID,
		AddTIME)
		VALUES
		   <iterate conjunction="," property="typeList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 
		INSERT INTO DP_SubscriptionList
		(UserID,
		SubType,
		Status,
		CityID,
		AddTIME)
		VALUES
		
		   <iterate conjunction="," property="subTypeList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		Mail_Queue
		(Email,
		Emailtitle,
		Emailbody,
		ADDDATE,
		UpdateDate,
		Rank,
		FromEmail,
		FromName,
		ReEmail,
		TemplateID,
		XTag
		)
		VALUES
		   <iterate conjunction="," property="emailEntryList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(ID,Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#id#,#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_ShopFeatureStatistics
		(ShopFeatureStatisticsId,
		CreatorId,
		CreateTime,
		ExpectedFeatures)
		VALUES
		(#entity.id#,#entity.creatorId#,#entity.createTime#,#entity.expectedFeatures#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
	
		INSERT INTO CI_MsgInfo
			(Type, trainId, Text, Link, name, StartTime)
		VALUES
			(1, 7, '10元团购抵用券免费领(此团购券只限手机客户端使用)', #link#, 'novicegift', #startTime#)
	       <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingMobile.CI_SceneryOrderDetail 
			(SerialID, UserID, Uuid, TotalPrice, PickUpPlace, OrderDate, PolicyName, EnableCancel, Status, ShopID, 
				Count, TravelDate, Guest, OtherGuest, Notices, ServicePhoneNo, ClientType)
		VALUES
			(#serialId#, #userId#, #uuid#, #totalPrice#, #pickUpPlace#, #orderDate#, #policyName#, #enableCancel#, #status#,
				#shopId#, #count#, #travelDate#, #guest#, #otherGuest#, #notices#, #servicePhoneNo#, #clientType#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
          MP_Staff_White_Paper(StaffId,serviceType,AddTime)
		VALUES
			(#staffId#,#serviceType#,now())
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Feedback
		(FeedbackId, 
		Content, 
		CreateTime, 
		CreatorId)
		VALUES
		(#entity.feedbackId#,#entity.content#,#entity.createTime#,#entity.creatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO MP_MerchantMessageStatistics
		(
            MessageId,
            IsNotified,
            CreateTime
		)
		VALUES
		(
            #entity.messageId#,
            #entity.isNotified#,
            #entity.createTime#
		)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_MessageToken
		(DeviceId, 
		Token, 
		IsValid, 
		CreateTime)
		VALUES
		(#entity.deviceId#,#entity.token#,#entity.isValid#,#entity.createTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_ShopFeatureStatistics
		(ShopFeatureStatisticsId,
		CreatorId,
		CreateTime,
		ExpectedFeatures)
		VALUES
		(#entity.id#,#entity.creatorId#,#entity.createTime#,#entity.expectedFeatures#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MP_UserProductAgreement(
        ShopAccountId,
        ProductId,
        AgreementId,
        IsAccepted
        )
        VALUES (
        #entity.shopAccountId#,
        #entity.productId#,
        #entity.agreementId#
        ,#entity.isAccepted#);
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Settle_Withdraw(ShopID, Amount, Status, AddTime, UpdateTime)
			VALUES (#shopId#, #amount#, #status#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO MOPay_Settle_Batch (BatchNumber, AddTime, UpdateTime)
			VALUES (#batchNumber#, NOW(), NOW())
		
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MOPay_Settle_Batch_Finance(BatchNumber, FinanceNumber)
        VALUES (#batchNumber#, #financeNumber#)
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO MOPay_Shop (ShopID, Status, AddTime, UpdateTime)
        VALUES
           <iterate conjunction="," property="shopIdList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact_Material (ShopContactID, MaterialPath, MaterialType,AddTime,UpdateTime)
			VALUES(
			#shopContactMaterialInfo.ShopContactID#,
			#shopContactMaterialInfo.path#,
			#shopContactMaterialInfo.type#,
			NOW(),
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
	
		INSERT INTO CI_Announcement 
			(Title, PageUrl, Utm, BeginTime, EndTime, ClientIDList, VersionList, CityIDList, Priority, Flag)
		VALUES
			(#annoucement.title#, #annoucement.pageUrl#, #annoucement.utm#, #annoucement.beginTime#, #annoucement.endTime#, '',
			 '', #annoucement.cityIdList#, #annoucement.priority#, 0)
		   <selectKey keyProperty="AnnouncementID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_AnnoucementClient 
			(AnnouncementID, ClientType, PicUrl, VersionList, SourceList, Flag)
		VALUES
			(#adaptor.annoucementId#, #adaptor.clientValue#, #adaptor.picUrl#, #adaptor.versionList#, #adaptor.sourceList#, 1)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingHotel.DP_OTAScenicSpotsOrderDetail
		(`OTAId`,`OTAOrderId`,`OrderName`,`ShopId`,`ScenicSpotId`,`dpid`,
		`OrderStatus`,`TotalPrice`,`UserId`, `OTAUserId`,`CurrencyCode`,
		`TicketAmount`,`TicketDeliveryDetail`,
		`OrderCreateDate`,`PolicyName`,`IsCancelable`,
		`TicketUseDate`,`ContactName`,
		`ContactTel`,`OtherGuest`,`Notice`, `ServicePhoneNo`,`ClientType`,`ClientVersion`,
		`AddTime`,`UpdateTime`) VALUES
		   <iterate conjunction="," property="orderDetail"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingHotel.DP_ScenicSpots 
		(ShopID,SceneryID,SID,CityID,CityName,SName,Status,AddTime,UpdateTime)
		VALUES
		   <iterate conjunction="," property="scenicSpotPOIPo"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_MovieRemind
        (MovieRemindID,UserID,MobileNo,RemindProductType,RemindProductId,RemindChannel,Status,AddDate,UpdateDate)
        VALUES
        (NULL,#userId#,#mobileNo#,#remindProductType#,#remindProductId#,#remindChannel#,0,now(),now())
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
		INSERT INTO API_Param 
			(ParamName, IsRequired, ParamDesc, APIID, CreateTime, UpdateTime, ParamType, BeginVer, EndVer, ParamStatus)
		VALUES
			(#mobileAPIParamDo.paramName#, #mobileAPIParamDo.isRequired#, #mobileAPIParamDo.paramDesc#, #mobileAPIParamDo.paramRefApi#,
			#mobileAPIParamDo.paramCreateTime#, #mobileAPIParamDo.paramUpdateTime#,#mobileAPIParamDo.paramType#, #mobileAPIParamDo.beginVer#, #mobileAPIParamDo.endVer#,#mobileAPIParamDo.paramStatus#) 
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
 
		INSERT INTO Model_Field 
			(FieldType, FieldRef, DataModel, FieldName, FieldDesc, FieldHash, CombinWay, FieldStatus, CreateTime, UpdateTime, CreateUser, BeginVer, EndVer)
		VALUES
			(#modelFieldDo.fieldType#, #modelFieldDo.fieldRef#, #modelFieldDo.modelRef#, #modelFieldDo.fieldName#, #modelFieldDo.fieldDesc#, #modelFieldDo.fieldHash#,
			 #modelFieldDo.combinWay#, #modelFieldDo.fieldStatus#, #modelFieldDo.fieldCreateTime#, #modelFieldDo.fieldUpdateTime#, #modelFieldDo.createUser#, #modelFieldDo.beginVer#, #modelFieldDo.endVer#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DP_RegionList(
		RegionID,
		OldID,
		RegionName,
		RegionAltName,
		CityID,
		RegionOrderID,
		ShopHits,
		RegionType,
		GLng,
		GLat,
		`Range`,
		ADDDATE,
		UpdateDate,
		ProvinceID,
		IsDefault,
		tmdRange,
		Address,
		ShopCount
		)VALUES(
		#regionListData.regionId#,
		#regionListData.oldId#,
		#regionListData.regionName#,
		#regionListData.regionAltName#,
		#regionListData.cityId#,
		#regionListData.regionOrderId#,
		#regionListData.shopHits#,
		#regionListData.regionType#,
		#regionListData.glng#,
		#regionListData.glat#,
		#regionListData.range#,
		#regionListData.addDate#,
		#regionListData.updateDate#,
		#regionListData.provinceId#,
		#regionListData.isDefault#,
		#regionListData.tmdRange#,
		#regionListData.address#,
		#regionListData.shopCount#)
		   <selectKey keyProperty="regionid" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DianPingMobile.CI_DownLoad 
			(TYPE,  SimpleDesc)
		VALUES
			(#Type#,  #SimpleDesc#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO CI_Announcement 
			(Title, PageUrl, BeginTime, EndTime, ClientIDList, 
			VersionList, CityIDList, Priority)
		VALUES
			(#Title#, #PageUrl#, #BeginTime#, #EndTime#, #ClientIDList#, 
			 #VersionList#, #CityIDList#, #Priority#)
		   <selectKey keyProperty="AnnouncementID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="orderId" resultClass="int"/>
  
    insert into TG_Order 
    	(UserID, 
    	 CityID, 
    	 DealGroupID, 
    	 DealID, 
    	 Quantity, 
    	 TotalAmount,
    	 PaymentAmount,
    	 MobileNo, 
    	 Status, 
    	 RefundStatus, 
    	 AddDate, 
    	 UpdateDate, 
    	 SuccessDate,
    	 PayChannelStatus,
    	 AddUserIP)
    values 
    	(#userId:INTEGER#, 
    	 #cityId:SMALLINT#, 
    	 #productGroupId:INTEGER#,
    	 #productId:INTEGER#, 
    	 #quantity:SMALLINT#, 
    	 #totalAmount:DECIMAL#, 
    	 #paymentAmount:DECIMAL#,
    	 #mobileNo:VARCHAR#, 
    	 #status:TINYINT#, 
    	 #refundStatus:TINYINT#, 
    	 now(),
    	 now(), 
    	 #successTime:TIMESTAMP#, 
    	 #payChannelStatus:VARCHAR#,
    	 #userIp:VARCHAR#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

    insert into TG_OrderTextInfo (
		OrderID,
		TextKey,
		TextValue,
		AddDate,
		UpdateDate)
    values (
		#orderID:INTEGER#,
		#textKey:VARCHAR#,
		#textValue:VARCHAR#,
		now(),
		now()
	)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
    INSERT INTO PCT_ProductBatchRefund
    	(ProductID,
		 ProductCode,
		 Status,
		 CityIDList,
		 BeginTime,
		 EndTime,
		 AddTime,
		 UpdateTime
    	)
    VALUES 
    	(#productId#, 
    	 #productCode#, 
    	 #status#, 
    	 #cityIdList#, 
    	 #beginTime#, 
    	 #endTime#, 
    	 now(), 
    	 now()
    	)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO TuanGou2010.TG_Charge 
			(UserID, 
			TotalAmount, 
			STATUS, 
			ADDDATE, 
			SuccessDate, 
			UserIP
			)
			VALUES
			(#userId#, 
			#totalAmount#, 
			#status#, 
			now(), 
			#successDate#, 
			#userIP#
			);
	    
	     <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		insert into PCT_UserHabit (ID, UserID, PayMethod,
		PaymentChannel,
		PayPlatform, Bank, Memo, CreateTime, UpdateTime)
		values (null,
		#userId:INTEGER#,
		#payMethod:TINYINT#,#paymentChannel:TINYINT#,
		#payPlatform:INTEGER#,
		#bank:VARCHAR#, #memo:VARCHAR#, now(), now())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

        insert into PCT_OrderGroupCoupon
        (OrderGroupID,
        DiscountID,
        DiscountCode,
        DiscountType,
        DiscountAmount,
        Status,
        AddTime,
        UpdateTime)
        values
        (#orderGroupID:INTEGER#,
        #discountID:INTEGER#,
        #discountCode:VARCHAR#,
        #discountType:TINYINT#,
        #discountAmount:Decimal#,
        0,
        NOW(),
        NOW())
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		    INSERT INTO PCT_UserChannelEvent
			(ChannelEventID,
			UserID,
			MobileNO,
			GUID,
			Status,
			AddDate,
			UpdateDate)
			VALUES
			(#channelEventID#,
			#userID#,
			#mobileNO#,
			#guid#,
			0,
			NOW(),
			NOW()
			)
	    
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO TuanGou2010.TG_Charge 
			(UserID, 
			TotalAmount, 
			STATUS, 
			ADDDATE, 
			SuccessDate, 
			UserIP
			)
			VALUES
			(#userId#, 
			#totalAmount#, 
			#status#, 
			now(), 
			#successDate#, 
			#userIP#
			);
	    
	     <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="orderItemId" resultClass="int"/>
  
    insert into TG_OrderItem 
    	   (OrderID, 
    	   UserID, 
    	   MobileNO,
    	   ProductGroupID, 
    	   ProductID, 
    	   Quantity, 
    	   TotalAmount, 
		   Status, 
		   RefundStatus, 
		   AddTime, 
		   ProductType,
		   ProductValue,
		   Platform,
		   ReceiveChannel,
		   UserIP,
		   CityID)
    values 
    	(#orderId:INTEGER#, 
    	 #userId:INTEGER#, 
    	 #mobileNo:VARCHAR#,
    	 #productGroupId:INTEGER#,
    	 #productId:INTEGER#, 
    	 #quantity:SMALLINT#, 
    	 #totalAmount:DECIMAL#, 
    	 #status:TINYINT#, 
    	 #refundStatus:TINYINT#, 
    	 now(), 
		 #productType:TINYINT#,
		 #productValue:DECIMAL#,
		 #platform:INTEGER#,
		 #receiveChannel:INTEGER#,
		 #userIp:VARCHAR#,
		 #cityId:SMALLINT#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO APP_PushMessage
               (appId,
				loginId,
				message,
				status,
				receivedDate,
				sentDate,
				lastRetryDate,
				retryTimes,
				token,
				remark) 
            VALUES 
               (#pushMessageDto.appId#,
				#pushMessageDto.loginId#,
				#pushMessageDto.message#,
				#pushMessageDto.status#,
				#pushMessageDto.receivedDate#,
				#pushMessageDto.sentDate#,
				#pushMessageDto.lastRetryDate#,
				#pushMessageDto.retryTimes#,
				#pushMessageDto.token#,
				#pushMessageDto.remark#);
        
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    insert into TuanGouPCT.PCT_ChannelMonitor 
    	   	 (PaymentChannel, 
    	   	  BeginDate, 
    	   	  EndDate, 
    	   	  ThresholdValue, 
    	   	  Status, 
    	   	  EmailStatus, 
    	   	  PhoneStatus, 
    	   	  EmailDate, 
    	   	  PhoneDate,
    	   	  StatusRank)
    	values 
    	(#paymentChannel:TINYINT#, 
    	 #beginDate:TIMESTAMP#, 
    	 #endDate:TIMESTAMP#,
    	 #thresholdValue:INTEGER#, 
    	 #status:TINYINT#,
    	 #emailStatus:TINYINT#,
    	 #phoneStatus:TINYINT#,
    	 #emailDate:TIMESTAMP#, 
    	 #phoneDate:TIMESTAMP#,
    	 #statusRank:TINYINT#)
	     <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	     INSERT INTO DP_InfoOperationLog (UserID, UserIP,LogInfo,AddDate,Comment)
		 VALUES(
		 #infoOperationLogData.userId#,
		 #infoOperationLogData.userIp#,
		 #infoOperationLogData.logInfo#,
		 #infoOperationLogData.addDate#,
		 #infoOperationLogData.comment#
		 );
		    <selectKey keyProperty="operationId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" resultClass="int"/>

        insert into Mon_UserBlackList
            (UserID,
            AddDate,
            Type,
            IsReleased)
        values
            (#userId:INTEGER#,
            now(),
            #type:INTEGER#,
            0)
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="notifyId" resultClass="int"/>

    insert into PCT_OrderNotify (
		OutBizID,
		Type,
		ProductCode,
		Status,
		AddTime,
		UpdateTime,
		Source,
        BizType
    )
    values (
		#outBizId:VARCHAR#,
		#type:INTEGER#,
		#productCode:INTEGER#,
		#status:INTEGER#,
		now(),
        now(),
		#source:INTEGER#,
        #bizType:INTEGER#
	)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			PicID,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#album.shopId#, 
			#album.name#,
			#album.picPath#, 
			#album.picType#,
			#album.picCount#,
			#album.isMain#,
			#album.picId#,
			#album.description#,
			#album.addTime#,
			#album.updateTime#,0);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO PCT_Discount (
			UserID,
			DiscountGroupID,
			DiscountCode,
			Status,
			BeginDate,
			EndDate,
			UsedDate,
			DiscountAmount,
			AddDate,
			UpdateDate
		) VALUES (
			#userID#,
			#discountGroupID#,
			#discountCode#,
			#status#,
			#beginDate#,
			#endDate#,
			#usedDate#,
			#discountAmount#,
			NOW(),
			NOW()
	    )
	
       <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO PCT_Discount (
			UserID,
			DiscountGroupID,
			DiscountCode,
			Status,
			BeginDate,
			EndDate,
			UsedDate,
			AddDate,
			UpdateDate,
            DiscountAmount
		) VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="deliverAddressId" resultClass="int"/>

    insert into TG_DeliverAddress (DeliverAddressID, UserID, Consignee, Address, PostCode, PhoneNo,
      IsDefault, Status, AddDate, UpdateDate, Province, City, District, DeliverTime, InvoiceTitle,
      NeedInvoice, Memo)
    values (#deliverAddressId:INTEGER#, #userId:INTEGER#, #consignee:VARCHAR#, #address:VARCHAR#,
      #postCode:VARCHAR#, #phoneNo:VARCHAR#, #isDefault:TINYINT#, #status:TINYINT#,
      now(), now(), #province:INTEGER#, #city:INTEGER#,
      #district:INTEGER#, #deliverTime:TINYINT#, #invoiceTitle:VARCHAR#, #needInvoice:TINYINT#,
      #memo:VARCHAR#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_WithdrawRefund
		(ID,
		WithdrawID,
		UserID,
		Amount,
		Status,
		AddTime,
		UpdateTime,
		SuccessTime)
		values
		(#id:INTEGER#,
		#withdrawId:VARCHAR#,
		#userId:INTEGER#,
		#amount:DECIMAL#,
		#status:INTEGER#,
		now(),
		now(),
		#successTime:TIMESTAMP#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		insert into PCT_UserHabit (UserID, PayMethod,
		PaymentChannel,
		PayPlatform, Bank, Memo, CreateTime, UpdateTime,UserType)
		values (#userId:INTEGER#,
		#payMethod:TINYINT#,#paymentChannel:TINYINT#,
		#payPlatform:INTEGER#,
		#bank:VARCHAR#, #memo:VARCHAR#, now(), now(),#userType:TINYINT#)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		    INSERT INTO PCT_OrderCouponBind
			(CouponID,
            OrderID,
            AddDate,
            UpdateDate,
            ProductCode)
			VALUES
			(#couponID#,
			#orderID#,
			NOW(),
			NOW(),
			#productCode#
			);
	    
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert into PCT_AccountFrozen (account_frozen_id, account_id, amount, status,
        create_time, modified_time,out_biz_id,memo,remain_amount)
        values (#accountFrozenId:VARCHAR#, #accountId:VARCHAR#, #amount:DECIMAL#, #status:TINYINT#, now(), now(),#outBizId:VARCHAR#,#memo:VARCHAR#,#remainAmount:DECIMAL#)
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
		insert into PCT_CheckDateJobConfig 
			(Status,
			CheckDate, 
			AddDate, 
			UpdateDate
			)values 
			(#status#,
			#checkDate#,
			NOW(),
			NOW())
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO PC_Picture (Url, Title, FlowerCount, FollowCount, Popularity, AddTime, LastTime, UserID, LastIP, Width, Height, ClientType, Status, Secret, StatusCode)
        VALUES (#pic.url#, #pic.title#, 0, 0, 0, NOW(), NOW(), #pic.userId#, #pic.lastIp#, #pic.width#, #pic.height#, #pic.clientType#, #pic.status#, #pic.secret#, #pic.statusCode#)
           <selectKey keyProperty="PicID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO PCT_RedEnvelopeRecord (
		    ID,
		    UserID,
		    DepartmentID,
		    OperatorID,
			TransID,
			Amount,
			Status,
			ExpiredTime,
			OperatorIp,
			Memo,
			AddTime,
			UpdateTime,
			AccountID,
			ErrorCode
		) VALUES (
		    #id#,
		    #userId#,
		    #departmentId#,
		    #operatorId#,
		    #transId#,
		    #amount#,
		    #status#,
		    #expiredTime#,
		    #operatorIp#,
		    #memo#,
			NOW(),
			NOW(),
			#accountId#,
			#errorCode#
	    )
	
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
	insert into PCT_AlipayAccountDetail 
		(TradeNO, 
		MerchantOutOrderNO,
		Balance, 
		Income, 
		Outcome,
		TradeRefundAmount, 
		ServiceFee, 
		ServiceFeeRatio, 
		TotalFee, 
		SourceType,
		TransCodeMsg,
		Memo,
		TransDate, 
		AddDate, 
		UpdateDate
		)values 
		(#tradeNO#, 
		#merchantOutOrderNO#,
		#balance#,
		#income#, 
		#outcome#, 
		#tradeRefundAmount#,
		#serviceFee#,
		#serviceFeeRatio#, 
		#totalFee#, 
		#sourceType#, 
		#transCodeMsg#, 
		#memo#,
		#transDate#,
		NOW(),
		NOW())
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
		insert into PCT_CheckDateJobConfig 
			(Status,
			CheckDate, 
			AddDate, 
			UpdateDate
			)values 
			(#status#,
			#checkDate#,
			NOW(),
			NOW())
  	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_SourceData
		(ID,GroupID,BUSINESS_CATEGORY,BUSINESS_DETAILED_CATEGORY,ALGORITHM,
		GL_DATE,ACCOUNT_AMOUNT,CREATION_DATE,Data_BeginDate,Data_EndDate
		)
		values
		(#id#,#groupID#,#businessCategory#,#businessDetailedCategory#,#algorithm#,
		#glDate#,#accountAmount#,now(),#beginDate#,#endDate#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    insert into PCT_EventProductUserLog (
		UserID,
		ProductID,
		UserValue,
		AddDate)
    values (
		#userID:INTEGER#,
		#productID:INTEGER#,
		#userValue:INTEGER#,
		now()
	)
       <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="notifyId" resultClass="int"/>

    insert into PCT_OrderNotify (
		OutBizID,
		Type,
		ProductCode,
		Status,
		AddTime,
		UpdateTime,
		Source,
        BizType,
        PaymentID
    )
    values (
		#outBizId:VARCHAR#,
		#type:INTEGER#,
		#productCode:INTEGER#,
		#status:INTEGER#,
		now(),
        now(),
		#source:INTEGER#,
        #bizType:INTEGER#,
        #paymentId:VARCHAR#
	)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		insert into PCT_UserPaySignLog (ID, SignNO, Mobile,
		Name,CertType,
		CertNO, CardNO, ValidDate, UserID, Status,
		ResultCode,Description,PayChannel,Platform,Bank,
		ProtocolCode,
		ItemCode, AddTime)
		values (null, #signNO:VARCHAR#, #mobile:VARCHAR#,
		#name:VARCHAR#,
		#certType:VARCHAR#, #certNO:VARCHAR#, #cardNO:VARCHAR#,
		#validDate:VARCHAR#, #userID:INTEGER#, #status:TINYINT#,
		#resultCode:VARCHAR#,
		#description:VARCHAR#, #payChannel:INTEGER#,#platform:INTEGER#,
		#bank:VARCHAR#,
		#protocolCode:VARCHAR#, #itemCode:VARCHAR#,
		now())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="couponId" resultClass="int"/>

		insert into TG_Coupon (CouponGroupID,UserID,BeginDate,EndDate,AddDate,UsedStatus,Status)
		values (#couponGroupId#,#userId#,#beginTime#,#endTime#,now(),0,1)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="couponId" resultClass="int"/>
  
		insert into TG_Coupon (CouponGroupID,UserID,CouponCode,BeginDate,EndDate,AddDate,UsedStatus,Status)
		values
		(#couponGroupId#,0,#batchCode#,#beginTime#,#endTime#,now(),0,1)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		    INSERT INTO PCT_OrderChannelEvent
			(MobileNO,
			OrderID,
			ChannelEventID,
			Status,
			AddDate,
			UpdateDate)
			VALUES
			(#mobileNO#,
			#orderID#,
			#channelEventID#,
			0,
			NOW(),
			NOW()
			);
	    
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

        insert into PCT_PaymentBatch(PaymentBatchID,BatchNO,AddTime,UpdateTime,NotifyTime) values (#paymentBatchId#,#batchNo#, Now(), Now(), #notifyTime#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
	insert into PCT_AlipayAccountDetail 
		(TradeNO, 
		MerchantOutOrderNO,
		Balance, 
		Income, 
		Outcome,
		TradeRefundAmount, 
		ServiceFee, 
		ServiceFeeRatio, 
		TotalFee, 
		SourceType,
		TransCodeMsg,
		Memo,
		TransDate, 
		AddDate, 
		UpdateDate
		)values 
		(#tradeNO#, 
		#merchantOutOrderNO#,
		#balance#,
		#income#, 
		#outcome#, 
		#tradeRefundAmount#,
		#serviceFee#,
		#serviceFeeRatio#, 
		#totalFee#, 
		#sourceType#, 
		#transCodeMsg#, 
		#memo#,
		#transDate#,
		NOW(),
		NOW())
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO APP_PushDevice 
                (appId,
                loginId,
                type,
                token,
                disabled,
                isLogin,
                lastRegisterDate,
                createdDate) 
            VALUES 
                (#deviceEntity.appId#,
                #deviceEntity.loginId#,
                #deviceEntity.type#,
                #deviceEntity.token#,
                #deviceEntity.disabled#,
                #deviceEntity.isLogin#,
                #deviceEntity.lastRegisterDate#,
                #deviceEntity.createdDate#);
        
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
		insert into PCT_CheckDateJobConfig 
			(Status,
			CheckDate, 
			AddDate, 
			UpdateDate
			)values 
			(#status#,
			#checkDate#,
			NOW(),
			NOW())
  	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
	         INSERT INTO DP_InfoPic(InfoID,UserID,FilePath,FileType,ADDDATE)
	         VALUES(
	         #infoPicData.infoId#,
	         #infoPicData.userId#,
	         #infoPicData.filePath#,
	         #infoPicData.fileType#,
	         #infoPicData.addDate#
	         );
	    
	       <selectKey keyProperty="infoPicId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_UserAccountAuditLog
		(ID,
		Income,
		Outcome,
		TransDate,
		AddDate
		)values
		(#id#,
		#income#,
		#outcome#,
		#transDate#,
		NOW())
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="deductionID" resultClass="int"/>

  	INSERT INTO TGHT_RefundDeduction
		(RefundID,
		RefundType,
		TotalAmount,
		ThirdPartyAmount,
		DeductionAmount,
		ThirdPartyPaymentMode,
		OrderID,
		DealID,
		Status,
		AdminID,
		AddDate,
		UpdateDate,
		Memo)
		VALUES
		(#refundID:INTEGER#,
		#refundType:TINYINT#,
		#totalAmount:DECIMAL#,
		#thirdPartyAmount:DECIMAL#,
		#deductionAmount:DECIMAL#,
		#thirdPartyPaymentMode:TINYINT#,
		#orderID:INTEGER#,
		#dealID:INTEGER#,
		#status:TINYINT#,
		#adminID:INTEGER#,
		#addDate:DATETIME#,
		#updateDate:DATETIME#,
		#memo:VARCHAR#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="orderGroupId" resultClass="int"/>
  
    insert into PCT_OrderGroup 
    	(UserID,
    	 TotalAmount,
    	 ThirdAmount,
    	 TotalNum,
    	 SuccessNum,
    	 Status,
    	 AddTime,
    	 UpdateTime,
         Source
    	)
    values 
    	(#userId:INTEGER#, 
    	 #totalAmount:DECIMAL#, 
    	 #thirdAmount:DECIMAL#, 
    	 #totalNum:INTEGER#, 
    	 #successNum:INTEGER#, 
    	 #status:TINYINT#, 
    	 now(),
    	 now(),
         #source:TINYINT#
      )
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="certificateId" resultClass="int"/>
  
    insert into TG_Certificate
    	(OrderID,
    	 OrderItemID,
    	 ProductType, 
    	 OutSystemID, 
    	 Certificate, 
		 Status,
		 AddTime)
    values
    	(#orderId:INTEGER#, 
    	 #orderItemId:INTEGER#,
    	 #productType:SMALLINT#, 
    	 #outSystemId:INTEGER#, 
    	 #certificate:VARCHAR#,
    	 #status:TINYINT#,
    	 now())
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="refundID" resultClass="int"/>
  
	    INSERT INTO TGHT_Refund
	    	(RefundType,
			 PayAuditID,
			 ThirdPartyAuditID,
			 TotalAmount,
			 ThirdPartyAmount,
			 ThirdPartyPaymentMode,
			 PaymentSerialNo,
			 OrderID,
			 UserID,
			 Status,
			 AdminID,
			 AddDate,
			 UpdateDate,
			 Memo,
			 Resultcode,
			 ResultText,
			 ReceiptIDs,
			 ResetStatus,
			 SettlementDate,
			 PCT_OrderRefundID
	    	)
	    VALUES 
	    	(#refundType:TINYINT#, 
	    	 #payAuditID:INTEGER#, 
	    	 #thirdPartyAuditID:INTEGER#, 
	    	 #totalAmount:DECIMAL#,
	    	 #thirdPartyAmount:DECIMAL#,
	    	 #thirdPartyPaymentMode:TINYINT#,
	    	 #paymentSerialNo:VARCHAR#,
	    	 #orderID:INTEGER#, 
	    	 #userID:INTEGER#, 
	    	 #status:TINYINT#,
	    	 #adminID:INTEGER#, 
	    	 NOW(),
	    	 NOW(),
			 #memo:VARCHAR#,
			 #resultCode:VARCHAR#,
    		 #resultText:VARCHAR#,
    		 #receiptIDs:VARCHAR#,
    	     #resetStatus:TINYINT#,
   			 #settlementDate:TIMESTAMP#,
    	     #pctOrderRefundID:INTEGER#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		insert into PCT_UserPaySign (ID, SignNO, Mobile,
		Name,CertType,
		CertNO,
		CardNO, ValidDate, UserID, Status, ResultCode,Description,PayChannel,
		Platform,Bank,ProtocolCode,
		ItemCode, AddTime, UpdateTime)
		values (null,
		#signNO:VARCHAR#, #mobile:VARCHAR#,
		#name:VARCHAR#, #certType:VARCHAR#,
		#certNO:VARCHAR#, #cardNO:VARCHAR#,
		#validDate:VARCHAR#,
		#userID:INTEGER#, #status:TINYINT#,
		#resultCode:VARCHAR#,
		#description:VARCHAR#, #payChannel:INTEGER#,#platform:INTEGER#,
		#bank:VARCHAR#, #protocolCode:VARCHAR#, #itemCode:VARCHAR#,
		now(),now())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
	insert into PCT_AlipayAccountDetail 
		(TradeNO, 
		MerchantOutOrderNO,
		Balance, 
		Income, 
		Outcome,
		TradeRefundAmount, 
		ServiceFee, 
		ServiceFeeRatio, 
		TotalFee, 
		SourceType,
		TransCodeMsg,
		Memo,
		TransDate, 
		AddDate, 
		UpdateDate
		)values 
		(#tradeNO#, 
		#merchantOutOrderNO#,
		#balance#,
		#income#, 
		#outcome#, 
		#tradeRefundAmount#,
		#serviceFee#,
		#serviceFeeRatio#, 
		#totalFee#, 
		#sourceType#, 
		#transCodeMsg#, 
		#memo#,
		#transDate#,
		NOW(),
		NOW())
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	   
			INSERT INTO DP_Info (InfoID, InfoTitle, Description, BeginDate,EndDate,ShopId,IsGroupLevel,CityId,
			Addtime,AddUser,Lasttime,LastUser,LastIP, ShopType,ShopFullName,AddUserName,LastUserName,VerifyStatus)
			VALUES(
			#infoData.infoId#,
			#infoData.infoTitle#,
			#infoData.description#,
			#infoData.beginDate#,
			#infoData.endDate#,
			#infoData.shopId#,
			#infoData.isGroupLevel#,
			#infoData.cityId#,
			#infoData.addtime#,
			#infoData.addUser#,
			#infoData.lasttime#,
			#infoData.lastUser#,
			#infoData.lastIp#,
			#infoData.shopType#,
			#infoData.shopFullName#,
			#infoData.addUserName#,
			#infoData.lastUserName#,
			#infoData.verifyStatus#);
		
		   <selectKey keyProperty="infoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DP_InfoFollowNote (InfoID, UserID, NoteBody, AddTime, LastTime, LastIP, VerifyStatus)
			VALUES (
				#infoId#,
				#userId#,
				#noteBody#,
				#addTime#,
				#lastTime#,
				#lastIp#,
				#verifyStatus#
			)
		
		   <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO PC_PicBiz
			(   <include refid="fullColumns"/>
)
		VALUES
			   <iterate conjunction="," property="picBizList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DP_ShopPicModificationLog(
			ID,
			PicID,
			ShopID,
			OldValue,
			OldValueInfo,
			NewValue,
			NewValueInfo,
			ActionType,
			EditorID,
			EditorName,
			UserID,
			UserName,
			CreateTime
		)VALUES(
			#picTransferEvent.id#,
			#picTransferEvent.picId#,
			#picTransferEvent.shopId#,
			#picTransferEvent.oldValue#,
			#picTransferEvent.oldValueInfo#,
			#picTransferEvent.newValue#,
			#picTransferEvent.newValueInfo#,
			#picTransferEvent.actionType#,
			#picTransferEvent.editorId#,
			#picTransferEvent.editorName#,
			#picTransferEvent.userId#,
			#picTransferEvent.userName#,
			#picTransferEvent.createTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO PCT_DiscountGroup (
			DiscountGroupTitle,
			DiscountGroupDesc,
			DiscountAmount,
			Type,
			Status,
			Memo,
			DepartID,
			DepartMemo,
			BudgetMemo,
			BeginDate,
			EndDate,
			AddDate,
			UpdateDate
		) VALUES (
			#discountGroupTitle#,
			#discountGroupDesc#,
			#discountAmount#,
			#type#,
			#status#,
			#memo#,
			#departID#,
			#departMemo#,
			#budgetMemo#,
			#beginDate#,
			#endDate#,
			NOW(),
			NOW()
	    )
	
       <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO PCT_DiscountMapping (
		    DiscountID,
		    DiscountGroupID,
		    OutBizID,
		    OutBizType,
		    UserID,
		    MobileNO,
		    GUID,
		    Status,
			AddDate,
			UpdateDate
		) VALUES (
		    #discountID#,
		    #discountGroupID#,
		    #outBizID#,
		    #outBizType#,
		    #userID#,
		    #mobileNO#,
		    #guid#,
		    #status#,
			NOW(),
			NOW()
	    )
	
       <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO PCT_DiscountRule (
		    DiscountGroupID,
		    Type,
		    Status,
		    Memo,
			AddDate,
			UpdateDate
		) VALUES (
		    #discountGroupID#,
		    #type#,
		    #status#,
		    #memo#,
			NOW(),
			NOW()
	    )
	
       <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
		INSERT INTO PCT_DiscountRuleDetail (
		    DiscountRuleID,
		    Value,
		    Status,
		    Memo,
			AddDate,
			UpdateDate
		) VALUES (
		    #discountRuleID#,
		    #value#,
		    #status#,
		    #memo#,
			NOW(),
			NOW()
	    )
	
       <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_AccountCheckLog
		(ID,
		Amount,
		Type,
		PaymentChannel,
		ProductCode,
		TransDate,
		AddDate
		)values
		(#id#,
		#amount#,
		#type#,
		#paymentChannel#,
		#productCode#,
		#transDate#,
		NOW())
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_Advance
		(ID,
		ProductID,
		ProductCode,
		Amount,
		VoucherType,
		PaymentChannel,
		BeginDate,
		EndDate,
		AddDate
		)values
		(#id#,
		#productId#,
		#productCode#,
		#amount#,
		#voucherType#,
		#paymentChannel#,
		#beginDate#,
		#endDate#,
		NOW())
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="orderItemId" resultClass="int"/>
  
    insert into TG_OrderItem 
    	   (OrderID, 
    	   UserID, 
    	   MobileNO,
    	   ProductGroupID, 
    	   ProductID, 
    	   Quantity, 
    	   TotalAmount, 
		   Status, 
		   RefundStatus, 
		   AddTime, 
		   ProductType,
		   ProductValue,
		   Platform,
		   ReceiveChannel,
		   UserIP,
		   CityID)
    values 
    	(#orderId:INTEGER#, 
    	 #userId:INTEGER#, 
    	 #mobileNo:VARCHAR#,
    	 #productGroupId:INTEGER#,
    	 #productId:INTEGER#, 
    	 #quantity:SMALLINT#, 
    	 #totalAmount:DECIMAL#, 
    	 #status:TINYINT#, 
    	 #refundStatus:TINYINT#, 
    	 now(), 
		 #productType:TINYINT#,
		 #productValue:DECIMAL#,
		 #platform:INTEGER#,
		 #receiveChannel:INTEGER#,
		 #userIp:VARCHAR#,
		 #cityId:SMALLINT#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

    	insert into PCT_OrderRefund
    	   	  (ID,
			   OrderID,
			   ReceiptList,
			   UserID,
			   Status,
			   PlatForm,
			   RefundSource,
			   AddTime,
			   UpdateTime,
			   UserIP,
			   Amount,
			   AccountAmount,
			   ThirdPartyAmount,
			   CouponAmount,
			   SuccessTime,
			   RefundType,
			   RefundBizType,
			   CallBackUrl,
			   OutBizID,
			   ThirdPartyOrderID,
			   ProductCode,
			   Memo,
			   ErrorCode,
			   NeedDeduction,
			   PaymentChannel,
   			   ChannelEventAmount,
			   DiscardAmount,
			   ReturnAmount,
			   AdminID,
        DiscountAmount,
        RedEnvelopeAmount,
        GiftCardAmount)
    	values
    	(#id:INTEGER#,
    	 #orderId:INTEGER#,
    	 #receiptList:VARCHAR#,
    	 #userId:INTEGER#,
    	 #status:INTEGER#,
    	 #platForm:INTEGER#,
    	 #refundSource:INTEGER#,
    	 now(),
    	 now(),
    	 #userIp:VARCHAR#,
    	 #amount:DECIMAL#,
    	 #accountAmount:DECIMAL#,
    	 #thirdPartyAmount:DECIMAL#,
    	 #couponAmount:DECIMAL#,
    	 #successTime:TIMESTAMP#,
		 #refundType:TINYINT#,
		 #refundBizType:TINYINT#,
		 #callBackUrl:VARCHAR#,
		 #outBizId:VARCHAR#,
		 #thirdPartyOrderID:VARCHAR#,
		 #productCode:TINYINT#,
		 #memo:VARCHAR#,
		 #errorCode:VARCHAR#,
		 #needDeduction:TINYINT#,
		 #paymentChannel:TINYINT#,
	   	 #channelEventAmount:DECIMAL#,
	     #discardAmount:DECIMAL#,
	     #returnAmount:DECIMAL#,
	     #adminID:INTEGER#,
        #discountAmount:DECIMAL#,
        #redEnvelopeAmount:DECIMAL#,
        #giftCardAmount:DECIMAL#)
  	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" resultClass="int"/>
  
    insert into Mon_UserBlackList 
    	(UserID, 
    	 AddDate, 
    	 Type, 
    	 IsReleased)
    values 
    	(#userId:INTEGER#, 
    	 now(),
    	 #type:INTEGER#,
    	 0)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
    insert into PCT_ThirdPartOrder (ID, OrderID,ThirdPartyUserID,ThirdPartyUserName,Status, Certificates, Quantity, NotifyStatus, ThirdPartyType,PaymentChannel,
      AddTime, UpdateTime, SuccessTime, ThirdPartyOrderCode, MerchantDpID, TotalAmount,Memo,NotifyUrl,ThirdPartyPlatform)
    values (#id:INTEGER#, #orderId:INTEGER#,#thirdPartyUserId:VARCHAR#,#thirdPartyUserName:VARCHAR#,#status:TINYINT#,
      #certificates:VARCHAR#, #quantity:SMALLINT# , #notifyStatus:TINYINT#, #thirdPartyType:TINYINT#, #paymentChannel:TINYINT# ,#addTime:TIMESTAMP#,
      #updateTime:TIMESTAMP#, #successTime:TIMESTAMP#,#thirdPartyOrderCode:VARCHAR#,#merchantDpId:INTEGER#,#totalAmount:DECIMAL#,#memo:VARCHAR#,#notifyUrl:VARCHAR#,#thirdPartyPlatform:VARCHAR#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="auditID" resultClass="int"/>
  
    	insert into TG_UserAccountAudit 
    	   	 (UserID, 
    	   	  ReferID, 
    	   	  Amount, 
    	   	  IsIncome, 
    	   	  Type, 
    	   	  Memo, 
    	   	  ADDDATE, 
    	   	  PaymentChannel, 
    	   	  RelatedAuditID,
    	   	  ThirdPartySerialNo, 
    	   	  UserIP)
    	values 
    	(#userID:INTEGER#, 
    	 #referID:INTEGER#, 
    	 #amount:DECIMAL#,
    	 #isIncome:INTEGER#, 
    	 #type:TINYINT#,
    	 #memo:VARCHAR#,
    	 now(),
    	 #paymentChannel:TINYINT#,
    	 #relatedAuditID:INTEGER#, 
    	 #thirdPartySerialNo:VARCHAR#,
    	 #userIP:VARCHAR#)
  	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
    INSERT INTO PCT_BatchRefund
    	(BatchNO,
  		 OrderRefundID,
		 Status,
		 ResultCode,
		 Description,
		 AddTime,
		 UpdateTime
    	)
    VALUES 
    	(#batchNO:VARCHAR#, 
    	 #orderRefundID:INTEGER#, 
    	 #status:TINYINT#, 
    	 #resultCode:VARCHAR#, 
    	 #description:VARCHAR#, 
    	 now(), 
    	 now()
    	)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    insert into PCT_BatchAuditEngineMapping (EngineControlID, BatchNO, OutBatchNO, OrderString, ThirdPartySerialNO, Memo,
      AddTime, UpdateTime, NotifyStatus,ErrorCode)
    values (#engineControlId:VARCHAR#, #batchNo:VARCHAR#, #outBatchNo:VARCHAR#, #orderString:VARCHAR#, #thirdPartySerialNo:VARCHAR#, #memo:VARCHAR#,
      #addTime:DATETIME#, now(), #notifyStatus:TINYINT#,#errorCode:VARCHAR#)
	     <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="auditID" resultClass="int"/>
  
    	insert into TG_UserAccountAudit 
    	   	 (UserID, 
    	   	  ReferID, 
    	   	  Amount, 
    	   	  IsIncome, 
    	   	  Type, 
    	   	  Memo, 
    	   	  ADDDATE, 
    	   	  PaymentChannel, 
    	   	  RelatedAuditID,
    	   	  ThirdPartySerialNo, 
    	   	  UserIP)
    	values 
    	(#userID:INTEGER#, 
    	 #referID:INTEGER#, 
    	 #amount:DECIMAL#,
    	 #isIncome:INTEGER#, 
    	 #type:TINYINT#,
    	 #memo:VARCHAR#,
    	 now(),
    	 #paymentChannel:TINYINT#,
    	 #relatedAuditID:INTEGER#, 
    	 #thirdPartySerialNo:VARCHAR#,
    	 #userIP:VARCHAR#)
  	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="notifyId" resultClass="int"/>

    insert into PCT_OrderNotify (
		OutBizID,
		Type,
		ProductCode,
		Status,
		AddTime,
		UpdateTime,
		Source,
        BizType
    )
    values (
		#outBizId:VARCHAR#,
		#type:INTEGER#,
		#productCode:INTEGER#,
		#status:INTEGER#,
		now(),
        now(),
		#source:INTEGER#,
        #bizType:INTEGER#
	)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_AccountCheckLog
		(ID,
		Amount,
		Type,
		PaymentChannel,
		ProductCode,
		TransDate,
		AddDate
		)values
		(#id#,
		#amount#,
		#type#,
		#paymentChannel#,
		#productCode#,
		#transDate#,
		NOW())
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DP_PromoFollowNote (PromoID, UserID, NoteBody, AddTime, LastTime, LastIP, VerifyStatus)
			VALUES(
			#promoId#,
			#userId#,
			#noteBody#,
			#addTime#,
			#lastTime#,
			#lastIp#,
			#verifyStatus#);
		

		   <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DP_PromoFollowNoteLog (FollowNoteID, AddTime, UserIP, Comment, UserID, LogType)
			VALUES(
			#followNoteId#,
			#addTime#,
			#userIp#,
			#comment#,
			#userId#,
			#logType#);
		

		   <selectKey keyProperty="logId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO PC_FollowNote 
			(PicID,UserID,NoteBody,AddDate,IP,OrigUserID,PicTitle,Status)	
		Value 
			(#followNote.picId#,#followNote.userId#,#followNote.noteBody#,#followNote.addDate#,
			#followNote.ip#,#followNote.origUserId#,#followNote.picTitle#,#followNote.status#)
		   <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		    INSERT INTO PCT_OrderChannelEvent
			(MobileNO,
			OrderID,
			ChannelEventID,
			Status,
			AddDate,
			UpdateDate)
			VALUES
			(#mobileNO#,
			#orderID#,
			#channelEventID#,
			0,
			NOW(),
			NOW()
			);
	    
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>
  
    	insert into PCT_ConsumeDetail 
    	   	  (ID, 
			   Amount, 
			   Type,
			   ProductCode,
			   PaymentChannel,
			   AddDate,
			   BeginDate,
			   EndDate)
    	values 
    	(#id#, 
    	 #amount#,
		 #type#,
		 #productCode#,
		 #paymentChannel#,
    	 NOW(),
    	 #beginDate#,
    	 #endDate#)
  	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_SourceData
		(ID,GroupID,BUSINESS_CATEGORY,BUSINESS_DETAILED_CATEGORY,ALGORITHM,
		GL_DATE,ACCOUNT_AMOUNT,CREATION_DATE,Data_BeginDate,Data_EndDate
		)
		values
		(#id#,#groupID#,#businessCategory#,#businessDetailedCategory#,#algorithm#,
		#glDate#,#accountAmount#,now(),#beginDate#,#endDate#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into PCT_UserAccountLog
		(ID,
		Balance,
		Frozen,
		TransDate,
		AddDate
		)values
		(#id#,
		#balance#,
		#frozen#,
		#transDate#,
		#addDate#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO PC_Picture (Url, Title, FlowerCount, FollowCount, Popularity, AddTime, LastTime, UserID, LastIP, Width, Height, ClientType, Status, Secret, StatusCode)
        VALUES (#pic.url#, #pic.title#, 0, 0, 0, NOW(), NOW(), #pic.userId#, #pic.lastIp#, #pic.width#, #pic.height#, #pic.clientType#, 1, 0, 0)
           <selectKey keyProperty="picId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    insert into TG_ThirdPartyPaymentAudit (UserID, ThirdPartySerialNO, PaymentType,
      ReferID, PaymentChannel, OrderString, Bank, Amount, Status, AddDate, UserIP, PaymentSource,
      IsVerified, Sign, PartnerID, MessageType)
    values ( #userId:INTEGER#, #thirdPartySerialNO:VARCHAR#,
      #paymentType:TINYINT#, #referId:INTEGER#, #paymentChannel:TINYINT#, #orderString:VARCHAR#,
      #bank:VARCHAR#, #amount:DECIMAL#, #status:TINYINT#, now(), #userIP:VARCHAR#,
      #paymentSource:TINYINT#, #isVerified:TINYINT#, #sign:VARCHAR#, #partnerId:VARCHAR#,
      #messageType:TINYINT#)
	     <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    insert into TG_ThirdPartyPaymentAudit (UserID, ThirdPartySerialNO, PaymentType,
      ReferID, PaymentChannel, OrderString, Bank, Amount, Status, AddDate, UserIP, PaymentSource,
      IsVerified, Sign, PartnerID, MessageType)
    values (#userId:INTEGER#, #thirdPartySerialNO:VARCHAR#,
      #paymentType:TINYINT#, #referId:INTEGER#, #paymentChannel:TINYINT#, #orderString:VARCHAR#,
      #bank:VARCHAR#, #amount:DECIMAL#, #status:TINYINT#, now(), #userIP:VARCHAR#,
      #paymentSource:TINYINT#, #isVerified:TINYINT#, #sign:VARCHAR#, #partnerId:VARCHAR#,
      #messageType:TINYINT#)
	     <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_RsCalloutEvent (
		shop_id,
		event_type,
		status,
		eventmessage,
		scheduled_time,
		send_time,
		priority,
		create_by,
		created_time,
		same_number,
		current_phone_number,
		dial_pre_wait,
		dial_post_wait)
		VALUES (
		#shopID#,
		#eventType#,
		#status#,
		#eventMsg#,
		#scheduledTime#,
		now(),
		#priority#,
		#createBy#,
		now(),
		#sameNumber#,
		#currentPhoneNumber#,
		#dialPreWait#,
		#dialPostWait#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_RsCalloutEvent (
		shop_id,
		event_type,
		status,
		eventmessage,
		scheduled_time,
		send_time,
		retry_times,
		retry_message,
		priority,
		create_by,
		created_time,
		same_number,
		init_task_id,
		current_phone_number,
		dial_pre_wait,
		dial_post_wait)
		select shop_id, event_type, 10, eventmessage,
		#scheduledTime#, now(),
		retry_times + 1, #retryMessage#,
		priority, create_by, created_time, same_number,
		init_task_id, #currentPhoneNumber#, dial_pre_wait, dial_post_wait
		from
		YY_RsCalloutEvent
		where id =
		#id#
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupCode
        (Code,DealID,UserID,Status,AddDate)
        VALUES(#code#,#dealId#,#userId#,1,Now());
           <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyStatus (
			VerifySn
		)
		VALUES (
			#verifySn#
		)
		   <selectKey keyProperty="verifyStatusID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_StockLock (
			LockID,
			DealGroupID,
			DealID,
			Quantity,
			Status,
			AddDate,
			LastDate
		)
		VALUES (
			#lockID#,
			#dealGroupID#,
			#dealID#,
			#quantity#,
			#status#,
			NOW(),
			NOW()
		)
		   <selectKey keyProperty="stockLockID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO Eim_ContractOverduePayment
        (
          opportunityId,
          addDate
        )
        VALUES
        (
          #opportunityId#,
          #addDate#
        )
        

           <selectKey resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO AD_City
        (
          cityId,
          regionId,
          regionName,
          cityName,
          cityPinYin,
          status
        )
        VALUES
        (
          #cityEntity.cityId#,
          #cityEntity.regionId#,
          #cityEntity.regionName#,
          #cityEntity.cityName#,
          #cityEntity.cityPinYin#,
          #cityEntity.status#
        )
        

           <selectKey keyProperty="cityId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountHistory (
		ReceiptAccountID,
		Amount,
		HistoryType,
		ReferID,
		Title,
		AddDate
		)
		VALUES (
		#receiptAccountID#,
		#amount#,
		#historyType#,
		#referID#,
		#title#,
		#addDate#
		)
		   <selectKey keyProperty="historyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyOperationLog (
		OperationType,
		DealProductType,
		ReceiptProductID,
		ReceiptID,
		SerialNumber,
		Amount,
		ShopID,
		ShopAccountID,
		Result,
		UserIP,
		AddDate,
		Memo
		)
		VALUES (
		#operationType#,
		#dealProductType#,
		#receiptProductID#,
		#receiptID#,
		#serialNumber#,
		#amount#,
		#shopID#,
		#shopAccountID#,
		#result#,
		#userIP#,
		NOW(),
		#memo#
		)
		   <selectKey keyProperty="logID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_CreditScoreLog (
		cellphone,
        creditscore_increment,
        record_id,
        client_uuid,
        user_id,
		create_by,
		created_time,
		update_by,
		updated_time )
		VALUES (
		#cellphone#,
        #creditScoreIncrement#,
        #recordId#,
        #clientUUID#,
        #userId#,
		#createBy#,
		#createTime#,
		#updateBy#,
		#updateTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_ReviseArrive (
		record_id,
        user_id,
        client_uuid,
        arrive_time,
        num_people,
        consumption,
		create_by,
		created_time,
		update_by,
		updated_time,
		remark
		)
		VALUES (
		#recordID#,
        #userID#,
        #clientUUID#,
        #arriveTime#,
        #peopleCount#,
        #consumption#,
		#createBy#,
		#createTime#,
		#updateBy#,
		#updateTime#,
		#remark#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_WaitressPay (
        phone_number,
        total_amout,
        remaining_amout,
        create_by,
        created_time,
        update_by,
        updated_time)
        VALUES (
        #phoneNumber#,
        #totalAmount#,
        #remainAmount#,
        #createBy#,
        #createTime#,
        #updateBy#,
        #updateTime#)
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BillingRecord (
			contract_id,
			billing_time,
			money,
			drawer,
	 		create_by, 
	 		created_time, 
	 		update_by, 
	 		updated_time)
	 	VALUES (
	 		#contractID#,
	 		#billingTime#,
	 		#money#,
	 		#drawer#,
	 		#createBy#,
	 		#createTime#,
	 		#updateBy#,
	 		#updateTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BusinessRecord (
			shop_id,
			amount,
			charge,
			ref_record_id,
			remark,
			type_code,
			balance_prior,
	 		create_by, 
	 		created_time, 
	 		update_by, 
	 		updated_time)
	 	VALUES (
	 		#shopID#,
	 		#amount#,
	 		#charge#,
	 		#refRecordID#,
	 		#remark#,
	 		#typeCode#,
	 		#balancePrior#,
	 		#createBy#,
	 		#createTime#,
	 		#updateBy#,
	 		#updateTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert into Report_Job_Job (Title, DatasourceName, JobSql, Serie, AddTime, UpdateTime)
        values (#title#, #datasource#, #sql#, #serie#, NOW(), NOW())
           <selectKey keyProperty="JobID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_MessageRecord (
		message_type, 
		send_status, 
		record_id, 
		shop_id, 
		create_by, 
		created_time
		)
		VALUES (
		#messageType#,
		#sendStatus#,
		#bookingRecordID#,
		#shopID#,
		#createBy#,
		#createTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupCode
        (Code,DealID,UserID,Status,AddDate)
        VALUES(#code#,#dealId#,#userId#,1,Now());
           <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyOperationLog (
		OperationType,
		DealProductType,
		ReceiptProductID,
		ReceiptID,
		SerialNumber,
		Amount,
		ShopID,
		ShopAccountID,
		Result,
		UserIP,
		AddDate,
		Memo
		)
		VALUES (
		#operationType#,
		#dealProductType#,
		#receiptProductID#,
		#receiptID#,
		#serialNumber#,
		#amount#,
		#shopID#,
		#shopAccountID#,
		#result#,
		#userIP#,
		NOW(),
		#memo#
		)
		   <selectKey keyProperty="logID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_ReceiptRefundDetail (ReceiptID,Status,PayRefundID,ApplyDate,FinishDate)
		values 
		(#receiptId#,1,null,now(),null)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        DP_ExtraReview
        (UserID,ShopID,Score1,Score2,Score3,Score4,AvgPrice,ReviewBody,TransPark,DishTags,ShopTags,ADDTIME,LastTime,LastIP,CityID,ShopType,ShopGroupID,
        POWER,PicTotal,QualityScore,ReviewBodyLength, WordDifference, Star,
        SearchableTags,CTU,Status)
        VALUES
        (#extraReview.userId#,#extraReview.shopId#,#extraReview.score1#,#extraReview.score2#,#extraReview.score3#,#extraReview.score4#,#extraReview.avgPrice#,#extraReview.reviewBody#,#extraReview.transPark#,
        #extraReview.dishTags#,#extraReview.shopTags#,   <dynamic>
      <isNull property="extraReview.addTime"/>
      <isNotNull property="extraReview.addTime"/>
      <isNull prepend="," property="extraReview.lastTime"/>
      <isNotNull prepend="," property="extraReview.lastTime"/>
   </dynamic>
,#extraReview.lastIP#,#extraReview.cityId#,#extraReview.shopType#,#extraReview.shopGroupId#,
        #extraReview.power#,0,#extraReview.qualityScore#, #extraReview.reviewBodyLength#,#extraReview.wordDifference#, #extraReview.star#,
        #extraReview.searchableTags#,#extraReview.ctu#,1)
           <selectKey keyProperty="reviewId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_ReviewModificationLog(ReviewID, ShopID, UserID, OldStatus, NewStatus, OldContent, NewContent, ActionType, EditorID, EditorName, LogTime, Comment)
		VALUES(#reviewId#, #shopId#, #userId#, #oldStatus#, #newStatus#, #oldContent#, #newContent#, #actionType#, #editorId#, #editorName#, Now(), #comment#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO	RT_DPReservation (
			shop_id,
			record_id,
			table_id,
			create_time,
			deal_status
		) VALUES (
			#shopID#,
			#recordID#,
			#tableID#,
			#createTime#,
			#dealStatus#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptRefundDetail 
        (
        	RefundRecordId, 
        	ReceiptId, 
        	CreateDate, 
        	UpdateDate
        ) VALUES (
            #refundRecordId#, 
            #receiptId#,
            now(), 
            now()
        );
           <selectKey keyProperty="DetailId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MRB_CRMRelation
        (
          orderId,
          opportunityId,
          contractId,
          contractNo,
          contractStatus,
          salesId,
          addTime,
          updateTime
        )
        VALUES
        (
          #crmRelationEntity.orderId#,
          #crmRelationEntity.opportunityId#,
          #crmRelationEntity.contractId#,
          #crmRelationEntity.contractNo#,
          #crmRelationEntity.contractStatus#,
          #crmRelationEntity.salesId#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MRB_Order
        (
          shopType,
          mainCategoryId,
          amount,
          salesCityId,
          submitTime,
          sourceType,
          status,
          addTime,
          updateTime
        )
        VALUES
        (
          #orderEntity.shopType#,
          #orderEntity.categoryId#,
          #orderEntity.amount#,
          #orderEntity.salesCityId#,
          #orderEntity.submitTime#,
          #orderEntity.sourceType#,
          #orderEntity.status#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccount (
		UserID,
		CardProductID,
		DealProductType,
		PrivateAccountID,
		SerialNumber,
		ExpireDate,
		AddDate,
		UpdateDate,
		CardName,
		Status,
		MobileNo
		)
		VALUES (
		#userID#,
		#cardProductID#,
		#dealProductType#,
		#privateAccountID#,
		#serialNumber#,
		#expireDate#,
		#addDate#,
		NOW(),
		#cardName#,
		#status#,
		#mobileNo#
		)
		   <selectKey keyProperty="receiptAccountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyRecord (
		DealProductType,
		ReceiptProductID,
		ReceiptID,
        UserID,
		Amount,
		ShopID,
		ShopAccountID,
		VerifyChannel,
		DeviceID,
		Status,
		AddDate
		)
		VALUES (
		#dealProductType#,
		#receiptProductID#,
		#receiptID#,
        #userID#,
		#amount#,
		#shopID#,
		#shopAccountID#,
		#verifyChannel#,
		#deviceID#,
		#status#,
		NOW()
		)
		   <selectKey keyProperty="receiptVerifyRecordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingDealPool (
		record_id,
		shop_id,
		created_time,
		master_status,
		operate_id
		)
		VALUES (
		#recordID#,
		#shopID#,
		#createTime#,
		#masterStatus#,
		#operateID#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

         INSERT INTO
            YY_ShopSwitchConfig
            (
               shop_id,
               type,
               status,
               sub_status,
               context,
               create_by,
               created_time,
               update_by,
               update_time
            )
        VALUES
            (
               #shopId#,
               #type#,
               #status#,
               #subStatus#,
               #context#,
               #createBy#,
               #createTime#,
               #updateBy#,
               #updateTime#
            )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountRefundRecord (
		ReceiptAccountID,
		ReceiptAccountRevokingAmount,
		RefundAmount,
		Status,
		RefundType,
		RefundID,
		AddDate
		)
		VALUES (
		#receiptAccountID#,
		#receiptAccountRevokingAmount#,
		#refundAmount#,
		#status#,
		#refundType#,
		#refundID#,
		NOW()
		)
		   <selectKey keyProperty="recordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ThirdPartyReceiptRefundApplication (
		OrderID,
		UserID,
		Amount,
		Platform,
		ReceiptList,
		RefundType,
		UserIp,
		UserMemo,
		RefundSource,
		RefundBizType,
		ProductCode,
		Status,
		AddDate
		)
		VALUES (
		#orderId#,
		#userId#,
		#amount#,
		#platform#,
		#receiptList#,
		#refundType#,
		#userIp#,
		#userMemo#,
		#refundSource#,
		#refundBizType#,
		#productCode#,
		0,
		now()
		)
		   <selectKey keyProperty="applicationId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_CallbackEvent (
		task_id,
		init_task_id,
		type,
		shop_id,
		event,
		event_time,
		status,
		schedule_time,
		processor,
		event_message,
		created_time)
		VALUES (
		#taskId#,
		#initTaskId#,
		#type#,
		#shopId#,
		#event#,
		#eventTime#,
		#status#,
		#scheduleTime#,
		null,
		#message#,
		now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_BookingCashEncashment
        (biz_id, shop_id, status, total_cash, deduct_cash, encash_cash, created_by, created_time, updated_by, updated_time)
        VALUES
        (#bizId#, #shopId#, #status#, #totalCash#, #deductCash#, #encashCash#, #createdBy#, NOW(), #updatedBy#, NOW())
           <selectKey keyProperty="shopId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into YY_AuditRecord(type, main_status, sub_status, create_by, create_time, description)
		values(#type#, #mainStatus#, #subStatus#, #createBy#, #createTime#, #description#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into YY_AuditLog(record_id, status, create_by, create_time, action, description)
		values(#recordId#, #status#, #createBy#, #createTime#, #action#, #description#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into YY_AuditContent(record_id, `key`, `value`, create_time, create_by)
		values(#recordId#, #key#, #value#, #createTime#, #createBy#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT 
		INTO YY_BookingRecordRebates (
			record_id,
			content_type,
			content_detail_type,
			short_display,
			title,
			description,
			url,
			source_id,
			cash_rebate,
			create_by,
			created_time,
			update_by,
			updated_time
		) VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Receipt (
		ReceiptID,UserID, DealGroupID, DealID, OrderID,
		SerialNumber, TCode, ReceiptType, MobileNo, Status, RefundID, AddDate,
		LastDate, VendorShopID, DealSalePrice
		)
		VALUES (
		#receiptID#,
		#userID#,
		#dealGroupID#,
		#dealID#,
		#orderID#,
		#serialNumber#,
		#tcode#,
		#receiptType#,
		#mobileNo#,
		#status#,
		#refundID#,
		now(),
		now(),
		#vendorShopID#,
		#dealSalePrice#
		)
		   <selectKey keyProperty="receiptID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Receipt (
        UserID, DealGroupID, DealID, OrderID,
        SerialNumber, TCode, ReceiptType, MobileNo, Status, RefundID, AddDate,
        LastDate, VendorShopID, DealSalePrice
        )
        VALUES (
        #userID#,
        #dealGroupID#,
        #dealID#,
        #orderID#,
        #serialNumber#,
        #tcode#,
        #receiptType#,
        #mobileNo#,
        #status#,
        #refundID#,
        now(),
        now(),
        #vendorShopID#,
        #dealSalePrice#
        )
           <selectKey keyProperty="receiptID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingRecord (
			serialized_id,
			shop_id,
			user_id,
			device_id,
			client_uuid,
			name,
			gender,
			phone,
			booker_name,
			booker_gender,
			booker_phone,
			num_people,
			position_type,
			compartment_selected,
			status,
			consumption,
			booking_time,
			inform_way,
			visibility,
			create_by,
			created_time,
			update_by,
			updated_time,
			apiversion,
			master_status,
			detail_status,
			deal_id,
			source,
			created_source,
			dimensional_code,
			rebate_consumption,
			is_thunder,
			record_type,
			appear_time,
	        cancel_id,
			openapi_key,
            user_remark
		) VALUES (
			#serializedID#,
			#shopID#,
			#userID#,
			#deviceID#,
			#clientUUID#,
			#name#,
			#gender#,
			#phone#,
			#bookerName#,
			#bookerGender#,
			#bookerPhone#,
			#peopleNumber#,
			#positionType#,
			#compartmentSelected#,
			#status#,
			#consumption#,
			#bookingTime#,
			#informWay#,
			#visibility#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#updateTime#,
			#version#,
			#masterStatusCode#,
			#detailStatusCode#,
			#dealId#,
			#source#,
			#createdSource#,
			#dimensionalCode#,
			#rebateConsumption#,
			#isThunder#,
			#recordType#,
			#appearTime#,
	        (SELECT COUNT(*) FROM (SELECT id FROM YY_BookingRecord WHERE phone=#phone#) tmp) % 10,
			#openApiKey#,
            #userRemark#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		DP_Review
		(UserID,ShopID,Score1,Score2,Score3,Score4,AvgPrice,TransPark,DishTags,ShopTags,ADDTIME,LastTime,LastIP,CityID,ShopType,ShopGroupID,
		POWER,PicTotal,QualityScore,ReviewBodyLength, WordDifference, Star,
		SearchableTags,CTU)
		VALUES
		(#review.userId#,#review.shopId#,#review.score1#,#review.score2#,#review.score3#,#review.score4#,#review.avgPrice#,#review.transPark#,
		#review.dishTags#,#review.shopTags#,   <dynamic>
      <isNull property="review.addTime"/>
      <isNotNull property="review.addTime"/>
      <isNull prepend="," property="review.lastTime"/>
      <isNotNull prepend="," property="review.lastTime"/>
   </dynamic>
,#review.lastIP#,#review.cityId#,#review.shopType#,#review.shopGroupId#,
		#review.power#,0,#review.qualityScore#, #review.reviewBodyLength#,#review.wordDifference#, #review.star#,
		#review.searchableTags#,#review.ctu#)
		   <selectKey keyProperty="reviewId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_BookingPayDetail
		(
			shop_id,
			user_id,
			operate_id,
			operate_type,
			amount,
			balance,
			type,
			biz_id,
			status,
			receipt_id,
			create_by,
			created_time
		)
		VALUES
		(
			#shopId#,
			#userId#,
			#operateId#,
			#operateType#,
			#amount#,
			#balance#,
			#type#,
			#bizId#,
			#status#,
			#receiptId#,
			#createBy#,
			#createTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    INSERT INTO YY_RechargeInvoiceRecord 
	    (invoice_status, invoice_type, invoice_amount, shop_id, shop_name, biz_id, invoice_title,
	     create_by, created_time, update_by, updated_time)
		VALUES
		('10','10', #amount#, #shopID#, #shopName#, #bizID#, #invoiceTitle#,
		 'DML', NOW(), 'DML', NOW())
	       <selectKey keyProperty="invoice_apply_id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingPaySequence
			()
		VALUES
			()
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_StationLetters
		(biz_id,letter_type,message,read_status,create_by,created_time,update_by,updated_time)
		VALUES (
			#bizID#,
			#letterType#,
			#message#,
			#readStatus#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#updateTime#
			)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountHistory (
		ReceiptAccountID,
		Amount,
		HistoryType,
		ReferID,
		Title,
		AddDate
		)
		VALUES (
		#receiptAccountID#,
		#amount#,
		#historyType#,
		#referID#,
		#title#,
		#addDate#
		)
		   <selectKey keyProperty="historyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountRefundRecord (
		ReceiptAccountID,
		ReceiptAccountRevokingAmount,
		RefundAmount,
		Status,
		RefundType,
		RefundID,
		AddDate
		)
		VALUES (
		#receiptAccountID#,
		#receiptAccountRevokingAmount#,
		#refundAmount#,
		#status#,
		#refundType#,
		#refundID#,
		NOW()
		)
		   <selectKey keyProperty="recordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptPoolBatchFetch (
		Memo,
		AddDate
		)
		VALUES (
		#memo#,
		NOW()
		)
		   <selectKey keyProperty="receiptPoolBatchFetchID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptResetBatch
        (AdminID,AddTime) VALUES (#adminId#,Now());
           <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyOperationLog (
		OperationType,
		DealProductType,
		ReceiptProductID,
		ReceiptID,
		SerialNumber,
		Amount,
		ShopID,
		ShopAccountID,
		Result,
		UserIP,
		AddDate,
		Memo
		)
		VALUES (
		#operationType#,
		#dealProductType#,
		#receiptProductID#,
		#receiptID#,
		#serialNumber#,
		#amount#,
		#shopID#,
		#shopAccountID#,
		#result#,
		#userIP#,
		NOW(),
		#memo#
		)
		   <selectKey keyProperty="logID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MRB_OrderItem
        (
          orderId,
          groupId,
          cityId,
          productId,
          priceId,
          amount,
          shopId,
          newShopId,
          contentId,
          displayTemplateId,
          status,
          addTime,
          updateTime
        )
        VALUES
        (
          #orderItemEntity.orderId#,
          #orderItemEntity.groupId#,
          #orderItemEntity.cityId#,
          #orderItemEntity.productId#,
          #orderItemEntity.priceId#,
          #orderItemEntity.amount#,
          #orderItemEntity.shopId#,
          #orderItemEntity.newShopId#,
          #orderItemEntity.contentId#,
          #orderItemEntity.displayTemplateId#,
          #orderItemEntity.status#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MRB_OrderItemDuration
        (
          orderItemId,
          beginTime,
          endTime,
          status,
          CpmAmount,
          addTime,
          updateTime
        )
        VALUES
        (
          #durationEntity.orderItemId#,
          #durationEntity.beginTime#,
          #durationEntity.endTime#,
          #durationEntity.status#,
          #durationEntity.cpmAmount#,
          now(),
          now()
        )
        

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_JournalVoucher (
		OrderID,
		OrderType,
		DealProductType,
		JournalVoucherType,
		UserID,
		ThirdPartyID,
		DealGroupID,
		DealID,
		DealSnapshotID,
		Details,
		Quantity,
		AddDate,
		ReferID,
		Status,
		TotalAmount
		)
		VALUES (
		#orderID#,
		#orderType#,
		#dealProductType#,
		#journalVoucherType#,
		#userID#,
		#thirdPartyID#,
		#dealGroupID#,
		#dealID#,
		#dealSnapshotID#,
		#details#,
		#quantity#,
		#addDate#,
		#referID#,
		2,
		#totalAmount#
		)
		   <selectKey keyProperty="voucherID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyStatus (
			VerifySn
		)
		VALUES (
			#verifySn#
		)
		   <selectKey keyProperty="verifyStatusID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_RechargeInvoiceRecord(
		invoice_apply_date,invoice_release_date,
		invoice_status,invoice_type,invoice_amount,
		invoice_title,invoice_no,shop_id,shop_name,
		express_id,biz_id,
		create_by, created_time, update_by, updated_time
		)
		VALUES (
		#invoiceApplyDate#,#invoiceReleaseDate#,
		#invoiceStatus#,#invoiceType#,#invoiceAmount#,
		#invoiceTitle#,#invoiceNo#,#shopId#,#shopName#,
		#expressId#,#bizId#,
		#createBy#,#createTime#,#updateBy#,#updateTime#
		)
		   <selectKey keyProperty="invoiceApplyId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_WaitressRecord (
        task_id,
        phone_number,
        record_id,
        shop_id,
        amout,
        create_by,
        created_time,
        update_by,
        updated_time)
        VALUES (
        #taskId#,
        #phoneNumber#,
        #recordId#,
        #shopId#,
        #amount#,
        #createBy#,
        #createTime#,
        #updateBy#,
        #updateTime#)
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	 	INSERT INTO YY_AccountConfig (
	 		shop_id,
	 		contract_phone,
	 		shop_phone,
	 		contract_name,
	 		bill_title,
	 		bill_address,
	 		bill_receiver_name,
	 		bill_receiver_phone,
	 		pre_pay_money,
	 		create_by, 
	 		created_time, 
	 		update_by, 
	 		updated_time,
	 		description)
		VALUES (
			#shopId#,
			#contractPhone#,
			#shopPhone#,
			#contractName#,
	 		#billTitle#,
	 		#billAddress#,
	 		#billReceiverName#,
	 		#billReceiverPhone#,
	 		#prePayMoney#,
			#createBy#, 
			#createTime#, 
			#updateBy#, 
			#updateTime#,
			#descriptionInfo#)
		   <selectKey keyProperty="shopId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	 	INSERT INTO YY_BillingConfig (
	 		using_contract_config,
	 		bill_title,
	 		bill_address,
	 		payment_director_phone,
	 		payment_executor_phone,
	 		create_by, 
	 		created_time, 
	 		update_by, 
	 		updated_time)
		VALUES (
			#usingContractConfig#,
			#billTitle#,
			#billAddress#,
			#paymentDirectorPhone#,
			#paymentExecutorPhone#,
			#createBy#, 
			#createTime#, 
			#updateBy#, 
			#updateTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BillingConfigShop (
			shop_id,
			billing_config_id,
			create_by,
			created_time)
		VALUES (
			#shopId#,
			#billingConfigId#,
			#createBy#,
			#createTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingPaySequence
			()
		VALUES
			()
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_MonthlyBill (
			shop_id,
			year,
			month,
			status,
			consumption,
			commission_rate,
			paid,
			actual_payment,
			create_by,
			created_time,
			update_by,
			updated_time,
			replenish)
		VALUES (
			#shopID#,
			#year#,
			#month#,
			#status#,
			#consumption#,
			#commissionRate#,
			#paid#,
			#actualPayment#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#updateTime#,
			#replenish#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingReAssignDealPool (
		record_id, shop_id, created_time )
		VALUES (#recordId#, #shopId#, NOW())
		   <selectKey keyProperty="recordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupDetail
        (ReceiptGroupCodeID,ReceiptID,AddDate)
        VALUES
           <iterate open="(" conjunction="," property="receiptIDs" close=")"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_ReceiptRefundDetail (ReceiptNewID,Status,PayRefundID,ApplyDate,FinishDate)
		values 
		(#receiptNewId#,1,null,now(),null)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_Refund (OrderID,RefundAmount,ApplyDate,Type)
		values 
		(#orderId#,#refundAmount#,now(),#type#)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_ActivityItemInfo (activity_id, item_title,
		title_bg, content_bg, create_by, created_time, update_by, updated_time)
		VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_Notifications
		(phone,type,title,content,read_status,create_by,created_time,update_by,updated_time)
		VALUES (
			#phone#,
			#type#,
			#title#,
			#content#,
			#readStatus#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#updateTime#
			)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_BookingPaySequence
        ()
        VALUES
        ()
           <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		ZS_FollowNote(MainNoteId ,NoteType ,UserId ,NoteBody ,IP ,OrigUserId ,MainNoteTitle ,AddDate ,UpdateDate , GrandpaId, Power, VerifyStatus, UserType)
		VALUES
		(#reviewFollowNote.mainNoteId#,#reviewFollowNote.noteType#,#reviewFollowNote.userId#,#reviewFollowNote.noteBody#,#reviewFollowNote.ip#,#reviewFollowNote.origUserId#,
		#reviewFollowNote.mainNoteTitle#,#reviewFollowNote.addDate#,#reviewFollowNote.addDate#,#reviewFollowNote.grandpaId#,#reviewFollowNote.power#,#reviewFollowNote.verifyStatus#, #reviewFollowNote.userType#)
		   <selectKey keyProperty="FollowNoteID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingRecord (
		serialized_id,
		shop_id,
		user_id,
		device_id,
		client_uuid,
		name,
		gender,
		phone,
		num_people,
		position_type,
		compartment_selected,
		status,
		consumption,
		booking_time,
		inform_way,
		visibility,
		create_by,
		created_time,
		update_by,
		updated_time )
		VALUES (
		#serializedID#,
		#shopID#,
		#userID#,
		#deviceID#,
		#clientUUID#,
		#name#,
		#gender#,
		#phone#,
		#peopleNumber#,
		#positionType#,
		#compartmentSelected#,
		#status#,
		#consumption#,
		#bookingTime#,
		#informWay#,
		#visibility#,
		#createBy#,
		#createTime#,
		#updateBy#,
		#updateTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO	RT_DPReservation (
			shop_id,
			record_id,
			table_id,
			create_time,
			deal_status
		) VALUES (
			#shopID#,
			#recordID#,
			#tableID#,
			#createTime#,
			#dealStatus#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_FeedBack(
			UserName,
			UserEmail,
			MailTO,
			FeedTitle,
			FeedComments,
			FeedAdddate,
			FeedSend,
			SendTitle,
			SendComments,
			SendDate,
			FeedType,
			LastAdminId,
			UserPhone,
			ReferId,
			ReferUserId,
			ReferShopId,
			UserId,
			FeedGroupId,
			IsCheck,
			CauseType,
			EmailType
		)VALUES(
			#feedBack.userName#,
			#feedBack.userEmail#,
			#feedBack.mailTO#,
			#feedBack.feedTitle#,
			#feedBack.feedComments#,
			#feedBack.feedAdddate#,
			#feedBack.feedSend#,
			#feedBack.sendTitle#,
			#feedBack.sendComments#,
			#feedBack.sendDate#,
			#feedBack.feedType.type#,
			#feedBack.lastAdminId#,
			#feedBack.userPhone#,
			#feedBack.referId#,
			#feedBack.referUserId#,
			#feedBack.referShopId#,
			#feedBack.userId#,
			#feedBack.feedGroupId#,
			#feedBack.isCheck#,
			#feedBack.causeType.type#,
			#feedBack.emailType#)
		   <selectKey keyProperty="feedId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountOrder (
		ReceiptAccountID,
		OrderID,
		Quantity,
		OrderAmount,
		ReceiptAmount,
		UsedAmount,
		RefundedAmount,
		ReceiptBalance,
		LockVersion
		)
		VALUES (
		#receiptAccountID#,
		#orderID#,
		#quantity#,
		#orderAmount#,
		#receiptAmount#,
		#usedAmount#,
		#refundedAmount#,
		#receiptBalance#,
		#lockVersion#
		)
		   <selectKey keyProperty="receiptAccountOrderID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountOrder (
		ReceiptAccountID,
		OrderID,
		Quantity,
		OrderAmount,
		ReceiptAmount,
		UsedAmount,
		RefundedAmount,
		ReceiptBalance,
		LockVersion
		)
		VALUES (
		#receiptAccountID#,
		#orderID#,
		#quantity#,
		#orderAmount#,
		#receiptAmount#,
		#usedAmount#,
		#refundedAmount#,
		#receiptBalance#,
		#lockVersion#
		)
		   <selectKey keyProperty="receiptAccountOrderID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_LotteryAward (
			award_type,
			award_code,
			available_start_date,
			available_end_date,
			award_status,
			created_time,
			updated_time
		) VALUES (
			#awardType#,
			#awardCode#,
			#availableStartDate#,
			#availableEndDate#,
			#awardStatus#,
			#updatedTime#,
			#createdTime#
		)
		   <selectKey keyProperty="awardId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_Notifications
		(phone,type,title,content,read_status,create_by,created_time,update_by,updated_time)
		VALUES (
			#phone#,
			#type#,
			#title#,
			#content#,
			#readStatus#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#updateTime#
			)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BookingShopRange (
		shop_id,
		year_id,
		month_id,
		record_count,
		range_num,
		create_time,
		city_id
		)
		VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_ShopReceiptStatus (
			status, 
			shop_id, 
			create_by, 
			create_time,
			update_by,
			update_time
			)
			VALUES (
			#status#,
			#shopID#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#createTime#
			)
	
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_BookingPayAction
		(
			biz_id,
			shop_id,
			account_id,
			amount,
			type,
			status,
			create_by,
			created_time
		)
		VALUES
		(
			#bizId#,
			#shopId#,
			#accountId#,
			#amount#,
			#type#,
			#status#,
			#createBy#,
			#createTime#
		)
		   <selectKey keyProperty="bizId" resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_OperationLog (
			operate_type,
			operate_id,
			operate_action,
			operate_detail,
			operator,
			source,
			create_by,
			created_time)
		VALUES (
			#operateType#,
			#operateID#,
			#operateAction#,
			#operateDetail#,
			#operator#,
			#source#,
			#createBy#,
			#createTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        YY_BookingPayDetail
        (
            shop_id,
            user_id,
            operate_id,
            operate_type,
            amount,
            balance,
            type,
            biz_id,
            status,
            receipt_id,
            comments,
            create_by,
            created_time
        )
        VALUES
        (
            #shopId#,
            #userId#,
            #operateId#,
            #operateType#,
            #amount#,
            #balance#,
            #type#,
            #bizId#,
            #status#,
            #receiptId#,
            #comment#,
            #createBy#,
            #createTime#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccount (
		UserID,
		CardProductID,
		DealProductType,
		PrivateAccountID,
		SerialNumber,
		ExpireDate,
		AddDate,
		UpdateDate,
		CardName,
		Status,
		MobileNo
		)
		VALUES (
		#userID#,
		#cardProductID#,
		#dealProductType#,
		#privateAccountID#,
		#serialNumber#,
		#expireDate#,
		#addDate#,
		NOW(),
		#cardName#,
		#status#,
		#mobileNo#
		)
		   <selectKey keyProperty="receiptAccountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyRecord (
		DealProductType,
		ReceiptProductID,
		ReceiptID,
		Amount,
		ShopID,
		ShopAccountID,
		VerifyChannel,
		DeviceID,
		Status,
		AddDate
		)
		VALUES (
		#dealProductType#,
		#receiptProductID#,
		#receiptID#,
		#amount#,
		#shopID#,
		#shopAccountID#,
		#verifyChannel#,
		#deviceID#,
		#status#,
		NOW()
		)
		   <selectKey keyProperty="receiptVerifyRecordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifySuccessLog 
			(ReceiptID,DealGroupID,DealID,AccountID,AddTime,VerifyChannel,DeviceID)
			  VALUES
			(#receiptVerifySuccessLog.receiptID#,
			#receiptVerifySuccessLog.dealGroupID#,
			#receiptVerifySuccessLog.dealID#,
			#receiptVerifySuccessLog.accountID#,Now(),
			#receiptVerifySuccessLog.verifyChannel#,
			#receiptVerifySuccessLog.deviceId#
			);		
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_JournalVoucher
		(OrderID, OrderType, DealProductType,
		JournalVoucherType, UserID, ThirdPartyID,
		DealGroupID, DealID,DealSnapshotID,
		Details, Quantity,AddDate,ReferID,TotalAmount, status)
		values
		(#orderId#,
		#orderType#, #dealProductType#, #journalVoucherType#, #userId#,
		#thirdPartyId#, #dealGroupId#,
		#dealId#,#dealSnapshotId#, #details#, #quantity#, now(),
		#referId#, #totalAmount#, 0)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Receipt_New (
		ReceiptID, UserID, DealGroupID, DealID,DealProductType, BeginDate, EndDate,
		OrderID,
		SerialNumber, ReceiptType, MobileNo, Status, AddDate, LastDate, OrderItemID, ReservedString
		)
		VALUES (
		#receiptID#,
		#userID#,
		#dealGroupID#,
		#dealID#,
		#dealProductType#,
		#beginDate#,
		#endDate#,
		#orderID#,
		#serialNumber#,
		#receiptType#,
		#mobileNo#,
		#status#,
		now(),
		now(),
		#orderItemID#,
		#reservedString#
		)
		   <selectKey keyProperty="receiptNewID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyRecord (
		DealProductType,
		ReceiptProductID,
		ReceiptID,
		Amount,
		ShopID,
		ShopAccountID,
		VerifyChannel,
		DeviceID,
		Status,
		AddDate,
		UserId
		)
		VALUES (
		#dealProductType#,
		#receiptProductId#,
		#receiptId#,
		#amount#,
		#shopID#,
		#shopAccountId#,
		#verifyChannel#,
		#deviceId#,
		0,
		NOW(),
		#userId#
		)
		   <selectKey keyProperty="receiptVerifyRecordId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_ReceiptVerifySuccessLog
		(ReceiptID, DealGroupID, DealID,
		AccountID, AddTime, VerifyChannel, DeviceID, ShopAccountId, CompanyId,
		SerialNumber)
		values
		(#receiptId#, #dealGroupId#, #dealId#, #accountId#,
		now(), #verifyChannel#, #deviceId#, #shopAccountId#, #companyId#,
		#serialNumber#)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_CreditScoreLog (
		cellphone,
        creditscore_increment,
        record_id,
        client_uuid,
        user_id,
		create_by,
		created_time,
		update_by,
		updated_time )
		VALUES (
		#cellphone#,
        #creditScoreIncrement#,
        #recordId#,
        #clientUUID#,
        #userId#,
		#createBy#,
		#createTime#,
		#updateBy#,
		#updateTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_WebBannerConfig
		(priority,status,begin_time,end_time,title,page_type,page_url,banner_url,description,create_time,create_by,update_time,update_by)
		VALUES
		(#priority#,#status#,#beginTime#,#endTime#,#title#,#pageType#,#pageUrl#,#bannerUrl#,#description#,NOW(),#createBy#,NOW(),#updateBy#)
		    <selectKey type="post" keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		DP_ReviewJobCompete
		    (JobID, CompeteTime)
		VALUES
		    (#jobId#, NOW())
		   <selectKey keyProperty="competitorId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_ReviewKeyWord
		(ReviewID, DishTag, UnknownDishTag, AddTime, LastTime)
		VALUES
		(#reviewKeyWord.reviewId#,#reviewKeyWord.dishTag#, #reviewKeyWord.unknownDishTag#, NOW(), NOW())
		   <selectKey keyProperty="reviewId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_ReviewDeleteLog
		  (ReviewID,UserID,ShopID,Score1,Score2,Score3,Score4,AvgPrice,TransPark,DishTags,ShopTags,Hits,ADDTIME,LastTime,LastIP,LogTime,LogReason,LogUser,ReviewBodyLength,Star,ReviewBody,ShopGroupID,ShopType)
          SELECT ReviewID,UserID,ShopID,Score1,Score2,Score3,Score4,AvgPrice,TransPark,DishTags,ShopTags,Hits,ADDTIME,LastTime,LastIP,NOW(),#logReason# AS LogReason ,#userId# AS LogUser,ReviewBodyLength,Star,ReviewBody,ShopGroupID,ShopType
          FROM  DP_Review WHERE  ReviewID=#reviewId#
		   <selectKey keyProperty="reviewLogId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BankAccount
		(
			
			account_no,
			account_name,
			account_type,
			bank_name,
			branch_name,
			bank_qualifiedname,
			bank_number,
			province,
			city,
			account_status,
			created_time,
			created_by,
			updated_time,
			updated_by,
			contract_global_id
		)
		VALUES
		(
			
			#accountNo#,
			#accountName#,
			#accountType#,
			#bankName#,
			#branchName#,
			#bankQualifiedName#,
			#bankNumber#,
			#province#,
			#city#,
			#accountStatus#,
			NOW(),
			#operator#,
			NOW(),
			#operator#,
			#contractGlobalID#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BankAccountShop
		(
			shop_id,
			account_id,
			approval_status,
			created_time,
			created_by,
			updated_time,
			updated_by
		)
		VALUES
		(
			#shopId#,
			#accountId#,
			30,
			NOW(),
			#operator#,
			NOW(),
			#operator#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_JobConfigInfo(
			job_name,
			job_bean,
			description,
			cron_script,
			status,
			level,
			owner,
			owner_id,
			owner_phone,
			create_by,
			create_time,
			update_by,
			update_time,
			arguments,
			mail_receivers,
			job_jar_name
		) VALUES (
			#jobName#,
			#jobBeanName#,
			#desc#,
			#cronScript#,
			#status#,
			#level#,
			#owner#,
			#ownerID#,
			#ownerPhone#,
			#createBy#,
			NOW(),
			#updateBy#,
			NOW(),
			#arguments#,
			#mailReceivers#,
			#jarName#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BankAccount
		(
			id,
			account_no,
			account_name,
			account_type,
			bank_name,
			branch_name,
			bank_qualifiedname,
			bank_number,
			province,
			city,
			account_status,
			created_time,
			created_by,
			updated_time,
			updated_by
		)
		VALUES
		(
			#id#,
			#accountNo#,
			#accountName#,
			#accountType#,
			#bankName#,
			#branchName#,
			#bankQualifiedName#,
			#bankNumber#,
			#province#,
			#city#,
			#accountStatus#,
			NOW(),
			#operator#,
			NOW(),
			#operator#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BankAccountShop
		(
			shop_id,
			account_id,
			approval_status,
			created_time,
			created_by,
			updated_time,
			updated_by
		)
		VALUES
		(
			#shopId#,
			#accountId#,
			10,
			NOW(),
			#operator#,
			NOW(),
			#operator#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_ActivityItemInfo (activity_id, item_title,
		title_bg, content_bg, create_by, created_time, update_by, updated_time)
		VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_CC_BookingEvent (
		shop_id,
		event_type,
		status,
		eventmessage,
		scheduled_time,
		send_time,
		priority,
		create_by,
		created_time,
		same_number)
		VALUES (
		#shopID#,
		#eventType#,
		#status#,
		#eventMsg#,
		#scheduledTime#,
		now(),
		#priority#,
		#createBy#,
		now(),
		#sameNumber#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_CC_BookingEvent (
		shop_id,
		event_type,
		status,
		eventmessage,
		scheduled_time,
		send_time,
		retry_times,
		priority,
		create_by,
		created_time,
		same_number, 
		init_task_id)
		select shop_id, event_type, 10, eventmessage, #scheduledTime#, now(), retry_times + 1,
		       priority, create_by, created_time, same_number, init_task_id
		from YY_CC_BookingEvent
		where id = #id#
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_OperationLog (
		operate_type,
		operate_id,
		operate_action,
		operate_detail,
		operator,
		create_by,
		created_time)
		VALUES (
		#operateType#,
		#operateID#,
		#operateAction#,
		#operateDetail#,
		#operator#,
		#createBy#,
		#createTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_JournalVoucher (
		OrderID,
		OrderType,
		DealProductType,
		JournalVoucherType,
		UserID,
		ThirdPartyID,
		DealGroupID,
		DealID,
		DealSnapshotID,
		Details,
		Quantity,
		AddDate,
		ReferID,
		TotalAmount,
        Status
		)
		VALUES (
		#orderID#,
		#orderType#,
		#dealProductType#,
		#journalVoucherType#,
		#userID#,
		#thirdPartyID#,
		#dealGroupID#,
		#dealID#,
		#dealSnapshotID#,
		#details#,
		#quantity#,
		#addDate#,
		#referID#,
		#totalAmount#,
        #status#
		)
		   <selectKey keyProperty="voucherID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccount (
		UserID,
		CardProductID,
		DealProductType,
		PrivateAccountID,
		SerialNumber,
		ExpireDate,
		AddDate,
		UpdateDate,
		CardName,
		Status,
		MobileNo
		)
		VALUES (
		#userID#,
		#cardProductID#,
		#dealProductType#,
		#privateAccountID#,
		#serialNumber#,
		#expireDate#,
		#addDate#,
		NOW(),
		#cardName#,
		#status#,
		#mobileNo#
		)
		   <selectKey keyProperty="receiptAccountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupDetail
        (ReceiptGroupCodeID,ReceiptID,AddDate)
        VALUES
           <iterate conjunction="," property="receiptIDs"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyRecord (
		DealProductType,
		ReceiptProductID,
		ReceiptID,
		Amount,
		ShopID,
		ShopAccountID,
		VerifyChannel,
		DeviceID,
		Status,
		AddDate
		)
		VALUES (
		#dealProductType#,
		#receiptProductID#,
		#receiptID#,
		#amount#,
		#shopID#,
		#shopAccountID#,
		#verifyChannel#,
		#deviceID#,
		#status#,
		NOW()
		)
		   <selectKey keyProperty="receiptVerifyRecordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifySuccessLog 
			(ReceiptID,DealGroupID,DealID,AccountID,AddTime,VerifyChannel,DeviceID)
			  VALUES
			(#receiptVerifySuccessLog.receiptID#,
			#receiptVerifySuccessLog.dealGroupID#,
			#receiptVerifySuccessLog.dealID#,
			#receiptVerifySuccessLog.accountID#,Now(),
			#receiptVerifySuccessLog.verifyChannel#,
			#receiptVerifySuccessLog.deviceId#
			);		
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_RefundRecord 
        (
        	OrderID, 
        	UserID, 
        	RefundID, 
        	DealGroupID, 
        	DealID, 
        	ProductType, 
        	RefundType, 
        	Quantity, 
        	Amount, 
        	Memo, 
        	Platform, 
        	Status, 
        	RefundSource, 
        	CreateDate, 
        	UpdateDate
        ) VALUES (
            #orderId#, 
            #userId#, 
            #refundId#, 
            #dealGroupId#, 
            #dealId#, 
            #productType#, 
            #refundType#, 
            #quantity#, 
            #amount#, 
            #memo#, 
            #platform#, 
            #status#, 
            #refundSource#, 
            now(), 
            now()
        );
           <selectKey keyProperty="refundRecordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_ShopReceiptStatus (
			status, 
			shop_id, 
			create_by, 
			create_time,
			update_by,
			update_time
			)
			VALUES (
			#status#,
			#shopID#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#createTime#
			)
	
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_PrivilegeInfo (
        shop_id,
        privilege,
        description,
        created_time,
        updated_time
        )
        VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupCode
        (Code,DealID,UserID,Status,AddDate)
        VALUES(#code#,#dealId#,#userId#,1,Now());
           <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupDetail
        (ReceiptGroupCodeID,ReceiptID,AddDate)
        SELECT #receiptGroupCodeId#,ReceiptID,Now()
        FROM TG_Receipt
        WHERE ReceiptID in
           <iterate open="(" conjunction="," property="receiptIdList" close=")"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_BillSendRecord (
				billing_config_id,
				send_time,
				amount,
				remark,
				remind_times,
		 		create_by, 
		 		created_time, 
		 		update_by, 
		 		updated_time)
		 	VALUES (
		 		#billingConfigId#,
		 		#sendTime#,
		 		#amount#,
		 		#remark#,
		 		#remindTimes#,
		 		#createBy#,
		 		#createTime#,
		 		#updateBy#,
		 		#updateTime#)
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_BookingPayDetail
		(
			shop_id,
			user_id,
			operate_id,
			operate_type,
			amount,
			balance,
			type,
			biz_id,
			status,
			receipt_id,
			comments,
			create_by,
			created_time
		)
		VALUES
		(
			#shopId#,
			#userId#,
			#operateId#,
			#operateType#,
			#amount#,
			#balance#,
			#type#,
			#bizId#,
			#status#,
			#receiptId#,
			#comment#,
			#createBy#,
			#createTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_CallCenterSwitchLog (
			switch_type,
			switch_reason,
			switch_id,
			switch_detail,
			callevent_type,
			create_by,
			create_time)
		VALUES (
			#switchType#,
			#switchReason#,
			#switchId#,
			#switchDetail#,
			#callEventType#,
			#createBy#,
			#createTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	 	INSERT INTO YY_Cooperation (
	 		name,
	 		phone, 
	 		information,
	 		create_by,
	 		created_time,
	 		update_by,
	 		updated_time)
		VALUES (
			#name#,
			#phone#, 
			#information#,
			#createBy#,
			#createTime#,
			#updateBy#,
			#updateTime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_MockBookingRecord (
		shop_id,
		name,
		gender,
		phone,
		num_people,
		position_type,
		status,
		consumption,
		booking_time,
		created_time,
		updated_time,
		created_by)
		VALUES (
		#shopID#,
		#name#,
		#genderCode#,
		#phone#,
		#peopleNumber#,
		#positionTypeCode#,
		#statusCode#,
		#consumption#,
		#bookingTime#,
		#createTime#,
		#updateTime#,
		#createBy#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	 	INSERT INTO YY_ShopBookableStatus (
	 		shop_id,
	 		date,
	 		update_time,
	 		status,
	 		line_lock)
		VALUES (
			#shop_id#,
			#date#,
		    NOW(),
			"",
			0)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_ShopBookableStatus (
        shop_id,
        date,
        update_time,
        status,
        line_lock)
        VALUES (
        #shop_id#,
        #date#,
        NOW(),
        #status#,
        0)
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO YY_WaitressPayDetail (
        phone_number,
        amout,
        create_by,
        created_time,
        update_by,
        updated_time)
        VALUES (
        #phoneNumber#,
        #amount#,
        #createBy#,
        #createTime#,
        #updateBy#,
        #updateTime#)
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_JournalVoucher (
		OrderID,
		OrderType,
		DealProductType,
		JournalVoucherType,
		UserID,
		ThirdPartyID,
		DealGroupID,
		DealID,
		DealSnapshotID,
		Details,
		Quantity,
		AddDate,
		ReferID,
		TotalAmount,
        Status
		)
		VALUES (
		#orderID#,
		#orderType#,
		#dealProductType#,
		#journalVoucherType#,
		#userID#,
		#thirdPartyID#,
		#dealGroupID#,
		#dealID#,
		#dealSnapshotID#,
		#details#,
		#quantity#,
		#addDate#,
		#referID#,
		#totalAmount#,
        #status#
		)
		   <selectKey keyProperty="voucherID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountHistory (
		ReceiptAccountID,
		Amount,
		HistoryType,
		ReferID,
		Title,
		AddDate
		)
		VALUES (
		#receiptAccountID#,
		#amount#,
		#historyType#,
		#referID#,
		#title#,
		#addDate#
		)
		   <selectKey keyProperty="historyID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptAccountOrder (
		ReceiptAccountID,
		OrderID,
		Quantity,
		OrderAmount,
		ReceiptAmount,
		UsedAmount,
		RefundedAmount,
		ReceiptBalance,
		LockVersion
		)
		VALUES (
		#receiptAccountID#,
		#orderID#,
		#quantity#,
		#orderAmount#,
		#receiptAmount#,
		#usedAmount#,
		#refundedAmount#,
		#receiptBalance#,
		#lockVersion#
		)
		   <selectKey keyProperty="receiptAccountOrderID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptPoolBatchFetch (
		Memo,
		AddDate
		)
		VALUES (
		#memo#,
		NOW()
		)
		   <selectKey keyProperty="receiptPoolBatchFetchID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptResetBatch
        (AdminID,AddTime) VALUES (#adminId#,Now());
           <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifyStatus (
			VerifySn
		)
		VALUES (
			#verifySn#
		)
		   <selectKey keyProperty="verifyStatusID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_StockLock (
			LockID,
			DealGroupID,
			DealID,
			Quantity,
			Status,
			AddDate,
			LastDate
		)
		VALUES (
			#lockID#,
			#dealGroupID#,
			#dealID#,
			#quantity#,
			#status#,
			NOW(),
			NOW()
		)
		   <selectKey keyProperty="stockLockID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Receipt (
		UserID, DealGroupID, DealID, OrderID,
		SerialNumber, TCode, ReceiptType, MobileNo, Status, RefundID, AddDate,
		LastDate, VendorShopID, DealSalePrice
		)
		VALUES (
		#userID#,
		#dealGroupID#,
		#dealID#,
		#orderID#,
		#serialNumber#,
		#tcode#,
		#receiptType#,
		#mobileNo#,
		#status#,
		#refundID#,
		now(),
		now(),
		#vendorShopID#,
		#dealSalePrice#
		)
		   <selectKey keyProperty="receiptID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT 
		INTO YY_CustomRebateScope (
			custom_id,
			activity_id,
			activity_type,
			create_by,
			created_time,
			update_by,
			updated_time
		) VALUES
		   <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO DP_RequestInfo
			(
				UserID,
				PageURL,
				GetParams,
				PostParams,
				AddTime,
				UpdateTime
			)
			VALUES
			(
				#requestInfo.userId#,
				#requestInfo.pageUrl#,
				#requestInfo.getParams#,
				#requestInfo.postParams#,
				NOW(),
				NOW()
			)
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_JobExecuteLog(
			job_id,
			start_time,
			end_time,
			result,
			result_message,
			create_time,
			create_by,
			update_time,
			update_by
		) VALUES (
			#jobID#,
			#startTime#,
			#endTime#,
			#result#,
			#resultMessage#,
			NOW(),
			#createBy#,
			NOW(),
			#updateBy#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT 
		INTO YY_CustomRebateItemInfo(
			rebateDesc,
			channel,
			display,
			short_display,
			status,
			begin_time,
			end_time,
			create_by,
			created_time,
			update_by,
			updated_time,
			bannerUrl,
			pageUrl,
			pageTitle,
			pageContent,
			pageType,
			android_url,
			page_picture_url,
			begin_used_time,
			end_used_time,
			web_rule_show)
		VALUES(
			#rebateDesc#,
			#channel#,
			#display#,
			#shortDisplay#,
			#status#,
			#beginTime#,
			#endTime#,
			#createBy#,
			NOW(),
			#updateBy#,
			NOW(),
			#bannerUrl#,
			#pageUrl#,
			#pageTitle#,
			#pageContent#,
			#pageType#,
			#androidUrl#,
			#pagePictureUrl#,
			#beginUsedTime#,
			#endUsedTime#,
			#webRuleShow#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		YY_RsCallbackEvent (
		task_id,
		init_task_id,
		type,
		shop_id,
		event,
		event_time,
		status,
		schedule_time,
		processor,
		event_message,
		created_time)
		VALUES (
		#taskId#,
		#initTaskId#,
		#type#,
		#shopId#,
		#event#,
		#eventTime#,
		#status#,
		#scheduleTime#,
		null,
		#message#,
		now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO YY_ShortMessage (
		type,
		status,
		record_id,
		phone,
		send_time,
		active_time,
		reply_time,
		create_by,
		created_time,
		update_by,
		updated_time
		)
		VALUES (
		#type#,
		#status#,
		#recordId#,
		#phone#,
		#sendTime#,
		#activeTime#,
		#replyTime#,
		#createBy#,
		#createTime#,
		#updateBy#,
		#updateTime#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardStopLog 
			(MemberCardID,
			 StopPeriod,
			 BeginDate,
			 EndDate,
			 IsActive,
			 AddTime) 
		VALUES 
			(#memberCardId#, 
			 #stopPeriod#,
			 #beginDate#,
			 #endDate#,
			 #isActive#,
			 #addTime#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardScoreLog(MemberCardID, LogType, Score, Comment, ReferLogID, Status, IsActive, AddTime, AdminID, AdminName)
			VALUES(#memberCardId#, #logType#, #score#, #comment#, #referLogId#, #status#, #isActive#, #addTime#, #adminId#, #adminName#)
			
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialOperationLog (AccountID,
			ShopID,
			ActionType,
			LogInfo,
			Comment,
			IP,
			AddTime) VALUES
			(#officialOperationLog.accountId#, 
			#officialOperationLog.shopId#, 
			#officialOperationLog.actionType#, 
			#officialOperationLog.logInfo#,
			#officialOperationLog.comment#,
			#officialOperationLog.ip#,NOW());
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO DP_UserAlbum(ShopID,UserID,PicTotal,DefaultPicUrl,ADDTIME,Popularity,ViewCount)
			VALUES(#shopId#, #userId#, #picTotal#, #defaultPicUrl#, #addTime#, 0 , 0)
		    <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_VoteRankFollowNote
        (
        MainNoteID,FromUserID,NoteBody,AddDate,UpdateDate,UserIP,ToUserID,OriginalUserID,GrandpaID,MainNoteTitle,VerifyStatus
           <isNotEmpty prepend="," property="userType"/>

        ) VALUES
        (
        #mainNoteID#,#fromUserID#,#noteBody#,Now(),Now(),#userIP#,#toUserID#,#originalUserID#,#grandpaID#,#mainNoteTitle#,#verifyStatus#
           <isNotEmpty prepend="," property="userType"/>

        );
           <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DianPing.DP_VoteRankTagRecord
        	(TagID, VoteRankID, AddDate)
        VALUES
           <iterate conjunction="," property="tagList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelHallPicRefSpace
        (
        ShopID, HallID, PicID, PicPath, OrderNo, AddTime, UpdateTime
        )
        VALUES
        (
            #weddingHotelHallPicRefSpace.shopId#,
            #weddingHotelHallPicRefSpace.hallId#,
            #weddingHotelHallPicRefSpace.picId#,
            #weddingHotelHallPicRefSpace.picPath#,
            #weddingHotelHallPicRefSpace.orderNo#,
            NOW(),
            NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_ShopProductCategoryList (
               <include refid="allFieldsInsert"/>

        ) VALUES (
            #categoryList.productCategoryId#,
            #categoryList.productCategoryName#,
            #categoryList.cityId#,
            #categoryList.orderNo#,
            #categoryList.shopCategoryId#,
            NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			BC_Notice(Title,Content,AddTime,AddAdminID)
		VALUES
			(#title#,#content#,NOW(),#addAdminID#)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_ShopAccountUser(ShopAccountId,UserId,AddTime,UpdateTime)
		VALUES
		(#shopAccountId#,#userId#,#addTime#,NOW())	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Feedback
		(FeedbackId, 
		Content, 
		CreateTime, 
		CreatorId)
		VALUES
		(#entity.feedbackId#,#entity.content#,#entity.createTime#,#entity.creatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_MobileDevice
		(UUID, 
		OSType, 
		OSDetail, 
		DeviceModel, 
		AppVersion, 
		CreateTime)
		VALUES
		(#entity.uuid#,#entity.osTypeId#,#entity.osDetail#,#entity.deviceModel#,#entity.appVersion#,#entity.createTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_HotelEventContent
        (
        eventID,
        platform,
        contentType,
        content,
        weight
        )
        values
           <iterate open="" conjunction="," property="eventContentList" close=""/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DP_ShopVideo
			(
				ShopID,
				ShopName,
				CityID,
				MainCategoryID,
				UserID,
				VideoID,
				VideoName,
				VideoUnique,
				Status,
				Description,
				Tag,
				DefaultPic,
				Duration,
				InitialSize,
				AuditStatus,
				LastIp,
				AddDate,
				LastDate,
				UploadDate
			)
			VALUES
			(
				#shopVideo.shopId#,
				#shopVideo.shopName#,
				#shopVideo.cityId#,
				#shopVideo.mainCategoryId#,
				#shopVideo.userId#,
				#shopVideo.videoId#,
				#shopVideo.videoName#,
				#shopVideo.videoUnique#,
				#shopVideo.status#,
				#shopVideo.description#,
				#shopVideo.tag#,
				#shopVideo.defaultPic#,
				#shopVideo.duration#,
				#shopVideo.initialSize#,
				#shopVideo.auditStatus#,
				#shopVideo.lastIp#,
				NOW(),
				NOW(),
				#shopVideo.uploadDate#
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_HotelEventLaunch
        (
        adID,
        otaID,
        cityID,
        shopID,
        status
        )
        values
           <iterate open="" conjunction="," property="eventList" close=""/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT  INTO DP_HotelEventAD
        (
        eventID,
        srcID,
        title,
        imgUrl,
        landPage,
        status,
        weight,
        startTime,
        endTime,
        addTime,
        updateTime
        )
        values
           <iterate open="" conjunction="," property="eventAdList" close=""/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_HotelEventAD
        (
        eventID,
        srcID,
        title,
        imgUrl,
        landPage,
        status,
        weight,
        startTime,
        endTime,
        addTime,
        updateTime
        )
        values (
        #eventAd.eventID#,
        #eventAd.srcID#,
        #eventAd.title#,
        #eventAd.imgUrl#,
        #eventAd.landPage#,
        #eventAd.status#,
        #eventAd.weight#,
        #eventAd.startTime#,
        #eventAd.endTime#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="adID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_ProductCategory
			(
				ProductID,
				ProductCategoryID,
				CityID
			)
			VALUES
			(
				#productCategory.productId#,
				#productCategory.productCategoryId#,
				#productCategory.cityId#
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			INSERT INTO DP_BizJournalAccount
			(
				TradeNo,
				OrderID,
				TradeFromAccountID,
				TradeFromName,
				TradeToAccountID,
				TradeToName,
				TradeTurnover,
				TradeType,
				TradeMode,
				AddUser,
				AddDate,
				TradeComment,
				AddIP,
				AddShopID,
				AddShopName,
				TradeStatus,
				ReceiptID,
                UpdateTime
			)
			VALUES
			(
				#bizJournalAccount.tradeNo#,
				#bizJournalAccount.orderId#,
				#bizJournalAccount.tradeFromAccountId#,
				#bizJournalAccount.tradeFromName#,
				#bizJournalAccount.tradeToAccountId#,
				#bizJournalAccount.tradeToName#,
				#bizJournalAccount.tradeTurnover#,
				#bizJournalAccount.tradeType#,
				#bizJournalAccount.tradeMode#,
				#bizJournalAccount.addUser#,
				NOW(),
				#bizJournalAccount.tradeComment#,
				#bizJournalAccount.addIp#,
				#bizJournalAccount.addShopId#,
				#bizJournalAccount.addShopName#,
				#bizJournalAccount.tradeStatus#,
				#bizJournalAccount.receiptId#,
                NOW()
			)
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO WED_PicSpace
			(
				ShopID,
				PicID,
				PicPath,
				Status,
				AddTime,
				UpdateTime
			)
			VALUES
			(
				#picSpace.shopId#,
				#picSpace.picId#,
				#picSpace.picPath#,
				#picSpace.status#,
				NOW(),
				NOW()
			)
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_ShopGuanfangBuluo (ShopId, Url, UpdateTime)
			VALUES(#buluo.shopId#, #buluo.url#, NOW());
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_ShopProductTagRecommend
			(
				ShopID,
				ProductCategoryID,
				TagNameID,
				TagValue,
				TagOrder,
				AddTime
			)
			VALUES
			(
				#shopProductTagRecommend.shopId#,
				#shopProductTagRecommend.productCategoryId#,
				#shopProductTagRecommend.tagNameId#,
				#shopProductTagRecommend.tagValue#,
				#shopProductTagRecommend.tagOrder#,
				NOW()
			)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_SpecialTopic
			(
				CityID,
				TopicUrl,
				TopicTitle,
				ThemeLabel,
				ThemeTitle,
				TopicPicId,
				TopicPicPath,
				ThemePicId,
				ThemePicPath,
				Status,
				AddUser,
				AddTime,
				UpdateUser
			)
			VALUES
			(
				#specialTopic.cityId#,
				#specialTopic.topicUrl#,
				#specialTopic.topicTitle#,
				#specialTopic.themeLabel#,
				#specialTopic.themeTitle#,
				#specialTopic.topicPicId#,
				#specialTopic.topicPicPath#,
				#specialTopic.themePicId#,
				#specialTopic.themePicPath#,
				#specialTopic.status#,
				#specialTopic.addUser#,
				NOW(),
				#specialTopic.updateUser#
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO WED_HotelSmsReplyRecord
		(
		Id,
		UserMobileNo,
		AddTime,
		UpdateTime,
		ReplyContent,
		SwallowCode
		)
		VALUES(
	    #wedHotelSmsReplyRecordEntity.id#,
		#wedHotelSmsReplyRecordEntity.userMobileNo#,
		now(),
		now(),
		#wedHotelSmsReplyRecordEntity.replyContent#,
		#wedHotelSmsReplyRecordEntity.swallowCode#
		)
		
	      <selectKey keyProperty="wedHotelSmsReplyRecordEntity.id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelHallOfficialAlbumRefSpace
        (
        ShopID, HallID, AlbumID, AddTime, UpdateTime
        )
        VALUES
        (
            #weddingHotelHallOfficialAlbumRefSpace.shopId#,
            #weddingHotelHallOfficialAlbumRefSpace.hallId#,
            #weddingHotelHallOfficialAlbumRefSpace.albumId#,
            NOW(),
            NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO WED_HotelBookShop
		(
		OrderId,
		ShopName,
		ShopId,
		UserContactNo,
		AddTime,
		UpdateTime,
		ClientType,
		UserName,
		Amount,
		ScheduleTime,
		RejectReason
		)
		VALUES(
		#wedHotelBookShop.orderId#,
		#wedHotelBookShop.shopName#,
		#wedHotelBookShop.shopId#,
		#wedHotelBookShop.userContactNo#,
		now(),
		now(),
		#wedHotelBookShop.clientType#,
		#wedHotelBookShop.userName#,
		#wedHotelBookShop.amount#,
		#wedHotelBookShop.scheduleTime#,
		#wedHotelBookShop.rejectReason#
		)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_EventSignUpShop
			(
				EventId,
				ModuleId,
				ShopId,
				ShopName,
				Comments,
				AuditStatus,
				SourceType,
				Ordinal,
				Enable,
				AddUser,
				AddTime,
				UpdateUser,
				UpdateTime
			)
			VALUES
			(
				#eventSignUpShop.eventId#,
				#eventSignUpShop.moduleId#,
				#eventSignUpShop.shopId#,
				#eventSignUpShop.shopName#,
				#eventSignUpShop.comments#,
				#eventSignUpShop.auditStatus:NUMERIC#,
				#eventSignUpShop.sourceType:NUMERIC#,
				#eventSignUpShop.ordinal#,
				#eventSignUpShop.enable:NUMERIC#,
				#eventSignUpShop.addUser#,
				NOW(),
				#eventSignUpShop.updateUser#,
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardConsumeAnalysis 
			(ShopID,
			 ConsumeAmount,
			 ConsumeCount,
			 ConsumeDate,
			 AddTime,
			 UpdateTime) 
		VALUES 
			(#shopID#,
			 #consumeAmount#,
			 #consumeCount#, 
			 #consumeDate#, 
			 #addTime#, 
			 #updateTime#)
		   <selectKey keyProperty="ReportID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
				
		INSERT INTO MC_MemberCardUserFeed(UserID,FeedID,Status,IsLike,IsUse,AddTime,UpdateTime) 
		VALUES(#userFeed.userId#, #userFeed.feedId#, #userFeed.status#, 0, 0, NOW(), NOW())
		   <selectKey keyProperty="userFeedId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#officialAlbum.shopId#, 
			#officialAlbum.name#,
			#officialAlbum.picPath#, 
			#officialAlbum.picType#,
			#officialAlbum.picCount#,
			#officialAlbum.isMain#,
			#officialAlbum.description#,
			NOW(),NOW(),#officialAlbum.albumType#);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_PicTag (PicID, ShopID, TagTypeName, TagName, UserID) 
		VALUES 
		   <iterate conjunction="," property="tagList"/>

		ON DUPLICATE KEY UPDATE UserID = VALUES(UserID)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DianPing.DP_MyListTagRecord
        (TagID,
        ListID,
        ADDDATE
        )
        VALUES
           <iterate conjunction="," property="tagList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_NewShopPhone
        (
        PhoneID,
        PhoneNum,
        SubNum,
        ShopID,
        ShopName,
        CityID,
        AddTime,
        AddUser,
        Phone1,
        Phone2,
        Phone3
        )VALUES (
        #shopPhone.phoneId#,
        #shopPhone.phoneNum#,
        #shopPhone.subNum#,
        #shopPhone.shopId#,
        #shopPhone.shopName#,
        #shopPhone.cityId#,
        NOW(),
        #shopPhone.addUser#,
        #shopPhone.phone1#,
        #shopPhone.phone2#,
        #shopPhone.phone3#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_Shop(ShopType,ShopName,BranchName,AltName,Address,CrossRoad ,PhoneNo,PhoneNo2
        ,CityID,ShopGroupID,District,ADDDATE, LastDate,AddUser,LastUser,LastIP,AddUserName,LastUserName
        ,BranchTotal,GroupFlag,SearchName,WriteUp,SearchKeyWord,BusinessHours,PublicTransit,PriceInfo,ClientType)
        VALUES(#shop.shopType#,#shop.shopName#,#shop.branchName#,#shop.altName#,#shop.address#,#shop.crossRoad#,
        #shop.phoneNo#,#shop.phoneNo2#,#shop.cityId#,#shop.shopGroupId#,#shop.district#,NOW(),NOW(),#shop.addUser#,
        #shop.lastUser#,#shop.lastIp#,#shop.addUserName#,#shop.lastUserName#,#shop.branchTotal#,#shop.groupFlag#,
        #shop.searchName#,#shop.writeUp#,#shop.searchKeyWord#,#shop.businessHours#,#shop.publicTransit#,#shop.priceInfo#,#shop.clientType#);
		   <selectKey keyProperty="shopId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MyListFollowNote 
		(
		MainNoteID,FromUserID,NoteBody,AddDate,UpdateDate,UserIP,ToUserID,OriginalUserID,GrandpaID,MainNoteTitle,VerifyStatus
		   <isNotEmpty prepend="," property="userType"/>

		) VALUES 
		(
		#mainNoteID#,#fromUserID#,#noteBody#,Now(),Now(),#userIP#,#toUserID#,#originalUserID#,#grandpaID#,#mainNoteTitle#,#verifyStatus#
		   <isNotEmpty prepend="," property="userType"/>

		);
		   <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 INSERT INTO DP_MyListShop (ListID,ShopID,AddDate,Reason
		    <isNotEmpty prepend="," property="verifyStatus"/>

		 ,Sort) VALUES
		 (#listId#,#shopId#,now(),#reason#   <isNotEmpty prepend="," property="verifyStatus"/>
,#sort#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MyList (UserID, Title,Content,AddDATE, UpdateDate,VerifyStatus,ShopCount)
		VALUES
		(#userId#,#title#,#content#,now(),now(),#verifyStatus#,0);
		   <selectKey keyProperty="listId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO HY_ManaPendingList (
		ActionType, ActionTarget, ADDTIME, STATUS
		   <isNotEmpty prepend="," property="userId"/>

		) VALUES 
		(#actionType#, #listId#, NOW(), 0
		   <isNotEmpty prepend="," property="userId"/>

		);
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MyListFollowNote 
		(
		MainNoteID,FromUserID,NoteBody,AddDate,UpdateDate,UserIP,ToUserID,OriginalUserID,GrandpaID,MainNoteTitle,VerifyStatus
		   <isNotEmpty prepend="," property="userType"/>

		) VALUES 
		(
		#mainNoteID#,#fromUserID#,#noteBody#,Now(),Now(),#userIP#,#toUserID#,#originalUserID#,#grandpaID#,#mainNoteTitle#,#verifyStatus#
		   <isNotEmpty prepend="," property="userType"/>

		);
		   <selectKey keyProperty="followNoteId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_ShopPhoneRecord
        (
        ShopID,
        ShopName,
        CityID,
        ExtTelRecordID,
        PhoneType,
        TelNum400,
        SubNum,
        AreaCode,
        FromNum,
        DestNum,
        Status,
        BookingFlag,
        Province,
        City,
        StartTime,
        Duration,
        MsgUrl,
        CreateTime,
        UpdateTime
        )
        VALUES
        (
        #shopPhoneRecord.shopId#,
        #shopPhoneRecord.shopName#,
        #shopPhoneRecord.cityId#,
        #shopPhoneRecord.extTelRecordId#,
        #shopPhoneRecord.phoneType#,
        #shopPhoneRecord.telNum400#,
        #shopPhoneRecord.subNum#,
        #shopPhoneRecord.areaCode#,
        #shopPhoneRecord.fromNum#,
        #shopPhoneRecord.destNum#,
        #shopPhoneRecord.status#,
        #shopPhoneRecord.bookingFlag#,
        #shopPhoneRecord.province#,
        #shopPhoneRecord.city#,
        #shopPhoneRecord.startTime#,
        #shopPhoneRecord.duration#,
        #shopPhoneRecord.msgUrl#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MSD_AlbumTagType(TypeName, ShopType, CreatorId, `Status`, AddTime, UpdateTime)
        VALUES(#albumTagType.typeName#, #albumTagType.shopType#, #albumTagType.creatorId#, #albumTagType.status#, NOW(), NOW());
        
           <selectKey keyProperty="tagType" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MSD_AlbumTag(TagType, Tag, CreatorId, `Status`, AddTime, UpdateTime)
        VALUES(#albumTag.tagType#, #albumTag.tag#, #albumTag.creatorId#, #albumTag.status#, NOW(), NOW())
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ResetPasswordRequest
		(ResetPasswordRequestId,
		ShopAccount,
		ShopAccountId,
		MobileNo,
		LastRequestTime,
		IsReset,
		LastResetTime,
		LastUpdatorId
		)
		VALUES
		(#entity.id#,#entity.shopAccount#,#entity.shopAccountId#,#entity.mobileNo#,#entity.lastRequestTime#,#entity.isReset#,#entity.lastResetTime#,#entity.lastUpdatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Device_Account_Assn
		(DeviceId, 
		ShopAccountId, 
		IsLogin, 
		CreateTime, 
		LastLoginTime, 
		LastLogoutTime)
		VALUES
		(#entity.deviceId#,#entity.shopAccountId#,#entity.isLogin#,#entity.createTime#,#entity.lastLoginTime#,#entity.lastLogoutTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_ShopPermissionList
			(
				ShopID,
				ShopName,
				PermissionType,
				PermissionFrom,
				StartDate,
				ExpirationDate,
				LastUser,
				LastTime
			)
			VALUES
			(
				#permission.shopId#,
				#permission.shopName#,
				#permission.permissionType#,
				#permission.permissionFrom#,
				#permission.startDate#,
				#permission.expirationDate#,
				#permission.lastUser#,
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_SearchProductRecommend
        (
           <include refid="allFieldsInsert"/>

        )
        VALUES
        (
        
            #reco.shopId#,
            #reco.productId#,
            #reco.productPicUrl#,
            Now()
		
        )
        ON DUPLICATE KEY UPDATE
        
            ShopID = #reco.shopId#,
            ProductPicUrl = #reco.productPicUrl#,
            AddTime = NOW()
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO WED_EventPoi
		(
		EventID, TemplateID, PoiType, PoiSource,
		OrderNo, Enable, AddDate, UpdateDate
		)
		VALUES
		(
		#weddingEventPoi.eventId#,
		#weddingEventPoi.templateId#,
		#weddingEventPoi.poiType:NUMERIC#,
		#weddingEventPoi.poiSource:NUMERIC#,
		#weddingEventPoi.orderNo#,
		#weddingEventPoi.enable:NUMERIC#,
		#weddingEventPoi.addDate#,
		#weddingEventPoi.updateDate#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO WED_HotelAbnormalSmsReply
		(
		AddTime,
		UpdateTime,
		Type,
		UserMobileNo,
		ReplyContent,
		SwallowCode,
		UserName
		)
		VALUES(
		now(),
		now(),
		#wedHotelAbnormalSmsReplyEntity.type#,
		#wedHotelAbnormalSmsReplyEntity.userMobileNo#,
		#wedHotelAbnormalSmsReplyEntity.replyContent#,
		#wedHotelAbnormalSmsReplyEntity.swallowCode#,
		#wedHotelAbnormalSmsReplyEntity.userName#
		)
		
		   <selectKey keyProperty="wedHotelAbnormalSmsReplyEntity.id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO WED_HotelBookUser
		(
		AddTime,
		UpdateTime,
		UserMobileNo,
		UserPhoneNo,
		Status,
		UserName
		)
		VALUES(
		now(),
		now(),
		#wedHotelBookUser.userMobileNo#,
		#wedHotelBookUser.userPhoneNo#,
		#wedHotelBookUser.status#,
		#wedHotelBookUser.userName#
		)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact_Material (ShopContactID, MaterialPath, MaterialType,AddTime,UpdateTime)
			VALUES(
			#shopContactMaterialInfo.ShopContactID#,
			#shopContactMaterialInfo.path#,
			#shopContactMaterialInfo.type#,
			NOW(),
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbumTag (AlbumID, ShopID, TagName, TagValue, UserID) 
		VALUES 
		   <iterate conjunction="," property="tagList"/>

		ON DUPLICATE KEY UPDATE UserID = VALUES(UserID)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialPicTag (PicID, ShopID, tagName, tagValue,
		UserID)
		VALUES
		   <iterate conjunction="," property="tagList"/>

		ON DUPLICATE KEY UPDATE UserID = VALUES(UserID)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_PicTagIndex (PicID, ShopID, UserID, Tag, Title, OrderNo, AddTime) 
		VALUES 
		   <iterate conjunction="," property="shopPicTagIndexList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_UserAlbumTag (AlbumID, ShopID, TagTypeName, TagName, UserID) 
		VALUES 
		   <iterate conjunction="," property="tagList"/>

		ON DUPLICATE KEY UPDATE UserID = VALUES(UserID)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_VoteRank (`CityID`, `UserID`, `Title`, `Description`, `ViewCount`, `AddTime`)
        VALUES (#cityId#, #userId#, #title#, #desc#, 0, NOW())
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    		insert into WED_HotelHallSchedule(
				ShopId,
				HallId,
				ScheduleDate,
				Status,
				AddTime,
				UpdateTime)
			values(
				#entity.shopId#,
				#entity.hallId#,
				#entity.scheduleDate#,
				#entity.status#,
				#entity.addTime#,
				#entity.updateTime#);
    	
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelExtraInfo
        (
            ShopID,
            ShopName,
            ReferShopID,
            ReferFoodShopID,
            CooperateType,
            SalesInfo,
            HallCapacityMin,
            HallCapacityMax,
            HallCount,
            HallDesc,
            WeddingHotelDesc,
            Stage,
            Trans,
            CommonEquipment,
            EnvPicUserId,
            Address,
            Metro,
            Bus,
            Park,
            AddTime,
            UpdateTime,
            ShopMobile,
            CooperateLevel,
            PlaceType,
            Percentage
        )
        VALUES
        (
            #weddingHotelExtraInfo.shopId#,
            #weddingHotelExtraInfo.shopName#,
            #weddingHotelExtraInfo.referShopId#,
            #weddingHotelExtraInfo.referFoodShopId#,
            #weddingHotelExtraInfo.cooperateType#,
            #weddingHotelExtraInfo.salesInfo#,
            #weddingHotelExtraInfo.hallCapacityMin#,
            #weddingHotelExtraInfo.hallCapacityMax#,
            #weddingHotelExtraInfo.hallCount#,
            #weddingHotelExtraInfo.hallDesc#,
            #weddingHotelExtraInfo.weddingHotelDesc#,
            #weddingHotelExtraInfo.stage#,
            #weddingHotelExtraInfo.trans#,
            #weddingHotelExtraInfo.commonEquipment#,
            #weddingHotelExtraInfo.envPicUserId#,
            #weddingHotelExtraInfo.address#,
            #weddingHotelExtraInfo.metro#,
            #weddingHotelExtraInfo.bus#,
            #weddingHotelExtraInfo.park#,
            NOW(),
            NOW(),
            #weddingHotelExtraInfo.shopMobile#,
            #weddingHotelExtraInfo.cooperateLevel#,
            #weddingHotelExtraInfo.placeType#,
            #weddingHotelExtraInfo.percentage#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DP_RegionList(
		RegionID,
		OldID,
		RegionName,
		RegionAltName,
		CityID,
		RegionOrderID,
		ShopHits,
		RegionType,
		GLng,
		GLat,
		`Range`,
		ADDDATE,
		UpdateDate,
		ProvinceID,
		IsDefault,
		tmdRange,
		Address,
		ShopCount
		)VALUES(
		#regionListData.regionId#,
		#regionListData.oldId#,
		#regionListData.regionName#,
		#regionListData.regionAltName#,
		#regionListData.cityId#,
		#regionListData.regionOrderId#,
		#regionListData.shopHits#,
		#regionListData.regionType#,
		#regionListData.glng#,
		#regionListData.glat#,
		#regionListData.range#,
		#regionListData.addDate#,
		#regionListData.updateDate#,
		#regionListData.provinceId#,
		#regionListData.isDefault#,
		#regionListData.tmdRange#,
		#regionListData.address#,
		#regionListData.shopCount#)
		   <selectKey keyProperty="regionid" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>


		INSERT INTO DP_ShopModificationLog(
			ShopLogId,
			ShopId,
			Content,
			ContentType,
			ActionType,
			AddDate,
			UserId,
			UserIP,
			ClientType,
			EditorId,
			ConfirmDate,
			ConfirmType,
			OldValue,
			Comment,
			UserName,
			EditorName
		)VALUES(
			#shopModificationLog.shopLogId#,
			#shopModificationLog.shopId#,
			#shopModificationLog.content#,
			#shopModificationLog.contentType.value#,
			#shopModificationLog.actionType.value#,
			#shopModificationLog.addDate#,
			#shopModificationLog.userId#,
			#shopModificationLog.userIP#,
			#shopModificationLog.clientType#,
			#shopModificationLog.editorId#,
			#shopModificationLog.confirmDate#,
			#shopModificationLog.confirmType#,
			#shopModificationLog.oldValue#,
			#shopModificationLog.comment#,
			#shopModificationLog.userName#,
			#shopModificationLog.editorName#)
			   <selectKey keyProperty="shoplogid" resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ShopAccount
		(Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,accountType,PasswordVersion)
		VALUES
		(#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#,1)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ShopAccount
		(ShopAccount,Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,accountType,PasswordVersion)
		VALUES
		(#shopAccount#,#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#,1)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
        	INSERT INTO DP_FeedUserPrivacy(UserID, FeedTypeID, AddDate) VALUES
 		
		   <iterate conjunction="," property="list"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
        	 INSERT INTO SC_Feed
        	(UserID, FeedTypeID, Content,MainID,Status,AddTime)
        	 VALUES
        	(#userId#,#feedTypeId#,#content#,#mainId#,1,NOW())
 		
		   <selectKey keyProperty="FeedID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_LandingPageShop
        (
		ShopID,
		ShopType,
		ShopName,
		BranchName,
		CityID,
		DefaultPic,
		VoteTotal,
		PicTotal,
		BookingPreferential,
		PositionID,	
		Tuan,
	    Quan,
		Ka,
		AddDate,
		UpdateDate
        )
        VALUES
        (
        #landingPageShop.shopId#,
        #landingPageShop.shopType#,
        #landingPageShop.shopName#,
        #landingPageShop.branchName#,
        #landingPageShop.cityId#,
        #landingPageShop.defaultPic#,
        #landingPageShop.voteTotal#,
        #landingPageShop.picTotal#,
        #landingPageShop.bookingPreferential#,
        #landingPageShop.positionId#,
        #landingPageShop.tuan#,
        #landingPageShop.quan#,
        #landingPageShop.ka#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO WED_HotelSmsRecord
		(
		Id,
		UserMobileNo,
		OrderIds,
		AddTime,
		UpdateTime,
		ReplyContent,
		ReplyResult,
		ReplyOrderId,
		SwallowCode
		)
		VALUES(
		#whsRecordEntity.id#,
		#whsRecordEntity.userMobileNo#,
		#whsRecordEntity.orderIds#,
		now(),
		now(),
		#whsRecordEntity.replyContent#,
		#whsRecordEntity.replyResult#,
		#whsRecordEntity.replyOrderId#,
		#whsRecordEntity.swallowCode#
		)
		
           <selectKey keyProperty="whsRecordEntity.id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_ShopProductPic (ProductID, PicID, AddTime) 
		VALUES 
		(#shopProductPic.productId#, #shopProductPic.picId#, NOW())
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    	INSERT INTO WED_WeddingHotelAppointment (ShopId, HallId, Type, StartDate, EndDate, FixedDate, WeekendOnly, RemoveFour, Contactor, PhoneNo, Gender, AddTime, UpdateTime, UserId)
		VALUES(#appointment.shopId#, #appointment.hallId#, #appointment.type#, #appointment.startDate#, #appointment.endDate#, #appointment.fixedDate#, #appointment.weekendOnly#, #appointment.removeFour#, #appointment.contactor#, #appointment.phoneNo#, #appointment.gender#, NOW(), NOW(), #appointment.userId#)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelExtraInfo
        (
            ShopID,
            ShopName,
            ReferShopID,
            ReferFoodShopID,
            CooperateType,
            SalesInfo,
            HallCapacityMin,
            HallCapacityMax,
            HallCount,
            HallDesc,
            WeddingHotelDesc,
            Stage,
            Trans,
            CommonEquipment,
            EnvPicUserId,
            Address,
            Metro,
            Bus,
            Park,
            AddTime,
            UpdateTime,
            ShopMobile,
            CooperateLevel,
            PlaceType,
            Percentage,
            EnviromentPicOrAlbum,
            MenuPicOrAlbum
        )
        VALUES
        (
            #weddingHotelExtraInfo.shopId#,
            #weddingHotelExtraInfo.shopName#,
            #weddingHotelExtraInfo.referShopId#,
            #weddingHotelExtraInfo.referFoodShopId#,
            #weddingHotelExtraInfo.cooperateType#,
            #weddingHotelExtraInfo.salesInfo#,
            #weddingHotelExtraInfo.hallCapacityMin#,
            #weddingHotelExtraInfo.hallCapacityMax#,
            #weddingHotelExtraInfo.hallCount#,
            #weddingHotelExtraInfo.hallDesc#,
            #weddingHotelExtraInfo.weddingHotelDesc#,
            #weddingHotelExtraInfo.stage#,
            #weddingHotelExtraInfo.trans#,
            #weddingHotelExtraInfo.commonEquipment#,
            #weddingHotelExtraInfo.envPicUserId#,
            #weddingHotelExtraInfo.address#,
            #weddingHotelExtraInfo.metro#,
            #weddingHotelExtraInfo.bus#,
            #weddingHotelExtraInfo.park#,
            NOW(),
            NOW(),
            #weddingHotelExtraInfo.shopMobile#,
            #weddingHotelExtraInfo.cooperateLevel#,
            #weddingHotelExtraInfo.placeType#,
            #weddingHotelExtraInfo.percentage#,
            #weddingHotelExtraInfo.enviromentPicOrAlbum#,
            #weddingHotelExtraInfo.menuPicOrAlbum#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelMenu
        (
            ShopId,
            Name,
            Price,
            PriceUnit,
            ServicePrice,
            OpenWinePrice,
            EnterPrice,
            SetMealA,
            SetMealB,
            ExtraService,
            AddTime,
            UpdateTime,
            Document
        )
        VALUES
        (
            #weddingHotelMenu.shopId#,
            #weddingHotelMenu.name#,
            #weddingHotelMenu.price#,
            #weddingHotelMenu.priceUnit#,
            #weddingHotelMenu.servicePrice#,
            #weddingHotelMenu.openWinePrice#,
            #weddingHotelMenu.enterPrice#,
            #weddingHotelMenu.setMealA#,
            #weddingHotelMenu.setMealB#,
            #weddingHotelMenu.extraService#,
            NOW(),
            NOW(),
            #weddingHotelMenu.document#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_ShopProductTag
        (
               <include refid="allFieldsInsert"/>

        )
        VALUES
        (
        
            #productTag.productId#,
            #productTag.shopId#,
            #productTag.userId#,
            #productTag.tagNameId#,
            #productTag.tagValue#,
            #productTag.productCategoryId#,
            #productTag.cityId#,
            Now()
		
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_EventTemplate
			(
				EventId,
				TemplateType,
				EventHeadPic,
				EventBottomPic,
				ButtonText,
				PageTitle,
				KeyWord,
				PageDescription,
				AboveAdPic,
				AboveAdUrl,
				BelowAdPic,
				BelowAdUrl,
				RightAdPic,
				RightAdUrl,
				ExtendColor,
				NaviColor,
				NaviCharColor,
				BackgroundColor,
				ModuleTitleColor,
				ButtonColor,
				AddUser,
				AddTime,
				UpdateUser,
				UpdateTime
			)
			VALUES
			(
				#eventId#,
				#templateType:NUMERIC#,
				#eventHeadPic#,
				#eventBottomPic#,
				#buttonText#,
				#pageTitle#,
				#keyWord#,
				#pageDescription#,
				#aboveAdPic#,
				#aboveAdUrl#,
				#belowAdPic#,
				#belowAdUrl#,
				#rightAdPic#,
				#rightAdUrl#,
				#extendColor#,
				#naviColor#,
				#naviCharColor#,
				#backgroundColor#,
				#moduleTitleColor#,
				#buttonColor#,
				#addUser#,
				NOW(),
				#updateUser#,
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_EventModule
			(
				Name,
				TemplateId,
				Ordinal,
				RelatedShopIds,
				Enable,
				AddUser,
				AddTime,
				UpdateUser,
				UpdateTime
			)
			VALUES
			(
				#eventTemplateModule.name#,
				#eventTemplateModule.templateId#,
				#eventTemplateModule.ordinal#,
				#eventTemplateModule.relatedShopIds#,
				#eventTemplateModule.enable:NUMERIC#,
				#eventTemplateModule.addUser#,
				NOW(),
				#eventTemplateModule.updateUser#,
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_LandingPageHunqingShop
        (
		ShopID,
		ShopType,
		ShopName,
		BranchName,
		CityID,
		DefaultPic,
		VoteTotal,
		PicTotal,
		BookingPreferential,
		PositionID,	
		Tuan,
	    Quan,
		Ka,
		AddDate,
		UpdateDate
        )
        VALUES
        (
        #landingPageHunqingShop.shopId#,
        #landingPageHunqingShop.shopType#,
        #landingPageHunqingShop.shopName#,
        #landingPageHunqingShop.branchName#,
        #landingPageHunqingShop.cityId#,
        #landingPageHunqingShop.defaultPic#,
        #landingPageHunqingShop.voteTotal#,
        #landingPageHunqingShop.picTotal#,
        #landingPageHunqingShop.bookingPreferential#,
        #landingPageHunqingShop.positionId#,
        #landingPageHunqingShop.tuan#,
        #landingPageHunqingShop.quan#,
        #landingPageHunqingShop.ka#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_LandingPageSheyingShop
        (
		ShopID,
		ShopType,
		ShopName,
		BranchName,
		CityID,
		DefaultPic,
		VoteTotal,
		PicTotal,
		BookingPreferential,
		PositionID,	
		Tuan,
	    Quan,
		Ka,
		AddDate,
		UpdateDate
        )
        VALUES
        (
        #landingPageSheyingShop.shopId#,
        #landingPageSheyingShop.shopType#,
        #landingPageSheyingShop.shopName#,
        #landingPageSheyingShop.branchName#,
        #landingPageSheyingShop.cityId#,
        #landingPageSheyingShop.defaultPic#,
        #landingPageSheyingShop.voteTotal#,
        #landingPageSheyingShop.picTotal#,
        #landingPageSheyingShop.bookingPreferential#,
        #landingPageSheyingShop.positionId#,
        #landingPageSheyingShop.tuan#,
        #landingPageSheyingShop.quan#,
        #landingPageSheyingShop.ka#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO DP_BookingUser
			(
				UserID,
				UserName,
				UserSex,
				UserPhone,
				ShopID,
				ShopName,
				ClientType,
				BookingDate,
				MessageSuccess,
				Guid,
				Remark,
				AddDate,
				UpdateDate,
				BookingType,
				StartDate,
				EndDate,
				HallID,
				HallName,
				TransAmount,
				OrderStatus
			)
			VALUES
			(
				#bookUser.userId#,
				#bookUser.userName#,
				#bookUser.userSex#,
				#bookUser.userPhone#,
				#bookUser.shopId#,
				#bookUser.shopName#,
				#bookUser.clientType#,
				#bookUser.bookingDate#,
				#bookUser.messageSuccess#,
				#bookUser.guid#,
				#bookUser.remark#,
				NOW(),
				NOW(),
				#bookUser.bookingType#,
				#bookUser.startDate#,
				#bookUser.endDate#,
				#bookUser.hallId#,
				#bookUser.hallName#,
				#bookUser.transAmount#,
				#bookUser.orderStatus#
			)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO WED_Event
		(
		EventTitle, CityID, EventContent,
		EventType, EventStatus, TemplateID, StartDate, EndDate,
		ExpirationDate, AddDate, UpdateDate, AddUser, UpdateUser
		)
		VALUES
		(
		#weddingEvent.eventTitle#,
		#weddingEvent.cityId#,
		#weddingEvent.eventContent#,
		#weddingEvent.eventType:NUMERIC#,
		#weddingEvent.eventStatus:NUMERIC#,
		#weddingEvent.templateId#,
		#weddingEvent.startDate#,
		#weddingEvent.endDate#,
		#weddingEvent.expirationDate#,
		#weddingEvent.addDate#,
		#weddingEvent.updateDate#,
		#weddingEvent.addUser#,
		#weddingEvent.updateUser#
		)
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact_Audit (ShopContactID, ActionType, Reason, Memo, AdminID, AddTime, UpdateTime)
			VALUES(
			#contactAuditInfo.ShopContactID#,
			#contactAuditInfo.ActionType#,
			#contactAuditInfo.Reason#,
			#contactAuditInfo.Memo#,
			#contactAuditInfo.AdminID#,
			NOW(),
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
  
	     
	        INSERT INTO MC_MemberCardNumber(CardNO,Type,AddTime) VALUES 
	      
	       <iterate conjunction="," property="cardNOList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_ShopPic(PicID, ShopID, UserID, CityID, ShopType, ShopGroupID, PicType, IsTop, OrderNo, Price, PicPrice, PicPower, LastIP, AddTime, LastTime) 
		VALUES 
		   <iterate conjunction="," property="shopPics"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_VoteRankShop (`VoteRankId`, `ShopID`, `UserID`, `Reason`, `AddTime`)
        VALUES (#voteRankId#, #shopId#, #userId#, #reason#, NOW())
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_Authority
			(
				AuthorityDesc,
                GroupName,
                ActionName,
                AddUser,
                AddTime,
                UpdateUser,
                UpdateTime
			)
			VALUES
			(
				#auth.authorityDesc#,
				#auth.groupName#,
				#auth.actionName#,
				#auth.addUser#,
				NOW(),
				#auth.updateUser#,
				NOW()
			)
		
           <selectKey keyProperty="authorityId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_ShopPermissionListLog
			(
				ShopID,
				ShopName,
				PrePermission,
				NewPermission,
				UserID,
				UserName,
				AddTime
			)
			VALUES
			(
				#permissionLog.shopId#,
				#permissionLog.shopName#,
				#permissionLog.prePermission#,
				#permissionLog.newPermission#,
				#permissionLog.userId#,
				#permissionLog.userName#,
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO DP_ShopPhone
			(
				ShopID,
				ShopName,
				TelNum400,
                Phone1,
                Phone2,
                Phone3,
                AddUser,
		        CreateTime,
		        UpdateUser,
		        UpdateTime,
		        CityID,
		        PhoneType
			)
			VALUES
			(
				#shopPhone.shopId#,
				#shopPhone.shopName#,
				#shopPhone.telNum400#,
				#shopPhone.phone1#,
				#shopPhone.phone2#,
				#shopPhone.phone3#,
				#shopPhone.addUser#,
				NOW(),
				#shopPhone.addUser#,
				NOW(),
				#shopPhone.cityID#,
				#shopPhone.phoneType#
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO SE_GiftUserGot (GiftId, EventId, ActionId, ActionObjId, UserId, UserIp)
		VALUES(#giftUserGot.giftId#, #giftUserGot.eventId#, #giftUserGot.actionId#, #giftUserGot.actionObjId#, #giftUserGot.userId#, #giftUserGot.userIp#);
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SeWordsGiftUserGot(UserId,GiftType,AddTime,WordIds) VALUES 
		(#seWordsGiftUserGot.userId#,#seWordsGiftUserGot.giftType#,NOW(),#seWordsGiftUserGot.wordIds#);
		   <selectKey keyProperty="wordsGiftUserGotId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO DP_ShopPhone
			(
				ShopID,
				ShopName,
				TelNum400,
                Phone1,
                Phone2,
                Phone3,
                AddUser,
		        CreateTime,
		        UpdateUser,
		        UpdateTime,
		        CityID
			)
			VALUES
			(
				#shopPhone.shopId#,
				#shopPhone.shopName#,
				#shopPhone.telNum400#,
				#shopPhone.phone1#,
				#shopPhone.phone2#,
				#shopPhone.phone3#,
				#shopPhone.addUser#,
				NOW(),
				#shopPhone.addUser#,
				NOW(),
				#shopPhone.cityID#
			)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_MessageToken
		(DeviceId, 
		Token, 
		IsValid, 
		CreateTime)
		VALUES
		(#entity.deviceId#,#entity.token#,#entity.isValid#,#entity.createTime#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DP_Shop(
			ShopId,
			ShopType,
			ShopName,
			BranchName,
			Address,
			CrossRoad,
			PhoneNo,
			PhoneNo2,
			CityId,
			Power,
			ShopGroupId,
			GroupFlag,
			AltName,
			SearchName,
			Hits,
			WeeklyHits,
			District,
			AddDate,
			LastDate,
			AddUser,
			AddUserName,
			LastUser,
			LastUserName,
			LastIP,
			SearchKeyWord,
			GLat,
			GLng,
			HasStaticMap,
			BusinessHours,
			PublicTransit,
			BranchTotal,
			WriteUp,
			PriceInfo,ClientType,CanSendSms,OldName
		)VALUES(
			#shop.shopId#,
			#shop.shopType#,
			#shop.shopName#,
			#shop.branchName#,
			#shop.address#,
			#shop.crossRoad#,
			#shop.phoneNo#,
			#shop.phoneNo2#,
			#shop.cityId#,
			#shop.power#,
			#shop.shopGroupId#,
			#shop.groupFlag#,
			#shop.altName#,
			#shop.searchName#,
			#shop.hits#,
			#shop.weeklyHits#,
			#shop.district#,
			#shop.addDate#,
			#shop.lastDate#,
			#shop.addUser#,
			#shop.addUserName#,
			#shop.lastUser#,
			#shop.lastUserName#,
			#shop.lastIP#,
			#shop.searchKeyWord#,
			#shop.gLat#,
			#shop.gLng#,
			#shop.hasStaticMap#,
			#shop.businessHours#,
			#shop.publicTransit#,
			#shop.branchTotal#,
			#shop.writeUp#,
			#shop.priceInfo#,#shop.clientType#,1,#shop.oldName#)
			   <selectKey keyProperty="shopid" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert ignore into DP_ShopAttribute(ShopID, AttributeID, ValueID)
        values
           <iterate open="" conjunction="," property="dpShopAttributeList" close=""/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO WED_AdProduct
            (
                ShopID,
                ADRule,
                ProductId,
                AddTime
            )
            VALUES
            (
                #adProduct.shopId#,
                #adProduct.adRule#,
                #adProduct.productId#,
                NOW()
            )
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_ApplyLog
			(
				ShopID,
				ApplyType,
				ApplyUserID,
				ApplyTime,
				LastIP,
				CityID,
                ShopName,
                CategoryID,
                Status,
                VerifyUserID,
                VerifyTime
			)
			VALUES
			(
				#applyLog.shopId#,
				#applyLog.applyType#,
				#applyLog.applyUserId#,
				NOW(),
				#applyLog.lastIp#,
				#applyLog.cityId#,
				#applyLog.shopName#,
				#applyLog.categoryId#,
				#applyLog.status#,
				#applyLog.verifyUserId#,
				#applyLog.verifyTime#
			)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
            INSERT INTO WED_MobileBanner
            (
                CityID,
                PicUrl,
                LinkUrl,
                Type,
                Document,
                AddTime,
                UpdateTime,
                ShopType
            )
            VALUES
            (
                #mobileBanner.cityId#,
                #mobileBanner.picUrl#,
                #mobileBanner.linkUrl#,
                #mobileBanner.type#,
                #mobileBanner.document#,
                NOW(),
                NOW(),
                #mobileBanner.shopType#
            )
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO WED_PicSpaceAdmin
			(
				ModuleID,
				ModuleName,
				PicID,
				PicPath,
				Status,
				AddTime,
				UpdateTime
			)
			VALUES
			(
				#picSpaceAdmin.moduleId#,
				#picSpaceAdmin.moduleName#,
				#picSpaceAdmin.picId#,
				#picSpaceAdmin.picPath#,
				#picSpaceAdmin.status#,
				NOW(),
				NOW()
			)
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO WED_EventCategory
		(
		EventID, CategoryID, AddDate, UpdateDate
		)
		VALUES
		(
		#weddingEventCategory.eventId#,
		#weddingEventCategory.categoryId#,
		#weddingEventCategory.addDate#,
		#weddingEventCategory.updateDate#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelCooperateTypeLog
        (
        ShopID, ShopName, OldCooperateType, NewCooperateType, AddTime, UpdateTime
        )
        VALUES
        (
            #weddingHotelCooperateTypeLog.shopId#,
            #weddingHotelCooperateTypeLog.shopName#,
            #weddingHotelCooperateTypeLog.oldCooperateType#,
            #weddingHotelCooperateTypeLog.newCooperateType#,
            NOW(),
            NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelHall
        (
            ShopID,
            PicUserId,
            Name,
            PlanPicID,
            Capacity,
            Height,
            PostCount,
            Stage,
            Money,
            AddTime,
            UpdateTime,
            CapacityMin,
            CapacityMax,
            Remark,
            HallPicOrAlbum
        )
        VALUES
        (
            #weddingHotelHall.shopId#,
            #weddingHotelHall.picUserId#,
            #weddingHotelHall.name#,
            #weddingHotelHall.planPicId#,
            #weddingHotelHall.capacity#,
            #weddingHotelHall.height#,
            #weddingHotelHall.postCount#,
            #weddingHotelHall.stage#,
            #weddingHotelHall.money#,
            NOW(),
            NOW(),
            #weddingHotelHall.capacityMin#,
            #weddingHotelHall.capacityMax#,
            #weddingHotelHall.remark#,
            #weddingHotelHall.hallPicOrAlbum#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DP_BookingShop
			(
				ShopID,
				ShopName,
				CityID,
				MainCategoryID,
				PhoneNo,
				Preferential,
				ToShopPresent,
				OrderPresent,
				Experience,
				ServiceGuarantee,
				PreferentialFeatures,
                PreferentialLimitDate,
                PreferentialLimitContent,
                ProductID,
                TemplateID,
				InChargePersonName,
				InChargePersonQQ,
				AddDate,
				UpdateDate,
				AuditStatus
			)
			VALUES
			(
				#bookingShop.shopId#,
				#bookingShop.shopName#,
				#bookingShop.cityId#,
				#bookingShop.mainCategoryId#,
				#bookingShop.phoneNo#,
				#bookingShop.preferential#,
				#bookingShop.toShopPresent#,
				#bookingShop.orderPresent#,
				#bookingShop.experience#,
				#bookingShop.serviceGuarantee#,
				#bookingShop.preferentialFeatures#,
                #bookingShop.preferentialLimitDate#,
                #bookingShop.preferentialLimitContent#,
                #bookingShop.productID#,
                #bookingShop.templateId#,
				#bookingShop.inChargePersonName#,
				#bookingShop.inChargePersonQQ#,
				NOW(),
				NOW(),
				#bookingShop.auditStatus#
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_ShopProduct (
               <include refid="allFieldsInsert"/>

        ) VALUES (
        
            #shopProduct.shopId#,
            #shopProduct.shopName#,
            #shopProduct.cityId#,
            #shopProduct.productCategoryId#,
            #shopProduct.price#,
            #shopProduct.originalPrice#,
            #shopProduct.isMain#,
            #shopProduct.orderNo#,
            #shopProduct.name#,
            #shopProduct.simpleDescription#,
            #shopProduct.picId#,
            #shopProduct.picPath#,
            NOW()
        
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_ShopProduct (
           <include refid="allFieldsInsertSpecifyId"/>

        ) VALUES (
        
            #shopProduct.id#,
            #shopProduct.shopId#,
            #shopProduct.shopName#,
            #shopProduct.cityId#,
            #shopProduct.productCategoryId#,
            #shopProduct.price#,
            #shopProduct.originalPrice#,
            #shopProduct.isMain#,
            #shopProduct.orderNo#,
            #shopProduct.name#,
            #shopProduct.picId#,
            #shopProduct.picPath#,
            NOW()
        
        )
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact (ShopID, CityID, ShopType, CategoryID, Name, Mobile, Email, Status, Memo, AddTime,
			UpdateTime)
			VALUES(
			#shopContactBasicInfo.shopId#,
			#shopContactBasicInfo.cityId#,
			#shopContactBasicInfo.shopType#,
			#shopContactBasicInfo.categoryId#,
			#shopContactBasicInfo.Name#,
			#shopContactBasicInfo.mobile#,
			#shopContactBasicInfo.email#,
			1,
			#shopContactBasicInfo.memo#,
			NOW(),
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   
			INSERT INTO DP_Shop_Contact_Operation_Trace (ShopContactID, ActionType, Source, ShopID, Operator, OperatorName, Memo, AccessIP, AddTime)
			VALUES(
			#shopContactOperationTraceInfo.ShopContactID#,
			#shopContactOperationTraceInfo.ActionType#,
			#shopContactOperationTraceInfo.Source#,
			#shopContactOperationTraceInfo.ShopID#,
			#shopContactOperationTraceInfo.Operator#,
			#shopContactOperationTraceInfo.OperatorName#,
			#shopContactOperationTraceInfo.Memo#,
			#shopContactOperationTraceInfo.AccessIP#,
			NOW());
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
	   
			INSERT INTO BC_Shop_Contact_Operation_Trace_Item (TraceID, TraceKey, ValueBefore, ValueAfter)
			VALUES
		
		   <iterate conjunction="," property="shopContactOperationTraceItems"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
  
    	 
        	INSERT INTO SMS_Queue(SmsType, MobileNo, Message, status, Rank, Remark, AddTime, UpdateTime ) VALUES 
 		
		   <iterate conjunction="," property="batchData"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO MC_MemberCardRecommend
	(MembercardID, CityID,RecommendCards,AddTime, UpdateTime)
	VALUES
	(#membercardId#,#cityId#,#recommendCards#,#addTime#,#updateTime#)
	ON DUPLICATE KEY UPDATE
	RecommendCards = VALUES(RecommendCards),
	UpdateTime = VALUES(UpdateTime)
	   <selectKey keyProperty="MembercardID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO MC_MemberCardRecommend
	(MembercardID, CityID,RecommendCards,AddTime, UpdateTime)
	VALUES
	   <iterate conjunction="," property="reportList"/>

	ON DUPLICATE KEY UPDATE
	RecommendCards = VALUES(RecommendCards),
	UpdateTime = VALUES(UpdateTime)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_ShopNavigation
			(
				ShopID,
				NavigationTitle,
                NavigationType,
                LinkUrl,
                OrderNo,
                AddTime,
                UpdateTime
			)
			VALUES
			(
				#shopNavigation.shopId#,
				#shopNavigation.navigationTitle#,
				#shopNavigation.navigationType#,
				#shopNavigation.linkUrl#,
				#shopNavigation.orderNo#,
				NOW(),
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_ShopProductRecommend (ShopId, ProductCategoryId, ProductCategoryOrder, ProductId, ProductOrder, STATUS, AddTime, UpdateTime)
			VALUES(#recommend.shopId#, #recommend.productCategoryId#, #recommend.productCategoryOrder#, #recommend.productId#, #recommend.productOrder#, #recommend.status#, NOW(), NOW())
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO DP_BizAccount
			(
				ShopID,
				ShopName,
				ShopAccountID,
				Balance,
				ContractNo,
				SalerID,
				SalerName,
				InvoiceTitle,
				AddDate,
				AddUser,
				LastDate,
				LastUser,
				CityID
			)
			VALUES
			(
				#bizAccount.shopId#,
				#bizAccount.shopName#,
				#bizAccount.shopAccountId#,
				#bizAccount.balance#,
				#bizAccount.contractNo#,
				#bizAccount.salerId#,
				#bizAccount.salerName#,
				#bizAccount.invoiceTitle#,
				NOW(),
				#bizAccount.addUser#,
				NOW(),
				#bizAccount.addUser#,
				#bizAccount.cityID#
			)
		
		   <selectKey keyProperty="accountId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_OfficalAlbumRefSpace
        (
        ShopID, AlbumID, AlbumOrder, Type, AddTime, UpdateTime
        )
        VALUES
        (
            #weddingHotelOfficialAlbumRefSpace.shopId#,
            #weddingHotelOfficialAlbumRefSpace.albumId#,
            #weddingHotelOfficialAlbumRefSpace.albumOrder#,
            #weddingHotelOfficialAlbumRefSpace.type#,
            NOW(),
            NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 INSERT INTO DP_MyListShop (ListID,ShopID,AddDate,Reason
		    <isNotEmpty prepend="," property="verifyStatus"/>

		 ,Sort) VALUES
		 (#listId#,#shopId#,now(),#reason#   <isNotEmpty prepend="," property="verifyStatus"/>
,#sort#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MyList (UserID, Title,Content,AddDATE, UpdateDate,VerifyStatus,ShopCount)
		VALUES
		(#userId#,#title#,#content#,now(),now(),#verifyStatus#,0);
		   <selectKey keyProperty="listId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO HY_ManaPendingList (
		ActionType, ActionTarget, ADDTIME, STATUS
		   <isNotEmpty prepend="," property="userId"/>

		) VALUES 
		(#actionType#, #listId#, NOW(), 0
		   <isNotEmpty prepend="," property="userId"/>

		);
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO SE_GiftUserGot (GiftId, EventId, ActionId, ActionObjId, UserId, UserIp)
		VALUES(#giftUserGot.giftId#, #giftUserGot.eventId#, #giftUserGot.actionId#, #giftUserGot.actionObjId#, #giftUserGot.userId#, #giftUserGot.userIp#);
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SeWordsGiftUserGot(UserId,GiftType,AddTime,WordIds) VALUES 
		(#seWordsGiftUserGot.userId#,#seWordsGiftUserGot.giftType#,NOW(),#seWordsGiftUserGot.wordIds#);
		   <selectKey keyProperty="wordsGiftUserGotId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO DP_BookingUser
			(
				UserID,
				UserName,
				UserSex,
				UserPhone,
				ShopID,
				ShopName,
				ClientType,
				BookingDate,
				MessageSuccess,
				Guid,
				AddDate,
				UpdateDate,
				BookingType,
				StartDate,
				EndDate,
				HallID,
				HallName,
				TransAmount
			)
			VALUES
			(
				#bookUser.userId#,
				#bookUser.userName#,
				#bookUser.userSex#,
				#bookUser.userPhone#,
				#bookUser.shopId#,
				#bookUser.shopName#,
				#bookUser.clientType#,
				#bookUser.bookingDate#,
				#bookUser.messageSuccess#,
				#bookUser.guid#,
				NOW(),
				NOW(),
				#bookUser.bookingType#,
				#bookUser.startDate#,
				#bookUser.endDate#,
				#bookUser.hallId#,
				#bookUser.hallName#,
				#bookUser.transAmount#
			)
		
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO DP_ShopDoubtLog(
			ID,
			Reason,
			ReasonType,
			Status,
			AddDate,
			SubmitterID,
			SubmitterName,
			ShopLogID
		)VALUES(
			#shopDoubtLog.id#,
			#shopDoubtLog.reason#,
			#shopDoubtLog.reasonType#,
			#shopDoubtLog.status#,
			#shopDoubtLog.addDate#,
			#shopDoubtLog.submitterId#,
			#shopDoubtLog.submitterName#,
			#shopDoubtLog.shopLogId#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MSD_ShopAuthorityLog(ItemID, ShopID, AccountID, OpType, OpUser, Content, IP, AddTime)
        VALUES(#log.itemId#, #log.shopId#, #log.accountId#, #log.opType#, #log.opUser#, #log.content#, #log.ip#, NOW())
        
           <selectKey keyProperty="ID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MSD_LaunchLog(LaunchGroupId, OpType, Content, AccountId, ShopId, IP, AddTime)
        VALUES(#log.launchGroupId#, #log.opType#, #log.content#, #log.accountId#, #log.shopId#, #log.ip#, NOW())
        
           <selectKey keyProperty="ID" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MSD_OrderInfo(OrderId, CustomerName, ContractNo, `Status`, AddTime, UpdateTime)
        VALUES (#orderInfo.orderId#, #orderInfo.customerName#, #orderInfo.contractNo#,  #orderInfo.status#, NOW(), NOW())
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(ID,Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#id#,#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_ShopFeatureStatistics
		(ShopFeatureStatisticsId,
		CreatorId,
		CreateTime,
		ExpectedFeatures)
		VALUES
		(#entity.id#,#entity.creatorId#,#entity.createTime#,#entity.expectedFeatures#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
          INSERT INTO WED_PicRefSpace
			(
				ShopID,
				ProductID,
				PicID,
				PicPath,
				OrderNo,
				RefType,
				LinkUrl,
				AddTime,
				UpdateTime
			)
			VALUES
			(
				#picRefSpace.shopId#,
				#picRefSpace.productId#,
				#picRefSpace.picId#,
				#picRefSpace.picPath#,
				#picRefSpace.orderNo#,
				#picRefSpace.refType#,
				#picRefSpace.linkUrl#,
				NOW(),
				NOW()
			)
        
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_Shop(ShopType,ShopName,BranchName,AltName,Address,CrossRoad ,PhoneNo,PhoneNo2
        ,CityID,ShopGroupID,District,ADDDATE, LastDate,AddUser,LastUser,LastIP,AddUserName,LastUserName
        ,BranchTotal,GroupFlag,SearchName,WriteUp,SearchKeyWord,BusinessHours,PublicTransit,PriceInfo,ClientType)
        VALUES(#shop.shopType#,#shop.shopName#,#shop.branchName#,#shop.altName#,#shop.address#,#shop.crossRoad#,
        #shop.phoneNo#,#shop.phoneNo2#,#shop.cityId#,#shop.shopGroupId#,#shop.district#,NOW(),NOW(),#shop.addUser#,
        #shop.lastUser#,#shop.lastIp#,#shop.addUserName#,#shop.lastUserName#,#shop.branchTotal#,#shop.groupFlag#,
        #shop.searchName#,#shop.writeUp#,#shop.searchKeyWord#,#shop.businessHours#,#shop.publicTransit#,#shop.priceInfo#,#shop.clientType#);
		   <selectKey keyProperty="shopId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_ProductTag
			(
				ProductID,
				ShopID,
				UserID,
				TagName,
				TagValue,
				ProductCategoryID,
				CityID,
				AddDate,
				UpdateDate
			)
			VALUES
			(
				#productTag.productId#,
				#productTag.shopId#,
				#productTag.userId#,
				#productTag.tagName#,
				#productTag.tagValue#,
				#productTag.productCategoryId#,
				#productTag.cityId#,
				Now(),
				Now()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO WED_BookingWhiteList
			(
				ShopID,
				CityID,
				StartDate,
				ExpirationDate,
				AddDate,
				UpdateDate
			)
			VALUES
			(
				#bookingWhiteList.shopId#,
				#bookingWhiteList.cityId#,
				#bookingWhiteList.startDate#,
				#bookingWhiteList.expirationDate#,
				Now(),
				Now()
			)
		
		   <selectKey keyProperty="Id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_ShopPhoneRecord
        (
        ShopID,
        ShopName,
        CityID,
        ExtTelRecordID,
        TelNum400,
        SubNum,
        AreaCode,
        FromNum,
        DestNum,
        Status,
        BookingFlag,
        Province,
        City,
        StartTime,
        Duration,
        MsgUrl,
        CreateTime,
        UpdateTime
        )
        VALUES
        (
        #shopPhoneRecord.shopId#,
        #shopPhoneRecord.shopName#,
        #shopPhoneRecord.cityId#,
        #shopPhoneRecord.extTelRecordId#,
        #shopPhoneRecord.telNum400#,
        #shopPhoneRecord.subNum#,
        #shopPhoneRecord.areaCode#,
        #shopPhoneRecord.fromNum#,
        #shopPhoneRecord.destNum#,
        #shopPhoneRecord.status#,
        #shopPhoneRecord.bookingFlag#,
        #shopPhoneRecord.province#,
        #shopPhoneRecord.city#,
        #shopPhoneRecord.startTime#,
        #shopPhoneRecord.duration#,
        #shopPhoneRecord.msgUrl#,
        NOW(),
        NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_ShopProduct (ProductName,ShopID,ShopName,CityID,ProductTemplateID,ProductType,PromoStartTime,PromoEndTime,Price
        ,OriginalPrice,Status,AddUser,AddTime,LastUser,LastTime,LastIP)
        VALUES(#shopProduct.productName#,#shopProduct.shopId#,#shopProduct.shopName#,#shopProduct.cityId#,#shopProduct.productTemplateId#,#shopProduct.productType#,#shopProduct.promoStartTime#,
        #shopProduct.promoEndTime#,#shopProduct.price#,#shopProduct.originalPrice#,#shopProduct.status#,#shopProduct.addUser#,NOW(),#shopProduct.lastUser#,NOW(),
        #shopProduct.lastIp#);
    	   <selectKey keyProperty="productId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO WED_HotelSmsRecord
		(
		Id,
		UserMobileNo,
		OrderIds,
		AddTime,
		UpdateTime,
		ReplyContent,
		ReplyResult,
		ReplyOrderId,
		SwallowCode
		)
		VALUES(
		#whsRecordEntity.id#,
		#whsRecordEntity.userMobileNo#,
		#whsRecordEntity.orderIds#,
		now(),
		now(),
		#whsRecordEntity.replyContent#,
		#whsRecordEntity.replyResult#,
		#whsRecordEntity.replyOrderId#,
		#whsRecordEntity.swallowCode#
		)
		
           <selectKey keyProperty="whsRecordEntity.id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_ShopTag
        (
            ShopID,
            ShopName,
            TagValue,
            AddTime,
            UpdateTime
        )
        VALUES
        (
            #weddingShopTag.shopId#,
            #weddingShopTag.shopName#,
            #weddingShopTag.tagValue#,
            NOW(),
            NOW()
        )

           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
			INSERT INTO WED_EventShopPoiDetail
			(
				TemplateId,
				SignUpID,
				EventPoiID,
				PoiSource,
				Content,
				AddDate,
				UpdateDate
			)
			VALUES
			(
				#eventShopPoiDetail.templateId#,
				#eventShopPoiDetail.signUpId#,
				#eventShopPoiDetail.eventPoiId#,
				#eventShopPoiDetail.poiSource:NUMERIC#,
				#eventShopPoiDetail.content#,
				NOW(),
				NOW()
			)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DianPing.DP_MyListTagRecord
        (TagID,
        ListID,
        ADDDATE
        )
        VALUES
           <iterate conjunction="," property="tagList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupDetail
        (ReceiptGroupCodeID,ReceiptID,AddDate)
        SELECT #receiptGroupCodeId#,ReceiptID,Now()
        FROM TG_Receipt
        WHERE ReceiptID in
           <iterate open="(" conjunction="," property="receiptIdList" close=")"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptVerifySuccessLog 
			(ReceiptID,DealGroupID,DealID,AccountID,AddTime,VerifyChannel,DeviceID)
			  VALUES
			(#receiptVerifySuccessLog.receiptID#,
			#receiptVerifySuccessLog.dealGroupID#,
			#receiptVerifySuccessLog.dealID#,
			#receiptVerifySuccessLog.accountID#,Now(),
			#receiptVerifySuccessLog.verifyChannel#,
			#receiptVerifySuccessLog.deviceId#
			);		
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_FilmReservation
        (ReceiptID, ThirdPartyOrderID, ThirdPartyID, OrderID, Status, AddDate, UpdateDate, TicketNo, UserId, MobileNo)
        VALUES (
        #receiptID#, #thirdPartyOrderID#, #thirdPartyID#, #orderID#, #status#, NOW(), NOW(), #ticketNo#, #userId#, #mobileNo#
        )
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="orderID" resultClass="int"/>
  
    insert into TG_Order 
    	(OrderID, 
    	 UserID, 
    	 CityID, 
    	 DealGroupID, 
    	 DealID, 
    	 Quantity, 
    	 TotalAmount,
    	 PaymentAmount, 
    	 MobileNo, 
    	 Status, 
    	 RefundStatus, 
    	 AddDate, 
    	 UpdateDate, 
    	 SuccessDate,
    	 PayChannelStatus,
    	 AddUserIP)
    values 
    	(#orderID:LONG#, 
    	 #userID:INTEGER#, 
    	 #cityID:SMALLINT#, 
    	 #dealGroupID:INTEGER#,
    	 #dealID:INTEGER#, 
    	 #quantity:SMALLINT#, 
    	 #totalAmount:DECIMAL#, 
    	 #paymentAmount:DECIMAL#,
    	 #mobileNo:VARCHAR#, 
    	 #status:TINYINT#, 
    	 #refundStatus:TINYINT#, 
    	 now(),
    	 now(), 
    	 #successDate:TIMESTAMP#, 
    	 #payChannelStatus:VARCHAR#,
    	 #addUserIP:VARCHAR#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsUserInfo
		(
			CpsKey, ScenarioID, ChannelID, ChannelCode, TrackingCode, ExtraData, AddDate
		)
		VALUES
		(
			#key#, #scenarioId#, #channelId#, #channelCode#, #trackingCode#, #extraData#, NOW()
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsUserInfo
		(
			CpsKey, ScenarioID, ChannelID, ChannelCode, TrackingCode, ExtraData, AddDate
		)
		VALUES
		   <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealDestination
		(
		DealGroupID,
		CityID,
		DistrictID,
		Priority,
		AddDate
		)
		VALUES
		(
		#dealGroupId#,
		#cityId#,
		#districtId#,
		#priority#,
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupDetail
		(
		DealGroupID,
		Title,
		Content,
		Type,
		AddDate
		)
		VALUES
		(
		#entity.dealGroupID#,
		#entity.title#,
		#entity.content#,
		#entity.type#,
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupDetail
		(DealGroupID,
		Title,
		Content,
		Type,
		AddDate)
		VALUES
		   <iterate conjunction="," property="entityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO TG_DealGroupVerify
	(
	VerifyID,
	DealID,
	DealGroupID,
	AddDate,
	UpdateDate,
	Status,
	ThirdPartyDealID,
	ThirdPartyId,
	Num,
	IsspID
	)
	VALUES
	(
	#verifyID#,
	#dealID#,
	#dealGroupID#,
	NOW(),
	NOW(),
	#status#,
	#thirdPartyDealID#,
	#thirdPartyId#,
	#num#,
	#isspID#
	)
	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EDM_TaskDetail 
		(EmailId,TaskId,Priority,Status,MailContentId,AddTime)
		Select Distinct A.EmailId,#taskId#,#priority#,0,#contentId#,NOW()
		From Mail_Subscription A INNER JOIN Mail_EmailAddress B
		ON A.EmailId=B.EmailId 
		Where A.BizId=#productId# AND A.Subpara1=#cityId# AND A.Status=1 AND B.IsValid=1
		AND A.Subpara3 = #confreq# 
		AND NOT EXISTS (Select 1 from EDM_TuanUsers U where U.UserID=B.UserID AND U.AddDate>#today# AND U.cityID=#cityId#)
		   <dynamic>
      <isGreaterThan compareValue="1" prepend="AND" property="mod"/>
   </dynamic>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Recommend_DailyPicks(CityID,DpID,DealGroupIDs, Version,AddDate,UpdateDate)
			VALUES (#cityId#,#dpId#,#dealgroupIds#,#version#,NOW(),NOW()) 
			ON DUPLICATE KEY
			UPDATE DealGroupIDs=#dealgroupIds#, UpdateDate=NOW()
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_OperationRole 
		(
			RoleName,
			RoleFunc,
			AddTime,
			UpdateTime
		)
		VALUES
		(
			#role.name#,
			#role.roleFunc#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_OperationUser
		(
			UserName,
			Status,
			AddTime,
			UpdateTime
		)
		VALUES
		(
			#user.name#,
			#user.status#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_OpUserRes
		(
			UserID,
			UserType,
			CityIDs
		)
		VALUES
		(
			#userRestrict.userID#,
			#userRestrict.userType#,
			#userRestrict.cityIDs#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
        INSERT INTO TE_AdChannel (Name, Owner, Author, Status, AddTime, UpdateTime) VALUES
        (#adChannel.name#, #adChannel.owner#, #adChannel.author#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
        INSERT INTO TE_AdContainer (Name, ChannelID, Author, Status, AddTime, UpdateTime) VALUES
        (#adContainer.name#, #adContainer.channelID#, #adContainer.author#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
        INSERT INTO TE_AdDetail (Title, SubTitle, Thumb, Link, `Desc`, Type, resourceId, Status, AddTime, UpdateTime) VALUES
        (#adDetail.title#, #adDetail.subTitle#, #adDetail.thumb#, #adDetail.link#, #adDetail.desc#, #adDetail.type#, #adDetail.resourceId#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
        INSERT INTO TE_AdDimension (PublishID, CityID, Version, OrderNo, AdvertisementID, Type, Status, AddTime, UpdateTime) VALUES
        (#adDimension.publishID#, #adDimension.cityID#, #adDimension.version#, #adDimension.orderNo#, #adDimension.advertisementID#, #adDimension.type#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
        INSERT INTO TE_AdPosition (Title, `Desc`, ChannelID, ContainerID, Width, Height, Type, Status, Author, AddTime, UpdateTime, PublishType) VALUES
        (#adPosition.title#, #adPosition.desc#, #adPosition.channelID#, #adPosition.containerID#, #adPosition.width#, #adPosition.height#, #adPosition.type#, 1, #adPosition.author#, NOW(), NOW(), #adPosition.publishType#)
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
        INSERT INTO TE_AdPublish (Position, Title, Status, Author, Auditor, AddTime, BeginDate, EndDate, AuditTime, UpdateTime) VALUES
        (#adPublish.position#, #adPublish.title#, 1, #adPublish.author#, #adPublish.auditor#, NOW(), #adPublish.beginDate#, #adPublish.endDate#, #adPublish.auditTime#, NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		insert into
		TG_Event(
		EventTitle,
		EventType,
		Status,
		BeginDate,
		EndDate,
		PreBeginDate,
		PreEndDate,
		AheadHour,
		Icon,
		Scale,
		EventDesc,
		CurrentJoin,
		MaxJoin,
		MaxPerUser,
		ConsumePoint,
		EventEnName,
		Config,
		EventHref,
		ReferEventID
		)
		values(
		#eventTitle#,
		#eventType#,
		#status#,
		#beginDate#,
		#endDate#,
		#preBeginDate#,
		#preEndDate#,
		#aheadHour#,
		#icon#,
		#scale#,
		#eventDesc#,
		#currentJoin#,
		#maxJoin#,
		#maxPerUser#,
		#consumePoint#,
		#eventEnName#,
		#config#,
		#eventHref#,
		#referEventID#
		)
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into
			TG_EventPrizeRule
			(EventID, MaxAwardNum, MaxJoinNum, LuckyUserLimits, BigUserRatio,UserThreshold, DefaultPrizeID, CouponPeriod, AddTime)
		values
			(#eventId#, #maxAwardNum#, #maxJoinNum#, #luckyUserLimits#, #bigUserRatio#, #userThreshold#, #defaultPrizeId#, #couponPeriod#, NOW())			
		   <selectKey keyProperty="EventPrizeRuleID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Event_TopicItemCityDeal(TopicItemID, CityID, DealGroupID, DealGroupShortName, RecommendReason, DisplayOrder, ImgUrl) VALUES
		   <iterate conjunction="," property="topicDealList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Event_TopicItem(TopicItemName, TopicID, Priority, DisplayCount, Type, ItemBgColor, ItemLabelColor, ItemButtonColor, ButtonFontColor) VALUES
		   <iterate conjunction="," property="topicItemList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Event_TopicCity(TopicID, CityID, Status, EntryValue) VALUES
		   <iterate conjunction="," property="topicCityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_Topic 
        SET TopicName=#topicDto.topicName#,BeginDate=#topicDto.beginDate#,EndDate=#topicDto.endDate#,BannerImage=#topicDto.bannerImage#,
        Status=#topicDto.status#,TopicEnName=#topicDto.topicEnName#,Logo=#topicDto.logo#,MobileBannerImage=#topicDto.mobileBannerImage#,
        MStationTemplate=#topicDto.mStationTemplate#,PcTemplate=#topicDto.pcTemplate#,TgTemplate=#topicDto.tgTemplate#,DpTemplate=#topicDto.dpTemplate#,
        DpIpadTemplate=#topicDto.dpIpadTemplate#,TailImage=#topicDto.tailImage#,Type=#topicDto.type#,Author=#topicDto.author#
           <selectKey keyProperty="topicID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_TopicCity 
        SET TopicID=#topicCityDto.topicID#,CityID=#topicCityDto.cityID#,Status=#topicCityDto.status#,EntryValue=#topicCityDto.entryValue#
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_TopicItem (TopicID, TopicItemName, Priority, DisplayCount, TYPE, ItemBgColor, ItemLabelColor, ItemButtonColor, ButtonFontColor) VALUES
           <iterate conjunction="," property="topicItemList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_TopicItem
        SET TopicID=#topicItem.topicID#, TopicItemName=#topicItem.topicItemName#, Priority=#topicItem.priority#, DisplayCount=#topicItem.displayCount#, TYPE=#topicItem.type#, ItemBgColor=#topicItem.itemBgColor#, ItemLabelColor=#topicItem.itemLabelColor#, 
        ItemButtonColor=#topicItem.itemButtonColor#, ButtonFontColor=#topicItem.buttonFontColor#
           <selectKey keyProperty="topicItemID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_TopicItemCityDeal (TopicItemID, CityID, DealGroupID, DealGroupShortName, RecommendReason, DisplayOrder, ImgUrl) VALUES
           <iterate conjunction="," property="topicDealList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    	INSERT INTO TG_Event_ShanTuan (DealGroupTitle, BeginDate, EndDate, DealGroupID, IsReplaceTitle, AddDate, UpdateDate, Status, ReplaceImgURL) VALUES
    	(#shantuan.dealGroupTitle#, #shantuan.beginDate#, #shantuan.endDate#, #shantuan.dealGroupID#, #shantuan.isReplaceTitle#, NOW(), NOW(), 1, #shantuan.replaceImgURL#)
    
    	   <selectKey keyProperty="shanID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO DianPingMC.MC_MemberCardProductConfirmInfo 
			(ShopConfirmInfoID, 
			ProductType, 
			ProductName, 
			ProductDiscountRate, 
			ProductDesc, 
			BeginDate, 
			EndDate, 
			AuthAdminID, 
			AuthAdminName, 
			CreateTime, 
			UpdateTime
			)
			VALUES
			(#shopConfirmInfoID#, 
			#productType#, 
			#productName#, 
			#productDiscountRate#, 
			#productDesc#, 
			#beginDate#, 
			#endDate#, 
			#authAdminID#, 
			#authAdminName#,
			Now(), 
			Now()
			);
			
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DianPing.DP_MyListPush
        (Page, CityID, DP_MyListPush.Order, BizID, BizType, PushTitle, AddDate, UpdateDate)
        VALUES
           <iterate conjunction="," property="pushList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DianPing.DP_MyListPush
        (Page,
        CityID,
        Power,
        DP_MyListPush.Order,
        BizID,
        BizType,
        PushTitle,
        PushDesc,
        PushPicID,
        PushPicUrl,
        PushUrl,
        AddDate,
        UpdateDate)
        VALUES
           <iterate conjunction="," property="pushList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			BC_Notice(Title,Content,AddTime,AddAdminID)
		VALUES
			(#title#,#content#,NOW(),#addAdminID#)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_ShopFeatureStatistics
		(ShopFeatureStatisticsId,
		CreatorId,
		CreateTime,
		ExpectedFeatures)
		VALUES
		(#entity.id#,#entity.creatorId#,#entity.createTime#,#entity.expectedFeatures#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
            TGE_DealBuyCouponSendLog 
        	(UserId, CouponId, OrderId, Status, CreateTime)
        values
        	(#userId#, #couponId#, #orderId#, #status#, NOW())
           <selectKey keyProperty="LogId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			insert INTO TG_ParternerPromoEvent (EventID, EventType, DiscountAmount, ThirdPartyID, Status, AddDate, UpdateDate) VALUES
			(#entity.eventId#, #entity.eventType#, #entity.discountAmount#, 8, #entity.status#, now(), now())
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO 
            TGE_CouponControl 
        SET 
            couponGroupID = #couponGroupId#,
            CreateDate = #createDate#,
            CouponNum = #couponNum#,
            CouponStock = #stock#,
            ShowTimes = #showTimes#,
            ActivityType =#couponType#,
            StartTime =#startTime#,
            EndTime= #endTime#,
            ADDTIME = NOW()
           <selectKey keyProperty="CouponControlID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_CouponSendLog
        SET CouponControlID = #couponInfoId#,UserID = #userId#,Status =#msgStatus#,ConfigID=#configId#,CreateTime=NOW()
           <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_UserCouponShow 
		SET UserId=#userId#,ShowTimes=#showTimes#,EventType=#eventType#,AddTime=NOW();
           <selectKey keyProperty="ShowUserId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_User (UserEmail,UserNickName,UserPW,UserAddDate,UserLastDate,
			UserPower,MediaSource,UserSource,UserCity,UserIP,EmailVerifyStatus) 
		VALUES (#userEmail# ,#userNickname# ,#password# ,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP ,
			1 ,252 ,252 ,#cityID# ,#userIP# ,0)
		   <selectKey keyProperty="UserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_SubThirdUserOrder
        (
          SubOrderID,
          OrderID,
          ReceiptID,
          ChannelID,
          ChannelCode,
          Status,
          isVerified,
          AddDate
        )
        VALUES
        (
          #subOrderId#,
          #orderId#,
          #receiptId#,
          #channelId#,
          #channelCode#,
          #status#,
          #isVerified#,
          #addDate#
        )
           <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EBIZ_ApplyFlow(
			FlowId,
			ApplyId,
			FlowType,
			ReasonId,
			AccountId,
			AccountType,
			Memo,
			AddDate,
			UpdateDate,
			Ip
		)VALUES(
			#applyFlow.flowId#,
			#applyFlow.applyId#,
			#applyFlow.flowType#,
			#applyFlow.reasonId#,
			#applyFlow.accountId#,
			#applyFlow.accountType#,
			#applyFlow.memo#,
			#applyFlow.addDate#,
			#applyFlow.updateDate#,
			#applyFlow.ip#)
			
		   <selectKey keyProperty="pId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TGE_UserMobileMd5
			(MobileNo, MobileMd5, AddTime)
		VALUES
			(#mobileNo#, #mobileMd5#, NOW())
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_UserSurvey(
			CityID,
			UserID,
			Interest,
			Gender,
			Age,
			Marriage,
			Education,
			Career,
			AddDate,
			Salary,
			Cost,
			Source,
			FollowUs
		)VALUES(
			#userSurvey.cityId#,
			#userSurvey.userId#,
			#userSurvey.interest#,
			#userSurvey.gender#,
			#userSurvey.age#,
			#userSurvey.marriage#,
			#userSurvey.education#,
			#userSurvey.career#,
			#userSurvey.addDate#,
			#userSurvey.salary#,
			#userSurvey.cost#,
			#userSurvey.source#,
			#userSurvey.followUs#)
		   <selectKey keyProperty="surveyId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupBundle
		(
		DealGroupID,
		DealGroupID1,
		DealGroupID2,
		DealGroupID3,
		DealGroupID4,
		Title,
		Discount,
		Status,
		ADDDATE,
		UpdateDate
		)
		VALUES
		(
		#data.dealGroupId#,
		#data.dealGroupId1#,
		#data.dealGroupId2#,
		#data.dealGroupId3#,
		#data.dealGroupId4#,
		#data.title#,
		#data.discount#,
		#data.status#,
		NOW(),
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_MemberProfit(
			UserID,
			OrderID,
			DealGroupID,
			PurchaseRate,
			ConsumeAmount,
			GrowValueAmount,
			ProfitAmount,
			ExpiredDate,
			STATUS,
			ProfitType,
			Memo,
			AccountID,
			AddTime)
		values(
			#memberProfit.userId#,
			#memberProfit.orderId#,
			#memberProfit.dealGroupId#,
			#memberProfit.purchaseRate#,
			#memberProfit.consumeAmount#,
			#memberProfit.growValueAmount#,
			#memberProfit.profitAmount#,
			#memberProfit.expiredDate#,
			#memberProfit.status#,
			#memberProfit.profitType#,
			#memberProfit.memo#,
			#memberProfit.accountId#,
			NOW())
		   <selectKey keyProperty="MemberProfitID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			insert into MB_Base_trigger(trigger_dt, flag, table_name, update_dt, del_dt)
			values(#trigger_dt#, #flag#, #table_name#, #update_dt#, #del_dt#)
		    <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupExceptDate(
         DealGroupID,
        BeginDate,
        EndDate,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
         #entity.dealGroupId#,
        #entity.beginDate#,
        #entity.endDate#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupExceptHoliday(
        DealGroupID,
        Holiday,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.holiday#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_COMPOSITABLE_STATEMENT_TEMPLATE_ASSN
			(CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			COMPOSITABLE_TEMPLATE_ID,
			STATEMENT_TEMPLATE_ID)
		VALUES
			(#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.compositableTemplate.id#,
			#entity.statementTemplateId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DOCUMENT_BUILDER 
			(CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VERSION_ID, 
			DEAL_GROUP_ID, 
			DOCUMENT_TEMPLATE_ID)
		VALUES
			(#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.versionId#,
			#entity.dealGroupId#,
			#entity.documentTemplateId#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_TEMPLATE_ENTRY 
			(DOCUMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			IS_MANDATORY,
			AREA_TYPE_ID,
			SEQUENCE,
			DTYPE)
		VALUES
			(#entity.documentTemplate.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.isMandatory#,
			#entity.areaTypeId#,
			#entity.sequence#,
			'StatementTemplate'
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_VERSION
		(DEAL_GROUP_ID,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,PUBLISH_FROM_DATE,PUBLISH_TO_DATE,EFFECTIVE_BEGIN_DATE,
		EFFECTIVE_END_DATE,DEAL_GROUP_CONTENT,DOCUMENT_BUILDER_CONTENT,VISUAL_VIEW_CONTENT)
		VALUES
		(#entity.dealGroupId#,#entity.creatorId#,#entity.lastUpdatorId#,
		#entity.createTime#,#entity.lastUpdateTime#,#entity.publishFromDate#,#entity.publishToDate#,#entity.effectiveBeginDate#,#entity.effectiveEndDate#
		,#entity.dealGroupContent#,#entity.documentBuilderContent#,#entity.visualViewContent#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_DESTINATION
            (DEAL_GROUP_ID,
            CITY_ID,
            DISTRICT_ID,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID
            )
        VALUES
            (#entity.dealGroup.id#,
            #entity.cityId#,
            #entity.districtId#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#
            );
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_RECEIPT_VERIFY_HISTORY
            (DEAL_GROUP_ID,
            POI_SHOP_ID,
            SUCCESS_SERIAL_NUMBER,
            FAILURE_SERIAL_NUMBER,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME,
            MEMO,
            RECALL_STATUS
            )
        VALUES
            (#entity.dealGroupId#,
            #entity.poiShopId#,
            #entity.successSerialNumber#,
            #entity.failureSerialNumber#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#,
            #entity.memo#,
            #entity.recallStatus#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_SALES
            (SALES_ID,
            SALES_TEAM_ID,
            SALES_NAME,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME,
            VERSION_ID
            )
            VALUES
            (#entity.salesId#,
            #entity.salesTeamId#,
            #entity.salesName#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#,
            #entity.versionId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_SHOP_CITY_GROUP
         (
		CREATOR_ID,
		LAST_UPDATOR_ID,
		CREATE_TIME,
		LAST_UPDATE_TIME,
		CITY_ID,
		SEQUENCE,
		VISUAL_VIEW_ID)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
         #entity.cityId#,#entity.sequence#,#entity.visualView.id#)
		
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_SHOP_INFO
         (
		CREATOR_ID,
		LAST_UPDATOR_ID,
		CREATE_TIME,
		LAST_UPDATE_TIME,
		ADDRESS,
		BUSINESS_HOURS,
		CONTACT_PHONE,
		POI_SHOP_ID,
		SEQUENCE,
		IS_AVG_PRICE_DISPLAYED,
		IS_BUSINESS_HOURS_DISPLAYED,
		IS_CONTACT_PHONE_DISPLAYED,
		IS_MAP_LINK_DISPLAYED,
		IS_STAR_RATE_DISPLAYED,
		SHOP_CITY_GROUP_ID,
		IS_VOTE_QUANTITY_DISPLAYED)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
         #entity.address#,#entity.businessHours#,#entity.contactPhone#,#entity.poiShopId#,#entity.sequence#,
         #entity.isAvgPriceDisplayed#,#entity.isBusinessHoursDisplayed#,#entity.contactPhoneDisplayed#,
         #entity.isMapLinkDisplayed#,#entity.isStarRateDisplayed#,#entity.shopCityGroup.id#,#entity.isVoteQuantityDisplayed#
         )
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_SPECIAL_REMINDER_TEMPLATE_MAP
            (
             NAV_CATEGORY_ID,
             TEMPLATE_URL,
             CREATE_TIME,
             LAST_UPDATE_TIME,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             TEMPLATE_NAME
             )
        VALUES (#entity.categoryId#,
                #entity.templateUrl#,
                #entity.createTime#,
                #entity.lastUpdateTime#,
                #entity.creatorId#,
                #entity.lastUpdatorId#,
                #entity.templateName#
            );
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_CinemaSeat(ThirdPartyID, TPHallID, TPSeatID, Name, RowID, ColumnID, LoveFlag, Status, AddDate, RowIndex, ColumnIndex, SeatCode,TPHallCode) VALUES
           <iterate conjunction="," property="cinemaSeats"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_CinemaSeat(ThirdPartyID, TPHallID, TPSeatID, Name, RowID, ColumnID, LoveFlag, Status, AddDate, RowIndex, ColumnIndex, SeatCode, TPHallCode) VALUES
           <iterate conjunction="," property="cinemaSeats"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPA_ProcessState (
            ProcessID,
            StateKey,
            StateValue,
            AddTime
        )
        VALUES (
            #processID#,
            #stateKey#,
            #stateValue#,
            NOW()
        )
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_AttributeCategoryAssn
     (AttributeId,
    CategoryId,
    AttributeCategoryAssnId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.attributeId#,
    #entity.categoryId#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_Deal(
        DealID,
        DealGroupID,
        DealStatus,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.id#,
        #entity.dealGroupId#,
        #entity.dealStatus#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroup(
        DealGroupID,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.id#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_Deal
         (
            DealGroupID,
            OriginalPrice,
            RetailPrice,
            CostPrice,
            StockQty,
            Title,
            IsShopInputSeparated,
            DealStatus,
            Sort,
            ImssProductID,
            CreateTime,
            UpdateTime,
            CreateBy,
            UpdateBy
        )
        VALUES
            (
            #entity.dealGroupId#,
            #entity.originalPrice#,
            #entity.retailPrice#,
            #entity.costPrice#,
            #entity.stockQty#,
            #entity.title#,
            #entity.isShopInputSeparated#,
            #entity.dealStatus#,
            #entity.sort#,
            #entity.imssProductID#,
            #entity.createTime#,
            #entity.updateTime#,
            #entity.createBy#,
            #entity.updateBy#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupMaintainer(
        DealGroupID,
        MaintainerID,
        MaintainerName,
        MaintainerPhone,
        MaintainerEmail,
        IsTrained,
        VersionID,
        TrainStatus,
        ReceiveTime,
        ChangeStatusTime,
        ContactMp,
        ContactEmail,
        IsSended,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.maintainerId#,
        #entity.maintainerName#,
        #entity.maintainerPhone#,
        #entity.maintainerEmail#,
        #entity.isTrained#,
        #entity.versionID#,
        #entity.trainStatus#,
        #entity.receiveTime#,
        #entity.changeStatusTime#,
        #entity.contactMp#,
        #entity.contactEmail#,
        #entity.isSended#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_EditLog(
        DealGroupID,
        SnapShot,
        Type,
        CreateBy,
        CreateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.snapShot#,
        #entity.type#,
        #entity.createBy#,
        #entity.createTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          TPE_Topic
        SET TopicID = #topicInfo.topicId#,
            Title =#topicInfo.title#,
            SubTitle =#topicInfo.subtitle#,
            STATUS =#topicInfo.status#,
            BgColor =#topicInfo.bgColor#,
            PcHeadImg =#topicInfo.pcHeadimg#,
            AppHeadImg =#topicInfo.appHeadimg#,
            PcTemplate =#topicInfo.pcTemplate#,
            AppTemplate =#topicInfo.appTemplate#,
            ShareImg = #topicInfo.shareImg#,
            SkinID = #topicInfo.skinId#,
            Author =#topicInfo.author#,
            ADDTIME= NOW()
           <selectKey keyProperty="topicId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        TPE_TopicItem
        SET ItemID =#topicItem.topicItemId#,
            NAME =#topicItem.topicItemName#,
            TopicID =#topicItem.topicId#,
            TYPE =#topicItem.type#,
            STATUS =#topicItem.status#,
            BgColor =#topicItem.itemBgColor#,
            LabelColor =#topicItem.itemLabelColor#,
            ButtonColor =#topicItem.itemButtonColor#,
            BtnFontColor =#topicItem.buttonFontColor#,
            ADDTIME= NOW()
           <selectKey keyProperty="topicId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          TPE_TopicDeal
        SET ItemID =#topicDeal.topicItemId#,
            CityID =#topicDeal.cityId#,
            DealGroupID =#topicDeal.dealGroupId#,
            OrderNo =#topicDeal.orderNo#,
            STATUS =#topicDeal.status#,
            ADDTIME= NOW()
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        TPE_TopicCity
        SET TopicID = #topicCity.topicId#,
            CityID=#topicCity.cityId#,
            STATUS=#topicCity.status#,
            ADDTIME= NOW()
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          TPE_Topic
        SET TopicID = #topicInfo.topicId#,
            Title =#topicInfo.title#,
            SubTitle =#topicInfo.subtitle#,
            STATUS =#topicInfo.status#,
            BgColor =#topicInfo.bgColor#,
            PcHeadImg =#topicInfo.pcHeadimg#,
            AppHeadImg =#topicInfo.appHeadimg#,
            PcTemplate =#topicInfo.pcTemplate#,
            AppTemplate =#topicInfo.appTemplate#,
            ShareImg = #topicInfo.shareImg#,
            SkinID = #topicInfo.skinId#,
            Author =#topicInfo.author#,
            ADDTIME= NOW()
           <selectKey keyProperty="topicId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        TPE_TopicItem
        SET ItemID =#topicItem.topicItemId#,
            NAME =#topicItem.topicItemName#,
            TopicID =#topicItem.topicId#,
            TYPE =#topicItem.type#,
            STATUS =#topicItem.status#,
            BgColor =#topicItem.itemBgColor#,
            LabelColor =#topicItem.itemLabelColor#,
            ButtonColor =#topicItem.itemButtonColor#,
            BtnFontColor =#topicItem.buttonFontColor#,
            ADDTIME= NOW()
           <selectKey keyProperty="topicId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          TPE_TopicDeal
        SET ItemID =#topicDeal.topicItemId#,
            CityID =#topicDeal.cityId#,
            DealGroupID =#topicDeal.dealGroupId#,
            OrderNo =#topicDeal.orderNo#,
            STATUS =#topicDeal.status#,
            ADDTIME= NOW()
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        TPE_TopicCity
        SET TopicID = #topicCity.topicId#,
            CityID=#topicCity.cityId#,
            STATUS=#topicCity.status#,
            ADDTIME= NOW()
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			insert INTO TG_TravelRefundLog(ID,OrderID, ReceiptID,Type, Status, AddDate,UpdateDate) VALUES
			   <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			insert INTO TG_TravelRefundLog(ID,OrderID, ReceiptID,Type, Status, AddDate,UpdateDate) VALUES
			   <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TPDA_EditorCityAssn
            (EditorID,
             CityID,
             CreateBy,
            UpdateBy,
            CreateTime,
            UpdateTime)
        VALUES (#entity.editorId#,
            #entity.cityId#,
            #entity.createBy#,
            #entity.updateBy#,
            #entity.createTime#,
            #entity.updateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TA_OrderLog(orderid,thirdpartyid, shopid,cityid,shopkey,userkey,ordertime,reachtime,payfee,mobile,
		shopname,fetchtype,reserve,`interval`,reviewed,reviewdelay,willing,lateststatus,fromstatus,
		payorderid,paystatus,frompaystatus,commissionrate, isfree,boxfee,deliveryfee,UUID,channel,onlinepayment,orderaddress
		   <dynamic>
      <isNotNull property="clientip"/>
      <isNotNull property="addtime"/>
   </dynamic>

		
		)
		values(#orderid#,#thirdpartyid#, #shopid#,#cityid#,#shopkey#,#userkey#,#ordertime#,#reachtime#,#payfee#,#mobile#,
		#shopname#,#fetchtype#,#reserve#,#interval#,#reviewed#,#reviewdelay#,#willing#,#lateststatus#,#fromstatus#,
		#payorderid#,#paystatus#,#frompaystatus#,#commissionRate#, #isfree#,#boxfee#,#deliveryfee#,#UUID#,#channel#,#onlinePayment#,#orderAddress#
		   <dynamic>
      <isNotNull property="clientip"/>
      <isNotNull property="addtime"/>
   </dynamic>

		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_TemplateAssociation
     (TemplateId,
    CategoryId,
    TemplateAssociationId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.templateId#,
    #entity.categoryId#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupAdmission
         (
            DealGroupID,
            Amount,
            Status,
            CreateTime,
            UpdateTime,
            CreateBy,
            UpdateBy
        )
        VALUES
            (
            #entity.dealGroupId#,
            #entity.amount#,
            #entity.status#,
            #entity.createTime#,
            #entity.updateTime#,
            #entity.createBy#,
            #entity.updateBy#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupExtend(
        DealGroupID,
        CustomerID,
        CustomerName,
        ProtocolID,
        ContractID,
        ProcessID,
        ApprovalStatus,
        PublishStatus,
        PublishDate,
        PlanSaleBeginDate,
        SaleBeginType,
        EffectiveBeginType,
        EffectiveEndType,
        DaysAfterSaleBeginDate,
        DaysAfterEffectiveBeginDate,
        DaysBeforeEffectiveEndDate,
        IsReleasedToAllCities,
        NonrefundableReason,
        CityID,
        OwnerID,
        ThirdPartyVerifyProviderID,
        ImssCompanyID,
        IsAdmissionRequired,
        DisplayStatus,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.id#,
        #entity.customerId#,
        #entity.customerName#,
        #entity.protocolId#,
        #entity.contractId#,
        #entity.processId#,
        #entity.approvalStatus#,
        #entity.publishStatus#,
        #entity.publishDate#,
        #entity.planSaleBeginDate#,
        #entity.saleBeginType#,
        #entity.effectiveBeginType#,
        #entity.effectiveEndType#,
        #entity.daysAfterSaleBeginDate#,
        #entity.daysAfterEffectiveBeginDate#,
        #entity.daysBeforeEffectiveEndDate#,
        #entity.isReleasedToAllCities#,
        #entity.nonrefundableReason#,
        #entity.cityId#,
        #entity.ownerId#,
        #entity.thirdPartyVerifyProviderID#,
        #entity.imssCompanyID#,
        #entity.isAdmissionRequired#,
        #entity.displayStatus#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TPDA_Editor
            (EditorID,
		    EditorName,
	    	WorkLoad,
            WorkLoadToday,
            WorkLoadAssign,
            IsActive,
            CreateBy,
            UpdateBy,
            CreateTime,
            UpdateTime)
        VALUES (#entity.editorId#,
            #entity.editorName#,
            #entity.workload#,
            #entity.workloadToday#,
            #entity.workloadAssigned#,
            #entity.isActive#,
            #entity.createBy#,
            #entity.updateBy#,
            #entity.createTime#,
            #entity.updateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPA_ProcessDocument (
            ProcessID,
            DocumentType,
            DocumentKey,
            DocumentValue,
            AddTime
        )
        VALUES (
            #processID#,
            #documentType#,
            #documentKey#,
            #documentValue#,
            NOW()
        )
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItemAttribute (NaviTagItemAttributeID, ItemID, Name, Value)
		VALUES (
		#id#, #itemId#, #name#, #value#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_User (UserEmail,UserNickName,UserPW,UserAddDate,UserLastDate,
			UserPower,MediaSource,UserSource,UserCity,UserIP,EmailVerifyStatus) 
		VALUES (#userEmail# ,#userNickname# ,#password# ,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP ,
			1 ,252 ,252 ,#cityID# ,#userIP# ,0)
		   <selectKey keyProperty="UserID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_SubThirdUserOrder
        (
          SubOrderID,
          OrderID,
          ReceiptID,
          ChannelID,
          ChannelCode,
          Status,
          isVerified,
          AddDate
        )
        VALUES
        (
          #subOrderId#,
          #orderId#,
          #receiptId#,
          #channelId#,
          #channelCode#,
          #status#,
          #isVerified#,
          #addDate#
        )
           <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT  INTO TG_ThirdUserOrder
        (
        ThirdUserOrderType,
        OrderID,
        UserID,
        CityID,
        DealGroupID,
        DealID,
        Quantity,
        SuccessDate,
        ThirdUserID,
        ThirdUserType,
        DealPrice,
        AddDate,
        ChannelCode,
        TrackingCode,
        UserStatus,
        RefundQuantity
        )
        VALUES
        (
        #thirdUserOrderType#,
        #orderId#,
        #userId#,
        #cityId#,
        #dealGroupId#,
        #dealId#,
        #quantity#,
        #successDate#,
        #thirdUserId#,
        #thirdUserType#,
        #dealPrice#,
        #addDate#,
        #channelCode#,
        #trackingCode#,
        #userStatus#,
        #refundQuantity#
        )
           <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_Track
		(
			ScenarioId,
			PageId,
			ChannelId,
			ChannelCode,
			AddDate,
			Amount,
			Referer,
			UserAgent,
			Url,
			Guid,
			SessionId,
			BizId,
			BizType,
			OrderId 
		)
		VALUES
		(
			#scenarioId#,
			#pageId#,
			#channelId#,
			#channelCode#,
			#time#,
			#amount#,
			#referer#,
			#userAgent#,
			#url#,
			#guid#,
			#sessionId#,
			#bizId#,
			#bizType#,
			#orderId#
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGHT_Contract 
		(
			ContractSerialNO,
			ContractNO,
			CustomerName,
			ContractGlobalID,
			ShopName,
			CustomerID,
			DealSalesID,
			DealSalesDepartmentID,
			LocalSalesDepartmentID,
			LocalSalesID,
			CityID,
			AddDate,
			UpdateDate
		)
		VALUES
		(
			#contractSerialNO#,
			#contractNO#,
			#customerName#,
			#contractGlobalID#,
			#shopName#,
			#customerID#,
			#dealSalesID#,
			#dealSalesDepartmentID#,
			#localSalesDepartmentID#,
			#localSalesID#,
			#cityID#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupShopDetail
		(
		DealGroupID,
		Address,
		ContactPhone,
		BusinessHours,
		IsStarRateDisplayed,
		IsAverageConsumeDisplayed,
		IsReviewDisplayed,
		IsMapDisplayed,
		ShopID,
		AddDate
		)
		VALUES
		(
		#entity.dealGroupID#,
		#entity.address#,
		#entity.contactPhone#,
		#entity.businessHours#,
		#entity.isStarRateDisplayed#,
		#entity.isAverageConsumeDisplayed#,
		#entity.isReviewDisplayed#,
		#entity.isMapDisplayed#,
		#entity.shopID#,
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupShopDetail
		(
		DealGroupID,
		Address,
		ContactPhone,
		BusinessHours,
		IsStarRateDisplayed,
		IsAverageConsumeDisplayed,
		IsReviewDisplayed,
		IsMapDisplayed,
		ShopID,
		AddDate
		)VALUES
		   <iterate conjunction="," property="entityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealRegion
		(
		DealGroupID,
		RegionID,
		CityID,
		District,
		AddDate,
		UpdateDate
		)
		VALUES
		(
		#dealGroupId#,
		#regionId#,
		#cityId#,
		#district#,
		NOW(),
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	
    	INSERT INTO EDM_TuanUsers (UserID,ADDDate,cityID,SortDealId,UserGroupID,TaskGroupType) 
    	SELECT UserID,ADDTIME,cityID,TID,UserGroupID,#taskGroupType# From Mail_Individual_UserID
        U WHERE U.TaskGroupType =#taskGroupType# AND U.AddTime > #today# 
		        
           <dynamic>
      <isEqual compareValue="2" prepend="and" property="priority"/>
   </dynamic>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Rank_DealGroupKV(DealGroupId, Bytes, Json)
			VALUES (#dealGroupId#, #dealGroupBytes#, #dealGroupJson#) 
			ON DUPLICATE KEY
			UPDATE Json=#dealGroupJson#, Bytes=#dealGroupBytes#
		   <selectKey keyProperty="DealGroupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_EventPromo
		SET EventID=#promoCodeInfo.eventID#,Type=#promoCodeInfo.type#,Quota=#promoCodeInfo.quota#,Stock=#promoCodeInfo.stock#,CouponRuleID=#promoCodeInfo.couponRuleID#,
		BeginDate=#promoCodeInfo.beginDate#,EndDate=#promoCodeInfo.endDate#,AddDate=NOW(),UpdateDate=NOW(),
		Name=#promoCodeInfo.name#,Status=#promoCodeInfo.status#,Author=#promoCodeInfo.author#,Department=#promoCodeInfo.department#,
		FinancialItem=#promoCodeInfo.financialItem#
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventPromoCond
        SET PromoID=#promoCond.promoID#, ValidOrderPlatform=#promoCond.validOrderPlatform#, ValidPayPlatform=#promoCond.validPayPlatform#, ValidPaymentChannel=#promoCond.validPaymentChannel#, 
        MaxPerUser=#promoCond.maxPerUser#, MaxPerDeal=#promoCond.maxPerDeal#, LimitOrderAmount=#promoCond.limitOrderAmount#, CategoryList=#promoCond.categoryList#, ShopList=#promoCond.shopList#,
        BuyLimitType=#promoCond.buyLimitType#, UseLimitType=#promoCond.useLimitType#, UserPropertyType=#promoCond.userPropertyType#, CityIds=#promoCond.cityList#
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_OpDepartment
        SET Name=#departInfo.name#,DptDesc=#departInfo.departDesc#,Status=#departInfo.status#,AddTime=NOW(),UpdateTime=NOW()
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ModuleBlack 
		(
			ModuleKey,
			IntValue,
			TextValue,
			Status,
			AddDate,
			UpdateDate
		)
		VALUES
		(
			#data.moduleKey#,
			#data.intValue#,
			#data.textValue#,
			#data.status#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCard(MemberCardType, Source, Status, BgImageID, LogoID ,AuthAdminID,AuthAdminName, AddTime, Title, SubTitle)
			VALUES(#cardType#, #source#, #status#,0,0,#authAdminId#, #authAdminName#, #addTime#, #shopName#, #branchName#)
			
		   <selectKey keyProperty="MemberCardID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardBgImage(IsCustom, Name, PicPath, PicType, AddTime)
			VALUES(#isCustom#, #name#, #picPath#, #picType#, #addTime#)
			
		   <selectKey keyProperty="BgImageID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardLiteralRecom(Title, Url, Status, AddTime, BeginDate, EndDate,RecomType,PicPath,RecomOrder,EventDesc)
			VALUES(#mcl.title#, #mcl.url#, #mcl.status#, #mcl.addTime#, #mcl.beginDate#, #mcl.endDate#,#mcl.recomType#,#mcl.picPath#,#mcl.recomOrder#,#mcl.eventDesc#)
			
		   <selectKey keyProperty="RecomID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardLiteralRecomCity(RecomID, CityID)
			VALUES(#recomID#, #cityID#)
			
		   <selectKey keyProperty="RecomCityID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

			INSERT INTO MC_MemberCardLiteralRecomCity(RecomID, CityID) VALUES
 		   <iterate conjunction="," property="cityList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardScoreLog(MemberCardID, LogType, Score, Comment, ReferLogID, Status, IsActive, AddTime, AdminID, AdminName)
			VALUES(#memberCardId#, #logType#, #score#, #comment#, #referLogId#, #status#, #isActive#, #addTime#, #adminId#, #adminName#)
			
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardCompanyConfirmInfo 
			(CrmID,
			 CitySelected,
			 CardType,
			 CreateTime,
			 AuthAdminID,
			 AuthAdminName) 
		VALUES 
			(#crmID#, 
			 #citySelected#,
			 #cardType#,
			 #createTime#,
			 #authAdminID#,
			 #authAdminName#)
		   <selectKey keyProperty="companyConfirmInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardShopConfirmInfo 
			(CrmID,
			 CompanyConfirmInfoID,
			 ShopID,
			 ShopName,
			 CreateTime,
			 AuthAdminID,
			 AuthAdminName) 
		VALUES 
			(#crmID#, 
			 #companyConfirmInfoID#,
			 #shopID#,
			 #shopName#,
			 #createTime#,
			 #authAdminID#,
			 #authAdminName#)
		   <selectKey keyProperty="shopConfirmInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TA_ShopSeller (
			ShopID, 
			ShopName, 
			ShopContactName,
			ShopPhone, 
			OwnerPhone, 
			CreateUserCode,
			CreateUserName,
			AccountID,
			Password,
			CityId
		) VALUES (
			#shopSeller.shopId#,
			#shopSeller.shopName#,
			#shopSeller.shopContactName#,
			#shopSeller.shopPhone#,
			#shopSeller.ownerPhone#,
			#shopSeller.createUserCode#,
			#shopSeller.createUserName#,
			#shopSeller.accountId#,
			#shopSeller.password#,
			#shopSeller.cityId#
		)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ShopAccount
		(Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,accountType)
		VALUES
		(#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO BC_ShopAccount
		(ShopAccount,Password,ParentId,ContactName,ContactMobileNO,AddTime,UpdateTime,accountType)
		VALUES
		(#shopAccount#,#password#,#parentId#,#contactName#,#contactMobileNO#,#addTime#,#updateTime#,#accountType#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Rank_DealGroupKV(DealGroupId, Bytes, Json)
			VALUES (#dealGroupId#, #dealGroupBytes#, #dealGroupJson#) 
			ON DUPLICATE KEY
			UPDATE Json=#dealGroupJson#, Bytes=#dealGroupBytes#
		   <selectKey keyProperty="DealGroupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReminderSMSLog
        (
        AddDate,
        UpdateDate,
        UserID,
        ShopGroupID,
        DealGroupID,
        MobileNo
        )
        VALUES
        (
        NOW(),
        NOW(),
        #data.userId#,
        #data.shopGroupId#,
        #data.dealGroupId#,
        #data.mobileNo#
        )
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ThirdVerifyLog 
		(VerifyID,Type,Time,Result,	PostContent,ResponseContent,AddDate,UpdateDate,Status,	ExtensionMessage,ReferID)
		VALUES
		(#verifyId#,#type#,#time#,#result#,#postContent#,#responseContent#,Now(),Now(),#status#,#extensionMessage#,#referId#);		
	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        insert into TGHT_DeliverImportBatch (LoginID, Status, AddDate)
        values(#loginId#, #status#, Now());
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGHT_DeliverImportPool (BatchID,OrderID,Express,Status)
        VALUES
           <iterate conjunction="," property="deliverInfo"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGHT_DeliverAdminDeal (LoginID,DealGroupID,AddTime,Status,ExportBatch)
        VALUES(-1,#dealGroupId#, NOW(), 0,0)
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_NaviDealTag (DealGroupID, TagID, ItemID)
        VALUES
	       <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsUserInfo
		(
			CpsKey, ScenarioID, ChannelID, ChannelCode, TrackingCode, ExtraData, AddDate
		)
		VALUES
		(
			#key#, #scenarioId#, #channelId#, #channelCode#, #trackingCode#, #extraData#, NOW()
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsUserInfo
		(
			CpsKey, ScenarioID, ChannelID, ChannelCode, TrackingCode, ExtraData, AddDate
		)
		VALUES
		   <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CPSSettleUnit
		(
			UNIID,
			TYPE,
			BUSID,
			ORDERID,
			CHANNELID,
			CHANNELCODE,
			STATUS,
			ADDTIME,
			UPDATETIME
		)
		VALUES
		(
			#unitId#,
			#type#,
			#businessId#,
			#orderId#,
			#channelId#,
			#channelCode#,
			#status#,
			#addTime#,
			#updateTime#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EBIZ_ApplyReason(
			ReasonId,
			ReasonName,
			Tip,
			Priority,
			Type,
			Status,
			AddDate,
			UpdateDate
		)VALUES(
			#applyReason.reasonId#,
			#applyReason.reasonName#,
			#applyReason.tip#,
			#applyReason.priority#,
			#applyReason.type#,
			#applyReason.status#,
			#applyReason.addDate#,
			#applyReason.updateDate#)
		   <selectKey keyProperty="pId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
    	INSERT INTO TG_Event_ShanTuan (DealGroupTitle, BeginDate, EndDate, DealGroupID, IsReplaceTitle, AddDate, UpdateDate, Status) VALUES
    	(#shantuan.dealGroupTitle#, #shantuan.beginDate#, #shantuan.endDate#, #shantuan.dealGroupID#, #shantuan.isReplaceTitle#, #shantuan.addDate#, #shantuan.updateDate#, #shantuan.status#)
    
    	   <selectKey keyProperty="shanID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_EventDeal (DealGroupID, EventDealTitle, Status, EventID, CityID, AddDate)
		VALUES
		   <iterate conjunction="," property="deals"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ClientTrackText 
		(
			TableName,
			ReferID,
			Platform,
			TrackGuid,
			TrackIp
		)
		VALUES
		(
			#data.tableName#,
			#data.referId#,
			#data.platform#,
			#data.trackGuid#,
			#data.trackIp#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ShopGroupRecom 
		(
			ShopGroupID,
			ShopGroupName,
			CityID,
			Priority,
			Status,
			ADDDATE,
			UpdateDate
		)
		VALUES
		(
			#data.shopGroupId#,
			#data.shopGroupName#,
			#data.cityId#,
			#data.priority#,
			#data.status#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_MemberBehaviorMsg(
			UserID,
			ReferID,
			DealGroupID,
			ConsumeAmount,
			ActualCostAmount,
			MsgType,
			Platform,
			Memo,
			Status,
			AddTime)
		values(
			#memberBehaviorMsg.userId#,
			#memberBehaviorMsg.referId#,
			#memberBehaviorMsg.dealGroupId#,
			#memberBehaviorMsg.consumeAmount#,
			#memberBehaviorMsg.actualCostAmount#,
			#memberBehaviorMsg.msgType#,
			#memberBehaviorMsg.platform#,
			#memberBehaviorMsg.memo#,
			#memberBehaviorMsg.status#,
			#memberBehaviorMsg.addTime#)
		   <selectKey keyProperty="MemberBehaviorMsgID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_MemberLevel
        SET UserID = #memberBaseInfo.userId#,
            GrowValue = #memberBaseInfo.growValue#,
            LevelConfigID = #memberBaseInfo.levelConfigId#,
            Source = #memberBaseInfo.source#,
            AddTime = NOW()
           <selectKey keyProperty="GiftCardOrderID" resultClass="Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_MemberProfit(
			UserID,
			OrderID,
			DealGroupID,
			PurchaseRate,
			ConsumeAmount,
			GrowValueAmount,
			ProfitAmount,
			ExpiredDate,
			STATUS,
			ProfitType,
			Memo,
			AddTime)
		VALUES(
			#memberProfit.userId#,
			#memberProfit.orderId#,
			#memberProfit.dealGroupId#,
			#memberProfit.purchaseRate#,
			#memberProfit.consumeAmount#,
			#memberProfit.growValueAmount#,
			#memberProfit.profitAmount#,
			#memberProfit.expiredDate#,
			#memberProfit.status#,
			#memberProfit.profitType#,
			#memberProfit.memo#,
			NOW())
		   <selectKey keyProperty="MemberProfitID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealShop(
         DealID,
        ShopID,
        ShopType,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
         #entity.dealId#,
        #entity.shopId#,
        #entity.shopType#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_TEMPLATE_ENTRY 
			(DOCUMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			IS_MANDATORY,
			AREA_TYPE_ID,
			SEQUENCE,
			DTYPE)
		VALUES
			(#entity.documentTemplate.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.isMandatory#,
			#entity.areaTypeId#,
			#entity.sequence#,
			'CompositableTemplate'
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DOCUMENT_TEMPLATE 
			(DOCUMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VERSION_ID, 
			NAME, 
			IS_ACTIVE,
			IS_OFFLINE)
		VALUES
			(#entity.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.versionId#,
			#entity.name#,
			#entity.isActive#,
			#entity.isOffline#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_AE
		(DEAL_GROUP_ID,AE_ID,AE_NAME,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,VERSION_ID,AE_PHONE,AE_EMAIL)
		VALUES
		(#entity.dealGroupId#,#entity.aeId#,#entity.aeName#,#entity.creatorId#,#entity.lastUpdatorId#,
		#entity.createTime#,#entity.lastUpdateTime#,#entity.versionId#,#entity.aePhone#,#entity.aeEmail#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_DEAL_GROUP_MAINTAINER
		(DEAL_GROUP_ID,MAINTAINER_ID,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,
		MAINTAINER_NAME,TRAIN_STATUS,VERSION_ID,CONTACT_MP,CONTACT_EMAIL,IS_SENDED)
		VALUES
		(#entity.dealGroupId#,#entity.maintainerId#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
		#entity.maintainerName#,#entity.trainStatus#,#entity.versionId#
		,#entity.contactMP#,#entity.contactEmail#,#entity.isSended#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.templateId#,'ImageTextComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_LIST_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VISUAL_COMPONENT_ID,SEQ,PICTURE_URL,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.visualComponent.id#,#entity.sequence#,#entity.pictureUrl#,#entity.templateId#,'ImageTextItem')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_PRODUCT
         (
         CREATOR_ID,
         LAST_UPDATOR_ID,
         CREATE_TIME,
         LAST_UPDATE_TIME,
         VERSION_ID)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.versionId#)
		
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_RESOURCE_ROLE_AUTHORITY_CONFIG
            (
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            SOURCE_SYSTEM,
            ROLE_ID,
            POWER_CODE
            )
        VALUES
            (
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.sourceSystem#,
            #entity.roleId#,
            #entity.powerCode#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,RICH_TEXT_CONTENT,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.content#,#entity.templateId#,'RichTextComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_LIST_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VISUAL_COMPONENT_ID,SEQ,CONTENT,IS_READ_ONLY,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.visualComponent.id#,#entity.sequence#,#entity.content#,#entity.isReadOnly#,#entity.templateId#,'TextItem')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.templateId#,'TextListComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_AttributeOption
     (AttributeId,
    AttributeValue,
    Sequence,
    AttributeOptionId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.attributeId#,
    #entity.attributeValue#,
    #entity.sequence#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_BankAccount(
        DealGroupID,
        ShopID,
        BankAccountID,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.shopId#,
        #entity.bankAccountId#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupComponent(
        DealGroupID,
        ComponentType,
        Data,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
         #entity.dealGroupId#,
        #entity.componentType#,
        #entity.data#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    INSERT INTO
	    TA_OperationContract(firstpartytype, companyname, idnumber, contactpeople, contactphone, effectivedate, commission, freeorders, collectiontype, bankaccountname, bankaccount, bankprovince, bankcity, bankhead, bankbranch, addtime)
	    VALUES(#firstpartytype#, #companyname#, #idnumber#, #contactpeople#, #contactphone#, #effectivedate#, #commission#, #freeorders#, #collectiontype#, #bankaccountname#, #bankaccount#, #bankprovince#, #bankcity#, #bankhead#, #bankbranch#, NOW())
	       <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			insert INTO TG_TravelReservation(ReceiptID,OrderID, BookName,TravelerName, TourDate, IdentityType, IdentityID, 
			MobileNo, Status, RefReceiptID, RefReceiptCode, ThirdPartnerID, UserID, Memo, ThirdPartnerDealID, AddDate, UpdateDate) VALUES
			(#entity.receiptId#,#entity.orderId#, #entity.bookName#,#entity.travelerName#, #entity.tourDate#,
			#entity.identityType#, #entity.identityId#, #entity.bookMobileNo#,#entity.travelerName#, #entity.status#,
			#entity.refReceiptId#, #entity.refReceiptCode#, #entity.thirdPartnerId#, #entity.userId#, 
			#entity.memo#, #entity.thirdPartnerDealId#, now(), now())
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert INTO TG_TravelReservation(ReceiptID,OrderID, BookName,TravelerName, TourDate, IdentityType, IdentityID, 
			BookMobileNo,TravelerMobileNo, Status, RefReceiptID, RefReceiptCode, ThirdPartnerID, UserID, Memo, ThirdPartnerDealID, AddDate, UpdateDate) VALUES
		   <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TE_AdChannel (Name, Owner, Author, Status, AddTime, UpdateTime) VALUES
        (#adChannel.name#, #adChannel.owner#, #adChannel.author#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TE_AdContainer (Name, ChannelID, Author, Status, AddTime, UpdateTime) VALUES
        (#adContainer.name#, #adContainer.channelID#, #adContainer.author#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TE_AdDetail (Title, SubTitle, Thumb, ExtraContent, Link, `Desc`, Type, resourceId, Status, AddTime, UpdateTime) VALUES
        (#adDetail.title#, #adDetail.subTitle#, #adDetail.thumb#, #adDetail.extraContent#, #adDetail.link#, #adDetail.desc#, #adDetail.type#, #adDetail.resourceId#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TE_AdDimension (PublishID, CityID, Version, OrderNo, AdvertisementID, Type, Status, AddTime, UpdateTime) VALUES
        (#adDimension.publishID#, #adDimension.cityID#, #adDimension.version#, #adDimension.orderNo#, #adDimension.advertisementID#, #adDimension.type#, 1, NOW(), NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TE_AdPublish (Position, Title, Status, Author, Auditor, AddTime, BeginDate, EndDate, AuditTime, UpdateTime) VALUES
        (#adPublish.position#, #adPublish.title#, 1, #adPublish.author#, #adPublish.auditor#, NOW(), #adPublish.beginDate#, #adPublish.endDate#, #adPublish.auditTime#, NOW())
    
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TPDA_DealGroupEditor
                (DealGroupID,
                 EditorID,
                 CreateBy,
                 UpdateBy,
                 CreateTime,
                 UpdateTime)
        VALUES(#entity.dealGroupId#,
              #entity.editorId#,
              #entity.createBy#,
            #entity.updateBy#,
            #entity.createTime#,
            #entity.updateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TS_City
		    (
			CityID,
			CityName,
			Status,
			AddTime,
			UpdateTime)
		values
		(
			#cityData.cityId#,
			#cityData.cityName#,
			#cityData.status#,
			#cityData.addTime#,
			#cityData.updateTime#
		)
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO TS_ReturnQuota
		            (OutBizID,
		             SettleAccountID,
		             GuaranteeAccountID,
		             CustomerID,
		             ReturnAmount,
		             Status,
		             AddTime,
		             UpdateTime)
			VALUES (#returnQuotaData.outBizId#,
			        #returnQuotaData.settleAccountId#,
			        #returnQuotaData.guaranteeAccountId#,
		            #returnQuotaData.customerId#,
		            #returnQuotaData.returnAmount#,
		            #returnQuotaData.status#,
		            NOW(),
		            NOW()
		            );
 		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into TS_TotalPool
		(	Id,
			MaxAmount,
			OwnerID,
			Status,
			Comment,
			AddTime,
			UpdateTime)
		values
		(
			#totalPoolData.id#,
			#totalPoolData.maxAmount#,
			#totalPoolData.ownerId#,
			#totalPoolData.status#,
			#totalPoolData.comment#,
			now(),
			now()
		)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_MIGRATION_ATTRIBUTE_REGULATION
			(CATEGORY_ID,
            PRODUCT_TYPE,
            ATTRIBUTE_KEY,
            PREPARED_VALUES,
            REG_TYPE,
            REG_EX,
            VALUE_OF_REG_EX_MATCHED,
            VALUE_OF_REG_EX_UNMATCHED,
            VALUE_OF_GROUP_INDEX
			)
		VALUES
			(#entity.categoryId#,
			#entity.productType#,
			#entity.attributeKey#,
			#entity.preparedValues#,
			#entity.regType#,
			#entity.regEx#,
			#entity.valueOfRegExMatched#,
			#entity.valueOfRegExUnmatched#,
			#entity.valueOfGroupIndex#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT ignore into
		TA_ShopThirdParty(thirdpartyid,thirdpartyshopid,`interval`,mindelivery,latestdelivery,servtimejson,
		minfee,discount,status,
		mindeliverfee,distance, picture, geojson, tips)
		VALUES(#thirdpartyid#, #thirdpartyshopid#,
		#interval#, #mindelivery#,
		#latestdelivery#,#servtimejson#,#minfee#, #discount#,
		#status#, 
		#mindeliverfee#,
		#distance#, #picture# , #geojson#, #tips#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EBIZ_RefundDeliver(
			RefundDeliverId,
            ApplyId,
            DeliverNo,
            DeliverCompanyId,
            CompanyName,
            Memo,
			AddDate,
			UpdateDate,
            Address
		)VALUES(
			#refundDeliver.refundDeliverId#,
			#refundDeliver.applyId#,
			#refundDeliver.deliverNo#,
			#refundDeliver.deliverCompanyId#,
			#refundDeliver.companyName#,
			#refundDeliver.memo#,
			#refundDeliver.addDate#,
			#refundDeliver.updateDate#,
            #refundDeliver.address#)
		   <selectKey keyProperty="pId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_TemplateAttributeAssn
     (AttributeId,
    AttributeType,
    TemplateId,
    Sequence,
    IsRequired,
    ParentAttributeId,
    ParentAttributeOptionId,
    TemplateAttributeAssnId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.attributeId#,
    #entity.attributeType#,
    #entity.templateId#,
    #entity.sequence#,
    #entity.isRequired#,
    #entity.parentAttributeId#,
    #entity.parentAttributeOptionId#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPA_ProcessHistoryActor (ProcessID, NodeName, UserID, UserType, AddTime)
        VALUES (#processID#, #nodeName#, #userID#, #userType#, NOW())
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptResetBatch 
		(AdminID,AddTime)  VALUES (#adminId#,Now());		
		   <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptResetPool 
		(ReceiptID,OldSerialNumber,BatchID,NewSerialNumber,AddTime,Status,UpdateTime)
		select ReceiptID,SerialNumber,#batchId#,'',NOW(),0,NOW() from TG_Receipt
		where ReceiptID in 
		   <iterate open="(" conjunction="," property="receiptIds" close=")"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_MovieReservation
		(MovieReservation_ID,ThirdPartyOrderID,ThirdPartyID,OrderID,MovieShowID,
		Status,UserId,MobileNo,SeatInfo,TicketCount,TicketID,TicketNO,AddDate,UpdateDate)
		VALUES
		(#id#,#thirdPartyOrderId#,#thirdPartyId#,#orderId#,#movieShowId#,
		#status#,#userId#,#mobileNo#,#seatInfo#,#ticketCount#,#ticketId#,#ticketNO#,now(),now())
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupRemind(
		RemindId,
		UserId,
		ShopGroupId,
		ShopGroupName,
		Status,
		AddDate,
		LastDate,
		DealGroupID,
		CityID,
		ReferType
		)VALUES(
		#dealGroupRemind.remindId#,
		#dealGroupRemind.userId#,
		#dealGroupRemind.shopGroupId#,
		#dealGroupRemind.shopGroupName#,
		#dealGroupRemind.status#,
		#dealGroupRemind.addDate#,
		#dealGroupRemind.lastDate#,
		#dealGroupRemind.dealGroupId#,
		#dealGroupRemind.cityId#,
		#dealGroupRemind.referType#);
		   <selectKey keyProperty="remindId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="deliverAddressID" resultClass="int"/>

    insert into TG_DeliverAddress (DeliverAddressID, UserID, Consignee, Address, PostCode, PhoneNo,
      IsDefault, Status, AddDate, UpdateDate, Province, City, District, DeliverTime, InvoiceTitle,
      NeedInvoice, Memo)
    values (#deliverAddressID:INTEGER#, #userID:INTEGER#, #consignee:VARCHAR#, #address:VARCHAR#,
      #postCode:VARCHAR#, #phoneNo:VARCHAR#, #isDefault:TINYINT#, #status:TINYINT#,
      #addDate:TIMESTAMP#, #updateDate:TIMESTAMP#, #province:INTEGER#, #city:INTEGER#,
      #district:INTEGER#, #deliverTime:TINYINT#, #invoiceTitle:VARCHAR#, #needInvoice:TINYINT#,
      #memo:VARCHAR#)
  </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_EventPrizeUser(EventID,UserID,UserMobile,CurrentJoin,MaxJoin,Period,EventDeaID,Entrance)
		VALUES 
			(#prizeUser.eventId#,#prizeUser.userId#,#prizeUser.mobile#,1,#prizeUser.maxJoin#,#prizeUser.period#,#prizeUser.eventDealId#,#prizeUser.entrance#)
		   <selectKey keyProperty="UID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TG_EventPrizeUserLog(EventID,UserID,UserMobile,UserNickName,PrizeID,PrizeName,Source,Entrance,IP,Memo) 
		VALUES 
			(#log.eventId#,#log.userId#,#log.userMobile#,#log.userNickName#,#log.prizeId#,#log.prizeName#,#log.source#,#log.entrance#,#log.ip#,#log.memo#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_EventUserPrize(UserID,UserMobile,EventID,Amount,Value,PrizedDate)
		VALUES 
			(#log.userId#,#log.userMobile#,#log.eventId#,1,#log.prizeValue#,#log.period#)
		   <selectKey keyProperty="UID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TG_EventUserPrizeLog(EventID,UserID,UserMobile,UserNickName,PrizeID,PrizeName,PrizeValue,Source,Entrance,IP,Memo) 
		VALUES 
			(#log.eventId#,#log.userId#,#log.userMobile#,#log.userNickName#,#log.prizeId#,#log.prizeName#,#log.prizeValue#,#log.source#,#log.entrance#,#log.ip#,#log.memo#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGE_SecondOrder 
		(
			SecondPrizeID,
			UserID,
			Status,
			Quantity,
			IpAddress,
			MobileNo,
			HippoId,
			AddDate
		)
		VALUES
		(
			#data.secondPrizeId#,
			#data.userId#,
			#data.status#,
			#data.quantity#,
			#data.ipAddress#,
			#data.mobileNo#,
			#data.hippoId#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGHT_ContractAccount
		(
			ContractID,
			ShopIDList,
			AccountName,
			AccountNO,
			BankName,
			Memo,
			AccountType,
			HasProvidePayPaper
		)
		VALUES
		(
			#contractID#,
			#shopIDList#,
			#accountName#,
			#accountNO#,
			#bankName#,
			#memo#,
			#accountType#,
			#hasProvidePayPaper#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGHT_ContractDealGroup 
		(
			ContractID,
			DealGroupID,
			IsFromOrder,
			DealPlanStatus
		)
		VALUES
		(
			#id#,
			#dealGroupId#,
			#isFromOrder#,
			#dealPlanStatus#

		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO TGHT_DealDataChangeLog
	(
		TableName,
		ColoumName,
		ColoumValue,
		ConditionName,
		ConditionValue,
		AddTime
	)
	VALUES
	(
		#tableName#,
		#coloumName#,
		#coloumValue#,
		#conditionName#,
		#conditionValue#,
		Now()
	)
	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupCity
		(
		DealGroupID,
		CityID
		)
		VALUES
		(
		#dealGroupId#,
		#cityId#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	INSERT INTO TG_PreviewPool
	(UpdateTime) VALUES (NOW())
	   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	insert into Mail_SectionSend
			(Type,AddNum,NextIndex,Status,AddTime,CreateDate)
    	values
    		(#type#,#addNum#,#nextIndex#,#status#,#addTime#,#createDate#)
    	   <selectKey keyProperty="sectionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Recommend_NavModule(CityID,ModuleType,Version,CategoryID,DistrictID,DealGroupIDs, AddDate,UpdateDate)
			VALUES (#cityId#, #moduleType#, #version#,#categoryID#,#districtID#,#dealGroupIDs#,NOW(),NOW()) 
			ON DUPLICATE KEY
			UPDATE DealGroupIDs=#dealGroupIDs#, UpdateDate=NOW()
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TG_EventDeal(DealGroupID,EventDealTitle,EventDealDesc,Status,AddDate,BeginDate,EndDate,EventID,CityID)
		VALUES
			(#deal.dealGroupId#,#deal.title#,#deal.desc#,1,#deal.addDate#,#deal.beginDate#,#deal.endDate#,#deal.eventId#,#deal.cityId#)
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TG_EventDeal(DealGroupID,EventDealTitle,EventDealDesc,Status,AddDate,BeginDate,EndDate,EventID,CityID)
		   <iterate open="(" prepend="VALUES" conjunction="," property="deals" close=")"/>
   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			GPA_OnlineActivity
		VALUES
			(NULL,
			#onlineActivity.beginTime#,
			#onlineActivity.endTime#,
			#onlineActivity.title#,
			#onlineActivity.contentType#,
			#onlineActivity.contentFilterField#,
			#onlineActivity.createUserId#,
			#onlineActivity.createAdminId#,
			#onlineActivity.body#,
			#onlineActivity.info#,
			#onlineActivity.bannerPicUrl#,
			#onlineActivity.cssTemplate#,
			0,
			0,
			#onlineActivity.status#,
			NOW(),
			NOW())
		   <selectKey keyProperty="onlineActivityId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardShop(MemberCardID,ShopID,CityID,ShopName,BranchName,Status, AddTime, ShopGroupId, CardGroupID, SecondCatgory, Region, Lat, Lng, POWER)
			VALUES(#memberCardId#, #shopId#, #cityId#, #shopName#, #branchName#, #status#, #addTime#, #shopGroupId#, #cardGroupId#,#category#,#region#,#lat#,#lng#,#power#)
			
		   <selectKey keyProperty="MemberCardShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO MC_MemberCardShopConfirmInfo 
				(CrmID, 
				CompanyConfirmInfoID, 
				ShopID, 
				ShopName, 
				ShopManager, 
				ShopManagerTel, 
				ProductSelected, 
				OtherMemberCard, 
				AuthAdminID, 
				AuthAdminName, 
				CreateTime, 
				UpdateTime,
				CheckedForBossAccount,
				)
				VALUES
				(#crmID#, 
				#companyConfirmInfoID#, 
				#shopID#, 
				#shopName#, 
				#shopManager#, 
				#shopManagerTel#, 
				#productSelected#, 
				#otherMemberCard#, 
				#authAdminID#, 
				#authAdminName#, 
				Now(), 
				Now(),
				0);
		
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		 INSERT INTO DP_MyListShop (ListID,ShopID,AddDate,Reason
		    <isNotEmpty prepend="," property="verifyStatus"/>

		 ,Sort) VALUES
		 (#listId#,#shopId#,now(),#reason#   <isNotEmpty prepend="," property="verifyStatus"/>
,#sort#)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MyList (UserID, Title,Content,AddDATE, UpdateDate,VerifyStatus,ShopCount,IsSeo,CreateType)
		VALUES
		(#userId#,#title#,#content#,now(),now(),#verifyStatus#,0,#isSeo#,#createType#);
		   <selectKey keyProperty="listId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO HY_ManaPendingList (
		ActionType, ActionTarget, ADDTIME, STATUS
		   <isNotEmpty prepend="," property="userId"/>

		) VALUES 
		(#actionType#, #listId#, NOW(), 0
		   <isNotEmpty prepend="," property="userId"/>

		);
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_MyListTesecai
		(Dish,
		Cities,
		Categories,
		Regions,
		Sort,
		IsGetShopFromDishTag,
		Title,
		Context,
		ADDTIME,
		UpdateTime,
		ListTagIDs,
		CreateType
		)
		VALUES
		(#dish#,
		#cities#,
		#categories#,
		#regions#,
		#sort#,
		#isGetShopFromDishTag#,
		#title#,
		#context#,
		NOW(),
		NOW(),
		#listTags#,
		#createType#
		);
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DianPing.DP_VoteRankTagRecord
        	(TagID, VoteRankID, AddDate)
        VALUES
           <iterate conjunction="," property="tagList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviDealCategory (DealGroupID,ChannelID,CategoryID,ADDDATE,IsMain)
		SELECT b.dealGroupId,#channelId#, #categoryId#,Now(),0
		FROM TG_NaviDealCategory a
        RIGHT JOIN TG_NaviDealTag b
            ON a.DealGroupID=b.DealGroupID AND a.CategoryID=#categoryId#
        WHERE a.DealGroupID IS NULL AND b.ItemID=#itemId#  AND b.TagID=#tagId#
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviDealCategory (DealGroupID,ChannelID,CategoryID,ADDDATE,IsMain)
		SELECT b.dealGroupId,#channelId#, #categoryId#,Now(),0
		FROM TG_NaviDealCategory a
        RIGHT JOIN TG_NaviDealCategory b
            ON a.DealGroupID=b.DealGroupID AND a.CategoryID=#categoryId#
        WHERE a.DealGroupID IS NULL AND b.CategoryID=#byCategoryId# AND b.ChannelID=#byChannelId#
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO TG_DealPriceReduceForPM
    	(DealGroupID ,DealID ,Price ,ReducePriceByPM ,PMName ,DealGroupTitle ,MainCategoryID ,Status ,AddTime ,UpdateTime)
    	VALUES(
    	#entity.dealGroupId#,
    	#entity.dealID#,
    	#entity.price#,
    	#entity.reducePriceByPM#,
    	#entity.PMName#,
    	#entity.dealGroupTitle#,
    	#entity.mainCategoryId#,
    	#entity.status#,
    	NOW(),
    	NOW())
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TGThirdParty.TG_Settlement_User
        (
        UserName,
        InviteCode,
        ICStatus,
        IllegalAction,
        Description
        )
        VALUES
        (
        #userName#,
        #inviteCode#,
        #icStatus#,
        #illegalAction#,
        #description#
        )
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TGThirdParty.TG_Settlement_Source
        (
        SourceName,
        Description
        )
        VALUES
        (
        #sourceName#,
        #description#
        )
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsTrack
		(
			ScenarioID,
			ChannelID,
			ChannelCode,
			TrackingCode,
			ExtraData,
			OrderID,
			UserID,
			CityID,
			BizID,
			BizType,
			Status,
			Quantity,
			Price,
			AddDate,
			LowPriceDeal
		)
		VALUES
		(
			#scenarioId#,
			#channelId#,
			#channelCode#,
			#trackingCode#,
			#extraData#,
			#orderId#,
			#userId#,
			#cityId#,
			#bizId#,
			#bizType#,
			#status#,
			#quantity#,
			#price#,
			#addDate#,
			#lowPriceDeal#
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT  INTO TG_ThirdUserOrder
        (
        ThirdUserOrderType,
        OrderID,
        UserID,
        CityID,
        DealGroupID,
        DealID,
        Quantity,
        SuccessDate,
        ThirdUserID,
        ThirdUserType,
        DealPrice,
        AddDate,
        ChannelCode,
        TrackingCode,
        UserStatus,
        RefundQuantity
        )
        VALUES
        (
        #thirdUserOrderType#,
        #orderId#,
        #userId#,
        #cityId#,
        #dealGroupId#,
        #dealId#,
        #quantity#,
        #successDate#,
        #thirdUserId#,
        #thirdUserType#,
        #dealPrice#,
        #addDate#,
        #channelCode#,
        #trackingCode#,
        #userStatus#,
        #refundQuantity#
        )
           <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

          INSERT INTO TGE_UserPurchaseLog
          SET
            UserId =#userId#,
            UserMobile =#moblie#,
            EventKey =#eventKey#,
            ReferID =#cid#,
            DPID = "",
            CreateTime=now()
           <selectKey keyProperty="logId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_CompanyUserIntegrityLog(UserId,UserIntegrity,MobileNo,ChannelName,RegTime,AddTime)
         VALUES(#userId#,#userIntegrity#,#mobileNo#,#channelName#,#regTime#,#addTime#)
           <selectKey keyProperty="eventID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TGE_UserDonation
			(UserID, UserName, UserMobile, Source, Money, DealGroupId, AddTime)
		VALUES
			(#userId#, #userName#, #mobile#, #source#, #money#, #dealGroupId#, NOW())
		   <selectKey keyProperty="DonationID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGE_WeixinGiftUserInfo (UserID, OpenID, MobileNo, UserType, WeixinNickName,
        WeixinHeadImg, WeixinSex, WeixinCity, WeixinCountry, WeixinProvince, ADDTIME, UpdateTime)
        VALUES (#userInfo.userID#, #userInfo.openId#, #userInfo.mobileNO#, #userInfo.userType#,
        #userInfo.wexinNickName#, #userInfo.weixinHeadImg#, #userInfo.weixinSex#,
        #userInfo.weixinCity#, #userInfo.weixinCountry#, #userInfo.weixinProvince#, NOW(), NOW())
        
           <selectKey keyProperty="weixinGiftUserInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGE_WeixinGiftInfo (OwnerID, GiftValue, GiftSource, StartTime, EndTime, ToValidDays, Consumption,
        Stock,  InitStock, GiftStatus, ShowStatus, ShowNum, ShowType, ADDTIME, UpdateTime)
        VALUES (#giftInfo.ownerID#, #giftInfo.giftValue#, #giftInfo.giftSource#, #giftInfo.startTime#, #giftInfo.endTime#,
        #giftInfo.toValidDays#, 0, #giftInfo.stock#,  #giftInfo.stock#, 1, 0, #giftInfo.stock#, 0, NOW(), NOW())
        
           <selectKey keyProperty="weixinGiftID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
       INSERT INTO TGE_WeixinGiftGrabLog (
              WeixinGiftID,
              GiftRecieverID,
              GiftValue,
              ReferID,
              CityID,
              OpenID,
              MobileNo,
              ADDTIME,
              UpdateTime
            )
            VALUES
              (
              #grabLog.weixinGiftID#,
              #grabLog.giftRecieverID#,
               #grabLog.giftValue#,
               #grabLog.referID#,
               #grabLog.cityId#,
               #grabLog.openId#,
               #grabLog.mobile#,
               NOW(), NOW())
        
           <selectKey keyProperty="weixinGiftGrabLogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGE_WeixinGiftReceiptLog (DealGroupID, DealID, DealGroupTitle, Price, UserID, GiftID, CityID, Status, AddDate, UpdateDate)
        VALUES (#giftReceiptLog.dealGroupID#, #giftReceiptLog.dealID#,#giftReceiptLog.dealGroupTitle#,#giftReceiptLog.price#,#giftReceiptLog.userID#,#giftReceiptLog.giftID#,#giftReceiptLog.cityID#,0,NOW(),NOW())
        
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          TGE_WeixinGiftAddtionLog
        SET
        UserID = #log.userID#,
        WeixinGiftID = #log.weixinGiftID#,
        AddtionNum = #log.addtionNum#,
        GiftStartTime = #log.giftStartTime#,
        GiftEndTime = #log.giftEndTime#,
        MobileNo = #log.mobile#,
        OpenID=#log.openId#,
        Ip =#log.ip#,
        ADDTIME = NOW()
           <selectKey keyProperty="logId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        TGE_WeixinGiftVIPUserApplyChannel
        SET
        TokenKey = #channel.key#,
        UserID = #channel.userId#,
        Status = 0,
        AddTime = NOW(),
        UpdateTime = NOW(),
        StartTime = #channel.startTime#,
        EndTime=#channel.endTime#,
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          TGE_WeixinGiftShareEmbraveLog
        SET
            WeixinGiftID =#weixinGiftId#,
            UserID =#userId#,
            EmbraveTrigger =#embraveTrigger#,
            ReferID =#referId#,
            ADDTIME= NOW()
           <selectKey keyProperty="ShareEmbraveLogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_OpModule
		SET  
			Name=#moduleInfo.moduleName#, ParentID=#moduleInfo.parentModuleId#,
			Status=#moduleInfo.status#, OrderNbr=#moduleInfo.orderNbr#, AddTime=#moduleInfo.addTime#, UpdateTime=#moduleInfo.updateTime#
           <selectKey keyProperty="moduleId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_OpRight
		SET  
			Name=#rightInfo.rightName#, Desc=#rightInfo.desc#,
			Status=#rightInfo.status#, AddTime=#rightInfo.addTime#, UpdateTime=#rightInfo.updateTime#
           <selectKey keyProperty="rightId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_OpAction
		SET  
			Name=#actionInfo.name#, ActionURL=#actionInfo.actionURL#, Status=#actionInfo.status#, 
			AddTime=#actionInfo.addTime#, UpdateTime=#actionInfo.updateTime#, ModuleID=#actionInfo.moduleId#, RightID=#actionInfo.rightId#
           <selectKey keyProperty="actionId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ModuleBlack 
		(
			ModuleKey,
			IntValue,
			TextValue,
			Status,
			AddDate,
			UpdateDate
		)
		VALUES
		(
			#data.moduleKey#,
			#data.intValue#,
			#data.textValue#,
			#data.status#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ShoppingCart 
		(
			UserID,
			DealGroupID,
			DealID,
			Quantity,
			Status,
			RemoveMemo,
			ADDDATE
		)
		VALUES
		(
			#data.userId#,
			#data.dealGroupId#,
			#data.dealId#,
			#data.quantity#,
			#data.status#,
			#data.removeMemo#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO {tableName} 
		(
{insertFieldList}
		)
		VALUES
		(
{valueList}
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_Holiday
         (
            Holiday,
            Title,
            BeginDate,
            EndDate,
            CreateTime,
            UpdateTime,
            CreateBy,
            UpdateBy
        )
        VALUES
            (
            #entity.holiday#,
            #entity.title#,
            #entity.beginDate#,
            #entity.endDate#,
            #entity.createTime#,
            #entity.updateTime#,
            #entity.createBy#,
            #entity.updateBy#
            );
		

           <selectKey keyProperty="holiday" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event
        SET EventTitle=#promoEvent.eventTitle#, EventType=#promoEvent.eventType#, Status=#promoEvent.status#, BeginDate=#promoEvent.beginDate#, 
        EndDate=#promoEvent.endDate#, Icon=#promoEvent.icon#,EventDesc=#promoEvent.eventDesc#, Notice=#promoEvent.notice#, EventEnName="", Author=#promoEvent.author#, AddDate=NOW();
           <selectKey keyProperty="eventID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventCity (EventID, CityID, Status) VALUES
           <iterate conjunction="," property="eventCities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventPromo
        SET EventID=#promoInfo.eventID#, Type=#promoInfo.type#, Quota=#promoInfo.quota#, Stock=#promoInfo.stock#, 
        CouponRuleID=#promoInfo.couponRuleID#, AddDate=NOW(), UpdateDate=NOW(), BeginDate=#promoInfo.beginDate#, EndDate=#promoInfo.endDate#
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventPromoCond
        SET PromoID=#promoCond.promoID#, ValidOrderPlatform=#promoCond.validOrderPlatform#, ValidPayPlatform=#promoCond.validPayPlatform#, ValidPaymentChannel=#promoCond.validPaymentChannel#, 
        MaxPerUser=#promoCond.maxPerUser#, MaxPerDeal=#promoCond.maxPerDeal#, LimitOrderAmount=#promoCond.limitOrderAmount#, CategoryList=#promoCond.categoryList#, ShopList=#promoCond.shopList#,
        BuyLimitType=#promoCond.buyLimitType#, UseLimitType=#promoCond.useLimitType#, UserPropertyType=#promoCond.userPropertyType#
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventDeal (EventID, CityID, DealGroupID, Status, AddDate, BeginDate, EndDate, UpdateDate) VALUES
           <iterate conjunction="," property="eventDeals"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_Event
		SET 
			EventTitle=#event.eventTitle#, EventEnName=#event.eventName#, EventType=#event.eventType#, Status=#event.status#, EventDesc=#event.eventDesc#,
			EventHref=#event.eventHref#, Notice=#event.notice#, TagItemEnName=#event.tagEnName#, TagItemChName=#event.tagName#,
			Author=#event.author#, BeginDate=#event.beginDate#, EndDate=#event.endDate#, AddDate=NOW()
		   <selectKey keyProperty="eventID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_EventCity(EventID, CityID, Status)
		VALUES
		   <iterate conjunction="," property="cityIds"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_CompanyUserCouponCodeLog(
            MobileNo,
            IP,
            CouponCode,
            CompanyName,
            UserID,
            UserType,
            ReferID
        )VALUES(
            #mobileNo#,
            #ip#,
            #couponCode#,
            #companyName#,
            #uid#,
            #userType#,
            #referId#)
           <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_CompanyUserIntegrityLog(
            MobileNo,
            UserId,
            ChannelName,
            UserIntegrity,
            AddTime
        )VALUES(
            #mobileNo#,
            #userId#,
            #channelName#,
            #userIntegrity#,
            #addTime#)
           <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupAttribute(
        DealGroupAttributeID,
        DealGroupID,
        AttributeKey,
        AttributeValue,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.id#,
        #entity.dealGroupId#,
        #entity.attributeKey#,
        #entity.attributeValue#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT_ATTRIBUTE 
			(STATEMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			SEQUENCE,
			FRONT_END_INPUT_TYPE,
			LABEL_CONTENT,
			LENGTH)
		VALUES
			(#entity.statementTemplate.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.sequence#,
			#entity.frontEndInputType#,
			#entity.labelContent#,
			#entity.length#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_ACCOUNT
            (CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            ACCOUNT_GLOBAL_ID,
            HAS_DEAL_GROUP_APPROVED,
            HAS_CRM_DEAL_GROUP_APPROVED
            )
        VALUES
            (#entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.accountGlobalId#,
            #entity.hasDealGroupApproved#,
            #entity.hasCRMDealGroupApproved#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_CARD
            (CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            NAME_TYPE,
            NAME,

            BACKGROUND_PICTURE_PATH,
            CATEGORY,
            CHARGE_AMOUNT,
            GIFT_AMOUNT,
            COMMISSION_RATE,
            DEAL_ID,
            CARD_TYPE,
            DISCOUNT
            )
        VALUES
            (#entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.nameType#,
            #entity.name#,

            #entity.backgroundPicPath#,
            #entity.category#,
            #entity.chargeAmount#,
            #entity.giftAmount#,
            #entity.commissionRate# ,
             #entity.dealId#,
               #entity.cardType#,
                #entity.discount#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		 INSERT INTO TGP_CORP_INFO
         (
		CREATOR_ID,
		LAST_UPDATOR_ID,
		CREATE_TIME,
		LAST_UPDATE_TIME,
		NAME,
		DESCRIPTION,
		IS_EXPANDED,
		VISUAL_VIEW_ID)
         VALUES (#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#,
         #entity.name#,#entity.description#,#entity.isExpanded#,#entity.visualView.id#)
		
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_SHOP_ASSN 
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		DEAL_ID,
		POI_SHOP_ID,
		SHOP_ID_TYPE)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.dealItem.id#,
		#entity.poiShopId#,
		#entity.shopIdType#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_EDITOR_TEAM
            (TEAM_NAME,
             TEAM_LEADER_ID,
             TEAM_LEADER_NAME,
             DEAL_GROUP_PRODUCE_TYPE,
             DEAL_GROUP_CATEGORY_ID,
             IS_ECOMMERCE,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             CREATE_TIME,
             LAST_UPDATE_TIME)
        VALUES (#entity.teamName#,
                #entity.teamLeaderId#,
                #entity.teamLeaderName#,
                #entity.dealGroupProduceTypeList,handler=dealGroupProduceTypeEnumsTypeHandler#,
                #entity.dealGroupCategoryId#,
                #entity.isEcommerce#,
                #entity.creatorId#,
                #entity.lastUpdatorId#,
                #entity.createTime#,
                #entity.lastUpdateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_FILE_ATTACHMENT 
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		FILE_NAME,
		RELATIVE_PATH,
		DEAL_GROUP_ID,
		DTYPE)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.fileName#,
		#entity.relativePath#,
		#entity.dealGroup.id#,
		'FileAttachment'
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_MESSAGE_QUEUE
            (COMMENT,
            TOPIC,
            CONTENT,
            MAX_RETRY_TIMES,
            CURRENT_RETRY_TIMES,
            STATUS_ID,
            CREATE_TIME,
            LAST_EXECUTE_TIME,
            NEXT_EXECUTE_TIME
            )
        VALUES
            (#entity.comment#,
            #entity.topic#,
            #entity.content#,
            #entity.maxRetryTimes#,
            #entity.currentRetryTimes#,
            #entity.statusId#,
            #entity.createTime#,
            #entity.lastExecuteTime#,
            #entity.nextExecuteTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_RESOURCE_AUTHORITY_CONFIG
            (
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            RESOURCE_CODE,
            ACTION_ID,
            POWER_CODE,
            PUBLISH_STATUS_ID,
            PROCESS_STATUS_ID
            )
        VALUES
            (
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.resourceCode#,
            #entity.actionId#,
            #entity.powerCode#,
            #entity.publishStatusId#,
            #entity.processStatusId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_SERIAL_NUM_OPERATION_LOG
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VERSION_ID,
		DEAL_ID,TOTAL_COUNT,DUPLICATED_COUNT,IMPORTED_COUNT,
        BATCH_NAME,
        IS_REMOVED,
        DUPLICATED_SERIAL_NUMBERS,
		DTYPE)
		VALUES
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.versionId#,
		#entity.dealId#,#entity.totalCount#,#entity.duplicatedCount#,#entity.importedCount#,
		#entity.batchName#,
		#entity.isRemoved#,
		#entity.duplicatedSerialNumbers#,
		'SerialNumberImportLog')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_TOP_CITY_INFO
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		BEGIN_DATE,
		END_DATE,
		DEAL_GROUP_ID,
		CITY_ID)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.beginDate#,
		#entity.endDate#,
		#entity.dealGroup.id#,
		#entity.cityId#
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPA_Process (ProcessType, SubjectID, UserID, UserType, Status, Version, AddTime, UpdateTime)
        VALUES (#processType#, #subjectID#, #userID#, #userType#, #status#, #version#, NOW(), NOW())
           <selectKey keyProperty="processID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TPDA_Deal
			(
			 DealStatus,
			 DealGroupID,
			 CreateTime,
			 UpdateTime,
			 CreateBy,
			 UpdateBy
			)
		VALUES
			(
			#entity.dealStatus#,
			#entity.dealGroupId#,
			#entity.createTime#,
			#entity.updateTime#,
			#entity.createBy#,
			#entity.updateBy#
			)
		

           <selectKey keyProperty="dealId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_Template
     (Title,
    Status,
    ParentTemplateId,
    ProductType,
    TemplateId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.title#,
    #entity.status#,
    #entity.parentTemplateId#,
    #entity.productType#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

      
      insert into TA_NewPartnerComboAssoc (thirdpartyId, comboId, shopId, dishId, groupId, dishOrder, isDefault, 
        isRequired, markupPrice, feature1, feature2, feature3, createDate)
      values ( #newPartnerComboAssoc.thirdpartyId#, #newPartnerComboAssoc.comboId#, #newPartnerComboAssoc.shopId#, #newPartnerComboAssoc.dishId#, #newPartnerComboAssoc.groupId#, #newPartnerComboAssoc.dishOrder#, #newPartnerComboAssoc.isDefault#, 
        #newPartnerComboAssoc.isRequired#, #newPartnerComboAssoc.markupPrice#, #newPartnerComboAssoc.feature1#, #newPartnerComboAssoc.feature2#, #newPartnerComboAssoc.feature3#, #newPartnerComboAssoc.createDate#)
      
         <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

      insert into TA_NewPartnerComboGroup
         <dynamic prepend="(">
      <isNotNull prepend="," property="newPartnerComboGroup.thirdpartyId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.comboId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.groupId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.shopId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.groupName"/>
      <isNotNull prepend="," property="newPartnerComboGroup.groupOrder"/>
      <isNotNull prepend="," property="newPartnerComboGroup.multiSupported"/>
      <isNotNull prepend="," property="newPartnerComboGroup.quantityLimit"/>
      <isNotNull prepend="," property="newPartnerComboGroup.createDate"/>
   </dynamic>

      values
         <dynamic prepend="(">
      <isNotNull prepend="," property="newPartnerComboGroup.thirdpartyId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.comboId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.groupId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.shopId"/>
      <isNotNull prepend="," property="newPartnerComboGroup.groupName"/>
      <isNotNull prepend="," property="newPartnerComboGroup.groupOrder"/>
      <isNotNull prepend="," property="newPartnerComboGroup.multiSupported"/>
      <isNotNull prepend="," property="newPartnerComboGroup.quantityLimit"/>
      <isNotNull prepend="," property="newPartnerComboGroup.createDate"/>
   </dynamic>
   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_MIGRATION_SOURCE
			(DEAL_GROUP_ID,
			STATUS_ID,
			CREATE_TIME,
			LAST_UPDATE_TIME
			)
		VALUES
			(#entity.dealGroupId#,
			#entity.statusId#,
			#entity.createTime#,
			#entity.lastUpdateTime#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		TA_Dish(ShopKey,DishID,DishName,Category,Price,Box,Comment,Valid,SoldOut,DishSet,Img)
		VALUES(#shopId#,#dishId#,#dishName#,#category#,#price#,#box#,#comment#,#valid#,#soldout#,#dishSet#,#pictureUrl#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		TA_RecommendDish(ShopKey,DishID,ShopID,DishName,TagCount,PicUrl,Valid)
		VALUES(#shopkey#,#dishid#,#shopid#,#dishname#,#tagcount#,#picurl#,1)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_OpModule
		SET  
			Name=#moduleInfo.moduleName#,ModuleDesc=#moduleInfo.moduleDesc#, ParentID=#moduleInfo.parentModuleId#, RootID=#moduleInfo.rootModuleId#,
			Status=#moduleInfo.status#, OrderNbr=#moduleInfo.orderNbr#, AddTime=NOW(), UpdateTime=NOW()
           <selectKey keyProperty="ID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO
			TG_OpRight
		SET  
			Name=#rightInfo.rightName#, `Desc`=#rightInfo.desc#,
			Status=#rightInfo.status#, AddTime=NOW(), UpdateTime=NOW()
     
           <selectKey keyProperty="ID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_OpAction
		SET  
			Name=#actionInfo.name#, ActionDesc=#actionInfo.actionDesc#, ActionURL=#actionInfo.actionURL#, ActionParam=#actionInfo.actionParamExp#, Status=#actionInfo.status#, 
			AddTime=NOW(), UpdateTime=NOW(), ModuleID=#actionInfo.moduleId#, RightID=#actionInfo.rightId#
           <selectKey keyProperty="ID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO
			TG_OpRole
		SET  
			Name=#roleInfo.roleName#, EnName=#roleInfo.roleEnName#, `Desc`=#roleInfo.desc#, Status=#roleInfo.status#, 
			AddTime=NOW(), UpdateTime=NOW()
	
           <selectKey keyProperty="ID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_OpRightRole (RightID, RoleID, ModuleID, Status, AddTime, UpdateTime) VALUES
           <iterate conjunction="," property="roleRights"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_OpUserRole 
        SET  
        	UserName=#userRole.userName#, AccessSourceID=#userRole.accessSourceId#, RoleID=#userRole.roleId#, CityIDs=#userRole.cityIds#, Status=1, AddTime=NOW(), UpdateTime=NOW(), Type=#userRole.type#
           <selectKey keyProperty="ID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TE_AuditLog
        SET AuthStr   = #authStr#,
        Auditor       = #auditor#,
        AuditType     = #auditType#,
        AuditItem     = #auditItem#,
        AuditStatus   = #auditStatus#,
        LogContext    = #logContext#,
        AddTime       = NOW()

           <selectKey keyProperty="AuditLogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroup(
        CategoryID,
        ProductType,
        SaleBeginDate,
        SaleEndDate,
        EffectiveBeginDate,
        EffectiveEndDate,
        MaxSaleQty,
        MinSaleQty,
        RefundType,
        VoucherType,
        Title,
        Summary,
        PurchaseProcessType,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.categoryId#,
        #entity.productType#,
        #entity.saleBeginDate#,
        #entity.saleEndDate#,
        #entity.effectiveBeginDate#,
        #entity.effectiveEndDate#,
        #entity.maxSaleQty#,
        #entity.minSaleQty#,
        #entity.refundType#,
        #entity.voucherType#,
        #entity.title#,
        #entity.summary#,
        #entity.purchaseProcessType#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_ProcessLog(
        DealGroupID,
        SnapShot,
        Type,
        CreateBy,
        CreateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.snapShot#,
        #entity.type#,
        #entity.createBy#,
        #entity.createTime#
        );
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EBIZ_Apply(
			ApplyId,
			ApplyType,
			MobileNo,
			Status,
			Amount,
			OrderId,
			DealGroupId,
			DealId,
			AddDate,
			UpdateDate,
			UserId,
            Quantity
		)VALUES(
			#apply.applyId#,
			#apply.applyType#,
			#apply.mobileNo#,
			#apply.status#,
			#apply.amount#,
			#apply.orderId#,
			#apply.dealGroupId#,
			#apply.dealId#,
			#apply.addDate#,
			#apply.updateDate#,
			#apply.userId#,
            #apply.quantity#)
		   <selectKey keyProperty="pId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TA_Partner(partnerkey,secret,name,status,createtime)
		VALUES(#key#,#secret#,#name#,#status#,now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TA_NewPartnerShop(thirdpartyid,thirdpartyshopid,cityname,thirdpartyshopname,thirdpartyaddress,thirdpartyphone,thirdpartylat,thirdpartylng,coordtype,`interval`,starttime,endtime,minfee,discount,`status`,startresttime,endresttime,servtimejson,mindeliverfee,distance,thirdpartypicture,geojson,`type`,createtime)
		VALUES(#thirdpartyid#,#thirdpartyshopid#,#cityname#,#thirdpartyshopname#,#thirdpartyaddress#,#thirdpartyphone#,#thirdpartylat#,#thirdpartylng#,#coordtype#,#interval#,#starttime#,#endtime#,#minfee#,#discount#,#status#,#startresttime#,#endresttime#,#servtimejson#,#mindeliverfee#,#distance#,#thirdpartypicture#,#geojson#,#type#,#createtime#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TA_NewPartnerDish(
		thirdpartyid,
		thirdpartyshopid,
		dishid,
		dishname,
		category,
		price,
		box,
		`comment`,
		soldout,
		dishset
		   <isNotNull prepend="," property="img"/>
   <isNotNull prepend="," property="dishType"/>
   <isNotNull prepend="," property="fromDate"/>
   <isNotNull prepend="," property="toDate"/>
   <isNotNull prepend="," property="soldSingle"/>

        )
		VALUES(#thirdpartyid#,#thirdpartyshopid#,#dishid#,#dishname#,#category#,#price#,#box#,#comment#,#soldout#,
		#dishset#
		   <isNotNull prepend="," property="img"/>
   <isNotNull prepend="," property="dishType"/>
   <isNotNull prepend="," property="fromDate"/>
   <isNotNull prepend="," property="toDate"/>
   <isNotNull prepend="," property="soldSingle"/>

		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TE_TopicSkin (Name,EnName,PcHeadImg,PcTailImg,AppBannerImg,ItemBgColor,ItemLabelColor,ItemButtonColor,ItemBtnFontColor,ThumbNail,BgColor) VALUES (#ts.name#,#ts.enName#,#ts.pcHeadImg#,#ts.pcTailImg#,#ts.appBannerImg#,#ts.itemBgColor#,#ts.itemLabelColor#,#ts.itemButtonColor#,#ts.itemBtnFontColor#,#ts.thumbNail#,#ts.bgColor#)
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TE_Topic
        SET Title=#topic.title#, SubTitle=#topic.subTitle#, Status=#topic.status#, AuditStatus=#topic.auditStatus#, JoinAllowed=#topic.joinAllowed#, Author=#topic.author#, AuditComment=#topic.auditComment#, DeadLine=#topic.deadLine#, ChannelType=#topic.channelType#, AddTime=NOW(), UpdateTime=NOW()
        
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        	TE_TopicItem(Name,TopicID,Type,OrderNo,Status,BgColor,LabelColor,ButtonColor,BtnFontColor,UpdateTime)
        VALUES
           <iterate conjunction="," property="categories"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        	TE_TopicItem(Name,TopicID,Type,OrderNo,Status,BgColor,LabelColor,ButtonColor,BtnFontColor,UpdateTime)
        VALUES
       		(#category.name#,#category.topicID#,#category.type#,#category.orderNo#,#category.status#,#category.bgColor#,#category.labelColor#,#category.buttonColor#,#category.btnFontColor#,NOW())
       	   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TE_TopicCity(TopicID, CityID, Status, UpdateTime)
		VALUES
		   <iterate conjunction="," property="topicCities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TE_TopicDeal(TopicID,TopicItemID,CityID,DealGroupID,OrderNo,Status,AddTime,UpdateTime)
        VALUES 
        	   <iterate conjunction="," property="deals"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TE_TopicRule
        SET TopicID=#topicRule.topicID#, RuleIdRelationExp=#topicRule.ruleIdRelationExp#, AddTime=NOW(), UpdateTime=NOW()
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TE_DealSearchRule
        SET Name=#searchRule.name#, Type=#searchRule.type#, ValueType=#searchRule.valueType#, LeftValue=#searchRule.leftValue#, 
        	RightValue=#searchRule.rightValue#, AddTime=NOW()
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO	
			TE_TopicPublish(TopicID, PositionName, PositionTitle, PublishStatus, ChannelType, BannerImg, Author) 
		VALUES
		   <iterate conjunction="," property="topicAdList"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TE_TopicPositionBanner(TopicID, PositionName, ChannelType, BannerImg, Type, AddDate)
		VALUES
			   <iterate conjunction="," property="list"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_MIGRATION_LOG
			(DEAL_GROUP_ID,
            LOG_TYPE,
            CONTENT,
            SERVER_NAME,
            CREATE_TIME
			)
		VALUES
			(#entity.dealGroupId#,
			#entity.logType#,
			#entity.content#,
			#entity.serverName#,
			#entity.createTime#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPA_ProcessActor (ProcessID, NodeName, UserID, UserType, AddTime)
        VALUES (#processID#, #nodeName#, #userID#, #userType#, NOW())
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_RateReviewMapping (
        RateID,
        ShopID,
        ReviewID)
        VALUES
        (
        #data.rateId#,
        #data.shopId#,
        #data.reviewId#)
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Deal 
		(
			DealGroupID,
			DealTitle,
			DealName,
			DealSMSName,
			DealPrice,
			MarketPrice,
			DealSortID,
			MaxJoin,
			CurrentJoin,
			RemainDisplayPercent,
			FinishDate,
			DeliverType,
			ReceiptType,
			ProvideInvoice,
			DealShortTitle,
			IsDealPriceLargerThanCost,
            DealStatus
		)
		VALUES
		(
			#dealGroupId#,
			#dealTitle#,
			#dealName#,
			#dealSMSName#,
			#dealPrice#,
			#marketPrice#,
			#dealSortId#,
			#maxJoin#,
			#currentJoin#,
			#remainDisplayPercent#,
			#finishDate#,
			#deliverType#,
			#receiptType#,
			#provideInvoice#,
			#dealShortTitle#,
			#isDealPriceLargerThanCost#,
            #dealStatus#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroup
		(
		DealGroupShortTitle,
		DealGroupTitleDesc,
		DealGroupTitle,
		DealGroupName,
		DealGroupPrice,
		MarketPrice,
		DefaultPic,
		HighLight,
		DealGroupInfo,
		ShopInfo,
		BeginDate,
		EndDate,
		FinishDate,
		MaxPerUser,
		MaxJoin,
		CurrentJoin,
		RemainDisplayPercent,
		IsSideDeal,
		DealGroupSortID,
		Status,
		PublishStatus,
		PV,
		UV,
		IsReward,
		EditorInfo,
		ImportantPoint,
		MemberInfo,
		SpecialPoint,
		ProductInfo,
		LotteryStatus,
		QRCodeValidate,
		DealAltName,
		AutoRefundSwitch,
		IsCanUseCoupon,
		DealGroupPics,
		IsECommerceWebSite,
		SaleChannel,
		AddDate,
		UpdateDate
		)
		VALUES
		(
		#dealGroupShortTitle#,
		#dealGroupTitleDesc#,
		#dealGroupTitle#,
		#dealGroupName#,
		#dealGroupPrice#,
		#marketPrice#,
		#defaultPic#,
		#highLight#,
		#dealGroupInfo#,
		#shopInfo#,
		#beginDate#,
		#endDate#,
		#finishDate#,
		#maxPerUser#,
		#maxJoin#,
		#currentJoin#,
		#remainDisplayPercent#,
		#isSideDeal#,
		#dealGroupSortId#,
		#status#,
		#publishStatus#,
		#pV#,
		#uV#,
		#isReward#,
		#editorInfo#,
		#importantPoint#,
		#memberInfo#,
		#specialPoint#,
		#productInfo#,
		#lotteryStatus#,
		#qRCodeValidate#,
		#dealAltName#,
		#autoRefundSwitch#,
		#isCanUseCoupon#,
		#dealGroupPics#,
		#isECommerceWebSite#,
		#saleChannel#,
		NOW(),
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_ReceiptDownloadLog 
		(
			UserID,
			ReceiptID,
			DownloadType,
			MobileNo,
			UserIP,
			AddDate
		)
		VALUES
		(
			#userId#,
			#receiptId#,
			#downloadType#,
			#mobileNo#,
			#userIP#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_WishRegionLog 
		(
			UserID,
			CityID,
			Type,
			RegionID,
			AddDate
		)
		VALUES
		(
			#userId#,
			#cityId#,
			#type#,
			#regionId#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsTrack
		(
			ScenarioID,
			ChannelID,
			ChannelCode,
			TrackingCode,
			ExtraData,
			OrderID,
			UserID,
			CityID,
			BizID,
			BizType,
			Status,
			Quantity,
			Price,
			AddDate,
			LowPriceDeal
		)
		VALUES
		(
			#scenarioId#,
			#channelId#,
			#channelCode#,
			#trackingCode#,
			#extraData#,
			#orderId#,
			#userId#,
			#cityId#,
			#bizId#,
			#bizType#,
			#status#,
			#quantity#,
			#price#,
			#addDate#,
			#lowPriceDeal#
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Feed_Queue
		(Url, Text, Status, TryTimes, AddDate, UpdateDate, ThirdPartyType, ThirdUserID, OrderID)
		VALUES (#url#, #text#, 0, 0, NOW(), NOW(), #thirdPartyType#, #thirdUserId#, #orderId#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupTop
		(
		DealGroupID,
		CityID,
		TopLevel,
		TopFrom,
		TopChannel,
		TopTo
		)
		VALUES
		(
		#dealGroupId#,
		#cityId#,
		#topLevel#,
		#topFrom#,
		#topChannel#,
		#topTo#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealShopInfo
		(
		DealGroupID, 
		ShopID, 
		ShopName, 
		BranchName, 
		PhoneNoAddress
		)
		VALUES
		(
		#dealGroupId#,
		#shopId#,
		#shopName#,
		#branchName#,
		#phoneNoAddress#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_NaviDealCategory
		(
		DealGroupID,
		ChannelID,
		CategoryID,
		isMain,
		AddDate
		)
		VALUES
		(
		#dealGroupId#,
		#channelId#,
		#categoryId#,
		#isMain#,
		NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="mailContentId" resultClass="int"/>
  
        Insert into Mail_Template (Subject,Content,TaskId,TId,AddTime)
    	values (#mailTitle#,#mailContent#,#taskId#,#orderDealId#,NOW())
    </insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO ZS_FeedBack (
			UserName, 
			UserPhone, 
			UserEmail, 
			MailTo, 
			FeedTitle, 
			FeedComments, 
			FeedType, 
			FeedAdddate,
			ReferID,
			ReferUserID,
			ReferShopID,
			UserID,
			FeedGroupID,
			CauseType
		) VALUES (
			#feedback.userName# , 
			#feedback.phone#, 
			#feedback.email#, 
			#feedback.mailTo# , 
			#feedback.feedTitle#, 
			#feedback.feedBody#, 
			#feedback.feedBackType#,
			#feedback.addDate#,
			#feedback.referID#,
			#feedback.referUserID#,
			#feedback.referShopID#,
			#feedback.userID#,
			#feedback.feedGroupID#,
			#feedback.causeType#)
			   <selectKey keyProperty="feedId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_HotKeyWord 
		(
			CityID,
			Position,
			KeyWord,
			KeyWordEnName,
			URL,
			Status,
			DivClass,
			ATarget,
			Priority,
			AddDate,
			UpdateDate
		)
		VALUES
		(
			#data.cityId#,
			#data.position#,
			#data.keyWord#,
			#data.keyWordEnName#,
			#data.url#,
			#data.status#,
			#data.divClass#,
			#data.aTarget#,
			#data.priority#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventDeal 
        SET DealGroupID=#eventDealDto.dealGroupID#,EventDealTitle=#eventDealDto.eventDealTitle#,CurrentJoin=#eventDealDto.currentJoin#,
            MaxJoin=#eventDealDto.maxJoin#,MaxPerUser=#eventDealDto.maxPerUser#,Status=#eventDealDto.status#,AddDate=NOW(),
            BeginDate=#eventDealDto.beginDate#,EndDate=#eventDealDto.endDate#,Stage1=#eventDealDto.stage1#,PayOff1=#eventDealDto.payOff1#,Stage2=#eventDealDto.stage2#,
            PayOff2=#eventDealDto.payOff2#,Stage3=#eventDealDto.stage3#,PayOff3=#eventDealDto.payOff3#
           <selectKey keyProperty="eventDealID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_OperationUserRole 
		(
			OpUserID,
			RoleID,
			AddTime
		)
		VALUES
		(
			#userId#,
			#roleId#,
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO BA_TC_Task (
        `TaskGuid`,
        `TaskFromLoginId`,
        `TaskFromSys`,
        `TaskToLoginId`,
        `AddTime`,
        `UpdateTime`,
        `Status`,
        `Title`,
        `Content`,
        `ReferUri`,
        `ReferId`
        ) VALUES (
        #task.taskGuid#,
        #task.taskFromLoginId#,
        #task.taskFromSys#,
        #task.taskToLoginId#,
        #task.addTime#,
        #task.updateTime#,
        #task.taskStatus#,
        #task.title#,
        #task.content#,
        #task.referUri#,
        #task.referId#
        )
        
           <selectKey keyProperty="taskId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_ShopAccountUser(ShopAccountId,UserId,AddTime,UpdateTime)
		VALUES
		(#shopAccountId#,#userId#,#addTime#,NOW())	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Rank_DealGroupCityKV(DealGroupId, CityID, Bytes, Json)
			VALUES (#dealGroupId#, #cityId#, #dealGroupCityBytes#, #dealGroupCityJson#) 
			ON DUPLICATE KEY
			UPDATE Json=#dealGroupCityJson#, Bytes=#dealGroupCityBytes#
		   <selectKey keyProperty="DealGroupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Recommend_NavModule(CityID,ModuleType,Version,CategoryID,DistrictID,DealGroupIDs, AddDate,UpdateDate)
			VALUES (#cityId#, #moduleType#, #version#,#categoryID#,#districtID#,#dealGroupIDs#,NOW(),NOW()) 
			ON DUPLICATE KEY
			UPDATE DealGroupIDs=#dealGroupIDs#, UpdateDate=NOW()
		   <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_Privilege(ID,Module,ParentId,Name,Url,AddDate,Status)
		VALUES
		(#id#,#module#,#parentId#,#name#,#url#,NOW(),1)	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_DealPriceReduce 
		(DealGroupID, DealID, Price, ReducePrice,CostPrice,ReducePriceByPM,DealGroupTitle,MTDealGroupID,MTPrice,MTDealGroupTitle,MainCategoryID,Status,AddTime,UpdateTime,VersionID) VALUES
           <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_HotelInventory 
		(TPID, TPHotelID, RoomType, AccDate,Inventory,AddDate,VersionID) VALUES
           <iterate conjunction="," property="inventories"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_RefundRecord 
        (
        	OrderID, 
        	UserID, 
        	RefundID, 
        	DealGroupID, 
        	DealID, 
        	ProductType, 
        	RefundType, 
        	Quantity, 
        	Amount, 
        	Memo, 
        	Platform, 
        	Status, 
        	RefundSource, 
        	CreateDate, 
        	UpdateDate
        ) VALUES (
            #orderID#, 
            #userID#, 
            #refundID#, 
            #dealGroupID#, 
            #dealID#, 
            #productType#, 
            #refundType#, 
            #quantity#, 
            #amount#, 
            #memo#, 
            #platform#, 
            #status#, 
            #refundSource#, 
            #createDate#, 
            #updateDate#
        );
           <selectKey keyProperty="refundRecordID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO TG_ReceiptRefundDetail 
    	(
    		RefundRecordID, 
    		ReceiptID, 
    		CreateDate, 
    		UpdateDate
    	) VALUES (
    		#refundRecordID#, 
    		#receiptID#, 
    		#createDate#, 
    		#updateDate#
        );
           <selectKey keyProperty="detailID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Feed_Queue
		(Url, Text, Status, TryTimes, AddDate, UpdateDate, ThirdPartyType, ThirdUserID, OrderID)
		VALUES (#url#, #text#, 0, 0, NOW(), NOW(), #thirdPartyType#, #thirdUserId#, #orderId#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EBIZ_ApplyPics(
			PicId,
			PicUrl,
			Status,
			FlowId,
			AddDate,
			UpdateDate
		)VALUES(
			#applyPics.picId#,
			#applyPics.picUrl#,
			#applyPics.status#,
			#applyPics.flowId#,
			#applyPics.addDate#,
			#applyPics.updateDate#)
			
		   <selectKey keyProperty="pId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO EBIZ_DeliverAddress(
			AddressId,
            AccountId,
            Address,
            Zipcode,
            Name,
            MobileNo,
			AddDate,
			UpdateDate
		)VALUES(
			#deliverAddress.addressId#,
			#deliverAddress.accountId#,
			#deliverAddress.address#,
            #deliverAddress.zipcode#,
            #deliverAddress.name#,
            #deliverAddress.mobileNo#,
			#deliverAddress.addDate#,
			#deliverAddress.updateDate#)
		   <selectKey keyProperty="pId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TE_AdPosition 
        SET Title=#adPosition.title#, Name=#adPosition.name#, `Desc`=#adPosition.desc#, ChannelID=#adPosition.channelID#,
        	ContainerID=#adPosition.containerID#, Width=#adPosition.width#, Height=#adPosition.height#, 
        	Type=#adPosition.type#, Status=#adPosition.status#, Author=#adPosition.author#,PublishType=#adPosition.publishType#, AddTime=NOW(), UpdateTime=NOW(),
        	MaxDisplay=#adPosition.maxDisplay#, TitleLen=#adPosition.titleLen#, SubTitleLen=#adPosition.subTitleLen#
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO 
    		TGE_MemberGiftStat
    		(UserID,MemberGiftID,NextSendTime,MaxSendTimes,GiftSendTimes,AddTime)
    	VALUES
    		(#userId#,#memberGiftId#,#nextSendTime#,#maxSendTimes#,#giftSendTimes#,NOW())
    	   <selectKey keyProperty="MemberGiftStatID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        TGE_MemberGiftStat
        (UserID,MemberGiftID,NextSendTime,MaxSendTimes,GiftSendTimes,AddTime)
        VALUES
        (#userId#,#memberGiftId#,#nextSendTime#,#maxSendTimes#,1,NOW())
           <selectKey keyProperty="MemberGiftStatID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItemAttribute (NaviTagItemAttributeID, ItemID, Name, Value)
		VALUES (
		#id#, #itemId#, #name#, #value#
		)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT 
			(DOCUMENT_BUILDER_ID, 
			STATEMENT_TEMPLATE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME)
		VALUES
			(#entity.documentBuilder.id#,
			#entity.statementTemplateId#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT_ATTRIBUTE_VALUE 
			(STATEMENT_ID, 
			STATEMENT_ATTRIBUTE_ID,
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VALUE)
		VALUES
			(#entity.statement.id#,
			#entity.statementAttributeId#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.value#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_CONTRACT
            (CONTRACT_CRM_ID,
            CONTRACT_SERIAL,
            CONTRACT_GLOBAL_ID,
            ACCOUNT_NAME,
            ACCOUNT_GLOBAL_ID,
            FP_SETTLE_TYPE,
            CASH_OUT_TYPE,
            CITY_ID,
            SIGN_ON_SALES_ID,
            SIGN_ON_SALES_NAME,
            OWNER_SALES_ID,
            OWNER_SALES_NAME,
            PAY_TERM_TYPE,
            STATUS,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID,
            VERSION_ID,
            ACCOUNT_REGISTER_NAME,
            ACCOUNT_RATING,
            ACCOUNT_ID,
            TYPE,
            SOURCE_TYPE
            )
        VALUES
            (#entity.contractCrmId#,
            #entity.contractSerial#,
            #entity.contractGlobalId#,
            #entity.accountName#,
            #entity.accountGlobalId#,
            #entity.fpSettleType#,
            #entity.cashOutType#,
            #entity.cityId#,
            #entity.signOnSalesId#,
            #entity.signOnSalesName#,
            #entity.ownerSalesId#,
            #entity.ownerSalesName#,
            #entity.payTermType#,
            #entity.status#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#,
            #entity.versionId#,
            #entity.accountRegisterName#,
            #entity.accountRating#,
            #entity.accountId#,
            #entity.type#,
            #entity.sourceType#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,IS_AUTO_CALCUATED,
		TITLE,
		TOTAL_PRICE,
		RETAIL_PRICE_DESC,
		TOTAL_PRICE_LABEL,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,
		#entity.isAutoCalcuated#,
		#entity.title#,
		#entity.totalPrice#,
		#entity.retailPriceDescription#,
		#entity.totalPriceLabel#,
		#entity.templateId#,
		'DealComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_EDITOR
                (DEAL_GROUP_ID,
                EDITOR_ID,
                EDITOR_NAME,
                DEAL_GROUP_PRODUCE_STATUS,
                CREATOR_ID,
                LAST_UPDATOR_ID,
                CREATE_TIME,
                LAST_UPDATE_TIME,
                VERSION_ID)
        VALUES(#entity.dealGroupId#,
              #entity.editorId#,
              #entity.editorName#,
              #entity.dealGroupProduceStatus,handler=dealGroupProduceStatusEnumTypeHandler#,
              #entity.creatorId#,
              #entity.lastUpdatorId#,
              #entity.createTime#,
              #entity.lastUpdateTime#,
              #entity.versionId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_PRODUCE_VERSION
		(DEAL_GROUP_ID,DESC_INFO,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME,IP_ADDRESS,SERVER_IP,DATA)
		VALUES
		(#entity.dealGroupId#,#entity.descInfo#,#entity.creatorId#,#entity.lastUpdatorId#,
		#entity.createTime#,#entity.lastUpdateTime#,#entity.ipAddress#,#entity.serverIp#,#entity.data#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_DEAL_GROUP_WORKFLOW
            (DEAL_GROUP_ID,
            PROCESS_INSTANCE_ID,
            PROCESS_CODE,
            WORKFLOW_STATUS,
            CREATOR_ID,
            LAST_UPDATOR_ID,
            CREATE_TIME,
            LAST_UPDATE_TIME
            )
        VALUES
            (#entity.dealGroupId#,
            #entity.processInstanceId#,
            #entity.processCode#,
            #entity.workflowStatus,handler=workflowStatusEnumTypeHandler#,
            #entity.creatorId#,
            #entity.lastUpdatorId#,
            #entity.createTime#,
            #entity.lastUpdateTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_HOTEL
		(DEAL_ID,ROOM_TYPE,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME)
		VALUES
		(#entity.dealId#,#entity.roomType#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_EXCEPT_DATE
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		BEGIN_DATE,
		END_DATE,
		DEAL_GROUP_ID)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.beginDate#,
		#entity.endDate#,
		#entity.dealGroup.id#
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_IMAGE_TEXT_DESC_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,IMAGE_TEXT_ITEM_ID,SEQ,CONTENT,IS_TITLE,TEMPLATE_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.imageTextItem.id#,#entity.sequence#,#entity.content#,#entity.isTitle#,#entity.templateId#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_LIST_ITEM 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VISUAL_COMPONENT_ID,SEQ,CONTENT,QUANTITY,SPECIFICATION,UNIT,TEMPLATE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.visualComponent.id#,#entity.sequence#,#entity.content#,#entity.quantity#,#entity.specification#,#entity.unit#,#entity.templateId#,'ProductItem')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_FILE_ATTACHMENT 
		(CREATE_TIME,
		CREATOR_ID,
		LAST_UPDATE_TIME,
		LAST_UPDATOR_ID,
		FILE_NAME,
		RELATIVE_PATH,
		DEAL_GROUP_ID,
		DTYPE)
		VALUES 
		(#entity.createTime#,
		#entity.creatorId#,
		#entity.lastUpdateTime#,
		#entity.lastUpdatorId#,
		#entity.fileName#,
		#entity.relativePath#,
		#entity.dealGroup.id#,
		'QualifiedAttachment'
		)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_SALES_TEAM
            (TEAM_NAME,
            TEAM_CITY_ID,
            TEAM_CITY_NAME,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME,
            VERSION_ID
            )
        VALUES
            (#entity.teamName#,
            #entity.teamCityId#,
            #entity.teamCityName#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#,
            #entity.versionId#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_SALES_TEAM_AE_ASSN
            (SALES_TEAM_ID,
            AE_ID,
            AE_NAME,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME
            )
            VALUES
            (#entity.salesTeam.id#,
            #entity.aeId#,
            #entity.aeName#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_SERIAL_NUM_OPERATION_LOG
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VERSION_ID,
		DEAL_ID,
		EXPORTED_COUNT,
		DTYPE)
		VALUES
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.versionId#,
		#entity.dealId#,
		#entity.exportedCount#,
		'SerialNumberExportLog')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_COMPONENT 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,CONFIGURABLE_BLOCK_ID,TEMPLATE_ID,AREA_TYPE_ID,DTYPE)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.configurableBlock.id#,#entity.templateId#,#entity.areaTypeId#,'TextAreaListComponent')
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_Settlement(
        DealGroupID,
        SettleLevel,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.settleLevel#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Movie(MovieID, MovieName, Director, 
		MainPerformer, SPhoto, SPhotoOriginal, LPhoto, HPhoto, ShowDate, Sort, Area, 
		MovieDes, MPrice, Grade, Msg, Status, Language, Has3D, HasIMAX, Minutes) 
		VALUES 
			(#movieInfoEntity.movieId#, #movieInfoEntity.movieName#, 
			#movieInfoEntity.director#, #movieInfoEntity.mainPerformer#, 
			#movieInfoEntity.sPhoto#, #movieInfoEntity.sPhotoOriginal#, #movieInfoEntity.lPhoto#, 
			#movieInfoEntity.hPhoto#, #movieInfoEntity.showDate#, 
			#movieInfoEntity.sort#, #movieInfoEntity.area#, 
			#movieInfoEntity.des#, #movieInfoEntity.mPrice#, 
			#movieInfoEntity.grade#, #movieInfoEntity.msg#, 
			#movieInfoEntity.status#, #movieInfoEntity.language#, 
			#movieInfoEntity.has3D#, #movieInfoEntity.hasImax#, 
			#movieInfoEntity.minutes#);
	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_RateReviewMapping (
        RateID,
        ShopID,
        ReviewID)
        VALUES
        (
        #data.rateId#,
        #data.shopId#,
        #data.reviewId#)
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			insert INTO TG_TravelReservation(ReceiptID,OrderID, BookName,TravelerName, TourDate, IdentityType, IdentityID, 
			MobileNo, Status, RefReceiptID, RefReceiptCode, ThirdPartnerID, UserID, Memo, ThirdPartnerDealID, AddDate, UpdateDate) VALUES
			(#entity.receiptId#,#entity.orderId#, #entity.bookName#,#entity.travelerName#, #entity.tourDate#,
			#entity.identityType#, #entity.identityId#, #entity.bookMobileNo#,#entity.travelerName#, #entity.status#,
			#entity.refReceiptId#, #entity.refReceiptCode#, #entity.thirdPartnerId#, #entity.userId#, 
			#entity.memo#, #entity.thirdPartnerDealId#, now(), now())
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert INTO TG_TravelReservation(ReceiptID,OrderID, BookName,TravelerName, TourDate, IdentityType, IdentityID, 
			BookMobileNo,TravelerMobileNo, Status, RefReceiptID, RefReceiptCode, ThirdPartnerID, UserID, Memo, ThirdPartnerDealID, AddDate, UpdateDate) VALUES
		   <iterate conjunction="," property="entities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupExtend(
        DealGroupID,
        ProcessID,
        ApprovalStatus,
        PublishStatus,
        PublishDate,
        DisplayStatus,
        CityId,
        OwnerId,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #id#,
        0,
        #approvalStatus#,
        #publishStatus#,
        #publishDate#,
        #displayStatus#,
        #cityId#,
        #ownerId#,
        #createBy#,
        #updateBy#,
        #createTime#,
        #updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        	INSERT INTO TS_WithdrawConfig
		            (AccountId,
		             PayMinInterval,
		             PayMaxInterval,
		             PayMinAmount,
		             AddTime,
		             UpdateTime,
		             Status)
			VALUES (#tsWithdrawConfigData.accountId#,
			        #tsWithdrawConfigData.payMinInterval#,
			        #tsWithdrawConfigData.payMaxInterval#,
		            #tsWithdrawConfigData.payMinAmount#,
		            #tsWithdrawConfigData.addTime#,
		            #tsWithdrawConfigData.updateTime#,
		            #tsWithdrawConfigData.status#);
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TS_GuaranteePayTerm
        (
	        GuaranteePayTermID,
	        OutBizID,
	        Amount,
	        PayDate,
	        AddTime,
	        UpdateTime
	        )
        VALUES
        (	
        	#guaranteePayTermData.guaranteePayTermID#,
        	#guaranteePayTermData.outBizID#,
        	#guaranteePayTermData.amount#,
        	#guaranteePayTermData.payDate#,
        	NOW(),
        	NOW()
       	);
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO TS_UseQuotaApply
		            (CityID,
		             CustomerID,
		             Amount,
		             ReturnAmount,
		             UseBy,
		             BizSequenceID,
		             PayTime,
		             Status,
		             AddTime,
		             UpdateTime)
			VALUES (#useQuotaApplyData.cityId#,
			        #useQuotaApplyData.customerId#,
		            #useQuotaApplyData.amount#,
		            #useQuotaApplyData.returnAmount#,
		            #useQuotaApplyData.useBy#,
		            #useQuotaApplyData.bizSequenceId#,
		            #useQuotaApplyData.payTime#,
		            #useQuotaApplyData.status#,
		            NOW(),
		            NOW()
		            );
 		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_Settlement(
        DealGroupID,
        SettleLevel,
        CreateBy,
        UpdateBy,
        CreateTime,
        UpdateTime
        )VALUES(
        #entity.dealGroupId#,
        #entity.settleLevel#,
        #entity.createBy#,
        #entity.updateBy#,
        #entity.createTime#,
        #entity.updateTime#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TA_Order(thirdpartyid, shopkey,userkey,reachtime,comment,payfee,mobile,
		shopname,shopphone,fetchtype,reserve,`interval`,noticetime, clientip
		   <dynamic>
      <isNotNull property="uuid"/>
   </dynamic>
   <dynamic>
      <isNotNull property="channel"/>
   </dynamic>
   <dynamic>
      <isNotNull property="paystatus"/>
   </dynamic>
   <dynamic>
      <isNotNull property="dpid"/>
   </dynamic>
   <dynamic>
      <isNotNull property="callid"/>
   </dynamic>

		)
		values(#thirdpartyid#, #shopkey#,#userkey#,#reachtime#,#comment#,#payfee#,#mobile#,
		#shopname#,#shopphone#,#fetchtype#,#reserve#,#interval#,#noticetime#,#clientip#
		   <dynamic>
      <isNotNull property="uuid"/>
   </dynamic>
   <dynamic>
      <isNotNull property="channel"/>
   </dynamic>
   <dynamic>
      <isNotNull property="paystatus"/>
   </dynamic>
   <dynamic>
      <isNotNull property="dpid"/>
   </dynamic>
   <dynamic>
      <isNotNull property="callid"/>
   </dynamic>

		)
		   <selectKey keyProperty="orderid" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TA_UserContact(userid,address
		   <dynamic>
      <isNotNull property="name"/>
   </dynamic>

		,cityid,phone)
		
		values(#userid#,#address#
		   <dynamic>
      <isNotNull property="name"/>
   </dynamic>

		,#cityid#,#phone#)
		
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TPA_ProcessHistory (
            ProcessID,
            OperationType,
            Status,
            UserID,
            UserType,
            Memo,
            NodeName,
            Param,
            AddTime
        )
        VALUES (
            #processID#,
            #operationType#,
            #status#,
            #userID#,
            #userType#,
            #memo#,
            #nodeName#,
            #param#,
            NOW()
        )
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (
				#tagId#, #itemName#, #itemEnName#, 1, #priority#
			)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (ItemID, TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (#itemId#, #tagId#, #itemName#, #itemEnName#, 1, #priority#)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_ReceiptGroupCode
        (Code,DealID,UserID,Status,AddDate)
        VALUES(#code#,#dealId#,#userId#,1,Now());
           <selectKey keyProperty="eventId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_SharedLinkLog(
			SharedLinkLogId,
			UserId,
			InviteUser,
			PlatformType,
			AddDate,
			Referer,
			URL,
			UserIP,
			DealGroupId,
			UserFlag,
			OrderId,
			OrderFlag,
			Status,
			UpdateDate
		)VALUES(
			#sharedLinkLogId#,
			#userId#,
			#inviteUser#,
			#platformType#,
			now(),
			#referer#,
			#url#,
			#userIP#,
			#dealGroupId#,
			#userFlag#,
			#orderId#,
			#orderFlag#,
			#status#,
			now())
			
	   <selectKey type="post" keyProperty="sharedLinkLogId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into PCT_ThirdPartPaymentAuditExtend
    	(productCode, 
    	 paymentMethod, 
    	 platformType, 
    	 productName, 
    	 productDesc, 
    	 productUrl,
    	 createTime, 
    	 thirdPartPaymentAuditId, 
    	 engineControlId,
    	 errorCode)
    values 
    	(#productCode:INTEGER#, 
    	 #paymentMode:INTEGER#, 
    	 #platformType:INTEGER#,
    	 #productName:VARCHAR#,
		 #productDesc:VARCHAR#, 
		 #productUrl:VARCHAR#,   
    	 NOW(),
		 #thirdPartPaymentAuditId:INTEGER#,
       	 #engineControlId:VARCHAR#,
       	 #errorCode:VARCHAR#)
		    <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_WishList
		(
			DealGroupID,
			UserID,
			Status,
			AddDate,
			LastDate
		)
		VALUES
		(
			#dealGroupId#,
			#userId#,
			#status#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_WishRegion 
		(
			UserID,
			CityID,
			RegionIDs,
			AddDate,
			UpdateDate
		)
		VALUES
		(
			#userId#,
			#cityId#,
			#regionIds#,
			NOW(),
			NOW()
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsDualWrite
		(
			OrderId
		)
		VALUES
		(
			#orderId#
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGE_CompanyUserCouponCodeLog(
			MobileNo,
			IP,
			CouponCode,
			CompanyName,
			UserID,
			UserType
		)VALUES(
			#mobileNo#,
			#ip#,
			#couponCode#,
			#companyName#,
			#uid#,
			#userType#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGE_CompanyUserIntegrityLog(
			MobileNo,
			UserId,
			ChannelName,
			UserIntegrity,
			AddTime
		)VALUES(
			#mobileNo#,
			#userId#,
			#channelName#,
			#userIntegrity#,
			#addTime#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TGE_SecondPrize
        (
        Name,
        BeginDate,
        EndDate,
        BannerImage,
        Status,
        Priority,
        Type,
        TypeReferID,
        Price,
        MarketPrice,
        MaxJoin,
        CurrentJoin,
        AddDate,
        UpdateDate
        )
        VALUES
        (
        #data.name#,
        #data.beginDate#,
        #data.endDate#,
        #data.bannerImage#,
        #data.status#,
        #data.priority#,
        #data.type#,
        #data.typeReferId#,
        #data.price#,
        #data.marketPrice#,
        #data.maxJoin#,
        #data.currentJoin#,
        NOW(),
        NOW()
        )
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO 
            TGE_UserPurchaseLog 
        SET 
	        UserID = #userId#,
			DPID = #dpId#,
			UserMobile = #mobile#,
			DealID = #dealId#,
			PayEntrance = #payEntrance#,
			IP = #ip#,
			CreateTime = NOW()
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealCategory
		(
		DealGroupID,
		CityID,
		CategoryID,
		CategoryName,
		CategoryOrderID,
		RootCategoryID,
		AddDate,
		UpdateDate
		)
		VALUES
		(
		#dealGroupId#,
		#cityId#,
		#categoryId#,
		#categoryName#,
		#categoryOrderId#,
		#rootCategoryId#,
		#createTime#,
		#updateTime#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupExtend
		(
		DealGroupID,
		ADDDATE,
		UpdateDate,
		IsShowDealGroupRegion,
		IsAsyncReceipt,
		ActivationType,
		AheadHours,
		EditorTeam,
		QQ,
		SourceID,
		IsBlockStock
		)
		VALUES
		(
		#id#,
		NOW(),
		NOW(),
		#isShowDealGroupRegion#,
		#isAsyncReceipt#,
		#activationType#,
		#aheadHours#,
		#editorTeam#,
		#qq#,
		#sourceId#,
		#isBlockStock#
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TGM_Announcement 
			(Title, PageUrl, BeginTime, EndTime, ClientIDList, 
			VersionList, CityIDList, Priority)
		VALUES
			(#Title#, #PageUrl#, #BeginTime#, #EndTime#, #ClientIDList#, 
			 #VersionList#, #CityIDList#, #Priority#)
		   <selectKey keyProperty="AnnouncementID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_Rank_DealGroupCityKV(DealGroupId, CityID, Bytes, Json)
			VALUES (#dealGroupId#, #cityId#, #dealGroupCityBytes#, #dealGroupCityJson#) 
			ON DUPLICATE KEY
			UPDATE Json=#dealGroupCityJson#, Bytes=#dealGroupCityBytes#
		   <selectKey keyProperty="DealGroupId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

   
	   INSERT IGNORE INTO TG_Event_UserCategory 
		(UserID, 
		STATUS, 
		TYPE, 
		ADDTIME
		)
		VALUES		 
   
      <iterate conjunction="," property="userIds"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TE_AdPosition 
        SET Title=#adPosition.title#, Name=#adPosition.name#, `Desc`=#adPosition.desc#, ChannelID=#adPosition.channelID#,
        	ContainerID=#adPosition.containerID#, Width=#adPosition.width#, Height=#adPosition.height#, 
        	Type=#adPosition.type#, Status=#adPosition.status#, Author=#adPosition.author#,PublishType=#adPosition.publishType#, AddTime=NOW(), UpdateTime=NOW()
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_RecommendKeyWord 
        (KeyWord,KeyWordEnName,URL,Status,BeginDate,EndDate,AddDate,UpdateDate)
        VALUES
        (#keyWordDto.keyWord#,
            #keyWordDto.keyWordEnName#,
            #keyWordDto.URL#,
            #keyWordDto.status#,
            #keyWordDto.beginDate#,
            #keyWordDto.endDate#,
            NOW(),
            NOW()
        )
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_Event_RecomKeyWordCity (CityID, KeyWordID, Priority) VALUES
           <iterate conjunction="," property="listCities"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO MC_MemberCardBossAccountInfo(CrmID,CompanyName,CompanyManager,CompanyManagerTel,CompanyManagerPosition,CompanyManagerMail,BossAccountCreateStatus,BossAccountCreateAuditInfo,BossAccountCreateTime,BossAccountUpdateTime,AuthAdminID,AuthAdminName) 
        VALUES(#bossData.crmID#,#bossData.companyName#,#bossData.companyManagerName#,#bossData.companyManagerTel#,#bossData.companyManagerPosition#,#bossData.companyManagerMail#,#bossData.bossAccountCreateStatus#,#bossData.bossAccountCreateAuditInfo#,#bossData.bossAccountCreateTime#,#bossData.bossAccountUpdateTime#,#bossData.authAdminId#,#bossData.authAdminName#)
         
           <selectKey keyProperty="companyInfoID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProduct 
			(MemberCardID,
			 AddAccountID,
			 ProductName,
			 ProductDesc,
			 ProductType,
			 BeginDate,
			 EndDate,
			 Status,
			 AddTime,
			 ProductDraftId,
			 CrmID) 
		VALUES 
			(#memberCardId#, 
			 #addAccountId#,
			 #productName#, 
			 #productDesc#, 
			 #productType#, 
			 #beginDate#,
			 #endDate#,
			 #status#,
			 #addTime#,
			 #productDraftId#,
			 #crmId#)
		   <selectKey keyProperty="ProductID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductDiscount 
			(ProductID,
			 DiscountRate) 
		VALUES 
			(#productId#, 
			 #discount#)
		   <selectKey keyProperty="ProductDiscountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductShop 
			(ProductID,
			 ShopID,
			 MemberCardID,
			 Status,
			 AddTime) 
		VALUES 
			(#productId#, 
			 #shopId#,
			 #memberCardId#,
			 #status#,
			 #addTime#)
		   <selectKey keyProperty="ProductShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductMallPromo
			(ProductID,
			 PromoTitle,
			 PromoDesc,
			 Tel)
		VALUES 
			(#productId#,
			 #promoTitle#,
			 #promoDesc#,
			 #tel#)
		   <selectKey keyProperty="PromoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductMallShopPromo
			(ProductID,
			 ShopType,
			 Floor,
			 Building,
			 PromoTitle,
			 PromoDesc,
			 Tel)
		VALUES 
			(#productId#,
			 #shopType#,
			 #floor#,
			 #building#,
			 #promoTitle#,
			 #promoDesc#,
			 #tel#)
		   <selectKey keyProperty="promoId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProduct_Draft
			(ProductID,
			 MemberCardID,
			 AddAccountID,
			 ProductName,
			 ProductDesc,
			 ProductType,
			 BeginDate,
			 EndDate,
			 ActionType,
			 Reason,
			 Status,
			 AddTime,
			 CrmID) 
		VALUES 
			(#productID#,
			 #memberCardID#, 
			 #addAccountID#,
			 #productName#, 
			 #productDesc#, 
			 #productType#, 
			 #beginDate#,
			 #endDate#,
			 #actionType#,
			 #reason#,
			 #status#,
			 #addTime#,
			 #crmId#)
		   <selectKey keyProperty="ProductDraftID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductShop_Draft 
			(ProductDraftID,
			 ShopID,
			 MemberCardID,
			 Status,
			 AddTime) 
		VALUES 
			(#productDraftId#, 
			 #shopID#, 
			 #memberCardID#, 
			 #status.value#, 
			 #addTime#)
		   <selectKey keyProperty="ProductShopID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO MC_MemberCardProductDiscount_Draft
			(ProductDraftID,
			 DiscountRate) 
		VALUES 
			(#productDraftId#, 
			 #discountRate#)
		   <selectKey keyProperty="ProductDiscountID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO DP_MyListTesecaiFavoriteOption
        (`Value`,`Desc`,`Frequency`,`Type`,`AddTime`,`UpdateTime`)
        VALUES (#option.value#,#option.desc#,#option.frequency#,#option.type#,now(),now());
           <selectKey keyProperty="ID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO BC_ShopAccountUser(ShopAccountId,UserId,AddTime,UpdateTime)
		VALUES
		(#shopAccountId#,#userId#,#addTime#,NOW())	
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    
		INSERT INTO DianPingBC.MP_Feedback
		(FeedbackId, 
		Content, 
		CreateTime, 
		CreatorId)
		VALUES
		(#entity.feedbackId#,#entity.content#,#entity.createTime#,#entity.creatorId#)
		
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO {tableName} 
		(
{insertFieldList}
		)
		VALUES
		(
{valueList}
		)
		   <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
            TGE_DealBuyCouponSendLog 
        	(UserId, CouponId, OrderId, Status, CreateTime)
        values
        	(#userId#, #couponId#, #orderId#, #status#, NOW())
           <selectKey keyProperty="LogId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_MobileTrigger(trigger_dt, flag, table_name, update_dt, del_dt)
		values(#trigger_dt#, #flag#, #table_name#, #update_dt#, #del_dt#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_EventPrizeUser(EventID,UserID,UserMobile,CurrentJoin,MaxJoin,Period,EventDeaID,Entrance)
		VALUES 
			(#prizeUser.eventId#,#prizeUser.userId#,#prizeUser.mobile#,1,#prizeUser.maxJoin#,#prizeUser.period#,#prizeUser.eventDealId#,#prizeUser.entrance#)
		   <selectKey keyProperty="UID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TG_EventPrizeUserLog(EventID,UserID,UserMobile,UserNickName,PrizeID,PrizeName,Source,Entrance,IP,Memo) 
		VALUES 
			(#log.eventId#,#log.userId#,#log.userMobile#,#log.userNickName#,#log.prizeId#,#log.prizeName#,#log.source#,#log.entrance#,#log.ip#,#log.memo#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			TG_EventUserPrize(UserID,UserMobile,EventID,Amount,Value,PrizedDate)
		VALUES 
			(#log.userId#,#log.userMobile#,#log.eventId#,1,#log.prizeValue#,#log.period#)
		   <selectKey keyProperty="UID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			TG_EventUserPrizeLog(EventID,UserID,UserMobile,UserNickName,PrizeID,PrizeName,PrizeValue,Source,Entrance,IP,Memo) 
		VALUES 
			(#log.eventId#,#log.userId#,#log.userMobile#,#log.userNickName#,#log.prizeId#,#log.prizeName#,#log.prizeValue#,#log.source#,#log.entrance#,#log.ip#,#log.memo#)
		   <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	   INSERT INTO 
	       TG_EventUserPrizeLog_Extend 
	   SET 
	       UserAwardID =#awardId# ,SendStatus =#sendStatus#, AwardDealID=#awardDealId# ,Memo=#memo# , CreateTime = NOW()
	      <selectKey keyProperty="LogID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_Track
		(
			ScenarioId,
			PageId,
			ChannelId,
			ChannelCode,
			AddDate,
			Amount,
			Referer,
			UserAgent,
			Url,
			Guid,
			SessionId,
			BizId,
			BizType,
			OrderId 
		)
		VALUES
		(
			#scenarioId#,
			#pageId#,
			#channelId#,
			#channelCode#,
			#time#,
			#amount#,
			#referer#,
			#userAgent#,
			#url#,
			#guid#,
			#sessionId#,
			#bizId#,
			#bizType#,
			#orderId#
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_CpsDualWrite
		(
			OrderId
		)
		VALUES
		(
			#orderId#
		)
		   <selectKey resultClass="long"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO TG_DealGroupRemind(
		RemindId,
		UserId,
		ShopGroupId,
		ShopGroupName,
		Status,
		AddDate,
		LastDate,
		DealGroupID,
		CityID,
		ReferType
		)VALUES(
		#dealGroupRemind.remindId#,
		#dealGroupRemind.userId#,
		#dealGroupRemind.shopGroupId#,
		#dealGroupRemind.shopGroupName#,
		#dealGroupRemind.status#,
		#dealGroupRemind.addDate#,
		#dealGroupRemind.lastDate#,
		#dealGroupRemind.dealGroupId#,
		#dealGroupRemind.cityId#,
		#dealGroupRemind.referType#);
		   <selectKey keyProperty="remindId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_NaviDealTag (DealGroupID, TagID, ItemID)
        VALUES
           <iterate conjunction=","/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (
				#tagId#, #itemName#, #itemEnName#, 1, #priority#
			)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT IGNORE INTO TG_NaviTagItem (ItemID, TagID, ItemName, ItemEnName, Status, Priority)
			VALUES (#itemId#, #tagId#, #itemName#, #itemEnName#, 1, #priority#)
		   <selectKey keyProperty="itemId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TG_CATEGORY
            (PARENT_CATEGORY_ID,
            NAME,
            VIEW_PRIORITY,
            STATUS_ID,
            LEVEL,
            CREATOR_ID,
            CREATE_TIME,
            LAST_UPDATOR_ID,
            LAST_UPDATE_TIME
            )
        VALUES
            (#entity.parentId#,
            #entity.name#,
            #entity.viewPriority#,
            #entity.statusId#,
            #entity.level#,
            #entity.creatorId#,
            #entity.createTime#,
            #entity.lastUpdatorId#,
            #entity.lastUpdateTime#
            );
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_MemberBehaviorMsg(
			UserID,
			ReferID,
			DealGroupID,
			ConsumeAmount,
			ActualCostAmount,
			MsgType,
			Platform,
			Memo,
			Status,
			AddTime)
		values(
			#memberBehaviorMsg.userId#,
			#memberBehaviorMsg.referId#,
			#memberBehaviorMsg.dealGroupId#,
			#memberBehaviorMsg.consumeAmount#,
			#memberBehaviorMsg.actualCostAmount#,
			#memberBehaviorMsg.msgType#,
			#memberBehaviorMsg.platform#,
			#memberBehaviorMsg.memo#,
			#memberBehaviorMsg.status#,
			#memberBehaviorMsg.addTime#)
		   <selectKey keyProperty="MemberBehaviorMsgID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TPDA_DealGroupCity(
        DealGroupCityID,
		DealGroupID,
		CityID,
		CreateTime,
		UpdateTime,
		CreateBy,
		UpdateBy
        )VALUES(
        #entity.id#,
		#entity.dealGroupId#,
		#entity.cityId#,
		#entity.createTime#,
		#entity.updateTime#,
		#entity.createBy#,
		#entity.updateBy#
        )
        
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN
			(CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			DOCUMENT_TEMPLATE_ID,
			NAV_CATEGORY_ID)
		VALUES
			(#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.documentTemplate.id#,
			#entity.categoryId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_STATEMENT_ATTRIBUTE_OPTION 
			(STATEMENT_ATTRIBUTE_ID, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME, 
			VALUE)
		VALUES
			(#entity.statementAttribute.id#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.value#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_CONFIGURABLE_BLOCK 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,TYPE_ID,VISUAL_VIEW_ID,TITLE,TEMPLATE_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.typeId#,#entity.visualView.id#,#entity.title#,#entity.templateId#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TGP_CONTACT
            (DEAL_GROUP_ID,
            CONTACT_NAME,
            CONTACT_EMAIL,
            CONTACT_MP,
            CONTACT_TYPE,
            CREATE_TIME,
            CREATOR_ID,
            LAST_UPDATE_TIME,
            LAST_UPDATOR_ID
            )
        VALUES
            (#entity.dealGroup.id#,
            #entity.contactName#,
            #entity.contactEmail#,
            #entity.contactMP#,
            #entity.contactType#,
            #entity.createTime#,
            #entity.creatorId#,
            #entity.lastUpdateTime#,
            #entity.lastUpdatorId#
            );
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL 
			(DEAL_GROUP_ID, 
			SHORT_TITLE, 
			ORIGINAL_PRICE, 
			RETAIL_PRICE, 
			COST_PRICE, 
			MAX_STOCK_QTY, 
			MAX_SALE_QTY, 
			MIN_SALE_QTY, 
			CREATOR_ID, 
			LAST_UPDATOR_ID, 
			CREATE_TIME, 
			LAST_UPDATE_TIME,
			IS_DEFAULT, 
			SEQ,
			PRODUCT_ID_IN_IMSS,
			DUPLICATED_SERIALNUMBER_COUNT_IN_LASTBATCH,
			TOTAL_IMPORTED_SERIALNUMBER_COUNT,
			RECEIPT_CONTACT_INFO,
			BANK_ACCOUNT_GLOBAL_ID,
			IS_ACTIVE,
			DISPLAY_TYPE,
			DEAL_TYPE)
		VALUES
			(#entity.dealGroup.id#,
			#entity.shortTitle#,
			#entity.originalPrice#,
			#entity.retailPrice#,
			#entity.costPrice#,
			#entity.maxStockQty#,
			#entity.maxSaleQty#,
			#entity.minSaleQty#,
			#entity.creatorId#,
			#entity.lastUpdatorId#,
			#entity.createTime#,
			#entity.lastUpdateTime#,
			#entity.isDefault#,
			#entity.sequence#,
			#entity.productIdInImss#,
			#entity.duplicatedSerialNumberCountInLastBatch#,
			#entity.totalImportedSerialNumberCount#,
			#entity.receiptContactInfo#,
			#entity.bankAccountGlobalId#,
			#entity.isActive#,
			#entity.displayType#,
			#entity.dealType#
			)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP (BUSINESS_TYPE_ID,CREATE_TIME,CREATOR_ID,DAYS_AFTER_INEFFECTIVE,DAYS_AFTER_ONLINE,
		DAYS_BEFORE_EFFECTIVE,EFFECTIVE_END_DATE,EFFECTIVE_END_TYPE,
		IS_DELIVERY_REQUIRED,IS_REFUNDABLE,IS_VOUCHER_AVAILABLE,LAST_UPDATE_TIME,LAST_UPDATOR_ID,PUBLISH_FROM_DATE,PUBLISH_TO_DATE,
		DISTRIBUTION_PARTY_ID,IS_TAX_INVOICE_AVARIABLE,VERSION_ID,CONTRACT_ID,IS_RELEASED_TO_ALL_CITIES,
		STATUS_ID,IS_HIGH_PROCESS_LEVEL,IS_HIGH_LEVEL_APPLIED_FOR,IS_VOUCHER_SETTED,VOUCHER_FORMAT_ID,
		VERIFICATION_DEVICE_ID,REMARKS,OTHER_EXCEPT_DATE,MAX_SALE_QTY,MIN_SALE_QTY,COMPANY_ID_IN_IMSS,THIRD_PARTY_VERIFY_PROVIDER_ID,EDITOR_ID,MAINTAINER_ID,
		IS_MANUAL_SET_REFUND,IS_AE_ASSIGNED,IS_HIDEN,PUBLISH_STATUS_ID,
		CUSTOMER_SERVICE_QQ,EFFECTIVE_BEGIN_DATE,EFFECTIVE_BEGIN_TYPE,IS_SUBMIT_EDITOR,
		BRIEF_DESC_DETAIL,
		BRIEF_DESC_SPECIAL_REMINDER,
		BRIEF_DESC_PRD_INTRDCTN,
		BRIEF_DESC_SHOP_INFO,
		BRIEF_DESC_SHOP_INTRDCTN,
        BRIEF_DESC_COMMENTS,
        IS_EDITOR_REQUIRED,SUBMIT_FOR_MERCHANT_CONFIRM_DATE,APPROVE_BY_MERCHANT_CONFIRM_DATE,IS_VALID,PARENT_DEAL_GROUP_ID,PUBLISH_TO_TYPE,DAYS_AFTER_ONLINE_OF_PUBLISH_TO_DATE,BRIEF_DESC_PROCESS_FOR_USE,
        IS_AUTO_DELAY, REFUND_REASON)
		VALUES (#entity.businessTypeId#,#entity.createTime#,#entity.creatorId#,#entity.daysAfterIneffective#,
		#entity.daysAfterOnline#,#entity.daysBeforeEffective#,
		#entity.effectiveEndDate#,#entity.effectiveEndType#,#entity.isDeliveryRequired#,#entity.refundableStatus#,
		#entity.isVoucherAvailable#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.publishFromDate#,#entity.publishToDate#,
		#entity.distributionPartyId#,#entity.isTaxInvoiceAvailable#,#entity.versionId#,
		#entity.contractId#,#entity.isReleasedToAllCities#,#entity.statusId#,#entity.isHighProcessLevel#,#entity.isHighLevelAppliedFor#,
		#entity.isVoucherSetted#,#entity.voucherFormatId#,#entity.verificationDeviceId#,
		#entity.remarks#,#entity.otherExceptDate#,#entity.maxSaleQty#,#entity.minSaleQty#,#entity.companyIdInImss#,#entity.thirdPartyVerifyProviderId#,#entity.editorId#,#entity.maintainerId#,
		#entity.isManualSetRefund#,#entity.isAEAssigned#,#entity.isHiden#,#entity.publishStatusId#,
		#entity.customerServiceQQ#,#entity.effectiveBeginDate#,#entity.effectiveBeginType#,#entity.isSubmitEditor#,
		#entity.briefDescription.detailedInformation#,
		#entity.briefDescription.specialReminder#,
		#entity.briefDescription.productIntroduction#,
		#entity.briefDescription.shopInformation#,
		#entity.briefDescription.shopIntroduction#,
		#entity.briefDescription.comments#,
		#entity.isEditorRequired#,#entity.submitForMerchantConfirmDate#,#entity.approveByMerchantConfirmDate#,#entity.isValid#,#entity.parentDealGroupId#,#entity.publishToType#,#entity.daysAfterOnlineOfPublishToDate#,#entity.briefDescription.processForUse#,
		#entity.isAutoDelay#, #entity.refundReason#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_DEAL_GROUP_ADMISSION(DEAL_GROUP_ID,IS_ADMISSION_REQUIRED,AMOUNT,STATUS_ID,CREATE_TIME,LAST_UPDATE_TIME,CREATOR_ID,LAST_UPDATOR_ID) VALUES
		(#entity.dealGroupId#,#entity.isAdmissionRequired#,#entity.amount#,#entity.statusId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.creatorId#,#entity.lastUpdatorId#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_DEAL_GROUP_CITY_ASSN 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,CITY_ID,DEAL_GROUP_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.cityId#,#entity.dealGroup.id#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	
		INSERT INTO TGP_DEAL_GROUP_NAV_CATEGORY_ASSN 
			(DEAL_GROUP_ID,NAV_CATEGORY_ID,CREATE_TIME,LAST_UPDATE_TIME,CREATOR_ID,LAST_UPDATOR_ID,IS_DEFAULT) 
		VALUES 
			(#entity.dealGroup.id#,#entity.categoryId#,#entity.createTime#,#entity.lastUpdateTime#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.isDefault#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO
		TGP_DEAL_GROUP_WORKFLOW_HISTORY
		(DEAL_GROUP_ID,PROCESS_INSTANCE_ID,OPERATION_ID,CREATOR_ID,LAST_UPDATOR_ID,CREATE_TIME,LAST_UPDATE_TIME)
		VALUES
		(#entity.dealGroupId#,#entity.processInstanceId#,#entity.operationId#,#entity.creatorId#,#entity.lastUpdatorId#,#entity.createTime#,#entity.lastUpdateTime#)
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_EDITOR
            (EDITOR_ID,
             EDITOR_NAME,
             EDITOR_TEAM_ID,
             WORKLOAD,
             WORKLOAD_TODAY,
             WORKLOAD_ASSIGNED,
             IS_ACTIVE,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             CREATE_TIME,
             LAST_UPDATE_TIME)
        VALUES (#entity.editorId#,
            #entity.editorName#,
            #entity.editorTeamId#,
            #entity.workload#,
            #entity.workloadToday#,
            #entity.workloadAssigned#,
            #entity.isActive#,
            #entity.creatorId#,
            #entity.lastUpdatorId#,
            #entity.createTime#,
            #entity.lastUpdateTime#);
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
		INSERT INTO TGP_EDITOR_CITY_ASSN
            (EDITOR_ID,
             CITY_ID,
             CREATOR_ID,
             LAST_UPDATOR_ID,
             CREATE_TIME,
             LAST_UPDATE_TIME)
        VALUES (#entity.editorId#,
            #entity.cityId#,
            #entity.creatorId#,
            #entity.lastUpdatorId#,
            #entity.createTime#,
            #entity.lastUpdateTime#);
		

           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_SLIDE_PICTURE 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,SEQ,URL,VISUAL_VIEW_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.sequence#,#entity.url#,#entity.visualView.id#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
		INSERT INTO TGP_VISUAL_VIEW 
		(CREATE_TIME,CREATOR_ID,LAST_UPDATE_TIME,LAST_UPDATOR_ID,VERSION_ID,JSON_CLOSURE,DEAL_GROUP_ID,TITLE,DESCRIPTION,IS_COMMERICAL_AREA_DISPLAYED,
		CORP_DESCRIPTION,
		CORP_NAME,
		CORP_IS_EXPANDED,
		SHORT_DESC,COMMENTS,
		PARENT_VISUAL_VIEW_ID)
		VALUES 
		(#entity.createTime#,#entity.creatorId#,#entity.lastUpdateTime#,#entity.lastUpdatorId#,#entity.versionId#,
		#entity.jsonClosure#,#entity.dealGroupId#,#entity.title#,#entity.description#,#entity.isCommericalAreaDisplayed#,
		#entity.corperationInfo.description#,#entity.corperationInfo.name#,#entity.corperationInfo.isExpanded#,
		#entity.shortDescription#,#entity.comments#,#entity.parentVisualViewId#)
		

		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into TG_MemberOrderProfit(
			OrderID,
			OrderItemID,
			UserID,
			DealGroupID,
			Status,
			AddTime)
		values(
			#orderId#,
			#orderItemId#,
			#userId#,
			#dealGroupId#,
			#status#,
			NOW())
		   <selectKey keyProperty="MemberOrderProfitID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	Insert into TA_FeedBack (Device,UserId,Content,CityID,Email,lat,lng)
	values
	(#feedback.device#,#feedback.userId#,#feedback.content#,
	#feedback.cityId#,#feedback.email#,#feedback.lat#,#feedback.lng#)
	       <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT IGNORE INTO TG_DealRate (
        DealID,
        OrderID,
        UserID,
        Score,
        IsAgain,
        IsExtra,
        ExtraMoney,
        Comment,
        AddDate,
        LastDate,
        DealGroupID,
        Status,
        ExtendBiz)
        VALUES
        (
        #data.dealId#,
        #data.orderId#,
        #data.userId#,
        #data.score#,
        #data.isAgain#,
        #data.isExtra#,
        #data.extraMoney#,
        #data.comment#,
        now(),
        now(),
        #data.dealGroupId#,
        #data.status#,
        #data.extendBiz#)
           <selectKey resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_Attribute
     (Title,
    AttributeKey,
    Format,
    InputType,
    Status,
    AttributeId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.title#,
    #entity.attributeKey#,
    #entity.format#,
    #entity.inputType#,
    #entity.status#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
   <selectKey type="post" keyProperty="id" resultClass="int"/>

		insert into TS_Withdraw
		(	ID,
			WithdrawID,
			AccountID,
			BankAccountID,
			Amount,
			Status,
			Operator,
			Memo,
            OutBizID,
            WithdrawType,
			AddTime,
			UpdateTime
            )
		values
		(
			#withdrawData.id#,
			#withdrawData.withdrawID#,
			#withdrawData.accountID#,
			#withdrawData.bankAccountID#,
			#withdrawData.amount#,
			#withdrawData.status#,
			#withdrawData.operator#,
			#withdrawData.memo#,
            #withdrawData.outBizId#,
            #withdrawData.withdrawType#,
			now(),
			now()
		)
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO TS_CityPortionPoolMapping
		            (CityID,
		             PoolID,
		             Operator,
		             Status,
		             AddTime,
		             UpdateTime)
			VALUES (#cityPortionPoolMappingData.cityId#,
			        #cityPortionPoolMappingData.poolId#,
		            #cityPortionPoolMappingData.operator#,
		            #cityPortionPoolMappingData.status#,
		            now(),
		            now()
		            );
 		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        INSERT INTO TS_GuaranteeForm
        (`CustomerID`,`BankAccountID`,`CityID`,`TotalAmount`,`AllowPostpone`,`AllowReturn`,`ExcludeM`,`Exclusive`,`SalesMan`,`Department`,`DealName`,`SalePrice`,`CostPrice`,`GrossMargin`,`DealMaxCount`,`DealComment`,`Comment`,`EffectiveDate`,`ExpireDate`,`PredictCycleBack`,`Status`,`WorkflowID`,`AddTime`,`UpdateTime`)
        VALUES
        (#guaranteeForm.customerId#,#guaranteeForm.bankAccountId#,#guaranteeForm.cityId#,#guaranteeForm.totalAmount#,#guaranteeForm.allowPostpone#,#guaranteeForm.allowReturn#,#guaranteeForm.excludeM#,#guaranteeForm.exclusive#,#guaranteeForm.salesMan#,#guaranteeForm.department#,#guaranteeForm.dealName#,#guaranteeForm.salePrice#,#guaranteeForm.costPrice#,#guaranteeForm.grossMargin#,#guaranteeForm.dealMaxCount#,#guaranteeForm.dealComment#,#guaranteeForm.comment#,#guaranteeForm.effectiveDate#,#guaranteeForm.expireDate#,#guaranteeForm.predictCycleBack#,#guaranteeForm.status#,#guaranteeForm.workflowId#, now(), now());
       
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
        	INSERT INTO TS_PortionPool
		            (PoolName,
		             MaxAmount,
		             TotalUsed,
		             TotalReturn,
		             LeftAmount,
		             Status,
		             OwnerId,
		             AddTime,
		             UpdateTime)
			VALUES (#portionPoolData.poolName#,
			        #portionPoolData.maxAmount#,
		            #portionPoolData.totalUsed#,
		            #portionPoolData.totalReturn#,
		            #portionPoolData.leftAmount#,
		            #portionPoolData.status#,
		            #portionPoolData.ownerId#,
		            NOW(),
		            NOW()
		            );
 		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        
    INSERT INTO TS_GuaranteeSync (ContractGlobalID,ContractCityID,UnDeductionAmount,ADDTIME,UpdateTime)
    VALUES(#guaranteeSyncData.contractGlobalId#,#guaranteeSyncData.contractCityId#,#guaranteeSyncData.unDeductionAmount#,NOW(),NOW());
 		
           <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    
     INSERT INTO TPA_TemplateOptionAssn
     (TemplateId,
    AttributeId,
    AttributeOptionId,
    TemplateOptionAssnId,
    CreateBy,
    UpdateBy,
    CreateTime,
    UpdateTime)
     VALUES
     (#entity.templateId#,
    #entity.attributeId#,
    #entity.attributeOptionId#,
    #entity.id#,
    #entity.createBy#,
    #entity.updateBy#,
    #entity.createTime#,
    #entity.updateTime#)
    
       <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO TG_EventUrlMapping
        (
            EventID,
            EventName,
            Type,
            ReferURL,
            EntryUTM,
            H5UTM,
            Title,
            Platform,
            Module,
            AddTime
        )
        VALUES
        (
        #eventId#,
        #name#,
        #type#,
        #referUrl#,
        #entryUtm#,
        #h5Utm#,
        #title#,
        #platform#,
        #module#,
        NOW()
        )
           <selectKey keyProperty="MappingID" resultClass="integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO JF_CardLog 
			(SystemType, 
			CardType, 
			UserID, 
			CardNO,
			CardID,
			CardAddress,
			CardZip,
			CardName, 
			CardLogStatus,
			LastPerson, 
			Comments) 
		VALUES (#systemType#, 
				#cardType#, 
				#userId#,
				#cardNo#,
				#cardId#,
				#cardAddress#,
				#cardZip#,
				#cardName#,
				#cardLogStatus#,
				#lastPerson#,
				#comments#)				
		   <selectKey keyProperty="cardLogId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_UserActivityOrder
		(userId,
		type,
		name,
		price,
		count,
		indexNum,
		exchangeDate,
		addDate,
		updateDate
		)
		VALUES(
		#userId#,
		#type#,
		#name#,
		#price#,
		#count#,
		#indexNum#,
		now(),
		now(),
		now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_Flower(FromUserID,ToUserID,ActionType,MainBizID,MinorBizID,AddDate,UpdateDate)
		VALUES
			(#fromUserId#,#toUserId#,#actionType#,#mainBizId#,#minorBizId#,now(),now());
		   <selectKey keyProperty="FlowerID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_UserLocation
			(UserID, CityID, GLat, GLng, AddDate, LastDate, LocationType, DistrictID, RegionID, Memo)
		VALUES
			(
				#location.userId#,
				#location.cityId#,
				   <isNull property="location.gLat"/>
   <isNotNull property="location.gLat"/>
,
				   <isNull property="location.gLng"/>
   <isNotNull property="location.gLng"/>
,
				NOW(),
				NOW(),
				#location.locationType#,
			 	   <isNull property="location.districtId"/>
   <isNotNull property="location.districtId"/>
,
			 	   <isNull property="location.regionId"/>
   <isNotNull property="location.regionId"/>
,
			 	   <isNull property="location.memo"/>
   <isNotNull property="location.memo"/>

			 )
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserBabyInfo(UserID, PregnantState, BabyBirth, BabySex, IsPublic, AddDate, UpdateDate)
		VALUES
		   <iterate conjunction="," property="userBabyList">
      <isNotEmpty property="userBabyList[].babyBirth"/>
      <isEmpty property="userBabyList[].babyBirth"/>
      <isNotEmpty property="userBabyList[].babySex"/>
      <isEmpty property="userBabyList[].babySex"/>
      <isNotEmpty property="userBabyList[].isPublic"/>
      <isEmpty property="userBabyList[].isPublic"/>
   </iterate>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into
		UC_UTag(TagName,Addtime,Updatetime)
		values(#tagName#,now(),now())
		ON DUPLICATE KEY UPDATE TagName =
		VALUES(TagName);

		   <selectKey keyProperty="TagID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into UC_UserTag(UserID,TagID,AddTime,Updatetime)
		VALUES
		   <iterate conjunction="," property="userTagList"/>

		ON DUPLICATE KEY UPDATE TagID = VALUES(TagID);
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO JF_MakeCardData (CardNO, UserName, UserNamePY, UserAddress, UserZip, Type)
			VALUES(
			#cardNo#,
			#userName#,
			#userNamePY#,
			#userAddress#,
			#userZip#,
			#type#);
		
		   <selectKey keyProperty="makeCardId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		
			INSERT INTO JF_MakeCardData (CardNO, UserName, UserNamePY, UserAddress, UserZip, Type)
			VALUES(
			#cardNo#,
			#userName#,
			#userNamePY#,
			#userAddress#,
			#userZip#,
			#type#);
		
		   <selectKey keyProperty="makeCardId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
		UC_DFW_EventRecord
			(UserID,
			MapID,
			EventID,
			CityID,	
			LogType,
			GridIndex,
			RoundCount,		
			AddTime,
			UpdateTime)
		VALUES
			(#userId#,
			#mapId#,
			#eventId#,
			#cityId#,
			#logType#,
			#gridIndex#,
			#roundCount#,
			NOW(),
			NOW()		
			)
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_UserActivity
		(UserId,
		count,
		chooseIndex,
		addDate,
		updateDate
		)
		VALUES(
		#entity.userId#,
		0,
		#entity.chooseIndex#,
		now(),
		now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO ZS_User
    		(UserEmail,
    		UserPW,
    		UserCity,
    		UserSource,
    		UserNickName,
    		MobileNO,
    		UserAddDate,
    		UserLastDate)
   		VALUES
   			(#userEmail#,
   			#userPW#,
   			#userCity#,
   			#userSource#,
   			#userNickName#,
   			#mobileNo#,
   			NOW(),
   			NOW())
		   <selectKey keyProperty="userId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_Dairy (UserID,Title,Visit,Type,Status,Flag,Star,AddDate,UpdateDate,CityID)
		VALUES
            (#userId#, #title#, 0, #type#, #status#, #flag#, #star#, #addDate#, #updateDate#, #cityId#)
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyTopic (Name,EnName,ShowIndex,Status,Icon,Description,AddDate,UpdateDate)
		VALUES
            (#name#, #enName#, #showIndex#, 0, #icon#, #description#, NOW(), NOW())
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_ShareBack(BizType, BizID, BackIP, OID, BID, TID, AddDate)
		VALUES
			 (#bizType#,
			 #bizId#,
			 #ip#,
			 #oid#,
			 #bid#,
			 #tid#,
			 NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserBabyInfo(UserID, PregnantState, BabyBirth, BabySex, IsPublic, AddDate, UpdateDate)
		VALUES
		   <iterate conjunction="," property="userBabyList">
      <isNotEmpty property="userBabyList[].babyBirth"/>
      <isEmpty property="userBabyList[].babyBirth"/>
      <isNotEmpty property="userBabyList[].babySex"/>
      <isEmpty property="userBabyList[].babySex"/>
      <isNotEmpty property="userBabyList[].isPublic"/>
      <isEmpty property="userBabyList[].isPublic"/>
   </iterate>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
		
		INSERT INTO ZS_Msg 
			(	FromUserID, 
				ToUserID, 
				MsgTitle, 
				FromUserOprFlag, 
				ToUserOprFlag, 
				MsgAddDate, 
				IP)
			VALUES
			(	#fromUserId#, 
				#toUserId#, 
				#msgTitle#, 
				#fromUserOprFlag#, 
				#toUserOprFlag#, 
				NOW(), 
				#ip#)
		   <selectKey keyProperty="msgId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserSignIn(YearMonth,UserID,Count,Days,AddTime,UpdateTIme)
		VALUES
			(#userSignIn.yearMonth#,#userSignIn.userId#,#userSignIn.count#,#userSignIn.days#,#userSignIn.addTime#,#userSignIn.updateTime#);
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyText (Content)
		VALUES
            (#content#)
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_Flower(FromUserID,ToUserID,ActionType,MainBizID,MinorBizID,AddDate,UpdateDate)
		VALUES
			(#fromUserId#,#toUserId#,#actionType#,#mainBizId#,#minorBizId#,now(),now());
		   <selectKey keyProperty="FlowerID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_ShareBack(BizType, BizID, BackIP, OID, BID, TID, AddDate)
		VALUES
			 (#bizType#,
			 #bizId#,
			 #ip#,
			 #oid#,
			 #bid#,
			 #tid#,
			 NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO ZS_User
    		(UserEmail,
    		UserPW,
    		UserCity,
    		UserSource,
    		UserNickName,
    		MobileNO,
    		UserAddDate,
    		UserLastDate)
   		VALUES
   			(#userEmail#,
   			#userPW#,
   			#userCity#,
   			#userSource#,
   			#userNickName#,
   			#mobileNo#,
   			NOW(),
   			NOW())
		   <selectKey keyProperty="userId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_UserLocation
			(UserID, CityID, GLat, GLng, AddDate, LastDate, LocationType, DistrictID, RegionID, Memo)
		VALUES
			(
				#location.userId#,
				#location.cityId#,
				   <isNull property="location.gLat"/>
   <isNotNull property="location.gLat"/>
,
				   <isNull property="location.gLng"/>
   <isNotNull property="location.gLng"/>
,
				NOW(),
				NOW(),
				#location.locationType#,
			 	   <isNull property="location.districtId"/>
   <isNotNull property="location.districtId"/>
,
			 	   <isNull property="location.regionId"/>
   <isNotNull property="location.regionId"/>
,
			 	   <isNull property="location.memo"/>
   <isNotNull property="location.memo"/>

			 )
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairySection
			(UserID, 
    		DairyID, 
    		SectionIndex, 
    		SectionType, 
    		Flag, 
    		BizType, 
    		BizID, 
    		Detail, 
    		AddDate, 
    		UpdateDate)
		VALUES
		   <iterate conjunction="," property="sections"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			UC_UserActivityOrder (userId, type, name, addDate ,indexNum)
		VALUES ( #userId#, #type#, #name#, now(), #indexNum#)
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			UC_Ad (CityID,TypeID,TypeName,AdTitle,AdDesc,AdLink,Priority,BeginTime,EndTime,AddTime,UpdateTime,Status)
		VALUES 
			(#ad.cityId#,
	        #ad.typeId#,
	        #ad.typeName#,
	        #ad.adTitle#,
	        #ad.adDesc#,
	        #ad.adLink#,
	        0,
	        #ad.beginTime#,
	        #ad.endTime#,
	        NOW(),
	        NOW(),
	        0)
	       <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyText (Content)
		VALUES
            (#content#)
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_UserLocation
			(UserID, CityID, GLat, GLng, AddDate, LastDate, LocationType, DistrictID, RegionID, Memo)
		VALUES
			(
				#location.userId#,
				#location.cityId#,
				   <isNull property="location.gLat"/>
   <isNotNull property="location.gLat"/>
,
				   <isNull property="location.gLng"/>
   <isNotNull property="location.gLng"/>
,
				NOW(),
				NOW(),
				#location.locationType#,
			 	   <isNull property="location.districtId"/>
   <isNotNull property="location.districtId"/>
,
			 	   <isNull property="location.regionId"/>
   <isNotNull property="location.regionId"/>
,
			 	   <isNull property="location.memo"/>
   <isNotNull property="location.memo"/>

			 )
		   <selectKey keyProperty="id" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyList (UserID,Title,Visit,Status,AddDate,UpdateDate)
		VALUES
            (#userId#, #title#, 0, #status#, now(), now())
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyPic
			(PicID,
			UserID,
			UC_DairyPic.Desc,
			AddDate,
			UpdateDate)
		VALUES
		   <iterate conjunction="," property="dairyPics"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
          UC_DairyTag (Name, AddDate)
        VALUES
          (#tagName#, now())
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into
		UC_UTag(TagName,Addtime,Updatetime)
		values(#tagName#,now(),now())
		ON DUPLICATE KEY UPDATE TagName =
		VALUES(TagName);

		   <selectKey keyProperty="TagID" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		insert into UC_UserTag(UserID,TagID,AddTime,Updatetime)
		VALUES
		   <iterate conjunction="," property="userTagList"/>

		ON DUPLICATE KEY UPDATE TagID = VALUES(TagID);
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_Dairy (UserID,Title,Visit,Type,Status,Flag,Star,AddDate,UpdateDate,CityID)
		VALUES
            (#userId#, #title#, 0, #type#, #status#, #flag#, #star#, #addDate#, #updateDate#, #cityId#)
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyList (UserID,Title,Visit,Status,AddDate,UpdateDate)
		VALUES
            (#userId#, #title#, 0, #status#, now(), now())
   		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairySection
			(UserID, 
    		DairyID, 
    		SectionIndex, 
    		SectionType, 
    		Flag, 
    		BizType, 
    		BizID, 
    		Detail, 
    		AddDate, 
    		UpdateDate)
		VALUES
		   <iterate conjunction="," property="sections"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO
        UC_DairyTagRecord (DairyID, TagID, AddDate)
        VALUES
           <iterate conjunction="," property="tagIds"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserPostInfo(UserID, Address, Phone, Zip, Addressee, PhoneNum, IsDefault, AddTime, UpdateTime)
		VALUES
			 (#userId#,
			 #address#,
			 #phone#,
			 #zip#,
			 #addressee#,
			    <isNotNull prepend="" property="phoneNum"/>
   <isNull prepend="" property="phoneNum"/>

			 #isDefault#,
			 NOW(),
			 NOW())
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserPostInfo(UserID, Address, Phone, Zip, Addressee, PhoneNum, IsDefault, AddTime, UpdateTime)
		VALUES
			 (#userId#,
			 #address#,
			 #phone#,
			 #zip#,
			 #addressee#,
			    <isNotNull prepend="" property="phoneNum"/>
   <isNull prepend="" property="phoneNum"/>

			 #isDefault#,
			 NOW(),
			 NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserPostInfo(UserID, Address, Phone, Zip, Addressee, PhoneNum, IsDefault, Province, City, AddTime, UpdateTime)
		VALUES
			 (#userId#,
			 #address#,
			 #phone#,
			 #zip#,
			 #addressee#,
			    <isNotNull prepend="" property="phoneNum"/>
   <isNull prepend="" property="phoneNum"/>

			 #isDefault#,
			 #province#,
			 #city#,
			 NOW(),
			 NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO 
    		UC_DairyRecommend(CityID,DairyID,UserID,PicID,DairyDate,AddDate,UpdateDate)
    	VALUES
    		   <iterate conjunction="," property="recDairys"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserPostInfo(UserID, Address, Phone, Zip, Addressee, PhoneNum, IsDefault, AddTime, UpdateTime)
		VALUES
			 (#userId#,
			 #address#,
			 #phone#,
			 #zip#,
			 #addressee#,
			    <isNotNull prepend="" property="phoneNum"/>
   <isNull prepend="" property="phoneNum"/>

			 #isDefault#,
			 NOW(),
			 NOW())
	</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserPostInfo(UserID, Address, Phone, Zip, Addressee, PhoneNum, IsDefault, AddTime, UpdateTime)
		VALUES
			 (#userId#,
			 #address#,
			 #phone#,
			 #zip#,
			 #addressee#,
			    <isNotNull prepend="" property="phoneNum"/>
   <isNull prepend="" property="phoneNum"/>

			 #isDefault#,
			 NOW(),
			 NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserPostInfo(UserID, Address, Phone, Zip, Addressee, PhoneNum, IsDefault, Province, City, AddTime, UpdateTime)
		VALUES
			 (#userId#,
			 #address#,
			 #phone#,
			 #zip#,
			 #addressee#,
			    <isNotNull prepend="" property="phoneNum"/>
   <isNull prepend="" property="phoneNum"/>

			 #isDefault#,
			 #province#,
			 #city#,
			 NOW(),
			 NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_UserSignIn(YearMonth,UserID,Count,Days,AddTime,UpdateTIme)
		VALUES
			(#userSignIn.yearMonth#,#userSignIn.userId#,#userSignIn.count#,#userSignIn.days#,#userSignIn.addTime#,#userSignIn.updateTime#);
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO 
    		UC_DairyRecommend(CityID,SecondCategoryID,DairyID,UserID,PicID,ReviewWord,PicCount,DairyDate,AddDate,UpdateDate)
    	VALUES
    		   <iterate conjunction="," property="recDairys"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO JF_CardLog 
			(SystemType, 
			CardType, 
			UserID, 
			CardNO,
			CardID,
			CardAddress,
			CardZip,
			CardName, 
			CardLogStatus,
			LastPerson, 
			Comments) 
		VALUES (#systemType#, 
				#cardType#, 
				#userId#,
				#cardNo#,
				#cardId#,
				#cardAddress#,
				#cardZip#,
				#cardName#,
				#cardLogStatus#,
				#lastPerson#,
				#comments#)				
		   <selectKey keyProperty="cardLogId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_CommonKV
		(
		CKey,
		CValue,
		addDate,
		updateDate
		)
		VALUES(
		#cKey#,
		#cValue#,
		now(),
		now())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO UC_Newerlist_Award
            (CityID,
             YYMM,
             UserID,
             ADDDATE)
		VALUES 
		   <iterate conjunction="," property="cityAwards"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			UC_UserNotice(UserID,ManaScore,UserPower,SendTimes,AddDate,UpdateDate)
		VALUES(#userId#,#manaScore#,#userPower#,#sendTimes#,NOW(),NOW());
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>
		
		INSERT INTO ZS_Msg 
			(	FromUserID, 
				ToUserID, 
				MsgTitle, 
				FromUserOprFlag, 
				ToUserOprFlag, 
				MsgAddDate, 
				IP)
			VALUES
			(	#fromUserId#, 
				#toUserId#, 
				#msgTitle#, 
				#fromUserOprFlag#, 
				#toUserOprFlag#, 
				NOW(), 
				#ip#)
		   <selectKey keyProperty="msgId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO ZS_User
    		(UserEmail,
    		UserPW,
    		UserCity,
    		UserSource,
    		UserNickName,
    		MobileNO,
    		UserAddDate,
    		UserLastDate)
   		VALUES
   			(#userEmail#,
   			#userPW#,
   			#userCity#,
   			#userSource#,
   			#userNickName#,
   			#mobileNo#,
   			NOW(),
   			NOW())
		   <selectKey keyProperty="userId" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO
			UC_DairyPic
			(PicID,
			UserID,
			UC_DairyPic.Desc,
			AddDate,
			UpdateDate)
		VALUES
		   <iterate conjunction="," property="dairyPics"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO 
			UC_DairyShare (DairyID,Comment,Title,Summary,Url,Images,Status,FromUrl,AdminName,RegularDate,ActualDate,AddDate,UpdateDate)
		VALUES 
			(#dairyId#,
	        #comment#,
	        #title#,
	        #summary#,
            #url#,
	        #images#,
	        0,
	        #fromUrl#,
	        #adminName#,
            #regularDate#,
	        NOW(),
	        NOW(),
	        NOW())
	       <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			PicID,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#album.shopId#, 
			#album.name#,
			#album.picPath#, 
			#album.picType#,
			#album.picCount#,
			#album.isMain#,
			#album.picId#,
			#album.description#,
			#album.addTime#,
			#album.updateTime#,0);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			PicID,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#album.shopId#, 
			#album.name#,
			#album.picPath#, 
			#album.picType#,
			#album.picCount#,
			#album.isMain#,
			#album.picId#,
			#album.description#,
			#album.addTime#,
			#album.updateTime#,0);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_SmartSalesEmailOpenCount 
			(Email, CCEmails, TYPE, ADDTIME)
		VALUES
			(#email#, #ccList#, #type#,NOW());
		   <selectKey keyProperty="emailId" resultClass="java.lang.Integer"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

    	INSERT INTO Spider_JobMonitor
    				   (JobID,
    					JobName,
    					Status,
    					JobStartTime,
    					JobRunTime,
    					URLTotalCount,
    					URLFailedCount,
    					TargetPagesTotalCount,
    					TargetPagesFailedCount,
    					ItemsDownloadedCount,
    					AddDate,
    					UpdateDate)
    	VALUES
    		(#jobMonitor.jobId#,
    		 #jobMonitor.jobName#,
    		 #jobMonitor.status#,
    		 NOW(),
    		 #jobMonitor.jobRunTime#,
    		 #jobMonitor.urlTotalCount#,
    		 #jobMonitor.urlFailedCount#,
    		 #jobMonitor.targetPagesTotalCount#,
    		 #jobMonitor.targetPagesFailedCount#,
    		 #jobMonitor.itemsDownloadedCount#,
    		 NOW(), 
    		 NOW())
    	   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO DP_OfficialAlbum (ShopID,
			Name,
			PicPath,
			PicType,
			PicCount,
			IsMain,
			PicID,
			Description,
			AddTime,
			UpdateTime,
			AlbumType) VALUES
			(#album.shopId#, 
			#album.name#,
			#album.picPath#, 
			#album.picType#,
			#album.picCount#,
			#album.isMain#,
			#album.picId#,
			#album.description#,
			#album.addTime#,
			#album.updateTime#,0);
			   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_ProductShop
        (
            ProductID,
            ProductPicUrl,
            ShopID,
            BookingContent,
            ProductPriceID,
            BookingOriginPrice,
            BookingPresentPrice,
            BookingCount,
            BusinessLevel,
            AddTime,
            UpdateTime
        )
        VALUES
        (
            #productShop.productId#,
            #productShop.productPicUrl#,
            #productShop.shopId#,
            #productShop.bookingContent#,
            #productShop.productPriceId#,
            #productShop.bookingOriginPrice#,
            #productShop.bookingPresentPrice#,
            #productShop.bookingCount#,
            #productShop.businessLevel#,
            NOW(),
            NOW()
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

		INSERT INTO Spider_JobConfig
						(JobName, 
						 IsRepeat,
						 JobTiming, 
						 Status,
						 JobPriority,
						 JobType,
						 TargetFindType,
						 SpiderRule,
						 ResultParseRules,
						 Charset,
						 AutoDetect,
						 Frequency,
						 UserAgent,
						 Cookie,
						 DownloadRetryTimes,
						 UrlDiscardTimes,
						 Author,
						 AddDate,
						 UpdateDate)
		VALUES
		(#jobConfig.jobName#,
		#jobConfig.isRepeat#,
		#jobConfig.jobTiming#,
		#jobConfig.status#,
		#jobConfig.jobPriority#,
		#jobConfig.jobType#,
		#jobConfig.targetFindType#,
		#jobConfig.spiderRule#,
		#jobConfig.itemParseRules#,
		#jobConfig.charset#,
		#jobConfig.autoDetect#,
		#jobConfig.frequency#,
		#jobConfig.userAgent#,
		#jobConfig.cookie#,
		#jobConfig.downloadRetryTimes#,
		#jobConfig.urlDiscardTimes#,
		#jobConfig.author#,
		NOW(), 
		NOW())
		   <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

        INSERT INTO WED_WeddingHotelHall
        (
            ShopID,
            PicUserId,
            Name,
            PlanPicID,
            Capacity,
            Height,
            PostCount,
            Stage,
            Money,
            AddTime,
            UpdateTime,
            CapacityMin,
            CapacityMax,
            Remark
        )
        VALUES
        (
            #weddingHotelHall.shopId#,
            #weddingHotelHall.picUserId#,
            #weddingHotelHall.name#,
            #weddingHotelHall.planPicId#,
            #weddingHotelHall.capacity#,
            #weddingHotelHall.height#,
            #weddingHotelHall.postCount#,
            #weddingHotelHall.stage#,
            #weddingHotelHall.money#,
            NOW(),
            NOW(),
            #weddingHotelHall.capacityMin#,
            #weddingHotelHall.capacityMax#,
            #weddingHotelHall.remark#
        )
           <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

       INSERT INTO DP_BookingShop_All
       (
          ShopID,
          Status,
          AddDate,
          LastDate
       )
       VALUES
       (
          #shopId#,
          1,
          NOW(),
          NOW()
       )
          <selectKey keyProperty="id" resultClass="int"/>
</insert>
<?xml version="1.0" encoding="utf-8"?>
<insert>

	    INSERT INTO $table$
	    	(
	    	   <iterate conjunction="," property="cols"/>

	    	)
    		VALUES
    		   <iterate conjunction="," property="vals"/>
</insert>
