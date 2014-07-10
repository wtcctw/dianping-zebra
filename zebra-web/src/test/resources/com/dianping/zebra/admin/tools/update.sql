
		UPDATE 
			TG_EventUserLog 
		SET
			EventId=#eventUserLog.eventId#,
			UserId=#eventUserLog.userId#,
			UserNickName=#eventUserLog.userNickName#,
			MobileNo=#eventUserLog.mobileNo#,
			AddDate=#eventUserLog.addDate#,
			CityId=#eventUserLog.cityId#,
			PrizeId=#eventUserLog.prizeId#,
			IP=#eventUserLog.iP#,
			Memo=#eventUserLog.memo#,
			ReferId=#eventUserLog.referId#
		WHERE
			LogId=#eventUserLog.logId#
	
		UPDATE 
			TG_EventPrize 
		SET
			PrizeName=#eventPrize.prizeName#,
			PrizeImage=#eventPrize.prizeImage#,
			PrizeDesc=#eventPrize.prizeDesc#,
			PrizeType=#eventPrize.prizeType#,
			Stocks=#eventPrize.stocks#,
			Odds=#eventPrize.odds#,
			Priority=#eventPrize.priority#,
			Consumption=#eventPrize.consumption#,
			SmsText=#eventPrize.smsText#,
			BeginDate=#eventPrize.beginDate#,
			EndDate=#eventPrize.endDate#,
			Status=#eventPrize.status#,
			EventId=#eventPrize.eventId#,
			Config=#eventPrize.config#
		WHERE
			PrizeId=#eventPrize.prizeId#
	
		
		UPDATE 
			TG_EventPrize 
		SET
			Consumption=(Consumption+1)
		WHERE
			PrizeId=#prizeId# AND (Stocks=0 OR Stocks>Consumption)
		
	
		UPDATE TGE_SecondOrder SET
			SecondPrizeID = #data.secondPrizeId#,
			UserID = #data.userId#,
			Status = #data.status#,
			Quantity = #data.quantity#,
			IpAddress = #data.ipAddress#,
			MobileNo = #data.mobileNo#,
			HippoId = #data.hippoId#
		WHERE SecondOrderID = #data.id#
	
   
	   UPDATE TG_EventThirdCoupon 
		SET	
		STATUS = #status#
		
		WHERE
		ThirdCouponID = #id#
   
   
		UPDATE
		TG_Event
		SET
		CurrentJoin=CurrentJoin+1
		WHERE
		EventID=#eventId# AND (MaxJoin=0 OR MaxJoin>CurrentJoin)
	
		UPDATE 
			TG_EventCity 
		SET
			Status=#eventCity.status#
		WHERE
			EventId=#eventCity.eventId#
			AND CityId=#eventCity.cityId#
	
		
			UPDATE 
				TG_AppDeductUser
			SET
				Amount= Amount+1
			WHERE
				(UserId=#orderNotifyDTO.userId# OR UserMobile=#orderNotifyDTO.mobileNo#) AND Amount<10
		
	
		UPDATE
			TG_EventDeal A
		SET
			A.CurrentJoin = A.CurrentJoin + #count#
		WHERE
			A.EventDealID IN (SELECT B.EventDealID FROM TG_EventCityDeal B WHERE B.CityID=#cityId# AND B.EventID=#eventId#)
			AND A.Status=1 AND A.DealGroupID=#dealGroupId#
	
		UPDATE
		TG_EventPoint
		SET
		Point=Point+#point#,
		LastDate=now()
		WHERE
		UserId=#userId#
	
		
		UPDATE 
			TG_EventPoint 
		SET
			Point=Point-#point#,
			LastDate=now()
		WHERE
			UserId=#userId# AND Point>=#point#
		
	
		UPDATE TGE_SecondPrize SET
			Name = #data.name#,
			BeginDate = #data.beginDate#,
			EndDate = #data.endDate#,
			BannerImage = #data.bannerImage#,
			Status = #data.status#,
			Priority = #data.priority#,
			Type = #data.type#,
			TypeReferID = #data.typeReferId#,
			Price = #data.price#,
			MarketPrice = #data.marketPrice#,
			MaxJoin = #data.maxJoin#,
			CurrentJoin = #data.currentJoin#,
			UpdateDate = NOW()
		WHERE SecondPrizeID = #data.id#
	

		UPDATE TGE_SecondPrize SET
			CurrentJoin = CurrentJoin + #quantity#,
			UpdateDate = NOW()
		WHERE SecondPrizeID = #id#
		  AND CurrentJoin < MaxJoin
        
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			BannerImage=#bannerImage:VARCHAR:No Image#,
			Status=#status#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			Status=#status#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_TopicItemCity
		set
			DealGroupIDs=#dealGroupIDS#
		where
			TopicItemID=#topicItemID# and CityID=#cityId#
			
	
		update 
			TG_Event_TopicItem
		set
			TopicItemName=#topicItemName#,
			DisplayCount=#displayCount#,
			Priority=#priority#
		where
			TopicItemID=#topicItemID#
	
    	
    		UPDATE CacheServerGroup 
    		SET Servers = #servers#, UpdatedTime = #updatedTime#
    		WHERE `Group` = #group#
    	
    
		
			UPDATE AC_HighRiskAccount
			SET
				Status=#status#,
				UpdateTime=NOW()
			WHERE
				UserId=#userId#
		
	
		UPDATE DP_ThirdUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
		UPDATE DP_ThirdUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
    	UPDATE
    		DP_AdItem
    	SET
    		CityID = #cityId#,
    		PlaceID = #placeId#,
    		ContentID = #contentId#,
    		TimeRange = #timeRange#,
    		Status = #status#,
    		UpdateTime = now()
    	WHERE
    		KID = #kId#
    
    	UPDATE
    		DP_AdItem
    	SET
    		Status = #status#,
    		UpdateTime = now()
    	WHERE
    		KID = #kId#
    		
		UPDATE AC_LostUser 
		SET
			UpdateTime = now(), 
			STATUS = 1	
		WHERE
			UserID = #userId# and Status = 0
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET TokenStatus = #TokenStatus#
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET DeviceID = #DeviceID#
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET	TokenStatus = 0 , ExInfo = #ExInfo#
		WHERE TokenType = 6 AND Content = #Content# AND DeviceID = #DeviceID#
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET	TokenStatus = TokenStatus - 1 
		WHERE TokenType = 6 AND Content = #Content# AND DeviceID = #DeviceID#
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET	ExInfo = #ExInfo# 
		WHERE ID = #ID# AND TokenType = 7
	
	
		UPDATE CI_UserInfo 
		SET CityID = #CityID# 
		WHERE UserID = #UserID#
	
		UPDATE CI_UserRank SET UserNickname = #NickName#, UserAvatar = #Avatar# WHERE UserID = #UserID#
	

    	UPDATE CI_MsgDeviceId
		SET Status = 1
		WHERE DeviceID = #DeviceID#

     
		AND DeviceId = #DeviceId#
   	 
		AND UserID = #UserID# AND MessageType = 0
    
   		UPDATE DianPingMobile.CI_MsgDeviceId 
		SET Flag = 2
		WHERE DeviceId = #DeviceId#
   	
    	UPDATE DianPingMobile.CI_Notifications 
		SET Flag = 2
		WHERE UserID = #UserID# AND MessageType = 0
    
		UPDATE CIT_Transition SET Status = #Status# WHERE UserID = #UserID#
	
		UPDATE CI_MiPush 
		SET UserID = #UserID#, UpdateTime = NOW()
		WHERE ID = #ID#	
	
		UPDATE CI_MiPush 
		SET MIRegID = #MIRegID#, UpdateTime = NOW()
	 	WHERE ID = #ID#
	
		UPDATE
			GPA_ActivityAdminPower
		SET
			TypeIDs = #activityAdminPower.typeIds#,
			CityIDs = #activityAdminPower.cityIds#,
			UpdateTime = NOW()
		WHERE
			AdminID = #activityAdminPower.adminId#
	
		update
			GPA_ActivityMode
		set
			ModeName=#modeName#,
			UpdateTime=now()
		where
			ModeID=#modeId#
	
		UPDATE
			GPA_OfflineActivityBlackList
		SET
			BlackListType=#blackListType#,
			UpdateTime=NOW()
		WHERE
			BlackListID=#blackListId#
	
        UpdateTime=NOW()
        WHERE
        BlackListID=#blackListId#
    
		UPDATE
			GPA_OfflineActivityBlackList
		SET
			BeginTime=#blackList.beginTime#,
			EndTime=#blackList.endTime#,
			ValidDays=#blackList.validDays#,
			AdminID=#blackList.adminId#,
			UpdateTime=NOW()
		WHERE
			UserID=#blackList.userId# AND BlackListType=#blackList.blackListType#
	
    	UPDATE 
    		GPA_OfflineActivityPushTask
    	SET
    		TaskStatus = #taskStatus#
    	WHERE
    		PushID = #pushId#
    
		UPDATE
			GPA_OfflineActivityShareSetInfo
		SET
			ShareType = #shareType#,
			ShareBody = #shareBody#,
			SharePic = #sharePic#,
			UpdateTime = NOW()
		WHERE
			OfflineActivityID = #offlineActivityId#
	
		set @curRow = 0
	
		
			UPDATE AC_MobileVerify
			SET
				IsValid = #isValid#,
				UpdateTime = NOW()
			WHERE 
				VerifyID = #verifyId#
		
	
		UPDATE DianPingMC.MC_MemberCardProductConfirmInfo 
		SET
			ProductName = #productName#,
			ProductDiscountRate = #productDiscountRate#,
			ProductDesc = #productDesc#,
			BeginDate = #beginDate#,
			EndDate = #endDate#,
			AuthAdminID = #authAdminID#,
			AuthAdminName = #authAdminName#
		WHERE ShopConfirmInfoID = #shopConfirmInfoID#
			  AND ProductType = #productType#
	
		update
			CV_ContentVerify_Note_HIS
		set
			VerifyResult=#verifyResult#, ManVerifyTime = NOW()
		where
			ID=#id#
	
		update
			CV_ContentVerify_Review_HIS
		set
			VerifyResult=#verifyResult#, ManVerifyTime = NOW()
		where
			ID=#id#
	
        
        UPDATE MAT_RoleAuthorityRelation SET
        status = #status#
       	WHERE roleId = #roleId#
		
    
        
        UPDATE MAT_RoleAuthorityRelation SET
        roleId = #roleAuthority.roleId#,
        status = #roleAuthority.status#,
        authorityId = #roleAuthority.authorityId#
       	WHERE id = #roleAuthority.id#
		
    
        UPDATE APP_Apps
        SET `downloads` = `downloads`+1
        WHERE `id` = #id#;
    
		UPDATE
			GPA_OfflineActivityCombo
		SET
			comboName = #combo.comboName#,
			UpdateTime = NOW()
		WHERE
			OfflineActivityID = #combo.offlineActivityId#
			And comboId = #combo.comboId#
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				Token = #token#
		
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				InternalToken = #internalToken#
		
	
		UPDATE DP_ThirdUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
		UPDATE DP_ThirdUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
		UPDATE ZS_User
		SET MobileNOStatus=#mobileNoStatus#
		Where UserID=#userId#
    
		UPDATE
		ZS_User
		SET MobileNO=#mobileNo#,
		MobileNOStatus=#mobileNoStatus#
		Where
		UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserEmail=#email#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		EmailVerifyStatus=#emailVerifyStatus#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserNickName=#nickname#
		Where UserID=#userId#
    
		
			UPDATE AC_HighRiskAccount
			SET
				Status=#status#,
				UpdateTime=NOW()
			WHERE
				UserId=#userId#
		
			
		UPDATE AC_LostUser 
		SET
			UpdateTime = now(), 
			STATUS = 1	
		WHERE
			UserID = #userId# and Status = 0
	
    	UPDATE DP_UserResetPasswordLog
    	SET IsValid=0,
    		UpdateTime=NOW()
    	Where UserID=#userId#
    
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				Token = #token#
		
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				InternalToken = #internalToken#
		
	
	 
	   update ZS_User set UserNickName=#userNickName# where UserID=#userID#
		
	
	   UPDATE ZS_UserProfile 
	   SET UserSex = #gender#, UpdateDate = NOW()
	   WHERE UserID = #userID#
	
		UPDATE DianPing.ZS_User 
		SET UserCity = #UserCity#
		WHERE UserID = #UserID#
	
		UPDATE CIT_Transition 
		SET Status = #status#
		WHERE UserID = #userID#
	
		UPDATE 
			GPA_ActivityType
		SET 
			TypeName=#typeName#,UpdateTime=NOW()
		WHERE 
			TypeID=#typeId#
	
		UPDATE  
			GPA_OfflineActivityExtract
		SET
			Status = #status#
		WHERE	
			OfflineActivityID = #offlineActivityId#
	
		UPDATE  
			GPA_OfflineActivityExtract
		SET
			Status = #extractVO.status#,
			ValidDays = #extractVO.validDays#,
			ApplyEndTime=#extractVO.applyEndTime#,
			UpdateTime = NOW()
		WHERE	
			OfflineActivityID = #extractVO.offlineActivityId#
	
		UPDATE
			GP_EventFollowNote
		SET
			NoteBody=#noteBody#,
			IP=#ip#,
			Status=#status#,
			UpdateDate=NOW()
		WHERE
			FollowNoteID=#followNoteId#
	
		UPDATE
			GP_EventFollowNote
		SET
			Status=#status#,
			UpdateDate=NOW()
		WHERE
			FollowNoteID=#followNoteId#
	
    
		UPDATE
			GPA_OfflineActivitySerialNoPool
		SET
			Status = 1,
			TicketID = #ticketId#,
			UpdateTime=NOW()
		WHERE
			ID = #id#
	
		UPDATE
			GPA_OfflineActivitySerialNoPool
		SET
			Status = 1,
			TicketID = #ticketId#,
			UpdateTime=NOW()
		WHERE
			SerialNumber = #serialNumber#
	
		update 
			GPA_OfflineActivitySerialNosPoolBatchID 
		SET
			Memo = #memo#
		WHERE
			batchID = #batchID#
	
	
		UPDATE DP_AliPayWalletToken SET
		UpdateDate = now(),
		Token=#token#
		WHERE 
		DPUid = #dpUid#
	
		UPDATE ZS_User
		SET MobileNOStatus=#mobileNoStatus#
		Where UserID=#userId#
    
		UPDATE
		ZS_User
		SET MobileNO=#mobileNo#,
		MobileNOStatus=#mobileNoStatus#
		Where
		UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserEmail=#email#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		EmailVerifyStatus=#emailVerifyStatus#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserPW=#userPW#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserNickName=#nickname#
		Where UserID=#userId#
    
		
			UPDATE MC_MemberCard m
			SET m.TopOrder = #topOrder#
			WHERE m.MemberCardID = #memberCardId# 
			
	
		
			UPDATE MC_MemberCard m
			SET m.PromoteOrder = #promoteOrder#
			WHERE m.MemberCardID = #memberCardId# 
			
	
		
			UPDATE MC_MemberCard m
			SET m.Title=#title#,m.SubTitle=#subTitle#,m.BgImageID=#bgImageId#,m.FontColor=#fontColor#,m.LogoID=#logoId#
			WHERE m.MemberCardID = #memberCardId#
			
	
	        WHERE m.MemberCardID = #memberCardId#
	
		
			UPDATE MC_MemberCardBgImage i
			SET i.Name=#name#,i.PicPath=#picPath#
			WHERE i.BgImageID = #bgImageID#
			
	
		
			UPDATE MC_MemberCard m
			SET m.CustomBgImageID = #customBgImageId#
			WHERE m.MemberCardID = #memberCardId#
			
	
		
			UPDATE MC_MemberCard m
			SET m.CustomBgImageID = 0
			WHERE m.MemberCardID = #memberCardId#
			
	
			WHERE r.RecomID = #mcl.recomID# 
	
		
			DELETE FROM MC_MemberCardLiteralRecomCity
			WHERE RecomID = #recomID# 
			
	
			UPDATE MC_MemberCard m
			SET m.CurrentScore = #score#
			WHERE m.MemberCardID = #memberCardId# 
	
			UPDATE MC_MemberCardScoreLog s
			SET s.Status = #status#, s.IsActive = #isActive#
			WHERE s.LogID = #logId# 
	
			UPDATE MC_MemberCardScoreDeductCancelApply ca
			SET ca.Status = #status#, ca.AuditAdminID = #auditAdminId#, ca.AuditContent = #auditContent#
			WHERE ca.ApplyID = #applyId# 
	
			UPDATE MC_MemberCard m
			SET m.LogoID = #logoId#, m.BgImageID = #bgImageId#, m.FontColor = #fontColor#
			WHERE m.MemberCardID = #memberCardId# 
	
		
			UPDATE MC_MemberCardApply SET Status = #status# 
			WHERE ShopID = #shopId#
			
	
		UPDATE MC_MemberCardCompanyConfirmInfo
		SET CitySelected = #citySelected#,
			CrmID = #crmID#,
			CardType = #cardType#,
			AuthAdminID = #authAdminID#,
			AuthAdminName = #authAdminName#
        	WHERE ID = #companyConfirmInfoID#
	
		UPDATE MC_MemberCardShopConfirmInfo 
		SET ShopName = #shopName#,
			ShopManager = #shopManager#,
			ShopManagerTel = #shopManagerTel#,
			ProductSelected = #productSelected#,
			OtherMemberCard = #otherMemberCard#,
			Deleted = 0,
			AuthAdminID = #authAdminID#,
			AuthAdminName = #authAdminName#
		WHERE CrmID = #crmID# 
			  AND CompanyConfirmInfoID = #companyConfirmInfoID#
		      AND ShopID = #shopID#
	
		UPDATE MC_MemberCardCompanyConfirmInfo
		SET Status = #status#
        	WHERE CrmID = #crmID#
	
      update DP_Account_LastLoginRecord
      	set
        `UpdateDate` = #lastLoginRecord.updateDate#,
        `System` = #lastLoginRecord.system#,
        `LastLoginTime` = #lastLoginRecord.lastLoginTime#
      	where
        	`LoginId` = #lastLoginRecord.loginId#
    
        
        UPDATE MAT_Authority SET
        description = #authority.description#,
        type = #authority.type#,
        status = #authority.status#
       	WHERE authorityId = #authority.authorityId#
       	AND code = #authority.code#
		
    
		UPDATE
			GPA_OfflineActivityUser
		SET
			ConfirmStatus=#confirmStatus#
		WHERE
			OfflineActivityID=#activityId# AND UserID=#userId#
	
		UPDATE
			GPA_OfflineActivityUser
		SET
			Role=#role#
		WHERE
			OfflineActivityID=#activityId# AND UserID=#userId#
	
		UPDATE
			GPA_OfflineActivityUser
		SET
			PhoneNo=#activityUser.phoneNo#,
			ShippingAddress=#activityUser.shippingAddress#,
			Email=#activityUser.email#,
			Birthday=#activityUser.birthday#,
			MarryDay=#activityUser.marryDay#,
			BabyInfo=#activityUser.babyInfo#,
			ExtraCount=#activityUser.extraCount#,
			Role=#activityUser.role#,
			ApplyExtendInfo1=#activityUser.applyExtendInfo1#,
			ApplyExtendInfo2=#activityUser.applyExtendInfo2#,
			ApplyExtendInfo3=#activityUser.applyExtendInfo3#,
			comboId=#activityUser.comboId#,
			branchId=#activityUser.branchId#
		WHERE
			ID=#activityUser.id#
	
		UPDATE
			GPA_OfflineActivityUser
		SET
			NoticeTime=NOW()
		WHERE
			UserID=#userId# AND OfflineActivityID=#activityId#
	
    	
    		UPDATE DP_CacheConfiguration 
    		SET ClientClazz = #clientClazz#, Servers = #servers#, TranscoderClazz = #transcoderClazz#
    		WHERE CacheKey = #cacheKey#
    	
    
		
			UPDATE AC_MobileVerify
			SET
				IsValid = #isValid#,
				UpdateTime = NOW()
			WHERE 
				VerifyID = #verifyId#
		
	
		
			UPDATE AC_MobileVerify
			SET
				IsValid = #isValid#,
				UpdateTime = NOW()
			WHERE 
				VerifyID = #verifyId#
		
	
    	
			UPDATE DP_Adwords SET
			KID=#kid#,
			Adtype=#adType#,
			Keyword=#keyword#,
			ContentID=#contentId#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			Status=#status#,
			Priority=#priority#,
			LastUpdateDate=NOW(),
			CityID=#cityId#,
			ShopType=#shopType#,
			RawKeyword=#rawKeyword#,
			ShopID=#shopId#
			WHERE KID=#kid#
	    
    
    	
			UPDATE DP_AdwordsContent SET
			ContentID=#contentId#,
			Content=#content#,
			LastUpdateDate=NOW()
			WHERE ContentID=#contentId#
	    
    
		UPDATE ZS_User
		SET MobileNOStatus=#mobileNoStatus#
		Where UserID=#userId#
    
		UPDATE
		ZS_User
		SET MobileNO=#mobileNo#,
		MobileNOStatus=#mobileNoStatus#
		Where
		UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserEmail=#email#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		EmailVerifyStatus=#emailVerifyStatus#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserNickName=#nickname#
		Where UserID=#userId#
    
		UPDATE DP_ThirdUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
		UPDATE DP_ThirdUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
   		UPDATE CI_CheckIn 
   		SET Status = #status# 
   		WHERE CheckInID = #checkInID#		
   
   		UPDATE CI_CheckIn 
   		SET photos = #photos#, deviceId = #deviceId#
   		WHERE CheckInID = #checkInID#		
   

   		UPDATE CI_CheckIn 
   		SET TotalComment = TotalComment + #increment# 
   		WHERE CheckInID = #checkInID#
		
   
    	UPDATE CI_CheckIn SET CityID = #cityId#, ShopType = #shopType#
        WHERE CheckInID = #checkInId#
    

   		UPDATE CI_CheckIn 
   		SET ShopID = #ToShopID# 
   		WHERE ShopID = #FromShopID#
		
   
   
   		UPDATE CI_CheckIn 
   		SET Queuing = #queuingEnum# 
   		WHERE CheckInID=#checkInID#
   		
   
   		UPDATE CI_CheckIn 
		SET LocalFilter = 1 
		WHERE CheckInID=#checkInID#
   

		UPDATE CI_HoneyInvitation 
		SET LastTime = CURRENT_TIMESTAMP, UserIP = #UserIP#, Memo = #Memo# 
		WHERE UserID = #UserID# AND InviteUserID = #InviteUserID#
    

    
	    
				DELETE FROM DianPingMobile.DP_Residence 
				WHERE deviceid = #deviceid#
	    	
	 
   		UPDATE CI_UserExtend 
   		SET ParamValue=#snsImportedFlags# 
        WHERE UserId=#userId# AND ParamName='SnsImportedFlags' 
   
		UPDATE CI_ScoreBoardAD set checkintime = null
		where ID = #id# and status = 0
	
		update CI_ScoreBoardAD
		set activatetime = #time#, status = #status#
		where id = #id#
	
  		UPDATE CI_IPhonePush SET PToken = '', UpdateTime = null WHERE PToken = #ptoken#
   
 		UPDATE CI_IPhonePush 
 		SET ClientID = #clientID#, Channel = #channel#, BeginHour =  #beginHour#, EndHour = #endHour#, UpdateTime = null
 		WHERE DeviceID = #deviceID#
    
    	UPDATE CI_IPhonePush 
    	SET ClientID = #clientID#, UserID = #userID#, UpdateTime = null
    	WHERE DeviceID = #deviceID#
    
    	UPDATE CI_IPhonePush
    	SET ClientID = #clientID#, PToken = #pToken#, UpdateTime = null
    	WHERE DeviceID = #deviceID#
    
    	UPDATE CI_IPhonePush
    	SET ClientID = #clientID#, 
    		UserID = #userID#, UpdateTime = null
    	WHERE OldDeviceID = #oldDeviceId#
    
 		UPDATE CI_IPhonePush
 		SET ClientID = #clientID#, 
 			Channel = #channel#, 
 			BeginHour = #beginHour#, 
 			EndHour = #endHour#,
 			UpdateTime = null
 		WHERE OldDeviceID = #oldDeviceId#
    
 		UPDATE CI_IPhonePush
 		SET DeviceID = #DeviceID#, UpdateTime = null
 		WHERE OldDeviceID = #oldDeviceId#
    
		UPDATE
			GPA_OfflineActivitySubscribe
		SET
			Status = 1,
			UpdateTime = NOW()
		WHERE
			Status = 0
		AND
			Email = #email#
	
		UPDATE
			GPA_OfflineActivityThirdPartyExtInfo
		SET
			DataValue = #extInfo.dataValue#,
			UpdateTime = NOW()
		WHERE
			OfflineActivityID = #extInfo.offlineActivityId# AND SourceType = #extInfo.sourceType# AND DataName = #extInfo.dataName#
	
		UPDATE
			GPA_ThirdPartyActivity
		SET
			IsAccess = #accessStatus#,
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE
			GPA_ThirdPartyActivity
		SET
			IsUpdate = #updateStatus#,
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE
			GP_VIPEventConfig
		SET
			Status = #status#
		WHERE
			ID = #configId#
	
		UPDATE DP_QQFriendShip
		SET FriendDPUid=#friend.friendDPUid#, FriendFace=#friend.friendFace#, FriendNickName=#friend.friendNickName#, ADDDATE=NOW()
			WHERE OpenID=#friend.openId# AND FriendID=#friend.friendId#
	
		UPDATE DP_QQFriendShip
		SET FriendDPUid=#friendDPUid#, FriendFace=#friendFace#, FriendNickName=#friendNickName#, ADDDATE=NOW()
			WHERE OpenID=#openId# AND FriendID=#friendId#
	
		UPDATE DP_ThirdUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Status = 0,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
		UPDATE DP_ThirdUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			ThirdUid = #thirdUid# and
			DPUid = #dPUid# and
			Type = #type#
	
		UPDATE DP_RenRenUser
		SET
			Token = #token#,
			UpdateDate = now()
		WHERE 
			RRUid = #rRUid# and
			DPUid = #dPUid#
	
		UPDATE 
			DP_UserBlackList
		SET 
			Status=#newStatus#
		WHERE
			UserID=#userId# AND Status=#oldStatus#
	
		UPDATE ZS_User
		SET MobileNOStatus=#mobileNoStatus#
		Where UserID=#userId#
    
		UPDATE
		ZS_User
		SET MobileNO=#mobileNo#,
		MobileNOStatus=#mobileNoStatus#
		Where
		UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserEmail=#email#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		EmailVerifyStatus=#emailVerifyStatus#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserPW=#userPW#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserNickName=#nickname#
		Where UserID=#userId#
    
		UPDATE MC_MemberCardShop
		SET
		FirstCatgory = #firstCatgory# ,
		SecondCatgory = #secondCatgory# ,
		OtherMemberCard = #otherMemberCard# ,
		PrivilegeGrade = #privilegeGrade#
		WHERE
		ShopID = #shopId# AND MemberCardID = #memberCardId#
	
	
	
	
    
        UPDATE MC_MemberCardShopConfirmInfo
        SET
        CheckedForBossAccount = 0
        WHERE
        CrmID = #crmID#
    
		UPDATE MC_MemberCardShopConfirmInfo
		SET
			ProductSelected = #productSelected#
		WHERE
			ID = #ID#
	
		update
			CV_ContentVerify_Category
		set
			CategoryId=#categoryID#, AddDate = now()
		where
			ID=#id#
	
		
			update
				CV_ContentVerify_HIS
			set
				ManVerifyResult=#status#,
				AdminID=#adminId#,
				ManVerifyTime=#manVerifyTime#
			where
				ID=#contentVerifyId# and ManVerifyResult<>4
		
	
		update
			CV_ContentVerify_HIS
		set
			TrainCount=#trainCount#
		where
			ID=#contentVerifyId#
	
		update
			CV_ContentVerify_FollowNote_HIS
		set
			VerifyResult=#verifyResult#, ManVerifyTime = NOW()
		where
			ID=#id#
	
        UPDATE DP_Account
        SET
            `IDNumber` = #accountRow.idNumber#,
            `EmployeeNumber` = #accountRow.employeeNumber#,
            `LoginId` = #accountRow.loginId#,
            `Gmail` = #accountRow.gmail#,
            `GmailStatus` = #accountRow.gmailStatus#,
            `Gmail2` = #accountRow.gmail2#,
            `Gmail2Status` = #accountRow.gmail2Status#,
            `AD` = #accountRow.ad#,
            `ADStatus` = #accountRow.adStatus#,
            `SysStatus` = #accountRow.sysStatus#,
            `Name` = #accountRow.name#,
            `UpdateTime` = #accountRow.updateTime#,
            `PasswordUpdateTime` = #accountRow.passwordUpdateTime#,
            `IsFrozen` = #accountRow.frozenStatus#
         WHERE id=#accountRow.accountId#
    
		UPDATE
			GPA_ThirdPartyActivity
		SET
			Title = #thirdPartyActivity.title#,
			DetailLink = #thirdPartyActivity.detailLink#,
			Body = #thirdPartyActivity.body#,
			CityName = #thirdPartyActivity.cityName#,
			Venues = #thirdPartyActivity.venues#,
			DateInfo = #thirdPartyActivity.dateInfo#,
			RegionName = #thirdPartyActivity.regionName#,
			DetailAddress = #thirdPartyActivity.detailAddress#,
			BeginDate = #thirdPartyActivity.beginDate#,
			EndDate = #thirdPartyActivity.endDate#,
			CostMemo = #thirdPartyActivity.costMemo#,
			TypeName = #thirdPartyActivity.typeName#,
			Poster = #thirdPartyActivity.poster#,
			TicketNum = #thirdPartyActivity.ticketNum#,
			Actors = #thirdPartyActivity.actors#
		WHERE
			ID = #thirdPartyActivity.id# 
	
		UPDATE
			GPA_ThirdPartyActivity
		SET
			UpdateTime = NOW()
		WHERE
			ID = #id# 
	
		set @curRow = 0
	
    	
    		UPDATE DP_CacheKeyConfiguration
			SET VERSION = VERSION + 1
			WHERE Category = #category#
    	
	
		
		UPDATE DP_CacheKeyConfiguration 
   		SET Duration = #duration#, IndexTemplate = #indexTemplate#, IndexDesc = #indexDesc#, CacheType = #cacheType#, SyncDNet = #sync2Dnet#,
   			IsHot = #hot#
   		WHERE Category = #category#
		
	
    	UPDATE DP_UserResetPasswordLog
    	SET IsValid=0,
    		UpdateTime=NOW()
    	Where UserID=#userId#
    
		UPDATE MAC_Account 
		SET BALANCE=BALANCE + #balance#, 
		EXTRA=EXTRA + #extra# 
		WHERE CUSTOMERID=#customerid#
	
		
			UPDATE DPAdwords.DP_AdwordsTemplate
			SET TemplateTitle = #templateTitle#, TemplateContent = #templateContent#
			WHERE TemplateId = #templateId#
			
    
		UPDATE DP_AliPayWalletToken SET
		UpdateDate = now(),
		Token=#token#
		WHERE 
		DPUid = #dpUid#
	
		UPDATE DP_Shop 
		SET Power = #power#
		WHERE ShopID = #shopID#
	
		UPDATE DP_Shop 
		SET GLat = #lat#,GLng= #lng#		
		WHERE ShopID = #shopID#
	
		UPDATE CI_Comments 
		SET STATUS = 4 
		WHERE CommentID=#CommentID# AND UserID=#UserID#
	
		UPDATE CI_Comments, CI_CheckIn 
		SET CI_Comments.Status = 4 
		WHERE CI_Comments.RootCheckInID = CI_CheckIn.CheckInID AND 
			CI_Comments.CommentID = #CommentID# AND CI_CheckIn.UserID = #UserID#
	
    	UPDATE CI_Comments 
    	SET Status=#status# WHERE CommentID=#commentId#;
    
	 	UPDATE CI_Lottery_DeviceID_PrizeID
		SET userID=#userId#
		WHERE deviceID=#deviceId# AND userID=0;
	 
	 	UPDATE CI_Lottery_DeviceID_PrizeID
		SET userID=#uesrId#
		WHERE deviceID=#deviceId# AND prizeID IN 
    	(SELECT ID FROM CI_Lottery_PrizeItem WHERE setID=#setId#)
	 
		UPDATE CI_Lottery_UserStatus
		SET UsedCounter=UsedCounter+1
		WHERE LotteryID=#setId# AND DeviceID=#deviceId#
	
		UPDATE CI_Lottery_UserStatus
		SET TotalCounter = TotalCounter + #count#
		WHERE LotteryID = #setId# AND DeviceID=#deviceId#
	
    		
    		UPDATE CI_BadgeCount SET RewardNumber = RewardNumber + 1 
    		WHERE BadgeID = #BadgeID# and RewardNumber = #count#
    	
    
		UPDATE CI_UserBadgeReward 
		SET IsUsed = TRUE, UsedTime = NOW() 
		WHERE UserID = #UserID# AND RewardID = #RewardID#
	
		INSERT INTO CI_UserBadgeReward 
		 (BadgeID, UserID, RewardID) 
		VALUES 
		 (#BadgeID#, #UserID#, #RewardID#)
	
		UPDATE DianPingMobile.CI_EBizPromo 
		SET IsShow = 0
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_EBizPromoSerialNum 
		SET IsUsed = 1, UpdateDate = NOW()
		WHERE ID = #ID# AND IsUsed = 0
	
		UPDATE
			GPA_OfflineActivitySms
		SET
			Status = 1,
			ReplyTime = NOW(),
			UpdateTime = NOW()
		WHERE
			SmsID = #smsId#
	
		UPDATE
			GPA_OfflineActivitySms
		SET
			Status = #status#,
			UpdateTime = NOW()
		WHERE
			SmsID = #smsId#
	
		UPDATE
			GPA_OfflineActivitySms
		SET
			Status = 2
		WHERE
			PhoneNo=#phoneNo# AND ExpireTime <= NOW()
        
    
		UPDATE
			GPA_OfflineActivityTicket
		SET
			Status = 1,
			VerifyTime = NOW(),
			VerifyShopID=#shopId#
		WHERE
			TicketID = #ticketId#
	
		
			UPDATE AC_HighRiskAccount
			SET
				Status=#status#,
				UpdateTime=NOW()
			WHERE
				UserId=#userId#
		
	
    	UPDATE DP_UserResetPasswordLog
    	SET IsValid=0,
    		UpdateTime=NOW()
    	Where UserID=#userId#
    
		
			update
				CV_ContentType
			set
				RoomCount=#roomCount#
			where
				ID=#contentType#
		
	
		
			update
				CV_ContentVerify
			set
				ManVerifyResult=#status#,
				AdminID=#adminId#,
				ManVerifyTime=#manVerifyTime#
			where
				ID=#contentVerifyId# and ManVerifyResult<>4
		
	
		update
			CV_ContentVerify
		set
			TrainCount=#trainCount#
		where
			ID=#contentVerifyId#
	
		UPDATE
			GPA_ThirdPartyActivity
		SET
			Title = #thirdPartyActivity.title#,
			DetailLink = #thirdPartyActivity.detailLink#,
			Body = #thirdPartyActivity.body#,
			CityName = #thirdPartyActivity.cityName#,
			Venues = #thirdPartyActivity.venues#,
			DateInfo = #thirdPartyActivity.dateInfo#,
			RegionName = #thirdPartyActivity.regionName#,
			DetailAddress = #thirdPartyActivity.detailAddress#,
			BeginDate = #thirdPartyActivity.beginDate#,
			EndDate = #thirdPartyActivity.endDate#,
			CostMemo = #thirdPartyActivity.costMemo#,
			TypeName = #thirdPartyActivity.typeName#,
			Poster = #thirdPartyActivity.poster#,
			TicketNum = #thirdPartyActivity.ticketNum#,
			Actors = #thirdPartyActivity.actors#
		WHERE
			ID = #thirdPartyActivity.id# 
	
		UPDATE
			GPA_ThirdPartyActivity
		SET
			UpdateTime = NOW()
		WHERE
			ID = #id# 
	
		UPDATE
			GPA_WeixinCoupon
		SET
			CouponNum = #couponNum# - 1
		WHERE 
			CouponID=#couponId#
		AND
			CouponNum = #couponNum#
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				Token = #token#
		
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				InternalToken = #internalToken#
		
	
		UPDATE ZS_User
		SET MobileNOStatus=#mobileNoStatus#
		Where UserID=#userId#
    
		UPDATE
		ZS_User
		SET MobileNO=#mobileNo#,
		MobileNOStatus=#mobileNoStatus#
		Where
		UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserEmail=#email#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		EmailVerifyStatus=#emailVerifyStatus#
		Where UserID=#userId#
    
		UPDATE ZS_User
		SET
		UserNickName=#nickname#
		Where UserID=#userId#
    		
		UPDATE AC_LostUser 
		SET
			UpdateTime = now(), 
			STATUS = 1	
		WHERE
			UserID = #userId# and Status = 0
	
		
			UPDATE AC_HighRiskAccount
			SET
				Status=#status#,
				UpdateTime=NOW()
			WHERE
				UserId=#userId#
		
	
		
			UPDATE AC_MobileVerify
			SET
				IsValid = #isValid#,
				UpdateTime = NOW()
			WHERE 
				VerifyID = #verifyId#
		
	
    	UPDATE DP_UserResetPasswordLog
    	SET IsValid=0,
    		UpdateTime=NOW()
    	Where UserID=#userId#
    
	    UPDATE CI_GuessULikeShop
		SET ShopList = #shopList#
		WHERE UserID = #userID#
	
    	UPDATE DianPingMobile.CI_SceneryOrderDetail 
		SET Status = #status#
		WHERE SerialId = #serialId#
    
    	UPDATE DianPingMobile.CI_SceneryOrderDetail 
		SET Status = #status#, EnableCancel = #enableCancel#, TotalPrice = #totalPrice#
		WHERE SerialId = #serialId#
    
    	UPDATE DianPingMobile.CI_CheckInTopic 
		SET TotalCheckIn = TotalCheckIn + 1
		WHERE ID = #TopicID#
    
    	UPDATE DianPingMobile.CI_CheckInTopic 
		SET BeginDate = #BeginDate#
		WHERE ID = #ID# 
    
    	INSERT INTO MB_PassBook_Promo
 			   (serialNum,AuthToken,ptoken,devLibId,createTime,status,passType) 
 		VALUES (#serialNum#, #authToken#,#ptoken#,#devLibId#,#createTime#,1,#passType#) 
 		ON DUPLICATE KEY 
 			UPDATE serialNum = #serialNum#, AuthToken = #authToken#,ptoken=#ptoken#,devLibId=#devLibId#,createTime=#createTime#, status=1,passType=#passType#
    
    	UPDATE MB_PassBook_Promo SET status=0 
    	WHERE serialNum=#serialNum# and AuthToken = #authToken# and devLibId=#devLibId# and passType=#passType# and status=1
    
	
		UPDATE
			GPA_OfflineActivityShop
		SET
			Summary=#summary#
		WHERE
			OfflineActivityID=#offlineActivityId#
			AND ShopID=#shopId#
	
		UPDATE
			GPA_OfflineActivitySpecificOpRule
		SET
			RuleExpression=#rule.expression#,
			RuleDimension=#rule.dimension#,
			RuleOperator=#rule.operator#,
			RuleLimit=#rule.limit#,
			AddTime=#rule.addTime#,
			UpdateTime=#rule.updateTime#
		WHERE
			OfflineActivityID=#rule.offlineActivityId# AND OperateType=#rule.operateType#
	
		UPDATE
			GP_VIPClub
		SET
			Status = #status#,
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE
			GP_VIPClub
		SET
			Status = 2,
			year = YEAR(NOW()),
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE
			GP_VIPClub
		SET
			StartTime = #startTime#,
			EndTime = #endTime#
		WHERE
			ID = #id#
	
		UPDATE
			GP_VIPClub
		SET
			Remark = #remark#
		WHERE
			ID = #id#
	
        update   GP_VIPWeekStar set  Description = #weekStarEntity.description#
        where id = #weekStarEntity.id#
    		
		UPDATE AC_LostUser 
		SET
			UpdateTime = now(), 
			STATUS = 1	
		WHERE
			UserID = #userId# and Status = 0
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				Token = #token#
		
	
		
			UPDATE AC_MailVerify
			SET
				Status = #status#,
				UpdateTime = #updateTime#
			WHERE 
				InternalToken = #internalToken#
		
	
		UPDATE
			DP_WeiXinUserMapping 
		SET
			UpdateDate = now(),
			Token=#token#
		WHERE
			OpenID=#openId#
			AND
			channel=#channel#;
	
        UPDATE MC_MemberCardBossAccountInfo b
        SET b.CompanyManager = #bossData.companyManagerName#, b.CompanyManagerTel = #bossData.companyManagerTel#, b.CompanyManagerPosition = #bossData.companyManagerPosition#, b.CompanyManagerMail = #bossData.companyManagerMail#, b.BossAccountUpdateTime = #bossData.bossAccountUpdateTime#
        WHERE b.CrmID = #bossData.crmID#
    
        UPDATE MC_MemberCardBossAccountInfo b
        SET b.BossAccountCreateStatus=#bossAccountCreateStatus#, b.BossAccountCreateAuditInfo=#bossAccountCreateAuditInfo#, b.AuthAdminID=#authAdminId#, b.AuthAdminName=#authAdminName#
        WHERE b.CrmID = #crmID#
    
		
			UPDATE MC_MemberCardProduct p
			SET p.Status = #status#,p.AuditInfo = #auditInfo#
			WHERE p.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProduct p
			SET p.AuditInfo = #auditInfo#
			WHERE p.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProduct_Draft p
			SET p.AuditInfo = #auditInfo#
			WHERE p.ProductDraftID = #productDraftId#
			
	
			WHERE p.ProductID = #productId#
	
			WHERE p.ProductDraftID = #productDraftId#
	
		
			UPDATE MC_MemberCardProductDiscount d
			SET d.DiscountRate = #discount#
			WHERE d.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProductDiscount_Draft d
			SET d.DiscountRate = #discount#
			WHERE d.ProductDraftID = #productDraftId#
			
	
		
			UPDATE MC_MemberCardProduct p
			SET p.Status = #toStatus#
			WHERE p.MemberCardID = #memberCardId# AND p.Status = #fromStatus#
			
	
		
			UPDATE MC_MemberCardProductMallPromo d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#
			WHERE d.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProductMallPromo_Draft d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#
			WHERE d.ProductDraftID = #productDraftId#
			
	
		
			UPDATE MC_MemberCardProductMallShopPromo d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#,d.ShopType = #shopType#,d.Floor = #floor#,d.Building = #building#
			WHERE d.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProductMallShopPromo_Draft d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#,d.ShopType = #shopType#,d.Floor = #floor#,d.Building = #building#
			WHERE d.ProductDraftID = #productDraftId#
			
	
		
			UPDATE MC_MemberCardProductShop s
			SET s.ShopID = #shopID#,s.MemberCardID = #memberCardID#,s.Status = #status#
			WHERE s.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProduct_Draft p
			SET p.Status = #toStatus#,
				p.AuditInfo = #auditInfo#
        	WHERE p.ProductDraftID = #productDraftId#
			
	
        	WHERE f.FeedID = #feedId#
	
			update
				CV_ContentVerify
			set
				ManVerifyResult=#manVerifyResult#
			where
				BizID=#bizId# and ContentType=#contentType# and ReferID=#referId# and ManVerifyResult=0
	
		UPDATE
			GPA_OfflineActivity
		SET
			Hits=#hits#
		WHERE
			OfflineActivityID=#offlineActivityId#
	
		UPDATE
			GPA_OfflineActivity
		SET
			ApplyCount=#offlineActivityStat.applyCount#,
			UserCount=#offlineActivityStat.userCount#,
			NoteCount=#offlineActivityStat.noteCount#,
			FollowNoteCount=#offlineActivityStat.followNoteCount#
		WHERE
			OfflineActivityID=#offlineActivityStat.offlineActivityId#
	
        
        UPDATE MAT_UserRoleRelation SET
        status = #status#
       	WHERE userId = #userId#
		
    
        
        UPDATE MAT_UserRoleRelation SET
        userId = #userRole.userId#,
        roleId = #userRole.roleId#,
        status = #userRole.status#
       	WHERE id = #userRole.id#
		
    
        
        UPDATE MAT_Role SET
        description = #role.description#,
        type = #role.type#,
        status = #role.status#
       	WHERE roleId = #role.roleId#
		
    
        
	        UPDATE MAT_Role SET
	        status = #status#
	       	WHERE roleId = #roleId#
		
    
		UPDATE
			GPA_OfflineActivity
		SET
			Status=#status#
		WHERE
			OfflineActivityID=#offlineActivityId#
	
        ,SaleEmails=#saleEmails#
        ,ApplyExtendInfo1=#applyExtendInfo1#
        ,ApplyExtendInfo2=#applyExtendInfo2#
        ,ApplyExtendInfo3=#applyExtendInfo3#
        ,UpdateTime=now()
        WHERE
        OfflineActivityID=#offlineActivityId#
    
		UPDATE
		GPA_OfflineActivity
		SET
		IsListRelease=#releaseStatus#,
		ListReleaseTime = NOW()
		WHERE
		OfflineActivityID=#offlineActivityId#
	
		UPDATE
		GPA_OfflineActivity
		SET
		UserCount=UserCount+#increaseNum#
		WHERE
		OfflineActivityID=#offlineActivityId#
	
		UPDATE
		GPA_OfflineActivity
		SET
		ApplyCount=ApplyCount+#increaseNum#
		WHERE
		OfflineActivityID=#offlineActivityId#
	
		UPDATE
			GPA_OfflineActivity
		SET
			IsShowInGroup=#isShowInGroup#
		WHERE
			OfflineActivityID=#activityId#
	
		UPDATE
			GPA_OfflineActivity
		SET
			NoteCount=NoteCount+#increaseNum#
		WHERE
			OfflineActivityID=#offlineActivityId#
	
		UPDATE
			GPA_OfflineActivity
		SET
			FollowNoteCount=FollowNoteCount+#increaseNum#
		WHERE
			OfflineActivityID=#offlineActivityId#
	
		UPDATE
			GPA_OfflineActivity
		SET
			ReleaseTime = NOW()
		WHERE
			OfflineActivityID = #offlineActivityId#
	
		UPDATE
		GPA_OfflineActivityBranch
		SET
		branchName = #branch.branchName#,
		UpdateTime = NOW()
		WHERE
		OfflineActivityID = #branch.offlineActivityId#
		And branchId = #branch.branchId#
	
		

			UPDATE ECP_DEAL
			SET
				TITLE=#title#, 
				DESCRIPTION=#description#, 
				DEAL_PRICE=#price#, 
				MARKET_PRICE=#marketPrice#, 
				COST=#cost#,
				MAX_JOIN=#maxJoin#,
				BEGIN_DATE=#validBeginDate#,
				END_DATE=#validEndDate#,
				TRAFFIC_INFO=#trafficInfo#,
			    CONTACT_INFO=#contactInfo#,
			    MEMO=#memo#,
				LAST_UPDATE_TIME=#lastUpdateTime#
			WHERE DEAL_ID=#dealId#

        
	
		Update TG_Vendor SET Password = #password#
		Where  DealID IN ($dealIDs$) and Account = #account#   and Password COLLATE utf8_bin = #oldPassword#		
	
        UPDATE TGP_DEAL_GROUP_ADMISSION
        SET
            DEAL_GROUP_ID = #entity.dealGroupId# ,
            IS_ADMISSION_REQUIRED= #entity.isAdmissionRequired# ,
            AMOUNT=  #entity.amount# ,
            STATUS_ID=#entity.statusId#,
            CREATE_TIME = #entity.createTime# ,
            CREATOR_ID = #entity.creatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId#
          WHERE
            ADMISSION_ID = #entity.id#;
	
        

		DELETE FROM TGP_DEAL_GROUP_EDITOR WHERE DEAL_GROUP_EDITOR_ID=#entity.id#

        
    
    

		UPDATE TGP_DEAL_GROUP_EDITOR
		SET DEAL_GROUP_PRODUCE_STATUS = #completedStatus,handler=dealGroupProduceStatusEnumTypeHandler#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
    
        

        UPDATE TGP_DEAL_GROUP_EDITOR
        SET
            DEAL_GROUP_ID = #entity.dealGroupId# ,
            EDITOR_ID = #entity.editorId# ,
            EDITOR_NAME = #entity.editorName# ,
            DEAL_GROUP_PRODUCE_STATUS = #entity.dealGroupProduceStatus,handler=dealGroupProduceStatusEnumTypeHandler# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            VERSION_ID = #entity.versionId#
        WHERE
            DEAL_GROUP_EDITOR_ID = #entity.id# ;

        
    
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='ImageTextComponent'
	
        UPDATE TGP_MESSAGE_QUEUE
        SET
            COMMENT=#entity.comment#,
            TOPIC=#entity.topic#,
            CONTENT=#entity.content#,
            MAX_RETRY_TIMES=#entity.maxRetryTimes#,
            CURRENT_RETRY_TIMES=#entity.currentRetryTimes#,
            STATUS_ID=#entity.statusId#,
            CREATE_TIME=#entity.createTime#,
            LAST_EXECUTE_TIME=#entity.lastExecuteTime#,
            NEXT_EXECUTE_TIME=#entity.nextExecuteTime#
        WHERE
            MESSAGE_QUEUE_ID=#entity.id#;
	
    
		UPDATE TGP_FILE_ATTACHMENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		FILE_NAME=#entity.fileName#,
		RELATIVE_PATH=#entity.relativePath#
		WHERE
		FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='QualifiedAttachment'
	
        UPDATE TGP_RESOURCE_ROLE_AUTHORITY_CONFIG
            SET 
                LAST_UPDATE_TIME = #entity.lastUpdateTime#,
                LAST_UPDATOR_ID = #entity.lastUpdatorId#,
                VERSION_ID = #entity.versionId#,
                POWER_CODE = #entity.powerCode#,
                SOURCE_SYSTEM = #entity.sourceSystem#,
                ROLE_ID = #entity.roleId#
            WHERE
                RESOURCE_ROLE_AUTHORITY_CONFIG_ID = #entity.id# ;
	
        UPDATE TGP_SALES_TEAM
        SET
        TEAM_NAME = #entity.teamName# ,
        TEAM_CITY_ID = #entity.teamCityId# ,
        TEAM_CITY_NAME = #entity.teamCityName#,
        LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
        LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
        VERSION_ID = #entity.versionId# + 1
        WHERE
        SALES_TEAM_ID = #entity.id#
	
		UPDATE
		TGP_VISUAL_LIST_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VISUAL_COMPONENT_ID=#entity.visualComponent.id#,
		SEQ=#entity.sequence#,
		CONTENT=#entity.content#,
		IS_READ_ONLY=#entity.isReadOnly#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='TextItem'
	
		UPDATE TGP_COMPOSITABLE_STATEMENT_TEMPLATE_ASSN
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		COMPOSITABLE_TEMPLATE_ID=#entity.compositableTemplate.id#,
		STATEMENT_TEMPLATE_ID=#entity.statementTemplateId#
		WHERE
		COMPOSITABLE_STATEMENT_TEMPLATE_ASSN_ID=#entity.id#
	
		UPDATE TGP_TEMPLATE_ENTRY
		SET
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplate.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		IS_MANDATORY=#entity.isMandatory#,
		AREA_TYPE_ID=#entity.areaTypeId#,
		SEQUENCE=#entity.sequence#
		WHERE
		TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='CompositableTemplate'
	
		UPDATE TGP_DOCUMENT_TEMPLATE
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VERSION_ID=#entity.versionId#,
		NAME=#entity.name#,
		IS_ACTIVE=#entity.isActive#,
		IS_OFFLINE=#entity.isOffline#
		WHERE
		DOCUMENT_TEMPLATE_ID=#entity.id#
	
		UPDATE TGP_DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplate.id#,
		NAV_CATEGORY_ID=#entity.categoryId#
		WHERE
		DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN_ID=#entity.id#
	
		UPDATE TGP_STATEMENT
		SET
		DOCUMENT_BUILDER_ID=#entity.documentBuilder.id#,
		STATEMENT_TEMPLATE_ID=#entity.statementTemplateId#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#
		WHERE
		STATEMENT_ID=#entity.id#
	
		UPDATE
		TGP_CONFIGURABLE_BLOCK
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		TYPE_ID=#entity.typeId#,
		VISUAL_VIEW_ID=#entity.visualView.id#,
		TITLE=#entity.title#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		CONFIGURABLE_BLOCK_ID=#entity.id#
	 
			WHERE
				Id = #cscCaseOtherBusiness.id#
	
		UPDATE CSC_RefundRemind
		SET
			Status = #cscRefundRemind.status# , 
			DailyMobileNo = #cscRefundRemind.dailyMobileNo# ,
			DailySmsInfo = #cscRefundRemind.dailySmsInfo# ,
			Memo = #cscRefundRemind.memo#
		WHERE
			ID = #cscRefundRemind.id#
	
		UPDATE CSC_RefundRemind
		SET
			ContactName = #cscRefundRemind.contactName# , 
			Email = #cscRefundRemind.email# ,
			MobileNo = #cscRefundRemind.mobileNo# ,
			EmailInfo = #cscRefundRemind.emailInfo#
		WHERE
			ID = #cscRefundRemind.id#
	
		UPDATE CSC_RefundRemind
		SET
			Status = #cscRefundRemind.status# , 
			DailyMobileNo = #cscRefundRemind.dailyMobileNo# ,
			DailySmsInfo = #cscRefundRemind.dailySmsInfo# ,
			Memo = #cscRefundRemind.memo#
		WHERE
			ID = #cscRefundRemind.id#
	
        
        UPDATE FS_ShopFundAccountFlow
        SET ExchangeOrderID = #exchangeOrderId#,LastUpdateDate=NOW()
        WHERE FAFlowID = #fundAccountFlowId#
 		
    
    
    
        UPDATE  PC_BalanceRelation
        SET  FSPPID = #fsPPId#
        WHERE BPID = #bpId#
    
        UPDATE  PC_BillingPayable
        SET  Status = #bpStatus#, LastUpdateDate = NOW(),LastUpdateLoginID = #loginId#
        WHERE BPID = #bpId# AND Status <> #bpStatus# AND PlanDate <= NOW()
    
         AND PlanDate <= NOW()
    
        UPDATE  PC_BillingPayable
        SET  Status = #status#, LastUpdateDate = NOW(),LastUpdateLoginID = #loginId#
        WHERE BPID = (SELECT BPID FROM PC_BalanceRelation WHERE FSPPID = #fsPPId#)
        AND Status <> #status#
    
        UPDATE PC_ActivityVoucher
        SET APStatus = #status#
        WHERE VoucherID = #voucherId# AND APStatus <> #status#
    
        UPDATE PC_ActivityVoucher
        SET RevenueStatus = #status#
        WHERE VoucherID = #voucherId# AND RevenueStatus <> #status#
    
        UPDATE PC_ActivityVoucher
        SET InvoiceStatus = #status#
        WHERE VoucherID = #voucherId# AND InvoiceStatus <> #status#
    
        UPDATE TG_BillingReceivable
        SET    PaidAmount = #tgBpData.paidAmount#,
               status = #tgBpData.status#,
               lastUpdateDate = #tgBpData.lastUpdateDate#,
               lastUpdateLoginId = #tgBpData.lastUpdateLoginId#
        WHERE  BRID = #tgBpData.brId#;
    
    
    
        UPDATE TG_BillingReceivable
        SET    PaidAmount = #brData.paidAmount#,
               Status = #brData.status#
        WHERE  BRID = #brData.brId#;
    
        UPDATE TG_PurchaseDetail
        SET PAID = #paId#, Status = #status#, LastUpdateDate = NOW()
        WHERE PDID = #pdId# AND PAID = 0 AND Status <> #status#
    
        
            UPDATE `TG_ReceiptAccumulate`
            SET
            `DealGroupID` = #receiptAccumulateData.dealGroupId#,
            `UnusedCount` = `UnusedCount` + #receiptAccumulateData.unusedCount#,
            `UsedCount` = `UsedCount` + #receiptAccumulateData.usedCount#,
            `RefundingCount` = `RefundingCount` + #receiptAccumulateData.refundingCount#,
            `RefundedCount` = `RefundedCount` + #receiptAccumulateData.refundedCount#,
            `UnusedRevenue` = `UnusedRevenue` + #receiptAccumulateData.unusedRevenue#,
            `UsedRevenue` = `UsedRevenue` + #receiptAccumulateData.usedRevenue#,
            `RefundingRevenue` = `RefundingRevenue` + #receiptAccumulateData.refundingRevenue#,
            `UnusedCost` = `UnusedCost` + #receiptAccumulateData.unusedCost#,
            `UsedCost` = `UsedCost` + #receiptAccumulateData.usedCost#,
            `RefundingCost` = `RefundingCost` + #receiptAccumulateData.refundingCost#,
            `UpdateDate` = NOW()
            WHERE `DealID` = #receiptAccumulateData.dealId#
            AND `ShopID` = #receiptAccumulateData.shopId#
            AND `DayID` = #receiptAccumulateData.dayId#;
 		
    
        UPDATE MB_Notification
        SET
            `MicroblogId` = #notification.microblogId#,
            `InvolvedUserType` = #notification.involvedUserType#,
            `LoginId` = #notification.loginId#,
            `EventType` = #notification.eventType#,
            `Status` = #notification.status#,
            `ReadTime` = #notification.readTime#,
            `AddTime` = #notification.addTime#,
            `UpdateTime` = #notification.updateTime#
         WHERE `NotificationId`=#notification.notificationId#
    
        UPDATE TGP_EMAIL_INFO
        SET
            EMAIL_TITLE = #entity.emailTitle#,
            EMAIL_CONTENT= #entity.emailContent#,
            EMAIL_ADDRESS= #entity.emailAddress#,
            TYPE = #entity.type#,
            STATUS=  #entity.status#,
            LAST_UPDATE_TIME =#entity.lastUpdateTime#,
            LAST_UPDATOR_ID= #entity.lastUpdatorId#
          WHERE
            EMAIL_ID = #entity.id# ;
	
        WHERE
        UcId = #cscCaseCallingPhone.ucId#
    
        WHERE
        CaseId = #caseId#
    
        UPDATE CSC_CaseCallingPhone
        SET
        ScoreRoutePoint = #cscCaseCallingPhone.scoreRoutePoint#,
        Score = #cscCaseCallingPhone.score#
        WHERE
        UcId = #cscCaseCallingPhone.ucId#
    
        UPDATE TGP_PARTNER_DATA
        SET
            PARTNER_ID = #entity.partnerId# ,
            PARTNER_DEAL_GROUP_ID=   #entity.partnerDealGroupId# ,
            PARTNER_DATA=  #entity.partnerData# ,
            CREATE_TIME = #entity.createTime# ,
            CREATOR_ID = #entity.creatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            STATUS = #entity.status# ,
             DEAL_GROUP_ID =#entity.dealGroupId#
            WHERE
            PARTNER_DATA_ID = #entity.id# ;
	
        UPDATE RBAC_Limitation
        SET
            `BusinessId` = #limitation.businessId#,
            `BusinessType` = #limitation.businessType#,
            `RolePermissionId` = #limitation.rolePermissionId#,
            `AddTime` = #limitation.addTime#,
            `UpdateTime` = #limitation.updateTime#
            `Status` = #limitation.status#
         WHERE `Id`=#limitation.limitationId#
    
        UPDATE RBAC_Role
        SET
            `Name` = #role.name#,
            `Description` = #role.description#,
            `AddTime` = #role.addTime#,
            `UpdateTime` = #role.updateTime#
         WHERE `Id`=#role.roleId#
    
		
			UPDATE ECP_DEAL_GROUP
			SET
				PUBLISH_DEAL_GROUP_ID=#publishDealGroupId#, 
				PUBLISH_CITY_IDS=#publishCityIds#, 
				BEGIN_DATE=#beginDate#, 
				END_DATE=#endDate#, 
				EXCEPT_DATE=#exceptDate#,
				MAX_PER_USER=#maxPerUser#,
				MIN_PER_USER=#minPerUser#,
				HOTEL_TYPE=#hotelType#,
				IS_REFUNDABLE=#refundable#,
				TITLE=#title#,
				DESCRIPTION=#description#,
				SUMMARY=#summary#,
				DEAL_GROUP_INFO=#dealGroupInfo#,
				MOBILE_DEAL_GROUP_INFO=#mobileDealGroupInfo#,
				SERVICE_REGULATION=#serviceRegulation#,
				PRODUCT_INFO=#productInfo#,
				LAST_UPDATE_TIME=#lastUpdateTime#,
				PICTURES=#pictures#,
				SHOP_DESCRIPTION=#shopDescription#
			WHERE DEAL_GROUP_ID=#dealGroupId#
		
	
		
			UPDATE ECP_DEAL_GROUP
			SET
				STATUS = #status#,
				LAST_UPDATE_TIME = #lastUpdateTime#
			WHERE DEAL_GROUP_ID = #dealGroupId#
		
	
		

			UPDATE ECP_DEAL_GROUP_PROMOTION
            SET LAST_UPDATE_TIME = #lastUpdateTime#,
              STATUS = #status#
            WHERE DEAL_GROUP_ID = #dealGroupId#;

        
	
		UPDATE TG_BusinessCenterValidateCode
		SET UsedStatus = 1
		WHERE VerifyCodeID = #verifyCodeId#
	
		UPDATE TG_DealGroupDelayApply
		SET Status=#status#, UpdateTime = NOW()
		WHERE DealGroupDelayApplyID=#dealGroupDelayId#
	
		UPDATE TGP_STATEMENT_ATTRIBUTE_OPTION
		SET
		STATEMENT_ATTRIBUTE_ID=#entity.statementAttribute.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VALUE=#entity.value#
		WHERE
		STATEMENT_ATTRIBUTE_OPTION_ID=#entity.id#
	
		UPDATE TGP_STATEMENT_ATTRIBUTE_VALUE
		SET
		STATEMENT_ID=#entity.statement.id#,
		STATEMENT_ATTRIBUTE_ID=#entity.statementAttributeId#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VALUE=#entity.value#
		WHERE
		STATEMENT_ATTRIBUTE_VALUE_ID=#entity.id#
	
        UPDATE TGP_DEAL_GROUP
        SET IS_AUTO_DELAY = #isAutoDelay#,
            LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE DEAL_GROUP_ID = #dealGroupId#
    
		UPDATE
			TGP_DEAL_GROUP_MAINTAINER
		SET
			DEAL_GROUP_ID=#entity.dealGroupId#,
			MAINTAINER_ID=#entity.maintainerId#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			MAINTAINER_NAME=#entity.maintainerName#,
			TRAIN_STATUS=#entity.trainStatus#,
			RECEIVE_TIME=#entity.receiveTime#,
			CHANGE_STATUS_TIME=#entity.changeStatusTime#,
			VERSION_ID=#entity.versionId#,
			CONTACT_MP=#entity.contactMP#,
			CONTACT_EMAIL=#entity.contactEmail#,
			IS_SENDED=#entity.isSended#
		WHERE
			ID=#entity.id#
	
		UPDATE 
			TGP_DEAL_GROUP_MAINTAINER 
		SET 
			MAINTAINER_ID=#maintainerId#,
			MAINTAINER_NAME=#maintainerName#,
			RECEIVE_TIME=NOW()
		WHERE 
			DEAL_GROUP_ID=#dealGroupId#;
	
        
        UPDATE TGP_DEAL_GROUP_WORKFLOW
        SET
            WORKFLOW_STATUS = #workflowStatus,handler=workflowStatusEnumTypeHandler# ,
            LAST_UPDATE_TIME = #updateTime#
        WHERE
            PROCESS_INSTANCE_ID = #processInstanceId# ;
		
    
        
		UPDATE TGP_EDITOR SET
             WORKLOAD_ASSIGNED = WORKLOAD_ASSIGNED + 1,
             LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE  EDITOR_ID = #editorId#
		
	
        
		UPDATE TGP_EDITOR SET
		     WORKLOAD_TODAY = #workloadToday#,
             WORKLOAD_ASSIGNED = #workloadAssigned#,
             LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE  EDITOR_ID = #editorId#
		
	
        UPDATE TGP_EDITOR
        SET EDITOR_ID = #entity.editorId#,
          EDITOR_NAME = #entity.editorName#,
          EDITOR_TEAM_ID = #entity.editorTeamId#,
          WORKLOAD = #entity.workload#,
          WORKLOAD_TODAY = #entity.workloadToday#,
          WORKLOAD_ASSIGNED = #entity.workloadAssigned#,
          IS_ACTIVE = #entity.isActive#,
          LAST_UPDATOR_ID = #entity.lastUpdatorId#,
          LAST_UPDATE_TIME = #entity.lastUpdateTime#
        WHERE ID = #entity.id#;
    
        
		UPDATE TGP_EDITOR SET
             WORKLOAD_ASSIGNED = WORKLOAD_ASSIGNED - 1,
             LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE  EDITOR_ID = #editorId#
          AND  WORKLOAD_ASSIGNED > 0
		
	
		UPDATE TGP_FILE_ATTACHMENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		FILE_NAME=#entity.fileName#,
		RELATIVE_PATH=#entity.relativePath#
		WHERE
		FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='FileAttachment'
	 
	 
		DELETE FROM TGP_PRODUCT WHERE PRODUCT_ID=#entity.id#
	
	
        UPDATE TGP_RECEIPT_VERIFY_HISTORY
        SET
            DEAL_GROUP_ID=#entity.dealGroupId#,
            POI_SHOP_ID=#entity.poiShopId#,
            SUCCESS_SERIAL_NUMBER=#entity.successSerialNumber#,
            FAILURE_SERIAL_NUMBER=#entity.failureSerialNumber#,
            CREATE_TIME=#entity.createTime#,
            CREATOR_ID=#entity.creatorId#,
            LAST_UPDATE_TIME=#entity.lastUpdateTime#,
            LAST_UPDATOR_ID=#entity.lastUpdatorId#,
            MEMO=#entity.memo#,
            RECALL_STATUS=#entity.recallStatus#
          WHERE
            ID=#entity.id#;
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		RICH_TEXT_CONTENT=#entity.content#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='RichTextComponent'
	
		UPDATE TGP_SHOP_CITY_GROUP
		SET
		LAST_UPDATOR_ID = #entity.lastUpdatorId#,
		LAST_UPDATE_TIME = #entity.lastUpdateTime#,
		CITY_ID = #entity.cityId#,
		SEQUENCE = #entity.sequence#,
		VISUAL_VIEW_ID = #entity.visualView.id#
		WHERE
		SHOP_CITY_GROUP_ID=#entity.id#
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET

		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextListComponent'
	 
			WHERE
				Id = #cscCase.id#
	 
			WHERE
				Id = #cscDealGroupAnnounce.id#
	
        WHERE
        OnlineDate = #onlineDate#
     
			WHERE
				Id = #cscTGOrder.id#
	
			UPDATE CSC_BatchRefundTimer
			SET STATUS = #cscBatchRefundTimer.status# , 
			ResultMsg = #cscBatchRefundTimer.resultMsg#
			WHERE
				Id = #cscBatchRefundTimer.id#
	
            WHERE m.MemberCardID = #memberCardId#
    
            UPDATE MC_MemberCard m
            SET 
            m.Title = #title#,
            m.SubTitle = #subTitle#,
            m.LogoID = #shopType#,
            m.BgImageID = #cardColor#,
            m.FontColor = #fontColor#
            WHERE m.MemberCardID = #memberCardId#
    
        WHERE ProductID=#productID#
            AND MemberCardID=#memberCardID#
            AND Status IN (2,3,5,7)
    
        WHERE ProductDraftID=#productDraftID#
            AND MemberCardID=#memberCardID#
            AND Status IN (1,2,4)
    
        WHERE ProductDraftID=#productDraftID#
            AND MemberCardID=#memberCardID#
            AND Status IN (1,2,4)
    
        WHERE ProductDraftID=#productDraftID#
            AND MemberCardID=#memberCardID#
            AND Status IN (1,2,4,5)
    
        UPDATE MC_MemberCardProductDiscount
        SET DiscountRate=#discountRate#
        WHERE ProductID=#productID#
    
        UPDATE MC_MemberCardProductDiscount_Draft
        SET DiscountRate=#discountRate#
        WHERE ProductDraftID=#productDraftID#
    
    
    
        UPDATE MC_MemberCardProduct
        SET AuditInfo=#auditInfo#
        WHERE ProductID=#productId#
              AND MemberCardID=#memberCardId#
    
        UPDATE MC_MemberCardConsume
        SET CardNO=#cardNO#,
            ConsumePrice=#consumePrice#,
            ConsumeDate=#consumeDate#
        WHERE MemberCardConsumeID=#memberCardConsumeId#
    
        UPDATE MC_MemberCardProductMallPromo
        SET PromoTitle = #promoTitle#,
            PromoDesc = #promoDesc#,
            Tel = #tel#
        WHERE PromoID = #promoId#
    
        UPDATE MC_MemberCardProductMallPromo_Draft
        SET PromoTitle = #promoTitle#,
            PromoDesc = #promoDesc#,
            Tel = #tel#
        WHERE PromoDraftID = #promoDraftId#
    
        UPDATE MC_MemberCardProductMallShopPromo
        SET ShopType = #shopType#,
            Floor = #floor#,
            Building = #building#,
            PromoTitle = #promoTitle#,
            PromoDesc = #promoDesc#,
            Tel = #tel#
        WHERE PromoID = #promoId#
    
        UPDATE MC_MemberCardProductMallShopPromo_Draft
        SET ShopType = #shopType#,
            Floor = #floor#,
            Building = #building#,
            PromoTitle = #promoTitle#,
            PromoDesc = #promoDesc#,
            Tel = #tel#
        WHERE PromoDraftID = #promoDraftId#
    
        UPDATE MC_MemberCardProduct
        SET Status = 9
        WHERE ProductID = #productId#
            AND MemberCardID = #memberCardId#
            AND (Status < 7 OR Status > 7)
    
        UPDATE MC_MemberCardProduct
        SET Status = 6
        WHERE ProductID = #productId#
            AND MemberCardID = #memberCardId#
            
            AND (Status < 4 OR Status > 4)
            

    
        UPDATE MC_MemberCardProduct
        SET Status = 1,
            AddTime = #addTime#
        WHERE MemberCardID = #memberCardId#
            AND Status = 7
    
        UPDATE MC_MemberCardProduct_Draft
        SET Status = 2,
            AddTime = #addTime#
        WHERE MemberCardID = #memberCardId#
            AND Status = 1
    
        UPDATE MC_MemberCardProduct
        SET Status = 1,
            AddTime = #addTime#
        WHERE MemberCardID = #memberCardId#
            AND ProductID = #productId#
            AND Status = 7
    
        UPDATE MC_MemberCardProduct_Draft
        SET Status = 2,
            AddTime = #addTime#
        WHERE MemberCardID = #memberCardId#
            AND ProductDraftID = #productDraftId#
            AND Status = 1
    
        UPDATE MC_MemberCardFeed
        SET Category = #feedCategory.feedCategory#,
            Title = #title#,
            PicPath = #picPath#,
            PicType = #picType#,
            Content = #content#,
            BeginDate = #beginDate#,
            EndDate = #endDate#,
            Status = #feedStatus.feedStatus#,
            ShopAccountID = #shopAccountId#,
            UpdateTime = #updateTime#
        WHERE FeedID = #feedID# 
    
        UPDATE MC_MemberCardFeed
        SET Status = 5,
            UpdateTime = NOW()
        WHERE FeedID = #feedID# 
    
            UPDATE DianPingMC.MC_MemberCardScoreLog 
                SET
                STATUS = #status#, 
                UpdateTime = NOW()
                WHERE
                LogID = #logID# ;
    
    
    
		
			UPDATE UC_UserBadge
			SET CurrentCount = Amount,
			  STATUS = 1,
			  COMMENT = #comment#,
			  GotDate = #now#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount - #decrement#,
			  STATUS = 0,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 1
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount + #increment#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount + #increment#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = #resetTo#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
        UPDATE TG_RefundDetail
        SET RAID = #raId#, Status = #status#, LastUpdateDate = NOW()
        WHERE RDID = #rdId# AND RAID = 0 AND Status <> #status#
    
        
            UPDATE `TG_DealDetail`
            SET
            `DealGroupID` = #dealData.dealGroupId#,
            `ContractGlobalId` = #dealData.contractGlobalId#,
            `CurrentJoin` = #dealData.currentJoin#,
            `Price` = #dealData.price#,
            `Cost` = #dealData.cost#,
            `ReceiptType` = #dealData.receiptType#,
            `DeliverType` = #dealData.deliverType#,
            `DealBeginDate` = #dealData.dealBeginDate#,
            `DealEndDate` = #dealData.dealEndDate#,
            `ReceiptBeginDate` = #dealData.receiptBeginDate#,
            `ReceiptEndDate` = #dealData.receiptEndDate#,
            `DealShortTitle` = #dealData.dealShortTitle#,
            `ContractID` = #dealData.contractId#
            WHERE DealID = #dealId#;
 		
    
    
		UPDATE CSC_RefundRecord
		SET
			Status = #cscRefundRecord.status# , 
			ResultMessage = #cscRefundRecord.resultMessage#
		WHERE
			Id = #cscRefundRecord.id#
	

        WHERE
        Id = #cscRefundRecord.id#
    
		UPDATE CSC_RefundRecord
		SET
			ApproveStatus = #cscRefundRecord.approveStatus#
		WHERE
			Id = #cscRefundRecord.id#
	
		UPDATE CSC_RefundRecord
		SET
			ApproveStatus = #cscRefundRecord.approveStatus#,
			ProcInstID = #cscRefundRecord.procInstID#
		WHERE
			Id = #cscRefundRecord.id#
	
		UPDATE CSC_RefundRecord
		SET
			HasDeducted = #cscRefundRecord.hasDeducted#,
			DeductionReason =#cscRefundRecord.deductionReason#
		WHERE
			Id = #cscRefundRecord.id#
	
		UPDATE CSC_RefundRecord
		SET
			Status = #cscRefundRecord.status# , 
			ResultMessage = #cscRefundRecord.resultMessage# ,
			PctOrderRefundId = #cscRefundRecord.pctOrderRefundId#
		WHERE
			OrderId = #cscRefundRecord.orderId#
	
        UPDATE RBAC_Permission
        SET
            `Name` = #permission.name#,
            `Description` = #permission.description#,
            `SystemName` = #permission.systemName#,
            `AddTime` = #permission.addTime#,
            `UpdateTime` = #permission.updateTime#
         WHERE `Id`=#permission.permissionId#
    
        UPDATE ECP_DEAL_GROUP_SHOP
        SET SHOP_ID = #shopId#
        WHERE ID = #id#;
    
		
			UPDATE DianPingECP.PartnerProductInUse SET
				ContractGlobalId=#ContractGlobalId#, 
				ProductId=#ProductId#, 
				SalesLoginId=#SalesLoginId#, 
				ProcessStatus=#ProcessStatus#, 
				AddDate=#AddDate#, 
				PartnerData=#PartnerData#, 
				UpdateDate=#UpdateDate#
			WHERE CustomerGlobalId=#CustomerGlobalId# 
			  AND PartnerProductId=#PartnerProductId#
		
	
		UPDATE 
        TG_Deliver,TGHT_DeliverExpressBatch,TGHT_DeliverExpressBatchDetail
        SET
        TG_Deliver.ExpressCheckStatus = 1
        ,TG_Deliver.ImportTime = NOW(),
        TG_Deliver.DeliverCompanyID =  TGHT_DeliverExpressBatch.CompanyID,
        TG_Deliver.DeliverSerialNO =  TGHT_DeliverExpressBatchDetail.ExpressNO	   
        WHERE 
        TGHT_DeliverExpressBatchDetail.BatchID = #batchID#
        AND 
        TG_Deliver.OrderID = TGHT_DeliverExpressBatchDetail.OrderID
        AND TGHT_DeliverExpressBatch.BatchID = TGHT_DeliverExpressBatchDetail.BatchID
        AND TG_Deliver.Status= 1
        AND TG_Deliver.ExpressCheckStatus in (0,1,3)
				
				
				
	
		Update TG_ValidPhoneList SET ShopAccountID = #ShopAccountID#,DealID=#DealID#
		Where  PhoneId = #PhoneID#
	
		Update TG_VerifyShopAccount SET Password = #password#
		Where  ShopAccountID = #shopAccountID#
	
		UPDATE TGP_STATEMENT_ATTRIBUTE
		SET
		STATEMENT_TEMPLATE_ID=#entity.statementTemplate.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		SEQUENCE=#entity.sequence#,
		FRONT_END_INPUT_TYPE=#entity.frontEndInputType#,
		LABEL_CONTENT=#entity.labelContent#,
		LENGTH=#entity.length#
		WHERE
		STATEMENT_ATTRIBUTE_ID=#entity.id#
	 
	 
		DELETE FROM TGP_CORP_INFO WHERE CORP_INFO_ID=#entity.id#
	
	
		UPDATE
		TGP_DEAL_GROUP_CITY_ASSN
		SET
		
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		CITY_ID=#entity.cityId#,
		DEAL_GROUP_ID=#entity.dealGroup.id#
		WHERE
		DEAL_GROUP_CITY_ASSN_ID=#entity.id#
	
		UPDATE
		TGP_DEAL_GROUP_PRODUCE_VERSION
		SET
		DEAL_GROUP_ID=#entity.dealGroupId#,
		DESC_INFO=#entity.descInfo#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		IP_ADDRESS=#entity.ipAddress#,
		SERVER_IP=#entity.serverIp#,
		DATA=#entity.data#
		WHERE
		DEAL_GROUP_PRODUCE_VERSION_ID=#entity.id#
	
		UPDATE TGP_SALES_TEAM_AE_ASSN
        SET
        SALES_TEAM_ID = #entity.salesTeam.id# ,
        AE_ID = #entity.aeId# ,
        AE_NAME = #entity.aeName# ,
        LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
        LAST_UPDATE_TIME = #entity.lastUpdateTime#
        WHERE
        SALES_TEAM_AE_ASSN_ID = #entity.id# ;
	
		UPDATE
		TGP_SERIAL_NUM_OPERATION_LOG
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VERSION_ID=#entity.versionId#+1,
		DEAL_ID=#entity.dealId#,
		TOTAL_COUNT=#entity.totalCount#,
		DUPLICATED_COUNT=#entity.duplicatedCount#,
		IMPORTED_COUNT=#entity.importedCount#,
		BATCH_NAME = #entity.batchName#,
        IS_REMOVED = #entity.isRemoved#,
        DUPLICATED_SERIAL_NUMBERS = #entity.duplicatedSerialNumbers#
		WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberImportLog'
	
		UPDATE TGP_SHOP_INFO
		SET
		LAST_UPDATOR_ID = #entity.lastUpdatorId#,
		LAST_UPDATE_TIME = #entity.lastUpdateTime#,
		ADDRESS = #entity.address#,
		BUSINESS_HOURS = #entity.businessHours#,
		CONTACT_PHONE = #entity.contactPhone#,
		POI_SHOP_ID = #entity.poiShopId#,
		SEQUENCE = #entity.sequence#,
		IS_AVG_PRICE_DISPLAYED = #entity.isAvgPriceDisplayed#,
		IS_BUSINESS_HOURS_DISPLAYED = #entity.isBusinessHoursDisplayed#,
		IS_CONTACT_PHONE_DISPLAYED = #entity.contactPhoneDisplayed#,
		IS_MAP_LINK_DISPLAYED = #entity.isMapLinkDisplayed#,
		IS_STAR_RATE_DISPLAYED = #entity.isStarRateDisplayed#,
		SHOP_CITY_GROUP_ID = #entity.shopCityGroup.id#,
		IS_VOTE_QUANTITY_DISPLAYED = #entity.isVoteQuantityDisplayed#
		WHERE
		SHOP_INFO_ID=#entity.id#
	
		UPDATE
		TGP_SLIDE_PICTURE
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		URL=#entity.url#,
		VISUAL_VIEW_ID=#entity.visualView.id#
		WHERE
		SLIDE_PICTURE_ID=#entity.id#
	
	  UPDATE TGP_SPECIAL_REMINDER_TEMPLATE_MAP 
	   SET
		TEMPLATE_URL= #entity.templateUrl#,
		LAST_UPDATE_TIME= #entity.lastUpdateTime#,
		LAST_UPDATOR_ID= #entity.lastUpdatorId#,
		TEMPLATE_NAME= #entity.templateName#
	WHERE 
	    NAV_CATEGORY_ID=#entity.categoryId#
	
			WHERE
				Id = #cscGroup.id#
	
                    
	
	
    
    
		
			UPDATE UC_UserBadge
			SET CurrentCount = Amount,
			  STATUS = 1,
			  COMMENT = #comment#,
			  GotDate = #now#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount - #decrement#,
			  STATUS = 0,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 1
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount + #increment#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = #count#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount + #increment#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = #resetTo#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
        AND FAFlowID = 0
        AND RelationType = #relationType#
    
        UPDATE FS_BalanceRelation
        SET BalanceAmount = #balanceAmount#
        WHERE PPID =#ppId#
        AND FAFlowID = #flowId#
    
    
        UPDATE TG_AccountPayable
        SET BPID = #bpId#, Status = #status#, LastUpdateDate = NOW()
        WHERE APID = #apId# AND BPID = 0 AND Status <> #status#
    
       UPDATE TG_BillingPayable
       SET PaidAmount = #tgBillingPayableData.paidAmount#,Status = #tgBillingPayableData.status#,
        LastUpdateDate = #tgBillingPayableData.lastUpdateDate#,LastUpdateLoginID = #tgBillingPayableData.lastUpdateLoginId#
       WHERE BPID = #tgBillingPayableData.bpId#
    
    
        UPDATE TG_BillingPayable
        SET    PaidAmount = #bpData.paidAmount#,
               Status = #bpData.status#
        WHERE  BPID = #bpData.bpId#;
    
    
        UPDATE TG_ActivityVoucher
        SET APStatus = #status#
        WHERE VoucherID = #voucherId# AND APStatus <> #status#
    
    
        UPDATE FC_PayFLow
        SET    Status = #status#,
               UpdateTime = #updateTime#
        WHERE  PaySequence = #paySequence#
    
        
        	UPDATE JS_ReceiveOrder
			SET ReceiveAmount = ReceiveAmount + #reFundAccount#,
			AddDate = NOW()
        WHERE ReceiveOrderID = #receiveOrderId#
 		
    
		UPDATE TGP_DEAL
		SET
		DEAL_GROUP_ID=#entity.dealGroup.id#,
		SHORT_TITLE=#entity.shortTitle#,
		ORIGINAL_PRICE=#entity.originalPrice#,
		RETAIL_PRICE=#entity.retailPrice#,
		COST_PRICE=#entity.costPrice#,
		MAX_STOCK_QTY=#entity.maxStockQty#,
		MAX_SALE_QTY=#entity.maxSaleQty#,
		MIN_SALE_QTY=#entity.minSaleQty#,
		SEQ=#entity.sequence#,
		IS_DEFAULT=#entity.isDefault#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		PRODUCT_ID_IN_IMSS=#entity.productIdInImss#,
		DUPLICATED_SERIALNUMBER_COUNT_IN_LASTBATCH=#entity.duplicatedSerialNumberCountInLastBatch#,
		TOTAL_IMPORTED_SERIALNUMBER_COUNT=#entity.totalImportedSerialNumberCount#,
		RECEIPT_CONTACT_INFO=#entity.receiptContactInfo#,
        BANK_ACCOUNT_GLOBAL_ID=#entity.bankAccountGlobalId#,
        IS_ACTIVE=#entity.isActive#,
        DISPLAY_TYPE=#entity.displayType#,
        DEAL_TYPE=#entity.dealType#,
        SEC_KILL_SALE_RULE=#entity.secKillSaleRule#
		WHERE
		DEAL_ID=#entity.id#
	
	    UPDATE TGP_DEAL D
		LEFT JOIN TGP_DEAL_GROUP DG ON DG.DEAL_GROUP_ID=D.DEAL_GROUP_ID
		LEFT JOIN TGP_CONTRACT C ON C.CONTRACT_ID=DG.CONTRACT_ID
		SET D.BANK_ACCOUNT_GLOBAL_ID=#bankAccountGlobalId#
		WHERE C.CONTRACT_CRM_ID=#contractCrmId#;
        
        
    
	

		UPDATE TGP_DEAL_GROUP
		SET 
			BUSINESS_TYPE_ID=#entity.businessTypeId#,
			DAYS_AFTER_INEFFECTIVE=#entity.daysAfterIneffective#,
			DAYS_AFTER_ONLINE=#entity.daysAfterOnline#,
			DAYS_BEFORE_EFFECTIVE=#entity.daysBeforeEffective#,
			EFFECTIVE_END_DATE=#entity.effectiveEndDate#,
			EFFECTIVE_END_TYPE=#entity.effectiveEndType#,
			IS_DELIVERY_REQUIRED=#entity.isDeliveryRequired#,
			IS_REFUNDABLE=#entity.refundableStatus#,
			IS_VOUCHER_AVAILABLE=#entity.isVoucherAvailable#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			PUBLISH_FROM_DATE=#entity.publishFromDate#,
			PUBLISH_TO_DATE=#entity.publishToDate#,
			DISTRIBUTION_PARTY_ID=#entity.distributionPartyId#,
			IS_TAX_INVOICE_AVARIABLE=#entity.isTaxInvoiceAvailable#,
			VERSION_ID=#entity.versionId#+1,
			CONTRACT_ID=#entity.contractId#,
			IS_RELEASED_TO_ALL_CITIES=#entity.isReleasedToAllCities#,
			IS_VOUCHER_SETTED=#entity.isVoucherSetted#,
			VOUCHER_FORMAT_ID=#entity.voucherFormatId#,
		    VERIFICATION_DEVICE_ID=#entity.verificationDeviceId#,
		    REMARKS=#entity.remarks#,
		    OTHER_EXCEPT_DATE = #entity.otherExceptDate#,
		    MAX_SALE_QTY=#entity.maxSaleQty#,
		    MIN_SALE_QTY=#entity.minSaleQty#,
		    COMPANY_ID_IN_IMSS=#entity.companyIdInImss#,
		    THIRD_PARTY_VERIFY_PROVIDER_ID=#entity.thirdPartyVerifyProviderId#,
		    IS_MANUAL_SET_REFUND= #entity.isManualSetRefund#,
			IS_HIDEN=#entity.isHiden#,
			PUBLISH_STATUS_ID=#entity.publishStatusId#,
			CUSTOMER_SERVICE_QQ=#entity.customerServiceQQ#,
			EFFECTIVE_BEGIN_DATE=#entity.effectiveBeginDate#,
			EFFECTIVE_BEGIN_TYPE=#entity.effectiveBeginType# ,
			BRIEF_DESC_DETAIL=#entity.briefDescription.detailedInformation#,
            BRIEF_DESC_SPECIAL_REMINDER=#entity.briefDescription.specialReminder#,
            BRIEF_DESC_PRD_INTRDCTN=#entity.briefDescription.productIntroduction#,
            BRIEF_DESC_SHOP_INFO=#entity.briefDescription.shopInformation#,
            BRIEF_DESC_SHOP_INTRDCTN=#entity.briefDescription.shopIntroduction#,
            BRIEF_DESC_COMMENTS=#entity.briefDescription.comments#,
            IS_EDITOR_REQUIRED=#entity.isEditorRequired#,
            IS_VALID=#entity.isValid#,
            PARENT_DEAL_GROUP_ID=#entity.parentDealGroupId#,
            DAYS_AFTER_ONLINE_OF_PUBLISH_TO_DATE=#entity.daysAfterOnlineOfPublishToDate#,
            PUBLISH_TO_TYPE=#entity.publishToType#,
            BRIEF_DESC_PROCESS_FOR_USE=#entity.briefDescription.processForUse#,
            IS_AUTO_DELAY=#entity.isAutoDelay#,
            REFUND_REASON=#entity.refundReason#
		WHERE
			DEAL_GROUP_ID=#entity.id#

        
	
	

		DELETE FROM TGP_DEAL_GROUP WHERE DEAL_GROUP_ID=#entity.id#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET EDITOR_ID = #editorId#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET IS_AE_ASSIGNED = #isAeAssigned#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET MAINTAINER_ID = #maintainerId#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET STATUS_ID = #statusId#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET IS_SUBMIT_EDITOR = #isSubmitEditor#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET SUBMIT_FOR_MERCHANT_CONFIRM_DATE = #submitForMerchantConfirmDate#, LAST_UPDATE_TIME = NOW()
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET APPROVE_BY_MERCHANT_CONFIRM_DATE = #approveByMerchantConfirmDate#, LAST_UPDATE_TIME = NOW()
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET IS_HIGH_PROCESS_LEVEL = #isHighProcessLevel#, IS_HIGH_LEVEL_APPLIED_FOR = #isHighLevelAppliedFor#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP SET
		  EDITOR_ID = #editorId#,
		  IS_EDITOR_REQUIRED = #isEditorRequired#,
		  LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
	  
        UPDATE TGP_DEAL_GROUP DG
        INNER JOIN TGP_DEAL_GROUP_VERSION V ON V.DEAL_GROUP_ID=DG.DEAL_GROUP_ID
        SET DG.PUBLISH_STATUS_ID=3
        WHERE V.PUBLISH_TO_DATE<CURDATE() AND DG.PUBLISH_STATUS_ID=2;
        
	
	  

		UPDATE TGP_DEAL_GROUP SET IS_VALID = #isValid# WHERE DEAL_GROUP_ID = #dealGroupId#;

        
	
	  

		UPDATE TGP_DEAL_GROUP SET PUBLISH_STATUS_ID=#publishStatusId# WHERE DEAL_GROUP_ID = #dealGroupId#;

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET PUBLISH_FROM_DATE=#publishFromDate#
		WHERE DEAL_GROUP_ID=#dealGroupId#

        
	
		
			UPDATE ECP_PARTNER_DATA
			SET
                PARTNER_DEAL_GROUP_ID=#partnerDealGroupId#,
                PARTNER_DATA=#partnerData#,
                STATUS=#status#,
                LAST_UPDATE_TIME=#lastUpdateTime#,
                PARTNER_ID=#partnerId#,
                EXCEPTION_MESSAGE=#exceptionMessage#
			WHERE PARTNER_DATA_ID=#partnerDataId#
		
	
		
			UPDATE ECP_PARTNER_DATA
			SET
				STATUS = #status#,
				LAST_UPDATE_TIME = #lastUpdateTime#
			WHERE PARTNER_DATA_ID = #partnerDataId#
		
	
		
			UPDATE ECP_PARTNER_DATA 
			SET
				STATUS=#status#,
				PARTNER_DATA=#partnerData#,
				LAST_UPDATE_TIME=#lastUpdateTime#
			WHERE PARTNER_DATA_ID=#partnerDataId#
		
	
        UPDATE TGP_DEAL_GROUP_PROMOTION
        SET
        DEAL_GROUP_PROMOTION_ID=#entity.id#,
        CREATE_TIME=#entity.createTime#,
        CREATOR_ID=#entity.creatorId#,
        LAST_UPDATE_TIME=#entity.lastUpdateTime#,
        LAST_UPDATOR_ID=#entity.lastUpdatorId#,
        DEAL_GROUP_ID=#entity.dealGroupId#,
        TYPE=#entity.type#,
        PARAMETER=#entity.parameter#,
        BEGIN_DATE=#entity.beginDate#,
        END_DATE=#entity.endDate#,
        STATUS=#entity.status#
            WHERE
            DEAL_GROUP_PROMOTION_ID = #entity.id# ;
  
		UPDATE TG_BillPassword
		SET BillPassword = #billPassword#
		WHERE ShopAccountID = #shopAccountId#
	
		UPDATE TG_BillPassword
		SET Status = #status#
		WHERE ShopAccountID = #shopAccountId#
	
	

		UPDATE TGP_DEAL_GROUP_WORKFLOW_HISTORY
		SET
			DEAL_GROUP_ID=#entity.dealGroupId#,
			PROCESS_INSTANCE_ID=#entity.processInstanceId#,
			OPERATION_ID=#entity.operationId#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		WHERE
			DEAL_GROUP_ID=#entity.id#

        
	
		UPDATE
			TGP_DEAL_HOTEL
		SET
			DEAL_ID=#entity.dealId#,
			ROOM_TYPE=#entity.roomType#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#
		WHERE
			ID=#entity.id#
	
        UPDATE TGP_EDITOR_TEAM
        SET TEAM_NAME = #entity.teamName#,
          TEAM_LEADER_ID = #entity.teamLeaderId#,
          TEAM_LEADER_NAME = #entity.teamLeaderName#,
          DEAL_GROUP_PRODUCE_TYPE = #entity.dealGroupProduceTypeList,handler=dealGroupProduceTypeEnumsTypeHandler#,
          DEAL_GROUP_CATEGORY_ID = #entity.dealGroupCategoryId#,
          IS_ECOMMERCE = #entity.isEcommerce#,,
          LAST_UPDATOR_ID = #entity.lastUpdatorId#,
          LAST_UPDATE_TIME = #entity.lastUpdateTime#
        where EDITOR_TEAM_ID = #entity.id#;
    
		UPDATE TGP_EXCEPT_DATE
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		BEGIN_DATE = #entity.beginDate#,
		END_DATE = #entity.endDate#,
		DEAL_GROUP_ID=#entity.dealGroup.id#
		WHERE
		EXCEPT_DATE_ID=#entity.id#
	
		UPDATE
		TGP_VISUAL_LIST_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VISUAL_COMPONENT_ID=#entity.visualComponent.id#,
		SEQ=#entity.sequence#,
		PICTURE_URL=#entity.pictureUrl#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ImageTextItem'
	
        UPDATE TGP_ACCOUNT
        SET
            ACCOUNT_GLOBAL_ID = #entity.accountGlobalId# ,
            HAS_DEAL_GROUP_APPROVED=   #entity.hasDealGroupApproved# ,
            HAS_CRM_DEAL_GROUP_APPROVED=  #entity.hasCRMDealGroupApproved# ,
            CREATE_TIME = #entity.createTime# ,
            CREATOR_ID = #entity.creatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            VERSION_ID = #entity.versionId#
          WHERE
            ACCOUNT_ID = #entity.id# ;
	
		UPDATE
			TGP_DEAL_GROUP_AE
		SET
			DEAL_GROUP_ID=#entity.dealGroupId#,
			AE_ID=#entity.aeId#,
			AE_NAME=#entity.aeName#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			VERSION_ID=#entity.versionId#,
			AE_PHONE=#entity.aePhone#,
			AE_EMAIL=#entity.aeEmail#
		WHERE
			ID=#entity.id#
	 
	 
		UPDATE TGP_DEAL_GROUP_NAV_CATEGORY_ASSN
		SET 
			DEAL_GROUP_ID=#entity.dealGroup.id#, 
			NAV_CATEGORY_ID=#entity.categoryId#, 
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			IS_DEFAULT=#entity.isDefault#
		WHERE
			DEAL_GROUP_NAV_CATEGORY_ASSN_ID=#entity.id#
	
	
		UPDATE
		TGP_DEAL_GROUP_VERSION
		SET
		DEAL_GROUP_ID=#entity.dealGroupId#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		PUBLISH_FROM_DATE=#entity.publishFromDate#,
		PUBLISH_TO_DATE=#entity.publishToDate#,
		EFFECTIVE_BEGIN_DATE=#entity.effectiveBeginDate#,
		EFFECTIVE_END_DATE=#entity.effectiveEndDate#,
		DEAL_GROUP_CONTENT=#entity.dealGroupContent#,
		DOCUMENT_BUILDER_CONTENT=#entity.documentBuilderContent#,
		VISUAL_VIEW_CONTENT=#entity.visualViewContent#
		WHERE
		DEAL_GROUP_VERSION_ID=#entity.id#
	
            WHERE
            Id = #cscAdminLogin.id#
    
			WHERE
				FeedBackId = #cscWeiXinComplainRecord.feedBackId#
	
        
            UPDATE `TG_ReceiptDetail`
            SET
            `DealID` = #receiptDetail.dealId#,
            `ShopID` = #receiptDetail.shopId#,
            `DealGroupID` = #receiptDetail.dealGroupId#,
            `Status` = #receiptDetail.status#,
            `FinanceStatus` = #receiptDetail.financeStatus#,
            `Price` = #receiptDetail.price#,
            `Cost` = #receiptDetail.cost#,
            `APID` = #receiptDetail.apId#,
            `AddDate` = #receiptDetail.addDate#,
            `UpdateDate` = #receiptDetail.updateDate#,
            `FinanceUpdateDate` = NOW()
            WHERE `ReceiptID` = #receiptDetail.receiptId#;
 		
    
        UPDATE MB_TempLogin
        SET
            `LoginId` = #tempLogin.loginId#,
            `TypoCount` = #tempLogin.typoCount#,
            `LastTypoTime` = #tempLogin.lastTypoTime#,
            `TempLoginTime` = #tempLogin.tempLoginTime#,
            `AddTime` = #tempLogin.addTime#,
            `UpdateTime` = #tempLogin.updateTime#
         WHERE `TempLoginId`=#tempLogin.tempLoginId#
    
        UPDATE FC_PayRequest
        SET    status = #status#,
               memo = #memo#
        WHERE  requestId = #requestId#;
    
        UPDATE TGP_DEAL_GROUP_THIRD_PARTNER_EXTEND
        SET
            DEAL_GROUP_ID = #entity.dealGroupId# ,
            IS_REAL_NAME_REGISTRATION_NEEDED= #entity.isRealNameRegistrationNeeded# ,
            NEED_CERTIFICATE=  #entity.needCertificate# ,
            CREATE_TIME = #entity.createTime# ,
            CREATOR_ID = #entity.creatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId#
            WHERE
            DEAL_GROUP_THIRD_PARTY_ID = #entity.id# ;
	
        UPDATE RBAC_RolePermission
        SET
        `RoleId` = #rolePermission.roleId#,
        `PermissionId`= #rolePermission.permissionId#,
        `DueDate`= #rolePermission.dueDate#,
        `Status`= #rolePermission.status#,
        `AddTime`= #rolePermission.addTime#,
        `UpdateTime`= #rolePermission.updateTime#
         WHERE `Id` = #rolePermission.rolePermissionId#
    
        UPDATE RBAC_UserRole
        SET
        `RoleId` = #userRole.roleId#,
        `UserId`= #userRole.loginId#,
        `DueDate`= #userRole.dueDate#,
        `Status`= #userRole.status#,
        `AddTime`= #userRole.addTime#,
        `UpdateTime`= #userRole.updateTime#
         WHERE `Id` = #userRole.userRoleId#
    
        WHERE
        Id = #cscHotelOrder.id#;
    
		UPDATE TG_BookShopType
		SET BookType = #shopType#
		WHERE ShopAccountID = #shopAccountId#
	
		UPDATE TG_BookShopDate
		SET BookStatus = #bookStatus#,
		LastTime = NOW()
		WHERE BookDate = #bookDate# AND ShopAccountID = #shopAccountId#
	
		UPDATE TGP_DEAL_SHOP_ASSN
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		DEAL_ID=#entity.dealItem.id#,
		POI_SHOP_ID=#entity.poiShopId#,
		SHOP_ID_TYPE= #entity.shopIdType#
		WHERE
		DEAL_SHOP_ASSN_ID=#entity.id#
	
		UPDATE TGP_DESTINATION
		SET
		DEAL_GROUP_ID = #entity.dealGroup.id# ,
		CITY_ID = #entity.cityId# ,
		DISTRICT_ID = #entity.districtId# ,
		LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
		LAST_UPDATOR_ID = #entity.lastUpdatorId#
		WHERE DESTINATION_ID=#entity.id#
	
		UPDATE
		TGP_IMAGE_TEXT_DESC_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		IMAGE_TEXT_ITEM_ID=#entity.imageTextItem.id#,
		SEQ=#entity.sequence#,
		CONTENT=#entity.content#,
		IS_TITLE=#entity.isTitle#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		IMAGE_TEXT_DESC_ITEM_ID=#entity.id#
	
		UPDATE
		TGP_VISUAL_LIST_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VISUAL_COMPONENT_ID=#entity.visualComponent.id#,
		SEQ=#entity.sequence#,
		CONTENT=#entity.content#,
		QUANTITY=#entity.quantity#,
		SPECIFICATION=#entity.specification#,
		UNIT=#entity.unit#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ProductItem'
	
        UPDATE TGP_RESOURCE_AUTHORITY_CONFIG
            SET 
                LAST_UPDATE_TIME = #entity.lastUpdateTime#,
                LAST_UPDATOR_ID = #entity.lastUpdatorId#,
                VERSION_ID = #entity.versionId#,
                RESOURCE_CODE = #entity.resourceCode#,
                ACTION_ID = #entity.actionId#,
                POWER_CODE = #entity.powerCode#,
                PUBLISH_STATUS_ID = #entity.publishStatusId#,
                PROCESS_STATUS_ID = #entity.processStatusId#
            WHERE
                RESOURCE_AUTHORITY_CONFIG_ID = #entity.id# ;
	
		UPDATE
		TGP_SERIAL_NUM_OPERATION_LOG
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VERSION_ID=#entity.versionId#+1,
		DEAL_ID=#entity.dealId#,

		EXPORTED_COUNT=#entity.exportedCount#

		WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberExportLog'
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		TEMPLATE_ID=#entity.templateId#,
		AREA_TYPE_ID=#entity.areaTypeId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextAreaListComponent'
	
		UPDATE TGP_TOP_CITY_INFO
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		BEGIN_DATE = #entity.beginDate#,
		END_DATE = #entity.endDate#,
		DEAL_GROUP_ID=#entity.dealGroup.id#,
		CITY_ID=#entity.cityId#
		WHERE
		TOP_CITY_INFO_ID=#entity.id#
	
		UPDATE
		TGP_VISUAL_VIEW
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VERSION_ID=#entity.versionId#+1,
		JSON_CLOSURE=#entity.jsonClosure#,
		DEAL_GROUP_ID=#entity.dealGroupId#,
		TITLE=#entity.title#,
		DESCRIPTION=#entity.description#,
		IS_COMMERICAL_AREA_DISPLAYED=#entity.isCommericalAreaDisplayed#,
		CORP_DESCRIPTION= #entity.corperationInfo.description#,
		CORP_NAME = #entity.corperationInfo.name#,
		CORP_IS_EXPANDED = #entity.corperationInfo.isExpanded#,
		SHORT_DESC = #entity.shortDescription#,
		COMMENTS = #entity.comments#,
		PARENT_VISUAL_VIEW_ID=#entity.parentVisualViewId#
		WHERE
		VISUAL_VIEW_ID=#entity.id#
	
		UPDATE TGP_DOCUMENT_BUILDER
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VERSION_ID=#entity.versionId#,
		DEAL_GROUP_ID=#entity.dealGroupId#,
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplateId#
		WHERE
		DOCUMENT_BUILDER_ID=#entity.id#
	
		UPDATE TGP_TEMPLATE_ENTRY
		SET
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplate.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		IS_MANDATORY=#entity.isMandatory#,
		AREA_TYPE_ID=#entity.areaTypeId#,
		SEQUENCE=#entity.sequence#
		WHERE
		TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='StatementTemplate'
	
        UPDATE TGP_CARD
        SET
            NAME_TYPE = #entity.nameType# ,
            NAME = #entity.name# ,

            BACKGROUND_PICTURE_PATH = #entity.backgroundPicPath#,
            CATEGORY =  #entity.category#,
            CHARGE_AMOUNT = #entity.chargeAmount#,
            GIFT_AMOUNT = #entity.giftAmount#,
            COMMISSION_RATE = #entity.commissionRate#,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId#    ,
            DEAL_ID=#entity.dealId#,
            CARD_TYPE=#entity.cardType#,
            DISCOUNT=#entity.discount#

          WHERE
            CARD_ID = #entity.id# ;
	
		UPDATE TGP_CONTACT
		SET
		DEAL_GROUP_ID = #entity.dealGroup.id# ,
		CONTACT_NAME = #entity.contactName# ,
		CONTACT_EMAIL = #entity.contactEmail# ,
		CONTACT_MP = #entity.contactMP# ,
		CONTACT_TYPE = #entity.contactType# ,
		LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
		LAST_UPDATOR_ID = #entity.lastUpdatorId#
		WHERE CONTACT_ID=#entity.id#
	
        UPDATE TGP_CONTRACT
        SET
            CONTRACT_CRM_ID = #entity.contractCrmId# ,
            CONTRACT_SERIAL = #entity.contractSerial# ,
            CONTRACT_GLOBAL_ID=   #entity.contractGlobalId# ,
            ACCOUNT_GLOBAL_ID=  #entity.accountGlobalId# ,
            FP_SETTLE_TYPE  = #entity.fpSettleType# ,
            CASH_OUT_TYPE = #entity.cashOutType# ,
            ACCOUNT_NAME = #entity.accountName# ,
            CITY_ID = #entity.cityId# ,
            SIGN_ON_SALES_ID = #entity.signOnSalesId#,
            SIGN_ON_SALES_NAME = #entity.signOnSalesName#,
            OWNER_SALES_ID = #entity.ownerSalesId# ,
            OWNER_SALES_NAME = #entity.ownerSalesName# ,
            PAY_TERM_TYPE=#entity.payTermType#,
            STATUS=#entity.status#,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            VERSION_ID = #entity.versionId# ,
            ACCOUNT_REGISTER_NAME = #entity.accountRegisterName#,
            ACCOUNT_RATING = #entity.accountRating#,
            ACCOUNT_ID = #entity.accountId#,
            TYPE = #entity.type#,
            SOURCE_TYPE=#entity.sourceType#
          WHERE
            CONTRACT_ID = #entity.id# ;
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		IS_AUTO_CALCUATED=#entity.isAutoCalcuated#,
		TITLE=#entity.title#,
		TOTAL_PRICE=#entity.totalPrice#,
		RETAIL_PRICE_DESC=#entity.retailPriceDescription#,
		TOTAL_PRICE_LABEL=#entity.totalPriceLabel#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='DealComponent'
	
		UPDATE Csc_Temp_ImportRefund
		SET
			City = #tgRefundImportData.city#,
			OwnerLoginId =#tgRefundImportData.ownerLoginId#,
			CustomerName =#tgRefundImportData.customerName#,
			Status =#tgRefundImportData.status#
		WHERE
			DealGroupID = #tgRefundImportData.dealGroupId#
			AND Status =0;
	
		UPDATE Csc_Temp_ImportRefund
		SET
			CityName = #tgRefundImportData.cityName#,
			Status =#tgRefundImportData.status#
		WHERE
			City = #tgRefundImportData.city#
			AND Status=1;
	
		UPDATE Csc_Temp_ImportRefund
		SET
			ValidBeginTime = #tgRefundImportData.validBeginTime#,
			ValidEndTime = #tgRefundImportData.validEndTime#,
			Status =#tgRefundImportData.status#
		WHERE
			DealId = #tgRefundImportData.dealId#
			AND Status =3;
	
		UPDATE Csc_Temp_ImportRefund
		SET
			OwnerRealName = #tgRefundImportData.ownerRealName#,
			OwnerEmail = #tgRefundImportData.ownerEmail#,
			Status =#tgRefundImportData.status#
		WHERE
			OwnerLoginId = #tgRefundImportData.ownerLoginId#
			AND Status =2;
	
		UPDATE Csc_Temp_MarkDic
		SET
			LastID = #lastid#
		WHERE
			BizType=#biztype#;
	
		UPDATE CSC_CaseBiz
		SET
			UpdateTime = now()
		WHERE
			Id = #id#
	
        UPDATE CTI_TelephoneCalloutRecord
        SET Status = #status#,
        callbackEventType = #callbackEventType#,
        subCallbackEventType = #subCallbackEventType#,
        resultOfInvokeCallbackUrl = #resultOfInvokeCallbackUrl#,
        attemptsMade = #attemptsMade#,
        callUUID = #callUUID#,
        ssgCallResult = #ssgCallResult#,
        timeToLive = #timeToLive#,
        timeRemaining = #timeRemaining#,
        tenantName = #tenantName#,
        memo = #memo#
        WHERE
        UniqueID = #uniqueID#

        UPDATE CTI_TelephoneCalloutRecord
        SET Status = #status#
        WHERE
        UniqueID = #uniqueID#

    
    
    

    
		UPDATE FP_Invoice
		SET InvoiceStatus = #invoiceStatus#, ReleaseDate = #releaseDate#, UpdateDate =
		NOW(), UpdateLoginID = #loginId#,InvoiceTaxNo=#invoiceTaxNo#
		WHERE InvoiceID =#invoiceID# 
	
        UPDATE PC_AccountPayable
        SET BPID = #bpId#, Status = #status#, LastUpdateDate = NOW()
        WHERE APID = #apId# AND BPID = 0 AND Status != #status#
    
        UPDATE PC_AccountPayable
        SET FSPPID = #fsPPId#,Status = #status#,LastUpdateDate = NOW()
        WHERE BPID = #bpId# AND Status <> #status#
    
        UPDATE PC_AccountPayable
        SET Status = #status#,LastUpdateDate = NOW(),LastUpdateLoginID = #loginId#
        WHERE FSPPID = #fsPPId# AND Status <> #status#
    
        UPDATE PC_AccountPayable
        SET FSPPID = #fsPPId#, LastUpdateDate = NOW(),LastUpdateLoginID = #loginId#
        WHERE BPID = #bpId# AND FSPPID = 0
    
		
			UPDATE UC_UserBadge
			SET CurrentCount = Amount,
			  STATUS = 1,
			  COMMENT = #badge.reason#,
			  GotDate = #badge.gotDate#,
			  UpdateDate = NOW()
			WHERE UserID = #badge.userId#
			    AND BadgeID = #badge.badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = #badge.count#,
			  UpdateDate = NOW()
			WHERE UserID = #badge.userId#
			    AND BadgeID = #badge.badgeId#
			    AND STATUS = 0
		
	
        UPDATE MB_Microblog
        SET
            `Content` = #microblog.content#,
            `LoginId` = #microblog.loginId#,
            `CommentCount` = #microblog.commentCount#,
            `LikeCount` = #microblog.likeCount#,
            `Deleted` = #microblog.deleted#,
            `AddTime` = #microblog.addTime#,
            `UpdateTime` = #microblog.updateTime#
         WHERE `MicroblogId`=#microblog.microblogId#
    
        UPDATE FS_ExchangeOrder
        SET Status = #postStatus#,
        LastUpdateLoginID = #loginId#,
        OrderDate = #orderDate#,
        LastUpdateDate = now()
        WHERE ExchangeOrderID = #exchangeOrderId# AND Status = #preStatus#
    
        UPDATE FS_ExchangeOrder
        SET LastUpdateLoginID = #loginId#,
        Status = #setStatus#,
        Memo = #refundDTO.refundReason#,
        LastUpdateDate = #todayDate#
        WHERE BizCode = #refundDTO.refundId#
        AND Status = #preStatus#
    
    
    
    
        
        	UPDATE ZH_ShopFundAccount
			SET Credit = #shopFundAccountBean.credit#,
			Debit = #shopFundAccountBean.debit#,
			Balance = #shopFundAccountBean.balance#,
			UpdateDate = NOW()
        WHERE FundAccountID  = #shopFundAccountBean.fundAccountId#
 		
    
        UPDATE FS_PaymentPlan
        SET PaidAmount = PaidAmount + #paidAmount#
        WHERE PPID = #paymentPlanId# AND Status  <> 4
    
        UPDATE FS_PaymentPlan
        SET Status = #setStatus#,LastUpdateLoginID = #lastUpdateLoginId#,LastUpdateDate = #lastUpdateDate#,AuditType = #auditType#,PaidAmount = #paidAmount#
        WHERE PPID = #ppId# AND Status = #preStatus#
    
        UPDATE FS_PaymentPlan
        SET AuditType = #auditType# ,Status = #status#,LastUpdateLoginID = #loginId#,LastUpdateDate = #lastUpdateDate#
        WHERE PPID = #paymentPlanId#
    
        UPDATE FS_PaymentPlan
        SET Status = #status#,
        PaidAmount = #paidAmount#,
        LastUpdateLoginID = #lastUpdateLoginID#,
        LastUpdateDate = #lastUpdateDate#
        WHERE PPID = #paymentPlanId#
    
        AND Status!= #status#
    
        UPDATE RBAC_Manage
        SET
        `ManagerId` = #manage.managerId#,
        `ReferenceId` = #manage.referenceId#,
        `ReferenceType` = #manage.referenceType#,
        `Status` = #manage.status#,
        `AddTime` = #manage.addTime#,
        `UpdateTime` = #manage.updateTime#
        WHERE `Id`=#manage.id#
    
	Update CMS_Fragment 
	set DesignContent=TempContent,IsUpdate=0 
	where CityId=#cityId#
			AND ShopType=#shopType#
			AND ModuleId=#moduleId#
	

		update Ods_SalesforceDirtyProcInst
		set
		  LastProcessedProcInstId = #entity.lastProcessedProcInstId#,
		  IsProcessed = #entity.isProcessed#,
		  ProcessResult = #entity.processResult#,
          ProcessTime = #entity.processTime#
        where Id = #entity.id#

	
    
    
    
        UPDATE MR_AttachmentMetaData
        SET
            Status = 0
        WHERE AttachID =#attachID#
    
        UPDATE MR_AttachmentMetaDataFuture
        SET
            Status = 0
        WHERE AttachID =#attachID#
    
        UPDATE MR_AttachmentMetaDataHistory
        SET
            Status = 0
        WHERE AttachID =#attachID#
    
        UPDATE MR_AttachmentMetaData
        SET
            ApproveID      = #attachmentMetaData.approveID#,
            ApproveBy = #attachmentMetaData.approveBy#,
            ApproveStatus = #attachmentMetaData.approveStatus#,
            ApproveComment = #attachmentMetaData.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #attachmentMetaData.lastModifiedBy#
        WHERE AttachID =#attachmentMetaData.attachID#
    
        UPDATE MR_AttachmentMetaDataFuture
        SET
            ApproveID      = #attachmentMetaData.approveID#,
            ApproveBy = #attachmentMetaData.approveBy#,
            ApproveStatus = #attachmentMetaData.approveStatus#,
            ApproveComment = #attachmentMetaData.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #attachmentMetaData.lastModifiedBy#
        WHERE AttachID =#attachmentMetaData.attachID#
    
        UPDATE MR_AttachmentMetaDataHistory
        SET
            ApproveID      = #attachmentMetaData.approveID#,
            ApproveBy = #attachmentMetaData.approveBy#,
            ApproveStatus = #attachmentMetaData.approveStatus#,
            ApproveComment = #attachmentMetaData.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #attachmentMetaData.lastModifiedBy#
        WHERE AttachID =#attachmentMetaData.attachID#
    
        UPDATE MR_AttachmentMetaDataFuture
        SET CustomerID = #customerID#
        WHERE FileSkey =#fileSkey#
    
    
    
    
        UPDATE MR_Contact
        SET
            Status = 0
        WHERE ContactID =#contactID#
    
        UPDATE MR_ContactFuture
        SET
            Status = 0
        WHERE ContactID =#contactID#
    
        UPDATE MR_ContactHistory
        SET
            Status = 0
        WHERE ContactID =#contactID#
    
        UPDATE MR_Contact
        SET
            ApproveID      = #contact.approveID#,
            ApproveBy = #contact.approveBy#,
            ApproveStatus = #contact.approveStatus#,
            ApproveComment = #contact.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #contact.lastModifiedBy#
        WHERE ContactID =#contact.contactID#
    
        UPDATE MR_ContactFuture
        SET
            ApproveID      = #contact.approveID#,
            ApproveBy = #contact.approveBy#,
            ApproveStatus = #contact.approveStatus#,
            ApproveComment = #contact.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #contact.lastModifiedBy#
        WHERE ContactID =#contact.contactID#
    
        UPDATE MR_ContactHistory
        SET
            ApproveID      = #contact.approveID#,
            ApproveBy = #contact.approveBy#,
            ApproveStatus = #contact.approveStatus#,
            ApproveComment = #contact.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #contact.lastModifiedBy#
        WHERE ContactID =#contact.contactID#
    
    
    
    
        UPDATE MR_CustomerInvoiceTitle
        SET
            Status = 0
        WHERE InvoiceTitleID =#invoiceTitleID#
    
        UPDATE MR_CustomerInvoiceTitleFuture
        SET
            Status = 0
        WHERE InvoiceTitleID =#invoiceTitleID#
    
        UPDATE MR_CustomerInvoiceTitleHistory
        SET
            Status = 0
        WHERE InvoiceTitleID =#invoiceTitleID#
    
        UPDATE MR_CustomerInvoiceTitle
        SET
            ApproveID      = #customerInvoiceTitle.approveID#,
            ApproveBy = #customerInvoiceTitle.approveBy#,
            ApproveStatus = #customerInvoiceTitle.approveStatus#,
            ApproveComment = #customerInvoiceTitle.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerInvoiceTitle.lastModifiedBy#
        WHERE InvoiceTitleID =#customerInvoiceTitle.invoiceTitleID#
    
        UPDATE MR_CustomerInvoiceTitleFuture
        SET
            ApproveID      = #customerInvoiceTitle.approveID#,
            ApproveBy = #customerInvoiceTitle.approveBy#,
            ApproveStatus = #customerInvoiceTitle.approveStatus#,
            ApproveComment = #customerInvoiceTitle.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerInvoiceTitle.lastModifiedBy#
        WHERE InvoiceTitleID =#customerInvoiceTitle.invoiceTitleID#
    
        UPDATE MR_CustomerInvoiceTitleHistory
        SET
            ApproveID      = #customerInvoiceTitle.approveID#,
            ApproveBy = #customerInvoiceTitle.approveBy#,
            ApproveStatus = #customerInvoiceTitle.approveStatus#,
            ApproveComment = #customerInvoiceTitle.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerInvoiceTitle.lastModifiedBy#
        WHERE InvoiceTitleID =#customerInvoiceTitle.invoiceTitleID#
    
    
    
    
        UPDATE MR_UserShopTerritory
        SET
            Status = 0
        WHERE UserShopTerritoryID =#userShopTerritoryID#
    
        UPDATE MR_UserShopTerritoryFuture
        SET
            Status = 0
        WHERE UserShopTerritoryID =#userShopTerritoryID#
    
        UPDATE MR_UserShopTerritoryHistory
        SET
            Status = 0
        WHERE UserShopTerritoryID =#userShopTerritoryID#
    
        WHERE
        TradingLicenseID = #license.tradingLicenseId#
    
        UPDATE MR_TradingLicenseFuture
        SET CustomerID = #customerID#
        WHERE SFTradingLicenseID =#sfLicenseID#
    
        UPDATE MR_TradingLicenseFuture
        SET
            Status = 0
        WHERE TradingLicenseID =#licenseID#
    
        UPDATE MR_TradingLicenseFuture
        SET
            ApproveID      = #license.approveID#,
            ApproveBy = #license.approveBy#,
            ApproveStatus = #license.approveStatus#,
            ApproveComment = #license.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #license.lastModifiedBy#
        WHERE TradingLicenseID =#license.tradingLicenseId#
    
        update CT_Contract
        SET
        AAccountID=#contract.aAccountID#,
        BAccountID=#contract.bAccountID#,
        BizType=#contract.bizType#,
        ValidFrom=#contract.validFrom#,
        ValidTo=#contract.validTo#,
        Source=#contract.source#,
        ContractStatus=#contract.contractStatus#,
        Status=#contract.status#,
        LastModifiedTime=NOW(),
        LastModifiedBy=#contract.lastModifiedBy#,
        ApproveID=#contract.approveID#,
        ApproveBy=#contract.approveBy#,
        ApproveStatus=#contract.approveStatus#,
	    ApproveComment=#contract.approveComment#,
	    ApproveTime=#contract.approveTime#,
        HistoryID=#contract.historyID#,
        SFContractID=#contract.sfContractID#,
        CCContractID=#contract.ccContractID#
        where ContractID=#contract.contractID#
    
        update CT_ContractFuture
        SET
        AAccountID=#contract.aAccountID#,
        BAccountID=#contract.bAccountID#,
	    ContractNo=#contract.contractNo#,
        BizType=#contract.bizType#,
        ValidFrom=#contract.validFrom#,
        ValidTo=#contract.validTo#,
        Source=#contract.source#,
        ContractStatus=#contract.contractStatus#,
        Status=#contract.status#,
        LastModifiedTime=NOW(),
        LastModifiedBy=#contract.lastModifiedBy#,
        ApproveID=#contract.approveID#,
        ApproveBy=#contract.approveBy#,
        ApproveStatus=#contract.approveStatus#,
        ApproveComment=#contract.approveComment#,
	    ApproveTime=#contract.approveTime#,
        HistoryID=#contract.historyID#,
        SFContractID=#contract.sfContractID#,
        ProcessID=#contract.processID#,
        CCContractID=#contract.ccContractID#
        where ContractID=#contract.contractID#
    
    
        update CT_Contract
        SET
        Status=0
        where ContractID=#contractID#
    
        update CT_ContractFuture
        SET
        Status=0
        where ContractID=#contractID#
    
        update CT_ContractHistory
        SET
        Status=0
        where ContractID=#contractID#
    
        update CT_Contract
        SET
        LastModifiedTime=NOW(),
        ApproveID=#contract.approveID#,
        ApproveBy=#contract.approveBy#,
        ApproveStatus=#contract.approveStatus#,
        ApproveComment=#contract.approveComment#,
        ApproveTime = #ApproveTime#
        where ContractID=#contract.contractID#
    
        update CT_ContractFuture
        SET
        LastModifiedTime=NOW(),
        ApproveID=#contract.approveID#,
        ApproveBy=#contract.approveBy#,
        ApproveStatus=#contract.approveStatus#,
        ApproveComment=#contract.approveComment#,
        ApproveTime = #ApproveTime#
        where ContractID=#contract.contractID#
    
        update CT_ContractHistory
        SET
        LastModifiedTime=NOW(),
        ApproveID=#contract.approveID#,
        ApproveBy=#contract.approveBy#,
        ApproveStatus=#contract.approveStatus#,
        ApproveComment=#contract.approveComment#,
        ApproveTime = #ApproveTime#
        where ContractID=#contract.contractID#
    
		update Ods_Account
		set
		ShopType=#account.shopType#,ShopName=#account.shopName#,
		BranchName=#account.branchName#,Address=#account.address#,CrossRoad=#account.crossRoad#,
		PhoneNo=#account.phoneNo#,PhoneNo2=#account.phoneNo2#,CityID=#account.cityId#,
		Power=#account.power#,ShopGroupID=#account.shopGroupId#,GroupFlag=#account.groupFlag#,
		AltName=#account.altName#,SearchName=#account.searchName#,Hits=#account.hits#,
		WeeklyHits=#account.weeklyHits#,District=#account.district#,AddDate=#account.addDate#,
		LastDate=#account.lastDate#,Score=#account.score#,Score1=#account.score1#,
		Score2=#account.score2#,Score3=#account.score3#,Score4=#account.score4#,
		ShopTags=#account.shopTags#,PrimaryTag=#account.primaryTag#,SearchKeyWord=#account.searchKeyWord#,
		PrevWeeklyHits=#account.prevWeeklyHits#,WebSite=#account.webSite#,TodayHits=#account.todayHits#,
		MonthlyHits=#account.monthlyHits#,Popularity=#account.popularity#,GLat=#account.glat#,
		GLng=#account.glng#,ShopPower=#account.shopPower#,NearByTags=#account.nearByTags#,
		BusinessHours=#account.businessHours#,ClientType=#account.clientType#,
		City__c=#account.City__c#,StartScore__c=#account.StartScore__c#,
		ShopType__c=#account.ShopType__c#,Tag__c=#account.Tag__c#,
		ShopHyperLink__c=#account.ShopHyperLink__c#,ShopStatus__c=#account.ShopStatus__c#,
		District__c=#account.District__c#,ShopType__c=#account.ShopType__c#,
		Category__c=#account.Category__c#,MainCategory__c=#account.MainCategory__c#,
		CategorySearch__c=#account.CategorySearch__c#,CategoryFull__c=#account.CategoryFull__c#,
		MainRegion__c=#account.MainRegion__c#,RegionFull__c=#account.RegionFull__c#,
		RegionSearch__c=#account.RegionSearch__c#,BranchTotal=#account.branchTotal#
		where ShopID = #account.shopId#
	
        update Ods_Account
        set
        MainCategory__c=#account.MainCategory__c#,
        CategorySearch__c=#account.CategorySearch__c#,CategoryFull__c=#account.CategoryFull__c#,
        MainRegion__c=#account.MainRegion__c#,RegionFull__c=#account.RegionFull__c#,
        RegionSearch__c=#account.RegionSearch__c#
        where ShopID = #account.shopId#
    
        update Ods_Account
        set
        BranchTotal=#account.branchTotal#
        where ShopID = #account.shopId#
    
	
	
    
        UPDATE MR_Bank
        SET
            Status = 0
        WHERE BankID =#bankID#
    
    
    
    
        update CT_AttachmentMetaData
        SET
        Status=0
        where AttachID=#attachmentId#
    
        update CT_AttachmentMetaDataFuture
        SET
        Status=0
        where AttachID=#attachmentId#
    
        update CT_AttachmentMetaDataHistory
        SET
        Status=0
        where AttachID=#attachmentId#
    
        update CT_AttachmentMetaData
        SET
        ApproveID=#attachmentMetaData.approveId#,
        ApproveBy=#attachmentMetaData.approveBy#,
        ApproveStatus=#attachmentMetaData.approveStatus#,
        ApproveComment=#attachmentMetaData.approveComment#,
        LastModifiedBy = #attachmentMetaData.lastModifiedBy#,
        LastModifiedTime = NOW()
        where AttachID=#attachmentMetaData.attachmentId#
    
        update CT_AttachmentMetaDataFuture
        SET
        ApproveID=#attachmentMetaData.approveId#,
        ApproveBy=#attachmentMetaData.approveBy#,
        ApproveStatus=#attachmentMetaData.approveStatus#,
        ApproveComment=#attachmentMetaData.approveComment#,
        LastModifiedBy = #attachmentMetaData.lastModifiedBy#,
        LastModifiedTime = NOW()
        where AttachID=#attachmentMetaData.attachmentId#
    
        update CT_AttachmentMetaDataHistory
        SET
        ApproveID=#attachmentMetaData.approveId#,
        ApproveBy=#attachmentMetaData.approveBy#,
        ApproveStatus=#attachmentMetaData.approveStatus#,
        ApproveComment=#attachmentMetaData.approveComment#,
        LastModifiedBy = #attachmentMetaData.lastModifiedBy#,
        LastModifiedTime = NOW()
        where AttachID=#attachmentMetaData.attachmentId#
    
    
    
    
        UPDATE MR_Shop
        SET
            Status = 0
        WHERE NewShopID =#newShopId#
    
        UPDATE MR_ShopFuture
        SET
            Status = 0
        WHERE NewShopID =#newShopId#
    
        UPDATE MR_ShopHistory
        SET
            Status = 0
        WHERE NewShopID =#newShopId#
    
    
    
    
        UPDATE MR_CustomerUser
        SET
            Status = 0
        WHERE CustomerUserID =#customerUserID#
    
        UPDATE MR_CustomerUserFuture
        SET
            Status = 0
        WHERE CustomerUserID =#customerUserID#
    
        UPDATE MR_CustomerUserHistory
        SET
            Status = 0
        WHERE CustomerUserID =#customerUserID#
    
    
    
    
        UPDATE MR_Territory
        SET
            Status = 0
        WHERE TerritoryID =#territoryID#
    
        UPDATE MR_TerritoryFuture
        SET
            Status = 0
        WHERE TerritoryID =#territoryID#
    
        UPDATE MR_TerritoryHistory
        SET
            Status = 0
        WHERE TerritoryID =#territoryID#
    
    
    
    
        UPDATE MR_ShopTerritory
        SET
            Status = 0
        WHERE ShopTerritoryID =#shopTerritoryID#
    
        UPDATE MR_ShopTerritoryFuture
        SET
            Status = 0
        WHERE ShopTerritoryID =#shopTerritoryID#
    
        UPDATE MR_ShopTerritoryHistory
        SET
            Status = 0
        WHERE ShopTerritoryID =#shopTerritoryID#
    
           UPDATE
           MR_HygienicLicenseFuture
           SET
           CustomerID=#hygienicLicense.customerID#,
           SfHygienicID=#hygienicLicense.sfHygienicID#,
           RegisterNum=#hygienicLicense.registerNum#,
           CompanyName=#hygienicLicense.companyName#,
           Name=#hygienicLicense.name#,
           Address=#hygienicLicense.address#,
           Permit=#hygienicLicense.permit#,
           ValidFrom=#hygienicLicense.validFrom#,
           ValidTo=#hygienicLicense.validTo#,
           Issuer=#hygienicLicense.issuer#,
           IssuingDate=#hygienicLicense.issuingDate#,
           LastModifiedTime=NOW(),
           EventID=#hygienicLicense.eventID#,
           SpecialApprovalBy=#hygienicLicense.specialApprovalBy#,
           SpecialApprovalValidTo=#hygienicLicense.specialApprovalValidTo#,
           Status=#hygienicLicense.status#
           WHERE HygienicLicenseID=#hygienicLicense.hygienicLicenseID#
       
        UPDATE MR_HygienicLicenseFuture
        SET CustomerID=#customerID#
        WHERE SFHygienicID=#sfLicenseID#
    
        update MR_HygienicLicenseFuture
        SET
        ApproveID=#license.approveID#,
        ApproveBy=#license.approveBy#,
        ApproveStatus=#license.approveStatus#,
        ApproveComment=#license.approveComment#,
        LastModifiedTime=NOW(),
        LastModifiedBy=#license.lastModifiedBy#
        WHERE HygienicLicenseID=#license.hygienicLicenseID#
    
		UPDATE CT_ShopFAQ SET Question = #question# WHERE FAQID = #faqId#
	
		UPDATE CT_ShopFAQ SET Answer = #answer# WHERE FAQID = #faqId#
	
		UPDATE CT_ShopFAQ SET Status = #status# WHERE FAQID = #faqId# AND UserID = #userId#
	
	 	UPDATE 
	 		UC_UserCurrency
	 	SET
	 		CurrencyAmount=CurrencyAmount+#currencyAmount#,
	 		UpdateDate=now()
	 	WHERE
		 	Status = 1
			AND CityID=#cityId#
			AND Channel=#channel# 
			AND CurrencyType = #currencyType#
			AND UserID=#userId# 
 	
	
    
    
    
        UPDATE MR_Customer
        SET
            Status = 0
        WHERE CustomerID =#customerID#
    
        UPDATE MR_CustomerFuture
        SET
            Status = 0
        WHERE CustomerID =#customerID#
    
        UPDATE MR_CustomerHistory
        SET
            Status = 0
        WHERE CustomerID =#customerID#
    
        UPDATE MR_Customer
        SET
            ApproveID      = #customer.approveID#,
            ApproveBy = #customer.approveBy#,
            ApproveStatus = #customer.approveStatus#,
            ApproveComment = #customer.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customer.lastModifiedBy#
        WHERE CustomerID =#customer.customerID#
    
        UPDATE MR_CustomerFuture
        SET
            ApproveID      = #customer.approveID#,
            ApproveBy = #customer.approveBy#,
            ApproveStatus = #customer.approveStatus#,
            ApproveComment = #customer.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customer.lastModifiedBy#
        WHERE CustomerID =#customer.customerID#
    
        UPDATE MR_CustomerHistory
        SET
            ApproveID      = #customer.approveID#,
            ApproveBy = #customer.approveBy#,
            ApproveStatus = #customer.approveStatus#,
            ApproveComment = #customer.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customer.lastModifiedBy#
        WHERE CustomerID =#customer.customerID#
    
    
    
    
        UPDATE MR_OrgTerritory
        SET
            Status = 0
        WHERE OrgTerritoryID =#orgTerritoryID#
    
        UPDATE MR_OrgTerritoryFuture
        SET
            Status = 0
        WHERE OrgTerritoryID =#orgTerritoryID#
    
        UPDATE MR_OrgTerritoryHistory
        SET
            Status = 0
        WHERE OrgTerritoryID =#orgTerritoryID#
    
    
    
    
        UPDATE ShopNewShop
        SET
            Status = 0
        WHERE NewShopID =#newShopID#
    
        UPDATE ShopNewShopFuture
        SET
            Status = 0
        WHERE NewShopID =#newShopID#
    
        UPDATE ShopNewShopHistory
        SET
            Status = 0
        WHERE NewShopID =#newShopID#
    
        UPDATE ShopNewShop
        SET
            ApproveID      = #shopNewShop.approveID#,
            ApproveBy = #shopNewShop.approveBy#,
            ApproveStatus = #shopNewShop.approveStatus#,
            ApproveComment = #shopNewShop.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #shopNewShop.lastModifiedBy#
        WHERE NewShopID =#shopNewShop.newShopID#
    
        UPDATE ShopNewShopFuture
        SET
            ApproveID      = #shopNewShop.approveID#,
            ApproveBy = #shopNewShop.approveBy#,
            ApproveStatus = #shopNewShop.approveStatus#,
            ApproveComment = #shopNewShop.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #shopNewShop.lastModifiedBy#
        WHERE NewShopID =#shopNewShop.newShopID#
    
        UPDATE ShopNewShopHistory
        SET
            ApproveID      = #shopNewShop.approveID#,
            ApproveBy = #shopNewShop.approveBy#,
            ApproveStatus = #shopNewShop.approveStatus#,
            ApproveComment = #shopNewShop.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #shopNewShop.lastModifiedBy#
        WHERE NewShopID =#shopNewShop.newShopID#
    
    
    
    
        UPDATE MR_CustomerShop
        SET
            Status = 0
        WHERE CustomerShopID =#customerShopID#
    
        UPDATE MR_CustomerShopFuture
        SET
            Status = 0
        WHERE CustomerShopID =#customerShopID#
    
        UPDATE MR_CustomerShopHistory
        SET
            Status = 0
        WHERE CustomerShopID =#customerShopID#
    
        UPDATE MR_CustomerShop
        SET
            ApproveID      = #customerShop.approveID#,
            ApproveBy = #customerShop.approveBy#,
            ApproveStatus = #customerShop.approveStatus#,
            ApproveComment = #customerShop.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerShop.lastModifiedBy#
        WHERE CustomerShopID =#customerShop.customerShopID#
    
        UPDATE MR_CustomerShopFuture
        SET
            ApproveID      = #customerShop.approveID#,
            ApproveBy = #customerShop.approveBy#,
            ApproveStatus = #customerShop.approveStatus#,
            ApproveComment = #customerShop.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerShop.lastModifiedBy#
        WHERE CustomerShopID =#customerShop.customerShopID#
    
        UPDATE MR_CustomerShopHistory
        SET
            ApproveID      = #customerShop.approveID#,
            ApproveBy = #customerShop.approveBy#,
            ApproveStatus = #customerShop.approveStatus#,
            ApproveComment = #customerShop.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerShop.lastModifiedBy#
        WHERE CustomerShopID =#customerShop.customerShopID#
    
        UPDATE ExtraFieldsRelation
        SET
            ExtraFieldID = #extraFieldsRelation.extraFieldID#
        WHERE XXXID = #extraFieldsRelation.xxxID#
              AND ExtraFieldType = #extraFieldsRelation.extraFieldType#
              AND TableName = #extraFieldsRelation.tableName#
    
    
    
    
        UPDATE MR_CustomerBankAccount
        SET
            Status = 0
        WHERE AccountID =#accountID#
    
        UPDATE MR_CustomerBankAccountFuture
        SET
            Status = 0
        WHERE AccountID =#accountID#
    
        UPDATE MR_CustomerBankAccountHistory
        SET
            Status = 0
        WHERE AccountID =#accountID#
    
        UPDATE MR_CustomerBankAccount
        SET
            ApproveID      = #customerBankAccount.approveID#,
            ApproveBy = #customerBankAccount.approveBy#,
            ApproveStatus = #customerBankAccount.approveStatus#,
            ApproveComment = #customerBankAccount.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerBankAccount.lastModifiedBy#
        WHERE AccountID =#customerBankAccount.accountID#
    
        UPDATE MR_CustomerBankAccountFuture
        SET
            ApproveID      = #customerBankAccount.approveID#,
            ApproveBy = #customerBankAccount.approveBy#,
            ApproveStatus = #customerBankAccount.approveStatus#,
            ApproveComment = #customerBankAccount.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerBankAccount.lastModifiedBy#
        WHERE AccountID =#customerBankAccount.accountID#
    
        UPDATE MR_CustomerBankAccount
        SET
            PaymentStatus = #customerBankAccount.paymentStatus#
        WHERE AccountID =#customerBankAccount.accountID#
    
        UPDATE MR_CustomerBankAccountFuture
        SET
            PaymentStatus = #customerBankAccount.paymentStatus#
        WHERE AccountID =#customerBankAccount.accountID#
    
        UPDATE MR_CustomerBankAccountHistory
        SET
            ApproveID      = #customerBankAccount.approveID#,
            ApproveBy = #customerBankAccount.approveBy#,
            ApproveStatus = #customerBankAccount.approveStatus#,
            ApproveComment = #customerBankAccount.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #customerBankAccount.lastModifiedBy#
        WHERE AccountID =#customerBankAccount.accountID#
    
    
    
    
        UPDATE MR_CustomerIDCard
        SET
            Status = 0
        WHERE IDNumber =#idNumber#
    
        UPDATE MR_CustomerIDCard
        SET
            Status = 0
        WHERE IDCardID =#idCardID#
    
        UPDATE MR_CustomerIDCardFuture
        SET
            Status = 0
        WHERE IDCardID =#idCardID#
    
        UPDATE MR_CustomerIDCardFuture
        SET
            Status = 0
        WHERE IDNumber =#idNumber#
    
        UPDATE MR_CustomerIDCardFuture
        SET CustomerID = #customerID#
        WHERE SFIDCardID =#sfLicenseID#
    
        UPDATE MR_CustomerIDCard
        SET Status = 0
        WHERE CustomerID =#customerID#
    
        UPDATE MR_CustomerIDCardFuture
        SET Status = 0
        WHERE CustomerID =#customerID#
    
        UPDATE MR_CustomerIDCardFuture
        SET
            ApproveID      = #license.approveID#,
            ApproveBy = #license.approveBy#,
            ApproveStatus = #license.approveStatus#,
            ApproveComment = #license.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #license.lastModifiedBy#
        WHERE IDCardID =#license.idCardID#
    
    
        UPDATE CRM_PPCContractBankAccount SET NormalStatus = #status#
            where PPCContractId = #contractId#
              and AccountNo = #bankAccountNo#  
    
    
		UPDATE
			UC_UserCurrencyTrans
		SET
			Type=#type#,UpdateDate=now()
		WHERE
			Id=#id#
			
	
        
        UPDATE CRM_YdContractBankAccount SET NormalStatus = #status#
            where YdContractId = #contractId#
              and AccountNo = #bankAccountNo#
    
    
    
    
    
        UPDATE MR_License
        SET
            Status = 0
        WHERE LicenseID =#licenseID#
    
        UPDATE MR_LicenseFuture
        SET
            Status = 0
        WHERE LicenseID =#licenseID#
    
        UPDATE MR_LicenseHistory
        SET
            Status = 0
        WHERE LicenseID =#licenseID#
    
        UPDATE MR_License
        SET
            ApproveID      = #license.approveID#,
            ApproveBy = #license.approveBy#,
            ApproveStatus = #license.approveStatus#,
            ApproveComment = #license.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #license.lastModifiedBy#
        WHERE LicenseID =#license.licenseID#
    
        UPDATE MR_LicenseFuture
        SET
            ApproveID      = #license.approveID#,
            ApproveBy = #license.approveBy#,
            ApproveStatus = #license.approveStatus#,
            ApproveComment = #license.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #license.lastModifiedBy#
        WHERE LicenseID =#license.licenseID#
    
        UPDATE MR_LicenseHistory
        SET
            ApproveID      = #license.approveID#,
            ApproveBy = #license.approveBy#,
            ApproveStatus = #license.approveStatus#,
            ApproveComment = #license.approveComment#,
            LastModifiedTime = NOW(),
            LastModifiedBy = #license.lastModifiedBy#
        WHERE LicenseID =#license.licenseID#
    
        UPDATE MR_LicenseFuture
        SET CustomerID = #customerID#
        WHERE SFLicenseID =#sfLicenseID#
    
	
	UPDATE AD_Product
		SET
		ProductCode=#productEntity.productCode#,
		ProductName=#productEntity.productName#, 
		ProductSubType=#productEntity.productSubType#, 
		 ProductType= #productEntity.productType#,
		 TargetPage =#productEntity.targetPage#,
		 CarouselType =  #productEntity.carouselType#,
		 IsAllowFree =  #productEntity.isAllowFree#,
		 InventoryLimit =  #productEntity.inventoryLimit#,
		 
		 AddTime =  #productEntity.addTime#,
		 AddUser = #productEntity.addUser#,
		 UpdateTime = #productEntity.updateTime#,
		 UpdateUser =  #productEntity.updateUser#,
		 Status =#productEntity.status#,
		 BeginDate =  #productEntity.beginDate#,
		 EndDate =  #productEntity.endDate#,
		 IsSaleByBrandGroup =  #productEntity.isSaleByBrandGroup#,
		 CarouselTimesLimit =  #productEntity.carouselTimesLimit#,
		SalableEndDate = #productEntity.salableEndDate#,
		SalableBeginDate = #productEntity.salableBeginDate#
		WHERE
			ProductId = #productEntity.productId#
		
		
	
	
	
	UPDATE [Sales].[CS_ContractItemRelation]
	   SET [ContractItemID] = #contractItemRelation.contractItemId#
	      ,[OrderItemID] = #contractItemRelation.orderItemId#
	      ,[OpportunityID] = #contractItemRelation.opportunityId#
	      ,[AddDate] = #contractItemRelation.addDate#
	      ,[UpdateDate] = #contractItemRelation.updateDate#
	      ,[Status] = #contractItemRelation.status#
	 WHERE ID=#contractItemRelation.id#
 	
	
	
		UPDATE  Sales.CS_Customer
		SET     CustomerGlobalID = #customerGlobalId#
		WHERE   CustomerID = #customerId#
	
	
		update TG_NaviTagItemAttribute set ItemID=#itemId#,Name=#name#,Value=#value#  where NaviTagItemAttributeID=#id#
	
		UPDATE
		TG_UserSetting
		SET
		RemindSmsType=#userSetting.remindSmsType#,
		RemindSmsStatus=#userSetting.remindSmsStatus#,
		RemindEmailStatus=#userSetting.remindEmailStatus#,
		UpdateDate=#userSetting.updateDate#
		WHERE
		UserId=#userSetting.userId#
	
    	UPDATE LAB_UserApp
		SET STATUS = #status#
		WHERE UserID = #userId#
		    AND AppID = #appId#
    
        UPDATE DP_dpQzoneLike SET
        Status=#status# , UpdateTime=Now()
        WHERE FeedID = #feedId# And UserID=#userId#;
    
		UPDATE TG_DealGroupExtend SET
		UpdateDate = NOW(),
		IsShowDealGroupRegion = #dealGroupExtend.isShowDealGroupRegion#,
		IsAsyncReceipt = #dealGroupExtend.isAsyncReceipt#,
		ActivationType = #dealGroupExtend.activationType#,
		AheadHours = #dealGroupExtend.aheadHours#,
		EditorTeam = #dealGroupExtend.editorTeam#,
		SourceID = #dealGroupExtend.sourceId#,
		IsBlockStock = #dealGroupExtend.isBlockStock#
		WHERE DealGroupID = #dealGroupExtend.id#
	
		UPDATE
		CQRS_ORDER
		SET
		ORDER_ID=#entity.id#,
		CONTENT=#entity.content#
		WHERE
		ORDER_ID=#entity.id#
	 
	 
		Update AD_ItemKeyword  
		SET
		    ShopId = #itemKeyword.shopId#,
		    NewShopId = #itemKeyword.newShopId#,
		    Keyword = #itemKeyword.keyword#,
			UpdateTime = #itemKeyword.updateTime#, 
			UpdateUser = #itemKeyword.updateUser#
		WHERE
			ItemKeywordId = #itemKeyword.itemKeywordId#; 
		  
	 
	 
		UPDATE AD_Order
		set 
			ShopType=#order.shopType#,
			MainCategoryId=#order.mainCategoryId#,
			Amount=#order.amount#,
			SalesCityId=#order.salesCityId#,
			PriceLoadTime=#order.priceLoadTime#,
			SourceType=#order.sourceType#,
			Status=#order.status#,
			UpdateTime=#order.updateTime#,
			UpdateUser=#order.updateUser#
		where
			OrderId=#order.orderId#
	
	
				
		update AD_OrderItemDuration
		set BeginDate=#OrderItemDuration.beginDate#,
		EndDate=#OrderItemDuration.endDate#,
		UpdateTime=#OrderItemDuration.updateTime#,
		UpdateUser=#OrderItemDuration.updateUser#
		where OrderItemDurationId=#OrderItemDuration.orderItemDurationId#
		

	
	
	UPDATE AD_ProductDisplayTemplate
	  SET  
		ProductId = #productDisplayTemplate.productId#, 
		TemplateName = #productDisplayTemplate.templateName#, 
		IsCustomSize = #productDisplayTemplate.isCustomSize#,
		AddTime = #productDisplayTemplate.addTime#,
		AddUser = #productDisplayTemplate.addUser#,
		UpdateTime = #productDisplayTemplate.updateTime#,
		UpdateUser = #productDisplayTemplate.updateUser#,
		Status = #productDisplayTemplate.status#,
		IsMaster = #productDisplayTemplate.isMaster#
	 WHERE 
	 	TemplateId =  #productDisplayTemplate.templateId#
		     
	
	
		
		UPDATE [Sales].[CS_ContractPayTerms]
		   SET [InvReqDate] = #contractPayTerm.invReqDate#
		      ,[PrePayDate] = #contractPayTerm.prePayDate#
		      ,[Amount] = #contractPayTerm.amount#
		      ,[Remark] = #contractPayTerm.remark#
		      ,[InvRelease] = #contractPayTerm.invRelease#
		      ,[InvReleaseDate] = #contractPayTerm.invReleaseDate#
		      ,[InvMemo] = #contractPayTerm.invMemo#
		      ,[InvNo] = #contractPayTerm.invNo#
		      ,[AdminID] = #contractPayTerm.adminId#
		      ,[IsAdjusted] = #contractPayTerm.isAdjusted#
		 WHERE ContractID=#contractPayTerm.contractId# AND TermID=#contractPayTerm.termId#
		
	
		
		UPDATE [ASPNet_zSurvey].[DP_AWDetail]
		   SET [MasterID] = #awDetail.masterId#
		      ,[CityID] = #awDetail.cityId#
		      ,[ShopType] = #awDetail.shopType#
		      ,[FromDate] = #awDetail.fromDate#
		      ,[EndDate] = #awDetail.endDate#
		      ,[AWTitle] = #awDetail.awTitle#
		      ,[AWText] = #awDetail.awText#
		      ,[AWText2] = #awDetail.awText2#
		      ,[AWLink] = #awDetail.awLink#
		      ,[AWShowLink] = #awDetail.awShowLink#
		      ,[AWMemo] = #awDetail.awMemo#
		      ,[Power] = #awDetail.power#
		      ,[ShopID] = #awDetail.shopId#
		      ,[Priority] = #awDetail.priority#
		      ,[AWStyle] = #awDetail.awStyle#
		      ,[AWImageLink] = #awDetail.awImageLink#
		      ,[AWImageLink2] = #awDetail.awImageLink2#
		      ,[ItemID] = #awDetail.itemId#
		      ,[OldItemID] = #awDetail.oldItemId#
		      ,[AwContent] = #awDetail.awContent#
		      ,[note] = #awDetail.note#
		      ,[BigImageLink] = #awDetail.bigImageLink#
		      ,[ButtonImagePath] = #awDetail.buttonImagePath#
		      ,[BottomImagePath] = #awDetail.bottomImagePath#
		 WHERE AWID=#awDetail.awid#
		
	
		UPDATE
		TG_DealGroup SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
		DealGroupID = #dealGroupID# AND (MaxJoin - #reduceMaxJoin#) > 0
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin - #reduceCurrentJoin# WHERE
		DealGroupID = #dealGroupId# AND CurrentJoin <= MaxJoin
		AND (CurrentJoin - #reduceCurrentJoin#) > -1
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin + #incCurrentJoin#
		WHERE
		DealGroupID = #dealGroupId#
	
		UPDATE
		TG_DealGroup SET CurrentJoin = #currentJoin#, CurrentJoinVersion = #dealGroupCurrentJoinVersion#
		WHERE DealGroupID = #dealGroupId#
		AND #dealGroupCurrentJoinVersion# > CurrentJoinVersion
	
		UPDATE TG_DealGroup
		SET FinishDate = NOW() WHERE DealGroupID = #dealGroupId#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
    	UPDATE UC_Dairy
		SET CityID = #cityId#
		WHERE ID = #dairyId#
    
        WHERE FollowNoteID = #followNoteId# And FromUserID=#fromUserId#;
    
        UPDATE TG_DealGroupVerify SET
        DealID = #verify.dealID#,
        DealGroupID = #verify.dealGroupID#,
        UpdateDate = NOW(),
        Status = #verify.status#,
        ThirdPartyDealID = #verify.thirdPartyDealID#,
        ThirdPartyId = #verify.thirdPartyId#,
        Num = #verify.num#,
        IsspID = #verify.isspID#
        WHERE VerifyID = #verify.verifyID#
    
        UPDATE TG_Vendor SET
        DealID = #vendor.id#,
        ShopIDList = #vendor.shopIDList#,
        Account = #vendor.account#,
        PassWord = #vendor.passWord#,
        Mobile = #vendor.mobile#,
        Mobile2 = #vendor.mobile2#
        WHERE DealID = #vendor.id#
    
		UPDATE
		CQRS_SEAT_AVAILABILITY
		SET
		SEAT_AVAILABILITY_ID=#entity.id#,
		ORDER_ID=#entity.order.id#
		WHERE
		SEAT_AVAILABILITY_ID=#entity.id#
	
        UPDATE TPD_DealGroupVersion
        SET PublishVersion = #version#
        WHERE DealGroupID = #dealGroupId#
    
        UPDATE TG_DealGroup SET
        DealGroupShortTitle = #dealGroup.dealGroupShortTitle#,
        DealGroupTitleDesc = #dealGroup.dealGroupTitleDesc#,
        DealGroupTitle = #dealGroup.dealGroupTitle#,
        DealGroupName = #dealGroup.dealGroupName#,
        DealGroupPrice = #dealGroup.dealGroupPrice#,
        MarketPrice = #dealGroup.marketPrice#,
        DefaultPic = #dealGroup.defaultPic#,
        HighLight = #dealGroup.highLight#,
        DealGroupInfo = #dealGroup.dealGroupInfo#,
        ShopInfo = #dealGroup.shopInfo#,
        BeginDate = #dealGroup.beginDate#,
        EndDate = #dealGroup.endDate#,
        MaxPerUser = #dealGroup.maxPerUser#,
        RemainDisplayPercent = #dealGroup.remainDisplayPercent#,
        IsSideDeal = #dealGroup.isSideDeal#,
        DealGroupSortID = #dealGroup.dealGroupSortId#,
        Status = #dealGroup.status#,
        PublishStatus = #dealGroup.publishStatus#,
        IsReward = #dealGroup.isReward#,
        EditorInfo = #dealGroup.editorInfo#,
        ImportantPoint = #dealGroup.importantPoint#,
        MemberInfo = #dealGroup.memberInfo#,
        SpecialPoint = #dealGroup.specialPoint#,
        ProductInfo = #dealGroup.productInfo#,
        LotteryStatus = #dealGroup.lotteryStatus#,
        QRCodeValidate = #dealGroup.qRCodeValidate#,
        DealAltName = #dealGroup.dealAltName#,
        AutoRefundSwitch = #dealGroup.refundType#,
        IsCanUseCoupon = #dealGroup.isCanUseCoupon#,
        DealGroupPics = #dealGroup.dealGroupPics#,
        IsECommerceWebSite = #dealGroup.isECommerceWebSite#,
        SaleChannel = #dealGroup.saleChannel#,
        UpdateDate = NOW(),
        ProcessMemo = #dealGroup.processMemo#,
        RefundMemo = #dealGroup.refundMemo#,
        ProductMemo = #dealGroup.productMemo#,
        ShopMemo = #dealGroup.shopMemo#,
        DealGroupAbstract = #dealGroup.dealGroupAbstract#,
        DealGroupSummary = #dealGroup.dealGroupSummary#,
        MinPerUser = #dealGroup.minPerUser#,
        ThirdPartVerify = #dealGroup.thirdPartVerify#,
        DealGroupTypeID = #dealGroup.dealGroupTypeID#
        WHERE DealGroupID = #dealGroup.id#
    
		UPDATE TG_DealGroup
		SET UpdateDate = NOW() WHERE DealGroupID = #id#
	
        UPDATE TG_DealGroup
        SET MaxJoin = (SELECT SUM(MaxJoin) FROM TG_Deal WHERE DealGroupID = #dealGroupId#),
        OldMaxJoin = (SELECT SUM(OldMaxJoin) FROM TG_Deal WHERE DealGroupID = #dealGroupId#)
        WHERE DealGroupID = #dealGroupId#;
    
        UPDATE TPD_TransferJob
        SET `Status` = #status#
        WHERE `ID` = #id#;
     
		 
		UPDATE AD_CategoryPriceLevel 
		SET 
			SearchPageLevel=#searchPageLevel#, 
			MainPageLevel=#mainPageLevel#, 
			Status=#status#, 
			UpdateUser=#updateUser#,
			UpdateTime=#updateTime# 
		WHERE CategoryLevelId=#categoryLevelId# 
		
	
		
		UPDATE [Sales].[CS_Contract_Extend]
		   SET [ContractID] = #contractExtend.contractId#
		      ,[InvType] = #contractExtend.invType#
		      ,[InvIdentifier] = #contractExtend.invIdentifier#
		      ,[InvAccountBank] = #contractExtend.invAccountBank#
		      ,[InvAccountName] = #contractExtend.invAccountName#
		      ,[InvAccountNo] = #contractExtend.invAccountNo#
		 WHERE ContractEXID = #contractExtend.contractEXId#
		
	
		
		UPDATE [Sales].[CS_ContractItems]
	    SET [ContractID] = #contractItem.contractId#
	       ,[ProductType] = #contractItem.productType#
	       ,[Amount] = #contractItem.amount#
	       ,[BeginDate] = #contractItem.beginDate#
	       ,[EndDate] = #contractItem.endDate#
	       ,[UpdateCount] = #contractItem.updateCount#
	       ,[Status] = #contractItem.status#
	       ,[AdminID] = #contractItem.adminId#
	       ,[UpdateDate] = #contractItem.updateDate#
	       ,[Memo] = #contractItem.memo#
		 WHERE ItemId=#contractItem.itemId#
		
	
		UPDATE DP_FeedBackForPOI
		SET
			FeedGroupID = #feedGroupId#
		WHERE
			FeedID = #feedId#
    
		UPDATE DP_FeedBackForPOI
		SET
			FeedGroupID = FeedID
		WHERE
			FeedGroupID = 0
    
		WHERE
			FeedID = #feedId#
    
		WHERE
			FeedGroupID = #feedGroupId#
      
    
		update TG_NaviTagItem set ItemName=#itemName#,Priority=#priority#  where ItemID=#itemId#
		
		UPDATE TG_NaviTagItem 
			SET STATUS=0
		WHERE
			ItemId=#itemId#
		
		UPDATE TG_NaviTagItem a, TG_NaviItemBrand b 
			SET a.Priority=0-b.SearchNum
		WHERE b.BrandEnName = a.ItemEnName
			AND b.cityId=#cityId#
			AND a.tagId=#tagId#;
	
        UPDATE
        TG_Deal SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
        DealID = #dealID# AND (MaxJoin - #reduceMaxJoin#) > 0
    
		UPDATE
		TG_Deal SET CurrentJoin = CurrentJoin + #incCurrentJoin#
		WHERE
		DealID = #dealId#
	
		UPDATE TG_Deal  SET CurrentJoin = #currentJoin#, CurrentJoinVersion = #dealCurrentJoinVersion#
		WHERE DealID = #dealId#
		AND #dealCurrentJoinVersion# > CurrentJoinVersion
	
		UPDATE TG_Deal  SET FinishDate = NOW()    WHERE DealID = #dealId#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
		update TG_NaviTagItem set ItemName=#itemName#,Priority=#priority#  where ItemID=#itemId#
		
		UPDATE TG_NaviTagItem 
			SET STATUS=0
		WHERE
			ItemId=#itemId#
		
		UPDATE TG_NaviTagItem a, TG_NaviItemBrand b 
			SET a.Priority=0-b.SearchNum
		WHERE b.BrandEnName = a.ItemEnName
			AND b.cityId=#cityId#
			AND a.tagId=#tagId#;
	
		update TG_NaviTagItemAttribute set ItemID=#itemId#,Name=#name#,Value=#value#  where NaviTagItemAttributeID=#id#
	
        UPDATE TG_Deal SET
        DealGroupID = #deal.dealGroupId#,
        DealTitle = #deal.dealTitle#,
        DealName = #deal.dealName#,
        DealSMSName = #deal.dealSMSName#,
        DealPrice = #deal.dealPrice#,
        MarketPrice = #deal.marketPrice#,
        Cost = #deal.cost#,
        DealSortID = #deal.dealSortId#,
        MaxJoin =  IF(#deal.maxJoin#=0,0,GREATEST(MaxJoin + #deal.maxJoin# - OldMaxJoin,1)),
        OldMaxJoin = #deal.maxJoin#,
        CurrentJoin = #deal.currentJoin#,
        RemainDisplayPercent = #deal.remainDisplayPercent#,
        FinishDate = #deal.finishDate#,
        DeliverType = #deal.deliverType#,
        ReceiptType = #deal.receiptType#,
        ProvideInvoice = #deal.provideInvoice#,
        DealShortTitle = #deal.dealShortTitle#,
        DealStatus = #deal.dealStatus#,
        IsDealPriceLargerThanCost = #deal.isDealPriceLargerThanCost#,
        UpdateTime = NOW()
        WHERE DealID = #deal.id#
    
        UPDATE TG_Deal  SET FinishDate = NOW()    WHERE DealID = #id#
    
        UPDATE TG_Deal
        SET FinishDate=#finishDate# WHERE DealID = #id#
     
				
		UPDATE AD_ItemButton 
		SET
			CategoryLevelId = #itemButton.categoryLevelId# , 
			ShopType = #itemButton.shopType# , 
			CategoryId = #itemButton.categoryId# , 
			TemplateId = #itemButton.templateId# , 
			UpdateTime = #itemButton.updateTime# , 
			UpdateUser = #itemButton.updateUser# , 
			STATUS = #itemButton.status#		
		WHERE
			ItemButtonId = #itemButton.itemButtonId# ;
		  
	
		

			UPDATE AD_Keyword
			SET
			    PriceLevel=#keywordEntity.priceLevel#,
			    TopThreeStock=#keywordEntity.topThreeStock#,
			    FirstPageStock=#keywordEntity.firstPageStock#,
				Status=#keywordEntity.status#,
				UpdateUser=#keywordEntity.updateUser#,
				UpdateTime=#keywordEntity.updateTime#
			WHERE
				KeywordId=#keywordEntity.keywordId#

        
	
        

			UPDATE AD_Keyword
			SET
				Status=0, UpdateTime = NOW(), UpdateUser = #loginId#
			WHERE
				KeywordId=#keywordId#

        
    
     
	 
		UPDATE AD_OrderItem
		SET 
			OrderId=#orderItem.orderId#,
			CityId=#orderItem.cityId#,
			ProductId=#orderItem.productId#,
			ProductType=#orderItem.productType#,
			ProductSubType=#orderItem.productSubType#,
			OrderItemPrice=#orderItem.orderItemPrice#,
			PriceUnit=#orderItem.priceUnit#,
			STATUS=#orderItem.status#,
			UpdateTime=#orderItem.updateTime#,
			UpdateUser=#orderItem.updateUser#
		WHERE
			OrderItemId=#orderItem.orderItemId#
	
	
         
        UPDATE AD_TrafficPriceLevel
        SET CityId = #trafficPriceLevel.cityId#,
            ShopType = #trafficPriceLevel.shopType#,
            PriceLevel = #trafficPriceLevel.priceLevel#,
            `Status` = #trafficPriceLevel.status#,
            UpdateTime = #trafficPriceLevel.updateTime#,
            UpdateUser = #trafficPriceLevel.updateUser#
        WHERE ID = #trafficPriceLevel.id#
        
    
		
		UPDATE [Sales].[CS_Contract]
		   SET [ContractNO] = #contract.contractNo#
		      ,[CustomerID] = #contract.customerId#
		      ,[ContactID] = #contract.contactId#
		      ,[SMS] = #contract.sms#
		      ,[ContactID2] = #contract.contactId2#
		      ,[SMS2] = #contract.sms2#
		      ,[ContractAddTime] = #contract.contractAddTime#
		      ,[ContractUpdateTime] = #contract.contractUpdateTime#
		      ,[ContractDate] = #contract.contractDate#
		      ,[NeedPay] = #contract.needPay#
		      ,[SalesID] = #contract.salesId#
		      ,[IsSeries] = #contract.isSeries#
		      ,[Amount] = #contract.amount#
		      ,[InvCompany] = #contract.invCompany#
		      ,[InvText] = #contract.invText#
		      ,[PayType] = #contract.payType#
		      ,[InvPostAddress] = #contract.invPostAddress#
		      ,[InvZipCode] = #contract.invZipCode#
		      ,[InvContact] = #contract.invContact#
		      ,[InvPhone] = #contract.invPhone#
		      ,[Status] = #contract.status#
		      ,[AdminID] = #contract.adminId#
		      ,[CanUpdate] = #contract.canUpdate#
		      ,[MayEnd] = #contract.mayEnd#
		      ,[OperateID] = #contract.operateId#
		      ,[CustomerName] = #contract.customerName#
		      ,[Memo] = #contract.memo#
		      ,[CompanyID] = #contract.companyId#
		      ,[USAccrualAmount] = #contract.usAccrualAmount#
		      ,[TAXAccrualAmount] = #contract.taxAccrualAmount#
		      ,[ParentContractID] = #contract.parentContractId#
		      ,[DepartmentID] = #contract.departmentId#
		      ,[OwnerLoginID] = #contract.ownerLoginId#
		      ,[CreatorLoginID] = #contract.creatorLoginId#
		      ,[ContractGlobalID] = #contract.contractGlobalId#
		      ,[AmountFlowType] = #contract.amountFlowType#
		      ,[SourceType] = #contract.sourceType#
		      ,[CanceledReason] = #contract.canceledReason#
		 WHERE ContractID = #contract.contractId#
		
	
	
		UPDATE [Sales].[CS_Contract]
		   SET [AmountFlowType] = #amountFlowType#
		      ,[CanceledReason] = #canceledReason#
		 WHERE ContractID = #contractId#
		
	
		UPDATE
		TG_DealGroupRemind
		SET
		Status=0
		WHERE
		RemindId in ($remindIds$) AND
		UserId=#userId#
	
		UPDATE
		TG_DealGroupRemind
		SET
		Status=0
		WHERE
		ShopGroupId in ($shopGroupIds$) AND
		UserId=#userId#
	
		UPDATE TG_WishList
		SET Status = #status#, LastDate = NOW()
		WHERE DealGroupID = #dealGroupId# AND UserID=#userId#
	
	
		UPDATE DP_UserAuthenticationLevel
		SET Level=#level#, UpdateTime=NOW()
		WHERE UserID=#userId#
	
	
		UPDATE DP_HotelNewFunctionScoreBook
		SET Score = Score +1
		WHERE FunctionId = #id#
	
		UPDATE
		TG_DealGroup SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
		DealGroupID = #dealGroupID# AND (MaxJoin - #reduceMaxJoin#) > 0
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin - #reduceCurrentJoin# WHERE
		DealGroupID = #dealGroupId# AND CurrentJoin <= MaxJoin
		AND (CurrentJoin - #reduceCurrentJoin#) > -1
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin + #incCurrentJoin#
		WHERE
		DealGroupID = #dealGroupId#
	
		UPDATE
		TG_DealGroup SET CurrentJoin = #currentJoin#, CurrentJoinVersion = #dealGroupCurrentJoinVersion#
		WHERE DealGroupID = #dealGroupId#
		AND #dealGroupCurrentJoinVersion# > CurrentJoinVersion
	
		UPDATE TG_DealGroup
		SET FinishDate = NOW() WHERE DealGroupID = #dealGroupId#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
        UPDATE TG_DealReceiptInfo
        SET DealGroupID = #receipt.dealGroupID#,
            Contact = #receipt.contact#,
            TrafficInfo = #receipt.trafficInfo#,
            Memo = #receipt.memo#,
            BeginDate = #receipt.beginDate#,
            EndDate = #receipt.endDate#,
            RefundDate = #receipt.refundDate#
        WHERE DealID = #receipt.dealID#
    
	  UPDATE TG_DealReceiptInfo SET
	      BeginDate = #beginDate#,
	      EndDate = #endDate#,
	      RefundDate = #refundDate#
	  WHERE DealGroupID = #dealGroupId#
  
        UPDATE TPD_TransferTask
        SET `Status` = #status#
        WHERE `ID` = #id#;
    
        UPDATE TPD_TransferTask
        SET `RetryTimes` = #retryTimes#
        WHERE `ID` = #id#;
    
	
		UPDATE AD_CRMRelation 
			SET
			OpportunityId = #crmRelation.opportunityId# , 
			ContractId = #crmRelation.contractId#, 
			OrderId = #crmRelation.orderId# , 
			AddUser = #crmRelation.addUser# , 
			ADDTIME = #crmRelation.addTime# , 
			UpdateUser = #crmRelation.updateUser# , 
			UpdateTime = #crmRelation.updateTime#			
			WHERE
			CRMRelationId =#crmRelation.crmRelationId# ;	
	
	
				
		update AD_ItemCityTone
		set shopId=#ItemCityTone.shopId#,
		    newShopId=#ItemCityTone.newShopId#,
			updateTime=#ItemCityTone.updateTime#,
			updateUser=#ItemCityTone.updateUser#
		where ItemCityToneId=#ItemCityTone.itemCityToneId#
		

	
		UPDATE AD_Price
		SET
		Price
		= #priceEntity.price#,
		Unit = #priceEntity.unit#,
		UpdateTime =
		#priceEntity.updateTime#,
		UpdateUser = #priceEntity.updateUser#
		WHERE
		ProductId=#priceEntity.productId#
		AND
		DatePeriodKey=#priceEntity.datePeriodKey#
		AND
		PriceLevel=#priceEntity.priceLevel#
	
		UPDATE AD_Price
		SET
		BeginDate = #datePeriodEntity.beginDate#,
		EndDate =
		#datePeriodEntity.endDate#
		WHERE
		DatePeriodKey=#datePeriodEntity.datePeriodKey# AND
		CityGroupKey=#cityGroupKey#
	
		UPDATE AD_Price
		SET
		Price = #priceEntity.price#,
		UpdateTime = #priceEntity.updateTime#,
		UpdateUser = #priceEntity.updateUser#
		WHERE
		ProductId=#priceEntity.productId#
		AND
		PriceLevel=#priceEntity.priceLevel#
		AND
		DatePeriodKey=#priceEntity.datePeriodKey#
	
	
	UPDATE AD_ProductDisplayTemplateItem
	  SET  
		TemplateId = #productDisplayTemplateItem.templateId#, 
		TemplateItemName = #productDisplayTemplateItem.templateItemName#, 
		TemplateItemType = #productDisplayTemplateItem.templateItemType#,
		
		ImageHeight = #productDisplayTemplateItem.imageHeight#, 
		ImageWidth = #productDisplayTemplateItem.imageWidth#, 
		TextLength = #productDisplayTemplateItem.textLength#, 
		MaterialPattern = #productDisplayTemplateItem.materialPattern#,
		
		
		
		AddTime = #productDisplayTemplateItem.addTime#,
		AddUser = #productDisplayTemplateItem.addUser#,
		UpdateTime = #productDisplayTemplateItem.updateTime#,
		UpdateUser = #productDisplayTemplateItem.updateUser#,
		Status = #productDisplayTemplateItem.status# 
	 WHERE 
	 	TemplateItemId =  #productDisplayTemplateItem.templateItemId#
		     
	
	
		
		UPDATE [ASPNet_zSurvey].[DP_AWKeyword]
		   SET [AWID] = #awKeyword.awid#
		      ,[Keyword] = #awKeyword.keyword#
		      ,[AdType] = #awKeyword.adType#
		      ,[Power] = #awKeyword.power#
		      ,[HasNotified] = #awKeyword.hasNotified#
		      ,[NotifyDate] = #awKeyword.notifyDate#
		      ,[Channel] = #awKeyword.channel#
		      ,[Category] = #awKeyword.category#
		      ,[Region] = #awKeyword.region#
		      ,[Priority] = #awKeyword.priority#
		      ,[SourceType] = #awKeyword.sourceType#
		 WHERE KID=#awKeyword.kid# AND AWID=#awKeyword.awid#
		
	
		
		UPDATE [ASPNet_zSurvey].[DP_AWKeyword]
		   SET SourceType = #sourceType#
		 WHERE [AWID] = #awid#
		
	
    	
		UPDATE [ASPNet_zSurvey].[DP_AWKeyword]
		   SET Keyword = #keyword#
		 WHERE [AWID] = #awid#
		
    
		UPDATE  ZS_FeedBack SET FeedGroupID = FeedID WHERE FeedGroupID = 0 
    
        
           update DP_Shop set AvgPrice = #avgPrice#
           where shopId = #shopId#;
        
    
    	UPDATE UC_DairySection
		SET Detail = #detail#
		WHERE ID = #id#
    
        UPDATE DP_dpQzoneOfficialLike SET
        Status=#status# ,UpdateTime=Now()
        WHERE SourceType=#sourceType# and FeedID = #feedId# And UserID=#userId#;
    
        UPDATE
        TG_Deal SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
        DealID = #dealID# AND (MaxJoin - #reduceMaxJoin#) > 0
    
		UPDATE
		TG_Deal SET CurrentJoin = CurrentJoin + #incCurrentJoin#
		WHERE
		DealID = #dealId#
	
		UPDATE TG_Deal  SET CurrentJoin = #currentJoin#, CurrentJoinVersion = #dealCurrentJoinVersion#
		WHERE DealID = #dealId#
		AND #dealCurrentJoinVersion# > CurrentJoinVersion
	
		UPDATE TG_Deal  SET FinishDate = NOW()    WHERE DealID = #dealId#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
		UPDATE TG_WishList
		SET Status = #status#, LastDate = NOW()
		WHERE DealGroupID = #dealGroupId# AND UserID=#userId#
	
        update
          UC_DairyShare
        set
          Status = 1 ,
          ActualDate = now()
        where ID = #id#
    
		UPDATE DP_OfficialAlbum SET AlbumType= #albumType#,picCount = #picCount#
        WHERE ID = #albumId#;	
	
        UPDATE
            DP_ShopPhone
        SET CityID = #cityID#
        WHERE
            ID = #id#
    
	    UPDATE EventPromo
		SET  SendCount = SendCount + #sendCount#
		WHERE EventID = #eventId# AND PromoID = #promoId# AND status = 0
	  
	    UPDATE EventPromo
		SET  Status = 1
		WHERE EventID = #eventId# AND PromoID = #promoId#
	  		
		UPDATE DPEvent.EV_PrizeGroup 
		SET
		GroupName = #groupName# , 
		BaseCount = #baseCount# , 
		Memo = #memo# , 
		ChanceNum = #chanceNum# , 
		GroupType = #groupType# , 
		StartDate = #startDate# , 
		EndDate = #endDate# , 
		Status = #status#,
		EventID = #eventId#,
		IPLimit = #ipLimit#		
		WHERE
		GroupID = #groupId# 
			
		UPDATE DPEvent.EV_PrizeGroup 
			SET AttendCount = AttendCount+1 
			WHERE GroupID = #groupId#;
	
		UPDATE DPEvent.EV_PrizeGroup 
		SET
			Status = #status#
		WHERE
			GroupID = #groupId#
	
		UPDATE EV_Questionnaire
		SET
		GroupID = #groupId# ,
		QuestionnaireType = #questionnaireType# ,
		QuestionnaireTitle = #questionnaireTitle# ,
		QuestionOrder = #questionOrder# ,
		Boundary = #boundary#,
		UpdateTime = NOW()
		WHERE
		QuestionnaireID = #questionnaireId#

	
	    UPDATE EventPromo
		SET  SendCount = SendCount + #sendCount#
		WHERE EventID = #eventId# AND PromoID = #promoId# AND status = 0
	  
	    UPDATE EventPromo
		SET  Status = 1
		WHERE EventID = #eventId# AND PromoID = #promoId#
	  
		UPDATE DPEvent.EV_PrizeRecord
		SET
		UserName = #userName# ,
		MobileNo = #mobileNo# ,
		Address = #address# ,
		Memo = #memo#
		WHERE
		RecordID = #recordId# AND UserID=#userId#;
	
		UPDATE DPEvent.EV_PrizeRecord
		SET STATUS = 1
		WHERE RecordID = #recordId# AND EventID=#eventId#;
	
		UPDATE EV_QuestionnaireGroup
		SET State = #state#
		WHERE GroupID=#groupId#
	
		UPDATE EV_QuestionnaireGroup
		SET
		EventID = #eventId# ,
		GroupType = #groupType# ,
		LimitType = #limitType# ,
		Memo = #memo# ,
		ChanceNum = #chanceNum# ,
		IPLimit = #ipLimit# ,
		StartTime = #startTime# ,
		EndTime = #endTime# ,
		State = #state# 
		WHERE
		GroupID=#groupId#

	
    
        UpdateDate = now()
        WHERE
        BookOwnerID = #bookOwnerStatusUpdateBean.bookOwnerId#
    
		UPDATE
		EV_VoteRecord
		SET
		Status=1
		WHERE RecordID=#recordId#
			
		UPDATE DPEvent.EV_PrizeGroup 
			SET AttendCount = AttendCount+1 
			WHERE GroupID = #groupId#;
	
        UPDATE DP_Account
        SET
            `IDNumber` = #accountRow.idNumber#,
            `EmployeeNumber` = #accountRow.employeeNumber#,
            `LoginId` = #accountRow.loginId#,
            `Gmail` = #accountRow.gmail#,
            `GmailStatus` = #accountRow.gmailStatus#,
            `Gmail2` = #accountRow.gmail2#,
            `Gmail2Status` = #accountRow.gmail2Status#,
            `AD` = #accountRow.ad#,
            `ADStatus` = #accountRow.adStatus#,
            `SysStatus` = #accountRow.sysStatus#,
            `Name` = #accountRow.name#,
            `UpdateTime` = #accountRow.updateTime#
         WHERE id=#accountRow.accountId#
    
      update EP_Leave_Application
      	set
      	    `AddTime`=#leaveApplication.addTime#,
            `UpdateTime`=NOW(),
            `LoginId`=#leaveApplication.loginId#,
            `LeaveType`=#leaveApplication.leaveType#,
            `StartTime`=#leaveApplication.startTime#,
            `EndTime`=#leaveApplication.endTime#,
            `Duration`=#leaveApplication.duration#,
            `ApplicationStatus`=#leaveApplication.applicationStatus#,
            `ApplyReason`=#leaveApplication.applyReason#,
            `RejectReason`=#leaveApplication.rejectReason#,
            `LeaveBankId`=#leaveApplication.leaveBankId#,
            `ReportTo`=#leaveApplication.reportTo#,
            `TaskGuid`=#leaveApplication.taskGuid#,
            `ApprovalTime`=#leaveApplication.approvalTime#
      	where
        	`ApplicationId` = #leaveApplication.applicationId#
    
        update EP_Leave_Application
          set
              `ApprovalTime` = NOW()
          where
              `ApplicationId` = #applicationId#
    
		UPDATE DP_OfficialAlbum SET AlbumType= #albumType#,picCount = #picCount#
        WHERE ID = #albumId#;	
	
        UPDATE DP_OfficialAlbum
        SET AlbumType = 1
        WHERE ShopId = #shopId#
    
		
			UPDATE
				DP_ShopVideo
            SET
                UploadDate = #uploadDate#
			WHERE
				VideoID = #videoId#
		
	
		UPDATE AttendUser
		SET Data3=#data3#
		WHERE AttendID=#attendId#
	
		UPDATE AttendUser
		SET Status=1
		WHERE AttendID=#attendId#
	
		WHERE AttendID=#attendId#
	
		UPDATE EV_Comment
		SET
		VerifyStatus = 2,
		UpdateTime=NOW()
		WHERE RecordID=#recordId#
	
		UPDATE EV_Comment
		SET
		VerifyStatus = 1,
		UpdateTime=NOW()
		WHERE RecordID=#recordId#
	
		UPDATE DPEvent.EV_Prize
		SET
		ConsumeCount = ConsumeCount+1
		WHERE
		PrizeID = #prizeId# AND TotalCount>=ConsumeCount+1;
			
				
			UPDATE DPEvent.EV_Prize 
			SET
			Rank = #rank# , 
			PrizeName = #prizeName# , 
			Memo = #memo# , 
			TotalCount = #totalCount# , 
			Msg = #msg# , 
			Difficulty = #difficulty# , 
			GroupID = #groupId# , 
			Pic = #pic# , 
			PrizeType = #prizeType#	,
			`Order` = #order#	
			WHERE
			PrizeID = #prizeId# ;	
		
			
		UPDATE DPEvent.EV_PrizeGroup 
		SET
		GroupName = #groupName# , 
		BaseCount = #baseCount# , 
		Memo = #memo# , 
		ChanceNum = #chanceNum# , 
		GroupType = #groupType# , 
		StartDate = #startDate# , 
		EndDate = #endDate# , 
		Status = #status#,
		EventID = #eventId#,
		IPLimit = #ipLimit#		
		WHERE
		GroupID = #groupId# 
			
		UPDATE DPEvent.EV_PrizeGroup 
			SET AttendCount = AttendCount+1 
			WHERE GroupID = #groupId#;
	
		UPDATE DPEvent.EV_PrizeGroup 
		SET
			Status = #status#
		WHERE
			GroupID = #groupId#
	
		UPDATE DPEvent.EV_CommentGroup
		SET AttendCount = AttendCount + 1
		WHERE GroupID
		= #groupId#
	
		UPDATE DPEvent.EV_PrizeRecordStatus
		SET
		 `Status` = #status#
		WHERE
		UserID=#userId# AND GroupID=#groupId#;
	
        UPDATE DPEvent.EV_PrizeRecordStatus
        SET status = 0
        WHERE GroupID = #groupId#
        AND PrizeID = #prizeId#;
    
	        UPDATE EV_PromoType SET Remark = #remark#,AddDate=#currentTime#
	        WHERE PromoType=#promoType#
	
        WHERE (ID=#id#)
    
        UPDATE DPEvent.ACT_MaterialDeploy  
        SET 
        PracticalValue = #practicalValue#,
        UpdateTime = NOW() 
        WHERE ID = #id#
    
	 	update DPEvent.ACT_Module set 
	 	ModuleName=#moduleName#,
	 	RawHTML = #rawHTML#,
	 	Introduce = #introduce#,
	 	ModType = #modType#,
	 	PicUrl = #picUrl#,
	 	EditJs = #editJs#,
	 	UpdateTime = NOW() 
	 	where id=#id#
			
		UPDATE DPEvent.EV_Prize 
			SET 
			ConsumeCount = ConsumeCount+1 
			WHERE
			PrizeID = #prizeId# AND TotalCount>=ConsumeCount+1;
	
        UPDATE EP_Contact_Favorite
        SET
            `LoginId` = #contactFavoriteRow.loginId#,
            `ContactLoginId` = #contactFavoriteRow.contactLoginId#,
            `Starred` = #contactFavoriteRow.starred#,
            `UpdateTime` = #contactFavoriteRow.updateTime#
         WHERE `LoginId`=#contactFavoriteRow.loginId#
         AND   `ContactLoginId` = #contactFavoriteRow.contactLoginId#
    
		update
		UC_ExchangeGiftItem set RemainCount = RemainCount - #exchangeNum#
		,UpdateTime = now() WHERE ID =#itemID#
	
		UPDATE
		UC_ExchangeGiftItem SET UpdateTime = DATE_SUB(NOW(), INTERVAL 3 YEAR)
		WHERE ID =#itemId#
	
			UPDATE DP_BizJournalAccount
			SET TradeStatus = #bizJournalAccount.tradeStatus#, ReceiptID=#bizJournalAccount.receiptId#
			WHERE TradeNo = #bizJournalAccount.tradeNo#
	
		UPDATE DP_OfficialAlbumPic SET AlbumID=#albumId#
        WHERE PicID IN ($picIds$);	
	
		DELETE FROM DP_OfficialAlbumPic 
        WHERE PicId = #picId#;	
	
		UPDATE DP_BookingShop SET MainCategoryID=#mainCategoryId#,CityID=#cityId# 
        WHERE ShopId = #shopId#
	
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId# AND PicType=2
			
		UPDATE DPEvent.EV_PrizeGroup 
		SET
		GroupName = #groupName# , 
		BaseCount = #baseCount# , 
		Memo = #memo# , 
		ChanceNum = #chanceNum# , 
		GroupType = #groupType# , 
		StartDate = #startDate# , 
		EndDate = #endDate# , 
		Status = #status#,
		EventID = #eventId#,
		IPLimit = #ipLimit#		
		WHERE
		GroupID = #groupId# 
	
		UPDATE DPEvent.EV_PrizeGroup 
		SET
			Status = #status#
		WHERE
			GroupID = #groupId#
	    	
		UPDATE DPEvent.EV_PrizeRecord 
			SET	STATUS = 1 	
			WHERE RecordID = #recordId# AND EventID=#eventId#;
	
		UPDATE DPEvent.EV_PrizeRecord
		SET
		UserName = #userName# ,
		MobileNo = #mobileNo# ,
		Address = #address# ,
		Memo = #memo#
		WHERE
		RecordID = #recordId# AND UserID=#userId#;
	
		UPDATE DPEvent.EV_PrizeRecord
		SET STATUS = 1
		WHERE RecordID = #recordId# AND EventID=#eventId#;
	
		UPDATE EV_QuestionnaireOption
		SET
		Type = #type# ,
		Label = #label# ,
		Text = #text#,
		Point = #point#,
		UpdateTime=NOW()
		WHERE
		QuestionnaireID = #questionnaireId# AND OptionID = #optionId#

	
		UPDATE EVP_WebModule
		SET Status = 0
		WHERE ID=#Id#
	
		WHERE
		ID = #Id#
	
		UPDATE EVP_WebModule
		SET
			Status = #status#
		WHERE
			ID = #Id#	
	
		UPDATE EVENT_Events
		SET AttendCount = #attendCount#,
		VisitCount=#visitCount#
		WHERE EventID = #eventId#
	
		UPDATE
		EVENT_Events
		SET State = #state#
		WHERE EventID = #eventId#
	
		WHERE (EventID=#eventId#)
	
		UPDATE
		EV_ConfigValue SET Value = #value#
		WHERE EventID=#eventId# AND
		CityID=#cityId# AND GroupID = #groupId# AND `Key` = #key#
	
		UPDATE EVP_WebModule SET Status = 0
		WHERE ID=#Id# 
			
			UPDATE EVP_WebModule 
			SET
			BizData = #bizData# , 
			CustomJS = #customJs# ,
			RawHTML = #rawHtml# 		
			WHERE
			ID = #Id# 

	
		UPDATE EV_QuestionnaireOption
		SET
		Type = #type# ,
		Label = #label# ,
		Text = #text#,
		Point = #point#,
		UpdateTime=NOW()
		WHERE
		QuestionnaireID = #questionnaireId# AND OptionID = #optionId#

	
		UPDATE EVP_WebModule
		SET Status = 0
		WHERE ID=#Id#
	
		UPDATE EV_QuestionnaireResultRule
		SET
		BeginScore = #beginScore# ,
		EndScore = #endScore# ,
		ContainsBegin = #containsBegin#,
		ContainsEnd = #containsEnd#,
		Text = #text#,
		UpdateTime=NOW()
		WHERE
		ResultID = #resultId# AND GroupID=#groupId#

			
		UPDATE DPEvent.EV_PrizeGroup 
			SET AttendCount = AttendCount+1 
			WHERE GroupID = #groupId#;
	
		UPDATE EVENT_Events
		SET AttendCount = #attendCount#,
		VisitCount=#visitCount#
		WHERE EventID = #eventId#
	
		UPDATE
		EVENT_Events
		SET State = #state#
		WHERE EventID = #eventId#
	
		WHERE (EventID=#eventId#)
			
		UPDATE DPEvent.EV_Prize 
			SET 
			ConsumeCount = ConsumeCount+1 
			WHERE
			PrizeID = #prizeId# AND TotalCount>=ConsumeCount+1;
					
		UPDATE DPEvent.EV_PrizeRecord 
			SET
			UserName = #userName# , 
			MobileNo = #mobileNo# , 
			Address = #address# , 
			Memo = #memo# 			
			WHERE
			RecordID = #recordId# AND UserID=#userId#;
	
        UPDATE
            YY_BookingShopCash
        SET
            balance = balance - #deduct#, update_by = #updateBy#, updated_time = #updatedTime#
        WHERE
            shop_id = #shopId#
    
		update
		UC_ExchangeGiftItem set RemainCount = RemainCount - #exchangeNum#
		,UpdateTime = now() WHERE ID =#itemID#
	
		UPDATE
		UC_ExchangeGiftItem SET UpdateTime = DATE_SUB(NOW(), INTERVAL 3 YEAR)
		WHERE ID =#itemId#
	
		UPDATE
			UC_ExchangeGift
		SET
			GiftName=#gift.giftName#,
			
			GiftSummary=#gift.giftSummary#,
			
			GiftPic=#gift.giftPic#,
			
			GiftPrice=#gift.giftPrice#,
			
			UpdateTime=NOW()
			
		WHERE 
			ID=#gift.id#	
	
		UPDATE
		UC_ExchangeGiftItem
		SET
		ItemStatus=#itemStatus#,
		UpdateTime = NOW()
		WHERE
		ID =#itemId#
	
		UpdateTime = NOW()
		WHERE
		ID =#item.id#
	
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId#
	
		UPDATE EV_Comment
		SET
		VerifyStatus = 2,
		UpdateTime=NOW()
		WHERE RecordID=#recordId#
	
		UPDATE EV_Comment
		SET
		VerifyStatus = 1,
		UpdateTime=NOW()
		WHERE RecordID=#recordId#
	
		UPDATE EventList
		SET AttendCount = #attendCount#,
		VisitCount=#visitCount#
		WHERE EventID = #eventId#
	
		UPDATE EventList
		SET State = #state#
		WHERE EventID = #eventId#
			
			WHERE (EventID=#eventId#)
			
				
			UPDATE DPEvent.EV_Prize 
			SET
			Rank = #rank# , 
			PrizeName = #prizeName# , 
			Memo = #memo# , 
			TotalCount = #totalCount# , 
			Msg = #msg# , 
			Difficulty = #difficulty# , 
			GroupID = #groupId# , 
			Pic = #pic# , 
			PrizeType = #prizeType#	,
			`Order` = #order#	
			WHERE
			PrizeID = #prizeId# ;	
		
	
	    UPDATE EV_Promo SET PromoName =#promoName# ,CityID =#cityId#, 
	    PromoType = #promoType# , ShopID = #shopId# , PromoCount =#promoCount#,
	    StartTime = #startTime# , EndTime = #endTime# ,PromoMessage=#promoMessage#, Memo=#memo#
		WHERE PromoID = #promoId# AND  EventID = #eventId# AND Status = 0
	
	    UPDATE EV_Promo
		SET  SendCount = SendCount + #sendCount#
		WHERE  PromoID = #promoId# AND  EventID = #eventId# AND Status = 0
	
	    UPDATE EV_Promo
		SET  Status = 1
		WHERE PromoID = #promoId# AND EventID = #eventId#
	
	      UPDATE EV_PromoDownload
	      SET Status = 1
	      WHERE EventID = #eventId# AND logID = #logId#
			
			UPDATE EV_URLRewriteRules 
			SET
			MaskURL = #maskUrl# , 
			RealURL = #realUrl# ,
			Context = #context# ,
			PageID = #pageId#		
			WHERE
			RuleID = #ruleId# 

	
		UPDATE DPEvent.EV_CommentGroup
		SET AttendCount = AttendCount + 1
		WHERE GroupID
		= #groupId#
	
        UPDATE DPEvent.EV_PrizeRecordStatus
        SET
        `Status` = #status#
        WHERE
        UserID=#userId# AND GroupID=#groupId#;
    
        UPDATE DPEvent.EV_PrizeRecordStatus
        SET status = 0
        WHERE GroupID = #groupId#
        AND PrizeID = #prizeId#;
    
		UPDATE EV_Questionnaire
		SET
		GroupID = #groupId# ,
		QuestionnaireType = #questionnaireType# ,
		QuestionnaireTitle = #questionnaireTitle# ,
		QuestionOrder = #questionOrder# ,
		Boundary = #boundary#,
		UpdateTime = NOW()
		WHERE
		QuestionnaireID = #questionnaireId#

	
        WHERE (ID=#id#)
     
			 WHERE EventID=#eventId#
	
		update
		UC_ExchangeGiftOrder set UserID = #userID# ,
		CityID = #cityId#,
		PhoneNum=#phone#,Remark=#remark#,UpdateTime=now(),UserName=#userName#,STATUS
		= #status# ,ExchangeDate=now(),DeliverType=2
		where ID = #id#
	
		UPDATE UC_ExchangeGiftOrder
		SET UserName = #userName#,
		  PhoneNum = #phoneNum#,
		  ProvinceName = #provinceName#,
		  CityName = #cityName#,
		  Address = #address#,
		  PostZip = #postZip#,
		  Remark = #remark#,
		  STATUS = #newStatus#,
		  UpdateTime = NOW()
		WHERE ID = #orderId#
		    AND UserID = #userId#
		    AND ExchangeType = #exchangeType#
		    AND STATUS = #oldStatus#
	
		update
		UC_ExchangeGiftOrder set UpdateTime=now(),STATUS = #status#
		,ExchangeDate=now()
		where ID = #id#
	
		update
		UC_ExchangeGiftOrder set
		UpdateTime=now(),MsgSendCounter=MsgSendCounter+1
		where ID = #orderId#
	
		update
		UC_ExchangeGiftOrder set UserID = #userID# ,
		CityID = #cityId#,
		PhoneNum=#phone#,Remark=#remark#,UpdateTime=now(),UserName=#userName#,STATUS
		= #status# ,ExchangeDate=now(),DeliverType=2
		where ID = #id#
	
		update
		UC_ExchangeGiftOrder set UpdateTime=now(),STATUS = #status#
		,ExchangeDate=now()
		where ID = #id#
	
		update
		UC_ExchangeGiftOrder set
		UpdateTime=now(),MsgSendCounter=MsgSendCounter+1
		where ID = #orderId#
	
		update
		UC_ExchangeGiftOrder set UserID = #userID# ,
		PhoneNum=#phone#,Remark=#remark#,UpdateTime=now(),UserName=#userName#,STATUS
		= #status# ,ExchangeDate=now(),DeliverType=2
		where ID = #id#
	
		update
		UC_ExchangeGiftOrder set UpdateTime=now(),STATUS = #status#
		,ExchangeDate=now()
		where ID = #id#
	
		update
		UC_ExchangeGiftOrder set
		UpdateTime=now(),MsgSendCounter=MsgSendCounter+1
		where ID = #orderId#
	
		update
			UC_ExchangeGiftOrder 
		set 
			UpdateTime=now(),
			GiftName=#giftName#,
			ExchangePrice=#exchangePrice#,
			ExchangeType=#exchageType#		
		where 
			ItemID = #itemId# and STATUS = 0
	
		update
		UC_ExchangeGiftOrder set UpdateTime=now(),STATUS = #status#
		where ID = #id#
	
		update
		UC_ExchangeGiftOrder set UpdateTime=now(),STATUS = 8
		where STATUS = 7 AND DeliverType = 1
	
		update
		UC_ExchangeGiftOrder set UpdateTime=now(),BussinessID = #expressNO#
		where ID = #id#
	
			UpdateTime=now()
		where ID = #id#
	
		UPDATE DP_OfficialAlbumPic SET AlbumID=#albumId#
        WHERE PicID IN ($picIds$);	
	
		DELETE FROM DP_OfficialAlbumPic 
        WHERE PicId = #picId#;	
	
        UPDATE
            DP_BizAccount
        SET CityID = #cityID#
        WHERE
            AccountID = #accountID#
    
		UPDATE EventList
		SET AttendCount = #attendCount#,
		VisitCount=#visitCount#
		WHERE EventID = #eventId#
	
		UPDATE EventList
		SET State = #state#
		WHERE EventID = #eventId#
			
			WHERE (EventID=#eventId#)
	
		UPDATE EVP_WebModule
		SET Status = 0
		WHERE ID=#Id#
	
		WHERE
		ID = #Id#
	
		UPDATE EVP_WebModule
		SET
			Status = #status#
		WHERE
			ID = #Id#	
	
		UPDATE AttendUser
		SET Data3=#data3#
		WHERE AttendID=#attendId#
	
		UPDATE AttendUser
		SET Status=1
		WHERE AttendID=#attendId#
	
		WHERE AttendID=#attendId#
	
		UPDATE DPEvent.EV_Prize
		SET
		ConsumeCount = ConsumeCount+1
		WHERE
		PrizeID = #prizeId# AND TotalCount>=ConsumeCount+1;
			
				
			UPDATE DPEvent.EV_Prize 
			SET
			Rank = #rank# , 
			PrizeName = #prizeName# , 
			Memo = #memo# , 
			TotalCount = #totalCount# , 
			Msg = #msg# , 
			Difficulty = #difficulty# , 
			GroupID = #groupId# , 
			Pic = #pic# , 
			PrizeType = #prizeType#	,
			`Order` = #order#	
			WHERE
			PrizeID = #prizeId# ;	
		
	
		UPDATE EV_QuestionnaireGroup
		SET State = #state#
		WHERE GroupID=#groupId#
	
		UPDATE EV_QuestionnaireGroup
		SET
		EventID = #eventId# ,
		GroupType = #groupType# ,
		LimitType = #limitType# ,
		Memo = #memo# ,
		ChanceNum = #chanceNum# ,
		IPLimit = #ipLimit# ,
		StartTime = #startTime# ,
		EndTime = #endTime# ,
		State = #state# 
		WHERE
		GroupID=#groupId#

	
		UPDATE EVP_WebModule
		SET Status = 0
		WHERE ID=#Id#
	
		UPDATE EV_QuestionnaireResultRule
		SET
		BeginScore = #beginScore# ,
		EndScore = #endScore# ,
		ContainsBegin = #containsBegin#,
		ContainsEnd = #containsEnd#,
		Text = #text#,
		UpdateTime=NOW()
		WHERE
		ResultID = #resultId# AND GroupID=#groupId#

	
		UPDATE AttendUser
		SET Status=1
		WHERE AttendID=#attendId#
			
			WHERE (EventID=#eventId#)
	
	    UPDATE EV_VoteCandidate
	    SET Name=#name#,
			Pic=#pic#,
			Memo=#memo#,
			VoteEventID=#voteEventId#
		WHERE CandidateID = #candidateId# AND Status = 0
	
		UPDATE EV_VoteEvent
		SET 
			VoteEventName=#voteEventName#,
			Pic=#pic#,
			StartDate=#startDate#,
			EndDate=#endDate#,
			Memo=#memo#,
			EventID=#eventId#,
			IPLimit=#ipLimit#,
			IDLimit=#idLimit#,
			CandidateLimit=#candidateLimit#,
			LimitPeriod=#limitPeriod#
		WHERE VoteEventID = #voteEventId# AND Status=0
					
		UPDATE DPEvent.EV_BadgeRecord 
			SET
			Data3 = Data3-1			
			WHERE 
			UserID=#userId# AND EventID=#eventId#;
	
		UPDATE DPEvent.ACT_MaterialStandard SET `Explain` = #explain#,
		  `Limit` = #limit#,
		  MatType = #matType#,
          `Name` = #name#,
          RelateName = #relateName#  
          WHERE ID = #id#
	
        
        UPDATE TO_FlashSaleDealGroup
        SET BeginDate = #dealGroup.beginDate#,
            EndDate = #dealGroup.endDate#,
            Status = #dealGroup.status#
        WHERE DealGroupID = #dealGroup.dealGroupId#
        
    
        
        UPDATE TO_FlashSaleDealGroupCity
        SET Status = 0
        WHERE DealGroupID = #dealGroupId#
        
    
		update DP_ThirdUser
		set Token = null
		where DPUid = #dpUid#
			and Type = #type#
	
        

         UPDATE TO_FlashSaleDealGroup SET WannaJoin = WannaJoin + 1 WHERE DealGroupID = #dealGroupId#

        
    
		update DP_ThirdUser
		set Token = null
		where DPUid = #dpUid#
			and Type = #type#
	  
	     
	        UPDATE TO_FlashSaleWannaJoinUser SET Status = #status# WHERE Status = 0 AND ReceiverID = #receiverId#
	         AND BeginDate > DATE_ADD(NOW(), INTERVAL 5 MINUTE) AND BeginDate < DATE_ADD(NOW(), INTERVAL (#beforeMinutes#+5) MINUTE)
	      
	
		UPDATE DP_GroupNote
		SET VerifyStatus = #verifyStatus#
		WHERE UserID = #userId#
	
		UPDATE DP_GroupNote
		SET NoteTitle = #noteTitle#,
		  UpdateTime = NOW(),
		  LastIP = #lastIP#,
		  HasPic = #hasPic#,
		  GroupNoteTypeID = #noteTypeId#,
		  VerifyStatus = #verifyStatus#
		WHERE NoteID = #noteId#;
	
		UPDATE DP_GroupNote
		SET FollowNoteNo = #count#,
		  lastUser = #userId#,
		  LastNoteTime = NOW()
		WHERE NoteID = #noteId#
	
		update DP_GroupNote
		set IsGood=#note.isGood#
		where NoteID=#note.noteId#
    
		update DP_GroupNote
		set IsTop=#note.isTop#
		where NoteID=#note.noteId#
    
		update DP_GroupNote
		set IsHighLight=#note.isHighLight#
		where NoteID=#note.noteId#
    
		update DP_GroupNote
		set IsLocked=#note.isLocked#
		where NoteID=#note.noteId#
    
		update DP_GroupNote
		set GroupID=#note.groupId#,
		UserID=#note.userId#,
		NoteTitle=#note.noteTitle#,
		Hits=#note.hits#,
		AddTime=#note.addTime#,
		UpdateTime=#note.updateTime#,
		LastIP=#note.lastIP#,
		LastUser=#note.lastUser#,
		LastNoteTime=#note.lastNoteTime#,
		FollowNoteNo=#note.followNoteNo#,
		NoteCategory=#note.noteCategory#,
		SurveyTotal=#note.surveyTotal#,
		HasPic=#note.hasPic#,
		IsHighLight=#note.isHighLight#,
		GroupNoteTypeID=#note.groupNoteTypeId#,
		DCashNumber=#note.dCashNumber#,
		IsGood=#note.isGood#,
		IsLocked=#note.isLocked#,
		IsTop=#note.isTop#,
		VerifyStatus=#note.verifyStatus#
		where NoteID=#note.noteId#
    
		update DP_GroupNote
		set VerifyStatus = 4
		where NoteID = #noteId#
    
		update DP_GroupNote
		set VerifyStatus=#verifyStatus#, Status = 1
		where NoteID=#noteId#
    
		UPDATE DP_GroupNote
		SET DCashNumber = DCashNumber + #dCashNumber#
		WHERE NoteID = #noteId#
	
		UPDATE DP_GroupNote
		SET GroupNoteTypeID = #groupNoteTypeId#,
		  IfMore = 0
		WHERE NoteID = #noteId#
	
		UPDATE DP_GroupNote
		SET GroupNoteTypeID = 0,
		  IfMore = 1
		WHERE NoteID = #noteId#
	
		UPDATE DP_GroupNote
		SET GroupNoteTypeID = #newTypeId#
		WHERE GroupID = #groupId#
		    AND GroupNoteTypeID = #oldTypeId#
	
		UPDATE DP_GroupNote
		SET Hits = Hits + #increase#
		WHERE NoteID = #noteId#
    
		UPDATE DP_GroupNoteExtInfo
		SET NoteScore = NoteScore + #score#
		WHERE NoteID = #noteId#
	
		UPDATE
			DP_GroupNoteExtInfo
		SET
			Original=#original#,
			GoldTotal=GoldTotal + #goldTotal#,
			GoldManual=GoldManual + #goldManual#
		WHERE
			NoteID = #noteId#
	
		UPDATE DP_UserGroupInfo
		SET GroupUserScore = #score#
		WHERE UserID = #userId#
	
		
		update DP_Event
		set
		EventPower=#eventPower#,
		updateDate=NOW()
		where EventID=#eventId#
		
	
		UPDATE
			DP_Event
		SET
			EventPower=#eventPower#,
			AuditingDate=NOW()
		WHERE
			EventID=#eventId#
	
		UPDATE
			GP_GroupCategory
		SET
			tag = #tag#
		WHERE
			NavID = #navId# ;
	
	UPDATE 
		DP_GroupTypeApply
	SET 
		GroupTypeFlag=#status# , AdminID=#loginId#
	WHERE 
		ApplyID=#applyId#
	
	UPDATE 
		GP_GroupCategory
	SET 
		CategoryID=#categoryId#		
	WHERE
		GroupID=#groupId#
	
    	WHERE QAApplyInfoID = #applyId#
    
    	UPDATE
    		GP_QAUserInfo
    	SET
    		UpdateTime=#starUserInfoCommon.updateTime#,
    		ApplyType=#starUserInfoCommon.applyType#,
    		ShopIDs=#starUserInfoCommon.shopIds#,
    		Age=#starUserInfoCommon.age#,
    		MobilePhone=#starUserInfoCommon.mobilePhone#,
    		Position=#starUserInfoCommon.position#,
    		WorkTime=#starUserInfoCommon.workTime#,
    		CustomRadio=#starUserInfoCommon.customRadio#,
    		CustomCheck=#starUserInfoCommon.customCheck#,
    		CustomInputSet=#starUserInfoCommon.customInputSet#,
    		CustomInput=#starUserInfoCommon.customInput#
    	WHERE
    		GroupSetID=#starUserInfoCommon.groupSetId# AND UserID=#starUserInfoCommon.userId#
    
		UPDATE DP_GroupUser
		SET
			RoleName = #roleName#
		WHERE
			GroupID = #groupId# AND UserID = #userId#;
	
		UPDATE 
			GP_NoteEventRecommend 
		SET
			Title = #noteEvent.title#,
			Link = #noteEvent.link#,
			PicUrl = #noteEvent.picUrl#,
			Sort = #noteEvent.sort#,
			UpdateTime = NOW()
		WHERE
			ID = #noteEvent.id#
	
		UPDATE 
			GP_NoteEventRecommend 
		SET
			Sort = #sort#,
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE 
			GP_NoteEventRecommend 
		SET
			Status = #status#
		WHERE
			ID = #id#
	
		UPDATE
			DP_RandomDCashActivity
		SET
			Status=0
		WHERE
			ActivityID=#activityId#
	
		UPDATE
			DP_RandomDCashActivity
		SET
			BeginDate=#randomDCashActivity.beginDate#,
			EndDate=#randomDCashActivity.endDate#,
			DCashNumber=#randomDCashActivity.dCashNumber#,
			ProbabilityNumerator=#randomDCashActivity.probabilityNumerator#,
			ProbabilityDenominator=#randomDCashActivity.probabilityDenominator#,
			UpdateDate=#randomDCashActivity.updateDate#
		WHERE
			ActivityID=#randomDCashActivity.activityId#
	
	
		UPDATE TO_GiftCardOrderNumber n
		SET n.Status = #newStatus#
		WHERE n.GiftCardOrderNumberID = #giftCardOrderNumberId# AND n.Status = #oldStatus#
	
	
	
		UPDATE TO_GiftCardOrderNumber n
		SET n.ReceiverID	= #receiverId#, 
			n.AccountID 	= #accountId#
		WHERE n.GiftCardOrderNumberID = #giftCardOrderNumberId# AND n.Status = 1
	
	
		UPDATE
			GP_EventFollowNote
		SET
			NoteBody = #noteBody#,
			UpdateDate = NOW(),
			IP = #ip#,
			Status = #status#
		WHERE
			FollowNoteID = #followNoteId#
	
		UPDATE
			GP_EventFollowNote
		SET
			Status = #status#
		WHERE
			FollowNoteID = #followNoteId#
	
		UPDATE
			GP_EventFollowNoteLast
		SET
			LastUserID = #lastUserId#,
			LastUserNickName = #lastUserNickName#,
			LastNoteTime = #lastNoteTime#,
			FollowNoteID = #followNoteId#,
			NoteCount = #noteCount#
		WHERE
			EventID = #eventId#
	
		UPDATE
			GP_EventFollowNoteLast
		SET
			NoteCount = #noteCount#
		WHERE
			EventID = #eventId#
	
		UPDATE DP_Group
		SET GroupName=#groupName#,
			GroupProperty=#groupProperty#, 
			GroupIntro=#groupIntro#,
			CityID=#cityId#,
			SearchKeyWord=CONCAT(#groupName#,'|',#groupIntro#)
		WHERE GroupID=#groupId#
	
		UPDATE DP_Group
		SET NoteTotal = NoteTotal + #size#
		WHERE GroupID=#groupId#
	
		UPDATE DP_Group
		SET MainNoteTotal = MainNoteTotal+#size#
		WHERE GroupID=#groupId#
	
		UPDATE DP_Group
		SET UserTotal = UserTotal+#size#
		WHERE GroupID=#groupId#
	
		UPDATE DP_Group
		SET GroupLogo=#groupLogo#
		WHERE GroupID=#groupId#
	
		UPDATE DP_Group
		SET Status=4
		WHERE GroupID=#groupId#
	
    	UPDATE DP_GroupMedalDetail
    	SET MedalID=#medalId#
    	WHERE GroupID=#groupId#
   	
		UPDATE
			GP_GroupNoteHideContent
		SET
			HideContent=#hideContent#,
			Status=#status#
		WHERE
			NoteID=#noteId#
	
    	UPDATE DP_GroupNoteType
    	SET GroupNoteTypeName=#typeName# 
    	WHERE GroupNoteTypeID=#noteTypeId#
    
    	UPDATE DP_GroupNoteType
    	SET OrderNumber=#orderNumber# 
    	WHERE GroupNoteTypeID=#noteTypeId#
    
		UPDATE DP_GroupUserScoreSum
		SET Score = #score#
		WHERE UserID = #userId# AND ConfigID = #configId#
	
		UPDATE
			GP_Group_AdminPower
		SET
			GroupIDs = #groupIds#
		WHERE
			AdminID = #loginId#
	
		UPDATE DP_GroupFollowNote
		SET
			NoteClass = #noteClass#,
			VerifyStatus = #verifyStatus#
		WHERE
			FollowNoteID = #followNoteId#
	
		UPDATE
			DP_GroupFollowNote
		SET
			DCashNumber=DCashNumber+#dCashNum#
		WHERE
			FollowNoteID=#followNoteId#
	
	UPDATE 
		DP_GroupFollowNote
	SET
		VerifyStatus = #verifyStatus#
	WHERE
		FollowNoteID=#followNoteId#
		
		AND NoteID=#noteId#	
						
	
		UPDATE
			DP_GroupHonorTitle 
		SET
			TitleName=#groupHonorTitle.titleName#,
			TitlePicPath=#groupHonorTitle.titlePicPath#,
			TitleLink=#groupHonorTitle.titleLink#,
			BeginDate=#groupHonorTitle.beginDate#,
			EndDate=#groupHonorTitle.endDate#
		WHERE
			TitleID=#groupHonorTitle.titleId#
	
		UPDATE DP_GroupSurvey
		SET
			VoterPrivilegeCategory = #category# ,
			VoterPrivilegeScore = #require#
		WHERE
			NoteID = #noteId#
	
		UPDATE DP_GroupSurvey
		SET
			Title = #title#
		WHERE
			SurveyID = #surveyId#
	
		
			UPDATE TO_GiftCardOrder o
			SET o.Status = #status#
			WHERE o.PayOrderID = #payOrderId#
		
	
		
			UPDATE TO_GiftCardOrder o
			SET o.DeliverTimes = o.DeliverTimes + 1
			WHERE o.GiftCardOrderID = #giftCardOrderId#
		
	
		UPDATE DP_GroupFollowNote
		SET VerifyStatus = #verifyStatus#,
		  UpdateTime = #now#,
		  LastIP = #lastIP#
		WHERE FollowNoteID = #followNoteId#
		AND NoteID = #noteId#;
	
		UPDATE DP_GroupFollowNote
		SET VerifyStatus = #verifyStatus#
		WHERE UserID = #userId#
	
		
		update
		DP_GroupFollowNote
		set
		VerifyStatus=4
		where FollowNoteID=#followNoteId#
		
	
		UPDATE DP_GroupFollowNote
		SET DCashNumber = DCashNumber + #dCashNumber#
		WHERE FollowNoteID = #followNoteId#
		AND NoteID = #noteId#
	
		
		update DP_GroupFollowNote
		set IfQuote = #ifQuote#
		where FollowNoteID = #followNoteId#
		and NoteID = #noteId#
		
	
		update DP_GroupFollowNote
		set VerifyStatus=#verifyStatus#
		where NoteID = #noteId# AND FollowNoteID=#followNoteId#
    
		UPDATE
			GP_FollowNoteExtInfo
		SET
			Excellent=#excellent#,
			GoldTotal=GoldTotal+#goldTotal#,
			GoldManual=GoldManual+#goldManual#
		WHERE
			FollowNoteId=#followNoteId#
	
		UPDATE
			GP_GroupPic
		SET
			PicType=#groupPic.picType#,
			BizID=#groupPic.bizId#,
			ReferID=#groupPic.referId#,
			Status=#groupPic.status#,
			UpdateTime=NOW()
		WHERE
			PicID=#groupPic.picId#
	
		UPDATE
			GP_GroupPic
		SET
			Status=#status#
		WHERE
			PicID=#picId#
	
		UPDATE
			GP_QANoteExtend
		SET
			FollowNoteID=#qaNoteExtend.followNoteId#,
			QAStatus=#qaNoteExtend.qaStatus#,
			UpdateTime=#qaNoteExtend.updateTime#
		WHERE
			NoteID=#qaNoteExtend.noteId#
	
		UPDATE
			GP_QAUserApplyInfo
		SET
			UpdateTime = #qaUserApplyInfo.updateTime#,
			ApplyType = #qaUserApplyInfo.applyType#,
			ShopIDs = #qaUserApplyInfo.shopIds#,
			Age = #qaUserApplyInfo.age#,
			MobilePhone = #qaUserApplyInfo.mobilePhone#,
			Position = #qaUserApplyInfo.position#,
			WorkTime = #qaUserApplyInfo.workTime#,
			CustomRadio = #qaUserApplyInfo.customRadio#,
			CustomCheck = #qaUserApplyInfo.customCheck#,
			CustomInputSet = #qaUserApplyInfo.customInputSet#,
			CustomInput = #qaUserApplyInfo.customInput#,
			Status = #qaUserApplyInfo.status#,
			AuditTime = #qaUserApplyInfo.auditTime#,
			CancelTime = #qaUserApplyInfo.cancelTime#,
			CancelReason = #qaUserApplyInfo.cancelReason#
		WHERE
			GroupSetID = #qaUserApplyInfo.groupSetId# AND UserID = #qaUserApplyInfo.userId#
	
    	
			UPDATE DP_GroupTypeApply 
			SET 
				AdminID=#adminId#,
				LastDate=NOW(),
				GroupTypeFlag=#status# 
			WHERE ApplyID=#applyId#
    	
    
		UPDATE
			GP_UserGold
		SET
			Gold=Gold+#increaseNum#,
			UpdateTime=NOW()
		WHERE
			UserID=#userId# AND GroupSetID=#groupSetId#
	
		
		update
			GP_WinningUserInfo
		set
			RewardID=#userInfo.rewardId#,
			AcceptDate=#userInfo.acceptDate#,
			Status=#userInfo.status#
		where
			WinningUserInfoID=#userInfo.winningUserInfoId# and Status=1
		
	
		
		update
			GP_WinningActivity
		set
			GrantNum=#grantNum#
		where
			ActivityID=#activityId#
		
	
		
		update
			GP_WinningActivity
		set
			GrantNum=GrantNum+#increaseNum#
		where
			ActivityID=#activityId#
		
	
		
		update
			GP_WinningUserInfo
		set
			RewardID=#winningUserInfo.rewardId#,
			AcceptDate=#winningUserInfo.acceptDate#,
			Status=#winningUserInfo.status#
		where
			WinningUserInfoID=#winningUserInfo.winningUserInfoId#
		
	
        create #ddl#
    
		
			UPDATE TO_GiftCardContact c
			SET c.DeliverTimes = c.DeliverTimes + 1
			WHERE c.GiftCardContactID = #contactId#
		
	
		
			UPDATE TO_GiftCardContact
			SET Status = #status#
			WHERE GiftCardContactID = #giftCardContactId#
		
	
		
			UPDATE TO_GiftCardOrder o
			SET o.Status = #status#
			WHERE o.GiftCardOrderID = #giftCardOrderId#
		
	
		UPDATE MP_Geocoding
		SET GLat=#lat#, GLng=#lng#,
		UpdateTime=Now() WHERE ID = #poiId#
			
		UPDATE DP_GroupSurvey
		SET EndDate = #endDate#
		WHERE NoteID = #noteId#
	
		UPDATE DP_GroupUser
		SET RoleID = #roleId#, GroupUserPower = #roleId#
		WHERE GroupID = #groupId# and UserID = #userId#
	
    	UPDATE DP_GroupUser
    	SET NoteTotal = NoteTotal + #size#
		WHERE GroupID = #groupId# and UserID = #userId#
    
    	UPDATE DP_GroupUser
    	SET roleId = 5, GroupUserPower = 5
		WHERE GroupID=#groupId# and roleId=1
    
		UPDATE
			GP_WinningUserInfo
		SET
			Status = #rewardStatus#
		WHERE
			WinningUserInfoID=#winningUserInfoId#
	
		UPDATE
			GP_BackCity
		SET
			Status=#status#
		WHERE
			CityID=#cityId#
		
		UPDATE DP_EventRecommend 
		SET
			Sort = #sort#	
		WHERE
			EventID = #eventId# AND CityID = #cityId#;
	
    	UPDATE DP_GroupUser
		SET WeeklyNoteCount = #weeklyNoteCount#
		WHERE UserID = #userId# AND GroupID = #groupId#
    
    	UPDATE DP_GroupUser
		SET MonthlyNoteCount = #monthlyNoteCount#
		WHERE UserID = #userId# AND GroupID = #groupId#
    
		UPDATE DP_GroupUser
		SET NoteTotal = #noteTotal# + NoteTotal
		WHERE UserID = #userId# AND GroupID = #groupId#
		
    
		UPDATE 
			DP_GroupUser
		SET
			WeeklyNoteCount = 0,
			MonthlyNoteCount = 0,
			NoteTotal = 0
		WHERE 
			GroupID = #groupId#
	
    	
		UPDATE 
			DP_GroupUser U
	    JOIN 
	    (
	        SELECT 
	        	UserID, 
	        	COUNT(*) AS NoteTotal
	        FROM 
	        	DP_GroupNote 
	        WHERE 
	        	VerifyStatus <> 3 
	        	AND VerifyStatus <> 4 
	        	AND GroupID = #groupId#
	        GROUP BY 
	        	UserID
	    ) AS A 
	    ON 
	    	A.UserID = U.UserID 
	    SET 
	    	U.NoteTotal = A.NoteTotal
	    WHERE 
			GroupID = #groupId#
	    
    
		
			UPDATE TO_GiftCardContact
			SET Status = #status#
			WHERE GiftCardContactID = #giftCardContactId#
		
	
		
		update DP_Event
		set 
		EventTitle=#event.eventTitle#,
		BeginDate=#event.beginDate#,
		EndDate=#event.endDate#,
		ShopID=#event.shopId#,
		Address=#event.address#,
		Cost=#event.cost#,
		EventTypeID=#event.eventTypeId#,
		EventBody=#event.eventBody#,
		GroupID=#event.groupId#,
		CityID=#event.cityId#,
		ParticipantLimit=#event.participantLimit#,
		UpdateDate=#event.updateDate#,
		PhoneNoFlag=#event.phoneNoFlag#,
		EventPower=#event.eventPower#,
		ExtFlag=#event.extFlag#,
		ExtendOption1=#event.extendOption1#,
		ExtendOption2=#event.extendOption2#,
		ExtendOption3=#event.extendOption3#
		where EventID=#event.eventId#
		
	
		
		update DP_Event
		set
		EventPower=#event.eventPower#,
		updateDate=#event.updateDate#
		where EventID=#event.eventId#
		
	
		
		update DP_Event
		set
		EventPower=#event.eventPower#,
		CancelDate=#event.cancelDate#
		where EventID=#event.eventId#
		
	
		
		update DP_Event
		set
		SignUpFlag=#event.signUpFlag#,
		updateDate=#event.updateDate#
		where EventID=#event.eventId#
		
	
		
		update DP_Event
		set
		IsShowInGroup=#event.showInGroup#,
		updateDate=#event.updateDate#
		where EventID=#event.eventId#
		
	
		
		update DP_Event
		set
		UserCount=#event.userCount#,
		updateDate=#event.updateDate#
		where EventID=#event.eventId#
		
	
		
		update DP_Event
		set
		NoteCount=#event.noteCount#,
		updateDate=#event.updateDate#
		where EventID=#event.eventId#
		
	
		UPDATE
			GP_QAUserData
		SET
			QAScore=#qaUserData.qaScore#,
			TotalReply=#qaUserData.totalReply#,
			TotalQuickReply=#qaUserData.totalQuickReply#,
			TotalBestReply=#qaUserData.totalBestReply#,
			updateTime=#qaUserData.updateTime#
		WHERE
			GroupSetID=#qaUserData.groupSetId# AND UserID=#qaUserData.userId#
	
		UPDATE DP_Group SET GroupProperty = 3 WHERE GroupID = #groupId#;
	
		UPDATE DP_GroupUser SET RoleID = 10 , GroupUserPower = 10 WHERE GroupID = #groupId# AND RoleID = 15;
	
		UPDATE DP_Group SET GroupProperty = 1 WHERE GroupID = #groupId#;
	
		UPDATE DP_Group SET MainNoteTotal = MainNoteTotal+#increament# WHERE GroupID = #groupId#;
	
		UPDATE DP_Group SET Status = #status# WHERE GroupID = #groupId#;
 
		WHERE 
			NoteID = #noteId#
	
		UPDATE
			GP_QAStarInfo
		SET
			GroupSetID=#starInfo.groupSetId#,
			UserID=#starInfo.userId#,
			StarType=#starInfo.starType#,
			Issue=#starInfo.issue#,
			Comment=#starInfo.comment#,
			RecommendNoteIds=#starInfo.recommendNoteIds#,
			RecommendPicURLs=#starInfo.recommendPicUrls#,
			UpdateTime=#starInfo.updateTime#
		WHERE
			QAStarID=#starInfo.qaStarId#
	
		WHERE
			HotNoteID = #hotNoteId#;
	;
	
		UPDATE DP_GroupNote
		SET
			STATUS = #status#,
			VerifyStatus = #verifyStatus#
		WHERE
			NoteID IN ($noteIdStr$)
	
		UPDATE
			DP_GroupNote
		SET
			NoteCategory=#noteCategory#
		WHERE
			NoteID=#noteId#
	
		UPDATE
			DP_GroupNote
		SET
			IsHighLight=#isHighLight#
		WHERE
			NoteID=#noteId#
	
		UPDATE
			DP_GroupNote
		SET
			GroupID=#note.groupId#,
			GroupNoteTypeID=#note.groupNoteTypeId#
		WHERE
			NoteID=#note.noteId#
	
		UPDATE
			DP_GroupNote
		SET
			VerifyStatus=#verifyStatus#
		WHERE
			NoteID=#noteId#
	
		UPDATE
			DP_GroupNote
		SET
			DCashNumber=DCashNumber+#dCashNum#
		WHERE
			NoteID=#noteId#
	
		UPDATE GP_Category
		SET
			CategoryName = #categoryName#,
			OrderID = #orderId#
		WHERE
			CategoryID = #categoryId#;
	
		UPDATE DP_Group
		SET WeeklyNoteCount = #weeklyNoteCount#
	    WHERE GroupID = #groupId#
	
	    UPDATE 
	    	DP_Group
	    SET 
	    	SearchKeyword = CONCAT(GroupName,'|',GroupIntro),
	    	MainNoteTotal = #mainNoteTotal#, 
	    	NoteTotal = #noteTotal#,
	    	UserTotal = #userTotal#
	    WHERE 
	    	GroupID = #groupId#
	
		UPDATE 
			DP_UserGroupInfo 
		SET 
			OldGroupUserScore = GroupUserScore 
		WHERE
			UserID = #userId#
			
	
		UPDATE MP_Geocoding
		SET GLat = #lat#,
		GLng = #lng#,
		UpdateTime=Now()
		WHERE ID = #poiId#
    
        update mc_table_info
        set
            schema_name        = #schema_name#,
            table_name         = #table_name#,
            db_name            = #db_name#,
            storage_type       = #storage_type#,
            table_owner        = #table_owner#,
            table_desc         = #table_desc#,
            table_size         = #table_size#,
            refresh_cycle      = #refresh_cycle#,
            refresh_type       = #refresh_type#,
            table_access_level = #table_access_level#,
            table_access_desc  = #table_access_desc#,
            is_validate        = #status#,
            update_time        = now()
        where table_id = #table_id#
    
    
    	 update alarm_config set  duration=#duration#,max=#max#,alarm_time=#alarmTime#,messageMax=#messageMax#,messageAlarmTime=#messageAlarmTime#,messageEnable=#messageEnable# where id=#id#;
    
    
    
    	 update jrobin_dynamic_key set value=#sumValue#, status=#status#
    	 where datasource_id=#dsID# and key_name=#keyName# and key_value=#keyValue# and status=0
    
    
    
    	 update jrobin_key set key_value=#keyValue#
    	 where id=#id#
    
    
    
    	update loginfo set execution_num=?, value_avg=?
    	where id=?
    
    
    
    	 update jrobin_datasource set name=#name#, key1=#key1#,key2=#key2#,key3=#key3#,
    	 key4=#key4#,data_type=#dataType#,host_names=#hostNames#,step=#step#,rrd_path=#rrdPath#,timespan=#timespan#,data_choice=#dataChoice#
    	 where id=#id#
    
    
     
    	 update jrobin_datasource set is_active=? where id=?
    
    
     
    	 update jrobin_datasource set process_id=? where id=?
    
    
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
    
    	 update jrobin_rra set name=#name#, cf=#cf#,
    	 xff=#xff#,steps=#steps#,rows=#rows#,timespan=#timespan#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef_item set type=#type#,cdef_id=#cdefId#,value=#value#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource_item set datasource_type=#datasourceType#, heart_beat=#heartBeat#,max_value=#maxValue#,min_value=#minValue#,
    	 datasource_id=#datasourceId# ,internal_name=#internalName# where id=#id#
    
    
    
    	 update consumer_rule set serviceNameId=#serviceNameId#,logType=#logType#,
    	 key1=#key1#,key2=#key2#,key3=#key3#,key4=#key4#,
    	 projectId=#projectId#,hostIp=#hostIp#,modifyTime=#modifyTime#,consumeInterval=#consumeInterval#
    	 where id=#id#
    
    
    
    	 update alarm_config set  duration=#duration#,max=#max#,alarm_time=#alarmTime#,messageMax=#messageMax#,messageAlarmTime=#messageAlarmTime#,messageEnable=#messageEnable# where id=#id#;
    
    
    
    	 update jrobin_cdef set name=#name#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef set name=#name#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource_item set datasource_type=#datasourceType#, heart_beat=#heartBeat#,max_value=#maxValue#,min_value=#minValue#,
    	 datasource_id=#datasourceId# ,internal_name=#internalName# where id=#id#
    
    
    
    	 update jrobin_graph set text=#text#,name=#name#,title=#title#,image_format_id=#imageFormateId#,height=#height#,
    	 width=#width#,upper_limit=#upperLimit#,lower_limit=#lowerLimit#,vertical_label=#verticalLabel#,grid_step=#gridStep#,label_factor=#labelFactor# where id=#id#
    
    
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
		UPDATE
		HUI_CouponInventory
		SET
		MaxInventory=#entity.maxInventory#,
		SoldQuantity=#entity.soldQuantity#,
		CouponOfferId=#entity.couponOfferId#,
        LastUpdateTime=#entity.lastUpdateTime#,
        Version=#entity.version#+1
		WHERE
		CouponInventoryId=#entity.id# AND Version = #entity.version#
	
		UPDATE
		HUI_RedeemRule
		SET
		MetricItemId=#entity.metricItemId#,
		CouponOfferId=#entity.couponOffer.id#,
		RedeemStrategyExpression=#entity.redeemStrategyExpression#,
		RedeemConditionExpression=#entity.redeemConditionExpression#,
		RedeemStrategyId=#entity.redeemStrategyId#,
        LastUpdateTime=#entity.lastUpdateTime#
		WHERE
		RedeemRuleId=#entity.id#
	
        UPDATE DP_HotelInvoice SET
        orderPrice = #hotelInvoice.orderPrice#,
        shopName = #hotelInvoice.shopName#,
        roomName = #hotelInvoice.roomName#,
        checkinDate = #hotelInvoice.checkinDate#,
        checkoutDate = #hotelInvoice.checkoutDate#,
        roomNum = #hotelInvoice.roomNum#,
        recipient = #hotelInvoice.recipient#,
        name = #hotelInvoice.name#,
        contactPhone = #hotelInvoice.contactPhone#,
        address = #hotelInvoice.address#,
        postCode = #hotelInvoice.postCode#,
        updateTime = NOW(),
        WHERE
        id = #hotelInvoice.invoiceID#;
    
        UPDATE DP_HotelCscOrderCase SET
           CaseId= #hotelCscOrderCase.caseId#,
	       ConTactName= #hotelCscOrderCase.conTactName#,
	       ConTactPhone=  #hotelCscOrderCase.conTactPhone#,
	       UpdateTime= NOW()
        WHERE
        	Id = #hotelCscOrderCase.id#
    
    
    	 update jrobin_graph set text=#text#,name=#name#,title=#title#,image_format_id=#imageFormateId#,height=#height#,
    	 width=#width#,upper_limit=#upperLimit#,lower_limit=#lowerLimit#,vertical_label=#verticalLabel#,grid_step=#gridStep#,label_factor=#labelFactor# where id=#id#
    
    
    
    	 update jrobin_cdef_item set type=#type#,cdef_id=#cdefId#,value=#value#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource_item set datasource_type=#datasourceType#, heart_beat=#heartBeat#,max_value=#maxValue#,min_value=#minValue#,
    	 datasource_id=#datasourceId# ,internal_name=#internalName# where id=#id#
    
    
    
    	update loginfo set execution_num=?, value_avg=?
    	where id=?
    
    
    
    	update loginfo set execution_num=?, value_avg=?
    	where id=?
    
    
    
    	 update jrobin_key set key_value=#keyValue#,des=#des#
    	 where id=#id#
    
    
    
    	 update jrobin_color set hex=#hex#
    	 where id=#id#
    
    
    
    	 update alarm_config set  duration=#duration#,max=#max#,alarm_time=#alarmTime# where id=#id#;
    
    
    
    	 update jrobin_dynamic_key set value=#sumValue#, status=#status#
    	 where datasource_id=#dsID# and key_name=#keyName# and key_value=#keyValue# and status=0
    
    
    
    	 update jrobin_key set key_value=#keyValue#,des=#des#
    	 where id=#id#
    
    
    
    	 update alarm_config set  duration=#duration#,max=#max#,alarm_time=#alarmTime# where id=#id#;
    
    
    
    	 update jrobin_cdef set name=#name#
    	 where id=#id#
    
    
    
    	 update jrobin_color set hex=#hex#
    	 where id=#id#
    
    
    
    	 update jrobin_graph_item set graph_id=#graphId#,datasource_id=#datasourceId#,color_id=#colorId#,
    	 alpha=#alpha#,graph_item_type=#graphItemType#,cdef_id=#cdefId#,consolidation_id=#consolidationId#,
    	 text_format=#textFormat#,value=#value#,sequence=#sequence# where id=#id#
    
    
    
    	 update jrobin_datasource set name=#name#, key1=#key1#,key2=#key2#,key3=#key3#,
    	 key4=#key4#,data_type=#dataType#,host_names=#hostNames#,step=#step#,rrd_path=#rrdPath#,timespan=#timespan#,data_choice=#dataChoice#
    	 where id=#id#
    
    
     
    	 update jrobin_datasource set is_active=? where id=?
    
    
     
    	 update jrobin_datasource set process_id=? where id=?
    
    
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
    
    	 update consumer_rule set serviceNameId=#serviceNameId#,logType=#logType#,
    	 key1=#key1#,key2=#key2#,key3=#key3#,key4=#key4#,
    	 projectId=#projectId#,hostIp=#hostIp#,modifyTime=#modifyTime#,consumeInterval=#consumeInterval#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef_item set type=#type#,cdef_id=#cdefId#,value=#value#
    	 where id=#id#
    
    
    
    	 update consumer_rule set serviceNameId=#serviceNameId#,logType=#logType#,
    	 key1=#key1#,key2=#key2#,key3=#key3#,key4=#key4#,
    	 projectId=#projectId#,hostIp=#hostIp#,modifyTime=#modifyTime#,consumeInterval=#consumeInterval#
    	 where id=#id#
    
    
    
    	 update alarm_config set  duration=#duration#,max=#max#,alarm_time=#alarmTime# where id=#id#;
    
    
    
    	 update jrobin_graph set text=#text#,name=#name#,title=#title#,image_format_id=#imageFormateId#,height=#height#,
    	 width=#width#,upper_limit=#upperLimit#,lower_limit=#lowerLimit#,vertical_label=#verticalLabel#,grid_step=#gridStep#,label_factor=#labelFactor# where id=#id#
    
    
    
    	 update jrobin_key set key_value=#keyValue#,des=#des#
    	 where id=#id#
    
    
    
    	 update jrobin_graph set text=#text#,name=#name#,title=#title#,image_format_id=#imageFormateId#,height=#height#,
    	 width=#width#,upper_limit=#upperLimit#,lower_limit=#lowerLimit#,vertical_label=#verticalLabel#,grid_step=#gridStep#,label_factor=#labelFactor# where id=#id#
    
    
    
    	 update jrobin_graph_item set graph_id=#graphId#,datasource_id=#datasourceId#,color_id=#colorId#,
    	 alpha=#alpha#,graph_item_type=#graphItemType#,cdef_id=#cdefId#,consolidation_id=#consolidationId#,
    	 text_format=#textFormat#,value=#value#,sequence=#sequence# where id=#id#
    
    
    
    	 update alarm_config set  duration=#duration#,max=#max#,alarm_time=#alarmTime#,messageMax=#messageMax#,messageAlarmTime=#messageAlarmTime#,messageEnable=#messageEnable# where id=#id#;
    
    
    
    	 update jrobin_color set hex=#hex#
    	 where id=#id#
    
    
    
    	 update jrobin_dynamic_key set value=#sumValue#, status=#status#
    	 where datasource_id=#dsID# and key_name=#keyName# and key_value=#keyValue# and status=0
    
    
    
    	 update jrobin_graph_item set graph_id=#graphId#,datasource_id=#datasourceId#,color_id=#colorId#,
    	 alpha=#alpha#,graph_item_type=#graphItemType#,cdef_id=#cdefId#,consolidation_id=#consolidationId#,
    	 text_format=#textFormat#,value=#value#,sequence=#sequence# where id=#id#
    
    
		UPDATE
		HUI_CouponOfferApplicableShop
		SET
		CouponOfferId=#entity.couponOffer.id#,
		ShopId=#entity.shopId#,
        LastUpdateTime=#entity.lastUpdateTime#
		WHERE
		CouponOfferApplicableShopId=#entity.id#
	
    
    	 update jrobin_rra set name=#name#, cf=#cf#,
    	 xff=#xff#,steps=#steps#,rows=#rows#,timespan=#timespan#
    	 where id=#id#
    
    
    
    	 update jrobin_key set key_value=#keyValue#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource_item set datasource_type=#datasourceType#, heart_beat=#heartBeat#,max_value=#maxValue#,min_value=#minValue#,
    	 datasource_id=#datasourceId# ,internal_name=#internalName# where id=#id#
    
    
    
    	 update jrobin_datasource set name=#name#, key1=#key1#,key2=#key2#,key3=#key3#,
    	 key4=#key4#,data_type=#dataType#,host_names=#hostNames#,step=#step#,rrd_path=#rrdPath#,timespan=#timespan#,data_choice=#dataChoice#
    	 where id=#id#
    
    
     
    	 update jrobin_datasource set is_active=? where id=?
    
    
     
    	 update jrobin_datasource set process_id=? where id=?
    
    
    
    	 update jrobin_rra set name=#name#, cf=#cf#,
    	 xff=#xff#,steps=#steps#,rows=#rows#,timespan=#timespan#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource_item set datasource_type=#datasourceType#, heart_beat=#heartBeat#,max_value=#maxValue#,min_value=#minValue#,
    	 datasource_id=#datasourceId# ,internal_name=#internalName# where id=#id#
    
    
    
    	 update jrobin_rra set name=#name#, cf=#cf#,
    	 xff=#xff#,steps=#steps#,rows=#rows#,timespan=#timespan#
    	 where id=#id#
    
    
		UPDATE
		HUI_CouponOffer
		SET
		Title=#entity.title#,
		Description=#entity.description#,
        LastUpdateTime=#entity.lastUpdateTime#,
        Version=#entity.version#+1
		WHERE
		CouponOfferId=#entity.id# AND Version = #entity.version#
	
        update DP_HotelOrder set
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
        NeedInvoice = #hotelOrder.needInvoice#,
        InvoiceId = #hotelOrder.invoiceId#
        where
        otaId = #hotelOrder.otaId#
        AND
        OTAOrderID = #hotelOrder.originOrderId#
    
        update DP_HotelOrder set
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
        where
        OrderID = #hotelOrder.orderId#
    
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource set name=#name#, key1=#key1#,key2=#key2#,key3=#key3#,
    	 key4=#key4#,data_type=#dataType#,host_names=#hostNames#,step=#step#,rrd_path=#rrdPath#,timespan=#timespan#,data_choice=#dataChoice#
    	 where id=#id#
    
    
     
    	 update jrobin_datasource set is_active=? where id=?
    
    
     
    	 update jrobin_datasource set process_id=? where id=?
    
    
    
    	 update jrobin_graph_item set graph_id=#graphId#,datasource_id=#datasourceId#,color_id=#colorId#,
    	 alpha=#alpha#,graph_item_type=#graphItemType#,cdef_id=#cdefId#,consolidation_id=#consolidationId#,
    	 text_format=#textFormat#,value=#value#,sequence=#sequence# where id=#id#
    
    
    
    	 update jrobin_key set key_value=#keyValue#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef set name=#name#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef set name=#name#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef_item set type=#type#,cdef_id=#cdefId#,value=#value#
    	 where id=#id#
    
    
    
    	 update jrobin_color set hex=#hex#
    	 where id=#id#
    
    
    
    	 update jrobin_graph set text=#text#,name=#name#,title=#title#,image_format_id=#imageFormateId#,height=#height#,
    	 width=#width#,upper_limit=#upperLimit#,lower_limit=#lowerLimit#,vertical_label=#verticalLabel#,grid_step=#gridStep#,label_factor=#labelFactor# where id=#id#
    
    
    
    	 update jrobin_graph_item set graph_id=#graphId#,datasource_id=#datasourceId#,color_id=#colorId#,
    	 alpha=#alpha#,graph_item_type=#graphItemType#,cdef_id=#cdefId#,consolidation_id=#consolidationId#,
    	 text_format=#textFormat#,value=#value#,sequence=#sequence# where id=#id#
    
    
    
    	 update jrobin_dynamic_key set value=#sumValue#, status=#status#
    	 where datasource_id=#dsID# and key_name=#keyName# and key_value=#keyValue# and status=0
    
    
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef_item set type=#type#,cdef_id=#cdefId#,value=#value#
    	 where id=#id#
    
    
    
    	 update jrobin_color set hex=#hex#
    	 where id=#id#
    
    
    
    	 update jrobin_dynamic_key set value=#sumValue#, status=#status#
    	 where datasource_id=#dsID# and key_name=#keyName# and key_value=#keyValue# and status=0
    
    
    
    	 update jrobin_key set key_value=#keyValue#,des=#des#
    	 where id=#id#
    
    
    
    	 update jrobin_rra set name=#name#, cf=#cf#,
    	 xff=#xff#,steps=#steps#,rows=#rows#,timespan=#timespan#
    	 where id=#id#
    
    
    
    	 update jrobin_datasource set name=#name#, key1=#key1#,key2=#key2#,key3=#key3#,
    	 key4=#key4#,data_type=#dataType#,host_names=#hostNames#,step=#step#,rrd_path=#rrdPath#,timespan=#timespan#,data_choice=#dataChoice#
    	 where id=#id#
    
    
     
    	 update jrobin_datasource set is_active=? where id=?
    
    
     
    	 update jrobin_datasource set process_id=? where id=?
    
    
    
    	 update jrobin_key set key_value=#keyValue#,des=#des#
    	 where id=#id#
    
    
			UPDATE MC_MemberCardProduct 
			SET EndDate = #endDay#
			WHERE ProductID = #productID# 
	
	        WHERE m.MemberCardID = #memberCardId#
	
			UPDATE MC_MemberCardStopLog st
			SET st.IsActive=0
	        WHERE st.MemberCardID = #memberCardId#
	
	        WHERE st.LogID = #logId# AND st.MemberCardID = #memberCardId# AND st.IsActive = 1
	
			UPDATE MC_MemberCardScoreLog s
			SET s.IsActive = 0
			WHERE s.MemberCardID = #memberCardId# 
	
        UPDATE MC_MemberCardUserInfo SET SynchronizFlag=1 WHERE userID=#userId# AND SynchronizFlag=0;
    
        UPDATE MC_MemberCardUserInfo SET UserName=#userName#,UserSex=#userSex#,UserBirthDay=#userBirthDay#,SynchronizFlag=1
        WHERE UserID = #userId#
    
		UPDATE MC_MemberCardShop s SET s.HotShop = 0 
		WHERE s.HotShop = 1 AND s.CityID = #cityId#;
	
	
		
		UPDATE MC_MemberCard m,
			(SELECT u.MemberCardID,SUM(IF(u.Status = 2,1,-1)) AS AddCount 
				FROM MC_MemberCardUser u WHERE u.AddTime >= (CURRENT_DATE - INTERVAL 1 DAY) AND u.AddTime < CURRENT_DATE  GROUP BY u.MemberCardID) u
		SET m.TotalMembers = m.TotalMembers + u.AddCount 
		WHERE m.MemberCardID = u.MemberCardID
			
	
		UPDATE MC_MemberCardConsumeAnalysis m
		SET ConsumeAmount = ConsumeAmount + #consumeAmount#,
			ConsumeCount = ConsumeCount + #consumeCount#,
			UpdateTime = #updateTime#
        WHERE ShopID = #shopID# AND ConsumeDate = #consumeDate#
	
			UPDATE MC_MemberCardNewRegister nr
			SET nr.UserID = #nUserId#  
			WHERE nr.UUID = #uuId# 
	
			UPDATE MC_MemberCardUserActionCoordinateLog ac
			SET ac.UserID = #nUserId#  
			WHERE ac.UUID = #uuId# 
	
			UPDATE MC_MemberCardUserActionLog ac
			SET ac.UserID = #nUserId#  
			WHERE ac.UUID = #uuId# 
	
		
		UPDATE TG_PrepaidCardRemindMsg m
		SET m.RemindStatus = #remindStatus#
		WHERE m.ConsumRemindMsgID = #consumRemindMsgID#
		
	
		UPDATE
			MC_MemberCardFeed
		SET
			PushTime = NOW()
		WHERE
			FeedID = #feedId#
	
		UPDATE
			MC_MemberCardFeed
		SET
			Status = 4
		WHERE
			FeedID = #feedId#
	
		UPDATE 
			MC_MemberCardShop s
		SET 
			s.ShopName = #shop.shopName#,s.BranchName = #shop.branchName#,
			s.ShopGroupId = #shop.shopGroupId#,s.CardGroupID = #shop.cardGroupId#,
			s.SecondCatgory = #shop.mainCategory#,s.Region = #shop.region#,
			s.Lat = #shop.lat#,s.Lng = #shop.lng#,s.Power = #shop.power#
		WHERE 
			s.ShopID = #shop.shopId#
	
		UPDATE MC_MemberCardNumberUser SET CardGroupID = #fromCardId# WHERE CardGroupID = #toCardId#
	  
	    UPDATE MC_MemberCardUser SET CardNO = #cardNO# WHERE MemberCardUserID = #memberCardUserId#
	
		update
			DP_Word
		set
			WordType=#wordType#,
			SensitiveType=#sensitiveType#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			LastTime= now(),
			LastAdminID=#lastAdminID#,
			Memo=#memo#
		where
			ID=#id#
	
    	UPDATE role SET name = #name#, remark = #remark# WHERE id = #id#
    
        
        UPDATE MLC_OrderBaseInfo SET
        contractNo = #orderBaseInfoEntity.contractNo#,
        customerName = #orderBaseInfoEntity.customerName#,
        salesId = #orderBaseInfoEntity.salesId#,
        salesName = #orderBaseInfoEntity.salesName#,
        salesCityId = #orderBaseInfoEntity.salesCityId#,
        launchAttribute = #orderBaseInfoEntity.launchAttribute#,
        status = #orderBaseInfoEntity.status#
        WHERE
        orderId = #orderBaseInfoEntity.orderId#
        
    
        
        UPDATE MLC_OrderBaseInfo SET
        status = #status#
        WHERE
        orderId = #orderId#
        
    
		UPDATE system_setting SET value = #value# WHERE `key` = #key#
	
    	update team
		set name=#name#
		where id=#id#
    
    	UPDATE user 
    	SET loginName = #loginName#, name = #name#, email = #email#, system = #system#, locked = #locked#, password = #password#,
    		onlineConfigView = #onlineConfigView#
    	WHERE id = #id#
    
		
		update user set password = #password# where id = #id#
		
    
        
        UPDATE MLC_OrderItemRelation SET
        orderItemId = #orderItemRelationEntity.orderItemId#,
        orderId = #orderItemRelationEntity.orderId#,
        producerId = #orderItemRelationEntity.producerId#,
        productId = #orderItemRelationEntity.productId#,
        cityId = #orderItemRelationEntity.cityId#,
        shopId = #orderItemRelationEntity.shopId#,
        shopGroupId = #orderItemRelationEntity.shopGroupId#,
        timeLimitFlag = #orderItemRelationEntity.timeLimitFlag#,
        effectiveDays = #orderItemRelationEntity.effectiveDays#,
        producedDays = #orderItemRelationEntity.producedDays#,
        validEndTime = #orderItemRelationEntity.validEndTime#,
        validTimeData = #orderItemRelationEntity.validTimeData#,
        status = #orderItemRelationEntity.status#
        WHERE
        relationId = #orderItemRelationEntity.relationId#
        
    
        
        UPDATE MLC_OrderItemRelation SET
        producedDays = #producedDays#
        WHERE
        relationId = #relationId#
        
    
        
        UPDATE MLC_OrderItemRelation SET
        status = #status#
        WHERE
        relationId = #relationId#
        
    
        
        UPDATE MLC_OrderItemRelation SET
        status = #status#
        WHERE
        orderId = #orderId#
        
    
        
        UPDATE MLC_OrderItemRelation SET
        shopId = #shopId#
        WHERE
        relationId = #relationId#
        
    
        
        UPDATE MLC_OrderItemRelation SET
        status = #status#
        WHERE
        orderItemId = #orderItemId#
        
    
        
        UPDATE MLC_ProducerDelegation SET
        status = #status#
        WHERE producerId = #producerId#
        
    
        
        UPDATE MLC_ProducerInfo SET
        status = #status#
        WHERE producerId = #producerId#
        
    
    	update environment
		set ips = #ips#,
			label = #label#,
			name = #name#,
			online = #online#,
			modifyUserId = #modifyUserId#,
			modifyTime = NOW(),
			seq = #seq#
		where id=#id#
    
			UPDATE job_exec_time SET lastJobExecTime=now() WHERE jobName=#jobName# AND TIMESTAMPDIFF(second, lastJobExecTime, now()) > #effectiveRange#
    
    	update job_exec_time
		set lastJobExecTime=#lastJobExecTime#
		where id=#id#
    
    	update job_exec_time
		set lastFetchTime=#lastFetchTime#
		where id=#id#
    
    	update job_exec_time
		set switcher=#switcher#,
			failMail=#failMail#
		where id=#id#
    
    		update product
		set name=#name#,
			teamId=#teamId#,
			productLeaderId=#productLeaderId#,
			createTime=#createTime#,
			modifyTime=#modifyTime#,
			seq=#seq#
		where id=#id#
    
        
        WHERE
        detectionId = #detectionEntity.detectionId#
        
    
        
        WHERE
        launchId = #detectionEntity.launchId#
        and version = #detectionEntity.version#
        and status = #detectionEntity.status#
        
    
        
        UPDATE MLC_ThirdPartyDetection SET
        status = #status#
        WHERE
        launchId = #launchId#
        and version = #version#
        
    
        
        UPDATE MLC_Launch SET
        status = #status#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        version = #version#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        status = #status#,
        version = #version#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        status = #status#,
        nextVersion = #nextVersion#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        status = #status#
        WHERE
        orderId = #orderId#
        
    
        
        UPDATE MLC_Launch SET
        onlineTime = #onlineTime#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        offlineTime = #offlineTime#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        planId = #planId#,
        relationId = 0,
        orderId = 0,
        accountName = #accountName#,
        productId = #productId#
        WHERE
        launchId = #launchId#
        
    
        
        UPDATE MLC_Launch SET
        accountName = #accountName#,
        productId = #productId#
        WHERE
        planId = #planId#
        
    
    	UPDATE config
    	SET `desc` = #desc#, type = #type#, modifyUserId = #modifyUserId#, modifyTime = NOW(), private = #privatee#, seq = #seq#
    	WHERE id = #id#
    
    	UPDATE config_instance
    	SET value = #value#, refkey = #refkey#, context = #context#, contextmd5 = #contextmd5#, 
    		modifyUserId = #modifyUserId#, modifyTime = NOW(), `desc` = #desc#, seq = #seq#
    	WHERE id = #id#
    
    	UPDATE config_status SET modifyUserId = #modifyUserId#, modifyTime = NOW() WHERE configId = #configId# AND envId = #envId#
    
        
        WHERE
        materialId = #materialEntity.materialId#
        
    
        
        WHERE
        launchId = #materialEntity.launchId#
        and version = #materialEntity.version#
        and status = #materialEntity.status#
        
    
        
        UPDATE MLC_Material SET
        status = #status#
        WHERE
        launchId = #launchId#
        and version = #version#
        
    
        
        UPDATE MLC_Material SET
        shopId = #shopId#
        WHERE
        materialId = #materialId#
        
    
        
        WHERE
        strategyId = #strategyEntity.strategyId#
        
    
        
        UPDATE MLC_Strategy SET
        status = #status#
        WHERE
        launchId = #launchId#
        and version = #version#
        
    
        
        UPDATE MLC_PromoPlanInfo SET
          promoPlanName = #promoPlanEntity.promoPlanName#,
          accountName = #promoPlanEntity.accountName#,
          budgetTotal = #promoPlanEntity.budgetTotal#,
          budgetPerDay = #promoPlanEntity.budgetPerDay#,
          producerId = #promoPlanEntity.producerId#,
          productId= #promoPlanEntity.productId#,
          status = #promoPlanEntity.status#,
          startTime = #promoPlanEntity.startTime#,
          endTime = #promoPlanEntity.endTime#
        WHERE
        promoPlanId = #promoPlanEntity.promoPlanId#
        
    
        
        UPDATE MLC_PromoPlanInfo SET
        status = #status#
        WHERE
        promoPlanId = #promoPlanId#
        
    
    	update project set name=#name#, productId=#productId#,createTime=#createTime#, modifyTime=#modifyTime#
    	where id = #id#
    
		
			update MMC_UserInfo
			SET type = type | #userInfo.userType.value#
			WHERE UId = #userInfo.userId#
		
	
		UPDATE CI_ScoreBoardAD
		SET checkintime = #checkinTime#
		WHERE id=#id#
	
		UPDATE CI_ScoreBoardAD
		SET checkintime = #checkinTime#, activatetime=#activateTime#,status=#status#
		WHERE id=#id#
	
    
    	 update jrobin_tree_node set parent_id=#parentId#,name=#name#
    	 where id=#id#
    
    
    	update  serviceReport set providerName=#providerName#,providerIp=#providerIp#,
    	consumerName=#consumerName#,consumerIp=#consumerIp#,serviceName=#serviceName#,code=#code#,
    	reportName=#reportName#,type=#type# where id=#id# 
	
    
    	 update jrobin_datasource_item set datasource_type=#datasourceType#, heart_beat=#heartBeat#,max_value=#maxValue#,min_value=#minValue#,
    	 datasource_id=#datasourceId# ,internal_name=#internalName# where id=#id#
    
    
    
    	 update jrobin_graph set text=#text#,name=#name#,title=#title#,image_format_id=#imageFormateId#,height=#height#,
    	 width=#width#,upper_limit=#upperLimit#,lower_limit=#lowerLimit#,vertical_label=#verticalLabel#,grid_step=#gridStep#,label_factor=#labelFactor# where id=#id#
    
    
    
    	 update jrobin_graph_item set graph_id=#graphId#,datasource_id=#datasourceId#,color_id=#colorId#,
    	 alpha=#alpha#,graph_item_type=#graphItemType#,cdef_id=#cdefId#,consolidation_id=#consolidationId#,
    	 text_format=#textFormat#,value=#value#,sequence=#sequence# where id=#id#
    
    
		UPDATE MC_MemberCardUserMobile 
		SET CustomerID 	= #scoreUser.customerId#,
			UserID		= #scoreUser.userId#,
			MobileNo	= #scoreUser.mobileNo#, 
			Status		= #scoreUser.status#
		WHERE UserMobileID = #scoreUser.userMobileId#
	
		WHERE MemberCardUserValueID=#data.memberCardUserValueId#
	
	
		UPDATE MC_MemberCardUserFeed SET Status = #status#
		WHERE UserID = #userId# AND FeedID = #feedId#
	
		UPDATE MC_MemberCardUserFeed SET Status = 2, ReadTime = NOW()
		WHERE UserID = #userId# AND FeedID = #feedId#
	
		UPDATE MC_MemberCardUserFeed SET IsLike = 1
		WHERE UserFeedID = #userFeedId#
	
		UPDATE MC_MemberCardUserFeed SET IsUse = 1, UseTime = NOW()
		WHERE UserFeedID = #userFeedId#
	
		UPDATE MC_MemberCardFeed SET TotalLikeNum = TotalLikeNum + 1
		WHERE FeedID = #feedId#
	
   		
   		Update DP_MsgSubscriptionList
   		set Status=0
   		WHERE UserID = #userId# AND DetailID = #detailId#
   		
	
	
		UPDATE CI_UserInfo SET NotificationCount = NotificationCount + 1, CircularCount = CircularCount + 1 WHERE UserID = #UserID#
	
		UPDATE Mail_EmailList SET
			Subject = #subject#,
			Content = #content#,
			Subs = #subs#,
			OptionalSubs = #optionalSubs#
		WHERE
			EmailListID = #emailListId#
	
		UPDATE DP_Shop
		SET
		Power = #power#
		WHERE ShopID = #shopID#
	
		UPDATE DP_Shop
		SET GLat = #lat#,GLng= #lng#
		WHERE ShopID = #shopID#
	
	
    	UPDATE Activity_SamsungS5_money
    	SET    currentPay = currentPay + #money#
    	WHERE  createTime = #nowDate# and (currentPay + #money# <= total)
    
    
	
    	UPDATE Activity_SamsungS5
    	SET    chanceStatus = 1,chance = chance + 1
    	WHERE  userId = #userId# and chance < 9 and createTime >= #startTime# and createTime <= #endTime#
    
    
	
    	UPDATE Activity_SamsungS5
    	SET    S5status = 1
    	WHERE  id=#id# and createTime >= #startTime# and createTime <= #endTime#
    
    
	
    	UPDATE Activity_SamsungS5
    	SET    chanceStatus = 0
    	WHERE  userId = #userId# and createTime >= #startTime# and createTime <= #endTime#
    
    
 		UPDATE CI_SceneryOrderDetail 
 		SET SerialID = #serialId#
 		WHERE ID = #id#
 	
    	UPDATE DianPingMobile.CI_SceneryOrderDetail 
		SET Status = #status#, EnableCancel = #enableCancel#, TotalPrice = #totalPrice#
		WHERE SerialId = #serialId#
    
		UPDATE
			BC_Notice
		SET 
			Title = #title#,
			Content = #content#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
			BC_Notice
		SET 
		    LastAdminID = #lastAdminID#,
			Status = 0
		WHERE
			ID=#id#
    
	    
		UPDATE DianPingBC.MP_MobileDevice 
		SET
		UUID = #entity.uuid# , 
		OSType = #entity.osTypeId# , 
		OSDetail = #entity.osDetail# , 
		DeviceModel = #entity.deviceModel# , 
		AppVersion = #entity.appVersion# 
		WHERE
		Id = #entity.id# ;
		
	
		
			UPDATE MLC_Code
			SET STATUS = #status#
			WHERE Code = #code#
		
	
    
		UPDATE DP_MessageReplyStatus
		SET Status=#newStatus#, UpdateTime=NOW()
		WHERE MessageID=#messageId#
	
		UPDATE DP_SysPrivateTask
		SET Status=#status#
		WHERE TaskID=#taskId#
	
		UPDATE DP_SysPrivateTask
		SET SuccessIDs=#successIds# , SuccessCount=#successCount#, UpdateTime=NOW()
		WHERE TaskID=#taskId#
	
		UPDATE DP_SysPrivateTask
		SET Status=1
		WHERE Status=0
	
		UPDATE
		DP_SysSubscriptionTask
		SET Status=#status#
		WHERE TaskID=#taskId#
	
		UPDATE
		DP_SysSubscriptionTask
		SET SuccessIDs=#successIds# ,
		SuccessCount=#successCount#, DetailIDs=#detailIds#, UpdateTime=NOW()
		WHERE TaskID=#taskId#
	
		UPDATE
		DP_SysSubscriptionTask
		SET Status=1
		WHERE Status=0
	
		UPDATE
		DP_SysSubscriptionTask
		SET ToUserIDs=#touserIds#,TotalCount=#totalCount#,UpdateTime=NOW()
		WHERE TaskID=#taskId#
	
        



			UPDATE MP_Module 
				SET
				ModuleName = #module.name# , 
				Remark = #module.remark#
				
				WHERE
				ModuleId = #module.id# ;    



        
     
		UPDATE
		BC_ShopAccount_Customer
		SET CustomerName = #customerName#
		Where
		ShopAccountId=#shopAccountId#
    
	    
		UPDATE MP_MerchantMessage
		SET
            ShopAccountId=#entity.shopAccountId#,
            MessageType=#entity.messageType#,
            BusinessId=#entity.businessId#,
            Content=#entity.content#,
            CreateTime=#entity.createTime#,
            EffectiveEndTime=#entity.effectiveEndTime#,
            IsShake=#entity.isShake#,
            IsSounding=#entity.isSounding#,
            SoundType=#entity.soundType#,
            RepeatCount=#entity.repeatCount#
		WHERE
		    Id = #entity.id# ;
		
	
	    
		UPDATE DianPingBC.BC_ShopAccount_Token
		SET
            IsLogin=#entity.isLogin#,
            OSType=#entity.osTypeId#,
            OSDetail=#entity.osDetail#,
            DeviceModel=#entity.deviceModel#,
            AppVersion=#entity.appVersion#,
            Token=#entity.token#,
            IsValid=#entity.isValid#,
            IsBadReviewNeed=#entity.isBadReviewNeed#,
            IsFailReplyNeed=#entity.isFailReplyNeed#,
            IsTakeawayMsgNeed=#entity.isTakeawayMsgNeed#,
            IsBookMsgNeed=#entity.isBookMsgNeed#,
            LastLoginTime=#entity.lastLoginTime#,
            LastLogoutTime=#entity.lastLogoutTime#,
            LastUpdateTime=#entity.lastUpdateTime#
		WHERE
		Id = #entity.id# ;
		
	
		UPDATE WAP_URLRewriteRules 
		SET maskurl=#maskurl# WHERE realurl='~/home/getmorecategory.action?cityPinYin=$$1'
	
			UPDATE WAP_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/getmoreregion.action?cityPinYin=$$1'
	
			UPDATE WAP_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/gethomedetail.action?cityPinYin=$$1'
	
			UPDATE WAP_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/home/hotcategory.action?cityPinYin=$$1'
	
			UPDATE WAP_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/home/hotregion.action?cityPinYin=$$1'
	
			UPDATE WAP_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/home/childcategory.action?cityPinYin=$$1&categoryId=$$2'
	
			UPDATE WAP_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/home/childregion.action?cityPinYin=$$1&regionId=$$2'
	
        UPDATE MOPay_Settle_Shop_Balance
        SET Balance = Balance + #balance#, UpdateTime = NOW()
        WHERE ShopID = #shopId#
    
        
           UPDATE MOPay_Shop
           SET Status=#status#, UpdateTime=#updateTime#
           WHERE shopId = #shopId#
         
	
		
		UPDATE
			DeviceInfo
		SET
			status=#status#
		WHERE
			deviceSerial=#deviceSerial#
		
	
		UPDATE
		CQRS_ORDER
		SET
		ORDER_ID=#entity.id#,
		CONTENT=#entity.content#
		WHERE
		ORDER_ID=#entity.id#
	
		UPDATE
		MP_Role
		SET RoleName
		= #roleInfo.name#,CanAuthorise = #roleInfo.canAuthorise#,
		Owner =
		#roleInfo.ownerId#, Remark = #roleInfo.remark#, UpdateTime =
		now()
		Where
		RoleId=#roleInfo.id#
	
		UPDATE CI_Gift_V2
		SET
		    Phone = #phone#,
		    UserId = #userId#,
		    Phone = #phone#,
		    TicketId = #ticketId#,
		    GrantTime = CURRENT_TIMESTAMP,
		    Status = #status#
		WHERE
		    Id = #id#;
	
        UPDATE CI_Gift_V2
        SET
            Phone = #phone#,
            UserId = #userId#,
            Phone = #phone#,
            EventProperties = #eventProperties#,
            TicketId = #ticketId#,
            GrantTime = CURRENT_TIMESTAMP,
            Status = #status#
        WHERE
            Id = #id#;
    
		UPDATE CI_Gift_V2
		SET Status = #status#
		WHERE Id = #id#;
	
        
           UPDATE MOPay_Payment
           SET Status=#status#, UpdateTime =NOW()
           WHERE PaymentID = #paymentId#
         
	
        
           UPDATE MOPay_Payment
           SET PayOrderID=#payOrderID#, UpdateTime =NOW()
           WHERE PaymentID = #paymentId#
         
	
        
           UPDATE MOPay_Payment
           SET Status = 2, RefundStatus=#refundStatus#, UpdateTime = NOW()
           WHERE PaymentID = #paymentId#
         
    
		
		UPDATE MMC_AdInfo
		SET 
			Status = #status#,
			IsUpdate = 1
		WHERE AdID = #adId#
		
	
		
		UPDATE MMC_AdInfo
		SET
		BValidTime = #adInfo.validBeginTime#, 
		EValidTime = #adInfo.validEndTime#, 
		BReleaseTime = #adInfo.releaseBeginTime#, 
		EReleaseTime = #adInfo.releaseEndTime#,
			Budget = #adInfo.budget#, 
			IsUpdate = 1
		WHERE adId = #adInfo.adId#
		
	
		
		UPDATE MMC_AdInfo
		SET 
			CID = #adInfo.cityId#,
			AdvertisementType = #adInfo.advertisementType.value#, 
			ExhibitionType = #adInfo.exhibitionType.value#,
			Title = #adInfo.title#, 
			Content = #adInfo.content#, 
			BURL = #adInfo.bigPicKey#, 
			SURL = #adInfo.smallPicKey#, 
			IsMsg = #adInfo.isMessage#, 
			MSG = #adInfo.message#,
			Introduce = #adInfo.introduce#, 
		BValidTime = #adInfo.validBeginTime#, 
		EValidTime = #adInfo.validEndTime#, 
		BReleaseTime = #adInfo.releaseBeginTime#, 
		EReleaseTime = #adInfo.releaseEndTime#,
			Budget = #adInfo.budget#, 
			Price = #adInfo.price#, 
			DayBudget = #adInfo.dayBudget#, 
			IsEven = 1, 
			IsUpdate = 1
		WHERE adId = #adInfo.adId#
		
	
		
		UPDATE MMC_AdInfo
		SET
			Consume = Consume + #adConsume.consume#,
			PV = PV + #adConsume.pv#,
			Print = Print + #adConsume.print#,
			Download = Download + #adConsume.messageDownload#,
			IsUpdate = 1
		WHERE AdID = #adConsume.adId#;
		
	
		
		UPDATE MMC_AdInfo
		SET
		status = #newStatus#,
		isupdate = 2
		WHERE
		status = #oldStatus# 
		AND BReleaseTime <= #time#
		
	
		
		UPDATE MMC_AdInfo
		SET
		status = #newStatus#,
		isupdate = 2
		WHERE
		status = #oldStatus# 
		AND EReleaseTime < #time#
		
	
		
		UPDATE MMC_AdInfo
		SET
		budget = 0,
		consume = 0,
		isUpdate = 1
		WHERE
		AdId = #adId#
		
	
		
		UPDATE MMC_AdInfo
		SET
		isupdate = 0
		WHERE adId = #adId#
		
	
		UPDATE CI_AutoPush 
		SET PushType = #autoPushDO.pushType#, Text = #autoPushDO.text#, Link = #autoPushDO.link#, 
			CityID = #autoPushDO.cityId#, PlatformIDs = #autoPushDO.platformIds#, PushDate = #autoPushDO.pushDate#,
			UpdateTime = NOW()
		WHERE AutoPushID = #autoPushDO.autoPushId#
	
		WHERE AutoPushID = #autoPushId#
	
		UPDATE CI_DeviceNumInfo 
		SET NotificationCount = NotificationCount + #count#
		WHERE DPID = #dpId#
	
		UPDATE CI_DeviceNumInfo 
		SET NotificationCount = 0
		WHERE DPID = #dpId#
	
        UPDATE CI_MessageInfo
        SET
            Type = #type#,
            TrainId = #trainId#,
            MsgText = #msgText#,
            ShareBody = #shareBody#,
            ShareUrl = #shareUrl#,
            Url = #url#,
            Utm = #utm#,
            CommitName = #commitName#,
            BizID = #bizId#,
            StartTime = #startTime#,
            MsgType = #msgType#
        WHERE
            ID = #id#
    
    
        UPDATE
            CI_OssUtm
        SET
            Utm = #utm#
        WHERE
            Id = #id#
    
    
    	 update server set name=#name#,ip=#ip#,port=#port#,des=#des#
    	 where id=#id#
    
	
    
    	 update configdetail set projectKey=#key#, projectValue=#value#,
    	 des=#des#,modifyTime=#modifyTime#    	
    	 where id=#id#
    
	
    
    	 update configdetail set des2=#des2# 	
    	 where id=#id#
    
	
    
    	 update configdetail set projectValue=#value# 	
    	 where id=#id#
    
	
    
    	 update configdetail set aValue=#aValue# 	
    	 where id=#id#
    
	
    
    	 update configdetail set bValue=#bValue# 	
    	 where id=#id#
    
	
    
    	 update configdetail set cValue=#cValue# 	
    	 where id=#id#
    
	
    
    	 update configdetail set dValue=#dValue# 	
    	 where id=#id#
    
	
    
    	 update configdetail set pos="Y" 	
    	 where id=#id#
    
	
    
    	 update configdetail set pos="N" 	
    	 where id=#id#
    
	
    
    	 update configdetail set pos="N" 	
    	 where projectId=#id# and pos<>"N"
    
	
      
    	 update  configdetail set deletePos=1 where id=#id#
    
	
      
    	update  configdetail set deletePos=1 where projectId=#id#
    
	
    
    	 update config_public set publicKeyName=#publicKeyName#,publicKeyValue=#publicKeyValue#,
    	 des=#des# where id=#id#
    
    
    
    	 update project set name=#name#,leader=#leader#,
    	 phone=#phone#,opDirector=#opDirector#,email=#email#,des=#des#,hawk=#hawk#,
    	 lion=#lion#,state=#state#,service=#service#,parentProject=#parentProject#,svnAddress=#svnAddress#,DBAEmailAddr=#DBAEmailAddr# where id=#id#
    
    
    
    	 update project set name=#name#,leader=#leader#,
    	 phone=#phone#,email=#email#,des=#des#,hawk=#hawk#,
    	 lion=#lion#,state=#state#,service=#service#,parentProject=#parentProject#,svnAddress=#svnAddress# where id=#id#
    
    
     
    	update project set opDirector=#opDirector#,DBAEmailAddr=#DBAEmailAddr#, email=#email# where parentProject=#id#
    	   
    
    
    	 update service set serviceIp=#serviceIp#,des=#des#
    	 where id=#id#
    
    
    
    	 update service set serviceIp1=#serviceIp# 	 where id=#id#
    
    
    
    	 update service set serviceIp2=#serviceIp# 	 where id=#id#
    
    
    
    	 update service set serviceIp3=#serviceIp# 	 where id=#id#
    
    
    
    	 update service set serviceIp4=#serviceIp# 	 where id=#id#
    
    
    
    	 update service set serviceIp5=#serviceIp# 	 where id=#id#
    
    
    
    	 update project set serviceIp=#serviceIp# and serviceIpList=#serviceIpList# where id=#projectId#
    
    
    
    	 update user set name=#name#,
    	 email=#email#,phone=#phone#,real_name=#realName# where id=#id#
    
	
    
    	 update role set name=#name# where id=#id#
    
	
    
    	 update jrobin_color set hex=#hex#
    	 where id=#id#
    
    
    
    	 update jrobin_key set key_value=#keyValue#,des=#des#
    	 where id=#id#
    
    
		UPDATE CI_AutoPush 
		SET Status = #status#, UpdateTime = NOW()
		WHERE AutoPushID = #autoPushId#
	
		UPDATE CI_AutoPushSendNum 
		SET AndroidNewUserSendNum = #sendNum.androidNewUserSendNum#,
			Android30UserSendNum = #sendNum.android30UserSendNum#,
			Android60UserSendNum = #sendNum.android60UserSendNum#,
			IphoneNewUserSendNum = #sendNum.iphoneNewUserSendNum#,
			IpadNewUserSendNum = #sendNum.ipadNewUserSendNum#,
			Iphone30UserSendNum = #sendNum.iphone30UserSendNum#,
			Ipad30UserSendNum = #sendNum.ipad30UserSendNum#,
			Iphone60UserSendNum = #sendNum.iphone60UserSendNum#,
			Ipad60UserSendNum = #sendNum.ipad60UserSendNum#
		WHERE AutoPushID = #sendNum.autoPushId#
	
		UPDATE CI_AutoMiPushNum 
		SET andNewSendNum_MI = #andNewSendNum_MI#,
			and30SendNum_MI = #and30SendNum_MI#,
			and60SendNum_MI = #and60SendNum_MI#,
			andNewArrivalNum = #andNewArrivalNum#,
			and30ArrivalNum = #and30ArrivalNum#,
			and60ArrivalNum = #and60ArrivalNum#
		WHERE AutoPushID = #autoPushId#
	
		UPDATE CI_AutoPushArrivalNum 
		SET IphoneNewUserArrivalNum = #arrNum.iphoneNewUserArrivalNum#,
			IpadNewUserArrivalNum = #arrNum.ipadNewUserArrivalNum#,
			Iphone30UserArrivalNum = #arrNum.iphone30UserArrivalNum#,
			Ipad30UserArrivalNum = #arrNum.ipad30UserArrivalNum#,
			Iphone60UserArrivalNum = #arrNum.iphone60UserArrivalNum#,
			Ipad60UserArrivalNum = #arrNum.ipad60UserArrivalNum#,
			AndroidNewUserArrivalNum = #arrNum.androidNewUserArrivalNum#,
			Android30UserArrivalNum = #arrNum.android30UserArrivalNum#,
			Android60UserArrivalNum = #arrNum.android60UserArrivalNum#
		WHERE AutoPushID = #arrNum.autoPushId#
	
		UPDATE MC_MemberCard SET TotalMembers = TotalMembers + 1
		WHERE MemberCardID = #cardId#
	
		UPDATE MC_MemberCard SET TotalMembers = TotalMembers - 1
		WHERE MemberCardID = #cardId#
	
	
   		
   		Update DP_NoticeList
   		set Status=0
   		WHERE UserID = #userId# AND DetailID = #detailId#
   		
	
	
	 
	   update ZS_User set UserNickName=#userNickName# where UserID=#userID#
		
	
		UPDATE DianPingMobile.YP_IPhonePush 
		SET DeviceID = #DeviceID#,
			PToken = #PToken#
		WHERE OldDeviceID = #OldDeviceID#
	
		UPDATE BC_ResetPasswordRequest
		SET
		ShopAccount=#entity.shopAccount#,
		ShopAccountId=#entity.shopAccountId#,
		MobileNo=#entity.mobileNo#,
		LastRequestTime=#entity.lastRequestTime#,
		IsReset=#entity.isReset#,
		LastResetTime=#entity.lastResetTime#,
		LastUpdatorId=#entity.lastUpdatorId#
		WHERE
		ResetPasswordRequestId=#entity.id#
	
		UPDATE BC_Tip
		SET
		Document=#entity.document#,
		Url=#entity.url#,
		Status=#entity.status#,
		LastUpdateTime=#entity.lastUpdateTime#,
		LastUpdatorId=#entity.lastUpdatorId#,
		CreateTime=#entity.createTime#
		WHERE
		TipId=#entity.id#
	
		UPDATE DianPingBC.MP_Device_Account_Assn 
		SET
		IsLogin = #entity.isLogin#
		, LastLoginTime = #entity.lastLoginTime#
		, LastLogoutTime = #entity.lastLogoutTime#
		WHERE
		Id = #entity.id# ;
	
	 
	   update ZS_User set UserNickName=#userNickName# where UserID=#userID#
		
	
		UPDATE Mail_Queue_Tmail
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE ID = #mailId# AND status = #oldStatus#
    
		UPDATE Mail_Queue_Tmail
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE status = #oldStatus#
		ORDER BY Rank DESC, ID ASC
		Limit #number#
	
		UPDATE Mail_Queue_Tmail
		SET Trytimes = #tryTimes#, UpdateDate = NOW()
		WHERE ID = #mailId#
    
		UPDATE Mail_Queue_Umail
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE ID = #mailId# AND status = #oldStatus#
    
		UPDATE Mail_Queue_Umail
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE status = #oldStatus#
		ORDER BY Rank DESC, ID ASC
		Limit #number#
	
		UPDATE Mail_Queue_Umail
		SET Trytimes = #tryTimes#, UpdateDate = NOW()
		WHERE ID = #mailId#
    
		UPDATE DP_SysNoticeTask
		SET Status=#status#
		WHERE TaskID=#taskId#
	
		UPDATE DP_SysNoticeTask
		SET SuccessIDs=#successIds# , SuccessCount=#successCount#, DetailIDs=#detailIds#, UpdateTime=NOW()
		WHERE TaskID=#taskId#
	
		UPDATE DP_SysNoticeTask
		SET Status=1
		WHERE Status=0
	
		UPDATE DP_SysNoticeTask
		SET Status=3
		WHERE TaskID=#taskId#
	
		
			UPDATE TG_SMS_Restrict
			SET
			SendTimes = SendTimes + 1,
			UpdateDate = now()
			where 
			orderId = #orderId# 
			and type = #type#
		
	
       update MP_AuthorizationAssn
       set Status = 0, UpdateTime = now(), RevokeRecordId=#revokeRecordId#
       WHERE ShopAccountId = #shopAccountId#
			AND contractId = #contractId#
			AND functionId = #functionId#
			AND AccessRecordId = #accessRecordId#
			AND Status=1
     
       update MP_AuthorizationAssn
       SET Status = 0, UpdateTime = now(), RevokeRecordId=#revokeRecordId#
       WHERE AccessRecordId = #recordId#
	   AND Status=1
     
     
    
       update MP_Function
       set FunctionName =#function.functionName#, IsFixed =#function.isFixed#, UpdateTime =now()
        WHERE FunctionId = #function.functionId#
     
		UPDATE
			BC_Notice
		SET 
			Title = #title#,
			Content = #content#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
			BC_Notice
		SET 
		    LastAdminID = #lastAdminID#,
			Status = 0
		WHERE
			ID=#id#
    
		UPDATE BC_ResetPasswordRequest
		SET
		ShopAccount=#entity.shopAccount#,
		ShopAccountId=#entity.shopAccountId#,
		MobileNo=#entity.mobileNo#,
		LastRequestTime=#entity.lastRequestTime#,
		IsReset=#entity.isReset#,
		LastResetTime=#entity.lastResetTime#,
		LastUpdatorId=#entity.lastUpdatorId#
		WHERE
		ResetPasswordRequestId=#entity.id#
	
		

			INSERT INTO BC_ShopAccount_Privilege
			(`ShopAccountId`,`Privileges`,`AddDate`,`UpdateDate`) VALUES (#shopAccountId#,#privileges#,now(),now());

        
	
		

			UPDATE BC_ShopAccount_Privilege 
			SET Privileges=#privileges#,UpdateDate=now()
			WHERE ShopAccountId = #shopAccountId#;

        
	
		UPDATE
		CQRS_SEAT_AVAILABILITY
		SET
		SEAT_AVAILABILITY_ID=#entity.id#,
		ORDER_ID=#entity.order.id#
		WHERE
		SEAT_AVAILABILITY_ID=#entity.id#
	
		





















			UPDATE DP_Shop_Contact
			SET Status = #status#
			WHERE ID=#contactId#





















        
	
		





















			UPDATE DP_Shop_Contact
			SET Name= #shopContactBasicInfo.Name#,
			Mobile= #shopContactBasicInfo.mobile#,
			Email= #shopContactBasicInfo.email#,
			Memo= #shopContactBasicInfo.memo#,
			ShopID= #shopContactBasicInfo.shopId#,
			UpdateTime= NOW()
			WHERE ID=#contactId#





















        
	
		





















			UPDATE DP_Shop_Contact
			SET ShopAccountId = #shopAccountId#,  UpdateTime = now()
			WHERE ID=#contactId#





















        
	
		UPDATE OSS_PowerGroup 
		SET NAME = #powerGroupPO.name#, PowerIDs = #powerGroupPO.powerIds#, 
			UserIDs = #powerGroupPO.userIds#, UpdateTime = NOW()
		WHERE ID = #powerGroupPO.id#
	
		UPDATE OSS_PowerGroup 
		SET UpdateTime = NOW(), Flag = 0
		WHERE ID = #ID#
	
		UPDATE CI_ScoreBoardAD set checkintime = null
		where ID = #id# and status = 0
	
		update CI_ScoreBoardAD
		set activatetime = #time#, status = #status#
		where id = #id#
	
		update CI_ScoreBoardAD
		set activatetime = #time#, status = #status#,idfv=#idfv#,bssid=#bssid#,dpid=#dpid#
		where id = #id#
	
		update CI_ScoreBoardAD
		set errormsg = #errormsg#, train_id = #train_id# ,isGenuine=#isGenuine#
		where id = #id#
	
		update CI_ScoreBoardAD
		set extra = #extra#
		where id = #id#
	
		UPDATE
		BC_ShopAccount
		SET ShopAccount = #shopAccountId#
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE
		BC_ShopAccount
		SET ContactMobileNO = #contactPhone#,ContactName =
		#contactName#
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE BC_ShopAccount
		SET Password =
		#password#,UpdateTime=#updateTime#,PasswordVersion=PasswordVersion+1
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE BC_ShopAccount
		SET Password =
		#password#,UpdateTime=now(),PasswordVersion=PasswordVersion+1
		Where
		ShopAccount=#shopAccount#
	
    
        and sa.AccountType=#accountType#
        and sa.ShopAccountId = sas.ShopAccountId
        join BC_ShopAccountModule sam on sam.Module=#module#
        and sa.ShopAccountId=sam.ShopAccountId
        ) t
        set sa.Password= #password#,
        sa.UpdateTime=#updateTime#,PasswordVersion=PasswordVersion+1
        where sa.`ShopAccountId` =t.`ShopAccountId`;
    
		UPDATE BC_ShopAccount
		SET ContactMobileNO = #mobile#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE BC_ShopAccount
		SET ContactMobileNO = #mobile#,UpdateTime=now()
		Where
		ShopAccount=#shopAccount#
	
    
		UPDATE
		BC_ShopAccount
		SET Status = -1,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
	

		UPDATE
		BC_ShopAccount
		SET ContactName = #contactName#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
	

		UPDATE MP_ShpAcnt_OpenId_Assn
		SET  OpenId=#openId#
		Where ShopAccountId=#shopAccountId#
	
    	update TG_MovieShow
    	set MarketPrice = #marketPrice#,
    		CostPrice = #costPrice#,
            DPPrice= #DPPrice#,
    		UpdateDate = NOW()
    	where MovieShowID = #movieShowId#
    
       update MP_CloudPrintOrder
       set Status =#printStatus#
        WHERE PrintOrderId = #cloudPrintOrderId#
     
        UPDATE MOPay_Order
        SET UserID = #toUserID#
        WHERE OrderID = #orderID# AND UserID = #fromUserID#
    
        
           UPDATE  MOPay_Order
           SET Status=#status#, UpdateTime =NOW()
           WHERE OrderID = #orderID#
         
	
		UPDATE
			MOBILE_SCHEME_URL
		SET
			Name=#name#,
			Url=#url#,
			`Desc`=#desc#,
			VersionID=#versionID#,
			UpdateTime=NOW()
		WHERE
			ID=#id#
	
	
		UPDATE MC_Files 
		SET
			Status = #fileEntity.status#,
			UpdateTime = #fileEntity.updateTime#		
		WHERE
			FileId =#fileEntity.fileId#
	
	
		
			UPDATE MC_Files 
			SET 
				FileTitle=#fileTitle#,
				FileDescription=#fileDescription#,
				UpdateTime=#updateTime#
			WHERE FileId=#fileId#
		
	
		UPDATE MC_Files 
		SET
			WithoutWaterMark = #waterMark#,
			UpdateTime = #updateTime#		
		WHERE
			FileId =#fileId#
	
	
		UPDATE HT_EbizPromo 
		SET PromoTitle=#promotitle#,ShopName=#shopname#, ShopIntroduction=#shopdesc#,PromoPicPath=#promopicpath# 
			,PromoPicType=#promopictype#, LogoPicPath=#logopicpath# 
			,LogoPicType=#logopictype#, BeginDate=#releasebegintime#
			, EndDate=#releaseendtime#, PromoBeginDate=#validbegintime#
			, PromoEndDate=#validendtime#, IsSMS=#hassms# ,Status=#status#, ActionType=#actiontype#
			,PromoDesc=#promodesc#,ShopList=#shoplist#
		WHERE EBizPromoID=#ebizpromoid#
	
    
    
        
          UPDATE zSurvey_NET.Sales.CS_Contract
          SET Status = Status - 100
          WHERE ContractID = #contractId# AND Status>100
        
    
        
          UPDATE zSurvey_NET.ASPNet_zSurvey.DP_AWDetail
          SET Power = 2
		  FROM zSurvey_NET.ASPNet_zSurvey.DP_AWDetail D
		  JOIN zSurvey_NET.Sales.CS_ContractItems I ON D.ItemID=I.ItemID AND D.Power=5
		  JOIN zSurvey_NET.Sales.CS_Contract C ON C.ContractID=I.ContractID AND C.Status<100
		  WHERE C.ContractId = #contractId#
        
    
        
          UPDATE [Sales].[CS_Contract]
          SET Status = Status + 100
          WHERE ContractID = #contractId# AND Status<100
        
    
        
          UPDATE ASPNet_zSurvey.DP_Promo
          SET Power=1
          FROM Sales.CS_ContractItems T
	      JOIN Sales.CS_Contract C ON C.ContractID=T.ContractID
		  JOIN ASPNet_zSurvey.DP_Promo P ON P.ItemID=T.ItemID
		  WHERE C.Status>100 AND C.ContractID=#contractId#
        
    
        
          UPDATE ASPNet_zSurvey.DP_AWDetail
          SET Power=3
          FROM Sales.CS_ContractItems T
		  JOIN Sales.CS_Contract C ON C.ContractID=T.ContractID
		  JOIN ASPNet_zSurvey.DP_AWDetail P ON P.ItemID=T.ItemID
		  WHERE C.Status>100 AND C.ContractID=#contractId#
        
    
        
          UPDATE ASPNet_zSurvey.DP_AWDetail
          SET Power = 5
		  FROM ASPNet_zSurvey.DP_AWDetail D
		  JOIN Sales.CS_ContractItems I ON D.ItemID=I.ItemID AND D. Power=3
		  JOIN Sales.CS_Contract C ON C.ContractID=I.ContractID AND C.Status>100
		  WHERE C.ContractId = #contractId#
        
    
        UPDATE OSS_JobInfo 
		SET DeployIps = #DeployIps#, UpdateTime = NOW()
		WHERE ID = #ID#
    
    	update exceptionReport set key1=#key1#,key2=#key2#,key3=#key3#,key4=#key4#,type=#type# where id=#id# 
	
    
    	 update jrobin_cdef set name=#name#
    	 where id=#id#
    
    
    
    	 update jrobin_cdef_item set type=#type#,cdef_id=#cdefId#,value=#value#
    	 where id=#id#
    
    
    
    	 update jrobin_dynamic_key set value=#sumValue#, status=#status#
    	 where datasource_id=#dsID# and key_name=#keyName# and key_value=#keyValue# and status=0
    
    
		UPDATE 
			MC_MemberCardUserBadge 
		SET 
			UserBadgeUsed=UserBadgeUsed+#badge.userBadgeUsed#,UserBadgeTotal=UserBadgeTotal+#badge.userBadgeTotal# 
		WHERE 
			BadgeID=#badge.badgeId# AND UserID=#badge.userId# AND (UserBadgeTotal-UserBadgeUsed-#badge.userBadgeUsed#)>=0
	
		UPDATE Shop_Vote SET Count=Count+1 WHERE shopId = #shopId#
	
		UPDATE OP_OperateTask set Status = #status#
		WHERE TaskId = #taskId#
	
		UPDATE DP_HeaderTemplate SET
			Status = #status#,
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE DP_HeaderTemplate SET		   
			TemplateContent = #templateContent#,
			Status = #status#,
			Description = #description#,
			UpdateTime = NOW()
		WHERE
			 TemplateName = #templateName#
	
    	UPDATE CIW_UserFeedList
    	set CheckInStatus=#checkInStatus#
    	where ReferID=#referId# AND FeedType=1
    
      update EP_Core_Message
      	set
            `MessageFromLoginId` = #message.messageFromLoginId#,
        	`MessageFromSys` = #message.messageFromSys#,
        	`MessageToLoginId` = #message.messageToLoginId#,
        	`AddDate` = #message.addDate#,
        	`Status` = #message.status#,
        	`Title` = #message.title#,
        	`Content` = #message.content#,
        	`EmailSwitch` = #message.emailSwitch#,
        	`SmsSwitch` = #message.smsSwitch#,
        	`PMSwitch` = #message.pmSwitch#,
        	`MessageFromName` = #message.messageFromName#,
        	`UpdateTime` = #message.updateTime#
      	where
        	MessageId = #message.messageId#
    
      update EP_Core_Message
      	set
	     	Status=1
      	where
        	`MessageId` = #messageId#
    
      update BA_MC_PostMessage
      	set
        `AddDate` = #message.addDate#,
        `UpdateDate` = #message.updateTime#,
        `FromLoginId` = #message.fromLoginId#,

        `FromName` = #message.fromName#,
        `FromSys` = #message.fromSys#,
        `ToLoginId` = #message.toLoginId#,
        `Status` = #message.status#,

        `Title` = #message.title#,
        `Content` = #message.content#,
        `Category1` = #message.category1#,
        `Category2` = #message.category2#,
        `Extra` = #message.extra#
      	where
        	`Id` = #message.messageId#
    
		UPDATE
			BC_Questionnaire
		SET 
			Title = #title#,
			URL = #url#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
		BC_ShopAccount
		SET ShopAccount = #shopAccountId#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE BC_ShopAccount
		SET Password = #password#,UpdateTime=#updateTime#,PasswordVersion=PasswordVersion+1
		Where
		ShopAccountId=#shopAccountId#
    
    
    			and sa.AccountType=#accountType# 
    			and sa.ShopAccountId = sas.ShopAccountId 
    		join BC_ShopAccountModule sam on sam.Module=#module# 
    			and sa.ShopAccountId=sam.ShopAccountId
    	) t 
    	set sa.Password= #password#, sa.UpdateTime=#updateTime#,PasswordVersion=PasswordVersion+1
    	where sa.`ShopAccountId` =t.`ShopAccountId`;
    
		UPDATE BC_ShopAccount
		SET ContactMobileNO = #mobile#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE
		BC_ShopAccount
		SET Status = -1,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE BC_ShopAccount
		SET ContactName = #contactName#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE
		BC_ShopAccount_Customer
		SET CustomerName = #customerName#
		Where
		ShopAccountId=#shopAccountId#
    
  		UPDATE CI_IPhonePush SET PToken = '' WHERE PToken = #ptoken#
   
		
			UPDATE AD_Order 
			SET ShopType = #colInfo.ShopType.newValue#,MainCategoryId = #colInfo.MainCategoryID.newValue#,
				Amount = #colInfo.Amount.newValue#,SalesCityId = #colInfo.SalesCityID.newValue#,
				PriceLoadTime = #colInfo.SubmitTime.newValue#,SourceType = #colInfo.SourceType.newValue#,
				Status = #colInfo.Status.newValue#,UpdateTime = NOW()
			WHERE OrderId = #colInfo.ID.newValue#;
		
		
	
		
			UPDATE AD_CRMRelation 
			SET OpportunityId = #colInfo.OpportunityID.newValue#,ContractId = #colInfo.ContractNO.newValue#, 
				OrderId = #colInfo.OrderID.newValue#,ADDTIME =#colInfo.AddTime.newValue#, 
				UpdateTime =#colInfo.UpdateTime.newValue#
			WHERE CRMRelationId = #colInfo.ID.newValue#;
		
	
	
	
	
		
	
    	UPDATE CI_ConfigParam
    	SET ParamID = #ParamID#, ParamKey = #ParamKey#, ParamValue = #ParamValue#, ValueType = #ValueType#, ParamDomain = #ParamDomain#, ClientLimit = #ClientLimit#, LimitID = #LimitID# 
    	WHERE ParamID = #OriParamID#
    
    	UPDATE CI_ConfigParamLimit
    	SET LimitID = #LimitID#, VersionLimit = #VersionLimit#, SourceLimit = #SourceLimit#, Proprioty = #Proprioty#, Ratio = #Ratio#, OSLimit = #OSLimit# 
    	WHERE LimitID = #OriLimitID#
    
		UPDATE Mail_Queue_Inner
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE ID = #mailId# AND status = #oldStatus#
    
		UPDATE Mail_Queue_Inner
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE status = #oldStatus#
		ORDER BY Rank DESC, ID ASC
		Limit #number#
	
		UPDATE Mail_Queue_Inner
		SET Trytimes = #tryTimes#, UpdateDate = NOW()
		WHERE ID = #mailId#
    
		WHERE MovieReservation_ID = #id# AND UserID = #userId#
	
		WHERE MovieReservation_ID = #id# AND UserID = #userId#
	
		update TG_MovieReservation A
		set A.Status = #status#,
		A.UpdateDate = now()
		where A.MovieReservation_ID = #id#
	
		UPDATE TG_MovieReservation
		SET
		UpdateDate = now(),
		Status = 4
		WHERE MovieReservation_ID = #id# AND UserID = #userId#
	
		UPDATE
		MP_ShpAcnt_Msg_Assn
		SET isAppReaded = 1
		Where
		ShopAccountId=#shopAccountId# and msgId = #msgId#
    
		UPDATE
		MP_ShpAcnt_Msg_Assn
		SET isWebReaded = 1
		Where
		ShopAccountId=#shopAccountId# and msgId = #msgId#
    
    
 		UPDATE CI_MerryChris 
 		SET msgstatus=#msgStatus#, updatetime=#updateTime# 
 		WHERE dpid = #dpid#
 	
   
   		UPDATE CI_CheckIn 
   		SET Status = #status# 
   		WHERE CheckInID = #checkInID#		
   
   		UPDATE CI_UserInfo 
   		SET CheckInCount = CheckInCount + #checkincount# ,  TotalScore = TotalScore + #score#
   		WHERE userId = #userId#		
   
    	UPDATE DianPingMobile.CI_CheckInTopic 
		SET TotalCheckIn = TotalCheckIn + 1
		WHERE ID = #TopicID#
    
   		UPDATE CI_CheckIn 
   		SET TotalComment = TotalComment + #increment# 
   		WHERE CheckInID = #checkInID#
	
    	UPDATE CI_CheckIn 
    	SET ShopID=#toShopId# WHERE ShopID=#fromShopId#;
    
		UPDATE CI_CheckIn SET Status = 4
		WHERE CheckInID=#checkInId#
    
    	UPDATE CI_CheckIn set PicCenterUrl = '', photos = ''
		WHERE CheckInID = (select checkinid from DP_CheckInPic where picid = #picId#)
    
        UPDATE CI_CheckIn
        SET ShopID=#toShopId#
        WHERE CheckInID = #checkInId# AND ShopID=#fromShopId#
    
		UPDATE OSS_HotAds 
		SET Title = #hotAdsPO.title#, SubTitle = #hotAdsPO.subTitle#, PicUrl = #hotAdsPO.picUrl#, UrlSchema = #hotAdsPO.link#, TYPE = #hotAdsPO.type#, Priority = #hotAdsPO.priority#, 
			BeginTime = #hotAdsPO.beginTime#, EndTime = #hotAdsPO.endTime#, CityFlag = #hotAdsPO.cityFlag#, CityIDs = #hotAdsPO.cityIds#, Yidi=#hotAdsPO.yidi#, Flag=2, UpdateTime = NOW()
		WHERE ID = #hotAdsPO.id#
	
		UPDATE OSS_HotAds_Client 
		SET SourceFlag = #hotAdsClientPO.sourceFlag#, Sources = #hotAdsClientPO.sources#, 
			MinVersion = #hotAdsClientPO.minVersion#, MaxVersion = #hotAdsClientPO.maxVersion#, UpdateTime = NOW()
		WHERE ID = #hotAdsClientPO.id#
	
		UPDATE OSS_HotAds 
		SET UpdateTime = NOW(), Flag = 0
		WHERE ID = #ID#
	
		UPDATE OSS_HotAds 
		SET UpdateTime = NOW(), Flag = #Flag#
		WHERE ID = #ID#
	
		UPDATE OSS_HotAds_Client 
		SET Flag = 0, UpdateTime = NOW()
		WHERE ID = #ID# 
	
		UPDATE CI_CustomPush 
		SET TargetType = #customPushDO.targetType#, PushText = #customPushDO.pushText#, Link = #customPushDO.link#,  Utm = #customPushDO.utm#,
			BeginTime = #customPushDO.beginTime#, EndTime = #customPushDO.endTime#, PlatformIDs = #customPushDO.platformIds#, AddNotify = #customPushDO.addNotify#, PushSound = #customPushDO.pushSound#,
			OperateName = #customPushDO.operateName#, Status = -1, UpdateTime = NOW()
		WHERE CustomPushID = #customPushDO.customPushId#
	
		UPDATE CI_CustomPush 
		SET STATUS = -2, UpdateTime = NOW()
		WHERE CustomPushID = #customPushId#
	
		UPDATE CI_CustomPushFileUpload 
		SET ContentType = #contentType#, FileName = #fileName#
		WHERE CustomPushID = #customPushId#
	
		UPDATE CI_CustomPush 
		SET Status = 1, UpdateTime = NOW()
		WHERE CustomPushID = #customPushId# AND Status = 0
	
		UPDATE CI_CustomPush 
		SET Status = #status#, UpdateTime = NOW()
		WHERE CustomPushID = #customPushId#
	
		UPDATE CI_CustomPushFileUpload 
		SET TotalNum = #totalNum# + TotalNum, SendNum = #sendNum# + SendNum, ArrivalNum = #arrivalNum# + ArrivalNum
		WHERE CustomPushID = #customPushId#
	
		UPDATE CI_CustomPushFileUpload 
		SET ArrivalNum = #increaseNum# + ArrivalNum
		WHERE CustomPushID = #customPushId#
	
        UPDATE TG_MovieDiscountVoucher
        SET Status = #newStatus#, UpdateDate = NOW()
        WHERE MovieDiscountVoucherID = #movieDiscountVoucherId# AND UserID = #userId# AND Status = #oldStatus#
    
	
		UPDATE Mobile_API 
		SET APIName = #mobileAPIDo.apiName#, TypeName = #mobileAPIDo.apiType#, APIDesc = #mobileAPIDo.apiDesc#, PlatformType = #mobileAPIDo.apiPlatform#, EndVer = #mobileAPIDo.apiEndVer#,
			CombinWay = #mobileAPIDo.apiCombinWay#, UpdateTime = #mobileAPIDo.apiUpdateTime#, BeginVer = #mobileAPIDo.apiBeginVer#, ReturnModel = #mobileAPIDo.apiReturnRef#
		WHERE ID = #mobileAPIDo.id#
	
        UPDATE Mobile_API
        SET APIStatus = 0  WHERE ID=#apiId#
    
        UPDATE Mobile_API
        SET APIStatus = 1  WHERE ID=#apiId#
    
		
	    UPDATE TEMP_Order
		SET
		  UploadedFileCount = #uploadedFileCount#,
		  UploadedFileNames = #uploadedFileNames#,
		  LOG = #log#,
		  STATUS = #status#
		WHERE Id = #id#
		
	
         update ASPNet_zSurvey.DP_Promo
         set Power=2
         WHERE PromoID=#promoid#
	
		UPDATE ASPNet_zSurvey.DP_Promo
		SET EndDate=#releaseendtime#, PromoEndDate=#validendtime# 
		WHERE PromoID=#promoid#
	
		
			UPDATE MMC_Account
			SET 
			frozen = frozen + #fee#,
			balance = balance - #fee#
			WHERE uId = #userId#
		
	
		
			UPDATE MMC_Account
			SET 
			frozen = frozen - #fee#
			WHERE uId = (SELECT uid FROM MMC_AdInfo WHERE adId = #adId#)
		
	
		
			UPDATE MMC_Account
			SET 
			balance = balance + #fee#
			WHERE uId = #userId#
		
	
		
			UPDATE MMC_Account
			SET 
			frozen = #userAccountFeeEntity.frozen#,
			consume = #userAccountFeeEntity.consume#,
			WHERE uId = #userAccountFeeEntity.uId#
		
	
		
			UPDATE MMC_Account
			SET 
			frozen = frozen - #fee#
			WHERE uId = #uId#
		
	
         
        UPDATE MSD_ItemInfo
        SET `Status` = #status#
        WHERE ItemId in ($itemIds$)
        
    
         
        UPDATE MSD_Contact
        SET `Status` = #status#
        WHERE `Status` = 100
        
    
		UPDATE CI_BizPush 
		SET Status = #status#, UpdateTime = NOW()
		WHERE BizPushID = #bizPushId#
	
		UPDATE OSS_BizPushApply 
		SET UpdateTime = NOW(), Flag = 0
		WHERE ID = #ID# AND Flag = 1
	
    	UPDATE Campus_promote
    	SET source = #source#, channelAppName = #apkUrl#, UpdateTime = now()
    	WHERE promoteId=#promoterId#
    
    	UPDATE Campus_promote
    	SET promoteNum = promoteNum+1, UpdateTime = now()
    	WHERE promoteId=#promoterId#
    
		update  Campus_promote
		set province=#province#, cityName=#cityName#,school=#school#,name=#name#,phoneNo=#phoneNo#,
			major=#major#,email=#email#,bank=#bank#,bankAccount=#bankAccount#,
			enrollDate=#enrollDate#,graduteDate=#graduteDate#
		where promoteId=#promoterId#
	
    	UPDATE Campus_promote
    	SET parentId=#parentId#, UpdateTime = now()
    	WHERE promoteId=#promoterId#
    
    	UPDATE Campus_promote
    	SET parentId=#parentId#,
    	    area=#area#,
    	    province=#province#,
    	    cityName=#cityName#,
    	    name=#name#,
    	    school=#school#,
    	    phoneNo=#phoneNo#,
    	    major=#major#,
    	    UpdateTime = now()
    	WHERE promoteId=#promoteId#
    
		UPDATE CI_HotModule 
		SET Title = #hotModuleDO.title#, SubTitle = #hotModuleDO.subTitle#, PicUrl = #hotModuleDO.picUrl#, ModuleUrl = #hotModuleDO.moduleUrl#,
			CityIDs = #hotModuleDO.cityIds#, PlatformIDs = #hotModuleDO.platformIds#, Versions = #hotModuleDO.versions#,  
			BeginTime = #hotModuleDO.beginTime#, EndTime = #hotModuleDO.endTime#, UpdateTime = NOW()
		WHERE ModuleID = #hotModuleDO.moduleId#
	
		UPDATE CI_HotModule 
		SET Status = #status#, UpdateTime = NOW()
		WHERE ModuleID = #moduleId#
	
	
    
    	 update environment set name=#name#,ip=#ip#
    	 where id=#id#
    
	
    
    	 update middlewareTree set parent_id=#parentId#,name=#name#
    	 where id=#id#
    
	
        
    	update  middlewareDocument set  name=#name#,url=#url#,des=#des#
    	where  id=#id# 
	
        
    	update  middlewareNotice set  content=#content#,title=#title#,updateTime=#updateTime#
    	where  id=#id# 
	
		 
    		 update sqlReview set sqlContent=#sqlContent#,reviewTime=#reviewTime#,
    		 reviewerId=#reviewerId#,recommendation=#recommendation#,state=1  where id=#id#
   		 
	
    
    	 update sqlReview set sqlContent=#sqlContent#,reviewTime=#reviewTime#,
    	 reviewerId=#reviewerId#,state=0 where id=#id#
    
	
    
    	 update jrobin_host set name=#name#,ip=#ip#,port=#port#,user_name=#userName#,
    	 user_password=#userPsw#,path=#path#,isFetch=#isFetch#,weight=#weight#,type=#type#,port1=#port1#
    	 where id=#id#
    
    
		UPDATE  MC_MemberCardOrder SET STATUS=#status# WHERE MemberCardOrderID=#memberCardOrderId# AND STATUS=0
	
		UPDATE  MC_MemberCardOrder SET PayOrderID=#payOrderId# WHERE MemberCardOrderID=#memberCardOrderId#
	
    	UPDATE CI_Comments 
    	SET Status=#status# WHERE CommentID=#commentId#;
    
		UPDATE SMS_TypeInfo SET
			Rank = #rank#, 
			Remark = #remark#,
			Content = #content#, 
			Subs = #subs#, 
			AddTime = NOW()
		WHERE
			SmsType = #smsType#
	
		

			INSERT INTO BC_ShopAccount_Privilege
			(`ShopAccountId`,`Privileges`,`AddDate`,`UpdateDate`) VALUES (#shopAccountId#,#privileges#,now(),now());

        
	
		

			UPDATE BC_ShopAccount_Privilege 
			SET Privileges=#privileges#,UpdateDate=now()
			WHERE ShopAccountId = #shopAccountId#;

        
	
	    
		UPDATE DianPingBC.MP_MessageToken 
		SET
		IsValid = #entity.isValid#
		WHERE
		Id = #entity.id# ;
		
	
		UPDATE DP_Shop 
		SET Power = #power#
		WHERE ShopID = #shopID#
	
		UPDATE DP_Shop 
		SET GLat = #lat#,GLng= #lng#		
		WHERE ShopID = #shopID#
	
		UPDATE CI_Comments 
		SET STATUS = 4 
		WHERE CommentID=#CommentID# AND UserID=#UserID#
	
		UPDATE CI_Comments, CI_CheckIn 
		SET CI_Comments.Status = 4 
		WHERE CI_Comments.RootCheckInID = CI_CheckIn.CheckInID AND 
			CI_Comments.CommentID = #CommentID# AND CI_CheckIn.UserID = #UserID#
	
    	UPDATE CI_Comments 
    	SET Status=#status# WHERE CommentID=#commentId#;
    
	
		UPDATE CI_SamsungWalletUserInfo
		SET
		 	SerialNumber=#serialNum#,
		 	UpdateTime=NOW()
		WHERE
			dpid=#dpid# and dpTicketId=#dpTicketId# and type=#type#
	
	
	
		UPDATE CI_SamsungWalletUserInfo
		SET
		 	UpdateTime=NOW()
		WHERE
			SerialNumber=#serialNum#
	
	
    
        UPDATE CI_SamsungWallet_Promo
        SET
            SmsInfo = #smsInfo#,
            BeginDate = #beginDate#,
            EndDate = #endDate#,
            UpdateTime = #updateTime#
        WHERE
            PromoId = #promoId#;
    
    
		UPDATE CI_HP_Index_Record
		set residence_list = #residenceList#, tourist_list = #touristList#
		WHERE city_id = #cityId#
	
		update CI_NoviceGiftDetail
		set userid=#userId#, phone=#phone#, couponid=#couponId#,ticketId=#ticketId#,grandtime=#grandTime#,status=#status#
		where eventid=#eventId# and deviceid=#dpid#
	
		update CI_NoviceGiftDetail
		set Status = #status#
		where eventid = #eventId# and DeviceID = #dpid#
	
		UPDATE Mail_Queue
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE ID = #mailId# AND status = #oldStatus#
    
		UPDATE Mail_Queue
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE status = #oldStatus#
		ORDER BY ID ASC
		Limit #number#
	
		UPDATE Mail_Queue
		SET Trytimes = #tryTimes#, UpdateDate = NOW()
		WHERE ID = #mailId#
    
	
        
        UPDATE MP_Operation_Merchant
        SET ParentMerchantId = #parentMerchantId#, Updater = #updater#
        WHERE MerchantId = #merchantId#
        
    
		UPDATE BC_ShopAccountEmail
		SET Status = #status#,VerifyCode=#verifyCode#,VerifyCodeTime=#verifyCodeTime#
		Where ShopAccountId=#shopAccountId#
    
		UPDATE BC_ShopAccountEmail
		SET Status = #status#,ActivedTime=#activedTime#
		Where ShopAccountId=#shopAccountId#
    
		UPDATE BC_ShopAccountEmail
		SET Email = #email#,Status = #status#,AddTime=#addTime#,
		VerifyCode=null,VerifyCodeTime=null,ActivedTime=null
		Where ShopAccountId=#shopAccountId#
    
    	UPDATE BC_ShopAccount
    	SET Email = #email# 
    	Where ShopAccountId=#shopAccountId#
    
		UPDATE BC_Tip
		SET
		Document=#entity.document#,
		Url=#entity.url#,
		Status=#entity.status#,
		LastUpdateTime=#entity.lastUpdateTime#,
		LastUpdatorId=#entity.lastUpdatorId#,
		CreateTime=#entity.createTime#
		WHERE
		TipId=#entity.id#
	
		UPDATE DianPingBC.MP_Device_Account_Assn 
		SET
		IsLogin = #entity.isLogin#
		, LastLoginTime = #entity.lastLoginTime#
		, LastLogoutTime = #entity.lastLogoutTime#
		WHERE
		Id = #entity.id# ;
	
	    
		UPDATE DianPingBC.MP_MobileDevice 
		SET
		UUID = #entity.uuid# ,
		OSType = #entity.osTypeId# ,
		OSDetail = #entity.osDetail# ,
		DeviceModel = #entity.deviceModel# ,
		AppVersion = #entity.appVersion#,
		 IsFailReplyNeed = #entity.isFailReplyNeed#,
		IsBadReviewNeed = #entity.isBadReviewNeed#
		WHERE
		Id = #entity.id# ;
        
	
        UPDATE MOPay_Discount
        SET Status = #status#, UpdateTime =NOW()
        WHERE DiscountID = #discountID#
        LIMIT 1;
    
        
           UPDATE  MOPay_Discount
           SET Status=#status#, UpdateTime =NOW()
           WHERE OrderID = #orderID#
           LIMIT 1;
         
    
        UPDATE MOPay_Settle_Contract
        SET BankAccountName=#bankAccountName#, BankAccountNumber=#bankAccountNumber#, BankName=#bankName#, BankProvince=#bankProvince#,
         BankCity=#bankCity#, FreeStart=#freeStart#, FreeEnd=#freeEnd#, TaxRate=#taxRate#, ShopAccountID=#shopAccountId#, UpdateTime=NOW()
        WHERE  ContractID = #contractId#
    
        update MOPay_Settle_Finance
        set status = #status#
        where FinanceNumber = #financeNumber#
    
    
    	UPDATE CI_IPhonePush
    	SET PToken = #pushToken#, UpdateTime = null
    	WHERE DeviceID = #deviceId# AND ClientID = #clientId#
    
        UPDATE CI_IPhonePush
        SET ClientID = #clientId#, PToken = #pushToken#, UpdateTime = null
        WHERE DeviceID = #deviceId#
    
    	UPDATE CI_IPhonePush
    	SET UserID = #userId#, UpdateTime = null
    	WHERE DeviceID = #deviceId# AND ClientID = #clientId#
    
		UPDATE CI_CustomPush 
		SET Status = #status#, UpdateTime = NOW()
		WHERE CustomPushID = #customPushId#
	
		UPDATE CI_CustomPush 
		SET Status = 1, UpdateTime = NOW()
		WHERE CustomPushID = #customPushId# AND Status = 0
	
		UPDATE CI_CustomPushFileUpload 
		SET SendNum = #sendNum# + SendNum, ArrivalNum = #arrivalNum# + ArrivalNum
		WHERE CustomPushID = #customPushId#
	
        UPDATE CI_GiftEvent
        SET Valid = 0
        WHERE Id = #id#
    
        UPDATE CI_GiftEvent
        SET EventId = #dto.eventId#, Source = #dto.source#, MessageContent = #dto.messageContent#,
            VersionCondition = #dto.versionConditions#, CouponGroupId = #dto.couponGroupId#,
            InvalidCouponGroupId = #dto.invalidCouponGroupId#, NewUserLimit = #dto.newUserLimit#,
            MaxCount = #dto.maxCount#, MessageType = #dto.messageType#, ShouldHasPhoneNumber = #dto.shouldHasPhoneNumber#,
            `Value` = #dto.value#, BeginDate = #dto.beginDate#, EndDate = #dto.endDate#, UpdateTime = NULL
        WHERE Id = #dto.id#
    
   		
   		Update DP_TGNotice
   		set Status=2
   		WHERE UserID = #userId# AND NoticeId = #noticeId# AND status=1
   		
	
   		
   		Update DP_TGNotice
   		set Status=2
   		WHERE UserID = #userId# AND NoticeType = #noticeType# AND status=1
   		
	
        UPDATE MOPay_OrderReceipt r SET r.UpdateTime = Now(),r.Status = 1 WHERE r.Status=0 and r.OrderID = #orderID# and r.ShopID =  #shopID#
    
        UPDATE MOPay_OrderReceipt r SET r.UpdateTime = Now(),r.Status = 2 WHERE r.Status=0 and r.OrderID = #orderID# and r.ShopID =  #shopID#
    
        UPDATE MOPay_OrderReceiptPool r SET r.UpdateTime = Now(),r.Status = 1 WHERE r.Status=0 and r.SerialNumber = #serialNumber#
    
        UPDATE TG_MovieDiscountItem
        SET CurrentJoin = CurrentJoin + #quantity#, UpdateDate = NOW()
        WHERE MovieDiscountItemID = #movieDiscountItemId# AND (CurrentJoin + #quantity# <= MaxJoin)
    
        UPDATE TG_MovieDiscountItem
        SET CurrentJoin = CurrentJoin - #quantity#, UpdateDate = NOW()
        WHERE MovieDiscountItemID = #movieDiscountItemId# AND (CurrentJoin - #quantity# >= 0)
    
        UPDATE TG_MovieDiscountItem
        SET CurrentJoin = #currentJoin#, UpdateDate = NOW()
        WHERE MovieDiscountItemID = #movieDiscountItemId# AND CurrentJoin != #currentJoin# AND #currentJoin# <= MaxJoin AND #currentJoin# >= 0
    
		UPDATE Data_Model 
		SET ModelName = #dataModelDo.modelName#, ModelHash = #dataModelDo.modelHash#, ModelStatus = #dataModelDo.modelStatus#, 
		UpdateTime = #dataModelDo.modelUpdateTime#, BeginVer = #dataModelDo.beginVer#, EndVer = #dataModelDo.endVer#, ModelDesc = #dataModelDo.modelDesc#
		WHERE ID = #dataModelDo.id#
	
        UPDATE Data_Model
        SET ModelStatus = 0  WHERE ID=#modelId#
    
        UPDATE Data_Model
        SET ModelStatus = 1  WHERE ID=#modelId#
    
		UPDATE
			MOBILE_VERSION
		SET
			Name=#name#,
			LockTime=#lockTime#,
			GrayTime=#grayTime#,
			PublishTime=#publishTime#,
			VersionInfo=#versionInfo#,
			UpdateTime=NOW()
		WHERE
			ID=#id#
	
		
			UPDATE MC_FileOrderCopy 
			SET FileTitle =#fileTitle#,
			FileDescription=#fileDescription#,
			Height =#height#,
			Width =#width#,
			RealHeight =#realHeight#,
			RealWidth =#realWidth#,
			MODE =#mode#,
			UpdateTime=#updateTime# 
			WHERE Id=#id# 
		
	
        
          UPDATE ASPNet_zSurvey.DP_PromoChangeLog
          SET Status = #status#, UpdateTime = GETDATE()
          WHERE ID = #id#;
        
    
		UPDATE CI_CommonPush 
		SET Status = #status#, UpdateTime = NOW()
		WHERE CommonPushID = #commonPushId#
	
		UPDATE CI_CommonPush 
		SET Status = 1, UpdateTime = NOW()
		WHERE CommonPushID = #commonPushId# AND Status = 0
	
		UPDATE CI_MsgType 
		SET Title = #msgType.title#, OnDesc = #msgType.onDesc#, OffDesc = #msgType.offDesc#,
		        Icon = #msgType.icon#, BottomDesc = #msgType.bottomDesc#,
		        BenefitCount = #msgType.benefitCount#, BenefitName = #msgType.benefitName#,
		        Sort = #msgType.sort#,  UpdateTime = NOW()
		WHERE ID = #msgType.type#
	
		UPDATE CI_MsgType 
		SET UpdateTime = NOW(), Flag = 0
		WHERE ID = #ID# 
	
    	update nginx_report_rule set project=#project#, status=#status#, 
    	url=#url#, threshold=#threshold#, reportName=#reportName#
    	where id=#id#
    
    
    	 update jrobin_datasource set name=#name#, key1=#key1#,key2=#key2#,key3=#key3#,
    	 key4=#key4#,data_type=#dataType#,host_names=#hostNames#,step=#step#,rrd_path=#rrdPath#,timespan=#timespan#,data_choice=#dataChoice#
    	 where id=#id#
    
    
     
    	 update jrobin_datasource set is_active=? where id=?
    
    
     
    	 update jrobin_datasource set process_id=? where id=?
    
    
    
    	 update jrobin_rra set name=#name#, cf=#cf#,
    	 xff=#xff#,steps=#steps#,rows=#rows#,timespan=#timespan#
    	 where id=#id#
    
    
		UPDATE DianPingMobile.CI_CheckInTopicDetail 
		SET Hot = #Hot#, CommentIDs = #CommentIDs#
		WHERE CheckInID = #CheckInID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET TotalCheckIn = #TotalCheckIn#, TopicStatus = #TopicStatus#
		WHERE ID = #TopicID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET TopicStatus = #TopicStatus# 
		WHERE NOW() > EndDate
	
    	UPDATE DianPingMobile.CI_SceneryOrderDetail 
		SET Status = #status#
		WHERE SerialId = #serialId#
    
    	UPDATE DianPingMobile.CI_SceneryOrderDetail 
		SET Status = #status#, EnableCancel = #enableCancel#, TotalPrice = #totalPrice#
		WHERE SerialId = #serialId#
    
		UPDATE MC_MemberCardIVRAccount SET Deleted = 1
		WHERE IVRAccountID = #ivrAccountID#
	
		UPDATE MC_MemberCardUser SET Status = #status#
		WHERE UserID = #userId# AND MemberCardID = #cardId#
	
		UPDATE MC_MemberCardUserShop SET Status = #status#
		WHERE UserID = #userId# AND MemberCardID = #cardId# AND ShopID = #shopId#
	
	  
	    UPDATE MC_MemberCardUser SET UserID = #userId# WHERE UserID = #nUserId#
	  
		UPDATE MC_MemberCardUserShop  SET UserID = #userId# WHERE UserID = #nUserId#
	  
		UPDATE MC_MemberCardUserFeed SET UserID = #userId# WHERE UserID = #nUserId#
	  
		UPDATE MC_MemberCardNumberUser SET UserID = #userId# WHERE UserID = #nUserId#
	  
		UPDATE MC_MemberCardConsume SET UserID = #userId# WHERE UserID = #nUserId#
	  
	    UPDATE MC_MemberCardUser SET CardNO = #cardNO# WHERE MemberCardUserID = #memberCardUserId#
	
       UPDATE MC_MemberCardUserInfo SET UserName=#userName#,UserSex =#userSex# ,UserBirthDay=#userBirthDay#,SynchronizFlag=#synchronizFlag# WHERE UserID =#userId# 
    
   		UPDATE CI_UserExtend 
   		SET ParamValue=#snsImportedFlags# 
        WHERE UserId=#userId# AND ParamName='SnsImportedFlags' 
   
		UPDATE
		DP_SubscriptionList
		set CityID=#cityId#
		where UserId=#userId#
	
		UPDATE DP_HeaderBusinessSourceCode SET
			Status = #status#,
			UpdateTime = NOW()
		WHERE
			ID = #id#
	
		UPDATE DP_HeaderBusinessSourceCode SET		   
			BusinessSourceCodeContent = #businessSourceCodeContent#,
			Status = #status#,
			Description = #description#,
			UpdateTime = NOW()
		WHERE
			 BusinessSourceCodeName = #businessSourceCodeName#
	
    	UPDATE CI_CheckInTopic 
		SET BeginDate = #BeginDate#
		WHERE ID = #ID# 
    
    	UPDATE CI_UserInfo 
    	SET CheckInCount = CheckInCount + #checkInCount#
    	WHERE UserID = #userId#
    
		update CI_NoviceGiftDetail
		set userid=#userId#, phone=#phone#, couponid=#couponId#,ticketId=#ticketId#,grandtime=#grandTime#,status=3
		where eventid=#eventId# and deviceid=#dpid#
	
		update CI_NoviceGiftDetail
		set Status = #status#
		where DeviceID = #deviceId#
	
		update CI_NoviceGiftDetail
		set Status = #status#
		where eventid = #eventId# and DeviceID = #dpid#
	
    
        UPDATE CI_SamsungWallet_Promo
        SET
            SmsInfo = #smsInfo#,
            BeginDate = #beginDate#,
            EndDate = #endDate#,
            UpdateTime = NOW()
        WHERE
            PromoId = #promoId#;
    
    
		UPDATE DianPingMobile.CI_CheckInTopicDetail 
		SET Hot = #Hot#, CommentIDs = #CommentIDs#
		WHERE CheckInID = #CheckInID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET TotalCheckIn = #TotalCheckIn#, TopicStatus = #TopicStatus#
		WHERE ID = #TopicID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET TopicStatus = #TopicStatus# 
		WHERE NOW() > EndDate
	
		UPDATE Mail_Queue
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE ID = #mailId# AND status = #oldStatus#
    
		UPDATE Mail_Queue
		SET status = #newStatus#, UpdateDate = NOW()
		WHERE status = #oldStatus#
		ORDER BY Rank DESC, ID ASC
		Limit #number#
	
		UPDATE Mail_Queue
		SET Trytimes = #tryTimes#, UpdateDate = NOW()
		WHERE ID = #mailId#
    
 		UPDATE CI_SceneryOrderDetail 
 		SET SerialID = #serialId#
 		WHERE ID = #id#
 	
 		UPDATE CI_TCScenery
 		SET hasTicket = #ticket#
 		WHERE tcSceneryId = #sceneryId#
 	
    	UPDATE DianPingMobile.CI_SceneryOrderDetail 
		SET Status = #status#, EnableCancel = #enableCancel#, TotalPrice = #totalPrice#
		WHERE SerialId = #serialId#
    
        
			UPDATE MP_Operation_Shop
            SET MerchantId=#merchantId#, Status = #status#, UpdateTime = NOW(), Updater = #updater#
            WHERE ShopId = #shopId#;
        
     
        
			UPDATE MP_Operation_Shop
            SET MerchantId=#merchantId#, Status = 1, UpdateTime = NOW(), Updater = #updater#
            WHERE ShopId = #shopId#;
        
     
		UPDATE
			BC_Questionnaire
		SET 
			Title = #title#,
			URL = #url#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
	    
		UPDATE MP_MerchantMessageStatistics
		SET
            IsNotified=#entity.isNotified#,
            NotifiedTime=#entity.notifiedTime#
		WHERE
		    MessageId=#entity.messageId#
		
	
	    
		UPDATE DianPingBC.MP_MessageToken 
		SET
		IsValid = #entity.isValid#
		WHERE
		Id = #entity.id# ;
		
	
		
		UPDATE DP_MessageList
		SET Status=#status# , AddTime = #timestamp#
		WHERE OwnerUserID <> FromUserID AND DetailID=#detailId#
		
	
		UPDATE 
			ZS_FollowNote 
		SET
			MainNoteTitle=#mainNoteTitle#
		WHERE
			WHERE MainNoteID  = #reviewId# AND NoteType=11
	
		UPDATE WAPM_URLRewriteRules 
		SET maskurl=#maskurl# WHERE realurl='~/home/getmorecategory.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/getmoreregion.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/gethomedetail.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/home/metroList.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/food/foodDetail.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/getLandmarkList.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/mylist/getMyList.action?cityPinYin=$$1'
	
 			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/mylist/getMyList.action?cityPinYin=$$1&tagId=$$2'
	
		UPDATE WAPM_URLRewriteRules 
		SET maskurl=#maskurl# WHERE realurl='~/home/getmorecategory.action?cityPinYin=$$1'
		
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/getmoreregion.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/gethomedetail.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/home/metroList.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl = '~/food/foodDetail.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/home/getLandmarkList.action?cityPinYin=$$1'
	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/mylist/getMyList.action?cityPinYin=$$1'
	
 	
			UPDATE WAPM_URLRewriteRules 
			SET maskurl=#maskurl# WHERE realurl='~/mylist/getMyList.action?cityPinYin=$$1&tagId=$$2'
	
	
        UPDATE MP_PosToken
        SET token = #token#, expirationTime = #expirationTime#,
            validPeriod = #validPeriod#, updateTime = NOW()
        WHERE shopAccountId = #shopAccountId#
    
        UPDATE MOPay_OrderReceiptPool r SET r.UpdateTime = Now(),r.Status = 1 WHERE r.Status=0 and r.SerialNumber = #serialNumber#
    
        SELECT SerialNumber
        FROM MOPay_OrderReceiptPool
        WHERE Status = 0
        LIMIT 1
    
        UPDATE MOPay_Shop
        SET Status = #status#, UpdateTime = NOW()
        WHERE ShopID = #shopId#
    
		
			UPDATE DP_Shop_Contact_Material
			SET MaterialPath = #shopContactMaterialInfo.path#,
			UpdateTime= NOW()
			WHERE ShopContactID=#contactId# AND MaterialType= #shopContactMaterialInfo.type#
		
	
		UPDATE CI_Announcement 
		SET Title = #annoucement.title#, PageUrl = #annoucement.pageUrl#, Utm = #annoucement.utm#, BeginTime = #annoucement.beginTime#,
			EndTime = #annoucement.endTime#, CityIDList = #annoucement.cityIdList#, Priority = #annoucement.priority#, Flag = 0
		WHERE AnnouncementID = #annoucement.annoucementId#
	
        UPDATE CI_Announcement
        SET Flag = -1
        WHERE AnnouncementID = #announcementId#
    
		UPDATE CI_AnnoucementClient 
		SET Flag = 0
		WHERE Flag = 1 AND AnnouncementID = #annouceId#
	
		UPDATE CI_Announcement 
		SET Flag = #Flag#
		WHERE AnnouncementID = #AnnouceID#
	
		update DP_ScenicSpots
		set status=#status#
		where shopid=#shopId#
		and sid=#otaId#
	
		UPDATE DianPingHotel.DP_OTAScenicSpotsOrderDetail
		SET 
		OrderStatus = #orderDetail.orderStatus#,
		TotalPrice = #orderDetail.orderBasicInfo.amount#,
		TicketAmount = #orderDetail.orderBasicInfo.quantity#,
		PolicyName = #params.policyName#,
		TicketUseDate = #params.ticketUserDate#,
		Notice = #params.notice#,
		UpdateTime = #params.updateTime# 
		WHERE OTAOrderId= #orderDetail.orderId#
		AND
		OTAId = 2
	
        UPDATE TG_MovieDiscountOrder
        SET Status = #newStatus#, UpdateDate = NOW()
        WHERE MovieDiscountOrderID = #movieDiscountOrderId# AND Status = #oldStatus#
    
		UPDATE API_Param 
		SET ParamName = #mobileAPIParamDo.paramName#, IsRequired = #mobileAPIParamDo.isRequired#, ParamDesc = #mobileAPIParamDo.paramDesc#, UpdateTime = #mobileAPIParamDo.paramUpdateTime#,
		BeginVer = #mobileAPIParamDo.beginVer#, EndVer = #mobileAPIParamDo.endVer#,ParamType = #mobileAPIParamDo.paramType#
		WHERE ID = #mobileAPIParamDo.id#
	
        UPDATE API_Param
        SET ParamStatus = 0  WHERE ID=#paramId#
    
		UPDATE Model_Field 
		SET FieldType = #modelFieldDo.fieldType#, FieldRef = #modelFieldDo.fieldRef#, DataModel =  #modelFieldDo.modelRef#, FieldDesc = #modelFieldDo.fieldDesc#, FieldHash = #modelFieldDo.fieldHash#,CreateUser = #modelFieldDo.createUser#,
			CombinWay = #modelFieldDo.combinWay#, FieldStatus = #modelFieldDo.fieldStatus#, FieldName = #modelFieldDo.fieldName#, UpdateTime = #modelFieldDo.fieldUpdateTime#, BeginVer = #modelFieldDo.beginVer#, EndVer = #modelFieldDo.endVer#
		WHERE ID = #modelFieldDo.id#
	
        UPDATE Model_Field
        SET FieldStatus = 0  WHERE ID=#fieldId#
    
		UPDATE 
			DP_CategoryList 
		SET 
			CategoryName=#categoryList.categoryName#, 
			CategorySearchName=#categoryList.categorySearchName#,
			CategoryAltName=#categoryList.categoryAltName#, 
			CategoryType=#categoryList.categoryType#,
			CategoryLevelType=#categoryList.categoryLevelType#, 
			UpdateDate=#categoryList.updateDate#,
			CategoryURLName=#categoryList.categoryURLName#,
			CategoryOrderId=#categoryList.categoryOrderId#
		WHERE
			CategoryId=#categoryList.categoryId#
			AND CityId=#categoryList.cityId#
	
		UPDATE 
			DP_CategoryList 
		SET 
			CategoryLevelType=#categoryLevelType#
		WHERE
			CategoryId=#categoryId#
			AND CityId=#cityId#
	
		WHERE
			RegionID = #regionListData.regionId#
	
		UPDATE Mid_MB_ThirdUser 
		SET Corporation = #Corporation#, ContractNo = #ContractNo#
		WHERE UserName = #UserName#  
	
		UPDATE Mid_MB_ThirdUser 
		SET Flag = 0
		WHERE UserName = #UserName#
	
		UPDATE Mid_MB_ThirdRatio 
		SET Ratio = #Ratio#, ADDTIME = #AddTime#
		WHERE ID = #ID#  
	
		UPDATE DianPingMobile.CI_DownLoad 
		SET IPhoneUrl = #IPhoneUrl#, AndroidDefaultUrl = #AndroidDefaultUrl#, IPadUrl = #IPadUrl#, 
			WinphoneUrl = #WinphoneUrl#, OtherUrl = #OtherUrl#, DefaultUrl = #DefaultUrl#
		WHERE ID = #ID#;
	
		Update IPadQQ_Activity Set title=#title#,category=#category#,picUrl=#picUrl#,picJumpUrl=#picJumpUrl#,url=#url#,icon=#icon#,iconretina=#retinaIcon#,
			startDate=#startDate#,endDate=#endDate#
		where id=#id#
	
		UPDATE DianPingMobile.CI_ConfigParam 
		SET 
		ParamValue = 'true'
		WHERE
		ParamKey = 'enableYY' AND ClientLimit =1
	
		UPDATE DianPingMobile.CI_ConfigParam 
		SET 
		ParamValue = 'true'
		WHERE
		ParamKey = 'enableYY' AND ClientLimit =2
	
		UPDATE DianPingMobile.CI_ConfigParam 
		SET 
		ParamValue = 'false'
		WHERE
		ParamKey = 'enableYY' AND ClientLimit =1
	
		UPDATE DianPingMobile.CI_ConfigParam 
		SET 
		ParamValue = 'false'
		WHERE
		ParamKey = 'enableYY' AND ClientLimit =2
	
		
			update CI_CityTag
			set Status = 0
			where CityID = #cityId#	
			and OpenTime > now()
			
	
		
			update CI_FlavourCityIntro
			set Status = 0
			where ID = #id#	
			
	
		
			update CI_FlavourCityIntro
			set Status = 0
			where CityID = #cityId#
			and PlatForm = #plat#
			and OnLineTime > now()		
			
	
		
			update CI_FlavourExt a
			set a.Status = 0
			where CityID = #cityId#
			and FoodName = #foodName# 
			AND OnLineTime > now()
		
	
		
			update CI_FlavourExt a
			set a.Status = 0
			where CityID = #cityId#
			AND OnLineTime > now()
		
	
		
			update CI_FlavourStreetExt a
			set a.Status = 0
			where CityID = #cityId#
			and StreetName = #streetName# 
			AND OnLineTime > now()
		
	
		UPDATE DianPingMobile.CI_EBizPromo 
		SET IsShow = 1 
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET Title = #Title#, Description = #Description#, Sorted = #Sorted#, BeginDate = #BeginDate#, EndDate = #EndDate#, PicUrl = #PicUrl#, 
	 		LinkDetail = #LinkDetail# ,Suggest=#suggest#
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET TopicStatus = 2
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CheckInTopic 
		SET TopicStatus = 1
		WHERE ID = #ID#
	
		delete from DianPingMobile.CI_CheckinTopicTag 
		WHERE tagname = #tagname# and cityid = #cityid#
	
	
		delete from DianPingMobile.CI_CheckinTopicTag 
		WHERE cityid = #cityid# and topicid <0
	
	
		UPDATE CI_Announcement 
		SET Title = #Title#, PageUrl = #PageUrl#, BeginTime = #BeginTime#, 
			EndTime = #EndTime#, CityIDList = #CityIDList#, Priority = #Priority#
		WHERE AnnouncementID = #AnnouncementID#
	
		UPDATE CI_AnnoucementClient 
		SET Flag = 0
		WHERE Flag = 1 AND AnnouncementID = #AnnouncementID#
	
		UPDATE CI_ShopAnnouncement 
		SET LinkWord = #LinkWord#, LinkUrl = #LinkUrl#, BeginTime = #BeginTime#, EndTime = #EndTime#, Rules = #Rules#
		WHERE ID = #ID#
	
        UPDATE DianPingBA_Account.DP_Account
        SET
        
            `Id` = #account.accountId#,
            `AccountType` = #account.accountType#,
            `IDNumber` = #account.idNumber#,
            `EmployeeNumber` = #account.employeeNumber#,
            `LoginId` = #account.loginId#,
            `Gmail` = #account.gmail#,
            `GmailStatus` = #account.gmailStatus#,
            `Gmail2` = #account.gmail2#,
            `Gmail2Status` = #account.gmail2Status#,
            `AD` = #account.ad#,
            `ADStatus` = #account.adStatus#,
            `SysStatus` = #account.sysStatus#,
            `Name` = #account.name#,
            `AddTime` = #account.addTime#,
            `IsFrozen` = #account.frozen#,
            `UpdateTime` = #account.updateTime#
        
         WHERE `AD`=#account.ad#
         AND   `Gmail` = #account.gmail#
         AND   `EmployeeNumber` = #account.employeeNumber#
    
    update TG_Order
    set UserID = #userId:INTEGER#,
      CityID = #cityId:SMALLINT#,
      DealGroupID = #productGroupId:INTEGER#,
      DealID = #productId:INTEGER#,
      Quantity = #quantity:SMALLINT#,
      TotalAmount = #totalAmount:DECIMAL#,
      MobileNo = #mobileNo:VARCHAR#,
      UpdateDate = now(),
      AddUserIP = #userIp:VARCHAR#
    where OrderID = #orderId:INTEGER# and Status=0
  
  update TG_Order
    set PayChannelStatus = #payChannel:VARCHAR#,
    UpdateDate = now()
  where OrderID = #orderId:INTEGER#
  
  update TG_Order
    set PaymentAmount = #paymentAmount:DECIMAL#,
    UpdateDate = now()
  where OrderID = #orderId:INTEGER#
  
  update TG_Order
    set MobileNo = #mobileNo:VARCHAR#,
    UpdateDate = now()
  where OrderID = #orderId:INTEGER#
  
  	UPDATE TG_Invite SET STATUS = 1,LastDate = now() WHERE UserID = #userId# AND STATUS = 0;
  
    
    
        update PCT_OrderDelayMessage set Status=1,UpdateTime=now()
        where ID=#id# and Status=0
    
        update PCT_OrderDelayMessage set Status=1,UpdateTime=now()
        where Topic=#topic# and OutBizID=#outBizId# and Status=0
    
        update TG_OrderTextInfo
        set TextValue=#textValue#,
        UpdateDate = now()
        where OrderID = #orderId#
        and TextKey = #textKey#
    
    UPDATE PCT_ProductBatchRefund
    SET 
	  	ProductID=#productId#, 
    	ProductCode=#productCode#, 
		Status=#status#, 
		CityIDList=#cityIdList#, 
		BeginTime=#beginTime#, 
		EndTime=#endTime#, 
		UpdateTime=NOW()
    WHERE ID = #id:INTEGER#
  
    UPDATE PCT_ProductBatchRefund
    SET 
		Status=#status:TINYINT#,
		UpdateTime=NOW()
    WHERE ID = #id:INTEGER#
    AND Status!=#status:TINYINT#
  
		update TG_Charge
		set STATUS=
		#status#,
		SuccessDate = now()
		WHERE ChargeID = #chargeId#
	
		update PCT_UserHabit
		set
		UpdateTime = now(),
		PayMethod = #payMethod:TINYINT#,
		PaymentChannel = #paymentChannel:TINYINT#,
		PayPlatform = #payPlatform:INTEGER#,
		Bank = #bank:VARCHAR#
		where UserID = #userId:INTEGER#
	
        update PCT_OrderGroupCoupon
        set OrderGroupID = #orderGroupID:INTEGER#,
            DiscountID = #discountID:INTEGER#,
            DiscountCode = #discountCode:VARCHAR#,
            DiscountType = #discountType:TINYINT#,
            DiscountAmount = #discountAmount:Decimal#,
            Status = #status:TINYINT#,
            UpdateTime = NOW()
        where ID = #id:INTEGER#
    
        
            UPDATE PCT_UserChannelEvent
            SET Status = #status#,
                UpdateDate = NOW()
            WHERE ID = #id#
        
    
		update TG_Charge
		set STATUS=
		#status#,
		SuccessDate = now()
		WHERE ChargeID = #chargeId#
	
     where ((PaymentRefundID = #paymentRefundId#) AND (status = #status2#))
    
     where PaymentRefundID = #paymentRefundId#
     and status != 1
    
    update TG_OrderItem
    set 
      OrderID = #orderId:INTEGER#,
      UserID = #userId:INTEGER#,
      MobileNO = #mobileNo:VARCHAR#,
      ProductGroupID = #productGroupId:INTEGER#,
      ProductID = #productId:INTEGER#,
      Quantity = #quantity:SMALLINT#,
      TotalAmount = #totalAmount:DECIMAL#,
      Status = #status:TINYINT#,
      ProductType = #productType:TINYINT#,
      ProductValue = #productValue:DECIMAL#,
	  UserIP=#userIp:VARCHAR#,
	  CityID=#cityId:SMALLINT#
    where OrderItemID = #orderItemId:INTEGER#
  
  update TG_OrderItem
    set Status = 2
  where OrderItemID = #orderItemId:INTEGER#
  
  update TG_OrderItem
    set Status = 1
  where OrderItemID = #orderItemId:INTEGER#
  and Status = 0
  
  update TG_OrderItem
    set Status = #state:TINYINT#
  where OrderID = #orderId:INTEGER#
  and Status = 0
  
  where OrderItemID = #orderId:INTEGER#
  
  update TG_OrderItem
    set RefundStatus = #refundState:TINYINT#
  where OrderItemID = #orderId:INTEGER#
  
        update TG_OrderItem
        set RefundStatus = #refundStatus:INTEGER#
        where OrderID = #orderId:INTEGER#
    
  	update TG_OrderItem set MobileNO=#mobileNo# where OrderItemID = #orderItemId#
  
    
        UPDATE APP_PushMessage SET 
            status = #pushMessageDto.status#,
            sentDate = #pushMessageDto.sentDate#,
            lastRetryDate = #pushMessageDto.lastRetryDate#,
            retryTimes = #pushMessageDto.retryTimes#,
            remark = #pushMessageDto.remark#
            where id = #pushMessageDto.id#
    
	
		UPDATE PC_FollowNoteLast
		SET
		  LastUserID = #followNoteLast.lastUserId#, 
		  FollowNoteID = #followNoteLast.followNoteId# ,
		  NoteCount = #followNoteLast.noteCount#,
		  LastNoteTime = #followNoteLast.lastNoteTime#
		WHERE PicId = #followNoteLast.picId#
	
        update Mon_UserBlackList
        set IsReleased = 1, ReleasedDate = now()
        where UserID = #userId# and IsReleased = 0 and Type in (6,7)
    
  	update PCT_OrderNotify
  	set Status=#status#,Source=#source#,updateTime=now()
  	where OutBizID=#outBizId#
  
    update PCT_OrderJobConfig 
		set 
		ID =#id#,
		JobType=#jobType#,
		JobDesc=#jobDesc#,
		BeginTime=#beginTime#,
		BeginID=#beginId#,
		AddTime=#addTime#,
		UpdateTime=now(),
		IsValid=#isValid#,
		Status=#status#,
		CronScript=#cronScript#,
		Pid = #pid:VARCHAR#,
		Ip= #ip:VARCHAR#
  	where ID = #id#
   
    update PCT_OrderJobConfig 
		set 
		Status = #status#,
		UpdateTime = now()
  	where JobType = #jobType#
  
    update PCT_OrderJobConfig
		set
		Status = 1,
		Ip=#ip#
		UpdateTime = now()
  	where JobType = #jobType# and Status=0
  
		UPDATE DP_OfficialAlbum SET AlbumType= #albumType#,picCount = #picCount#
        WHERE ID = #albumId#;	
	
        UPDATE
            DP_ShopPhone
        SET CityID = #cityID#
        WHERE
            ID = #id#
    
         UPDATE DP_PicTagIndex SET
            OrderNo = #orderNo#
            WHERE picID=#picId#
    
    
        update /*MS-POSCORE-PAYMENT-UPDATE-STATUS*/ PCT_Payment set status=#status#, modify_time=Now(), payment_time = Now() where (payment_id = #paymentId#)
    
    
	    
	        update PCT_Payment 
	        set refundStatus=#refundStatus#, 
	        modify_time=Now()
	        where payment_id = #paymentId#
	        and refundStatus !=#refundStatus#
	    
    
	    
	        update PCT_Payment 
	        set thirdPartyOrderID=#thirdPartyOrderId#
	        where payment_id = #paymentId#
	    
    
	   
        update PCT_Payment 
        set status=#status#, 
        thirdPartyOrderID=#thirdPartyOrderId#, 
        modify_time=Now(), 
        payment_time = Now() 
        where payment_id = #paymentId#
    
    
          update TG_Coupon C
          set C.UsedStatus= #usedStatus#,
		  C.UsedDate = now()
		  where C.CouponID=#couponID#
    
    update TG_Deliver 
		set 
		UserID = #userID:INTEGER#,
		Consignee = #consignee:VARCHAR#,
		Address = #address:VARCHAR#,
		PostCode = #postCode:VARCHAR#,
		PhoneNO = #phoneNO:VARCHAR#,
		DeliverTimeType = #deliverTimeType:INTEGER#,
		Memo = #memo:VARCHAR#,
		Status = #status:TINYINT#,
		UpdateDate = now(),
		Province = #province:INTEGER#,
		City = #city:INTEGER#,
		District = #district:INTEGER#
    where OrderID=#orderID# and Type = #type#
  
    update TG_DeliverAddress
    set UserID = #userId:INTEGER#,
      Consignee = #consignee:VARCHAR#,
      Address = #address:VARCHAR#,
      PostCode = #postCode:VARCHAR#,
      PhoneNo = #phoneNo:VARCHAR#,
      IsDefault = #isDefault:TINYINT#,
      Status = #status:TINYINT#,
      UpdateDate = now(),
      Province = #province:INTEGER#,
      City = #city:INTEGER#,
      District = #district:INTEGER#,
      DeliverTime = #deliverTime:TINYINT#,
      InvoiceTitle = #invoiceTitle:VARCHAR#,
      NeedInvoice = #needInvoice:TINYINT#,
      Memo = #memo:VARCHAR#
    where DeliverAddressID = #deliverAddressId:INTEGER#
  
    update TG_DeliverAddress
    set 
      IsDefault = 1,
      UpdateDate=now()
    where DeliverAddressID = #deliverAddressId:INTEGER#
  
    update TG_DeliverAddress
    set 
      IsDefault = 0,
      UpdateDate=now()
    where UserID = #userId:INTEGER# and IsDefault = 1
  
    update TG_DeliverAddress
    set IsDefault = 0,
    UpdateDate=now()    
    where UserID = #userId:INTEGER#
  
    update TG_DeliverAddress
    set 
      Status = 0,
      UpdateDate=now()
    where DeliverAddressID = #deliverAddressId:INTEGER#
  
  	update PCT_OrderMapping
    set Status = #status#, UpdateTime = now()
    where OrderGroupID = #orderGroupId# and OrderID = #orderId#
  
  	update PCT_OrderMapping
    set AccountAmount = #accountAmount#, UpdateTime = now()
    where OrderGroupID = #orderGroupId# and OrderID = #orderId#
  
  	update PCT_OrderMapping
    set ThirdAmount = #thirdAmount#, UpdateTime = now()
    where OrderGroupID = #orderGroupId# and OrderID = #orderId#
  
  	update PCT_OrderMapping
    set AccountAmount = #accountAmount#, ThirdAmount = #thirdAmount#, UpdateTime = now()
    where OrderGroupID = #orderGroupId# and OrderID = #orderId#
  
    update TG_OrderProfile
          set ProfileValue=#profileValue#
          where OrderID=#orderId#
          and ProfileKey=#profileKey#
  
		where WithdrawID = #withdrawId#
	
		update PCT_UserHabit
		set
		UpdateTime = now(),
		PayMethod = #payMethod:TINYINT#,
		PaymentChannel = #paymentChannel:TINYINT#,
		Bank = #bank:VARCHAR#
		where ID=#id#
	
		update PCT_UserHabit
		set
		UpdateTime = now(),
		PayMethod = #payMethod:TINYINT#,
		PaymentChannel = #paymentChannel:TINYINT#,
		Bank = #bank:VARCHAR#
		where UserID=#userId# and PayPlatform=#payPlatform# and UserType=#userType#
	
    

    UPDATE PCT_OrderCouponBind
    SET CouponID = #couponID#,
        OrderID = #orderID#,
        UpdateDate = NOW(),
        ProductCode = #productCode#
    WHERE ID = #id#

        
    
		update	 PCT_AccountFrozen
		set account_id=#accountId#,
			      amount=#amount#,
    	          status=#status#,
    	          create_time=#createTime#,
		    	  modified_time=NOW(),
		    	  out_biz_id=#outBizId#,
		    	  memo=#memo#,
		    	  remain_amount=#remainAmount#
		WHERE account_frozen_id = #accountFrozenId#
		and status = 0
	
        WHERE account_frozen_id = #accountFrozenId#
        and remain_amount=#remainAmount#
    
			LastTime=NOW()
		WHERE
			PicID=#picId#
	
		UPDATE PC_Picture
		SET
			Title=#picture.title#,
			LastTime=NOW(),
			UserID=#picture.userId#,
			LastIP=#picture.lastIp#,
			Status=#picture.status#,
			Secret=#picture.secret#
		WHERE
			PicID=#picture.picId#
	
		UPDATE PC_Picture
		SET
			LastTime=NOW(),
			LastIP=#lastIp#,
			Status=#status#,
			StatusCode=#statusCode#
		WHERE
			PicID IN ($picIds$)
	
		UPDATE PC_Picture
		SET
			FlowerCount = FlowerCount + #delta#
		WHERE
			PicId=#picId#
	
		UPDATE PC_Picture
		SET
			FollowCount = FollowCount + #delta#
		WHERE
			PicId=#picId#
	
		UPDATE PC_Picture
		SET
			LastTime=NOW(),
			Status=#status#,
			StatusCode=#statusCode#
		WHERE
			PicID IN ($picIds$)
	
         UPDATE PC_Picture
         SET Status = #status#, LastTime=NOW()
         WHERE PicID = #picId#
    
    
		UPDATE PCT_RedEnvelopeRecord
		SET Status=#status#,
		AccountID=#accountId#,
		ErrorCode=#errorCode#
		WHERE ID=#id# and Status=0;
	
    
	    update PCT_CheckDateJobConfig
	    set 
	      Status = #status#,
	      UpdateDate = now()
	    where ID = #id# and Status!=#status#
  
		
			UPDATE
				DP_ShopVideo
            SET
                UploadDate = #uploadDate#
			WHERE
				VideoID = #videoId#
		
	
        update
        PCT_CashierDetail a
        set a.PaymentName = #paymentName# ,a.Status = #status# ,a.Rank=#rank#,a.PaymentTip = #paymentTip#,
        a.PreBankMode =#preBankMode#,a.PaymentDesc=#paymentDesc#,a.Memo=#memo#,a.IsDefault=#isDefault#,
        a.PaymentEventID= #paymentEventId# where a.CashierID = #cashierId# and a.PaymentChannel= #paymentChannel#
        and a.PaymentMethod = #paymentMethod#
    
        update
        PCT_CashierDetail a
        set a.Rank=#rank#
        where a.CashierID = #cashierId# and a.PaymentChannel= #paymentChannel#
        and a.PaymentMethod = #paymentMethod#
    
  	update PCT_OrderNotify
  	set Status=#status#,Source=#source#,updateTime=now()
  	where OutBizID=#outBizId#
  
		update PCT_WeixinAccessToken
		set
		AccessToken=#accessToken#, 
		ExpiredTime=#expiredTime#, 
		Status=#status#,
		UpdateTime=now()
		where AppID=#appId#
	
		update PCT_WeixinAccessToken
		set
		Status=1
		where AppID=#appId# and AccessToken=#accessToken# and status=0;
	
    

    UPDATE PCT_ChannelEvent
    SET EventName = #eventName#,
        EventDesc = #eventDesc#,
        EventStyle = #eventStyle#,
        EventLink = #eventLink#,
        EventImageLink = #eventImageLink#,
        DiscountAmount = #discountAmount#,
        SinglePaymentLimit = #singlePaymentLimit#,
        MaxJoin = #maxJoin#,
        PaymentChannel = #paymentChannel#,
        Status = #status#,
        BeginDate = #beginDate#,
        EndDate = #endDate#,
        Priority = #priority#,
        UpdateDate = NOW()
    WHERE ChannelEventID = #channelEventID#

        
    
		update
		TG_OrderCoupon
		set
		CouponID = #couponID#
		where OrderID = #orderID#
	
		update TG_Order
		set
		PaymentAmount = #paymentAmount#
		where OrderID = #orderID#
	
		update
		TG_Coupon C
		set C.UsedStatus= #usedStatus#,
		C.UsedDate = now()
		where
		C.CouponID=#couponID#
	
		update
		TG_Coupon C
		set C.Status= #status#
		where
		C.CouponID=#couponID#
		and C.Status != #status#
	
        
            UPDATE PCT_OrderChannelEvent
            SET MobileNO = #mobileNO#,
                OrderID = #orderID#,
                ChannelEventID = #channelEventID#,
                Status = #status#,
                UpdateDate = NOW()
            WHERE ID = #id#
        
    
    
        update PCT_PaymentBatch set NotifyTime=Now(), UpdateTime=Now() where (ID = #value#)
    
	
    
        UPDATE APP_PushDevice SET disabled = #disabled#
            where appId = #appId#
              and type = #type#
              and token = #token#
    
	
    
        UPDATE APP_PushDevice SET 
            disabled = #deviceEntity.disabled#,
            isLogin = #deviceEntity.isLogin#,
            lastRegisterDate = #deviceEntity.lastRegisterDate#
            where appId = #deviceEntity.appId#
              and loginId = #deviceEntity.loginId#
              and type = #deviceEntity.type#
              and token = #deviceEntity.token#
    
	
	    update PCT_CheckDateJobConfig
	    set 
	      Status = #status#,
	      UpdateDate = now()
	    where ID = #id# and Status!=#status#
  
		
			UPDATE DP_InfoPic
			SET InfoID = #infoId#
			WHERE InfoPicID IN ($picIds$) 
		
	
		
			UPDATE DP_Promo
			SET ReplyTotal = ReplyTotal + #num#
			WHERE PromoId=#promoId#
		
	
    update PCT_CouponNotify
		set 
		CouponID = #couponID#,
		UpdateDate = now()
  	where Type = #type#
    
        UPDATE PC_PicBiz
        SET Secret = 0
        WHERE PicID = #picId#
    
        UPDATE PC_PicBiz
        SET Status = 2
        WHERE PicID = #picId#
    
			UPDATE DP_BizJournalAccount
			SET TradeStatus = #bizJournalAccount.tradeStatus#, ReceiptID=#bizJournalAccount.receiptId#
			WHERE TradeNo = #bizJournalAccount.tradeNo#
	
		UPDATE DP_OfficialAlbumPic SET AlbumID=#albumId#
        WHERE PicID IN ($picIds$);	
	
		DELETE FROM DP_OfficialAlbumPic 
        WHERE PicId = #picId#;	
	
		UPDATE DP_BookingShop SET MainCategoryID=#mainCategoryId#,CityID=#cityId# 
        WHERE ShopId = #shopId#
	
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId# AND PicType=2
	
        UPDATE TG_DealGroup
        SET IsCanUseCoupon = #isCanUseCoupon#
        WHERE DealGroupID = #dealGroupID#
    
    where OrderGroupID=#orderGroupId#
  
  	update PCT_OrderGroup
    set Status = #status#, UpdateTime = now()
    where OrderGroupID = #orderGroupId#
  
  	update PCT_OrderGroup
    set SuccessNum = #successNum#, UpdateTime = now()
    where OrderGroupID = #orderGroupId#
  
  	update PCT_OrderGroup
    set ThirdAmount = #thirdAmount#, UpdateTime = now()
    where OrderGroupID = #orderGroupId#
  
		update TG_SharedLinkLog 
		set UserID=#userId#,
		InviteUser=#inviteUser#,
		PlatformType=#platformType#,
		Referer=#referer#,
		URL=#url#,
		UserIP=#userIp#,
		DealGroupID=#dealGroupId#,
		UserFlag=#userFlag#,
		OrderID=#orderId#,
		OrderFlag=#orderFlag#,
		Status=#status#,
		UpdateDate=now()
		where SharedLinkLogID =#sharedLinkLogId#;
	
    update TG_Certificate
    set Status = #status:TINYINT#
    where CertificateID = #certificateId:INTEGER#
  
		UPDATE TGHT_Refund
		    set Status = 2
		WHERE OrderID = #orderId:INTEGER#
	
		UPDATE TGHT_Refund
		    set Status = #status#,
		    ThirdPartyPaymentMode = #thirdPartyPaymentMode#,
		    UpdateDate = NOW(),
		    SettlementDate = NOW()
		WHERE RefundID = #refundID#
	
		update PCT_UserPaySign
		set
		UpdateTime = now(),
		Mobile = #mobile:VARCHAR#,
		Name = #name:VARCHAR#,
		CertNO =
		#certNO:VARCHAR#,
		ValidDate = #validDate:VARCHAR#,
		Status =
		#status:TINYINT#,
		Platform = #platform:TINYINT#,
		Bank = #bank:VARCHAR#,
		ResultCode = #resultCode:VARCHAR#,
		UserID = #userID:INTEGER#,
		Description = #description:VARCHAR#
		where SignNO = #signNO:INTEGER#
	
        update PCT_ChargeCoupon
        set Status = #status:TINYINT#
        where CouponID = #couponID# and ChargeID = #chargeID# and status = 0
    
  update TG_Order
    set RefundStatus = #refundStatus:INTEGER#
  where OrderID = #orderId:INTEGER#
  
    update PCT_OrderRefund
    set 
	  OrderID =#orderId:INTEGER#,  
	  ReceiptList=#receiptList:VARCHAR#, 
	  UserID=#userId:INTEGER#, 
	  Status=#status:INTEGER#,
	  PlatForm=#platForm:INTEGER#,
	  RefundSource=#refundSource:INTEGER#,  
	  UpdateTime=now(),
	  SuccessTime=#successTime:DATETIME#,
	  UserIP=#userIp:VARCHAR#, 
	  Amount=#amount:DECIMAL#, 
	  AccountAmount=#accountAmount:DECIMAL#,
	  ThirdPartyAmount=#thirdPartyAmount:DECIMAL#,
	  CouponAmount=#couponAmount:DECIMAL#,
	  RefundType=#refundType:TINYINT#,
	  RefundBizType=#refundBizType:TINYINT#,
	  CallBackUrl=#callBackUrl:VARCHAR#,
	  OutBizID=#outBizId:VARCHAR#,
	  ThirdPartyOrderID=#thirdPartyOrderID:VARCHAR#,
	  ProductCode=#productCode:TINYINT#,
	  Memo=#memo:VARCHAR#,
	  ErrorCode=#errorCode:VARCHAR#,
	  NeedDeduction=#needDeduction:TINYINT#,
	  PaymentChannel=#paymentChannel:TINYINT#
    where ID = #id:INTEGER#
  
		UPDATE MPC_LeftPV
		SET LeftTotalDisplayTimes = LeftTotalDisplayTimes - #pvtimes#,
		LeftPeriodDisplayTimes = LeftPeriodDisplayTimes - #pvtimes#
		WHERE AdID = #adID#
    
		
			UPDATE DP_Info
			SET InfoTitle = #infoData.infoTitle#,
			Description = #infoData.description#,
			BeginDate = #infoData.beginDate#,
			EndDate = #infoData.endDate#,
			ShopID = #infoData.shopId#,
			IsGroupLevel = #infoData.isGroupLevel#,
			CityID = #infoData.cityId#,
		    Lasttime = #infoData.lasttime#,
			LastUser = #infoData.lastUser#,
			LastIP = #infoData.lastIp#,
			ShopType = #infoData.shopType#,
			ShopFullName = #infoData.shopFullName#,
		    LastUserName = #infoData.lastUserName#,
		    VerifyStatus = #infoData.verifyStatus#
			WHERE InfoID=#infoData.infoId# AND AddUser = #userId#
		
	
		
			UPDATE DP_Info
			SET 
			LastUser=#infoData.lastUser# ,
			Lasttime=#infoData.lasttime#,
			LastIP=#infoData.lastIp#,
			LastUserName = #infoData.lastUserName#,
			Power = #infoData.power#,
			VerifyStatus = #infoData.verifyStatus#
	        WHERE  InfoID = #infoData.infoId# AND AddUser = #userId#
	    
	
        
             UPDATE DP_Info
             SET VerifyStatus = #verifyStatus#
             WHERE InfoID = #infoId#
        
    
		
			UPDATE DP_InfoFollowNote 
			SET NoteBody=#noteBody#,
				LastTime=#lastTime#,
				LastIP=#lastIp#,
				verifyStatus=#verifyStatus#
			WHERE FollowNoteID=#followNoteId#
		
	
		
			UPDATE DP_InfoFollowNote
			SET VerifyStatus=#verifyStatus#
			WHERE FollowNoteID=#followNoteId#
		
	
		UPDATE PC_PicBiz
		SET
			LastTime = NOW(),
			Status = #picBiz.status#,
			StatusCode = #picBiz.statusCode#
		WHERE
			PicID=#picBiz.picId# AND
			BizID=#picBiz.bizId# AND
			ReferID=#picBiz.referId#
	
		UPDATE PC_PicBiz
		SET
			LastTime = NOW(),
			Status = #status#,
			StatusCode = #statusCode#
		WHERE
			PicID IN ($picIds$)
	
		UPDATE PC_PicBiz
		SET
		  ReferID = #referId#, 
		  LastTime = NOW()
		WHERE PicID IN ($picIds$) AND BizID = #bizTypeValue#
	
		UPDATE PC_PicBiz
		SET
		  Status = #status#, 
		  LastTime = NOW()
		WHERE UserId = #userId#
		  AND BizID = #bizTypeValue#
	
        UPDATE PCT_DiscountGroup
        SET Status = #status#
        WHERE DiscountGroupID = #discountGroupID#
        AND Status != #status#
    
        UPDATE PCT_DiscountMapping
        SET Status = #status#
        WHERE DiscountID = #discountID#
    
    update PCT_OrderJobConfig 
		set 
		ID =#id#,
		JobType=#jobType#,
		JobDesc=#jobDesc#,
		BeginTime=#beginTime#,
		BeginID=#beginId#,
		AddTime=#addTime#,
		UpdateTime=now(),
		IsValid=#isValid#,
		Status=#status#,
		CronScript=#cronScript#,
		Pid = #pid:VARCHAR#,
		Ip= #ip:VARCHAR#
  	where ID = #id#
    
    update PCT_OrderJobConfig 
		set 
		Status = #status#,
		UpdateTime = now()
  	where JobType = #jobType#
    
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId#
	
    UPDATE DP_ShopPic
    SET Status = 2
    WHERE PicID = #picId#
    
    update PCT_OrderJobConfig 
		set 
		ID =#id#,
		JobType=#jobType#,
		JobDesc=#jobDesc#,
		BeginTime=#beginTime#,
		BeginID=#beginId#,
		AddTime=#addTime#,
		UpdateTime=now(),
		IsValid=#isValid#,
		Status=#status#,
		CronScript=#cronScript#,
		Pid = #pid:VARCHAR#,
		Ip= #ip:VARCHAR#
  	where ID = #id#
   
    update PCT_OrderJobConfig 
		set 
		Status = #status#,
		UpdateTime = now()
  	where JobType = #jobType#
  
    update PCT_OrderJobConfig
		set
		Status = 1,
		Ip=#ip#,
		UpdateTime = now()
  	where JobType = #jobType# and Status=0
  
    update TG_OrderItem
    set 
      OrderID = #orderId:INTEGER#,
      UserID = #userId:INTEGER#,
      MobileNO = #mobileNo:VARCHAR#,
      ProductGroupID = #productGroupId:INTEGER#,
      ProductID = #productId:INTEGER#,
      Quantity = #quantity:SMALLINT#,
      TotalAmount = #totalAmount:DECIMAL#,
      ProductType = #productType:TINYINT#,
      ProductValue = #productValue:DECIMAL#,
	  UserIP=#userIp:VARCHAR#,
	  CityID=#cityId:SMALLINT#,
	  Platform=#platform:INTEGER#,
	  ReceiveChannel=#receiveChannel:INTEGER#,
	  UpdateTime=now()
    where OrderItemID = #orderItemId:INTEGER# and Status=0
  
  	update TG_OrderItem set MobileNO=#mobileNo# where OrderItemID = #orderItemId#
  
  	update TG_OrderItem set MobileNO=#mobileNo# where OrderID = #orderId#
  
        update PCT_OrderExtend
        set AdvanceOrderID=#advanceOrderId#,
        StockStatus=#stockStatus#,
        ExpiredTime=#expiredTime#,
        DiscountRuleID=#discountRuleId#,
        PaymentRuleID=#paymentRuleId#,
        DisplayMode=#displayMode#
        where OrderItemID=#orderItemId#
    
    
    
      update PCT_OrderExtend
      set DisplayMode=1
      where OrderItemID=#orderItemId#
  
    update PCT_OrderRefund
    set
	  OrderID =#orderId:INTEGER#,
	  ReceiptList=#receiptList:VARCHAR#,
	  UserID=#userId:INTEGER#,
	  Status=#status:INTEGER#,
	  PlatForm=#platForm:INTEGER#,
	  RefundSource=#refundSource:INTEGER#,
	  UpdateTime=now(),
	  SuccessTime=#successTime:DATETIME#,
	  UserIP=#userIp:VARCHAR#,
	  Amount=#amount:DECIMAL#,
	  AccountAmount=#accountAmount:DECIMAL#,
	  ThirdPartyAmount=#thirdPartyAmount:DECIMAL#,
	  CouponAmount=#couponAmount:DECIMAL#,
	  RefundType=#refundType:TINYINT#,
	  RefundBizType=#refundBizType:TINYINT#,
	  CallBackUrl=#callBackUrl:VARCHAR#,
	  OutBizID=#outBizId:VARCHAR#,
	  ThirdPartyOrderID=#thirdPartyOrderID:VARCHAR#,
	  ProductCode=#productCode:TINYINT#,
	  Memo=#memo:VARCHAR#,
	  ErrorCode=#errorCode:VARCHAR#,
	  NeedDeduction=#needDeduction:TINYINT#,
	  PaymentChannel=#paymentChannel:TINYINT#,
	  ChannelEventAmount=#channelEventAmount:DECIMAL#,
	  DiscardAmount=#discardAmount:DECIMAL#,
	  ReturnAmount=#returnAmount:DECIMAL#,
	  AdminID=#adminID:INTEGER#,
	  DiscountAmount=#discountAmount:DECIMAL#,
	  RedEnvelopeAmount=#redEnvelopeAmount:DECIMAL#,
	  GiftCardAmount=#giftCardAmount:DECIMAL#
    where ID = #id:INTEGER#
  
    update PCT_OrderRefund
    set
	  NeedDeduction=#needDeduction#
    where ID = #orderRefundId#
    and NeedDeduction!=1
  
    update Mon_UserBlackList
    set
    IsReleased = 1,
    ReleasedDate = now()
    where UserID = #userId# and IsReleased = 0 and Type in (6,7)
  
    update PCT_ThirdPartOrder
    set OrderID = #orderId#,
      UpdateTime = now()
    where ID = #thirdPartyOrderId# and OrderID != #orderId#
  
    update PCT_ThirdPartOrder
    set NotifyStatus = #notifyStatus#,
      UpdateTime = now()
    where ID = #thirdPartyOrderId# and NotifyStatus != #notifyStatus#
  
    update PCT_ThirdPartOrder
    set Certificates = #certificates#,
      UpdateTime = now()
    where ID = #thirdPartyOrderId#
  
    update PCT_ThirdPartOrder
    set NotifyStatus = #notifyStatus#,
        Memo = #memo#,
      UpdateTime = now()
    where ID = #thirdPartyOrderId# 
  
	  update TG_UserAccountAudit
	     SET RefundAmount= (#refundAmount# +IFNULL(RefundAmount,0))
	  where AuditID = #auditID#
  	
    UPDATE PCT_BatchRefund
    SET 
	  	BatchNO=#batchNO:VARCHAR#, 
  		OrderRefundID=#orderRefundID:INTEGER#, 
		Status=#status:TINYINT#, 
		ResultCode=#resultCode:VARCHAR#, 
		Description=#description:VARCHAR#, 
		UpdateTime=NOW(), 
		SuccessTime=#successTime:DATETIME#
    WHERE ID = #id:INTEGER#
  
    update TG_Invoice 
		set OrderID = #orderID:INTEGER#,
		Title = #title:VARCHAR#,
		Status = #status:TINYINT#,
		UpdateDate = now()
  	where OrderID = #orderID# and Type = #type#
  
    update TG_Invoice 
		set 
		Status = 1,
		UpdateDate = now()
  	where OrderID = #orderId# and Type = #type# 
  
		update PCT_BatchAuditEngineMapping
		set
		EngineControlID=#engineControlId#, 
		BatchNO=#batchNo#, 
		OutBatchNO=#outBatchNo#, 
		OrderString=#orderString#, 
		ThirdPartySerialNO=#thirdPartySerialNo#, 
		Memo=#memo#,
        AddTime=#addTime#,
        UpdateTime=now(), 
        NotifyStatus=#notifyStatus#,
        ErrorCode=#errorCode#
		where BatchNO=#batchNo# and EngineControlID=#engineControlId#
	
		UPDATE PCT_ThirdPartPaymentAuditExtend A
			JOIN TG_ThirdPartyPaymentAudit C ON A.thirdPartPaymentAuditId = C.PaymentAuditID 
		SET
			A.errorCode = #error#
		WHERE 
			C.MessageType = #messageType# AND A.engineControlId = #engineControlID#
	
        update PCT_OrderCoupon
        set CouponID=#couponID#,CouponType=#couponType#
        where OrderID=#orderID# and Status >=0
    
        update PCT_OrderCoupon
        set Status=-1
        where OrderID=#orderID#
    
	  update TG_UserAccountAudit
	     SET RefundAmount= (#refundAmount# +IFNULL(RefundAmount,0))
	  where AuditID = #auditID#
  	
  	update PCT_OrderNotify
  	set Status=#status#,Source=#source#,updateTime=now()
  	where OutBizID=#outBizId#
  
		
			UPDATE DP_PromoFollowNote
			SET NoteBody=#noteBody#,
				LastTime=#lastTime#,
				LastIP=#lastIp#,
				VerifyStatus=#verifyStatus#
			WHERE FollowNoteID=#followNoteId#
		
	
		
			UPDATE DP_PromoFollowNote
			SET VerifyStatus=#verifyStatus#
			WHERE FollowNoteID=#followNoteId#
		
	
		UPDATE PC_FollowNote
		SET
			NoteBody = #followNote.noteBody#,
			Status = #followNote.status#,
			UpdateDate = #followNote.updateDate#,
			IP = #followNote.ip#
		WHERE
			FollowNoteID = #followNote.followNoteId#
	
		WHERE
			FollowNoteID = #followNoteId#
	
		INSERT INTO MPR_PassBook
			(serialNum,AuthToken,ptoken,devLibId,createTime,status,passType) 
		VALUES (#serialNum#, #authToken#,#ptoken#,#devLibId#,#createTime#,1,#passType#) 
		ON DUPLICATE KEY 
		UPDATE serialNum = #serialNum#, AuthToken = #authToken#,ptoken=#ptoken#,devLibId=#devLibId#,createTime=#createTime#, status=1,passType=#passType#
    
		UPDATE MPR_PassBook SET status=0
		WHERE serialNum=#serialNum# and AuthToken = #authToken# and devLibId=#devLibId# and passType=#passType# and status=1
	
        
            UPDATE PCT_OrderChannelEvent
            SET MobileNO = #mobileNO#,
                OrderID = #orderID#,
                ChannelEventID = #channelEventID#,
                Status = #status#,
                UpdateDate = NOW()
            WHERE ID = #id#
        
    
        UPDATE PC_Picture
        SET Secret = 0, ClientType = 0
        WHERE PicID = #picId#
    
        UPDATE PC_Picture
        SET Title = #title#
        WHERE PicID = #picId#
    
        UPDATE PC_Picture
        SET Status = 2
        WHERE PicID = #picId#
    
        UPDATE
            DP_BizAccount
        SET CityID = #cityID#
        WHERE
            AccountID = #accountID#
    
		update TG_ThirdPartyPaymentAudit set
			PartnerID=#partnerId#
		where 
			MessageType=1 and PaymentType in (1,2) and OrderString=#orderString#;
	
		UPDATE 
			HT_SelectedShop 
		SET
			Status=#status#,
			Flag=#accordShopId#,
			EditorID=#editorId#,
			EditorName=#editorName#,
			LastDate=#lastDate#			
		WHERE
			ShopID=#doubtShopId#
	
		UPDATE 
			HT_SelectedShop 
		SET
			Status=#status#,
			Flag=null,
			EditorID=#editorId#,
			EditorName=#editorName#,
			LastDate=#lastDate#				
		WHERE
			ShopID=#shopId#
	
		UPDATE 
			HT_SelectedShop 
		SET
			Status=#status#,
			Flag=#accordShopId#,
			EditorID=#editorId#,
			EditorName=#editorName#,
			LastDate=#lastDate#			
		WHERE
			ID=#id#
	
		UPDATE 
			HT_SelectedShop 
		SET
			Status=#status#,
			Flag=null,
			EditorID=#editorId#,
			EditorName=#editorName#,
			LastDate=#lastDate#				
		WHERE
			ID=#id#
	
		UPDATE 
			HT_SelectedShop 
		SET
			Status=#status#,
			EditorId=#editorId#,
			EditorName=#editorName#,
			LastDate=#lastDate#
		WHERE
			ID=#id#
	
		UPDATE YY_BookingShopCash SET
			balance = balance + #amount#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
		UPDATE YY_BookingApply 
		SET
		shop_id = #shopId#,
		shop_name = #shopName#,
		rebate = #rebate#,
		average_cost = #averageCost#,
		contact_phone = #contactPhone#,
		status = #status#,
		apply_name = #applyName#,
		created_time = #createTime#,
		create_by = #createBy#,
		update_time = #updateTime#,
		update_by = #updateBy#
		WHERE
		shop_id = #shopId#
	
		UPDATE YY_BookingApply 
		SET 
		status = #status#,
		update_by= #userName#,
		update_time = NOW()
		WHERE
		shop_id = #shopId#
	
        UPDATE YY_BookingApply 
        SET 
        status = #status#,
        update_by = #updateBy#,
        update_time = NOW()
        WHERE
        shop_id = #shopId#
    
		UPDATE YY_BookingApply 
		SET 
		reject_reason = #reason#,
		update_time = NOW(),
		update_by = #userName# 
		WHERE
		shop_id = #shopId#
	
		UPDATE YY_BookingApply 
		SET 
		average_cost = #cost#,
		rebate = #rebate#,
		update_time = NOW(),
		update_by = #userName#
		WHERE
		shop_id = #shopId#
	
		UPDATE YY_BookingApply 
		SET 
		status = #status#,
		is_try = #trial#,
		update_time = NOW(),
		update_by = #userName#
		WHERE
		shop_id = #shopId#
	
   		UPDATE YY_OperatorStatics SET handled_deal=
		(SELECT * FROM (SELECT handled_deal FROM YY_OperatorStatics WHERE operator_id=#operatorId# AND date_stamp=#dateStamp# AND domain=#domain#)tmp) +1
		WHERE operator_id=#operatorId# AND date_stamp=#dateStamp# AND domain=#domain#
   
		UPDATE YY_AccountConfig SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BillingConfigShop SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BillingIDShopMap SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingApply SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingPay SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingPayAction SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingPayDetail  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingPayUser  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingRecord  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingShopRange  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ContractShop  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_DunningConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_MonthlyBill   SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_RebateConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_RechargeInvoiceRecord  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_RechargeRemindConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_RecommendShop  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_Room  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_SellerShop  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopBookableStatus  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopInfo  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopOnlineFlow  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopPoint  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopReceiptStatus   SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_OperationLog SET operate_id = #toShopID# WHERE operate_id = #fromShopID# AND operate_type IN (10, 60, 110);
	
		UPDATE YY_HotShopLocation  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_PrivilegeInfo  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopDunningOperationLog  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopDunningRecord  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_UserFeedbackArrive  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_CallCenterLog  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingEvent_Backup  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_CallbackEvent_Backup SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_CC_CallCenterLog  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_CC_BookingEvent_Backup  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_CC_CallbackEvent_Backup  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingCashConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingShopCash  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingCashAction  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingCashDetail  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingCashEncashment  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_BookingDealPool  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_Compartment  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_MagicAccount  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_Shopism  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_ShopSwitchConfig  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		UPDATE YY_WaitressRecord  SET shop_id = #toShopID# WHERE shop_id = #fromShopID#;
	
		update
		YY_RsCalloutEvent
		set init_task_id = id
		where id = #id#
	
		update YY_RsCalloutEvent
		set current_phone_number = #phoneNumber#
		where id = #id#
	
	
		UPDATE YY_RsCalloutEvent
		SET updated_time=NOW()
		WHERE id=#id# and updated_time<=#date#
	
	
	
 		UPDATE YY_RsCalloutEvent
 		SET line_lock = 0,
 		status = 50
 		WHERE line_lock = 1
 		AND send_time <=#date#
 	
	
	
		UPDATE YY_RsCalloutEvent
 		SET updated_time = now(),
 		status = 50
 		WHERE 
 		id = #id#
 		AND updated_time <=#date#
 	
	
		UPDATE
		YY_RsCalloutEvent
		SET
		status = #status#,
		updated_time = now()
		WHERE
		id = #id#
	
	
		UPDATE YY_RsCalloutEvent
		SET
			line_lock = 1,
			send_time =	now(),
			updated_time = now()
		WHERE id = #id# and line_lock<>1
	
	
		UPDATE YY_RsCalloutEvent
		SET
		line_lock = #lock#,
		send_time = now(),
		updated_time = now()
		WHERE id =
		#id#
	
		UPDATE
		YY_RsCalloutEvent
		SET
		retry_times = retry_times+1,
		scheduled_time = #scheduled_time#,
		line_lock = 0,
		status = 10,
		updated_time = now()
		WHERE
		id = #id#
	
		UPDATE
		YY_RsCalloutEvent
		SET
		line_lock = 0,
		status = 10
		WHERE
		id = #id#
	
		UPDATE
		YY_RsCalloutEvent
		SET
		eventmessage = #eventMsg#
		WHERE
		id = #id#
	
		UPDATE
		YY_RsCalloutEvent
		SET
		retry_message = #retryMessage#
		WHERE
		id = #id#
	
		UPDATE
		YY_RsCalloutEvent
		SET
		id = #newid#
		WHERE
		id = #id#
	
		UPDATE
		YY_RsCalloutEvent
		SET
		hangup_status = #status#,
		line_lock = 2,
		send_time = #date#,
		updated_time = #date#
		WHERE
		id = #id#
	
	
		UPDATE YY_FlowerCode
		SET status = #status#
		WHERE code = #code#
	
		UPDATE YY_RecordFlowerCode
		SET code = #code#
		WHERE record_id = #recordID#
	
		UPDATE RT_ShopInventory
		SET
			bookable_map = #bookableMap#
		WHERE
			shop_id = #shopId# AND TO_DAYS(inventory_date) = TO_DAYS(#date#) AND table_id = #tableId#;
	
        UPDATE TG_Invite SET STATUS = 1,LastDate = now() WHERE UserID = #userId# AND STATUS = 0;
    
        UPDATE TG_ReceiptGroupCode
        SET Status = 0
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId# and Status=1;
    
        UPDATE TG_ReceiptGroupCode
        SET Code = #code#
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId#;
    
        UPDATE TG_ReceiptPrepaidCardNumberSequence
        SET
        CardNumber = #cardNumber#,
        LockVersion = LockVersion + 1
        WHERE
        PrepaidCardGroupID = #prepaidCardGroupID# AND LockVersion = #lockVersion#
    
        UPDATE TG_StockLock SET Status = 1, LastDate = NOW() WHERE LockID = #lockID# AND Status = 0;
    
        UPDATE TG_StockLock SET Status = 2, LastDate = NOW() WHERE LockID = #lockID# AND Status = 1;
    
        UPDATE TG_StockLock SET Status = 3, LastDate = NOW() WHERE LockID = #lockID# AND Status = 1;
    
		UPDATE TG_OrderStatus SET Status = #newStatus#
		WHERE OrderID = #orderId# AND Status = #oldStatus# AND BizType = #bizType#
	
        
        UPDATE MRB_CPMInventory_Keyword SET 
        Count = Count + #count# 
		WHERE KeywordID = (#keywordid#) AND DayID = #dayid# 
        
    
        
        UPDATE MRB_CPMInventory_Keyword SET 
        Count = Count - #count# 
		WHERE KeywordID = (#keywordid#) AND DayID = #dayid# 
        
    
        UPDATE TG_ReceiptGroupCodePool
        SET Status = 0
        WHERE
        PoolID = #poolId#
        AND Status = 1 >0
    
		UPDATE
			YY_ClientUUIdBindInfo
		SET
	 		phone= #phone#,	
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE
			client_uuid= #clientUUid# and source=#source#
	
		UPDATE YY_ReviseArrive SET
	        user_id = #userID#,
	        client_uuid = #clientUUID#,
	        arrive_time = #arriveTime#,
	        num_people = #peopleCount#,
	        consumption = #consumption#,
			remark = #remark#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE record_id = #recordID#
	
		UPDATE 
			YY_SalesTask
		SET
			sales_name=#salesName#,
			exception=#exception#,
			`comment`=#comment#,
			update_by=#updateBy#,
			updated_time=NOW(),
			`status`=#status#,
			result_comment=#resultComment#,
			source=#source#
		WHERE 
			id=#id#
	
        UPDATE
            YY_WaitressPay
        SET
            total_amout = total_amout + #total#,
            remaining_amout = remaining_amout + #remainAmount#,
            updated_time = now(),
            update_by =  #operator#
        WHERE
            phone_number = #phone#
    
		UPDATE
			YY_BillingRecord
		SET
	 		contract_id = #contractID#,
	 		billing_time = #billingTime#,
	 		money = #money#,
	 		drawer = #drawer#,
	 		update_by = #updateBy#,
	 		updated_time = #updateTime#
	 	WHERE
	 		id = #id#
	
		UPDATE YY_BookingPay
		SET
			update_by = #updateBy#,
			updated_time =#updateTime#
		WHERE
			shop_id = #shopId#
	
		UPDATE YY_BookingPay
		SET
			balance = balance+#realAmount#,
			virtual_balance = virtual_balance+#extraAmount#,
			update_by = #operator#,
			updated_time = now()
		WHERE
			shop_id = #shopId#
	
		UPDATE YY_BookingPay
		SET
			balance = balance+#amount#,
			update_by = #operator#,
			updated_time = now()
		WHERE
			shop_id = #shopId#
	
		UPDATE
			YY_BusinessRecord
		SET
	 		shop_id = #shopID#,
	 		amount = #amount#,
	 		charge = #charge#,
	 		ref_record_id = #refRecordID#,
	 		remark = #remark#,
	 		type_code = #typeCode#,
	 		balance_prior = #balancePrior#,
	 		update_by = #updateBy#,
	 		updated_time = #updateTime#
	 	WHERE
	 		id = #id#
	
	 	UPDATE YY_CRMContract SET
	 		start_time = #start#, 
	 		end_time = #end#,
	 		party_name = #partyName#,
	 		phone = #phone#,
	 		city_id = #cityID#,
	 		commission_rate = #commissionRate#,
	 		deposit = #deposit#,
			party_a_name = #partyAName#,
			payment_mode = #paymentMode#,
	 		update_by = #updateBy#, 
	 		updated_time = #updateTime#
		WHERE contracthead_id = #contractHeadID#
	
        update Report_Job_Job
        set Title=#title#, DatasourceName=#datasource#, JobSql=#sql#, Serie=#serie#, UpdateTime=NOW()
        where JobID = #jobId#
    
		UPDATE YY_MessageRecord SET
		send_status = #status#
		WHERE record_id = #bookingRecordID#
		AND message_type = #messageType#
	
		UPDATE TG_DealCurrentJoin SET CurrentJoin = CurrentJoin + #quantity#
		WHERE DealID = #dealID# AND (CurrentJoin + #quantity# <= #maxJoin#)
	
		UPDATE TG_DealGroupCurrentJoin SET CurrentJoin = CurrentJoin + #quantity#
		WHERE DealGroupID = #dealGroupID# AND (CurrentJoin + #quantity# <= #maxJoin#)
	
		UPDATE TG_DealCurrentJoin SET CurrentJoin = CurrentJoin - #quantity#
		WHERE DealID = #dealID#
	
		UPDATE TG_DealGroupCurrentJoin SET CurrentJoin = CurrentJoin - #quantity#
		WHERE DealGroupID = #dealGroupID#
	
		UPDATE TG_EbuyRequestNumber SET RequestNumber = RequestNumber + 1
		WHERE RequestNumber=#requestNumber# AND ThirdPartyID=#thirdPartyID#;
	
        UPDATE TG_ReceiptGroupCode
        SET Status = 0
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId# and Status=1;
    
        UPDATE TG_ReceiptGroupCode
        SET Code = #code#
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId#;
    
		update TG_Deliver set quantity=0,Status=0 where
		OrderID=#orderId#
	
		update
		TG_Deliver set RefundingQuantity=RefundingQuantity-#refundingQuantity#,
		IsShow=CASE RefundingQuantity>0 WHEN true
		then 0 ELSE CASE Quantity - RefundQuantity WHEN 0 THEN 0 ELSE 1 END END
		where OrderID=#orderId#
		
	
		update
		TG_Deliver set
		RefundQuantity=RefundQuantity-#refundQuantity#,RefundingQuantity=RefundingQuantity+#refundQuantity#,
		IsShow=CASE
		RefundingQuantity>0 WHEN true then 0 ELSE CASE Quantity - RefundQuantity WHEN 0 THEN 0 ELSE 1 END END
		where
		OrderID=#orderId#
		
	
		UPDATE YY_MonthlyBill SET
			shop_id = #shopID#,
			year = #year#,
			month = #month#,
			status = #status#,
			consumption = #consumption#,
			commission_rate = #commissionRate#,
			paid = #paid#,
			actual_payment = #actualPayment#,
			update_by = #updateBy#,
			updated_time = #updateTime#,
			replenish = #replenish#
		WHERE
			id = #id#
	
		UPDATE YY_BookingPayDetail
		SET
			amount = #amount#,
			type = #type#,
			biz_id = #bizId#,
			status = #status#,
			receipt_id = #receiptId#,
			comments = #comment#
		WHERE
			id = #id# AND shop_id = #shopId# AND operate_id = #operateId#
	
		UPDATE YY_BookingPay
		SET
			balance = balance+#amount#,
			update_by = #operator#,
			updated_time = now()
		WHERE
			shop_id = #shopId#
	
		UPDATE YY_Switchs
		SET switch_status = (switch_status + 1) MOD 2
		WHERE  switch_key = #switchKey#
		AND	   switch_status = #switchStatus#
	
		
			UPDATE DP_ExtraReview
			SET Status = 2, LastTime = NOW()
			WHERE ReviewID = #reviewId#
    	
	
		
			UPDATE DP_ExtraReview
			SET
			Score1= #extraReview.score1#,Score2=#extraReview.score2#,Score3=#extraReview.score3#,Score4=#extraReview.score4#,AvgPrice=#extraReview.avgPrice#,ReviewBody=#extraReview.reviewBody#,TransPark=#extraReview.transPark#,
            DishTags=#extraReview.dishTags#,ShopTags=#extraReview.shopTags#,LastIP=#extraReview.lastIP#,LastTime =NOW(), ReviewBodyLength = #extraReview.reviewBodyLength#,
            Star = #extraReview.star#,WordDifference = #extraReview.wordDifference#, SearchableTags = #extraReview.searchableTags#,POWER=#extraReview.power#
            WHERE ReviewId=#extraReview.reviewId#
    	
	
		UPDATE DP_ReviewDraft
		SET
			ReviewDraft=#reviewDraft.reviewDraft#
		WHERE ShopID=$reviewDraft.shopId$
		AND UserID=#reviewDraft.userId#
	
		UPDATE
		YY_ShopOnlineFlow SET rollback_reason=#reason#, flow_status=#status#, updated_time=NOW(), update_by=#updateBy#
		WHERE
		shop_id=#shopId#
	
		UPDATE
		YY_ShopOnlineFlow SET rollback_reason=#reason# WHERE shop_id=#shopId#
	
		UPDATE Test_CallCenter
		SET
		status = #status#
		WHERE id = #id#
	
		UPDATE Test_CallCenter
		SET
		status = #status#
		WHERE id = #id#
	
		UPDATE Test_CallCenter_Old
		SET
		status = #status#
		WHERE id = #id#
	
		UPDATE RT_DPReservation SET
			deal_status = #status#
		WHERE
			id = #reservationID#
	
		UPDATE TMS_ShopConfig SET
			sync_time = #syncTime#
		WHERE
			shop_id = #shopId#
	
		UPDATE TMS_Reservation SET
			deal_status = #status#
		WHERE
			record_id = #bookingRecordID#
	
		UPDATE
			POS_ShopSyncTime
		SET
			sync_time = #syncTime#
		WHERE
			shop_id = #shopId#
	
        UPDATE TG_ReceiptGroupCodePool
        SET Status = 0
        WHERE
        PoolID = #poolId#
        AND Status = 1 >0
    
		UPDATE TG_ThirdReceiptCode 
		SET TCode = #TCode#, SerialNumber = #serialNumber#, UpdateDate =NOW(), 
		ThirdPartyOrderID = #thirdPartyOrderId#, SeqID = #seqId# 
        WHERE DianpingReceiptID = #dianpingReceiptId# 
	
		UPDATE
			YY_BookingPayDetail
		SET
			amount = #amount#,
			balance = #balance#
		WHERE
			id = #id#
	
        
        UPDATE MRB_CRMRelation SET
        contractStatus = #contractStatus#
        WHERE
        orderId = #orderId#
        
    
        
        UPDATE MRB_CRMRelation SET
        opportunityId = #crmRelationEntity.opportunityId#,
        contractId = #crmRelationEntity.contractId#,
        contractNo = #crmRelationEntity.contractNo#,
        contractStatus = #crmRelationEntity.contractStatus#,
        salesId = #crmRelationEntity.salesId#
        WHERE
        orderId = #crmRelationEntity.orderId#
        
    
        
        UPDATE MRB_CRMRelation SET
        opportunityId = #opportunityId#
        WHERE
        id = #id#
        
    
        
        UPDATE MRB_Order SET
        shopType = #orderEntity.shopType#,
        mainCategoryId = #orderEntity.categoryId#,
        amount = #orderEntity.amount#,
        salesCityId = #orderEntity.salesCityId#,
        submitTime = #orderEntity.submitTime#,
        sourceType = #orderEntity.sourceType#,
        status = #orderEntity.status#
        WHERE
        id = #orderEntity.id#
        
    
        
        UPDATE MRB_Order SET
        amount = #amount#
        WHERE
        id = #orderId#
        
    
        
        UPDATE MRB_Order SET
        status = #status#
        WHERE
        id = #orderId#
        
    
		UPDATE TG_ReceiptAccount
		SET PrivateAccountID = #privateAccountID#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET ExpireDate = #expireDate#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET MobileNo = #mobileNo#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET Status = #status#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptVerifyRecord
		SET Status = #status#
		WHERE ReceiptVerifyRecordID = #receiptVerifyRecordID#
	
        UPDATE YY_GrouponInfo
        SET title = #title#
        WHERE deal_id = #dealID#
    
		UPDATE
			YY_ShopInfo
		SET
			avg_time = #avgTime#,
			visibility = #visibility#,
			update_by = #updateBy#,
			updated_time = #updatedTime#
		WHERE
			shop_id = #shopId#
	
		UPDATE
			YY_ShopInfo
		SET
			visibility = #visibility#
	
		UPDATE
			YY_UserIdBindInfo
		SET	 
	 		phone= #phone#,		
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE
			user_id= #userID#
	
        UPDATE
            YY_ShopSwitchConfig
        SET
            status = #status#,
            sub_status = #subStatus#,
            update_by = #opreateId#,
            update_time = now()
       WHERE
            shop_id = #shopId#
        AND
            type = #type#
    
        UPDATE TG_ReceiptAccountRefundRecord
        SET
        Status = 1,
        RefundID = #refundID#
        WHERE RecordID = #recordID#
    
        UPDATE TG_ReceiptAccountRefundRecord
        SET
        Status = 2,
        RefundID = 0
        WHERE RecordID = #recordID#
    
        UPDATE TG_Invite SET STATUS = 1,LastDate = now() WHERE UserID = #userId# AND STATUS = 0;
    
		update TG_ReceiptResetPool set
		NewSerialNumber=#serialNo#,Status=1,TG_ReceiptResetPool.updateTime=NOW()
		where PoolID=#poolId#
	
		update TG_Receipt_New set
		status=#currentStatus#
		where receiptID=#receiptId#
		
	
		update TG_Receipt_New set status=0
		where receiptID=#receiptId# 
	
		UPDATE YY_BookingConfig SET
		setting =
		#config,handler=com.dianping.columbus.marketing.service.activity.dao.BookingConfigHandler#,
		status = #status#,
		subStatus = #subStatus#,
		same_number = #sameNumber#,
		main_phone_area_code = #mainAreaCode#,
		main_phone_number = #mainPhoneNum#,
		main_phone_extension_number = #mainExtensionNum#,
		main_phone_number_type = #mainPhoneNumberType#,
		backup_phone_area_code = #backupAreaCode#,
		backup_phone_number = #backupPhoneNum#,
		backup_phone_extension_number = #backupExtensionNum#,
		backup_phone_number_type = #backupPhoneNumberType#,
		is_thunder = #isThunder#,
		update_by = #updateBy#,
		updated_time = #updateTime#,
		groupon_date = #grouponDate#,
		is_groupon = #isGroupon#,
		dial_pre_wait = #dialPreWait#,
		dial_post_wait = #dialPostWait#,
		free_start_date = #freeStartDate#,
		free_end_date = #freeEndDate#
		WHERE shop_id = #shopID#
	
		UPDATE YY_BookingConfig SET
			shop_name=#shopName#,
			shop_branchname=#shopBranchName#,
			city_id=#cityId#
		WHERE shop_id = #shopID#
	
		UPDATE YY_BookingConfig SET
		status = #status#,
		updated_time = NOW()
		WHERE shop_id = #shopID#
	
		UPDATE YY_Room SET
		name = #name#,
		min_people = #minPeople#,
		max_people = #maxPeople#,
		status = #status#,
		room_config =
		#roomConfigList,handler=com.dianping.columbus.marketing.service.activity.dao.RoomConfigHandler#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE compartment_id = #id#
	
		UPDATE
		YY_BookingConfig
		SET
		balance = balance + #addedBalance#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE
		shop_id = #shopID#
	
	
	
		UPDATE
		YY_BookingConfig
		SET
		is_groupon= #isGroupon#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE
		shop_id=#shopId#
	
		UPDATE
		YY_BookingConfig
		SET
		sub_informway = #subInformWay#,
		inform_way = #informWay#,
		update_by = #updateBy#,
		updated_time = now()
		WHERE
		shop_id=#shopId#
	
		UPDATE YY_BookingConfig SET
			$field$=#value#, update_by = #updateBy#, updated_time = now()
		WHERE shop_id = #shopID#
	
		UPDATE YY_Switchs SET 
			switch_status = (switch_status + 1) MOD 2,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE  
			switch_key = #switchKey#
			AND	switch_status = #switchStatus#
	
		UPDATE
		YY_CreditScore
		SET 
		credit_score = #creditScore#,
		credit_level = #creditLevel#,
		updated_time = #updateTime#,
		update_by = #updateBy#
		WHERE
		cellphone = #cellphone#
	
		UPDATE YY_BookingRecord SET read_status=#readStatus# where id=#id#
	
		update YY_RebateConfig
		 set shop_rebates = #shopRebates#
		 where shop_id = #shopId#
	
		)
	
		)
	
	
		UPDATE YY_CallbackEvent
		SET status = 1,
		retry = retry+1,
		updated_time = now()
		WHERE id = #id#
		AND status <> 1
	
	
	
		UPDATE YY_CallbackEvent
		SET status = 0,
		updated_time = now()
		WHERE status = 1
		AND schedule_time <= #date#
	
	
	
		UPDATE YY_CallbackEvent
 		SET updated_time = now()
 		WHERE id = #id#
 		    AND updated_time <= #date#
 	
	
	
		UPDATE  YY_CallbackEvent
		SET status = 0,
			schedule_time = #scheduleTime#,
			updated_time = now()
		WHERE id = #id#
		
	
		UPDATE YY_BookingCashAction SET
			status = #payedStatus#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE biz_id = #bizID#
			AND status = #initStatus#
	
		UPDATE YY_BookingCashEncashment
		SET status = #status#, updated_by = #operatorId#, updated_time = NOW()
		WHERE biz_id = #bizId#
	
		UPDATE TG_ThirdVerifyParty 
		SET PinKey=#thirdVerifyParty.pinKey#, 
		MacKey=#thirdVerifyParty.macKey#
		WHERE ThirdPartyID = #thirdVerifyParty.thirdPartyId# 
	
		update YY_AuditRecord
		 set main_status = #mainStatus#,
		     sub_status = #subStatus#,
		     update_time = now(),
		     update_by = #updateBy#,
		     description = #description#
		 where id = #id#   
	
		update TG_Receipt set
		status=#targetStatus#,LastDate=now()
		where receiptID=#receiptID#
	
		where receiptID=#receiptId#
	
		update TG_Receipt set status=1,LastDate=now()
		where orderID=#orderId#
	
		update TG_Receipt set refundID=#refundId# where
		receiptID=#receiptId#
	
		update TG_Receipt set MobileNo=#mobileNo# where
		receiptID=#receiptId#
	
		update TG_Receipt set serialNumber=#serialNo# where
		receiptID=#receiptId#
	
	
		UPDATE YY_BankAccount
		SET account_status = #accountStatus#
		WHERE id = #accountId#
	
		UPDATE YY_BookingCashConfig SET
			online_pay = #isOnlinePay#,
			contacts = #contacts#,
			rebates = #rebates#,
			rebate_level_updatedtime = #levelUpdateTime#,
			rebate_level = #rebateLevel#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#		
	
		UPDATE YY_BookingCashConfig SET
			online_pay = #isOnlinePay#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#		
	
		UPDATE YY_BookingCashConfig SET
			contacts = #contacts#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#		
	
	
		UPDATE YY_BookingRecord
		SET dunning_status = #dunning_status_to#
		WHERE dunning_status = #dunning_status_from# 
			AND	booking_time > #start# 
			AND booking_time < #end#
	
	
	
		UPDATE YY_BookingRecord
		SET dunning_status=10
		WHERE shop_id=#shopID# 
			AND STATUS = 20 
			AND dunning_count < 2 
			AND dunning_status IN(0,20) 
			AND booking_time < #end#
    
	
	
		UPDATE YY_BookingRecord
		SET dunning_status = 10
		WHERE shop_id= #shopID# 
			AND master_status = 2 
			AND dunning_count < 2 
			AND dunning_status IN(0,20) 
			AND booking_time < #timeBefore#
    
	
		UPDATE YY_BookingRecord SET
			serialized_id = #serializedID#,
			user_id = #userID#,
			device_id =	#deviceID#,
			name = #name#,
			gender = #gender#,
			shop_id = #shopID#,
			client_uuid = #clientUUID#,
			phone = #phone#,
			num_people = #peopleNumber#,
			position_type =	#positionType#,
			compartment_selected = #compartmentSelected#,
			booking_time = #bookingTime#,
			inform_way = #informWay#,
			status = #status#,
			consumption = #consumption#,
			visibility = #visibility#,
			update_by =	#updateBy#,
			updated_time = #updateTime#,
			master_status = #masterStatusCode#,
			detail_status = #detailStatusCode#,
			dunning_status = #dunningStatus#,
			dunning_count =	#dunningCount#,
			deal_id = #dealId#,
			is_thunder = #isThunder#,
			read_status = #readStatus#,
			record_type = #recordType#
		WHERE id = #id#
	
		UPDATE YY_BookingRecord SET
			serialized_id = #bookingRecord.serializedID#,
			user_id = #bookingRecord.userID#,
			device_id = #bookingRecord.deviceID#,
			name = #bookingRecord.name#,
			gender = #bookingRecord.gender#,
			shop_id = #bookingRecord.shopID#,
			client_uuid = #bookingRecord.clientUUID#,
			phone = #bookingRecord.phone#,
			num_people = #bookingRecord.peopleNumber#,
			position_type = #bookingRecord.positionType#,
			compartment_selected = #bookingRecord.compartmentSelected#,
			booking_time = #bookingRecord.bookingTime#,
			inform_way = #bookingRecord.informWay#,
			status = #bookingRecord.status#,
			consumption = #bookingRecord.consumption#,
			visibility = #bookingRecord.visibility#,
			update_by = #bookingRecord.updateBy#,
			updated_time = #bookingRecord.updateTime#,
			dunning_status = #bookingRecord.dunningStatus#,
			dunning_count = #bookingRecord.dunningCount#,
			dimensional_code = #bookingRecord.dimensionalCode#,
			rebate_consumption = #bookingRecord.rebateConsumption#,
			master_status = #bookingRecord.masterStatusCode#,
			detail_status = #bookingRecord.detailStatusCode#,
			deal_id = #bookingRecord.dealId#,
			is_thunder=#bookingRecord.isThunder#
		WHERE id = #bookingRecord.id# 
			AND status = #statusLock#
	
		UPDATE YY_BookingRecord SET
			serialized_id = #bookingRecord.serializedID#,
			user_id = #bookingRecord.userID#,
			device_id = #bookingRecord.deviceID#,
			name = #bookingRecord.name#,
			gender = #bookingRecord.gender#,
			shop_id = #bookingRecord.shopID#,
			client_uuid = #bookingRecord.clientUUID#,
			phone = #bookingRecord.phone#,
			num_people = #bookingRecord.peopleNumber#,
			position_type = #bookingRecord.positionType#,
			compartment_selected = #bookingRecord.compartmentSelected#,
			booking_time = #bookingRecord.bookingTime#,
			inform_way = #bookingRecord.informWay#,
			status = #bookingRecord.status#,
			consumption = #bookingRecord.consumption#,
			visibility = #bookingRecord.visibility#,
			update_by = #bookingRecord.updateBy#,
			updated_time = #bookingRecord.updateTime#,
			dunning_status = #bookingRecord.dunningStatus#,
			dunning_count = #bookingRecord.dunningCount#,
			dimensional_code = #bookingRecord.dimensionalCode#,
			rebate_consumption = #bookingRecord.rebateConsumption#,
			master_status = #bookingRecord.masterStatusCode#,
			detail_status = #bookingRecord.detailStatusCode#,
			source = #bookingRecord.source#,
			deal_id = #bookingRecord.dealId#,
			is_thunder=#bookingRecord.isThunder#
		WHERE id = #bookingRecord.id# 
			AND master_status = #masterStatusLock#
	
		UPDATE YY_BookingRecord SET
			serialized_id = #bookingRecord.serializedID#,
			user_id = #bookingRecord.userID#,
			device_id = #bookingRecord.deviceID#,
			name = #bookingRecord.name#,
			gender = #bookingRecord.gender#,
			shop_id = #bookingRecord.shopID#,
			client_uuid = #bookingRecord.clientUUID#,
			phone = #bookingRecord.phone#,
			num_people = #bookingRecord.peopleNumber#,
			position_type = #bookingRecord.positionType#,
			compartment_selected = #bookingRecord.compartmentSelected#,
			booking_time = #bookingRecord.bookingTime#,
			inform_way = #bookingRecord.informWay#,
			status = #bookingRecord.status#,
			consumption = #bookingRecord.consumption#,
			visibility = #bookingRecord.visibility#,
			update_by = #bookingRecord.updateBy#,
			updated_time = #bookingRecord.updateTime#,
			dunning_status = #bookingRecord.dunningStatus#,
			dunning_count = #bookingRecord.dunningCount#,
			dimensional_code = #bookingRecord.dimensionalCode#,
			rebate_consumption = #bookingRecord.rebateConsumption#,
			master_status = #bookingRecord.masterStatusCode#,
			detail_status = #bookingRecord.detailStatusCode#,
			source = #bookingRecord.source#,
			deal_id = #bookingRecord.dealId#,
			is_thunder=#bookingRecord.isThunder#
		WHERE id = #bookingRecord.id#
			AND	master_status = #masterStatus#
	
		UPDATE
			YY_BookingRecord
		SET
			visibility = #visibility#,
			updated_time = #updateTime#,
			update_by = #updateBy#
		WHERE
			id IN ($ids$)
	
		UPDATE
		YY_BookingRecord
		SET
		serialized_id = #serializedID#
		WHERE
		id = #bookingRecordID#
	
		UPDATE YY_BookingRecord SET read_status=#readStatus# where id=#id#
	
		UPDATE YY_BookingRecord 
		SET read_status = 0
		WHERE phone = #phone#
		AND read_status = 1
	
		UPDATE DP_Review
		SET
		VoteGoodTotal = VoteGoodTotal + #delta#
		WHERE
		ReviewId=#reviewId#
	
		UPDATE DP_Review
		SET
		FollowNoteNo = FollowNoteNo + #delta#
		WHERE
		ReviewId=#reviewId#
	
		
			UPDATE DP_Review
			SET
			Score1= #review.score1#,Score2=#review.score2#,Score3=#review.score3#,Score4=#review.score4#,AvgPrice=#review.avgPrice#,TransPark=#review.transPark#,
            DishTags=#review.dishTags#,ShopTags=#review.shopTags#,LastIP=#review.lastIP#,LastTime =NOW(), ReviewBodyLength = #review.reviewBodyLength#, 
            Star = #review.star#,WordDifference = #review.wordDifference#, SearchableTags = #review.searchableTags#,POWER=#review.power#
            WHERE ReviewId=#review.reviewId#
    	
	
		
			UPDATE DP_Review
			SET
			PicTotal = #picTotal#
			WHERE
			ReviewId=#reviewId#
    	
	
        
			UPDATE DP_Review
			SET
			Rank = #rank#
			WHERE
			ReviewId=#reviewId#
    	
    
		UPDATE DP_Review
		SET
		Power=#power#
		WHERE
		ReviewId = #reviewId#
	
	
		
	
        UPDATE DP_Review
        SET Rank = #rank#
        WHERE ReviewID = #reviewId#
          AND ShopID = #shopId#
    
		UPDATE 
			YY_Power 
		SET 
			power_name=#powerName#, manager=#Manager#, comment=#comment#, is_valid=#isValid#,
			created_by= #createBy#, created_time=#createTime#, updated_by=#updateBy#, updated_time=#updateTime#
		WHERE 
			power_id=#powerId#
	
		UPDATE
			YY_PowerStaff 
		SET 
			 is_valid=0
		WHERE 
			power_id=#powerId#
	
		UPDATE
			YY_PowerStaff 
		SET 
			 is_valid=0
		WHERE 
			staff_id=#staffId#
	
		UPDATE 
			YY_PowerStaff 
		SET 
			comment=#comment#, is_valid=#isValid#,
			created_by= #createBy#, created_time=#createTime#, updated_by=#updateBy#, updated_time=#updateTime#
		WHERE 
			power_id=#powerId#	AND	staff_id=#staffId#
	
		UPDATE YY_BookingPay
		SET
			balance = balance + #amount#
		WHERE
			shop_id = #shopID#
	
		UPDATE 
			YY_BookingStaff 
		SET 
			staff_name=#staffName#,comment=#comment#, is_valid=#isValid#,
			created_by= #createBy#, created_time=#createTime#, updated_by=#updateBy#, updated_time=#updateTime#
		WHERE 
			staff_id=#staffId#
	
  		UPDATE CI_IPhonePush SET PToken = '' WHERE PToken = #ptoken#
   
		UPDATE YY_StationLetters SET
			read_status = #status# 
		WHERE id = #id#
	
		UPDATE YY_BookingRecord 
		SET STATUS = #status#
		WHERE id = #id#
	
		UPDATE YY_BookingRecord 
		SET inform_way=#inform_way# 
		WHERE id = #id#
	
		UPDATE RT_ShopInventory
		SET
			bookable_map = #bookableMap#
		WHERE
			shop_id = #shopId# AND TO_DAYS(inventory_date) = TO_DAYS(#date#) AND table_id = #tableId#;
	
		UPDATE RT_ShopStatus SET
			bookable = #bookable#
		WHERE
			shop_id = #shopId# 
			AND TO_DAYS(status_date) = TO_DAYS(#date#);
	
        UPDATE TG_ReceiptAccountRefundRecord
        SET
        Status = 1,
        RefundID = #refundID#
        WHERE RecordID = #recordID#
    
        UPDATE TG_ReceiptAccountRefundRecord
        SET
        Status = 2,
        RefundID = 0
        WHERE RecordID = #recordID#
    
        update TG_ReceiptNotifyJob
        set JobStatus = #jobStatus#,
        RetryCount = #retryCount#,
        ExecuteDetail = #executeDetail#,
        UpdateTime = current_timestamp
        where JobID = #jobId#
    
	
	
        UPDATE TG_ReceiptResetPool SET Status=2, UpdateTime = NOW() WHERE PoolID = #poolId#;
    
		UPDATE
			YY_BookingPay
		SET
			balance = #balance#,
			update_by = #updateBy#,
			updated_time = #updatedTime#
		WHERE
			shop_id = #shopId#
	
        
        UPDATE MRB_CPMInventory_Shop SET 
        Count = Count + #count# 
		WHERE CityID = #cityid# AND ShopType = #shoptype# AND DayID = #dayid# 
        
    
        
        UPDATE MRB_CPMInventory_Shop SET 
        Count = Count - #count# 
		WHERE CityID = #cityid# AND ShopType = #shoptype# AND DayID = #dayid# 
        
    
        
        UPDATE MRB_OrderItem SET
        orderID = #orderItemEntity.orderId#,
        groupID = #orderItemEntity.groupId#,
        cityID = #orderItemEntity.cityId#,
        productID = #orderItemEntity.productId#,
        priceID = #orderItemEntity.priceId#,
        amount = #orderItemEntity.amount#,
        shopID = #orderItemEntity.shopId#,
        newShopID = #orderItemEntity.newShopId#,
        contentID = #orderItemEntity.contentId#,
        displayTemplateID = #orderItemEntity.displayTemplateId#,
        status = #orderItemEntity.status#
        WHERE
        id = #orderItemEntity.id#
        
    
        
        UPDATE MRB_OrderItemDuration SET
        orderItemId = #durationEntity.orderItemId#,
        beginTime = #durationEntity.beginTime#,
        endTime = #durationEntity.endTime#,
        CpmAmount = #durationEntity.cmpAmount#,
        status = #durationEntity.status#
        WHERE
        id = #durationEntity.id#
        
    
        
        UPDATE MRB_OrderItemDuration SET
        status = #durationStatus#
        WHERE
        orderItemId = #orderItemId#
        
    
		UPDATE
		YY_RechargeInvoiceRecord
		SET
		invoice_apply_date = #invoiceApplyDate#,invoice_release_date = #invoiceReleaseDate#,
		invoice_status = #invoiceStatus#,invoice_type = #invoiceType#,
		invoice_title = #invoiceTitle#,invoice_no = #invoiceNo#,
		shop_name = #shopName#,
		express_id = #expressId#,
		update_by = #updateBy#,updated_time = #updateTime#
		WHERE
		invoice_apply_id = #invoiceApplyId#
	
		UPDATE YY_RechargeRemindConfig SET
		setting = #config,handler=com.dianping.columbus.booking.rechargeremindconfig.dao.RechargeRemindConfigHandler#,
		remind_threshold=#remindThreshold#,
		update_by = #updateBy#, updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
		UPDATE YY_SellerShop
		SET
			shop_name=#shopName#,
			branch_name=#branchName#,
			status = #status#,
			pre_pay_money = #prePayMoney#,
			seller_name=#sellerName#,
			city = #cityId#,
			online_time = #onlineTime#,
			updated_by = #updateBy#,
			updated_time = #updateTime#
		WHERE
			shop_id = #shopId#
	
		UPDATE YY_ShopConfig SET
			create_account = #createAccount#,
			online_sign = #isOnlineSign#,
			accept_contract = #isAcceptContract#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
		UPDATE YY_ShopConfig SET
			create_account = #createAccount#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
		UPDATE YY_ShopConfig SET
			accept_contract = #isAcceptContract#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
		UPDATE YY_ShopConfig SET
			online_sign = #isOnlineSign#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
	 	UPDATE
	 		YY_AccountConfig
	 	SET
	 		contract_phone = #contractPhone#,
	 		shop_phone = #shopPhone#,
	 		contract_name = #contractName#,
	 		bill_title = #billTitle#,
	 		bill_address = #billAddress#,
	 		bill_receiver_name = #billReceiverName#,
	 		bill_receiver_phone = #billReceiverPhone#,
	 		pre_pay_money = #prePayMoney#,
	 		update_by = #updateBy#,
	 		updated_time = #updateTime#
		WHERE shop_id = #shopId#
	
		UPDATE
	 		YY_AccountConfig
	 	SET
	 	  description = #descriptionInfo#,
	 	  update_by = #updateBy#,
	 	  updated_time = #updateTime#
	 	WHERE
	 	  shop_id = #shopId#  
	
	 	UPDATE
	 		YY_BillingConfig
	 	SET
	 		using_contract_config = #usingContractConfig#,
	 		bill_title = #billTitle#,
	 		bill_address = #billAddress#,
	 		payment_director_phone = #paymentDirectorPhone#,
	 		payment_executor_phone = #paymentExecutorPhone#,
	 		update_by = #updateBy#,
	 		updated_time = #updateTime#
		WHERE id = #id#
	
		UPDATE YY_BookingPaySequence SET
			biz_id = biz_id + 1
		WHERE biz_id = #newSequence#
	
	 	UPDATE YY_ContractHead SET
	 		contract_id = #contractID#,
	 		status = #status#,
	 		update_by = #updateBy#, 
	 		updated_time = #updateTime#
		WHERE contracthead_id = #contractHeadID#
	
	 	UPDATE YY_ContractHead SET
            apply_status = #applyStatus#,
            update_by = #updateBy#,
	 		updated_time = #updateTime#
		WHERE contract_id = #contractId#
	
		UPDATE YY_ContractHead SET
		reason = #reason#,
		update_by = #updateBy#, 
	 	updated_time = #updateTime#
		WHERE contracthead_id = #contractHeadID#
	
		UPDATE YY_BookingConfig SET 
		STATUS = 20,
		update_by = #updateBy#, 
	 	updated_time = #updateTime#
		WHERE shop_id IN (
		SELECT shop_id FROM YY_ContractShop
		WHERE contract_id = #contractHeadID#
		)
	
		UPDATE YY_ContractSequence SET
			contract_id = contract_id + 1
		WHERE
			year_id = #year# and contract_id = #contractID#
	
        UPDATE
            YY_ContractHead
        SET
            wechat_pay = #targetStatus#,
            update_by = #operator#,
	 		updated_time = now()
	 	WHERE
	 	    contracthead_id =#crmId#
    
		UPDATE YY_MonthlyBill SET
			shop_id = #shopID#,
			year = #year#,
			month = #month#,
			status = #status#,
			consumption = #consumption#,
			commission_rate = #commissionRate#,
			paid = #paid#,
			actual_payment = #actualPayment#,
			update_by = #updateBy#,
			updated_time = #updateTime#,
			replenish = #replenish#
		WHERE
			id = #id#
	
		UPDATE TG_OrderStatus SET Status = #newStatus#
		WHERE OrderID = #orderId# AND Status = #oldStatus# AND BizType = #bizType#
	
		UPDATE TG_ThirdReceiptCode 
		SET TCode = #TCode#, SerialNumber = #serialNumber#, UpdateDate =NOW(), 
		ThirdPartyOrderID = #thirdPartyOrderId#, SeqID = #seqId# 
        WHERE DianpingReceiptID = #dianpingReceiptId# 
	
		update
		TG_Deliver set quantity=#quantity#,DealID=#dealId#, DealGroupID=#dealGroupId#,Status=1 where OrderID=#orderId# and Status=0
	
		update
		TG_Deliver set RefundingQuantity=RefundingQuantity+#refundingQuantity#,
		IsShow=CASE RefundingQuantity>0 WHEN true then 0 ELSE CASE Quantity - RefundQuantity WHEN 0 THEN 0 ELSE 1 END END
		where OrderID=#orderId#
		
	
		update
		TG_Deliver set RefundQuantity=RefundQuantity+#refundQuantity#,RefundingQuantity=RefundingQuantity-#refundQuantity#,
		IsShow=CASE RefundingQuantity>0 WHEN true then 0 ELSE CASE Quantity - RefundQuantity WHEN 0 THEN 0 ELSE 1 END END
		where OrderID=#orderId#
		
	
		UPDATE YY_ActivityInfo SET title=#title#, channel=#channel#, banner_web=#bannerWeb#, banner_app=#bannerApp#, content_bg=#bgContent#, 
		tail_bg=#bgTail#, rule=#rule#, `status`=20, update_by=#updateBy#, updated_time=NOW()
		WHERE activity_id=#id#
	
		UPDATE YY_ActivityItemInfo SET item_title=#title#, title_bg=#bgTitle#, content_bg=#bgContent#, update_by=#updateBy#, updated_time=NOW()
		WHERE item_id=#id#
	
        UPDATE YY_ActivityItemShop 
        SET shop_ids = #shop_ids#,
            updated_time = NOW(),
            update_by = #update_by#
         WHERE item_id = #item_id#
          AND city_id = #city_id#
    
		UPDATE YY_Notifications SET
			read_status = #status# 
		WHERE phone = #phone#
	
		UPDATE YY_Notifications SET
			read_status = #status# 
		WHERE phone = #phone#
		AND type = #type# 
	
		UPDATE
			YY_SalesTask
		SET 
			sales_name = #salesName#
		WHERE
			id = #salesTaskId#
	
		update TG_Receipt set
		status=#currentStatus#
		where receiptID=#receiptId#
	
		where receiptID=#receiptId#
	
		update TG_Receipt set status=0
		where orderID=#orderId#
	
		update TG_Receipt set refundID=null where
		receiptID=#receiptId#
	
		UPDATE
			YY_BookingCashEncashment
		SET
			status = #status#
		WHERE
			biz_id = #bizId#
	
		UPDATE ZS_FollowNoteLast
		SET
		LastUserId=#reviewFollowNote.userId#,LastUserNickName=#userNickName#,FollowNoteId=#reviewFollowNote.followNoteId#, NoteCount=#floorNo#, LastNoteTime=#reviewFollowNote.addDate#
		WHERE
		MainNoteId = #reviewFollowNote.mainNoteId# AND NoteType = #reviewFollowNote.noteType#
	
		UPDATE ZS_FollowNote
		SET
		NoteBody= #noteBody#, UpdateDate=NOW(),Power=#power#,VerifyStatus=#verifyStatus#
		WHERE
		FollowNoteId = #followNoteId#
	
		UPDATE ZS_FollowNoteLast
		SET
		NoteCount=NoteCount-1
		WHERE
		MainNoteId = #mainNoteId# AND NoteType = #noteType#
	
		UPDATE ZS_FollowNoteLast L, ZS_FollowNote F
		SET
		L.NoteCount=L.NoteCount-1,L.LastUserID=F.UserId,L.LastUserNickName=#maxUserNickName#,L.FollowNoteId=#maxFollowNoteId#,L.LastNoteTime=F.AddDate
		WHERE
		L.MainNoteId=#mainNoteId# AND L.NoteType=#noteType# AND F.FollowNoteID=#maxFollowNoteId#
	
		UPDATE ZS_FollowNote
		SET
		VerifyStatus=#verifyStatus#
		WHERE
		FollowNoteId = #followNoteId#
	
		UPDATE DP_ReviewLog SET LogReason = #logReason#
		WHERE reviewID = #reviewId#
	
		UPDATE YY_ValentineCode
		SET status = #status#
		WHERE code = #code# AND status != #status#
	
		UPDATE YY_ValentinePhoneCode
		SET code = #code#
		WHERE phone = #phone#
	
		UPDATE YY_CustomerServiceStaff
		SET status = #status#, updated_time = NOW()
		WHERE staff_id = #staffId#
	
		UPDATE YY_CustomerServiceStaff
		SET last_active_time = #lastActiveTime#, updated_time = NOW()
		WHERE staff_id = #staffId#
	
		UPDATE YY_MobiActivityInfo SET title=#title#, banner=#banner#, winner_info=#winnerInfo#, 
		rmd_shop=#rmdShop#, rmd_shop_img=#rmdShopImg#, rmd_desc=#rmdDesc#, similar_shops=#similarShops#, 
		more_shops=#moreShops#, rule=#rule#, update_by=#updateBy#, updated_time=NOW()
		WHERE id=#id#
	
		UPDATE
		YY_ShopDunningRecord SET update_time=NOW(), operator_id=#operatorId#,
		op_status=1, shop_status=#shopStatus#
		WHERE id=#id#
	
		UPDATE
		YY_ShopDunningRecord SET update_time=NOW(), operator_id=#operatorId#,
		op_status=#opStatus#, shop_status=#shopStatus#
		WHERE id=#id#
	
		UPDATE
		YY_ShopDunningRecord SET update_time=NOW(),
		sms_num=sms_num+#smsCount#, call_num=call_num+#callCount#,
		shop_status=#shopStatus#, appear_time=#remindDate#, comment=#comment#
		WHERE id=#id#
	
	
		UPDATE YY_ShopInfo SET dunning_type=#dunningType# WHERE shop_id=#shopId#
	
	
		UPDATE
		YY_BookingRecord
		SET dunning_status=#dunning_status_to#
		WHERE
		dunning_status=#dunning_status_from# AND
		booking_time > #start# AND booking_time < #end#
	
	
	
		UPDATE YY_BookingRecord
		SET dunning_status=10
		WHERE shop_id=#shopID# AND STATUS=20 AND dunning_count<2 AND dunning_status IN(0,20) AND booking_time <#end#
    
	
		UPDATE YY_BookingRecord SET
		serialized_id = #serializedID#,
		user_id = #userID#,
		device_id =
		#deviceID#,
		name = #name#,
		gender = #gender#,
		shop_id = #shopID#,
		client_uuid = #clientUUID#,
		phone = #phone#,
		num_people = #peopleNumber#,
		position_type =
		#positionType#,
		compartment_selected =
		#compartmentSelected#,
		booking_time = #bookingTime#,
		inform_way = #informWay#,
		status = #status#,
		consumption =
		#consumption#,
		visibility = #visibility#,
		update_by =
		#updateBy#,
		updated_time = #updateTime#,

		dunning_status = #dunningStatus#,
		dunning_count =
		#dunningCount#

		WHERE id = #id#
	
		UPDATE YY_BookingRecord SET
		serialized_id = #bookingRecord.serializedID#,
		user_id =
		#bookingRecord.userID#,
		device_id = #bookingRecord.deviceID#,
		name = #bookingRecord.name#,
		gender = #bookingRecord.gender#,
		shop_id =
		#bookingRecord.shopID#,
		client_uuid =
		#bookingRecord.clientUUID#,
		phone = #bookingRecord.phone#,
		num_people = #bookingRecord.peopleNumber#,
		position_type = #bookingRecord.positionType#,
		compartment_selected =
		#bookingRecord.compartmentSelected#,
		booking_time = #bookingRecord.bookingTime#,
		inform_way = #bookingRecord.informWay#,
		status = #bookingRecord.status#,
		consumption =
		#bookingRecord.consumption#,
		visibility =
		#bookingRecord.visibility#,
		update_by = #bookingRecord.updateBy#,
		updated_time = #bookingRecord.updateTime#,
		dunning_status =
		#bookingRecord.dunningStatus#,
		dunning_count = #bookingRecord.dunningCount#,
		dimensional_code = #bookingRecord.dimensionalCode#
		WHERE id =
		#bookingRecord.id# AND status = #statusLock#
	
		)
		AND
		booking_time < #nowTime#
	
		)
		AND
		booking_time >= #nowTime# AND status IN ($statusList$)
	
		UPDATE
		YY_BookingRecord
		SET
		serialized_id = #serializedID#
		WHERE
		id = #bookingRecordID#
	
		UPDATE RT_DPReservation SET
			deal_status = #status#
		WHERE
			id = #reservationID#
	
		UPDATE RT_ShopStatus SET
			bookable = #bookable#
		WHERE
			shop_id = #shopId# 
			AND TO_DAYS(status_date) = TO_DAYS(#date#);
	
		UPDATE ZS_FeedBack
		SET
			FeedGroupID = #feedGroupId#
		WHERE
			FeedID = #feedId#
    
        UPDATE TG_ReceiptAccountOrder
        SET
        UsedAmount = #usedAmount#,
        RefundedAmount = #refundedAmount#,
        ReceiptBalance = #receiptBalance#,
        LockVersion = LockVersion + 1
        WHERE ReceiptAccountOrderID = #receiptAccountOrderID# AND LockVersion = #lockVersion#
    
		UPDATE YY_MonthlyBill
		SET consumption = #consumption#, paid = #paid#
		WHERE shop_id = #shopId#
		 AND year = #year#
	     AND month = #month#
	
		UPDATE YY_MonthlyBill
		SET  paid = #paid#
		WHERE shop_id = #shopId#
		 AND year = #year#
	     AND month = #month#
	
		UPDATE YY_MonthlyBill
		SET consumption = #consumption#
		WHERE shop_id = #shopId#
		 AND year = #year#
	     AND month = #month#
	
        
        UPDATE MRB_CPMInventoryLog SET 
        Status = 0 
		WHERE OrderItemID = #orderitemid#
        
    
        
        UPDATE MRB_CPMInventory_Search SET 
        Count = Count + #count# 
		WHERE CityID = #cityid# AND ShopType = #shoptype# AND DayID = #dayid# 
        
    
        
        UPDATE MRB_CPMInventory_Search SET 
        Count = Count - #count# 
		WHERE CityID = #cityid# AND ShopType = #shoptype# AND DayID = #dayid# 
        
    
        UPDATE TG_ReceiptAccountOrder
        SET
        UsedAmount = #usedAmount#,
        RefundedAmount = #refundedAmount#,
        ReceiptBalance = #receiptBalance#,
        LockVersion = LockVersion + 1
        WHERE ReceiptAccountOrderID = #receiptAccountOrderID# AND LockVersion = #lockVersion#
    
        UPDATE TG_ReceiptPrepaidCardNumberSequence
        SET
        CardNumber = #cardNumber#,
        LockVersion = LockVersion + 1
        WHERE
        PrepaidCardGroupID = #prepaidCardGroupID# AND LockVersion = #lockVersion#
    
		UPDATE YY_PhoneNotification 
		SET notification_count = #notificationCount#,
 		update_by = #updateBy#,
 		updated_time = #updateTime#
		WHERE phone = #phone#
	
		UPDATE YY_PhoneNotification 
		SET notification_count = 0,
		updated_time = NOW()
		WHERE phone = #phone#
		AND notification_count > 0
	
		UPDATE
		YY_CreditScore
		SET 
		credit_score = #creditScore#,
		credit_level = #creditLevel#,
		updated_time = #updateTime#,
		update_by = #updateBy#
		WHERE
		cellphone = #cellphone#
	
		UPDATE YY_LotteryAward SET 
			award_status = '20',
			updated_time = #updatedTime#
		WHERE
			award_id = #awardId# AND award_status = '10'
	
		UPDATE YY_Notifications SET
			read_status = #status# 
		WHERE phone = #phone#
	
		UPDATE YY_Notifications SET
			read_status = #status# 
		WHERE phone = #phone#
		AND type = #type# 
	
		
			UPDATE YY_ShopReceiptStatus
			SET status=#status#
			WHERE shop_id=#shopID# 
	    
	
		UPDATE
			YY_RebateConfig
		SET
			has_pos = #hasPos#,
			rebate_type = #type#,
			money = #money#,
			title = #title#,
			detail = #detail#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE
			shop_id = #shopID#
	
		UPDATE YY_BookingConfig SET
		setting =
		#config,handler=com.dianping.columbus.booking.bookingconfig.dao.BookingConfigHandler#,
		status = #status#,
		subStatus = #subStatus#,
		same_number = #sameNumber#,
		main_phone_area_code = #mainAreaCode#,
		main_phone_number = #mainPhoneNum#,
		main_phone_extension_number = #mainExtensionNum#,
		main_phone_number_type = #mainPhoneNumberType#,
		backup_phone_area_code = #backupAreaCode#,
		backup_phone_number = #backupPhoneNum#,
		backup_phone_extension_number = #backupExtensionNum#,
		backup_phone_number_type = #backupPhoneNumberType#,
		is_thunder = #isThunder#,
		update_by = #updateBy#,
		updated_time = #updateTime#,
		groupon_date = #grouponDate#,
		is_groupon = #isGroupon#,
		dial_pre_wait = #dialPreWait#,
		dial_post_wait = #dialPostWait#,
		free_start_date = #freeStartDate#,
		free_end_date = #freeEndDate#
		WHERE shop_id = #shopID#
	
		UPDATE YY_BookingConfig SET
			shop_name=#shopName#,
			shop_branchname=#shopBranchName#,
			city_id=#cityId#
		WHERE shop_id = #shopID#
	
		UPDATE YY_BookingConfig SET
		status = #status#,
		updated_time = NOW()
		WHERE shop_id = #shopID#
	
		UPDATE YY_Room SET
		name = #name#,
		min_people = #minPeople#,
		max_people = #maxPeople#,
		status = #status#,
		room_config =
		#roomConfigList,handler=com.dianping.columbus.booking.bookingconfig.dao.RoomConfigHandler#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE compartment_id = #id#
	
		UPDATE
		YY_BookingConfig
		SET
		balance = balance + #addedBalance#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE
		shop_id = #shopID#
	
	
	
		UPDATE
		YY_BookingConfig
		SET
		is_groupon= #isGroupon#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE
		shop_id=#shopId#
	
		UPDATE
		YY_BookingConfig
		SET
		sub_informway = #subInformWay#,
		inform_way = #informWay#,
		update_by = #updateBy#,
		updated_time = now()
		WHERE
		shop_id=#shopId#
	
		UPDATE YY_BookingConfig SET
			$field$=#value#, update_by = #updateBy#, updated_time = now()
		WHERE shop_id = #shopID#
	
		UPDATE YY_BookingConfig
		SET
		setting = #config,handler=com.dianping.columbus.booking.bookingconfig.dao.BookingConfigHandler#,
		update_by = #updateBy#,
		updated_time = NOW()
		WHERE
		shop_id=#shopId#
	
		UPDATE YY_BookingPayAction
		SET
			shop_id = #shopId#,
			account_id = #accountId#,
			amount = #amount#,
			type = #type#,
			status = #status#
		WHERE
			biz_id = #bizId#
	
		UPDATE YY_BookingPayAction
		SET
			status = #status#
		WHERE
			biz_id = #bizId#
	
	 UPDATE YY_CrmContractCache SET 
	 	crm_response_result=#crmResponseResult# WHERE crm_id = #crmId#;
	
		update YY_FeatureContingency set status = #status#
		where fc_id = #fcID#
	
		UPDATE
			YY_OperationLog SET
			operate_type = #operateType#,
			operate_id = #operateID#,
			operate_action = #operateAction#,
			operate_detail = #operateDetail#,
			operator = #operator#,
			source = #source#,
		WHERE
			id = #id#
	
	 	UPDATE YY_Contract SET
	 		start_time = #start#,
	 		end_time = #end#,
	 		party_name = #partyName#,
	 		phone = #phone#,
	 		balance_day = #balanceDay#,
	 		backend_balance = #backendBalance#,
	 		monthly_bill_day = #monthlyBillDay#,
	 		commission_rate = #commissionRate#,
	 		deposit = #deposit#,
			title = #title#,
			address = #address#,
			zip_code = #zipCode#,
			party_a_name = #partyAName#,
			party_a_phone = #partyAPhone#,
			payment_mode = #paymentMode#,
			payment_director_phone = #paymentDirectorPhone#,
			payment_executor_phone = #paymentExecutorPhone#,
	 		update_by = #updateBy#, 
	 		updated_time = #updateTime#
		WHERE contract_id = #contractHeadID#
	
        UPDATE YY_BookingPay
        SET balance = balance + #amount#,
        update_by = #updateBy#,
        updated_time = NOW()
        WHERE shop_id = #shopID#
    
		UPDATE TG_ReceiptAccount
		SET PrivateAccountID = #privateAccountID#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET ExpireDate = #expireDate#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET MobileNo = #mobileNo#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET Status = #status#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
        UPDATE TG_ReceiptPrepaidCardNumberSequence
        SET
        CardNumber = #cardNumber#,
        LockVersion = LockVersion + 1
        WHERE
        PrepaidCardGroupID = #prepaidCardGroupID# AND LockVersion = #lockVersion#
    
		UPDATE TG_ReceiptVerifyRecord
		SET Status = #status#
		WHERE ReceiptVerifyRecordID = #receiptVerifyRecordID#
	
	
	
		update TG_JournalVoucher
		set status=#targetStatus#,updateTime=now()
		where VoucherID=#journalVoucherId# and 
		(status=#currentStatus# or updateTime<=date_sub(now(),interval 1 hour))
	
	
	
		update TG_JournalVoucher set TotalAmount=#totalAmount#, DealProductType=#productType#
		where VoucherID=#journalVoucherId#
	
	
		update TG_Receipt_New set
		status=#targetStatus#,LastDate=now()
		where receiptID=#receiptId#
		
	
		where receiptID=#receiptId# 
	
		update TG_Receipt_New set MobileNo=#mobileNo# where
		receiptID=#receiptId#
	
		update TG_Receipt_New set SerialNumber=#serialNo#
		where
		receiptID=#receiptId#
	
	
	
		update TG_ReceiptVerifyRecord set status=1 where
		ReceiptVerifyRecordID=#receiptVerifyRecordId#
	
		update TG_ReceiptVerifyRecord set status=2 where
		ReceiptVerifyRecordID=#receiptVerifyRecordId#
	
	
		UPDATE YY_MobiActivityInfo SET title=#title#, banner=#banner#, winner_info=#winnerInfo#, 
		rmd_shop=#rmdShop#, rmd_shop_img=#rmdShopImg#, rmd_desc=#rmdDesc#, similar_shops=#similarShops#, 
		more_shops=#moreShops#, rule=#rule#, update_by=#updateBy#, updated_time=NOW()
		WHERE id=#id#
	
		UPDATE
		YY_PhoneNotification
		SET notification_count = #notificationCount#,
		update_by = #updateBy#,
		updated_time = #updateTime#
		WHERE phone = #phone#
	
		UPDATE
		YY_WebBannerConfig SET
		priority=#priority#,status=#status#,begin_time=#beginTime#,end_time=#endTime#,title=#title#,page_type=#pageType#,page_url=#pageUrl#,banner_url=#bannerUrl#,description=#description#,update_time=NOW(),update_by=#updateBy#
		WHERE id = #id#
	
	    UPDATE
	    YY_WebBannerConfig SET
	    status = #status#
	    WHERE id= #id#
	
	
		UPDATE DP_ReviewExtInfo SET 
		Category=#reviewExtInfo.category#
		WHERE ReviewID=#reviewExtInfo.reviewId#
	
	
		UPDATE DP_ReviewKeyWord 
		SET DishTag = #reviewKeyWord.dishTag#, UnknownDishTag = #reviewKeyWord.unknownDishTag#, LastTime = NOW()
		WHERE ReviewID = #reviewKeyWord.reviewId# 
	
		UPDATE YY_BankAccount b
		SET
			b.account_no = #accountNo#,
			b.account_name = #accountName#,
			b.account_type = #accountType#,
			b.bank_name = #bankName#,
			b.branch_name = #branchName#,
			b.bank_qualifiedname = #bankQualifiedName#,
			b.bank_number = #bankNumber#,
			b.province = #province#,
			b.city = #city#,
			b.account_status = #accountStatus#,
			b.updated_by = #operator#,
			b.updated_time = NOW()
		WHERE
			b.id = #id#
	
		UPDATE YY_BankAccountShop
		SET
			approval_status = #status#,
			comments = #comments#,
			updated_by = #operator#,
			updated_time = NOW()
		WHERE
			id = #id#
	
		UPDATE YY_JobConfigInfo
		SET status=#status#
		WHERE id=#jobID#
	
		UPDATE YY_BankAccount b
		SET
			b.account_no = #accountNo#,
			b.account_name = #accountName#,
			b.account_type = #accountType#,
			b.bank_name = #bankName#,
			b.branch_name = #branchName#,
			b.bank_qualifiedname = #bankQualifiedName#,
			b.bank_number = #bankNumber#,
			b.province = #province#,
			b.city = #city#,
			b.account_status = #accountStatus#,
			b.updated_by = #operator#,
			b.updated_time = NOW()
		WHERE
			b.id = #id#
	
		WHERE
			r.id = #id#
	
		UPDATE YY_MayReplyWechatMessage
		SET	status = 20, updated_time = NOW()
		WHERE booking_record_id = #bookingRecordId# AND type = #type# AND status = 10
	
		UPDATE YY_ActivityInfo SET title=#title#, channel=#channel#, banner_web=#bannerWeb#, banner_app=#bannerApp#, content_bg=#bgContent#, 
		tail_bg=#bgTail#, rule=#rule#, `status`=20, update_by=#updateBy#, updated_time=NOW()
		WHERE activity_id=#id#
	
		UPDATE YY_ActivityItemInfo SET item_title=#title#, title_bg=#bgTitle#, content_bg=#bgContent#, update_by=#updateBy#, updated_time=NOW()
		WHERE item_id=#id#
	
        UPDATE YY_ActivityItemShop 
        SET shop_ids = #shop_ids#,
            updated_time = NOW(),
            update_by = #update_by#
         WHERE item_id = #item_id#
          AND city_id = #city_id#
    
		UPDATE YY_MongoRule
		SET
			qtype = #qtype#, 
			cond = #cond#, 
			keyString = #keyString#, 
			keyJson = #keyJson#, 
			initial = #initial#, 
			reduce = #reduce#, 
			finalize = #finalize#, 
			sort = #sort#, 
			otherInfo = #otherInfo#,
			description = #description#
		WHERE
			id = #id#
	
		update YY_App_Config
		set content = #content#
		where 
			app_name = #appName#
			and config_name = #configName#
	
		update YY_CC_BookingEvent
		set init_task_id = id
		where id = #id#
	
	
		UPDATE YY_CC_BookingEvent 
		SET updated_time=NOW()
		WHERE id=#id# and updated_time<=#date#
	
	
	
 		UPDATE YY_CC_BookingEvent
 		SET line_lock = 0,
 		status = 50
 		WHERE line_lock = 1
 		AND send_time <=#date#
 	
	
	
		UPDATE YY_CC_BookingEvent
 		SET updated_time = now(),
 		status = 50
 		WHERE 
 		id = #id#
 		AND updated_time <=#date#
 	
	
		UPDATE
		YY_CC_BookingEvent
		SET
		status = #status#,
		updated_time = now()
		WHERE
		id = #id#
	
	
		UPDATE YY_CC_BookingEvent
		SET
			line_lock = 1,
			send_time =	now(),
			updated_time = now()
		WHERE id = #id# and line_lock<>1
	
	
		UPDATE YY_CC_BookingEvent
		SET
		line_lock = #lock#,
			send_time =	now(),
		updated_time = now()
		WHERE id = #id#
	
		UPDATE
		YY_CC_BookingEvent
		SET
		retry_times = retry_times+1,
		scheduled_time = #scheduled_time#,
		line_lock = 0,
		status = 10,
		updated_time = now()
		WHERE
		id = #id#
	
		UPDATE
		YY_CC_BookingEvent 
		SET 
		line_lock = 0, 
		status = 10
		WHERE 
		id = #id#
	
		UPDATE
		YY_CC_BookingEvent
		SET 
		eventmessage = #eventMsg# 
		WHERE
		id = #id#
	
		UPDATE
		YY_CC_BookingEvent
		SET 
		id = #newid# 
		WHERE
		id = #id#
	
		update YY_CC_BookingEvent
		set send_time = #sendTime#, updated_time = #sendTime#
		where 
			init_task_id = #initTaskID#
	
		update YY_CC_BookingEvent
		set scheduled_time = #scheduleTime#
		where 
			init_task_id = #initTaskID#
	
		UPDATE YY_OperationLog SET
		operate_type = #operateType#,
		operate_id = #operateID#,
		operate_action
		= #operateAction#,
		operate_detail = #operateDetail#,
		operator = #operator#
		WHERE
		id = #id#
	
		UPDATE TMS_Menu	SET
			code = #code#,
			name = #name#,
			category = #category#,
			sub_category = #subCategory#,
			description = #description#,
			alias1 = #alias1#,
			alias2 = #alias2#,
			spec = #spec#,
			unit = #unit#,
			price = #price#,
			member_price = #memberPrice#,
			is_current_price = #isCurrentPrice#
		WHERE
			menu_id = #id# and shop_id = #shopID#
	
		UPDATE TMS_Menu	SET
			menu_version = #version#
		WHERE
			shop_id = #shopID#
	
		UPDATE TG_DealCurrentJoin SET CurrentJoin = CurrentJoin + #quantity#, Version = Version + 1
		WHERE DealID = #dealID# AND (CurrentJoin + #quantity# <= #maxJoin#)
	
		UPDATE TG_DealGroupCurrentJoin SET CurrentJoin = CurrentJoin + #quantity#, Version = Version + 1
		WHERE DealGroupID = #dealGroupID# AND (CurrentJoin + #quantity# <= #maxJoin#)
	
		UPDATE TG_DealCurrentJoin SET CurrentJoin = CurrentJoin - #quantity#, Version = Version + 1
		WHERE DealID = #dealID#
	
		UPDATE TG_DealGroupCurrentJoin SET CurrentJoin = CurrentJoin - #quantity#, Version = Version + 1
		WHERE DealGroupID = #dealGroupID#
	
        UPDATE TG_DealCurrentJoin SET Version = Version + 1
        WHERE DealID = #dealID#
    
        UPDATE TG_DealGroupCurrentJoin SET Version = Version + 1
        WHERE DealGroupID = #dealGroupID#
    
		UPDATE TG_DealCurrentJoin SET
		AfterAutoRefundJoin = #afterAutoRefundJoin#,
        Version = Version + 1,
		UpdateTime = CURRENT_TIMESTAMP
		WHERE DealID = #dealId#
	
		UPDATE TG_ReceiptAccount
		SET PrivateAccountID = #privateAccountID#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET ExpireDate = #expireDate#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET MobileNo = #mobileNo#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptAccount
		SET Status = #status#,
		UpdateDate = NOW()
		WHERE ReceiptAccountID = #receiptAccountID#
	
		UPDATE TG_ReceiptVerifyRecord
		SET Status = #status#
		WHERE ReceiptVerifyRecordID = #receiptVerifyRecordID#
	
    	UPDATE TG_RefundRecord r
    	JOIN TG_ReceiptRefundDetail d
    	ON r.refundrecordid = d.refundrecordid
    	set r.status=#status#, r.updatedate= now()
		where d.receiptid=#receiptID#
    
        update TG_EmailPush set
        UserID=#userId#,
        Status=#status#,
        UpdateDate=now(),
        AddMode=#addMode#
        WHERE EmailPushID = #emailPushId#
    
		UPDATE YY_ValentineCode
		SET status = #status#
		WHERE code = #code# AND status != #status#
	
		UPDATE YY_ValentinePhoneCode
		SET code = #code#
		WHERE phone = #phone#
	
		
			UPDATE YY_ShopReceiptStatus
			SET status=#status#
			WHERE shop_id=#shopID# 
	    
	
      UPDATE
        YY_PrivilegeInfo
      SET
        privilege = #privilege#,
        description = #description#,
        updated_time = #updatedTime#
      WHERE
        shop_id = #shopId#
	
        UPDATE TG_ReceiptGroupCode
        SET Status = 0
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId# and Status=1;
    
        UPDATE TG_ReceiptGroupCode
        SET Code = #code#
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId#;
    
	
		UPDATE YY_LotteryChance
			SET lottery_status=#lotteryStatus#
		WHERE
			chance_id = #chanceId#
	
	
	
		UPDATE YY_LotteryChance
			SET lottery_status='20'
		WHERE
			chance_id = #chanceId# AND lottery_status='10'
	
	
		UPDATE
			YY_BillSendRecord
		SET
			billing_config_id = #billingConfigId#,
			send_time = #sendTime#,
			amount = #amount#,
			remark = #remark#,
			remind_times = #remindTimes#,
			update_by = #updateBy#,
			updated_time = #updateTime#
		WHERE
			id = #id#
	
		UPDATE YY_BookingPayDetail
		SET
			amount = #amount#,
			type = #type#,
			biz_id = #bizId#,
			status = #status#,
			receipt_id = #receiptId#
		WHERE
			id = #id# AND shop_id = #shopId# AND operate_id = #operateId#
	
		UPDATE YY_Docket SET
			txt = #docketTxt#,
			update_by = #updateBy#,
			updated_time = #updatedTime#
		WHERE docket_key = #docketKey#
	
		UPDATE YY_DunningConfig SET
		setting = #config,handler=com.dianping.columbus.booking.dunning.dao.DunningConfigHandler#,
		telType=#telType#,
		areaCode=#areaCode#,
		phone=#phone#,
		autoDunningStatus=#autoDunningStatus#,
		update_by = #updateBy#, updated_time = #updateTime#
		WHERE shop_id = #shopID#
	
		UPDATE YY_MockBookingRecord
	    SET
		name = #name#,
		gender = #genderCode#,
		shop_id = #shopID#,
		phone = #phone#,
		num_people = #peopleNumber#,
		position_type = #positionTypeCode#,
		booking_time = #bookingTime#,
		status = #statusCode#,
		consumption = #consumption#,
		updated_time = #updateTime#
		WHERE id = #id#
	
        UPDATE YY_ShopBookableStatus
        SET
        status = #status#,
        update_time = NOW()
        WHERE
        shop_id = #shop_id# AND date = #date#
    
    
		UPDATE YY_ShopBookableStatus
        SET
        status = '',
        update_time = NOW()
        WHERE
        status <> ''
        AND date < #date#
	
    
        UPDATE TG_ReceiptAccountOrder
        SET
        UsedAmount = #usedAmount#,
        RefundedAmount = #refundedAmount#,
        ReceiptBalance = #receiptBalance#,
        LockVersion = LockVersion + 1
        WHERE ReceiptAccountOrderID = #receiptAccountOrderID# AND LockVersion = #lockVersion#
    
        UPDATE TG_ReceiptGroupCodePool
        SET Status = 0
        WHERE
        PoolID = #poolId#
        AND Status = 1 >0
    
	
	
        UPDATE TG_ReceiptResetPool SET Status=2, UpdateTime = NOW() WHERE PoolID = #poolId#;
    
        UPDATE TG_StockLock SET Status = 1, LastDate = NOW() WHERE LockID = #lockID# AND Status = 0;
    
        UPDATE TG_StockLock SET Status = 2, LastDate = NOW() WHERE LockID = #lockID# AND Status = 1;
    
        UPDATE TG_StockLock SET Status = 3, LastDate = NOW() WHERE LockID = #lockID# AND Status = 1;
    
        update TG_EmailPush set
        UserID=#userId#,
        Status=#status#,
        UpdateDate=now(),
        AddMode=#addMode#
        WHERE EmailPushID = #emailPushId#
    
		UPDATE
			YY_ShopPoint
		SET
			shop_point = #point#, update_by = "rs-job-update-shoppoint", updated_time = NOW()
		WHERE
			shop_id = #shopId#
	
		update TG_Receipt set
		status=#targetStatus#,LastDate=now()
		where receiptID=#receiptID# and
		status=#currentStatus#
	
		where receiptID=#receiptId# and status=0
	
		update TG_Receipt set status=1,LastDate=now()
		where orderID=#orderId# and status=0
	
		update TG_Receipt set refundID=#refundId# where
		receiptID=#receiptId#
	
		update TG_Receipt set MobileNo=#mobileNo# where
		receiptID=#receiptId#
	
		update TG_Receipt set serialNumber=#serialNo# where
		receiptID=#receiptId#
	
	
		UPDATE
		YY_CustomRebateScope
		SET
			custom_id = #customID#,
			activity_id = #activityID#,
			activity_type = #activityType#,
			update_by = #updateBy#,
			updated_time = NOW()
		WHERE id = #id#
	
		UPDATE YY_JobExecuteLog SET
			start_time = #startTime#,
			end_time = #endTime#,
			result = #result#,
			result_message = #resultMessage#,
			update_time = NOW(),
			update_by = #updateBy#
		WHERE id = #id#
	
		UPDATE
		YY_CustomRebateItemInfo
		SET
			rebateDesc=#rebateDesc#,
			channel=#channel#,
			display=#display#,
			short_display=#shortDisplay#,
			status=#status#,
			begin_time=#beginTime#,
			end_time=#endTime#,
			update_by=#updateBy#,
			updated_time=NOW(),
			bannerUrl=#bannerUrl#,
			pageUrl=#pageUrl#,
			pageTitle=#pageTitle#,
			pageContent=#pageContent#,
			pageType=#pageType#,
			android_url=#androidUrl#,
			page_picture_url=#pagePictureUrl#,
			begin_used_time=#beginUsedTime#,
			end_used_time=#endUsedTime#,
			web_rule_show=#webRuleShow#
		WHERE id=#id#
	
		UPDATE
		YY_CustomRebateItemInfo
		SET pageUrl = #pageUrl#
		where id = #id#
	
		UPDATE YY_CustomRebateItemInfo 
		SET status = #status#, update_by = #operator#, updated_time= now()
		WHERE id = #id# and status=#fromStatus#
	
		update TG_Receipt set
		status=#currentStatus#
		where receiptID=#receiptId#
	
		where receiptID=#receiptId#
	
		update TG_Receipt set status=0
		where orderID=#orderId#
	
		update TG_Receipt set refundID=null where
		receiptID=#receiptId#
	
		)
	
		)
	
	
		UPDATE YY_RsCallbackEvent
		SET status = 1,
		retry = retry+1,
		updated_time = now()
		WHERE id = #id#
		AND status <> 1
	
	
	
		UPDATE YY_RsCallbackEvent
		SET status = 0,
		updated_time = now()
		WHERE status = 1
		AND schedule_time <= #date#
	
	
	
		UPDATE YY_RsCallbackEvent
 		SET updated_time = now()
 		WHERE id = #id#
 		    AND updated_time <= #date#
 	
	
	
		UPDATE  YY_RsCallbackEvent
		SET status = 0,
			schedule_time = #scheduleTime#,
			updated_time = now()
		WHERE id = #id#
		
	
		UPDATE DP_ReviewBeeUser SET ReviewNum=#reviewNum# WHERE UserID=#userId#
	 
        INSERT INTO DP_ReviewExtendedInfo
            (ReviewID, ReferType, ReferID, ClientType, MerchantFollowCount, AddTime)
        VALUES
            (#extendedInfo.reviewId#, #extendedInfo.referType#, #extendedInfo.referId#, #extendedInfo.clientType#, #extendedInfo.merchantFollowCount#, NOW())
    
        UPDATE DP_ReviewExtendedInfo
        SET MerchantFollowCount = MerchantFollowCount + #delta#
        WHERE ReviewID = #reviewId#
    
        UPDATE DP_ReviewExtendedInfo
        SET ReferType = #extendedInfo.referType#,
          ReferID = #extendedInfo.referId#
        WHERE ReviewID = #extendedInfo.reviewId#
    
		UPDATE
			YY_ShortMessage
		SET
			type = #type#,
			status = #status#,
			record_id = #recordId#,
			phone = #phone#,
			send_time = #sendTime#,
			active_time = #activeTime#,
			reply_time = #replyTime#,
	 		update_by = #updateBy#,
	 		updated_time = #updateTime#
	 	WHERE
	 		id = #id#
	
			UPDATE MC_MemberCardProduct 
			SET EndDate = #endDay#
			WHERE ProductID = #productID# 
	
	        WHERE m.MemberCardID = #memberCardId#
	
			UPDATE MC_MemberCardStopLog st
			SET st.IsActive=0
	        WHERE st.MemberCardID = #memberCardId#
	
	        WHERE st.LogID = #logId# AND st.MemberCardID = #memberCardId# AND st.IsActive = 1
	
			UPDATE MC_MemberCardScoreLog s
			SET s.IsActive = 0
			WHERE s.MemberCardID = #memberCardId# 
	
    	UPDATE DP_ShopDefaultPicConfig
    	SET PicID = #picId#, LastTime = NOW()
		WHERE ShopID = #shopId#
    
    	UPDATE DP_UserAlbum
    	SET PicTotal = #picCount#
		WHERE ShopID = #shopId#
			AND UserID = #userId#
    
    	UPDATE DP_UserAlbum
    	SET DefaultPicUrl = #defaultPicUrl#
		WHERE ShopID = #shopId#
			AND UserID = #userId#
    
		UPDATE SMS_Queue
		SET	status = #status#
		WHERE MsgID = #msgId# AND status = 0
    
	
		UPDATE SMS_Queue
		SET	status = #status#, TryTimes = #tryTimes#, Remark = #remark#,
		SMSChannel = #smsChannel#, UpdateTime = GETDATE()
		WHERE MsgID = #msgId#
	
        update DP_HotelReward set
          Reward =  #hotelReward.reward#,
          RewardDate =  #hotelReward.rewardDate#,
          Status = #hotelReward.status#,
          EventTitle = #hotelReward.eventTitle#,
          UpdateTime = NOW()
        where
        id = #hotelReward.rewardID#
        limit 1;
    
        WHERE FollowNoteID = #followNoteId# And FromUserID=#fromUserId#;
    
        WHERE MainNoteID = #mainNoteId#;
    
			UPDATE WED_ContractNoConfig
			SET Count = Count + 1
			WHERE CityID = #cityId# AND BizTypeEN = #bizTypeEN# AND YearMonth = #yearMonth#
	
		UPDATE DP_ShopModification set ActionType=#shopModification.actionType#, AddDate=NOW(),ClientType=#shopModification.clientType#,
		Content=#shopModification.content#,ContentType=#shopModification.contentType#,ShopID=#shopModification.shopID#,UserID=#shopModification.userID#,
		UserIP=#shopModification.userIP#,UserName=#shopModification.userName#,OldValue=#shopModification.oldValue# 
		WHERE ShopLogID=#shopModification.shopLogID#
	
		DELETE FROM DP_ShopModification WHERE ShopLogID=#shopLogId#
	
		UPDATE ZS_Friend SET
		FriendTag=#friendTag#
		WHERE UserID = #userId# AND
		FriendID = #friendId#
	
		UPDATE
		ZS_Friend SET
		FriendTag=#newfriendTag#
		WHERE UserID = #userId# AND
		FriendTag = #oldfriendTag# 
	
		INSERT INTO SE_DCoinUserGot(UserID, Type, ReferID, AddTime)
		VALUES(#userId#, #type#, #referId#, NOW())
	
		UPDATE SE_Gift g SET g.LeftCount = g.LeftCount - #num#
		WHERE g.GiftId = #giftId#;
	
        UPDATE SE_MaziUserReview
        SET WordID = #wordId#
        WHERE id = #id#
    
		UPDATE DP_SeWords SET WordLeft = WordLeft - #num# WHERE WordId = #wordId#;
	
        UPDATE SE_UserGiftChance
        SET ChanceRemain = ChanceRemain - 1
        WHERE
          UserID = #userId#
    
		INSERT INTO SE_DCoinUserGot(UserID, Type, ReferID, AddTime)
		VALUES(#userId#, #type#, #referId#, NOW())
	
		UPDATE DP_SeWordsUser SET IsSendBadge = 1 WHERE UserId = #userId#;
	
		UPDATE SE_UploadEventRank
		SET DCoinCount=#dCoinCount#
		WHERE CityID = #cityId#
		AND UserID = #userId#
	
		UPDATE DP_ShopModification set ActionType=#shopModification.actionType#, AddDate=NOW(),ClientType=#shopModification.clientType#,
		Content=#shopModification.content#,ContentType=#shopModification.contentType#,ShopID=#shopModification.shopID#,UserID=#shopModification.userID#,
		UserIP=#shopModification.userIP#,UserName=#shopModification.userName#,OldValue=#shopModification.oldValue# 
		WHERE ShopLogID=#shopModification.shopLogID#
	
		DELETE FROM DP_ShopModification WHERE ShopLogID=#shopLogId#
	
		UPDATE 
			DP_ShopCategory 
		SET
			IsMain=#shopCategory.isMain#,
			AddDate=#shopCategory.addDate#
		WHERE
			ShopId=#shopCategory.shopId#
			AND CategoryId=#shopCategory.categoryId#
	
      update DP_HotelConfiguration
      set ConfigValue = #configValue#, UpdateTime = NOW()
      where KeyName = #keyName#
    
		UPDATE
			BC_Notice
		SET 
			Title = #title#,
			Content = #content#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
			BC_Notice
		SET 
		    LastAdminID = #lastAdminID#,
			Status = 0
		WHERE
			ID=#id#
    
	    
		UPDATE DianPingBC.MP_MobileDevice 
		SET
		UUID = #entity.uuid# , 
		OSType = #entity.osTypeId# , 
		OSDetail = #entity.osDetail# , 
		DeviceModel = #entity.deviceModel# , 
		AppVersion = #entity.appVersion# 
		WHERE
		Id = #entity.id# ;
		
	
        update DP_HotelEvent
        set enTitle = #event.enTitle#,
        description = #event.description#,
        startTime = #event.startTime#,
        endTime = #event.endTime#,
        owner = #event.owner#,
        status = #event.status#,
        addTime = #event.addTime#,
        updateTime = NOW()
        where eventID = #event.eventID#
    
        update DP_HotelEvent
        set status = #event.status#,
        updateTime = NOW()
        where eventID = #event.eventID#
    
        update DP_HotelEvent
        set status = 2,
        updateTime = NOW()
        where endTime  < #expireTime# 
        and status != 2
    
        update DP_HotelEvent
        set status = 2,
        updateTime = NOW()
        where enTitle = #enTitle#
        and status != 2
    
				
			WHERE
				VideoID = #shopVideo.videoId#
		
	
    
        update DP_HotelEventLaunch
        set status = #launch.status#
        where adID = #launch.adID#
        and otaID = #launch.otaID#
        and cityID = #launch.cityID#
        and shopID = #launch.shopID#
    
    
        update DP_HotelEventAD
        set title = #ad.title#,
        imgUrl = #ad.imgUrl#,
        landPage = #ad.landPage#,
        status = #ad.status#,
        weight = #ad.weight#,
        startTime = #ad.startTime#,
        endTime = #ad.endTime#,
        updateTime = NOW()
        where eventID = #eventID#
        and srcID = #srcID#
    
        update DP_HotelEventAD
        set updateTime = NOW()
        where eventID = #eventID#
        and srcID = #srcID#
    
        update DP_HotelEventAD
        set
            title = #ad.title#,
            imgUrl = #ad.imgUrl#,
            landPage = #ad.landPage#,
            status = #ad.status#,
            weight = #ad.weight#,
            startTime = #ad.startTime#,
            endTime = #ad.endTime#,
            updateTime = NOW()
        where adID = #ad.adID#
    
      update DP_HotelReviewCat
      SET
          userId = #userId#,
          reviewBody = #reviewBody#,
          reviewStar = #reviewStar#,
          reviewBodyLength = #reviewBodyLength#,
          UpdateTime = NOW()
      where reviewId = #reviewId#
    
			UPDATE DP_BizJournalAccount
			SET TradeStatus = #bizJournalAccount.tradeStatus#, ReceiptID=#bizJournalAccount.receiptId#, UpdateTime=NOW()
			WHERE ID = #bizJournalAccount.id#
	
		
			UPDATE WED_ShopGuanfangBuluo SET Url=#url#,UpdateTime=NOW() WHERE Id=#id#;
		
	
         
			 UPDATE WED_ShopProductTagRecommend
			 SET
                TagNameID  = #tagNameId#
             WHERE
                ID = #id#
		
    
		
			UPDATE WED_SpecialTopic SET
				CityID=#specialTopic.cityId#,
				TopicUrl=#specialTopic.topicUrl#,
				TopicTitle=#specialTopic.topicTitle#,
				ThemeLabel=#specialTopic.themeLabel#,
				ThemeTitle=#specialTopic.themeTitle#,
				TopicPicId=#specialTopic.topicPicId#,
				TopicPicPath=#specialTopic.topicPicPath#,
				ThemePicId=#specialTopic.themePicId#,
				ThemePicPath=#specialTopic.themePicPath#,
				Status=#specialTopic.status#,
				UpdateUser=#specialTopic.updateUser#
			WHERE ID=#specialTopic.id#
		
	   
    	 WHERE Id = #wedHotelBookShopDto.id#
    
		
			UPDATE WED_EventSignUpShop SET
				EventId=#eventSignUpShop.eventId#,
				ModuleId=#eventSignUpShop.moduleId#,
				ShopId=#eventSignUpShop.shopId#,
				ShopName=#eventSignUpShop.shopName#,
				Comments=#eventSignUpShop.comments#,
				AuditStatus=#eventSignUpShop.auditStatus:NUMERIC#,
				SourceType=#eventSignUpShop.sourceType:NUMERIC#,
				Ordinal=#eventSignUpShop.ordinal#,
				Enable=#eventSignUpShop.enable:NUMERIC#,
				UpdateUser=#eventSignUpShop.updateUser#,
				UpdateTime=NOW()
			WHERE ID=#eventSignUpShop.id#
		
	
		UPDATE MC_MemberCardShop s SET s.HotShop = 0 
		WHERE s.HotShop = 1 AND s.CityID = #cityId#;
	
	
		UPDATE MC_MemberCardConsumeAnalysis m
		SET ConsumeAmount = ConsumeAmount + #consumeAmount#,
			ConsumeCount = ConsumeCount + #consumeCount#,
			UpdateTime = #updateTime#
        WHERE ShopID = #shopID# AND ConsumeDate = #consumeDate#
	
		UPDATE DP_OfficialAlbum SET ShopID=#officialAlbum.shopId#, Name= #officialAlbum.name#,PicID=#officialAlbum.picId#,PicPath= #officialAlbum.picPath#,
		PicType= #officialAlbum.picType#,PicCount=#officialAlbum.picCount#
        ,IsMain= #officialAlbum.isMain#,Description= #officialAlbum.description#, AlbumType=#officialAlbum.albumType#, UpdateTime=NOW()
        WHERE ID = #officialAlbum.id#;	
	
		UPDATE DP_OfficialAlbum SET PicCount=#picCount#
        WHERE ID = #albumId#;	
	
    	
    		UPDATE DP_OfficialAlbum
    		SET
    			IsMain = 0,
    			OrderNo = 0,
    			UpdateTime = NOW()
    		WHERE
    			ShopID = #shopId#
    		AND 
    			IsMain = 1
    	
    
    	
    		UPDATE DP_OfficialAlbum
    		SET
    			IsMain = 1,
    			OrderNo = #officialAlbum.orderNo#,
    			UpdateTime = NOW()
    		WHERE
    			ID = #officialAlbum.id#
    		AND
    			ShopID = #officialAlbum.shopId#
    	
    
		UPDATE DP_ShopPic
		SET
			Status=2,StatusCode=#statusCode#,LastTime=NOW()
		WHERE PicID=#picId#
	
		UPDATE DP_ShopPic
		SET
			Status=2,StatusCode=#statusCode#,LastTime=NOW()
		WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic
		SET
			Status=1,StatusCode=#statusCode#,LastTime=NOW()
		WHERE PicID=#picId#
	
		UPDATE DP_ShopPic
		SET
		  Status = #statusValue#
		WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic
		SET
		  ShopID = #shopPic.shopId#, 
		  ShopType = #shopPic.shopType#,
		  ShopGroupID = #shopPic.shopGroupId#,
		  CityID = #shopPic.cityId#,
		  LastTime = NOW()
		WHERE PicID IN ($picIds$)
	
			LastTime=NOW()
		WHERE ShopID=#shopId#
	
     update DP_OTAHotelPrice
     set LowPrice = #hotelPrice.lowPrice#,
         PriceDate = #hotelPrice.priceDate#,
         LastTime = NOW(),
         URL= #hotelPrice.url#,
         StockStatus = #hotelPrice.stockStatus#
     where ShopID = #hotelPrice.shopId#
     and HotelID = #hotelPrice.hotelId#
     and OTAID = #hotelPrice.otaId#
     and TO_DAYS(PriceDate) = TO_DAYS(#hotelPrice.priceDate#)
     and DistributionID = #hotelPrice.distributionId#
    
        UPDATE DP_OTAHotelPrice
        set LowPrice = #lowPrice#,
        StockStatus = #stockStatus#,
        lasttime = NOW(),
        promoinfo = #promoInfo#
        where
        ShopID=#shopId#
        AND
        TO_DAYS(PriceDate)=TO_DAYS(#priceDate#)
        AND
        OTAID = #otaId#
        AND
        DistributionID = #distributionId#
        AND
        RoomID = #roomId#
        AND
        Strategy = #strategy#;
    
	
		
		update  DP_OTAHotelTicket set deviceID=#deviceID# ,addtime=now() where ticketID in (select t.ticketID from ( SELECT ticketID FROM DP_OTAHotelTicket WHERE deviceID="" order by ticketID limit 1) as t)
		
	
		
		update  DP_OTAHotelTicket set deviceID=#deviceID# ,addtime=now() where ticketID = #ticketId#
		
	
        update DP_CruiseOrder
        set PrizeDate = #cruiseOrder.prizeDate#,
        Prized = #cruiseOrder.prized#,
        Used = #cruiseOrder.used#,
        CruiseId = #cruiseOrder.cruiseId#,
        UpdateTime = NOW()
        where OrderId = #cruiseOrder.orderId#
    
        
           update DP_Shop set AvgPrice = #avgPrice#
           where shopId = #shopId#;
        
    
		
			 UPDATE DP_Shop SET ShopTags =#shopTags# WHERE ShopID=#shopId#
    	
	
		
			 UPDATE DP_Shop SET DishTags =#dishTags# WHERE ShopID=#shopId#
    	
	
		UPDATE DP_Shop SET BranchTotal = #branchTotal# WHERE ShopGroupID = #shopGroupId#;
	
		UPDATE DP_Shop SET PhoneNo = #phoneNo#, PhoneNo2= #phoneNo2# WHERE ShopID = #shopId#;
	
		UPDATE DP_Shop SET ShopGroupID = #shopId# WHERE ShopID = #shopId#;
	
		UPDATE DP_Shop SET ShopType=#shop.shopType#, ShopName= #shop.shopName#,BranchName= #shop.branchName#,Address= #shop.address#,CrossRoad=#shop.crossRoad#
        ,PhoneNo= #shop.phoneNo#,PhoneNo2= #shop.phoneNo2#,AltName=#shop.altName#, District= #shop.district#,LastIP	= #shop.lastIp#,LastUser=#shop.lastUser#,LastUserName= #shop.lastUserName#,LastDate=NOW(), HasStaticMap = 0,
        BusinessHours = #shop.businessHours#, PublicTransit = #shop.publicTransit#, PriceInfo = #shop.priceInfo#
        WHERE ShopID = #shop.shopId#;	
	
		UPDATE DP_Shop SET GLat=#lat#,GLng=#lng#,HasStaticMap=0 WHERE ShopID = #shopId#;
	
		
			 UPDATE DP_Shop SET WishTotal = WishTotal + #increament# WHERE ShopID=#shopId#
    	
	
		
			 UPDATE DP_Shop SET Power =#power# WHERE ShopID=#shopId#
    	
	
		WHERE FollowNoteID = #followNoteId# And FromUserID=#fromUserId#;
	
		WHERE MainNoteID = #mainNoteId#;
	
    	
    		UPDATE DP_ShopDishTag SET Star=#shopDishTag.star#,Price=#shopDishTag.price#,LastIP=#shopDishTag.lastIP# 
    		WHERE ShopID=#shopDishTag.shopID# AND UserID=#shopDishTag.userID# AND DishTagName=#shopDishTag.dishTagName# 
    		ORDER BY CookieUserID DESC limit 1
    	
	
		WHERE ListID=#listId# AND ShopID=#shopId#
	
		WHERE ListID=#listId#
	
		WHERE ListID=#listId#
	
		WHERE FollowNoteID = #followNoteId# And FromUserID=#fromUserId#;
	
		WHERE MainNoteID = #mainNoteId#;
	
		UPDATE DP_SeWordsUser SET IsSendBadge = 1 WHERE UserId = #userId#;
	
		UPDATE DP_ShopModificationLog set ActionType=#shopModificationLog.actionType#, AddDate=#shopModificationLog.addDate#,ClientType=#shopModificationLog.clientType#,
		Content=#shopModificationLog.content#,ContentType=#shopModificationLog.contentType#,
		ShopID=#shopModificationLog.shopID#,UserID=#shopModificationLog.userID#,UserIP=#shopModificationLog.userIP#,UserName=#shopModificationLog.userName#
		WHERE ShopLogID=#shopModificationLog.shopLogID#
	
        UPDATE
        DP_ShopPhoneRecord
        SET UpdateTime = NOW(),
        BookingFlag = #shopPhoneRecord.bookingFlag#
        WHERE
        ID = #shopPhoneRecord.id#
    
        UPDATE SE_MaziUserReview
        SET WordID = #wordId#
        WHERE id = #id#
    
		
            UPDATE SMS_Status
            SET status = #status#, reachTime = #reachTime#, TimeDelay = TIMESTAMPDIFF(second, AddTime, #reachTime#), Remark = #remark#
            WHERE SmsSerialID = #smsSerialID# AND SMSChannel = #smsChannel#
        
	
		UPDATE 
			DP_ShopPOI 
		SET
			Address=#shopPOI.address#,
			Lng=#shopPOI.lng#,
			Lat=#shopPOI.lat#,
			GLng=#shopPOI.gLng#,
			GLat=#shopPOI.gLat#,
			MLng=#shopPOI.mLng#,
			MLat=#shopPOI.mLat#,
			Power=#shopPOI.power#,
			Status=#shopPOI.status#
		WHERE
			ShopId=#shopPOI.shopId#
	
        


        UPDATE MSD_AlbumTagType
        SET `Status` = #status#
        WHERE TagType = #tagType# AND `Status` >= 0


        
    
        

        UPDATE MSD_AlbumTag
        SET `Status` = #status#
        WHERE TagType = #tagType# AND `Status` >= 0

        
    
      update DP_OTAHotelPrice
      set LowPrice = #price.lowPrice#, LastTime = NOW()
      where OTAID = #price.otaId# and PriceDate = #priceDateStr# and ShopID = #price.shopId# and RoomID = #price.roomId#
    
		UPDATE BC_ResetPasswordRequest
		SET
		ShopAccount=#entity.shopAccount#,
		ShopAccountId=#entity.shopAccountId#,
		MobileNo=#entity.mobileNo#,
		LastRequestTime=#entity.lastRequestTime#,
		IsReset=#entity.isReset#,
		LastResetTime=#entity.lastResetTime#,
		LastUpdatorId=#entity.lastUpdatorId#
		WHERE
		ResetPasswordRequestId=#entity.id#
	
		UPDATE BC_Tip
		SET
		Document=#entity.document#,
		Url=#entity.url#,
		Status=#entity.status#,
		LastUpdateTime=#entity.lastUpdateTime#,
		LastUpdatorId=#entity.lastUpdatorId#,
		CreateTime=#entity.createTime#
		WHERE
		TipId=#entity.id#
	
		UPDATE DianPingBC.MP_Device_Account_Assn 
		SET
		IsLogin = #entity.isLogin#
		, LastLoginTime = #entity.lastLoginTime#
		, LastLogoutTime = #entity.lastLogoutTime#
		WHERE
		Id = #entity.id# ;
	
		UPDATE DP_Review SET  PicTotal = PicTotal + #picNumber# WHERE  ShopID=#shopId# AND  UserID=#userId#
    
        UPDATE WED_EnterpriseQQ
        SET
          SendStatus=#qq.sendStatus#,
          SendErrorCode=#qq.sendErrorCode#,
          SendErrorMsg=#qq.sendErrorMsg#,
          FKFNameAccount=#qq.FKFNameAccount#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          PullStatus=#qq.pullStatus#,
          PullErrorCode=#qq.pullErrorCode#,
          PullErrorMsg=#qq.pullErrorMsg#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          FSuperPasswd=#qq.FSuperPasswd#,
          FKFExtCount=#qq.FKFExtCount#,
          FKFExpireTime=#qq.FKFExpireTime#,
          FKFExt1=#qq.FKFExt1#,
          FKFExt1Passwd=#qq.FKFExt1Passwd#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          FLicenceNumber=#qq.FLicenceNumber#,
          FLicenceNumberEndTime=#qq.FLicenceNumberEndTime#,FLegalPersonName=#qq.FLegalPersonName#,
        FLegalPersonLicence=#qq.FLegalPersonLicence#,FTaxNumber=#qq.FTaxNumber#,
        UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          OpenStatus=#qq.openStatus#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          OpenStatus=#qq.openStatus#,
          UpdateTime=NOW()
        WHERE
          Id=#qq.id#
    
        
          UPDATE WED_ShopPermissionList
          SET
              StartDate = #startDate#,
              ExpirationDate = #expirationDate#,
              LastUser = #lastUser#,
              LastTime = NOW()
          WHERE
              ID = #id#
        
    
		
			UPDATE WED_EventPoi SET
				EventId=#weddingEventPoi.eventId#,
				TemplateID=#weddingEventPoi.templateId#,
				PoiType=#weddingEventPoi.poiType:NUMERIC#,
				PoiSource=#weddingEventPoi.poiSource:NUMERIC#,
				OrderNo=#weddingEventPoi.orderNo#,
				Enable=#weddingEventPoi.enable:NUMERIC#,
				UpdateDate=NOW()
			WHERE ID=#weddingEventPoi.id#
		
	
	
	    UPDATE WED_HotelBookUser
	    SET   UpdateTime = now(),
			Status = #destStatus#
		WHERE UserMobileNo = #userMobileNo#
	
		
			UPDATE DP_Shop_Contact_Material
			SET MaterialPath = #shopContactMaterialInfo.path#,
			MaterialType= #shopContactMaterialInfo.type#,
			UpdateTime= NOW()
			WHERE ShopContactID=#contactId#
		
	
			UPDATE MC_MemberCardNewRegister nr
			SET nr.UserID = #nUserId#  
			WHERE nr.UUID = #uuId# 
	
			UPDATE MC_MemberCardUserActionCoordinateLog ac
			SET ac.UserID = #nUserId#  
			WHERE ac.UUID = #uuId# 
	
			UPDATE MC_MemberCardUserActionLog ac
			SET ac.UserID = #nUserId#  
			WHERE ac.UUID = #uuId# 
	
		UPDATE DP_PicTagIndex SET OrderNo = #orderNo#
		WHERE PicID = #picId#
	
		UPDATE SMS_Queue
		SET	status = #status#
		WHERE MsgID = #msgId# AND status = 0
    
	
		UPDATE SMS_Queue
		SET	status = #status#, TryTimes = #tryTimes#, Remark = #remark#,
		SMSChannel = #smsChannel#, UpdateTime = NOW()
		WHERE MsgID = #msgId#
	
        update
        DP_HotelAdContent
        set AdContent = #adContent#
        where AdKey = #adKey#
        and
        EventType = #eventType#
    
      update DP_HotelReviewEvent2
      SET
          Score = #bean.score#,
          ToalScore = #bean.toalScore#,
          Src = #bean.src#,
          Name = #bean.name#,
          Tel = #bean.tel#,
          Address = #bean.address#,
          UpdateTime = NOW()
      where UserID = #bean.userId#
    
      update DP_Hotel314
      SET
      otaid = #bean.otaId#,
      orderid = #bean.orderId#,
      tel = #bean.tel#
      where
      uid = #bean.uid#
      AND
      source = #bean.source#
    
        update DP_HotelPVWeight set
            Status = 0
        WHERE
        
            (
              shopID = #shopID#
            AND
              statDate < #statDate#
            AND
              Status > 0
            )

        
    
        update DP_HotelPVWeight set
        Status = 0
        WHERE
        
            (
              statDate < #statDate#
            AND
              Status > 0
            )

        
    
        update DP_HotelShopRank set
          Rank =  #hotelRank.rank#,
          CityId = #hotelRank.cityId#,
           CatId = #hotelRank.catId#,
          UpdateTime = NOW()
        where
        ShopId = #hotelRank.shopId#
    
        update
        DP_HotelValentineLOMO
        set userId = #userId#,
        orderId=#orderId#,
        otaid=#otaId#
        where ticketId = #ticketId#
    
        UPDATE DP_VoteRank
        SET ViewCount = ViewCount + #viewCount#
        WHERE id = #id#
    
        WHERE ID=#voteRankId#
    
    	
    		update 
    			WED_HotelHallSchedule
    		set
    			Status=#status#
    		where
    			Id=#id#
    	
    
		UPDATE DP_ShopModificationLog set ActionType=#shopModificationLog.actionType#, AddDate=#shopModificationLog.addDate#,ClientType=#shopModificationLog.clientType#,
		Content=#shopModificationLog.content#,ContentType=#shopModificationLog.contentType#,
		ShopID=#shopModificationLog.shopID#,UserID=#shopModificationLog.userID#,UserIP=#shopModificationLog.userIP#,UserName=#shopModificationLog.userName#
		WHERE ShopLogID=#shopModificationLog.shopLogID#
	
		UPDATE ZS_Friend SET
		FriendTag=#friendTag#
		WHERE UserID = #userId# AND
		FriendID = #friendId#
	
		UPDATE
		ZS_Friend SET
		FriendTag=#newfriendTag#
		WHERE UserID = #userId# AND
		FriendTag = #oldfriendTag# 
	
		UPDATE SE_UploadEvent
		SET IsDGot = #isDGet#, Status = 2
		WHERE ShopID = #shopId#
		AND   UserID = #userId#
		AND   PicID  = #picId#
	
		UPDATE SE_Gift g SET g.LeftCount = g.LeftCount - #num#
		WHERE g.GiftId = #giftId#;
	
		UPDATE SE_ShopList
		SET Status = #status#
		WHERE ShopID = #shopId#
	
		UPDATE SE_ShopList
		SET PicCount = #picCount#
		WHERE ShopID = #shopId#
	
        UPDATE SE_UserGiftChance
        SET ChanceRemain = ChanceRemain - 1
        WHERE
          UserID = #userId#
    
        
          UPDATE WED_WeddingHotelExtraInfo
          SET
              ReferShopID = #weddingHotelExtraInfo.referShopId#,
              ReferFoodShopID = #weddingHotelExtraInfo.referFoodShopId#,
              CooperateType = #weddingHotelExtraInfo.cooperateType#,
              SalesInfo = #weddingHotelExtraInfo.salesInfo#,
              HallCapacityMin = #weddingHotelExtraInfo.hallCapacityMin#,
              HallCapacityMax = #weddingHotelExtraInfo.hallCapacityMax#,
              HallCount = #weddingHotelExtraInfo.hallCount#,
              HallDesc = #weddingHotelExtraInfo.hallDesc#,
              WeddingHotelDesc = #weddingHotelExtraInfo.weddingHotelDesc#,
              Stage = #weddingHotelExtraInfo.stage#,
              Trans = #weddingHotelExtraInfo.trans#,
              CommonEquipment = #weddingHotelExtraInfo.commonEquipment#,
              EnvPicUserId = #weddingHotelExtraInfo.envPicUserId#,
              Address = #weddingHotelExtraInfo.address#,
              Metro = #weddingHotelExtraInfo.metro#,
              Bus = #weddingHotelExtraInfo.bus#,
              Park = #weddingHotelExtraInfo.park#,
              ShopMobile=#weddingHotelExtraInfo.shopMobile#,
              CooperateLevel=#weddingHotelExtraInfo.cooperateLevel#,
              PlaceType=#weddingHotelExtraInfo.placeType#,
              UpdateTime = NOW(),
              Percentage=#weddingHotelExtraInfo.percentage#
          WHERE
              ShopID = #weddingHotelExtraInfo.shopId#
        
    
		UPDATE 
			DP_CategoryList 
		SET 
			CategoryName=#categoryList.categoryName#, 
			CategorySearchName=#categoryList.categorySearchName#,
			CategoryAltName=#categoryList.categoryAltName#, 
			CategoryType=#categoryList.categoryType#,
			CategoryLevelType=#categoryList.categoryLevelType#, 
			UpdateDate=#categoryList.updateDate#,
			CategoryURLName=#categoryList.categoryURLName#,
			CategoryOrderId=#categoryList.categoryOrderId#
		WHERE
			CategoryId=#categoryList.categoryId#
			AND CityId=#categoryList.cityId#
	
		UPDATE 
			DP_CategoryList 
		SET 
			CategoryLevelType=#categoryLevelType#
		WHERE
			CategoryId=#categoryId#
			AND CityId=#cityId#
	
		WHERE
			RegionID = #regionListData.regionId#
	
		Update DP_ShopModificationLog
		set EditorId=#shopModificationLog.editorId#,EditorName=#shopModificationLog.editorName#,ConfirmDate=#shopModificationLog.confirmDate#,ConfirmType=#shopModificationLog.confirmType#
		Where ShopLogId=#shopModificationLog.shopLogId#
	
		Update DP_ShopModificationLog
		set EditorId=#shopModificationLog.editorId#,EditorName=#shopModificationLog.editorName#,ConfirmDate=#shopModificationLog.confirmDate#,ConfirmType=#shopModificationLog.confirmType#
		Where ShopLogId=#shopModificationLog.shopLogId#
	
		UPDATE
			BC_Questionnaire
		SET 
			Title = #title#,
			URL = #url#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
		BC_ShopAccount
		SET ShopAccount = #shopAccountId#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE BC_ShopAccount
		SET Password = #password#,UpdateTime=#updateTime#,PasswordVersion=PasswordVersion+1
		Where
		ShopAccountId=#shopAccountId#
    
    
    			and sa.AccountType=#accountType# 
    			and sa.ShopAccountId = sas.ShopAccountId 
    		join BC_ShopAccountModule sam on sam.Module=#module# 
    			and sa.ShopAccountId=sam.ShopAccountId
    	) t 
    	set sa.Password= #password#, sa.UpdateTime=#updateTime#,PasswordVersion=PasswordVersion+1
    	where sa.`ShopAccountId` =t.`ShopAccountId`;
    
		UPDATE BC_ShopAccount
		SET ContactMobileNO = #mobile#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE
		BC_ShopAccount
		SET Status = -1,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE BC_ShopAccount
		SET ContactName = #contactName#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE
		BC_ShopAccount_Customer
		SET CustomerName = #customerName#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE SC_Feed SET Status=#status# WHERE FeedTypeID=#feedTypeId# AND MainID=#mainId#
	
		UPDATE SC_Feed SET Status=#status# WHERE FeedTypeID=#feedTypeId# AND MainID=#mainId# AND UserID = #userId#
	
		UPDATE SC_Feed SET Status=#status# WHERE FeedID=#feedId#
	
		WHERE Id = #updateEntity.id#
	
        
          UPDATE WED_WeddingHotelExtraInfo
          SET
              ReferShopID = #weddingHotelExtraInfo.referShopId#,
              ReferFoodShopID = #weddingHotelExtraInfo.referFoodShopId#,
              CooperateType = #weddingHotelExtraInfo.cooperateType#,
              SalesInfo = #weddingHotelExtraInfo.salesInfo#,
              HallCapacityMin = #weddingHotelExtraInfo.hallCapacityMin#,
              HallCapacityMax = #weddingHotelExtraInfo.hallCapacityMax#,
              HallCount = #weddingHotelExtraInfo.hallCount#,
              HallDesc = #weddingHotelExtraInfo.hallDesc#,
              WeddingHotelDesc = #weddingHotelExtraInfo.weddingHotelDesc#,
              Stage = #weddingHotelExtraInfo.stage#,
              Trans = #weddingHotelExtraInfo.trans#,
              CommonEquipment = #weddingHotelExtraInfo.commonEquipment#,
              EnvPicUserId = #weddingHotelExtraInfo.envPicUserId#,
              Address = #weddingHotelExtraInfo.address#,
              Metro = #weddingHotelExtraInfo.metro#,
              Bus = #weddingHotelExtraInfo.bus#,
              Park = #weddingHotelExtraInfo.park#,
              ShopMobile=#weddingHotelExtraInfo.shopMobile#,
              CooperateLevel=#weddingHotelExtraInfo.cooperateLevel#,
              PlaceType=#weddingHotelExtraInfo.placeType#,
              UpdateTime = NOW(),
              Percentage=#weddingHotelExtraInfo.percentage#,
              EnviromentPicOrAlbum=#weddingHotelExtraInfo.enviromentPicOrAlbum#,
              MenuPicOrAlbum=#weddingHotelExtraInfo.menuPicOrAlbum#
          WHERE
              ShopID = #weddingHotelExtraInfo.shopId#
        
    
    	UPDATE WED_WeddingHotelMenu 
		SET
		Name = #menu.name#, 
		Price = #menu.price#, 
		ServicePrice = #menu.servicePrice#, 
		OpenWinePrice = #menu.openWinePrice#,
		EnterPrice = #menu.enterPrice#, 
		SetMealA = #menu.setMealA#, 
		SetMealB = #menu.setMealB#,
		ExtraService = #menu.extraService#,
		UpdateTime = NOW(), 
		PriceUnit = #menu.priceUnit#, 
		Document = #menu.document#
		WHERE Id = #menu.id#;
    
		
			UPDATE WED_EventTemplate SET
				EventId = #eventId#,
				TemplateType=#templateType:NUMERIC#,
				EventHeadPic=#eventHeadPic#,
				EventBottomPic=#eventBottomPic#,
				ButtonText=#buttonText#,
				PageTitle=#pageTitle#,
				KeyWord=#keyWord#,
				PageDescription=#pageDescription#,
				AboveAdPic=#aboveAdPic#,
				AboveAdUrl=#aboveAdUrl#,
				BelowAdPic=#belowAdPic#,
				BelowAdUrl=#belowAdUrl#,
				RightAdPic=#rightAdPic#,
				RightAdUrl=#rightAdUrl#,
				ExtendColor=#extendColor#,
				NaviColor=#naviColor#,
				NaviCharColor=#naviCharColor#,
				BackgroundColor=#backgroundColor#,
				ModuleTitleColor=#moduleTitleColor#,
				ButtonColor=#buttonColor#,
				UpdateUser=#updateUser#,
				UpdateTime=NOW()
			WHERE Id=#id#
		
	
		
			UPDATE WED_EventModule SET
				Name=#eventTemplateModule.name#,
				TemplateId=#eventTemplateModule.templateId#,
				Ordinal=#eventTemplateModule.ordinal#,
				Enable=#eventTemplateModule.enable:NUMERIC#,
				RelatedShopIds=#eventTemplateModule.relatedShopIds#,
				UpdateUser=#eventTemplateModule.updateUser#,
				UpdateTime=NOW()
			WHERE ID=#eventTemplateModule.id#
		
	
        WHERE
        ID = #bookUser.id#
        AND
        ShopID = #bookUser.shopId#
    
    
		
			UPDATE WED_Event
			SET
				EventTitle = #weddingEvent.eventTitle#,
				CityID = #weddingEvent.cityId#,
				EventContent = #weddingEvent.eventContent#,
				EventType = #weddingEvent.eventType:NUMERIC#,
				EventStatus = #weddingEvent.eventStatus:NUMERIC#,
				TemplateID = #weddingEvent.templateId#,
				StartDate = #weddingEvent.startDate#,
				EndDate = #weddingEvent.endDate#,
				ExpirationDate = #weddingEvent.expirationDate#,
				UpdateDate = #weddingEvent.updateDate#,
				UpdateUser = #weddingEvent.updateUser#
			WHERE
				EventID = #weddingEvent.eventId#
		
	
		UPDATE
			MC_MemberCardFeed
		SET
			PushTime = NOW()
		WHERE
			FeedID = #feedId#
	
		UPDATE
			MC_MemberCardFeed
		SET
			Status = 4
		WHERE
			FeedID = #feedId#
	
			LastIP = #shopPic.lastIp#,
			LastTime = Now()
		WHERE PicID = #shopPic.picId#
    
    	UPDATE DP_ShopPic 
    		SET OrderNo = #orderNo#,
			LastTime = Now()
		WHERE PicID = #picId#
    
		UPDATE DP_ShopPic 
		SET IsTop = #shopPic.isTopValue#,
			LastIP = #shopPic.lastIp#,
			LastTime = Now()
		WHERE PicID = #shopPic.picId#
    
		UPDATE DP_ShopPic
		SET 
			IsTop = #shopPic.isTopValue#,
			OrderNo = #shopPic.orderNo#,
			LastIP = #shopPic.lastIp#,
			LastTime = Now()
		WHERE 
			PicID = #shopPic.picId#
		AND
			ShopID = #shopPic.shopId#
    
		UPDATE DP_ShopPic
		SET IsTop = 0,
			OrderNo =0
		WHERE 
			ShopID = #shopId#
		AND
			IsTop = 1
		AND 
			PicType = 2
    
		UPDATE DP_ShopPic 
		SET Status = 2,
			LastIP = #lastIp#, 
			StatusCode = #statusCode#,
			LastTime = Now() 
		WHERE DP_ShopPic.PicID = #picId#;
	
        UPDATE DP_ShopPic
        SET Rank = #rank#, LastTime = Now()
        WHERE PicID = #picId#
    
		UPDATE SMS_SendNumber
		SET CountValue = CountValue + #increment#, UpdateTime = NOW()
		WHERE SMSChannel = #smsChannel# AND CountKey = #countKey#
    
        UPDATE DP_HotelExchangeRate
        set Price = #price#,
        UpdateTime = NOW()
        where
        FromCurrency=#fromCurrency#
        AND
        ToCurrency =#toCurrency#
    
        update DP_HotelActivity set
        userinfo = #hotelActivity.userInfo#
        , score = #hotelActivity.score#
        , status = #hotelActivity.status#
        , detail = #hotelActivity.detail#
        , addtime = #hotelActivity.addTime#
        , updatetime = NOW()
        where
        userid = #hotelActivity.userId#
        and
        dpid = #hotelActivity.dpId#
        and
        activity = #hotelActivity.activity#
    
        
        update DP_OTAHotelEvent
        set status = 0,
        LastTime = NOW()
        where eventType in ($eventTypes$)
        and endTime < #expireTime#
        and status = 1
        
    
        update DP_OTAHotelEvent
        set status = 0,
        LastTime = NOW()
        where eventType in ($eventTypes$)
        and shopId = #shopId#
        and status = 1
    
        update DP_OTAHotelEvent
        set status = 1,
        LastTime = NOW()
        where eventType in ($eventTypes$)
        and shopId = #shopId#
        and status = 0
    
        UPDATE DP_VoteRankShop
        SET `VoteCount` = VoteCount+1
		WHERE `VoteRankID` = #voteRankId# AND `ShopID` = #shopId#
    
        UPDATE DP_VoteRankShop
        SET `LastRank` = #lastRank#
		WHERE `VoteRankID` = #voteRankId# AND `ShopID` = #shopId#
    
        UPDATE DP_VoteRankShop
        SET `Reason` = #reason#
		WHERE `VoteRankID` = #voteRankId# AND `ShopID` = #shopId#
    
        UPDATE WED_Authority
        SET
          AuthorityDesc=#auth.authorityDesc#,
          GroupName=#auth.groupName#,
          ActionName=#auth.actionName#,
          UpdateUser=#auth.updateUser#,
          UpdateTime=NOW()
        WHERE
          AuthorityID=#auth.authorityId#
    
		UPDATE
		DP_ShopPhone
		SET UpdateTime = NOW(),
		UpdateUser = #shopPhone.updateUser#,
		ShopName = #shopPhone.shopName#,
		TelNum400 = #shopPhone.telNum400#,
		Phone1 = #shopPhone.phone1#,
		Phone2 = #shopPhone.phone2#,
		Phone3 = #shopPhone.phone3#,
		PhoneType = #shopPhone.phoneType#
		WHERE
		ID = #shopPhone.id#
    
		Update 
			DP_DishMenu 
		SET
			TagCount = TagCount +1
		WHERE
			 ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update 
			DP_DishMenu 
		SET
			TagCount = TagCount -1
		WHERE
			 ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update 
			DP_DishMenu 
		SET
			PriceMap = #priceMap#, 
			FinalPrice = #finalPrice#,
			PriceCount = #priceCount#
		WHERE
			ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update 
			DP_DishMenu 
		SET
			OfficialPrice = #officialPrice#
		WHERE
			ShopID=#shopId# AND DishTagName=#dishTagName#
	
		
			 UPDATE DP_ShopAutoAudit SET Status =#status# WHERE ShopID=#shopId#
    	
	
        UPDATE SE_GiftUserGot
        SET Status = #status#
        WHERE  GiftUserGotId = #giftUserGotId#
    
		UPDATE DP_SeWordsUserGot SET STATUS=2 WHERE UserId=#userId# AND WordId=#wordId# AND STATUS=1 LIMIT 1
	
		UPDATE SE_ShopList
		SET Status = #status#
		WHERE ShopID = #shopId#
	
		UPDATE SE_ShopList
		SET PicCount = #picCount#
		WHERE ShopID = #shopId#
	
		UPDATE SE_UploadEventRank
		SET DCoinCount=#dCoinCount#
		WHERE CityID = #cityId#
		AND UserID = #userId#
	
		UPDATE DP_SeWordsUserGot SET STATUS=2 WHERE UserId=#userId# AND WordId=#wordId# AND STATUS=1 LIMIT 1
	
		Update 
			DP_DishMenu 
		SET
			TagCount = TagCount +1
		WHERE
			 ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update 
			DP_DishMenu 
		SET
			TagCount = TagCount -1
		WHERE
			 ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update 
			DP_DishMenu 
		SET
			PriceMap = #priceMap#, 
			FinalPrice = #finalPrice#,
			PriceCount = #priceCount#
		WHERE
			ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update 
			DP_DishMenu 
		SET
			OfficialPrice = #officialPrice#
		WHERE
			ShopID=#shopId# AND DishTagName=#dishTagName#
	
		Update
			DP_DishMenu
		SET
			OfficialPicID = #officialPicID#,
			OfficialPicURL = #officialPicURL#
		WHERE
			ShopID=#shopId# AND DishTagName=#dishTagName#
	
		
			 UPDATE DP_ShopAutoAudit SET Status =#status# WHERE ShopID=#shopId#
    	
	
		UPDATE  DP_Info	SET POWER = 0, LastTime = NOW()
		WHERE  ShopID = #shopId#

	UPDATE DP_Info A,DP_Shop B SET A.ShopFullName=#shopFullName#
	WHERE A.ShopId=B.ShopId And B.ShopGroupId=#shopGroupId# And A.IsGroupLevel=#isGroupLevel#

		

			INSERT INTO BC_ShopAccount_Privilege
			(`ShopAccountId`,`Privileges`,`AddDate`,`UpdateDate`) VALUES (#shopAccountId#,#privileges#,now(),now());

        
	
		

			UPDATE BC_ShopAccount_Privilege 
			SET Privileges=#privileges#,UpdateDate=now()
			WHERE ShopAccountId = #shopAccountId#;

        
	
	    
		UPDATE DianPingBC.MP_MessageToken 
		SET
		IsValid = #entity.isValid#
		WHERE
		Id = #entity.id# ;
		
	
		UPDATE
			DP_Shop
		SET
			ShopGroupId=#shopId#
		WHERE
			ShopId=#shopId#
	
		UPDATE
			DP_Shop
		SET
			BranchName=#branchName#
		WHERE
			ShopGroupId=#shopGroupId#
	
		UPDATE
			DP_Shop
		SET
			CanSendSms=1
		WHERE
			ShopId=#shopId#
	
		UPDATE
			DP_Shop
		SET
			BranchTotal =#branchTotal#
		WHERE
			ShopGroupId=#shopGroupId#
	
		UPDATE
			DP_Shop
		SET
			 PicTotal = PicTotal + #picTotal#
		WHERE
			ShopId=#shopId#
	
		UPDATE
			DP_Shop
		SET
			 GLat=#gLat#,GLng= #gLng#
		WHERE
			ShopId=#shopId#
	
        UPDATE DP_Shop
        SET Power=#power#
        WHERE ShopId=#shopId#
    
	
		UPDATE
			DP_Shop
		SET
			ShopType=#shop.shopType#,
			ShopName=#shop.shopName#,
			BranchName=#shop.branchName#,
			Address=#shop.address#,
			CrossRoad=#shop.crossRoad#,
			PhoneNo=#shop.phoneNo#,
			PhoneNo2=#shop.phoneNo2#,
			CityId=#shop.cityId#,
			Power=#shop.power#,
			ShopGroupId=#shop.shopGroupId#,
			GroupFlag=#shop.groupFlag#,
			AltName=#shop.altName#,
			SearchName=#shop.searchName#,
			Hits=#shop.hits#,
			WeeklyHits=#shop.weeklyHits#,
			District=#shop.district#,
			AddDate=#shop.addDate#,
			LastDate=#shop.lastDate#,
			AddUser=#shop.addUser#,
			LastUser=#shop.lastUser#,
			SearchKeyWord=#shop.searchKeyWord#,
			GLat=#shop.gLat#,
			GLng=#shop.gLng#,
			HasStaticMap=#shop.hasStaticMap#,
			BusinessHours=#shop.businessHours#,
			PublicTransit=#shop.publicTransit#,
			OldName = #shop.oldName#,
			LastIP =#shop.lastIP#,
			CanSendSms=1 ,
			LastUserName=#shop.lastUserName#
		WHERE
			ShopId=#shop.shopId#
	
	Update HT_ShopStatus
	Set HasRemark=1
	WHERE ShopId=#shopId#
	
        update DP_HotelRoomTag
        set
        RoomTag = #roomTag#,
        UpdateTime = NOW()
        where ShopID = #shopId#
    
        UPDATE WED_AdProduct
        
        SET ProductID = #adProduct.productId#
        
        WHERE ID = #adProduct.id#
        LIMIT 1
    
        UPDATE WED_ApplyLog
        SET
          ApplyType=#applyLog.applyType#,
          ApplyUserID=#applyLog.applyUserId#,
          ApplyTime=#applyLog.applyTime#,
          LastIP=#applyLog.lastIp#,
          CityID=#applyLog.cityId#,
          ShopName=#applyLog.shopName#,
          CategoryID=#applyLog.categoryId#,
          Status=#applyLog.status#,
          VerifyUserId=#applyLog.verifyUserId#,
          VerifyTime=#applyLog.verifyTime#
         WHERE
          ShopID=#applyLog.shopId#
    
        UPDATE WED_MobileBanner
        
        SET
            UpdateTime = NOW(),
            CityId = #mobileBanner.cityId#,
            PicUrl = #mobileBanner.picUrl#,
            LinkUrl = #mobileBanner.linkUrl#,
            Type = #mobileBanner.type#,
            Document = #mobileBanner.document#,
            ShopType = #mobileBanner.shopType#
        
        WHERE ID = #mobileBanner.id#
        LIMIT 1
    
        UPDATE WED_NewPhone
        SET 
          AreaCode='',
          Phone1='',
          Phone2='',
          Phone3='',
          PhoneStatus=1,
          UpdateTime=NOW()
        WHERE
          ID=#id#
    
        UPDATE WED_NewPhone
        SET
          AreaCode=#phone.areaCode#,
          Phone1=#phone.phone1#,
          Phone2=#phone.phone2#,
          Phone3=#phone.phone3#,
          PhoneStatus=2,
          UpdateTime=NOW()
        WHERE
          ID=#phone.id#
    
        UPDATE WED_NewPhone
        SET
          PhoneStatus=5,
          UpdateTime=NOW()
        WHERE
          ID=#phoneId#
    
		UPDATE WED_NewPhone
		SET PhoneStatus=#status#
		WHERE Id=#id#;
	
        UPDATE WED_NewPhone
        SET PhoneStatus=1,UpdateTime=NOW()
        WHERE PhoneStatus=4
    
        
          UPDATE WED_WeddingHotelHall
          SET
            ShopID = #weddingHotelHall.shopId#,
            PicUserId = #weddingHotelHall.picUserId#,
            Name = #weddingHotelHall.name#,
            PlanPicID = #weddingHotelHall.planPicId#,
            Capacity = #weddingHotelHall.capacity#,
            Height = #weddingHotelHall.height#,
            PostCount = #weddingHotelHall.postCount#,
            Stage = #weddingHotelHall.stage#,
            Money = #weddingHotelHall.money#,
            UpdateTime = NOW(),
            CapacityMin = #weddingHotelHall.capacityMin#,
            CapacityMax = #weddingHotelHall.capacityMax#,
            Remark = #weddingHotelHall.remark#,
            HallPicOrAlbum = #weddingHotelHall.hallPicOrAlbum#
          WHERE
            ID = #weddingHotelHall.id#
        
    
		
			UPDATE DP_BookingShop
			SET
				PhoneNo = #bookingShop.phoneNo#,
				Preferential = #bookingShop.preferential#,
				ToShopPresent = #bookingShop.toShopPresent#,
				OrderPresent = #bookingShop.orderPresent#,
				Experience = #bookingShop.experience#,
				ServiceGuarantee = #bookingShop.serviceGuarantee#,
				PreferentialFeatures = #bookingShop.preferentialFeatures#,
				PreferentialLimitDate = #bookingShop.preferentialLimitDate#,
                PreferentialLimitContent = #bookingShop.preferentialLimitContent#,
                ProductID = #bookingShop.productID#,
				InChargePersonName = #bookingShop.inChargePersonName#,
				InChargePersonQQ = #bookingShop.inChargePersonQQ#,
				TemplateID = #bookingShop.templateId#,
				UpdateDate = NOW(),
				AuditStatus = #bookingShop.auditStatus#
			WHERE
				ShopID = #bookingShop.shopId#
		
	
        update DP_HotelOrder set
        Status = #hotelOrder.status#
        , CheckinDate = #hotelOrder.checkinDate#
        , CheckoutDate = #hotelOrder.checkoutDate#
        , OrderDate = #hotelOrder.orderDate#
        , UpdateDate = #hotelOrder.updateDate#
        , OrderPrice = #hotelOrder.orderPrice#
        , CurrencyCode = #hotelOrder.currencyCode#
        , RoomCount = #hotelOrder.roomCount#
        , ContactName = #hotelOrder.contactName#
        , ContactPhone = #hotelOrder.contactPhone#
        , OriginStatus = #hotelOrder.originStatus#
        , UpdateTime = NOW()
        , HotelID = #hotelOrder.hotelId#
        , ShopID = #hotelOrder.shopId#
        where
        otaId = #hotelOrder.otaId#
        AND
        OTAOrderID = #hotelOrder.originOrderId#
    
        UPDATE WED_ShopProduct
        SET
        
            ShopID = #shopProduct.shopId#,
            ShopName = #shopProduct.shopName#,
            CityID = #shopProduct.cityId#,
            ProductCategoryID = #shopProduct.productCategoryId#,
            Price = #shopProduct.price#,
            OriginalPrice = #shopProduct.originalPrice#,
            IsMain = #shopProduct.isMain#,
            OrderNo = #shopProduct.orderNo#,
            Name = #shopProduct.name#,
            SimpleDescription = #shopProduct.simpleDescription#,
            PicID = #shopProduct.picId#,
            PicPath = #shopProduct.picPath#
        
        WHERE
            ID = #shopProduct.id#
        LIMIT 1
    
        UPDATE WED_ShopProduct
        SET
            IsMain = #isMain#
        WHERE
            ID = #id#
        LIMIT 1
    
    	
    		UPDATE WED_ShopProduct
    		SET
    			IsMain = 0,
    			OrderNo = 0,
    			UpdateTime = NOW()
    		WHERE
    			ShopID = #shopId#
    		AND
    			IsMain = 1
    	
    
    	
    		UPDATE WED_ShopProduct
    		SET
    			IsMain = 1,
    			OrderNo = #orderNo#
    		WHERE
    			ID = #productId#
    	
    
		
			UPDATE DP_Shop_Contact
			SET Status = #status#
			WHERE ID=#contactId#
		
	
		
			UPDATE DP_Shop_Contact
			SET Name= #shopContactBasicInfo.Name#,
			Mobile= #shopContactBasicInfo.mobile#,
			Email= #shopContactBasicInfo.email#,
			Memo= #shopContactBasicInfo.memo#,
			ShopID= #shopContactBasicInfo.shopId#,
			UpdateTime= NOW()
			WHERE ID=#contactId#
		
	
		UPDATE MC_MemberCardShop s
		SET s.ShopName = #shopName#,s.BranchName = #branchName#,s.ShopGroupId = #shopGroupId#,s.CardGroupID = #cardGroupId#
		WHERE s.ShopID = #shopId#
	
		UPDATE MC_MemberCardNumberUser SET CardGroupID = #fromCardId# WHERE CardGroupID = #toCardId#
	  
	    UPDATE MC_MemberCardUser SET CardNO = #cardNO# WHERE MemberCardUserID = #memberCardUserId#
	
		UPDATE DP_OfficialAlbumPic 
		SET OrderNo = #orderNo# 
		WHERE AlbumID = #albumId# AND PicID = #picId#
	
        update DP_HotelCruiseCoupon
        set Status = #bean.status#,
        UserID = #bean.userId#,
        EventId = #bean.eventId#,
        UpdateTime = NOW()
        where Id = #bean.id#
    
      update DP_HotelEventReview319
      SET
          Name = #name#,
          Tel = #tel#,
          Address = #address#,
          UpdateTime = NOW()
      where UserID = #userId#
    
		
			UPDATE DP_ShopProductAuditLog SET  Comments =#ShopProductAuditLog.comments#, AddUser = #ShopProductAuditLog.addUser#, AddTime=NOW() WHERE ProductLogID=#productLogId# AND LogType=#logType#
    	
	
			UPDATE DP_BizAccount
			SET ShopAccountID = #bizAccount.shopAccountId#, ContractNo=#bizAccount.contractNo#,SalerID=#bizAccount.salerId#,SalerName=#bizAccount.salerName#, InvoiceTitle=#bizAccount.invoiceTitle#, LastUser=#bizAccount.lastUser#, LastDate=NOW()
			WHERE AccountID = #bizAccount.accountId#
	
			UPDATE DP_BizAccount
			SET Balance = Balance + #amount#
			WHERE AccountID = #accountId#
	
		WHERE ListID=#listId# AND ShopID=#shopId#
	
		WHERE ListID=#listId#
	
		WHERE ListID=#listId#
	
        UPDATE SE_GiftUserGot
        SET Status = #status#
        WHERE  GiftUserGotId = #giftUserGotId#
    
		UPDATE DP_SeWords SET WordLeft = WordLeft - #num# WHERE WordId = #wordId#;
	
		UPDATE SE_UploadEvent
		SET IsDGot = #isDGet#, Status = 2
		WHERE ShopID = #shopId#
		AND   UserID = #userId#
		AND   PicID  = #picId#
	
    	
    		UPDATE DP_ShopDishTag SET Star=#shopDishTag.star#,Price=#shopDishTag.price#,LastIP=#shopDishTag.lastIP# 
    		WHERE ShopID=#shopDishTag.shopID# AND UserID=#shopDishTag.userID# AND DishTagName=#shopDishTag.dishTagName# 
    		ORDER BY CookieUserID DESC limit 1
    	
	
		UPDATE DP_ShopDoubtLog SET Reason = #shopDoubtLog.reason#, ReasonType = #shopDoubtLog.reasonType#, AddDate = #shopDoubtLog.addDate#,
								   SubmitterID = #shopDoubtLog.submitterId#, SubmitterName = #shopDoubtLog.submitterName#
		WHERE ShopLogID = #shopDoubtLog.shopLogId# AND Status = 0
	
		UPDATE DP_ShopDoubtLog SET Status = 1 WHERE ShopLogID = #shopLogId# AND Status = 0
	
		UPDATE 
			DP_ShopSMSInfo 
		SET
			ShopName=#shopSMSInfo.shopName#,
			Address=#shopSMSInfo.address#
		WHERE
			ShopId=#shopSMSInfo.shopId#
	
	UPDATE DP_ShopSMSInfo A, DP_Shop B SET A.ShopName = #shopName# 
		    WHERE A.ShopID =B.ShopID AND  B.ShopGroupID = #shopGroupId#
	
    
    
        


        UPDATE MSD_ShopAuthority
        SET `Status` = #status#
        WHERE ItemId = #itemId# AND AccountId = #accountId#
        AND `Status` >= 0


        
    
        

        UPDATE MSD_ShopAuthority
        SET `Status` = #status#
        WHERE ItemId = #itemId#
        AND `Status` >= 0

        

    
    
    
        
        UPDATE MSD_LaunchInfo
        SET `Status` = #status#
        WHERE `Status` > 0 AND LaunchId = #launchId#
        
    
         
        UPDATE MSD_LaunchInfo
        SET `Status` = #status#
        WHERE `Status` > 0 AND LaunchGroupId = #launchGroupId#
        
    
    
    
    
    
        
        UPDATE MSD_OrderInfo
        SET `Status` = #status#
        WHERE OrderId = #orderId# AND `Status` >= 0
        
    
        
        UPDATE MSD_ItemInfo
        SET `Status` = #status#
        WHERE OrderId = #orderId# AND `Status` >= 0
        
    
        
        UPDATE MSD_ItemDuration
        SET `Status` = #status#
        WHERE ItemId = #itemId# AND `Status` >= 0
        
    
        
        UPDATE MSD_Contact
        SET `Status` = #status#
        WHERE OrderId = #orderId# AND `Status` >= 0
        
    
        
            UPDATE DP_ShoppingMall_Promo SET Url=#promo.url#, Content=#promo.content#, UpdateTime=NOW()
            WHERE ShopID=#promo.shopId#;
        
    
		UPDATE DP_Shop SET PicTotal = PicTotal + #picNumber# WHERE  ShopID=#shopId#
    
		
			 UPDATE DP_Shop SET ShopTags =#shopTags# WHERE ShopID=#shopId#
    	
	
		
			 UPDATE DP_Shop SET DishTags =#dishTags# WHERE ShopID=#shopId#
    	
	
		UPDATE DP_Shop SET BranchTotal = #branchTotal# WHERE ShopGroupID = #shopGroupId#;
	
		UPDATE DP_Shop SET PhoneNo = #phoneNo#, PhoneNo2= #phoneNo2# WHERE ShopID = #shopId#;
	
		UPDATE DP_Shop SET ShopGroupID = #shopId# WHERE ShopID = #shopId#;
	
		UPDATE DP_Shop SET ShopType=#shop.shopType#, ShopName= #shop.shopName#,BranchName= #shop.branchName#,Address= #shop.address#,CrossRoad=#shop.crossRoad#
        ,PhoneNo= #shop.phoneNo#,PhoneNo2= #shop.phoneNo2#,AltName=#shop.altName#, District= #shop.district#,LastIP	= #shop.lastIp#,LastUser=#shop.lastUser#,LastUserName= #shop.lastUserName#,LastDate=NOW(), HasStaticMap = 0,
        BusinessHours = #shop.businessHours#, PublicTransit = #shop.publicTransit#, PriceInfo = #shop.priceInfo#
        WHERE ShopID = #shop.shopId#;	
	
		
          UPDATE DP_Shop SET DefaultPic=#picUrl#,LastDate=NOW() WHERE ShopID = #shopId#;
    	
    
		UPDATE DP_Shop SET GLat=#lat#,GLng=#lng#,HasStaticMap=0 WHERE ShopID = #shopId#;
	
		
			 UPDATE DP_Shop SET WishTotal = WishTotal + #increament# WHERE ShopID=#shopId#
    	
	
		
			 UPDATE DP_Shop SET Power =#power# WHERE ShopID=#shopId#
    	
	
		UPDATE WED_Phone
		SET Status=#status#
		WHERE Id=#id#;
	
		UPDATE WED_Phone
		SET Status=#status#
		WHERE TelNum=#telNum# AND SubNum=#subNum#;
	
        UPDATE
            DP_ShopPhoneRecord
        SET UpdateTime = NOW(),
            BookingFlag = #shopPhoneRecord.bookingFlag#
        WHERE
            ID = #shopPhoneRecord.id#
    
    	
    	UPDATE DP_ShopProduct SET ProductName=#shopProduct.productName#,ShopID=#shopProduct.shopId#, CityID= #shopProduct.cityId#,ProductTemplateID= #shopProduct.productTemplateId#,ProductType= #shopProduct.productType#,PromoStartTime=#shopProduct.promoStartTime#
        ,PromoEndTime= #shopProduct.promoEndTime#,Price= #shopProduct.price#,OriginalPrice=#shopProduct.originalPrice#, LastUser = #shopProduct.lastUser#,LastTime = NOW(),LastIP = #shopProduct.lastIp#,Status = #shopProduct.status#
        WHERE ProductID = #shopProduct.productId#;
    	
	
	
		WHERE Id = #updateEntity.id#
	
		
			UPDATE WED_EventShopPoiDetail SET
				TemplateId=#eventShopPoiDetail.templateId#,
				SignUpID=#eventShopPoiDetail.signUpId#,
				EventPoiID=#eventShopPoiDetail.eventPoiId#,
				PoiSource=#eventShopPoiDetail.poiSource:NUMERIC#,
				Content=#eventShopPoiDetail.content#,
				AddDate=#eventShopPoiDetail.addDate#,
				UpdateDate=NOW()
			WHERE ID=#eventShopPoiDetail.id#
		
	
        UPDATE TG_ReceiptGroupCodePool
        SET Status = 0
        WHERE
        PoolID = #poolId#
        AND Status = 1 >0
    
		UPDATE 
			TG_EventUserLog 
		SET
			EventId=#eventUserLog.eventId#,
			UserId=#eventUserLog.userId#,
			UserNickName=#eventUserLog.userNickName#,
			MobileNo=#eventUserLog.mobileNo#,
			AddDate=#eventUserLog.addDate#,
			CityId=#eventUserLog.cityId#,
			PrizeId=#eventUserLog.prizeId#,
			IP=#eventUserLog.iP#,
			Memo=#eventUserLog.memo#,
			ReferId=#eventUserLog.referId#
		WHERE
			LogId=#eventUserLog.logId#
	
    
  	update TG_OrderCoupon
  	set CouponID = #couponId#
  	where OrderID = #orderId#
  
    update TG_Order
    set UserID = #userID:INTEGER#,
      CityID = #cityID:SMALLINT#,
      DealGroupID = #dealGroupID:INTEGER#,
      DealID = #dealID:INTEGER#,
      Quantity = #quantity:SMALLINT#,
      TotalAmount = #totalAmount:DECIMAL#,
      PaymentAmount = #paymentAmount:DECIMAL#,
      MobileNo = #mobileNo:VARCHAR#,
      Status = #status:TINYINT#,
      RefundStatus = #refundStatus:TINYINT#,
      AddDate = #addDate:TIMESTAMP#,
      UpdateDate = now(),
      SuccessDate = #successDate:TIMESTAMP#,
      PayChannelStatus = #payChannelStatus:VARCHAR#,
      AddUserIP = #addUserIP:VARCHAR#
    where OrderID = #orderID:INTEGER#
  
    update TG_Order
    set UserID = #userID:INTEGER#,
      CityID = #cityID:SMALLINT#,
      DealGroupID = #dealGroupID:INTEGER#,
      DealID = #dealID:INTEGER#,
      Quantity = #quantity:SMALLINT#,
      TotalAmount = #totalAmount:DECIMAL#,
      PaymentAmount = #paymentAmount:DECIMAL#,
      MobileNo = #mobileNo:VARCHAR#,
      Status = #status:TINYINT#,
      RefundStatus = #refundStatus:TINYINT#,
      AddDate = #addDate:TIMESTAMP#,
      UpdateDate = now(),
      SuccessDate = now(),
      PayChannelStatus = #payChannelStatus:VARCHAR#,
      AddUserIP = #addUserIP:VARCHAR#
    where OrderID = #orderID:INTEGER#
  
    where OrderID = #orderID:INTEGER#
  
    
    update TG_ReceiptPool
    set Status = #status:INTEGER#
    where SerialNumber = #serialNumber:VARCHAR#
  
    
    update TG_ReceiptPool
    set Status = #status:INTEGER#
    where ReceiptPoolID = #receiptPoolID#
  
          update TG_Coupon C
          set C.UsedStatus= #status#,
		  C.UsedDate = now()
		  where C.CouponID=#couponID#
  
  	update Mon_UserBlackList
  	set IsReleased = 1,
  	ReleasedDate = now()
  	WHERE UserID = #userID#
  	AND Type in (6,7) and IsReleased = 0
  
          update TG_Charge
          set STATUS= #status#,
		  SuccessDate = now()
          WHERE ChargeID = #chargeId#
  
		UPDATE TG_WishList
		SET Status = #status#, LastDate = NOW()
		WHERE DealGroupID = #dealGroupId# AND UserID=#userId#
	
          update TG_EmailPush set 
			UserID=#userId#,
			Status=#status#,
			UpdateDate=now(),
			AddMode=#addMode#  
          WHERE EmailPushID = #emailPushId#
  
		UPDATE DianPingMobile.CI_CommonToken 
		SET TokenStatus = #TokenStatus#
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET DeviceID = #DeviceID#
		WHERE ID = #ID#
	
  		UPDATE TGM_IPhonePush SET PToken = '' WHERE PToken = #ptoken#
   
        UPDATE 
		  TGE_UserCouponShow 
		SET
		  ShowTimes = ShowTimes + 1 
		WHERE UserId = #userId# AND EventType = #couponType#
    
	UPDATE TG_DealGroupVerify SET
		DealID = #dealID#,
		DealGroupID = #dealGroupID#,
		UpdateDate = NOW(),
		Status = #status#,
		ThirdPartyDealID = #thirdPartyDealID#,
		ThirdPartyId = #thirdPartyId#,
		Num = #num#,
		IsspID = #isspID#
	WHERE VerifyID = #verifyID#
	
  UPDATE TG_DealReceiptInfo SET
      DealGroupID = #dealGroupID#,
      Contact = #contact#,
      TrafficInfo = #trafficInfo#,
      Memo = #memo#,
      BeginDate = #beginDate#,
      EndDate = #endDate#,
      RefundDate = #refundDate#
  WHERE DealID = #dealID#
  
  UPDATE TG_DealReceiptInfo SET
      BeginDate = #beginDate#,
      EndDate = #endDate#,
      RefundDate = #refundDate#
  WHERE DealGroupID = #dealGroupID#
  
	 
	
	
		UPDATE TG_BD_Ratio 
		SET Ratio = #Ratio#, ADDTIME = #AddTime#
		WHERE RatioID = #RatioID#
	
		UPDATE TG_Mid_MB_ThirdRatio 
		SET Ratio = #Ratio#, ADDTIME = #AddTime#
		WHERE ID = #ID#  
	
    
	    UPDATE TG_EventSendDeal 
		SET
		STATUS = #type#,  
		SuccessTime = NOW()		
		WHERE
		ID = #id#
    
    
		UPDATE Mon_ChannelReport SET PV = #channelAnalyseEntity.PV# ,
		                             UV = #channelAnalyseEntity.UV#,
		                             Amount = #channelAnalyseEntity.amount#
		WHERE ChannelID = #channelID# 
		  AND CountType = #countType#
          AND StartTime = #startTime#
	
		UPDATE Mon_ChannelReport SET OrderPV = #channelAnalyseEntity.PV# ,
		                             OrderUV = #channelAnalyseEntity.UV#,
		                             OrderAmount = #channelAnalyseEntity.amount#
		WHERE ChannelID = #channelID# 
		  AND CountType = #countType#
          AND StartTime = #startTime#
	
		UPDATE Mon_ChannelReport SET BuyPV = #channelAnalyseEntity.PV# ,
		                             BuyUV = #channelAnalyseEntity.UV#,
		                             BuyAmount = #channelAnalyseEntity.amount#
		WHERE ChannelID = #channelID# AND
		      CountType = #countType# AND
		      StartTime = #startTime#
	
		UPDATE TG_OperationRole SET
			RoleName = #role.name#,
			RoleFunc = #role.roleFunc#,
			UpdateTime = NOW()
		WHERE RoleID = #role.id#
	
        UPDATE TG_OpUserRes 
        SET UserType=#userRestrict.userType#, CityIDs=#userRestrict.cityIDs#
        WHERE UserID=#userRestrict.userID#
    
    
        UPDATE TE_AdChannel
        SET Name=#adChannel.name#, Owner=#adChannel.owner#, Author=#adChannel.author#, UpdateTime=NOW()
        WHERE ID=#adChannel.id#
    
    
    
        UPDATE TE_AdChannel
        SET Status=#status#
        WHERE ID=#id#
    
    
    
        UPDATE TE_AdContainer
        SET Name=#adContainer.name#, ChannelID=#adContainer.channelID#, Author=#adContainer.author#, UpdateTime=NOW()
        WHERE ID=#adContainer.id#
    
    
    
        UPDATE TE_AdContainer
        SET Status=#status#
        WHERE ID=#id#
    
    
    
        UPDATE TE_AdDetail
        SET Title=#adDetail.title#, SubTitle=#adDetail.subTitle#, Thumb=#adDetail.thumb#, Link=#adDetail.link#, `Desc`=#adDetail.desc#, Type=#adDetail.type#, resourceId=#adDetail.resourceId#, UpdateTime=NOW()
        WHERE ID=#adDetail.id#
    
    
    
        UPDATE TE_AdDetail
        SET Status=#status#
        WHERE ID=#id#
    
    
    
        UPDATE TE_AdDimension
        SET PublishID=#adDimension.publishID#, CityID=#adDimension.cityID#, Version=#adDimension.version#, OrderNo=#adDimension.orderNo#, AdvertisementID=#adDimension.advertisementID#, Type=#adDimension.type#, UpdateTime=NOW()
        WHERE ID=#adDimension.id#
    
    
    
        UPDATE TE_AdDimension
        SET Status=#status#
        WHERE ID=#id#
    
    
    
        UPDATE TE_AdPosition
        SET Title=#adPosition.title#, `Desc`=#adPosition.desc#, ChannelID=#adPosition.channelID#, ContainerID=#adPosition.containerID#, Width=#adPosition.width#, Height=#adPosition.height#, Type=#adPosition.type#, Author=#adPosition.author#, UpdateTime=NOW(), PublishType = #adPosition.publishType#
        WHERE ID=#adPosition.id#
    
    
    
        UPDATE TE_AdPosition
        SET Status=#status#
        WHERE ID=#id#
    
    
    
        UPDATE TE_AdPublish
        SET Position=#adPublish.position#, Title=#adPublish.title#, Author=#adPublish.author#, Auditor=#adPublish.auditor#, BeginDate=#adPublish.beginDate#, EndDate=#adPublish.endDate#, AuditTime=#adPublish.auditTime#, UpdateTime=NOW()
        WHERE ID=#adPublish.id#
    
    
    
        UPDATE TE_AdPublish
        SET Status=#status#
        WHERE ID=#id#
    
    
		
		update 
			TG_Event
		set
			EventTitle=#eventTitle#,
			EventType=#eventType#,
			Status=#status#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			PreBeginDate=#preBeginDate#,
			PreEndDate=#preEndDate#,
			AheadHour=#aheadHour#,
			Icon=#icon#,
			Scale=#scale#,
			EventDesc=#eventDesc#,
			CurrentJoin=#currentJoin#,
			MaxJoin=#maxJoin#,
			MaxPerUser=#maxPerUser#,
			ConsumePoint=#consumePoint#,
			EventEnName=#eventEnName#,
			Config=#config#,
			EventHref=#eventHref#,
			ReferEventID=#referEventID#
		where
			EventID=#eventId#
		
	
		UPDATE
			TG_EVENT
		SET
			EventTitle=#event.eventTitle#, EventType=#event.eventType#, Status=#event.status#, BeginDate=#event.beginDate#,
			EndDate=#event.endDate#, PreBeginDate=#event.preBeginDate#, PreEndDate=#event.preEndDate#, Icon=#event.icon#, Notice=#event.notice#, EventDesc=#event.eventDesc#		
		WHERE
			EventID=#event.eventID#
	
		UPDATE TG_Event SET TagItemEnName=#tagEnName# WHERE EventID=#eventId#
	
		
		update 
			TG_EventPrize
		set
			PrizeName=#prizeName#,
			PrizeImage=#prizeImage#,
			PrizeDesc=#prizeDesc#,
			PrizeType=#prizeType#,
			Stocks=#stocks#,
			Odds=#odds#,
			Priority=#priority#,
			Consumption=#consumption#,
			SmsText=#smsText#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			Status=#status#,
			EventId=#eventId#,
			Config=#config#,
			Source=#source#
		where
			PrizeId=#prizeId#
		
	
		update
			TG_EventPrizeRule
		set
			EventID = #eventId#,
			MaxAwardNum = #maxAwardNum#,
			MaxJoinNum = #maxJoinNum#,
			LuckyUserLimits = #luckyUserLimits#,
			BigUserRatio = #bigUserRatio#,
			UserThreshold = #userThreshold#,
			DefaultPrizeID = #defaultPrizeId#,
			CouponPeriod = #couponPeriod#
		where
			EventPrizeRuleID = #eventPrizeRuleId#
	
	    UPDATE TG_Event_TopicSkin
        SET TopicSkinID=#topicDto.topicSkinID#,SkinName=#topicDto.skinName#,Type=#topicDto.type#,BannerImage=#topicDto.bannerImage#,
           TailImage=#topicDto.tailImage#,MobileBannerImage=#topicDto.mobileBannerImage#,
            ItemBgColor=#topicDto.itemBgColor#,ItemLabelColor=#topicDto.itemLabelColor#,ItemButtonColor=#topicDto.itemButtonColor#,ButtonFontColor=#topicDto.buttonFontColor#,
            ThumbNail=#topicDto.thumbNail#,SkinEnName=#topicDto.skinEnName#
	    WHERE TopicSkinID=#topicDto.topicSkinID#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			BannerImage=#bannerImage:VARCHAR:No Image#,
			MobileBannerImage=#mobileBannerImage#,
			Status=#status#,
			Type=#type#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			MobileBannerImage=#mobileBannerImage#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			Status=#status#,
			Type=#type#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			BannerImage=#bannerImage:VARCHAR:No Image#,
			Status=#status#,
			Type=#type#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			Status=#status#,
			Type=#type#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_Topic
		set
			Href=#href#
		where
			TopicID=#topicId#
	
	    WHERE TopicID=#topicDto.topicID#
	
	   UPDATE TG_Event_Topic
	   SET BgColor=#bgColor# 
	   WHERE TopicID=#topicId#
	
        UPDATE TG_Event_TopicItem
        SET TopicItemName=#topicItem.topicItemName#, Priority=#topicItem.priority#, DisplayCount=#topicItem.displayCount#, ItemBgColor=#topicItem.itemBgColor#, ItemLabelColor=#topicItem.itemLabelColor#, 
        ItemButtonColor=#topicItem.itemButtonColor#, ButtonFontColor=#topicItem.buttonFontColor#
        WHERE TopicItemID=#topicItem.topicItemID#
    
        UPDATE 
        	TG_Event_TopicItem
        SET 
        	ItemBgColor=#topicItem.itemBgColor#, ItemLabelColor=#topicItem.itemLabelColor#, 
        	ItemButtonColor=#topicItem.itemButtonColor#, ButtonFontColor=#topicItem.buttonFontColor#
        WHERE 
        	TopicID=#topicId#
    
        UPDATE TG_Event_TopicCity
        SET STATUS=1
        WHERE TopicID=#topicId#;
    
        UPDATE TG_Event_TopicItemCityDeal
        SET TopicItemID=#topicDeal.topicItemID#, CityID=#topicDeal.cityID#, DealGroupID=#topicDeal.dealGroupID#, DealGroupShortName=#topicDeal.dealGroupShortName#, 
        RecommendReason=#topicDeal.recommendReason#, DisplayOrder=#topicDeal.displayOrder#, ImgUrl=#topicDeal.imgUrl#
        WHERE TopicItemID=#topicDeal.topicItemID# AND CityID=#topicDeal.cityID# AND DealGroupID=#topicDeal.dealGroupID#
    
        UPDATE TG_Event_TopicItemCityDeal
        SET TopicItemID=#topicDeal.topicItemID#, DisplayOrder=#topicDeal.displayOrder#
        WHERE TopicItemCityDealID=#topicDeal.topicItemCityDealID#
    
		update 
			TG_Event_TopicItemCity
		set
			DealGroupIDs=#dealGroupIDs#
		where
			id=#id#
	
		update 
			TG_Event_TopicItemCity
		set
			DealGroupIDs=#dealGroupIDs#
		where
			TopicItemID=#topicItemId# and CityID=#cityId#
	
    
    	UPDATE TG_Event_ShanTuan
    	SET DealGroupTitle=#shantuan.dealGroupTitle#, BeginDate=#shantuan.beginDate#, EndDate=#shantuan.endDate#, DealGroupID=#shantuan.dealGroupID#, 
        IsReplaceTitle=#shantuan.isReplaceTitle#, UpdateDate=NOW(), ReplaceImgURL=#shantuan.replaceImgURL#
        WHERE ShanID=#shantuan.shanID#
    
    
	
		UPDATE TG_Event_ShanTuan
		SET Status=#status#
		WHERE ShanID=#shanID#
	
    
    
    	UPDATE TG_Event_ShanTuanCity
    	SET ShanID=#shantuanCity.shanID#, CityID=#shantuanCity.cityID#, UpdateDate=NOW()
        WHERE ID=#shantuanCity.id#
    
    
	
		UPDATE TG_Event_ShanTuanCity
		SET Status=#status#
		WHERE ID=#id#
	
    
		UPDATE TG_MovieSort 
		SET MovieIDs = #movieIDs#, UpdateDate = Now()
		WHERE
		CityID = #cityId# 
		AND 
		Type = #type#
	
		UPDATE DianPingMC.MC_MemberCardProductConfirmInfo 
		SET
			ProductName = #productName#,
			ProductDiscountRate = #productDiscountRate#,
			ProductDesc = #productDesc#,
			BeginDate = #beginDate#,
			EndDate = #endDate#,
			AuthAdminID = #authAdminID#,
			AuthAdminName = #authAdminName#
		WHERE ShopConfirmInfoID = #shopConfirmInfoID#
			  AND ProductType = #productType#
	
		UPDATE
			GPA_OnlineActivityCity
		SET
			Status = #status#,
			UpdateTime = NOW()
		WHERE
			OnlineActivityID = #onlineActivityId#
		AND
			CityID = #cityId#
	
		UPDATE 
			GPA_OnlineActivityJoinRule 
		SET 
			ContentType=#onlineActivityJoinRule.contentType#,
			RuleDimension=#onlineActivityJoinRule.ruleDimension#,
			RuleValue=#onlineActivityJoinRule.ruleValue#,
			Status=#onlineActivityJoinRule.status#,
			UpdateTime=NOW()
		WHERE
			ID=#onlineActivityJoinRule.id#
	
		UPDATE 
			GPA_OnlineActivityJoinRule 
		SET 
			Status=#status#
		WHERE
			ID = #id#
	
    	
    		UPDATE `TG_MonitorData` MD
			SET MD.`Value` = #value#, MD.`DecVal` = #decVal#
			WHERE MD.`DataDate` = #date#
			AND MD.`Key` = #key#
			;
    	
	
        UPDATE DianPing.DP_MyListPush
        SET DP_MyListPush.Order = DP_MyListPush.Order + #order#,
        DP_MyListPush.UpdateDate = now()
        WHERE
        Page = #page#
        AND CityID = #cityId#
    
        UPDATE DianPing.DP_MyListPush
        SET DP_MyListPush.Order = DP_MyListPush.Order - 1,
        DP_MyListPush.UpdateDate = now()
        WHERE ID = #upId#
    
        UPDATE DianPing.DP_MyListPush
        SET DP_MyListPush.Order = DP_MyListPush.Order + 1,
        DP_MyListPush.UpdateDate = now()
        WHERE ID = #downId#
    
        UPDATE DianPing.DP_MyListPush
        SET DP_MyListPush.Order = DP_MyListPush.Order - 1,
        DP_MyListPush.UpdateDate = now()
        WHERE
        Page = #page#
        AND CityID = #cityId#
        AND DP_MyListPush.Order > #order#
    
        UPDATE DianPing.DP_MyListPush
        SET PushTitle = #title#,
        UpdateDate = now()
        WHERE
        ID = #id#
    
        UPDATE DianPing.DP_MyListPush
        SET
        PushPicID = #picId#,
        PushPicUrl = #picUrl#,
        UpdateDate = now()
        WHERE
        ID = #id#
    
    	UPDATE DPAPIReport.Mid_MB_ThirdUser 
		SET PASSWORD = #PASSWORD#
		WHERE UserName = #UserName#
    
		UPDATE
			BC_Notice
		SET 
			Title = #title#,
			Content = #content#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
			BC_Notice
		SET 
		    LastAdminID = #lastAdminID#,
			Status = 0
		WHERE
			ID=#id#
    
		update
			TGE_DealBuyCouponSendLog
		set
			Status = #status#
		where 
			LogId = #logId#
	
	   UPDATE 
	       TGE_ExtraCouponInfo 
	   SET 
	       STATUS=1 
	   WHERE 
	       CouponInfoID=#couponInfoId#
	
    	UPDATE TG_HotelForReservation
    	Set Status = #entity.status#, UpdateDate = now()
    	WHERE TPID = #entity.tpId# AND TPHotelID = #entity.tpHotelId# AND RoomType = #entity.roomType#
    
    	UPDATE TG_HotelForReservation
    	SET Status = #to#, UpdateDate = now()
    	WHERE Status = #from#
    
		UPDATE TGHT_ReceiptDailyConsume
		SET ConsumeCount = #consumeCount#,AddTime = NOW()
		WHERE DealID = #dealId# AND DealGroupID = #dealGroupId#
		AND ShopID = #shopId# AND LoginAccountId = #loginAccountId#
		AND ReceiptDate BETWEEN #beginDate# AND #endDate#
	
    	
        UPDATE 
            TGE_CouponControl
        SET CouponNum = CouponNum+1
        WHERE 
            CouponControlID = #couponInfoId# AND CouponNum <CouponStock 
        
	
        
        UPDATE 
            TGE_CouponControl
        SET ShowTimes = ShowTimes +1 
        WHERE 
            CouponControlID = #couponInfoId# AND ShowTimes < CouponStock
        
    
        UPDATE 
		  TGE_UserCouponShow 
		SET
		  ShowTimes = ShowTimes + 1 
		WHERE UserId = #userId#	  AND EventType =#eventType#
    
     UPDATE TGE_CouponGroupInfo SET CouponStatus =#status# 
     WHERE CouponType=#type# AND StartTime = #startTime# 
    
		UPDATE DP_Shop 
		SET Power = #power#
		WHERE ShopID = #shopID#
	
		UPDATE DP_Shop 
		SET GLat = #lat#,GLng= #lng#		
		WHERE ShopID = #shopID#
	
	 
	   update ZS_User set UserNickName=#userNickName# where UserID=#userID#
		
	

	   update ZS_UserProfile set UserSex=#gender# where UserID=#userID#
		
	
		UPDATE DianPing.ZS_User 
		SET UserCity = #UserCity#
		WHERE UserID = #UserID#
	
  		UPDATE TGM_IPhonePush SET PToken = '' WHERE PToken = #ptoken#
   
        UPDATE TG_SubThirdUserOrder
        SET Status = #status#
        WHERE OrderID = #orderId# AND SubOrderID = #subOrderId# AND ChannelID = #channelId#
    
        UPDATE TG_SubThirdUserOrder
        SET IsVerified = #isVerified#
        WHERE OrderID = #orderId# AND SubOrderID = #subOrderId# AND ChannelID = #channelId#
    
        UPDATE TG_SubThirdUserOrder
        SET ReceiptID = #receiptId#, Status = #status#, IsVerified = #isVerified#
        WHERE OrderID = #orderId# AND SubOrderID = #subOrderId# AND ChannelID = #channelId#
    
		UPDATE 
			EBIZ_ApplyFlow 
		SET
			ApplyId=#applyFlow.applyId#,
			FlowType=#applyFlow.flowType#,
			ReasonId=#applyFlow.reasonId#,
			AccountId=#applyFlow.accountId#,
			AccountType=#applyFlow.accountType#,
			Memo=#applyFlow.memo#,
			AddDate=#applyFlow.addDate#,
			UpdateDate=#applyFlow.updateDate#,
			Ip=#applyFlow.ip#
		WHERE
			FlowId=#applyFlow.flowId#
	
		UPDATE 
			TG_DealGroupRemind 
		SET
			Status=0
		WHERE
			RemindId in ($remindIds$) AND UserId=#userId#
	
		UPDATE 
			TG_DealGroupRemind 
		SET
			Status=0
		WHERE
			ShopGroupId in ($shopGroupIds$) AND UserId=#userId#
	
		UPDATE TG_DealScore SET
			RateScore = #data.rateScore#,
			RateCount = #data.rateCount#,
			PublicCount = #data.publicCount#,
			RateCount10 = #data.rateCount10#,
			RateCount20 = #data.rateCount20#,
			RateCount30 = #data.rateCount30#,
			RateCount40 = #data.rateCount40#,
			RateCount50 = #data.rateCount50#,
			UpdateDate = NOW(),
			AgainCount = #data.againCount#
		WHERE DealGroupID = #data.id#
	
		UPDATE 
			TG_FriendLink 
		SET
			Title=#friendLink.title#,
			Type=#friendLink.type#,
			ImageUrl=#friendLink.imageUrl#,
			Link=#friendLink.link#,
			Status=#friendLink.status#,
			Priority=#friendLink.priority#
		WHERE
			Id=#friendLink.id#
	
		UPDATE 
			TG_UserSetting 
		SET
			RemindSmsType=#userSetting.remindSmsType#,
			RemindSmsStatus=#userSetting.remindSmsStatus#,
			RemindEmailStatus=#userSetting.remindEmailStatus#,
			UpdateDate=#userSetting.updateDate#
		WHERE
			UserId=#userSetting.userId#
	
		UPDATE TG_DealGroupBundle SET
		DealGroupID = #data.dealGroupId#,
		DealGroupID1 = #data.dealGroupId1#,
		DealGroupID2 = #data.dealGroupId2#,
		DealGroupID3 = #data.dealGroupId3#,
		DealGroupID4 = #data.dealGroupId4#,
		Title = #data.title#,
		Discount =
		#data.discount#,
		Status = #data.status#,
		UpdateDate = NOW()
		WHERE
		BundleID = #data.id#
	
	
			update MB_Base_trigger
			set flag = #flag#, update_dt = #update_dt#
			where ID = #id#
		
        DealGroupExceptDateID=DealGroupExceptDateID
        WHERE DealGroupExceptDateID=#entity.id#
    
        

		DELETE FROM TPDA_DealGroupExceptDate WHERE DealGroupExceptDateID=#entity.id#

        
    
        

		DELETE FROM TPDA_DealGroupExceptDate WHERE DealGroupID=#dealGroupId#

        
    
            DealGroupExceptHolidayID=DealGroupExceptHolidayID
        WHERE DealGroupExceptHolidayID=#entity.id#
    
		UPDATE TGP_COMPOSITABLE_STATEMENT_TEMPLATE_ASSN
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		COMPOSITABLE_TEMPLATE_ID=#entity.compositableTemplate.id#,
		STATEMENT_TEMPLATE_ID=#entity.statementTemplateId#
		WHERE
		COMPOSITABLE_STATEMENT_TEMPLATE_ASSN_ID=#entity.id#
	
		UPDATE TGP_DOCUMENT_BUILDER
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VERSION_ID=#entity.versionId#,
		DEAL_GROUP_ID=#entity.dealGroupId#,
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplateId#
		WHERE
		DOCUMENT_BUILDER_ID=#entity.id#
	
		UPDATE TGP_TEMPLATE_ENTRY
		SET
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplate.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		IS_MANDATORY=#entity.isMandatory#,
		AREA_TYPE_ID=#entity.areaTypeId#,
		SEQUENCE=#entity.sequence#
		WHERE
		TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='StatementTemplate'
	
		UPDATE
		TGP_DEAL_GROUP_VERSION
		SET
		DEAL_GROUP_ID=#entity.dealGroupId#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		PUBLISH_FROM_DATE=#entity.publishFromDate#,
		PUBLISH_TO_DATE=#entity.publishToDate#,
		EFFECTIVE_BEGIN_DATE=#entity.effectiveBeginDate#,
		EFFECTIVE_END_DATE=#entity.effectiveEndDate#,
		DEAL_GROUP_CONTENT=#entity.dealGroupContent#,
		DOCUMENT_BUILDER_CONTENT=#entity.documentBuilderContent#,
		VISUAL_VIEW_CONTENT=#entity.visualViewContent#
		WHERE
		DEAL_GROUP_VERSION_ID=#entity.id#
	
		UPDATE TGP_DESTINATION
		SET
		DEAL_GROUP_ID = #entity.dealGroup.id# ,
		CITY_ID = #entity.cityId# ,
		DISTRICT_ID = #entity.districtId# ,
		LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
		LAST_UPDATOR_ID = #entity.lastUpdatorId#
		WHERE DESTINATION_ID=#entity.id#
	
        UPDATE TGP_RECEIPT_VERIFY_HISTORY
        SET
            DEAL_GROUP_ID=#entity.dealGroupId#,
            POI_SHOP_ID=#entity.poiShopId#,
            SUCCESS_SERIAL_NUMBER=#entity.successSerialNumber#,
            FAILURE_SERIAL_NUMBER=#entity.failureSerialNumber#,
            CREATE_TIME=#entity.createTime#,
            CREATOR_ID=#entity.creatorId#,
            LAST_UPDATE_TIME=#entity.lastUpdateTime#,
            LAST_UPDATOR_ID=#entity.lastUpdatorId#,
            MEMO=#entity.memo#,
            RECALL_STATUS=#entity.recallStatus#
          WHERE
            ID=#entity.id#;
	
		UPDATE TGP_SHOP_CITY_GROUP
		SET
		LAST_UPDATOR_ID = #entity.lastUpdatorId#,
		LAST_UPDATE_TIME = #entity.lastUpdateTime#,
		CITY_ID = #entity.cityId#,
		SEQUENCE = #entity.sequence#,
		VISUAL_VIEW_ID = #entity.visualView.id#
		WHERE
		SHOP_CITY_GROUP_ID=#entity.id#
	
		UPDATE TGP_SHOP_INFO
		SET
		LAST_UPDATOR_ID = #entity.lastUpdatorId#,
		LAST_UPDATE_TIME = #entity.lastUpdateTime#,
		ADDRESS = #entity.address#,
		BUSINESS_HOURS = #entity.businessHours#,
		CONTACT_PHONE = #entity.contactPhone#,
		POI_SHOP_ID = #entity.poiShopId#,
		SEQUENCE = #entity.sequence#,
		IS_AVG_PRICE_DISPLAYED = #entity.isAvgPriceDisplayed#,
		IS_BUSINESS_HOURS_DISPLAYED = #entity.isBusinessHoursDisplayed#,
		IS_CONTACT_PHONE_DISPLAYED = #entity.contactPhoneDisplayed#,
		IS_MAP_LINK_DISPLAYED = #entity.isMapLinkDisplayed#,
		IS_STAR_RATE_DISPLAYED = #entity.isStarRateDisplayed#,
		SHOP_CITY_GROUP_ID = #entity.shopCityGroup.id#,
		IS_VOTE_QUANTITY_DISPLAYED = #entity.isVoteQuantityDisplayed#
		WHERE
		SHOP_INFO_ID=#entity.id#
	
	  UPDATE TGP_SPECIAL_REMINDER_TEMPLATE_MAP 
	   SET
		TEMPLATE_URL= #entity.templateUrl#,
		LAST_UPDATE_TIME= #entity.lastUpdateTime#,
		LAST_UPDATOR_ID= #entity.lastUpdatorId#,
		TEMPLATE_NAME= #entity.templateName#
	WHERE 
	    NAV_CATEGORY_ID=#entity.categoryId#
	
        UPDATE TPA_ProcessState SET
            StateValue = #stateValue#
        WHERE
            ID = #ID#
    
    UPDATE TPA_AttributeCategoryAssn SET
    AttributeId=#entity.attributeId#,
    CategoryId=#entity.categoryId#,
    AttributeCategoryAssnId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE AttributeCategoryAssnId=#entity.id#
    
        CreateTime=CreateTime
        WHERE DealID=#entity.id#;
    
        

		DELETE FROM TPDA_Deal WHERE DealID=#entity.id#

        
    
        DealGroupId=DealGroupID
        WHERE DealGroupMaintainerID=#entity.id#
    
        
		DELETE FROM TPDA_DealGroupMaintainer WHERE DealGroupMaintainerID=#entity.id#
        
    
        
        UPDATE TPE_Advertisement
        SET Title     = #advertisement.title#,
        SubTitle      = #advertisement.subTitle#,
        Link          = #advertisement.link#,
        Thumb         = #advertisement.thumb#,
        `Desc`        = #advertisement.desc#,
        ExtraContent  = #advertisement.extraContent#,
        Type          = #advertisement.type#,
        ResourceID    = #advertisement.resourceId#,
        Status        = #advertisement.status#,
        Position      = #advertisement.position#,
        CityID        = #advertisement.cityId#,
        Version       = #advertisement.version#,
        OrderType     = #advertisement.orderType#,
        OrderNo       = #advertisement.orderNo#,
        BeginDate     = #advertisement.beginDate#,
        EndDate       = #advertisement.endDate#,
        PublishTime   = #advertisement.publishTime#
        WHERE
        AdvertisementID = #advertisement.advertisementId#
        
    
         UPDATE
          TPE_Topic
        SET
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
            Author =#topicInfo.author#
        WHERE TopicID = #topicInfo.topicId#
    
        UPDATE
          TPE_Topic
        SET
          STATUS = #status#
        WHERE
          TopicID = #topicId#
    
        UPDATE
        TPE_TopicItem
        SET
            NAME =#topicItem.topicItemName#,
            TopicID =#topicItem.topicId#,
            TYPE =#topicItem.type#,
            STATUS =#topicItem.status#,
            BgColor =#topicItem.itemBgColor#,
            LabelColor =#topicItem.itemLabelColor#,
            ButtonColor =#topicItem.itemButtonColor#,
            BtnFontColor =#topicItem.buttonFontColor#
        WHERE ItemID =#topicItem.topicItemId#
        AND TopicID =#topicItem.topicId#
    
        UPDATE
          TPE_TopicItem
        SET
          STATUS = #status#
        WHERE
          TopicID = #topicId#
    
        UPDATE
          TPE_TopicDeal
        SET
            OrderNo =#topicDeal.orderNo#,
            STATUS =#topicDeal.status#
        WHERE ItemID =#topicDeal.topicItemId#
            AND CityID =#topicDeal.cityId#
            AND DealGroupID =#topicDeal.dealGroupId#
    
         UPDATE
          TPE_TopicDeal
        SET
            STATUS =#topicDeal.status#
        WHERE ItemID =#topicDeal.topicItemId#
            AND CityID =#topicDeal.cityId#
            AND DealGroupID =#topicDeal.dealGroupId#
    
        UPDATE
        TPE_TopicCity
        SET
            STATUS=#topicCity.status#
        WHERE TopicID = #topicCity.topicId#
            AND CityID=#topicCity.cityId#
    
         UPDATE
          TPE_Topic
        SET
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
            Author =#topicInfo.author#
        WHERE TopicID = #topicInfo.topicId#
    
        UPDATE
          TPE_Topic
        SET
          STATUS = #status#
        WHERE
          TopicID = #topicId#
    
        UPDATE
        TPE_TopicItem
        SET
            NAME =#topicItem.topicItemName#,
            TopicID =#topicItem.topicId#,
            TYPE =#topicItem.type#,
            STATUS =#topicItem.status#,
            BgColor =#topicItem.itemBgColor#,
            LabelColor =#topicItem.itemLabelColor#,
            ButtonColor =#topicItem.itemButtonColor#,
            BtnFontColor =#topicItem.buttonFontColor#
        WHERE ItemID =#topicItem.topicItemId#
        AND TopicID =#topicItem.topicId#
    
        UPDATE
          TPE_TopicItem
        SET
          STATUS = #status#
        WHERE
          TopicID = #topicId#
    
        UPDATE
          TPE_TopicDeal
        SET
            OrderNo =#topicDeal.orderNo#,
            STATUS =#topicDeal.status#
        WHERE ItemID =#topicDeal.topicItemId#
            AND CityID =#topicDeal.cityId#
            AND DealGroupID =#topicDeal.dealGroupId#
    
         UPDATE
          TPE_TopicDeal
        SET
            STATUS =#topicDeal.status#
        WHERE ItemID =#topicDeal.topicItemId#
            AND CityID =#topicDeal.cityId#
            AND DealGroupID =#topicDeal.dealGroupId#
    
        UPDATE
        TPE_TopicCity
        SET
            STATUS=#topicCity.status#
        WHERE TopicID = #topicCity.topicId#
            AND CityID=#topicCity.cityId#
    
		
		
        UPDATE
          DP_MyList
        SET
          IsBoutique = 0
        WHERE
          ListID = #listId#
    
    
        update TG_ReceiptNotifyJob
        set JobStatus = #jobStatus#,
        RetryCount = #retryCount#,
        ExecuteDetail = #executeDetail#,
        UpdateTime = current_timestamp
        where JobID = #jobId#
    
    UPDATE TPA_TemplateAssociation SET
    TemplateId=#entity.templateId#,
    CategoryId=#entity.categoryId#,
    TemplateAssociationId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE TemplateAssociationId=#entity.id#
    
        UpdateTime=UpdateTime
        WHERE DealGroupAdmissionID=#entity.id#;

    
        UpdateTime=UpdateTime
        WHERE DealGroupID=#entity.dealGroupId#;
    
        
		DELETE FROM TPDA_DealGroupAdmission WHERE DealGroupAdmissionID=#entity.id#
        
    
        WHERE DealGroupId=#entity.id#
    
      UPDATE TPDA_DealGroupExtend SET ApprovalStatus=#approvalStatus#,UpdateTime=#updateTime#
      WHERE DealGroupId=#dealGroupId#
    
        UPDATE TPDA_DealGroupExtend SET ProcessID=#processId#,UpdateTime=#updateTime#
        WHERE DealGroupId=#dealGroupId#
    
        WHERE ID = #entity.id#;
    
		UPDATE 
			TG_EventCity 
		SET
			Status=#eventCity.status#
		WHERE
			EventId=#eventCity.eventId#
			AND CityId=#eventCity.cityId#
	
		UPDATE 
			TG_EventPrize 
		SET
			PrizeName=#eventPrize.prizeName#,
			PrizeImage=#eventPrize.prizeImage#,
			PrizeDesc=#eventPrize.prizeDesc#,
			PrizeType=#eventPrize.prizeType#,
			Stocks=#eventPrize.stocks#,
			Odds=#eventPrize.odds#,
			Priority=#eventPrize.priority#,
			Consumption=#eventPrize.consumption#,
			SmsText=#eventPrize.smsText#,
			BeginDate=#eventPrize.beginDate#,
			EndDate=#eventPrize.endDate#,
			Status=#eventPrize.status#,
			EventId=#eventPrize.eventId#,
			Config=#eventPrize.config#
		WHERE
			PrizeId=#eventPrize.prizeId#
	
		
		UPDATE 
			TG_EventPrize 
		SET
			Consumption=(Consumption+1)
		WHERE
			PrizeId=#prizeId# AND (Stocks=0 OR Stocks>Consumption)
		
	
		update TG_NaviTagItemAttribute set ItemID=#itemId#,Name=#name#,Value=#value#  where NaviTagItemAttributeID=#id#
	
		UPDATE TG_OrderStatus SET Status = #newStatus#
		WHERE OrderID = #orderId# AND Status = #oldStatus# AND BizType = #bizType#
	
	   UPDATE TG_ParternerPromoDeal 
	   SET PromoParameter = #dealPromoEntity.promoParameter#,
	   BeginDate = #dealPromoEntity.beginDate#,EndDate = #dealPromoEntity.endDate#,
	   Status =#dealPromoEntity.status#,UpdateDate = NOW()
	   WHERE ID = #dealPromoEntity.id# 
	
	 
	   update ZS_User set UserNickName=#userNickName# where UserID=#userID#
		
	

	   update ZS_UserProfile set UserSex=#gender# where UserID=#userID#
		
	
		UPDATE DianPing.ZS_User 
		SET UserCity = #UserCity#
		WHERE UserID = #UserID#
	
        UPDATE TG_SubThirdUserOrder
        SET Status = #status#
        WHERE OrderID = #orderId# AND SubOrderID = #subOrderId# AND ChannelID = #channelId#
    
        UPDATE TG_SubThirdUserOrder
        SET IsVerified = #isVerified#
        WHERE OrderID = #orderId# AND SubOrderID = #subOrderId# AND ChannelID = #channelId#
    
        UPDATE TG_SubThirdUserOrder
        SET ReceiptID = #receiptId#, Status = #status#, IsVerified = #isVerified#
        WHERE OrderID = #orderId# AND SubOrderID = #subOrderId# AND ChannelID = #channelId#
    
        UPDATE TG_ThirdUserOrder
        SET RefundQuantity = RefundQuantity + #addQuantity#
        WHERE OrderID = #orderId#
    
        UPDATE TG_ThirdUserOrder
        SET VerifiedQuantity = VerifiedQuantity + #addQuantity#
        WHERE OrderID = #orderId#
    
        UPDATE TG_ThirdUserOrder
        SET UpdateDate = NOW()
        WHERE OrderID = #orderId#
    
		UPDATE 
			TG_Event_CityPromotionURL 
		SET
			PromotionURL=#eventPromotionConfig.promotionURL#
		WHERE
			CityID=#eventPromotionConfig.cityId#
	
		UPDATE 
			TG_EventUserLog 
		SET
			EventId=#eventUserLog.eventId#,
			UserId=#eventUserLog.userId#,
			UserNickName=#eventUserLog.userNickName#,
			MobileNo=#eventUserLog.mobileNo#,
			AddDate=#eventUserLog.addDate#,
			CityId=#eventUserLog.cityId#,
			PrizeId=#eventUserLog.prizeId#,
			IP=#eventUserLog.iP#,
			Memo=#eventUserLog.memo#,
			ReferId=#eventUserLog.referId#
		WHERE
			LogId=#eventUserLog.logId#
	
   	
	    UPDATE TGE_ThirdPartyCoupon 
		SET	
		Status = 1,UpdateDate = NOW()
		WHERE
		ThirdCouponID = #id# And Status = 0
   	
   	
		UPDATE TGHT_Contract SET
			ContractSerialNO = #contractSerialNO#,
			ContractNO = #contractNO#,
			CustomerName = #customerName#,
			ContractGlobalID = #contractGlobalID#,
			ShopName = #shopName#,
			CustomerID = #customerID#,
			DealSalesID = #dealSalesID#,
			DealSalesDepartmentID = #dealSalesDepartmentID#,
			LocalSalesDepartmentID = #localSalesDepartmentID#,
			LocalSalesID = #localSalesID#,
			CityID = #cityID#,
			UpdateDate = NOW()
		WHERE ContractID = #id#
	
		UPDATE TG_Deal SET
			DealGroupID = #dealGroupId#,
			DealTitle = #dealTitle#,
			DealName = #dealName#,
			DealSMSName = #dealSMSName#,
			DealPrice = #dealPrice#,
			MarketPrice = #marketPrice#,
			Cost = #cost#,
			DealSortID = #dealSortId#,
			MaxJoin =  IF(#maxJoin#=0,0,GREATEST(MaxJoin + #maxJoin# - OldMaxJoin,1)),
			OldMaxJoin = #maxJoin#,
			CurrentJoin = #currentJoin#,
			RemainDisplayPercent = #remainDisplayPercent#,
			FinishDate = #finishDate#,
			DeliverType = #deliverType#,
			ReceiptType = #receiptType#,
			ProvideInvoice = #provideInvoice#,
			DealShortTitle = #dealShortTitle#,
			DealStatus = #dealStatus#,
			IsDealPriceLargerThanCost = #isDealPriceLargerThanCost#,
			UpdateTime = NOW()
		WHERE DealID = #id#
	
    	 UPDATE TG_Deal  SET CurrentJoin = CurrentJoin + #quantity#    WHERE DealID = #id# 
	
    	 UPDATE TG_Deal  SET CurrentJoin = CurrentJoin - #quantity#, MaxJoin = MaxJoin - #maxjoinq#   WHERE DealID = #id# 
	
    	 UPDATE TG_Deal  SET FinishDate = NOW()    WHERE DealID = #id# 
	
		UPDATE TG_Deal
		SET FinishDate=#finishDate# WHERE DealID = #id#
	
	UPDATE TGHT_DealContract SET
		ContractID = #contractID#,
		DealID = #dealID#,
		Cost = #cost#,
		AdvancePercent = #advancePercent#,
		PayType = #payType#,
		Invoice = #invoice#,
		Memo = #memo#,
		UpdateDate = NOW()
	WHERE ContractID = #contractID# 
	AND DealID = #dealID#

		UPDATE TG_DealGroupDelayApply
		SET Status = #status#, UpdateTime = NOW()
		WHERE DealGroupDelayApplyID = #id#
	
		Update Mail_Task set status=#status# where taskId=#taskId#
	
		Update Mail_Task set status=#status# where status=0 and ExpireDate < NOW()
	
		Update EDM_TaskGroup Set Status=#status# where taskGroupId=#taskGroupId#
	
		Update EDM_TaskGroup Set Status=#status#,NextRunTime=#nextRunTime# where taskGroupId=#taskGroupId#
	
	
	    
		UPDATE TG_Deal SET finishdate='0001-01-01 00:00:00' WHERE
		DealID = #id# and currentjoin < maxjoin and 
		 finishdate > '2000-01-01'
		 
    
	    
		UPDATE TG_DealGroup SET finishdate='0001-01-01 00:00:00' WHERE
		DealGroupID = #id# and currentjoin < maxjoin and 
		 finishdate > '2000-01-01' and 
		 publishstatus=1 and 
		 begindate < NOW() and 
		 NOW() < enddate
		 
    
		UPDATE TG_ThirdReceiptApplyingQueue 
		SET UpdateDate = NOW(), Status = #value#, TryTimes = TryTimes + 1 
		WHERE QueueID = #queueId#
	 
	
        UPDATE temp_Tuan800_refund
        SET Status = #status#
        WHERE OrderID = #orderId#
    
		UPDATE TG_EventPromo
		SET Type=#promoCodeInfo.type#,Quota=#promoCodeInfo.quota#,Stock=#promoCodeInfo.stock#,CouponRuleID=#promoCodeInfo.couponRuleID#,
			BeginDate=#promoCodeInfo.beginDate#,EndDate=#promoCodeInfo.endDate#,UpdateDate=NOW(),
			Name=#promoCodeInfo.name#,Status=#promoCodeInfo.status#,Author=#promoCodeInfo.author#,Department=#promoCodeInfo.department#,
			FinancialItem=#promoCodeInfo.financialItem#
		WHERE ID=#promoCodeInfo.ID#
	
		UPDATE TG_EventPromoCond
        SET PromoID=#promoCond.promoID#, ValidOrderPlatform=#promoCond.validOrderPlatform#, ValidPayPlatform=#promoCond.validPayPlatform#, ValidPaymentChannel=#promoCond.validPaymentChannel#, 
        	MaxPerUser=#promoCond.maxPerUser#, MaxPerDeal=#promoCond.maxPerDeal#, LimitOrderAmount=#promoCond.limitOrderAmount#, CategoryList=#promoCond.categoryList#, ShopList=#promoCond.shopList#,
        	BuyLimitType=#promoCond.buyLimitType#, UseLimitType=#promoCond.useLimitType#, UserPropertyType=#promoCond.userPropertyType#, CityIds=#promoCond.cityList#
        WHERE ID=#promoCond.ID#
	
		UPDATE TG_EventPromo
		SET Status=#status#
		WHERE ID=#promoId#
	
	
		UPDATE TG_ModuleBlack SET
			ModuleKey = #data.moduleKey#,
			IntValue = #data.intValue#,
			TextValue = #data.textValue#,
			Status = #data.status#,
			UpdateDate = NOW()
		WHERE ID = #data.id#
	
    
		UPDATE TG_Cinema 
		SET CityID = #cinemaInfoEntity.cityId#, CinemaName = #cinemaInfoEntity.cinemaName#,
		HallCount = #cinemaInfoEntity.hallCount#, Photo = #cinemaInfoEntity.photo#,
		SpecialDes = #cinemaInfoEntity.specialDes#, Type = #cinemaInfoEntity.type#, 
		CinemaDesc =  #cinemaInfoEntity.desc#
		WHERE
		ShopID = #shopId#
	
		
			UPDATE MC_MemberCard m
			SET m.TopOrder = #topOrder#
			WHERE m.MemberCardID = #memberCardId# 
			
	
		
			UPDATE MC_MemberCard m
			SET m.PromoteOrder = #promoteOrder#
			WHERE m.MemberCardID = #memberCardId# 
			
	
		
			UPDATE MC_MemberCard m
			SET m.Title=#title#,m.SubTitle=#subTitle#,m.BgImageID=#bgImageId#,m.FontColor=#fontColor#,m.LogoID=#logoId#
			WHERE m.MemberCardID = #memberCardId#
			
	
	        WHERE m.MemberCardID = #memberCardId#
	
		
			UPDATE MC_MemberCardBgImage i
			SET i.Name=#name#,i.PicPath=#picPath#
			WHERE i.BgImageID = #bgImageID#
			
	
		
			UPDATE MC_MemberCard m
			SET m.CustomBgImageID = #customBgImageId#
			WHERE m.MemberCardID = #memberCardId#
			
	
		
			UPDATE MC_MemberCard m
			SET m.CustomBgImageID = 0
			WHERE m.MemberCardID = #memberCardId#
			
	
			WHERE r.RecomID = #mcl.recomID# 
	
		
			DELETE FROM MC_MemberCardLiteralRecomCity
			WHERE RecomID = #recomID# 
			
	
			UPDATE MC_MemberCard m
			SET m.CurrentScore = #score#
			WHERE m.MemberCardID = #memberCardId# 
	
			UPDATE MC_MemberCardScoreLog s
			SET s.Status = #status#, s.IsActive = #isActive#
			WHERE s.LogID = #logId# 
	
			UPDATE MC_MemberCardScoreDeductCancelApply ca
			SET ca.Status = #status#, ca.AuditAdminID = #auditAdminId#, ca.AuditContent = #auditContent#
			WHERE ca.ApplyID = #applyId# 
	
			UPDATE MC_MemberCard m
			SET m.LogoID = #logoId#, m.BgImageID = #bgImageId#, m.FontColor = #fontColor#
			WHERE m.MemberCardID = #memberCardId# 
	
		
			UPDATE MC_MemberCardApply SET Status = #status# 
			WHERE ShopID = #shopId#
			
	
		UPDATE MC_MemberCardCompanyConfirmInfo
		SET CitySelected = #citySelected#,
			CrmID = #crmID#,
			CardType = #cardType#,
			AuthAdminID = #authAdminID#,
			AuthAdminName = #authAdminName#
        	WHERE ID = #companyConfirmInfoID#
	
		UPDATE MC_MemberCardShopConfirmInfo 
		SET ShopName = #shopName#,
			ShopManager = #shopManager#,
			ShopManagerTel = #shopManagerTel#,
			ProductSelected = #productSelected#,
			OtherMemberCard = #otherMemberCard#,
			Deleted = 0,
			AuthAdminID = #authAdminID#,
			AuthAdminName = #authAdminName#
		WHERE CrmID = #crmID# 
			  AND CompanyConfirmInfoID = #companyConfirmInfoID#
		      AND ShopID = #shopID#
	
		UPDATE MC_MemberCardCompanyConfirmInfo
		SET Status = #status#
        	WHERE CrmID = #crmID#
	
	    UPDATE TA_ShopSeller
	    SET ShopPhone=#shopPhone#,OwnerPhone=#ownerPhone#,ShopContactName=#shopContactName#
	    WHERE ShopID=#shopId#
    
	    UPDATE TA_ShopSeller
	    SET ShopName=#shopName#
	    WHERE ShopID=#shopId#
    
		UPDATE
		BC_ShopAccount
		SET ShopAccount = #shopAccountId#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE BC_ShopAccount
		SET Password = #password#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
    
    			and sa.AccountType=#accountType# 
    			and sa.ShopAccountId = sas.ShopAccountId 
    		join BC_ShopAccountModule sam on sam.Module=#module# 
    			and sa.ShopAccountId=sam.ShopAccountId
    	) t 
    	set sa.Password= #password#, sa.UpdateTime=#updateTime#
    	where sa.`ShopAccountId` =t.`ShopAccountId`;
    
		UPDATE BC_ShopAccount
		SET ContactMobileNO = #mobile#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
		UPDATE
		BC_ShopAccount
		SET Status = -1,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
	
		UPDATE BC_ShopAccount
		SET ContactName = #contactName#,UpdateTime=#updateTime#
		Where
		ShopAccountId=#shopAccountId#
    
    
		UPDATE TG_ReminderSMSLog SET
			UpdateDate = NOW(),
			UserID = #data.userId#,
			ShopGroupID = #data.shopGroupId#,
			DealGroupID = #data.dealGroupId#,
			MobileNo = #data.mobileNo#
		WHERE ReminderSMSLogID = #data.id#
	
    
	    UPDATE TG_EventSendDeal 
		SET
		STATUS = #type#,  
		SuccessTime = NOW()		
		WHERE
		ID = #id#
    
	
		UPDATE TG_Hotel
		SET
		TPID=#hotelEntity.tpId#,
		TPHotelID=#hotelEntity.tpHotelId#,
		HotelName=#hotelEntity.hotelName#,
		NaviCategoryID=#hotelEntity.naviCategoryId#,
		CityID=#hotelEntity.cityId#,
		CityName=#hotelEntity.cityName#,
		Address=#hotelEntity.address#,
		Tel=#hotelEntity.tel#,
		HotelDes=#hotelEntity.hotelDes#,
		UpdateDate=NOW()
		WHERE
		ID=#hotelEntity.id#
	
        
        UPDATE TG_HotelDealRecommendForHotelShop
        SET
        DealGroupIds = #dealGroupIds#,
        UpdateDate = Now()
        WHERE
        Id = #id#;
        
    
    	UPDATE TG_HotelPrice
    	Set Price = #price#, UpdateDate = now()
    	WHERE ID = #id#
    
    	UPDATE TG_HotelPrice
    	Set Price = #entity.price#, MarketPrice = #entity.marketPrice#, CostPrice = #entity.costPrice#, UpdateDate = now()
    	WHERE ID = #id#
    
    	UPDATE TG_HotelForReservation
    	Set Status = #entity.status#, UpdateDate = now()
    	WHERE TPID = #entity.tpId# AND TPHotelID = #entity.tpHotelId# AND RoomType = #entity.roomType#
    
		UPDATE TG_ThirdVerifyLog
		SET Time = #time#,Result=#result#,ResponseContent=#responseContent#,UpdateDate = Now(),Status = #status# 
		WHERE LogID = #logId#
	
        UPDATE TG_Deliver d,TGHT_DeliverImportPool p
        SET d.DeliverCompanyID = #companyId#,
        d.DeliverSerialNO = p.Express,
        d.Status = 2,d.UpdateDate = Now(),
        p.Status = 1
        WHERE
        p.BatchID = #batchId#
        AND
        d.OrderID = p.OrderID
        AND d.Status = 1
        AND d.ReceiptCheck = 0
    
        update TGHT_DeliverAdminDeal d
        set d.FirstDeliverDate = #firstDeliverDate#
        where d.DealGroupID = #dealGroupId# and d.Status in (0,1);
    
        update TGHT_DeliverAdminDeal d
        set d.DeliverOverDate = #deliverOverDate#,d.Status = 1
        where d.DealGroupID = #dealGroupId# and d.Status in (0,1);
    
      update TG_DealAutoRefund set Status = #toStatus#, UpdateTime = CURRENT_TIMESTAMP
      where DealID = #dealId# and Status = #fromStatus#
    
    
    update TG_DealAutoRefund dar
      set dar.RefundOrdersNum = dar.RefundOrdersNum + #refundOrdersNum#, dar.RefundReceiptsNum = dar.RefundReceiptsNum + #refundReceiptsNum#
      where dar.DealID = #dealId#;
    
        update TG_DealAutoRefund dar
        set dar.RefundOrdersNum = dar.RefundOrdersNum + #refundOrdersNum#,
        dar.RefundReceiptsNum = dar.RefundReceiptsNum + #refundReceiptsNum#,
        Status = #toStatus#,
        UpdateTime = CURRENT_TIMESTAMP
        where dar.DealID = #dealId# and Status = #fromStatus#;
    
		UPDATE DianPingMobile.CI_CommonToken 
		SET TokenStatus = #TokenStatus#
		WHERE ID = #ID#
	
		UPDATE DianPingMobile.CI_CommonToken 
		SET DeviceID = #DeviceID#
		WHERE ID = #ID#
	
        UPDATE TG_CPSSettleUnit
        SET STATUS = #status#,UPDATETIME = #updateTime#
        WHERE UNIID = #unitId#
    
        UPDATE TG_CPSSettleUnit
        SET STATUS = #status#
        WHERE TYPE = #type# and BUSID = #busId#
    
		UPDATE 
			EBIZ_ApplyReason
		SET
			ReasonName=#applyReason.reasonName#,
			Tip=#applyReason.tip#,
			Priority=#applyReason.priority#,
			Type=#applyReason.type#,
			Status=#applyReason.status#,
			AddDate=#applyReason.addDate#,
			UpdateDate=#applyReason.updateDate#
		WHERE
			ReasonId=#applyReason.reasonId#
	
    
    	UPDATE TG_Event_ShanTuan
    	SET DealGroupTitle=#shantuan.dealGroupTitle#, BeginDate=#shantuan.beginDate#, EndDate=#shantuan.endDate#, DealGroupID=#shantuan.dealGroupID#, 
        IsReplaceTitle=#shantuan.isReplaceTitle#, AddDate=#shantuan.addDate#,UpdateDate=#shantuan.updateDate#, Status=#shantuan.status#
        WHERE ShanID=#shantuan.shanID#
    
    
	
		UPDATE TG_Event_ShanTuan
		SET Status=#status#
		WHERE ShanID=#shanID#
	
    
    
    	UPDATE TG_Event_ShanTuanCity
    	SET ShanID=#shantuanCity.shanID#, CityID=#shantuanCity.cityID#, AddDate=#shantuanCity.addDate#,
    	UpdateDate=#shantuanCity.updateDate#, Status=#shantuanCity.status#
        WHERE ID=#shantuanCity.id#
    
    
	
		UPDATE TG_Event_ShanTuanCity
		SET Status=#status#
		WHERE ID=#id#
	
    
		UPDATE
			TG_OpUser
		SET
			Status=0
		WHERE 
			UserName=#username# 
	
		UPDATE
			TG_OpUser
		SET
			UserName=#user.username#, NickName=#user.nickname#, Department=#user.department#,
			Email=#user.email#, PhoneNumber=#user.mobile#, UserNumber=#user.userNo#, Status=#user.status#, UserType=#user.type#
		WHERE
			UserName=#user.username# 
	
	
		UPDATE
			TG_EventDeal
		SET 
			Status=#status#
		WHERE
			EventID=#eventId#
	
		UPDATE
			TG_EventDeal
		SET 
			Status=#status#
		WHERE
			EventID=#eventId# AND Status=0
	
		UPDATE TG_ShopGroupRecom SET
			ShopGroupID = #data.shopGroupId#,
			ShopGroupName = #data.shopGroupName#,
			CityID = #data.cityId#,
			Priority = #data.priority#,
			Status = #data.status#,
			UpdateDate = NOW()
		WHERE ID = #data.id#
	
		UPDATE
			Mon_UserBlackList
		SET
			IsReleased = 1,
			ReleasedDate = now()
		WHERE
			UserID = #userId#
			AND IsReleased = 0
  			AND Type in (6,7)

	
    
		UPDATE
			TG_MemberBehaviorMsg
		SET
			Status = #status#, 
			Memo = #memo#
		WHERE
			MemberBehaviorMsgID = #memberBehaviorMsgID#
    
        DealShopID=DealShopID
        WHERE DealShopID=#entity.id#
    
        


		DELETE FROM TPDA_DealShop WHERE DealShopID=#entity.id#


        
    
		UPDATE TGP_TEMPLATE_ENTRY
		SET
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplate.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		IS_MANDATORY=#entity.isMandatory#,
		AREA_TYPE_ID=#entity.areaTypeId#,
		SEQUENCE=#entity.sequence#
		WHERE
		TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='CompositableTemplate'
	
		UPDATE TGP_DOCUMENT_TEMPLATE
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VERSION_ID=#entity.versionId#,
		NAME=#entity.name#,
		IS_ACTIVE=#entity.isActive#,
		IS_OFFLINE=#entity.isOffline#
		WHERE
		DOCUMENT_TEMPLATE_ID=#entity.id#
	
		UPDATE
			TGP_DEAL_GROUP_AE
		SET
			DEAL_GROUP_ID=#entity.dealGroupId#,
			AE_ID=#entity.aeId#,
			AE_NAME=#entity.aeName#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			VERSION_ID=#entity.versionId#,
			AE_PHONE=#entity.aePhone#,
			AE_EMAIL=#entity.aeEmail#
		WHERE
			ID=#entity.id#
	
		UPDATE
			TGP_DEAL_GROUP_MAINTAINER
		SET
			DEAL_GROUP_ID=#entity.dealGroupId#,
			MAINTAINER_ID=#entity.maintainerId#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			MAINTAINER_NAME=#entity.maintainerName#,
			TRAIN_STATUS=#entity.trainStatus#,
			RECEIVE_TIME=#entity.receiveTime#,
			CHANGE_STATUS_TIME=#entity.changeStatusTime#,
			VERSION_ID=#entity.versionId#,
			CONTACT_MP=#entity.contactMP#,
			CONTACT_EMAIL=#entity.contactEmail#,
			IS_SENDED=#entity.isSended#
		WHERE
			ID=#entity.id#
	
		UPDATE 
			TGP_DEAL_GROUP_MAINTAINER 
		SET 
			MAINTAINER_ID=#maintainerId#,
			MAINTAINER_NAME=#maintainerName#,
			RECEIVE_TIME=NOW()
		WHERE 
			DEAL_GROUP_ID=#dealGroupId#;
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='ImageTextComponent'
	
		UPDATE
		TGP_VISUAL_LIST_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VISUAL_COMPONENT_ID=#entity.visualComponent.id#,
		SEQ=#entity.sequence#,
		PICTURE_URL=#entity.pictureUrl#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ImageTextItem'
	 
	 
		DELETE FROM TGP_PRODUCT WHERE PRODUCT_ID=#entity.id#
	
	
        UPDATE TGP_RESOURCE_ROLE_AUTHORITY_CONFIG
            SET 
                LAST_UPDATE_TIME = #entity.lastUpdateTime#,
                LAST_UPDATOR_ID = #entity.lastUpdatorId#,
                VERSION_ID = #entity.versionId#,
                POWER_CODE = #entity.powerCode#,
                SOURCE_SYSTEM = #entity.sourceSystem#,
                ROLE_ID = #entity.roleId#
            WHERE
                RESOURCE_ROLE_AUTHORITY_CONFIG_ID = #entity.id# ;
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		RICH_TEXT_CONTENT=#entity.content#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='RichTextComponent'
	
		UPDATE
		TGP_VISUAL_LIST_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VISUAL_COMPONENT_ID=#entity.visualComponent.id#,
		SEQ=#entity.sequence#,
		CONTENT=#entity.content#,
		IS_READ_ONLY=#entity.isReadOnly#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='TextItem'
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET

		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextListComponent'
	
    UPDATE TPA_AttributeOption SET
    AttributeId=#entity.attributeId#,
    AttributeValue=#entity.attributeValue#,
    Sequence=#entity.sequence#,
    AttributeOptionId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE AttributeOptionId=#entity.id#
    
        UpdateTime=UpdateTime
        WHERE DealGroupBankAccountID=#entity.id#
    
            DealGroupComponentID=DealGroupComponentID
        WHERE DealGroupComponentID=#entity.id#
    
        

		DELETE FROM TPDA_DealGroupComponent WHERE DealGroupComponentID=#entity.id#

        
    
	    UPDATE TA_Operation
	    SET status=#state#
	    WHERE shopid=#shopid#
    
		UPDATE TA_Operation
		SET dishfilepath = #dishfilepath#,dishfilename = #dishfilename#,dishpicname = #dishpicname#, shopinfoname = #shopinfoname#
		WHERE shopid = #shopid#
	
	    UPDATE TA_Operation
	    SET onlinefailmsg= #onlinefailmsg#
	    WHERE shopid=#shopid#
    
	    UPDATE TA_Operation
	    SET first_online_time=#firstOnlineTime#
	    WHERE shopid=#shopid#
	
	    UPDATE TA_OperationStatistic
	    SET ordertotalcount=#orderTotalCount#,activeconfirmedcount=#activeConfirmedCount#,activedeclinedcount=#activeDeclinedCount#,dialreachdeclinedcount=#dialReachDeclinedCount#,dialcanceldeclinedcount=#dialCancelDeclinedCount#,unprocessedcount=#unprocessedCount#,shopage=#shopAge#
	    WHERE date=#date# AND shopid=#shopId#
	
	    UPDATE TA_OperationContract
	    SET firstpartytype=#firstpartytype#, companyname=#companyname#, idnumber=#idnumber#, contactpeople=#contactpeople#, contactphone=#contactphone#, effectivedate=#effectivedate#, commission=#commission#, freeorders=#freeorders#, collectiontype=#collectiontype#, bankaccountname=#bankaccountname#, bankaccount=#bankaccount#, bankprovince=#bankprovince#, bankcity=#bankcity#, bankhead=#bankhead#, bankbranch=#bankbranch#
	    WHERE id=#id#
	
		UPDATE TA_OperationContract
		SET valid=#valid#
		WHERE id=#id#
	
		UPDATE TA_OperationContract
		SET contractfilepath=#contractfilepath#, contractfilename=#contractfilename#
		WHERE id=#id#
	
		update TA_ContractMappingLog
		set freeamount = freeamount+1
		where id = #id#
	
		update TA_ContractMappingLog
		set freeamount = freeamount-1
		where id = #id#
	
        update TG_TravelReservation 
        set RefReceiptID=#entity.refReceiptId#,
        BookName = #entity.bookName#,
        TravelerName = #entity.travelerName#,
        TourDate = #entity.tourDate#,
        IdentityID = #entity.identityId#,
        BookMobileNo = #entity.bookMobileNo#,
        TravelerMobileNo = #entity.travelerMobileNo#,
        Memo = #entity.memo#,
        RefReceiptCode=#entity.refReceiptCode#,
        Status =#entity.status#,
        UpdateDate = now()              
        Where ReceiptID = #entity.receiptId#
    
		
	
        
        UPDATE TE_AdChannel
        SET Name=#adChannel.name#, Owner=#adChannel.owner#, Author=#adChannel.author#, UpdateTime=NOW()
        WHERE ID=#adChannel.id#
    
    
        
        UPDATE TE_AdChannel
        SET Status=#status#
        WHERE ID=#id#
    
    
        
        UPDATE TE_AdContainer
        SET Name=#adContainer.name#, ChannelID=#adContainer.channelID#, Author=#adContainer.author#, UpdateTime=NOW()
        WHERE ID=#adContainer.id#
    
    
        
        UPDATE TE_AdContainer
        SET Status=#status#
        WHERE ID=#id#
    
    
        
        UPDATE TE_AdDetail
        SET Title=#adDetail.title#, SubTitle=#adDetail.subTitle#, Thumb=#adDetail.thumb#, ExtraContent=#adDetail.extraContent#, Link=#adDetail.link#, `Desc`=#adDetail.desc#, Type=#adDetail.type#, resourceId=#adDetail.resourceId#, UpdateTime=NOW()
        WHERE ID=#adDetail.id#
    
    
        
        UPDATE TE_AdDetail
        SET Status=#status#
        WHERE ID=#id#
    
    
        
        UPDATE TE_AdDimension
        SET PublishID=#adDimension.publishID#, CityID=#adDimension.cityID#, Version=#adDimension.version#, OrderNo=#adDimension.orderNo#, AdvertisementID=#adDimension.advertisementID#, Type=#adDimension.type#, UpdateTime=NOW()
        WHERE ID=#adDimension.id#
    
    
        
        UPDATE TE_AdDimension
        SET PublishID=#adDimension.publishID#, CityID=#adDimension.cityID#, Version=#adDimension.version#, OrderNo=#adDimension.orderNo#, AdvertisementID=#adDimension.advertisementID#, Type=#adDimension.type#, Status = #adDimension.status#
        WHERE ID=#adDimension.id#
    
     
    
        
        UPDATE TE_AdPosition
        SET Status=#status#
        WHERE ID=#id#
    
    
        
        UPDATE TE_AdPublish
        SET Position=#adPublish.position#, Title=#adPublish.title#, Author=#adPublish.author#, Auditor=#adPublish.auditor#, BeginDate=#adPublish.beginDate#, EndDate=#adPublish.endDate#, AuditTime=#adPublish.auditTime#, UpdateTime=NOW()
        WHERE ID=#adPublish.id#
    
    
        
        UPDATE TE_AdPublish SET Status = #status# WHERE ID = #publishId#
    
    
        

		DELETE FROM TPDA_DealGroupEditor WHERE DealGroupEditorID=#entity.id#

        
    
        UPDATE TS_City
        SET Status = #status# ,UpdateTime = NOW()
        WHERE ID = #id#
    
        UPDATE TS_City
        SET Status = #status# ,UpdateTime = NOW(),CityName = #cityName#
        WHERE ID = #id#
    
        UPDATE TS_ReturnQuota
        SET Status = #status#,
            UpdateTime = NOW()
        WHERE id = #id#;
    ;
    
		UPDATE TS_TotalPool 
		SET Status = #status#, UpdateTime = NOW() 
		WHERE Id = #id#
	
 		update TA_ShopDP set valid = #status# where id =#shopId# and type=#type#
 	
		UPDATE TA_ShopThirdParty
		SET thirdpartyid=#thirdpartyid#,
		thirdpartyshopid=#thirdpartyshopid#,
		`interval` = #interval#,
		mindelivery = #mindelivery#,
		latestdelivery = #latestdelivery#,
		servtimejson = #servtimejson#,
		minfee = #minfee#,
		discount = #discount#,
		status = #status#,
		mindeliverfee = #mindeliverfee#,
		distance = #distance#,
		picture = #picture#,
		geojson = #geojson#,
		tips = #tips#
		WHERE id = #shopKey#
	
		UPDATE TA_MiPush
		SET shopid=#shopId#
		WHERE dpid=#dpId#
     
        UPDATE TA_IosPush
        SET shopid=#shopId#
        WHERE dpid=#dpId#
     
         UPDATE TA_ShopDP
         SET address=#address#,
         phone=#phone#,
         lat=#lat#,
         lng=#lng#,
         valid=#valid#,
         type=#type#,
         pickup=#pickup#,
         delivery=#delivery#
         WHERE id=#shopId#
     
     	 UPDATE TA_ShopDP
         SET name=#shopName#
         WHERE id=#shopId#
     
         UPDATE TA_ShopDP
         SET address=#address#,
         phone=#phone#,
         pictureurl=#pictureurl#,
         lat=#lat#,
         lng=#lng#,
         valid=#valid#,
         type=#type#,
         pickup=#pickup#,
         delivery=#delivery#,
         introduction=#introduction#
         WHERE id=#shopId#
     
         UPDATE TA_ShopDP
         SET valid=#valid#
         WHERE id=#shopid#
      
         UPDATE TA_ShopDP
         SET pictureurl=#url#, introduction=#introduction#
         WHERE id=#shopid#
     
		UPDATE TA_ShopMapping
		SET thirdpartyid=#thirdpartyid#, thirdpartyshopid=#thirdpartyshopid#
		WHERE dpshopid=#dpshopid#
     
	    UPDATE TA_ShopThirdParty
	    SET busyswitch=#busyswitch#, busyswitchontime=CURRENT_TIMESTAMP
	    WHERE thirdpartyid=#thirdpartyid# AND thirdpartyshopid=#thirdpartyshopid#
	
		UPDATE TA_ShopThirdParty
		SET onlinepayment=#onlinepayment#
		WHERE thirdpartyid=#thirdpartyid# AND thirdpartyshopid=#thirdpartyshopid#
	
		UPDATE TA_ShopThirdParty
		SET requirecontract=#requirecontract#
		WHERE thirdpartyid=#thirdpartyid# AND thirdpartyshopid=#thirdpartyshopid#
	
		UPDATE
            EBIZ_RefundDeliver
		SET
			RefundDeliverId=#refundDeliver.refundDeliverId#,
			ApplyId=#refundDeliver.applyId#,
			DeliverNo=#refundDeliver.deliverNo#,
			DeliverCompanyId=#refundDeliver.deliverCompanyId#,
			CompanyName=#refundDeliver.companyName#,
            Memo=#refundDeliver.memo#,
			AddDate=#refundDeliver.addDate#,
			UpdateDate=#refundDeliver.updateDate#,
            Address=#refundDeliver.address#
		WHERE
            RefundDeliverId=#refundDeliver.refundDeliverId#
	
    UPDATE TPA_TemplateAttributeAssn SET
    AttributeId=#entity.attributeId#,
    AttributeType=#entity.attributeType#,
    TemplateId=#entity.templateId#,
    Sequence=#entity.sequence#,
    IsRequired=#entity.isRequired#,
    ParentAttributeId=#entity.parentAttributeId#,
    ParentAttributeOptionId=#entity.parentAttributeOptionId#,
    TemplateAttributeAssnId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE TemplateAttributeAssnId=#entity.id#
    
        AND Status = 0
    
        AND Status = 1
    
        AND Status = 1
    
        UPDATE TG_Receipt 
		SET SerialNumber = #serialNumber#  
		WHERE ReceiptID = #receiptId# 
    	
		UPDATE TG_Receipt,TG_ReceiptResetPool 
        SET TG_Receipt.SerialNumber=#serialNumber#, TG_ReceiptResetPool.NewSerialNumber= #serialNumber#,TG_ReceiptResetPool.Status = 1,TG_ReceiptResetPool.updateTime = NOW()
        WHERE	TG_ReceiptResetPool.PoolID = #poolId# AND TG_Receipt.ReceiptID= #receiptId#
	
	     UPDATE TG_ReceiptResetPool set status=2, updateTime = NOW() where PoolID = #poolId#;  
	
		UPDATE TG_ThirdReceiptCode 
		SET TCode = #TCode#, SerialNumber = #serialNumber#, UpdateDate =NOW(), 
		ThirdPartyOrderID = #thirdPartyOrderId#, SeqID = #seqId# 
        WHERE DianpingReceiptID = #dianpingReceiptId# 
	
		UPDATE
		TG_UserSetting
		SET
		RemindSmsType=#userSetting.remindSmsType#,
		RemindSmsStatus=#userSetting.remindSmsStatus#,
		RemindEmailStatus=#userSetting.remindEmailStatus#,
		UpdateDate=#userSetting.updateDate#
		WHERE
		UserId=#userSetting.userId#
	
		WHERE MovieReservation_ID = #id# AND UserID = #userId#
	
		WHERE MovieReservation_ID = #id# AND UserID = #userId#
	
		update TG_MovieReservation A
		set A.Status = #status#,
		A.UpdateDate = now()
		where A.MovieReservation_ID = #id#
	
        UPDATE
        TG_Deal SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
        DealID = #dealID# AND (MaxJoin - #reduceMaxJoin#) > 0
    
		UPDATE
		TG_DealGroupRemind
		SET
		Status=0
		WHERE
		RemindId in ($remindIds$) AND
		UserId=#userId#
	
		UPDATE
		TG_DealGroupRemind
		SET
		Status=0
		WHERE
		ShopGroupId in ($shopGroupIds$) AND
		UserId=#userId#
	
		UPDATE TG_DealScore SET
			RateScore = #data.rateScore#,
			RateCount = #data.rateCount#,
			PublicCount = #data.publicCount#,
			RateCount10 = #data.rateCount10#,
			RateCount20 = #data.rateCount20#,
			RateCount30 = #data.rateCount30#,
			RateCount40 = #data.rateCount40#,
			RateCount50 = #data.rateCount50#,
			UpdateDate = NOW(),
			AgainCount = #data.againCount#
		WHERE DealGroupID = #data.dealGroupId#
	
					
		UPDATE TG_DealUserTip 
			SET
			ADDDATE = NOW()			
			WHERE
			DealGroupID = #dealGroupId# AND UserID = #userId# AND TipType = #tipType#
		
  
  	UPDATE TG_ReceiptControl SET  OrderID = #orderID# WHERE  ControlID = 1;
  
    
  UPDATE TG_UserAccount SET Credit = Credit + #amount#, Balance = Balance + #amount#, LastDate = NOW() WHERE UserID = #userID#
  
    
   UPDATE TG_UserAccount SET Debit = Debit + #amount#, Balance = Balance - #amount#,LastDate = NOW() WHERE UserID =  #userID#
  
          update TG_Coupon C
          set C.UsedStatus= #usedStatus#,
		  C.UsedDate = now()
		  where C.CouponID=#couponID#
  	
    update TG_DeliverAddress
    set UserID = #userID:INTEGER#,
      Consignee = #consignee:VARCHAR#,
      Address = #address:VARCHAR#,
      PostCode = #postCode:VARCHAR#,
      PhoneNo = #phoneNo:VARCHAR#,
      IsDefault = #isDefault:TINYINT#,
      Status = #status:TINYINT#,
      UpdateDate = #updateDate:TIMESTAMP#,
      Province = #province:INTEGER#,
      City = #city:INTEGER#,
      District = #district:INTEGER#,
      DeliverTime = #deliverTime:TINYINT#,
      InvoiceTitle = #invoiceTitle:VARCHAR#,
      NeedInvoice = #needInvoice:TINYINT#,
      Memo = #memo:VARCHAR#
    where DeliverAddressID = #deliverAddressID:INTEGER#
  
    update TG_DeliverAddress
    set IsDefault = 0    
    where UserID = #userID:INTEGER#
  
    update TG_DeliverAddress
    set Status = #status#    
    where UserID = #userId# AND DeliverAddressID=#addressId#
  
    update TG_DeliverAddress
    set IsDefault = 1    
    where UserID = #userId# AND DeliverAddressID=#addressId#
  
		UPDATE TGM_UserBadgeReward 
		SET IsUsed = TRUE, UsedTime = NOW() 
		WHERE UserID = #UserID# AND RewardID = #RewardID#
	
		INSERT INTO TGM_UserBadgeReward 
		 (BadgeID, UserID, RewardID) 
		VALUES 
		 (#BadgeID#, #UserID#, #RewardID#)
	
        UPDATE TG_MissedThirdOrder
        SET Status = #status#
        WHERE OrderID = #orderId#
    
		UPDATE 
			TG_EventPrize 
		SET
			PrizeName=#eventPrize.prizeName#,
			PrizeImage=#eventPrize.prizeImage#,
			PrizeDesc=#eventPrize.prizeDesc#,
			PrizeType=#eventPrize.prizeType#,
			Stocks=#eventPrize.stocks#,
			Odds=#eventPrize.odds#,
			Priority=#eventPrize.priority#,
			Consumption=#eventPrize.consumption#,
			SmsText=#eventPrize.smsText#,
			BeginDate=#eventPrize.beginDate#,
			EndDate=#eventPrize.endDate#,
			Status=#eventPrize.status#,
			EventId=#eventPrize.eventId#,
			Config=#eventPrize.config#
		WHERE
			PrizeId=#eventPrize.prizeId#
	
		
		UPDATE 
			TG_EventPrize 
		SET
			Consumption=(Consumption+1)
		WHERE
			PrizeId=#prizeId# AND (Stocks=0 OR Stocks>Consumption)
		
	
		
			UPDATE 
				TG_EventPrizeUser force index (idx_userid,idx_mobile)
			SET 
				CurrentJoin=CurrentJoin+1 
			WHERE 
				EventID=#prizeUser.eventId# AND (UserID=#prizeUser.userId# OR UserMobile = #prizeUser.mobile#) AND CurrentJoin<MaxJoin AND MaxJoin>0 AND Entrance=#prizeUser.entrance# AND Period=#prizeUser.period#
		
	
		
			UPDATE 
				TG_EventUserPrize force index (idx_userid,idx_mobile)
			SET 
				Value=Value+#log.prizeValue#, Amount=Amount+1
			WHERE 
				EventID=#log.eventId# AND (UserID=#log.userId# OR UserMobile = #log.userMobile#) AND Amount<#maxValue# AND PrizedDate=#log.period#
		
	
		UPDATE 
			TG_EventPrizeUser force index (idx_userid,idx_mobile)
		SET 
			MaxJoin = #prizeUser.maxJoin# 
		WHERE
			EventID=#prizeUser.eventId# AND (UserID=#prizeUser.userId# OR UserMobile=#prizeUser.mobile#) 
			AND Period=#prizeUser.period# AND Entrance=#prizeUser.entrance#
	
	
		UPDATE 
			TGE_EventPriceGroup
		SET 
			`ConsumptionAmout` = `ConsumptionAmout` + #consumptionValue#  ,UpdateDate = now()
		WHERE
			`PriceGroupID` = #prizeGroupId# AND `ConsumptionAmout` + #consumptionValue# <= `TotalAmout`
	
	
	
		UPDATE 
			TGE_EventPriceGroup
		SET 
			`ConsumptionAmout` = `ConsumptionAmout` + #consumptionValue#,UpdateDate = now()
		WHERE
			`PriceGroupID` = #prizeGroupId# AND `ConsumptionAmout` + #consumptionValue# <= #currentMax#
	
	
		UPDATE 
			TGE_LotteryInfo 
		SET
			LotteryNumberCount=LotteryNumberCount+#luckyNumbersCnt#,
			MaxLotteryNumber=MaxLotteryNumber+#luckyNumbersCnt#
		WHERE
			DealGroupID=#dealGroupID#
	
		UPDATE TGE_SecondOrder SET
			SecondPrizeID = #data.secondPrizeId#,
			UserID = #data.userId#,
			Status = #data.status#,
			Quantity = #data.quantity#,
			IpAddress = #data.ipAddress#,
			MobileNo = #data.mobileNo#,
			HippoId = #data.hippoId#
		WHERE SecondOrderID = #data.id#
	
	UPDATE TG_PreviewPool 
	SET Status = 1
	WHERE PreviewID = #id# AND Status = 0
  
	UPDATE TG_PreviewPool 
	SET UpdateTime = NOW(),Status = 0
	WHERE PreviewID = #id#
  
  	UPDATE TG_Receipt set mobileNo=#mobileNo# WHERE receiptID=#receiptID#
  
  	UPDATE TG_Receipt set refundID=#refundId# WHERE receiptID=#receiptId#
  
    update TG_Receipt
    set Status = #status:INTEGER#,
      LastDate = #lastDate:TIMESTAMP#,
      VendorShopID = #vendorShopID:VARCHAR#    
    where ReceiptID = #receiptID:INTEGER#
  
    update TG_Receipt
    set Status = #status:INTEGER#,LastDate = now()      
    where ReceiptID = #receiptID:INTEGER#
  
	UPDATE TGHT_SerialNoImportBatch SET
	DealID = #dealID#,
	LoginID = #loginID#,
	Status = #status#,
	AddDate = #addDate#
	WHERE BatchID = #batchID#

    	update Mail_SectionSend 
    	set 
    		Type=#type#, AddNum=#addNum#, NextIndex=#nextIndex#, Status=#status#
    	where 
    		SectionId=#sectionId#
    
    
		update 
			TG_Event_TopicItemCity
		set
			DealGroupIDs=#dealGroupIDS#
		where
			TopicItemID=#topicItemID# and CityID=#cityId#
			
	
		update 
			TG_Event_TopicItem
		set
			TopicItemName=#topicItemName#,
			DisplayCount=#displayCount#,
			Priority=#priority#
		where
			TopicItemID=#topicItemID#
	
		UPDATE TG_DealShopInfo SET
			ShopGroupID = #data.shopGroupId#,
			CityID = #data.cityId#
		WHERE ShopID=#data.shopId#
	
        update TG_HotelRecommendScore h
        set h.Score=#score#
        where h.DealGroupID=#dealGroupId#;
    
        update TG_HotelRecommendScore h
        set h.Score=#score#
        where h.CityID=#cityId#
        and h.DealGroupID=#dealGroupId# ;
    
        update TG_HotelRecommendScore h
        set h.Score=#score#
        where h.CityID=#cityId# ;
    
        update TG_HotelRecommendScore h
        SET h.Score=h.SaleScore
        where h.CityID=#cityId# ;
    
        UPDATE TG_APIStatic SET status = #status#, UpdateDate = NOW() WHERE CityID = #cityID#
    
        UPDATE TG_APIStatic SET StaticFiles = #files#, status = 0, UpdateDate = NOW()
        WHERE CityID = #cityID# AND APIType = #type#
    
		UPDATE 
			GPA_OnlineActivity OA
		SET 
			OA.BeginTime=#onlineActivity.beginTime#,
			OA.EndTime=#onlineActivity.endTime#,
			OA.Title=#onlineActivity.title#,
			OA.ContentType=#onlineActivity.contentType#,
			OA.ContentFilterField=#onlineActivity.contentFilterField#,
			OA.CreateUserID=#onlineActivity.createUserId#,
			OA.CreateAdminID=#onlineActivity.createAdminId#,
			OA.Body=#onlineActivity.body#,
			OA.Info=#onlineActivity.info#,
			OA.BannerPicUrl=#onlineActivity.bannerPicUrl#,
			OA.CssTemplate=#onlineActivity.cssTemplate#,
			OA.UpdateTime=NOW()
		WHERE 
			OA.OnlineActivityID = #onlineActivity.onlineActivityId#
	
		UPDATE
			GPA_OnlineActivity
		SET
			JoinContentCount = JoinContentCount + 1
		WHERE
			OnlineActivityID = #onlineActivityId#
	
		UPDATE
			GPA_OnlineActivity
		SET
			JoinUserCount = JoinUserCount + 1
		WHERE
			OnlineActivityID = #onlineActivityId#
	
		UPDATE 
			GPA_OnlineActivity 
		SET 
			STATUS = #status#,
			UpdateTime = NOW()
		WHERE 
			OnlineActivityID = #onlineActivityId#
	
		UPDATE
			GPA_OnlineActivityUserInfo
		SET
			JoinCount = JoinCount + 1
		WHERE
			OnlineActivityID = #onlineActivityId# AND UserID = #userId#	
	
		UPDATE
			GPA_OnlineActivityUserInfo
		SET
			LastIP = #userInfo.lastIP#,
			UpdateTime = NOW()
		WHERE
			OnlineActivityID = #userInfo.onlineActivityId# AND UserID = #userInfo.userId#
	
		UPDATE
			GPA_OnlineActivityUserInfo
		SET
			FirstRewardRank = #firstRewardRank#
		WHERE
			OnlineActivityID = #onlineActivityId# AND UserID = #userId#
	
		UPDATE MC_MemberCardShop
		SET
		FirstCatgory = #firstCatgory# ,
		SecondCatgory = #secondCatgory# ,
		OtherMemberCard = #otherMemberCard# ,
		PrivilegeGrade = #privilegeGrade#
		WHERE
		ShopID = #shopId# AND MemberCardID = #memberCardId#
	
	
	
	
    
        UPDATE MC_MemberCardShopConfirmInfo
        SET
        CheckedForBossAccount = 0
        WHERE
        CrmID = #crmID#
    
		UPDATE MC_MemberCardShopConfirmInfo
		SET
			ProductSelected = #productSelected#
		WHERE
			ID = #ID#
	
		WHERE ListID=#listId# AND ShopID=#shopId#
	
		WHERE ListID=#listId#
	
		WHERE ListID=#listId#
	
        UPDATE DP_MyList
        SET IsDisplay = #isDisplay#
        WHERE ListID=#listId#
    
    
		UPDATE DP_MyListTesecai SET State = 0,UpdateTime=NOW()
		WHERE ID = #Id# ;
	
		UPDATE TA_City SET `type`=#type# WHERE cityid=#cityid#
	
		UPDATE TA_CityArea SET 
		cityid=#cityid#,
		areaid=#areaid#,
		areaname=#areaname#,
		thirdpartyid=#thirdpartyid#,
		thirdpartyareaid=#thirdpartyareaid#,
		priority=#priority#,
		`status`=#status# 
		WHERE id=#id#
	
		UPDATE TA_CityAreaPoint SET 
		cityid=#cityid#,
		cityname=#cityname#,
		areaid=#areaid#,
		areaname=#areaname#,
		pointid=#pointid#,
		pointname=#pointname#,
		thirdpartyid=#thirdpartyid#,
		thirdpartypointid=#thirdpartypointid#,
		pointlat=#pointlat#,
		pointlng=#pointlng#,
		areapriority=#areapriority#,
		areastatus=#areastatus#,
		priority=#priority#,
		`status`=#status# 
		WHERE id=#id#
	
		UPDATE TA_CityAreaPoint SET 
		areaname=#areaname#,
		areapriority=#areapriority#,
		`areastatus`=#areastatus# 
		WHERE areaid=#areaid# and thirdpartyid=#thirdpartyid#
	
     
    
    
            UPDATE TG_BaotuanUser 
            SET Status=#status#,UpdateTime = NOW()
            WHERE DealGroupID = #dealID# AND EventID = #eventID# AND CityID=#cityID#;
        
    
		

			INSERT INTO BC_ShopAccount_Privilege
			(`ShopAccountId`,`Privileges`,`AddDate`,`UpdateDate`) VALUES (#shopAccountId#,#privileges#,now(),now());

        
	
		

			UPDATE BC_ShopAccount_Privilege 
			SET Privileges=#privileges#,UpdateDate=now()
			WHERE ShopAccountId = #shopAccountId#;

        
	
		UPDATE TG_Deal SET MaxJoin=#dto.maxJoin#,OldMaxJoin=#dto.oldMaxJoin# WHERE DealGroupID=#dto.dealGroupId#
	
		UPDATE TG_DealGroup SET BeginDate=#dto.beginDate#,MaxJoin=#dto.maxJoin#,OldMaxJoin=#dto.oldMaxJoin# WHERE DealGroupID=#dto.dealGroupId#
	
		UPDATE TG_DealGroupExtend SET AheadHours=#aheadHour# WHERE DealGroupID=#dealGroupId#
	
        UPDATE TG_DealPriceReduce
        SET ReducePriceByPM = #ReducePrice#
        WHERE DealGroupID=#DealGroupID# AND VersionID = #VersionID#
    
        UPDATE TG_DealPriceReduceForPM
        SET ReducePriceByPM = #reducePrice#
        WHERE DealGroupID=#dealGroupId#
    
        UPDATE TG_DealPriceReduce
        SET Status =2
        WHERE DealGroupID=#DealGroupID# AND VersionID = #VersionID#
    
        UPDATE TG_DealPriceReduceForPM
        SET Status =2
        WHERE DealGroupID=#dealGroupId#
    
        UPDATE TG_DealPriceReduce
        SET Status =1
        WHERE DealGroupID=#DealGroupID# AND VersionID = #VersionID#
    
        UPDATE TG_DealPriceReduceForPM
        SET Status =1
        WHERE DealGroupID=#dealGroupId#
    
        UPDATE TGThirdParty.TG_Settlement_User
        SET  UserName = #userName# ,
             Description = #description#
        WHERE  UserID = #userID#
    
        UPDATE TGThirdParty.TG_Settlement_User
        SET  Password = #password#  ,
             ICStatus = 1
        WHERE  UserID = #userId#
    
        UPDATE TGThirdParty.TG_Settlement_Source
        SET SourceName = #sourceName# ,
             Description = #description#
        WHERE  SourceID = #sourceID#
    
        UPDATE TGThirdParty.TG_Settlement_UserSource
        SET SourceType = #sourceType#
        WHERE  SourceID = #sourceID#
    
    
        UPDATE TGHT_DeliverExpressBatch b
        SET b.UpdateDate = NOW(), b.SuccessNum = b.OrderNum
        WHERE b.BatchID =#batchId#;
    
        UPDATE TGHT_DeliverExpressBatch d SET d.Status = #status# where d.BatchID = #batchId#;
    
        UPDATE TGHT_DeliverExpressBatch d
        SET d.Status = #status#,d.SuccessNum = #successNum#,d.UpdateDate=Now(),d.TryTimes = #tryTimes#
        WHERE d.BatchID = #batchId#;
    
        UPDATE TGE_CouponSendLog 
        SET Status = #status#
        WHERE CouponInfoID = #couponInfoId# AND UserID = #userId# 
    
		UPDATE TuanGouMobile.TGM_CommonToken 
		SET TokenStatus = #TokenStatus#
		WHERE ID = #ID#
	
		UPDATE TuanGouMobile.TGM_CommonToken 
		SET DeviceID = #DeviceID#
		WHERE ID = #ID#
	
   		UPDATE TGM_UserExtend 
   		SET ParamValue=#snsImportedFlags# 
        WHERE UserId=#userId# AND ParamName='SnsImportedFlags' 
   
        UPDATE TG_CpsTrack
        SET LowPriceDeal = #LowPriceDeal#
        WHERE CpsTrackID in ($IdList$)
    
        UPDATE TG_ThirdUserOrder
        SET RefundQuantity = RefundQuantity + #addQuantity#
        WHERE OrderID = #orderId#
    
        UPDATE TG_ThirdUserOrder
        SET VerifiedQuantity = VerifiedQuantity + #addQuantity#
        WHERE OrderID = #orderId#
    
        UPDATE TG_ThirdUserOrder
        SET UpdateDate = NOW()
        WHERE OrderID = #orderId#
    
        
		UPDATE
			TG_EventPrize
		SET
			Consumption=(Consumption+1)
		WHERE
			PrizeId=#prizeId# AND (Stocks=0 OR Stocks>Consumption)
		
    
        UPDATE
          TGE_WeixinGiftInfo
        SET
          ShowStatus = 1,
          ShowNum = 0
        WHERE OwnerID = #userId#
    
        
        UPDATE TGE_WeixinGiftReceiptLog SET Status = #status#
        WHERE ID = #id#
        
    
        
        UPDATE TGE_WeixinGiftInfo SET Consumption = Consumption + #addCons#, Stock = Stock + #addStock#
        WHERE WeixinGiftID = #weixinGiftID# AND GiftStatus = 1
        
    
        
        UPDATE TGE_WeixinGiftInfo SET InitStock = InitStock + #addInit#, Stock = Stock + #addStock#, EndTime = #endTime#
        WHERE WeixinGiftID = #weixinGiftID# AND GiftStatus = 1
        
    
          WeixinNickName =#wxNickName#,
          WeixinHeadImg =#wxHeadImg#,
          WeixinSex =#wxSex#,
          WeixinCity =#wxCity#,
          WeixinCountry =#wxcountry#,
          CityID = #cityId#
        WHERE UserID = #userId#
    
        UPDATE
         TGE_WeixinGiftUserInfo
        SET
         UserType = 2
        WHERE UserID = #userId#
    
        
            UPDATE
            TGE_WeixinGiftInfo
            SET
            Consumption = Consumption + 1
            WHERE Consumption < Stock
                AND WeixinGiftID =#giftId#
                AND GiftStatus = 1
        
    
        UPDATE
          TGE_WeixinGiftInfo
        SET
          Stock = Stock + #num#,
          ShowNum = #num#,
          ShowType = 1,
          ShowStatus = 0,
          StartTime = NOW(),
          EndTime = #endTime#
        WHERE WeixinGiftID = #giftId#
            AND GiftStatus = 1
    
        
        UPDATE
          TGE_WeixinGiftVIPUserApplyChannel
        SET
          UserID = #userId#,
          Status = 1,
          UpdateTime = NOW()
        WHERE ID = #id#
        
    
		UPDATE
			TG_OpModule
		SET
			Status=#status#
		WHERE 
			ID=#moduleId# 
	
		UPDATE
			TG_OpModule
		SET  
			Name=#moduleInfo.moduleName#, ParentID=#moduleInfo.parentModuleId#,
			Status=#moduleInfo.status#, OrderNbr=#moduleInfo.orderNbr#, AddTime=#moduleInfo.addTime#, UpdateTime=#moduleInfo.updateTime#
		WHERE
			ID=#moduleInfo.moduleId# 
	
		UPDATE
			TG_OpRight
		SET
			Status=#status#
		WHERE 
			ID=#rightId# 
	
		UPDATE
			TG_OpRight
		SET  
			Name=#rightInfo.rightName#, Desc=#rightInfo.desc#,
			Status=#rightInfo.status#, AddTime=#rightInfo.addTime#, UpdateTime=#rightInfo.updateTime#
		WHERE
			ID=#rightInfo.rightId# 
	
		UPDATE
			TG_OpAction
		SET
			Status=#status#
		WHERE 
			ID=#actionId# 
	
		UPDATE
			TG_OpAction
		SET
			Name=#actionInfo.name#, ActionURL=#actionInfo.actionURL#, Status=#actionInfo.status#, 
			AddTime=#actionInfo.addTime#, UpdateTime=#actionInfo.updateTime#, ModuleID=#actionInfo.moduleId#, RightID=#actionInfo.rightId#
		WHERE
			ID=#actionInfo.actionId#
	
		UPDATE TG_ModuleBlack SET
			ModuleKey = #data.moduleKey#,
			IntValue = #data.intValue#,
			TextValue = #data.textValue#,
			Status = #data.status#,
			UpdateDate = NOW()
		WHERE ID = #data.id#
	
		UPDATE TG_ShoppingCart SET
			UserID = #data.userId#,
			DealGroupID = #data.dealGroupId#,
			DealID = #data.dealId#,
			Quantity = #data.quantity#,
			Status = #data.status#,
			RemoveMemo = #data.removeMemo#
		WHERE ID = #data.id#
	
		UPDATE {tableName} SET
{updateList}
		WHERE {firstField} = #data.id#
	
		UPDATE
		TG_UserSetting
		SET
		RemindSmsType=#userSetting.remindSmsType#,
		RemindSmsStatus=#userSetting.remindSmsStatus#,
		RemindEmailStatus=#userSetting.remindEmailStatus#,
		UpdateDate=#userSetting.updateDate#
		WHERE
		UserId=#userSetting.userId#
	
            Holiday=Holiday
        WHERE Holiday=#entity.holiday#;

    
        

		DELETE FROM TPDA_Holiday WHERE Holiday=#entity.holiday#

        
    
        UPDATE TG_Event 
        SET EventTitle=#promoEvent.eventTitle#, EventType=#promoEvent.eventType#, Status=#promoEvent.status#, BeginDate=#promoEvent.beginDate#, 
        EndDate=#promoEvent.endDate#, Icon=#promoEvent.icon#,EventDesc=#promoEvent.eventDesc#, TagItemEnName=#promoEvent.tagItemEnName#, 
        TagItemChName=#promoEvent.tagItemChName#, TagPushed=#promoEvent.tagPushed#, Notice=#promoEvent.notice#, Author=#promoEvent.author#
        WHERE EventID=#promoEvent.eventID#
    
        UPDATE TG_Event 
        SET Status=#status#
        WHERE EventID=#eventId#
    
    
        UPDATE TG_EventPromo 
        SET Type=#promoInfo.type#, Quota=#promoInfo.quota#, Stock=#promoInfo.stock#, 
        CouponRuleID=#promoInfo.couponRuleID#, UpdateDate=NOW(), BeginDate=#promoInfo.beginDate#, EndDate=#promoInfo.endDate#
        WHERE ID=#promoInfo.ID# AND EventID=#promoInfo.eventID# 
    
		
        UPDATE 
        	TG_EventPromoCond 
        SET 
        	PromoID=#promoCond.promoID#, ValidOrderPlatform=#promoCond.validOrderPlatform#, ValidPayPlatform=#promoCond.validPayPlatform#, ValidPaymentChannel=#promoCond.validPaymentChannel#, 
       		MaxPerUser=#promoCond.maxPerUser#, MaxPerDeal=#promoCond.maxPerDeal#, LimitOrderAmount=#promoCond.limitOrderAmount#, CategoryList=#promoCond.categoryList#, ShopList=#promoCond.shopList#,
        	BuyLimitType=#promoCond.buyLimitType#, UseLimitType=#promoCond.useLimitType#, UserPropertyType=#promoCond.userPropertyType#
         WHERE 
         	ID=#promoCond.ID#
        
    
    
    
		UPDATE
			TG_Event
		SET 
			EventTitle=#event.eventTitle#, EventType=#event.eventType#, EventDesc=#event.eventDesc#,
			EventHref=#event.eventHref#, Notice=#event.notice#, BeginDate=#event.beginDate#, 
			EndDate=#event.endDate#,TagItemChName=#event.tagName#
		WHERE
			EventID=#event.eventId#
	
		UPDATE
			TG_Event
		SET 
			Status=#status#
		WHERE
			EventID=#eventId#
	
    	UPDATE
    		TG_Event
    	SET
    		TagItemEnName=#tagEnName#
    	WHERE
    		EventID=#eventId#
    
		UPDATE
			TG_EventCity
		SET 
			Status=#status#
		WHERE
			EventID=#eventId#
	
       UPDATE 
          TGE_CompanyGiftChannel 
        SET
          Consumption = Consumption + 1 
        WHERE ChannelName = #channelName#
          AND Consumption < Stock 
    
        CreateTime=CreateTime
        WHERE DealGroupAttributeID=#entity.id#
    
        

		DELETE FROM TPDA_DealGroupAttribute WHERE DealGroupAttributeID=#entity.id#

        
    
		UPDATE TGP_STATEMENT_ATTRIBUTE
		SET
		STATEMENT_TEMPLATE_ID=#entity.statementTemplate.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		SEQUENCE=#entity.sequence#,
		FRONT_END_INPUT_TYPE=#entity.frontEndInputType#,
		LABEL_CONTENT=#entity.labelContent#,
		LENGTH=#entity.length#
		WHERE
		STATEMENT_ATTRIBUTE_ID=#entity.id#
	
        UPDATE TGP_ACCOUNT
        SET
            ACCOUNT_GLOBAL_ID = #entity.accountGlobalId# ,
            HAS_DEAL_GROUP_APPROVED=   #entity.hasDealGroupApproved# ,
            HAS_CRM_DEAL_GROUP_APPROVED=  #entity.hasCRMDealGroupApproved# ,
            CREATE_TIME = #entity.createTime# ,
            CREATOR_ID = #entity.creatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            VERSION_ID = #entity.versionId#
          WHERE
            ACCOUNT_ID = #entity.id# ;
	
        UPDATE TGP_CARD
        SET
            NAME_TYPE = #entity.nameType# ,
            NAME = #entity.name# ,

            BACKGROUND_PICTURE_PATH = #entity.backgroundPicPath#,
            CATEGORY =  #entity.category#,
            CHARGE_AMOUNT = #entity.chargeAmount#,
            GIFT_AMOUNT = #entity.giftAmount#,
            COMMISSION_RATE = #entity.commissionRate#,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId#    ,
            DEAL_ID=#entity.dealId#,
            CARD_TYPE=#entity.cardType#,
            DISCOUNT=#entity.discount#

          WHERE
            CARD_ID = #entity.id# ;
	 
	 
		DELETE FROM TGP_CORP_INFO WHERE CORP_INFO_ID=#entity.id#
	
	
		UPDATE TGP_DEAL_SHOP_ASSN
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		DEAL_ID=#entity.dealItem.id#,
		POI_SHOP_ID=#entity.poiShopId#,
		SHOP_ID_TYPE= #entity.shopIdType#
		WHERE
		DEAL_SHOP_ASSN_ID=#entity.id#
	
        UPDATE TGP_EDITOR_TEAM
        SET TEAM_NAME = #entity.teamName#,
          TEAM_LEADER_ID = #entity.teamLeaderId#,
          TEAM_LEADER_NAME = #entity.teamLeaderName#,
          DEAL_GROUP_PRODUCE_TYPE = #entity.dealGroupProduceTypeList,handler=dealGroupProduceTypeEnumsTypeHandler#,
          DEAL_GROUP_CATEGORY_ID = #entity.dealGroupCategoryId#,
          IS_ECOMMERCE = #entity.isEcommerce#,,
          LAST_UPDATOR_ID = #entity.lastUpdatorId#,
          LAST_UPDATE_TIME = #entity.lastUpdateTime#
        where EDITOR_TEAM_ID = #entity.id#;
    
		UPDATE TGP_FILE_ATTACHMENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		FILE_NAME=#entity.fileName#,
		RELATIVE_PATH=#entity.relativePath#
		WHERE
		FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='FileAttachment'
	
        UPDATE TGP_MESSAGE_QUEUE
        SET
            COMMENT=#entity.comment#,
            TOPIC=#entity.topic#,
            CONTENT=#entity.content#,
            MAX_RETRY_TIMES=#entity.maxRetryTimes#,
            CURRENT_RETRY_TIMES=#entity.currentRetryTimes#,
            STATUS_ID=#entity.statusId#,
            CREATE_TIME=#entity.createTime#,
            LAST_EXECUTE_TIME=#entity.lastExecuteTime#,
            NEXT_EXECUTE_TIME=#entity.nextExecuteTime#
        WHERE
            MESSAGE_QUEUE_ID=#entity.id#;
	
    
        UPDATE TGP_RESOURCE_AUTHORITY_CONFIG
            SET 
                LAST_UPDATE_TIME = #entity.lastUpdateTime#,
                LAST_UPDATOR_ID = #entity.lastUpdatorId#,
                VERSION_ID = #entity.versionId#,
                RESOURCE_CODE = #entity.resourceCode#,
                ACTION_ID = #entity.actionId#,
                POWER_CODE = #entity.powerCode#,
                PUBLISH_STATUS_ID = #entity.publishStatusId#,
                PROCESS_STATUS_ID = #entity.processStatusId#
            WHERE
                RESOURCE_AUTHORITY_CONFIG_ID = #entity.id# ;
	
		UPDATE
		TGP_SERIAL_NUM_OPERATION_LOG
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VERSION_ID=#entity.versionId#+1,
		DEAL_ID=#entity.dealId#,
		TOTAL_COUNT=#entity.totalCount#,
		DUPLICATED_COUNT=#entity.duplicatedCount#,
		IMPORTED_COUNT=#entity.importedCount#,
		BATCH_NAME = #entity.batchName#,
        IS_REMOVED = #entity.isRemoved#,
        DUPLICATED_SERIAL_NUMBERS = #entity.duplicatedSerialNumbers#
		WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberImportLog'
	
		UPDATE TGP_TOP_CITY_INFO
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		BEGIN_DATE = #entity.beginDate#,
		END_DATE = #entity.endDate#,
		DEAL_GROUP_ID=#entity.dealGroup.id#,
		CITY_ID=#entity.cityId#
		WHERE
		TOP_CITY_INFO_ID=#entity.id#
	
        UPDATE TPA_Process
        SET Status = #status#, UpdateTime = NOW()
        WHERE ProcessID = #processID#
    
        UPDATE TPA_Process
        SET Version = Version + 1, UpdateTime = NOW()
        WHERE ProcessID = #processID# AND Version = #version#
    
        UPDATE TPA_Process
        SET UpdateTime = NOW()
        WHERE ProcessID = #processID#
    
        UPDATE TPDA_Deal
        SET
        DealStatus = #dealStatus#
        WHERE
        DealID = #dealId# ;
    
    UPDATE TPA_Template SET
    Title=#entity.title#,
    Status=#entity.status#,
    ParentTemplateId=#entity.parentTemplateId#,
    ProductType=#entity.productType#,
    TemplateId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE TemplateId=#entity.id#
    
      
      where  thirdpartyId=#newPartnerComboAssoc.thirdpartyId# and shopId=#newPartnerComboAssoc.shopId# and comboId=#newPartnerComboAssoc.comboId# and dishId=#newPartnerComboAssoc.dishId#
      
    
      
      update TA_NewPartnerComboAssoc
      set thirdpartyId=#thirdpartyId#
        comboId = #comboId#,
        shopId = #shopId#,
        dishId = #dishId#,
        groupId = #groupId#,
        dishOrder = #dishOrder#,
        isDefault = #isDefault#,
        isRequired = #isRequired#,
        markupPrice = #markupPrice#,
        feature1 = #feature1#,
        feature2 = #feature2#,
        feature3 = #feature3#,
        createDate = #createDate#,
        modifyDate = #modifyDate#
        where thirdpartyId=#thirdpartyId# and shopId=#shopId# and comboId=#comboId# and dishId=#dishId#
      
    
      where thirdpartyId=#newPartnerComboGroup.thirdpartyId# and shopId=#newPartnerComboGroup.shopId# and comboId=#newPartnerComboGroup.comboId# and groupId=#newPartnerComboGroup.groupId# 
    
      
      update TA_NewPartnerComboGroup
      set comboId = #comboId#,
        groupId = #groupId#,
        shopId = #shopId#,
        groupName = #groupName#,
        groupOrder = #groupOrder#,
        multiSupported = #multiSupported#,
        quantityLimit = #quantityLimit#,
        createDate = #createDate#,
        modifyDate = #modifyDate#
      
      where thirdpartyId=#thirdpartyId# and shopId=#shopId# and comboId=#comboId# and groupId=#groupId#
    
        UPDATE TG_DirectionalGift
        SET GiftStatus = 1
        WHERE DirectionalGiftId = #directionalGiftId#
    
		UPDATE TGP_MIGRATION_SOURCE
		SET
            DEAL_GROUP_ID=#entity.dealGroupId#,
            STATUS_ID=#entity.statusId#,
            CREATE_TIME=#entity.createTime#,
            LAST_UPDATE_TIME=#entity.lastUpdateTime#
		WHERE
		    ID=#entity.id#
	
		UPDATE TA_Dish
		SET SoldOut =
		#soldOut#
		WHERE ShopKey = #shopId#
		AND DishID = #dishId#
	
		UPDATE TA_Dish
		SET Box = #box#
		WHERE ShopKey = #shopId#
		AND DishID = #dishId#
	
		UPDATE TA_Dish
		SET Price =
		#price#
		WHERE ShopKey = #shopId#
		AND DishID = #dishId#
	
		UPDATE TA_Dish
		SET Valid =
		#valid#
		WHERE ShopKey = #shopId#
		AND DishID = #dishId#
	
		UPDATE TA_Dish
		SET DishName =
		#dishName#,
		Category = #category#,
		Price = #price#,
		Box = #box#,
		Comment =
		#comment#,
		Valid = #valid#,
		SoldOut = #soldOut#,
		DishSet = #dishSet#,
		Img = #pictureUrl#
		WHERE id = #id#
	
		UPDATE TA_DishCategory
		SET Priority = #Priority#
		WHERE ShopKey = #ShopKey#
		AND Category = #Category#;
	
		UPDATE TA_RecommendDish
		SET DishName = #DishName#
		WHERE ShopKey = #ShopKey#
		AND DishID = #DishID#
	
	
	
		UPDATE
			TG_OpModule
		SET  
			Name=#moduleInfo.moduleName#, ModuleDesc=#moduleInfo.moduleDesc#, ParentID=#moduleInfo.parentModuleId#, RootID=#moduleInfo.rootModuleId#,
			Status=#moduleInfo.status#, OrderNbr=#moduleInfo.orderNbr#, UpdateTime=NOW()
		WHERE
			ID=#moduleInfo.moduleId# 
	 
	
	
		UPDATE
			TG_OpRight
		SET  
			Name=#rightInfo.rightName#, `Desc`=#rightInfo.desc#,
			Status=#rightInfo.status#, UpdateTime=NOW()
		WHERE
			ID=#rightInfo.rightId#
	 
	 
	 
	 
	
		UPDATE
			TG_OpAction
		SET
			Name=#actionInfo.name#, ActionDesc=#actionInfo.actionDesc#, ActionURL=#actionInfo.actionURL#, ActionParam=#actionInfo.actionParamExp#, Status=#actionInfo.status#, 
			UpdateTime=NOW(), ModuleID=#actionInfo.moduleId#, RightID=#actionInfo.rightId#
		WHERE
			ID=#actionInfo.actionId#
	 
	
	
		UPDATE
			TG_OpRole
		SET
			Name=#roleInfo.roleName#, EnName=#roleInfo.roleEnName#, `Desc`=#roleInfo.desc#, Status=#roleInfo.status#,UpdateTime=NOW()
		WHERE
			ID=#roleInfo.roleId#
	
	
	
		UPDATE
			TG_OpUserRole
		SET
			UserName=#userRole.userName#, AccessSourceID=#userRole.accessSourceId#, RoleID=#userRole.roleId#, CityIDs=#userRole.cityIds#, Status=#userRole.status#, UpdateTime=NOW(),Type=#userRole.type#
		WHERE
			ID=#userRole.relationId#
	
        DealGroupId=DealGroupID
        WHERE DealGroupId=#entity.id#
    
		UPDATE 
			EBIZ_Apply 
		SET
			ApplyType=#apply.applyType#,
			MobileNo=#apply.mobileNo#,
			Status=#apply.status#,
			Amount=#apply.amount#,
			OrderId=#apply.orderId#,
			DealGroupId=#apply.dealGroupId#,
			DealId=#apply.dealId#,
			AddDate=#apply.addDate#,
			UpdateDate=#apply.updateDate#,
			UserId=#apply.userId#,
            Quantity=#apply.quantity#
		WHERE
			ApplyId=#apply.applyId#
	
        UPDATE
            EBIZ_Apply
        SET
            Status=#status#,
            UpdateDate=CURRENT_TIMESTAMP
        WHERE
            ApplyId in $applyIds$
    
		UPDATE TA_Partner
		SET status = #status#,
		updatetime = now()
		WHERE id = #id#
	
		UPDATE TA_Partner
		SET secret = #secret#,
		updatetime = now()
		WHERE id = #id#
	
		UPDATE TA_NewPartnerShop
		SET cityname = #cityname#,
		thirdpartyshopname = #thirdpartyshopname#,
		thirdpartyaddress = #thirdpartyaddress#,
		thirdpartyphone = #thirdpartyphone#,
		thirdpartylat = #thirdpartylat#,
		thirdpartylng = #thirdpartylng#,
		coordtype = #coordtype#,
		`interval` = #interval#,
		starttime = #starttime#,
		endtime = #endtime#,
		minfee = #minfee#,
		discount = #discount#,
		`status` = #status#,
		startresttime = #startresttime#,
		endresttime = #endresttime#,
		servtimejson = #servtimejson#,
		mindeliverfee = #mindeliverfee#,
		distance = #distance#,
		thirdpartypicture = #thirdpartypicture#,
		geojson = #geojson#
		WHERE thirdpartyid = #thirdpartyid#
		and thirdpartyshopid = #thirdpartyshopid#
	
		UPDATE TA_NewPartnerDish
		SET dishname = #dishname#,
		category = #category#,
		price = #price#,
		box = #box#,
		`comment` = #comment#,
		soldout = #soldout#,
		dishset = #dishset#,
		img = #img#,
		dishType = #dishType#,
		fromDate = #fromDate#,
		toDate = #toDate#,
		soldSingle = #soldSingle#
		WHERE thirdpartyid = #thirdpartyid#
		and thirdpartyshopid = #thirdpartyshopid#
		and dishid = #dishid#
	
		UPDATE TA_NewPartnerShop
		SET dpshopid = #dpshopid#,
		dpshopname = #dpshopname#,
		cityid = #cityid#,
		dpaddress = #dpaddress#,
		dpphone = #dpphone#,
		dplat = #dplat#,
		dplng = #dplng#,
		dppicture = #dppicture#,
		type = #type#
		WHERE thirdpartyid = #thirdpartyid#
		and thirdpartyshopid = #thirdpartyshopid#
	
		UPDATE TA_NewPartnerShop
		SET type = #type#,
		onlinefailmsg = #onlinefailmsg#
		WHERE thirdpartyid = #thirdpartyid#
		and thirdpartyshopid = #thirdpartyshopid#
	
		UPDATE TA_NewPartnerShop
		SET type = #type#,
		onlinefailmsg = #onlinefailmsg#,
		firstonlinetime = #firstonlinetime#
		WHERE thirdpartyid = #thirdpartyid#
		and thirdpartyshopid = #thirdpartyshopid#
	
    
    	UPDATE TE_Topic SET Status = #status# WHERE ID = #topicId#
    
    
    	WHERE ID = #topicId#
    
        
        UPDATE TE_Topic
        SET Title=#topic.title#, SubTitle=#topic.subTitle#, Status=#topic.status#, AuditStatus=#topic.auditStatus#, Author=#topic.author#, AuditComment=#topic.auditComment#, JoinAllowed=#topic.joinAllowed#, DeadLine=#topic.deadLine#, ChannelType=#topic.channelType#, UpdateTime=NOW()
        WHERE ID=#topic.ID#
        
    
        UPDATE 
        	TE_TopicStyle
        SET 
        	SkinID=#topicStyle.skinID#, BgColor=#topicStyle.bgColor#, PcHeadImg=#topicStyle.pcHeadImg#, PcTailImg=#topicStyle.pcTailImg#, AppBannerImg=#topicStyle.appBannerImg#, PcLayoutID=#topicStyle.pcLayoutID#, AppLayoutID=#topicStyle.appLayoutID#,UpdateTime=NOW()
        WHERE 
        	TopicID=#topicStyle.topicID#
    
        UPDATE
        	TE_TopicItem
        SET 
        	Name =#category.name#,Type=#category.type#,OrderNo=#category.orderNo#,
        	BgColor=#category.bgColor#,LabelColor=#category.labelColor#,ButtonColor=#category.buttonColor#,BtnFontColor=#category.btnFontColor#
        WHERE 
        	ID=#category.ID#
    
	
	
	
		UPDATE TE_TopicDeal
		SET Status=#status#
		WHERE 
			TopicID=#topicId# AND CityID=#cityId# AND DealGroupID=#dealgroupId#
	
	
	
		UPDATE
			TE_TopicDeal
		SET 
			Status=#status#
		WHERE ID=#id#
	
        UPDATE TE_TopicRule
        SET TopicID=#topicRule.topicID#, RuleIdRelationExp=#topicRule.ruleIdRelationExp#, UpdateTime=NOW()
        WHERE ID = #topicRule.ID#
	
	    UPDATE TE_TopicPublish
	    SET PublishStatus = #status#
	    WHERE TopicID=#topicId# AND AdPublishID=#publishId#
	
        UPDATE TE_TopicPublish SET AdPublishID=#publishId#, PublishStatus=2 WHERE TopicID=#topicId# AND ChannelType=#channel#
    
    	UPDATE
    		TE_TopicPublish
    	SET
    		BannerImg=#tp.bannerImg#
    	WHERE
    		TopicID=#tp.topicID# AND PositionName=#tp.positionName#
    
        UPDATE TPABZ_DealGroupProcessContractQueue Q
        SET Status = 1
        WHERE Q.ProcessID = #processID#
    
		UPDATE TG_DealRate
		SET
			Score=#entity.score#,
			IsAgain=#entity.isAgain#,
			IsExtra=#entity.isExtra#,
			Comment=#entity.comment#,
			ExtraMoney=#entity.ExtraMoney#,
			Status=#entity.status#,
			ExtendBiz=#entity.extendBiz#,
			LastDate=now()
		WHERE RateID=#entity.rateID#
	
		UPDATE
		TG_Event
		SET
		CurrentJoin=CurrentJoin+1
		WHERE
		EventID=#eventId# AND (MaxJoin=0 OR MaxJoin>CurrentJoin)
	
		UPDATE TG_RateReviewMapping
		SET
			RateID=#data.rateId#,
			ShopID=#data.shopId#,
			ReviewID=#data.reviewId#
		WHERE MappingID=#data.id#
	
		UPDATE TG_Deal SET
			DealGroupID = #dealGroupId#,
			DealTitle = #dealTitle#,
			DealName = #dealName#,
			DealSMSName = #dealSMSName#,
			DealPrice = #dealPrice#,
			MarketPrice = #marketPrice#,
			DealSortID = #dealSortId#,
			MaxJoin = #maxJoin#,
			CurrentJoin = #currentJoin#,
			RemainDisplayPercent = #remainDisplayPercent#,
			FinishDate = #finishDate#,
			DeliverType = #deliverType#,
			ReceiptType = #receiptType#,
			ProvideInvoice = #provideInvoice#,
			DealShortTitle = #dealShortTitle#,
			IsDealPriceLargerThanCost = #isDealPriceLargerThanCost#,
			DealStatus = #dealStatus#
		WHERE DealID = #id#
	
    	UPDATE TG_Deal  SET CurrentJoin = CurrentJoin + #quantity#
		WHERE DealID = #id#
		AND (MaxJoin = 0 OR (CurrentJoin + #quantity#) <= MaxJoin)
	
    	UPDATE TG_Deal  SET CurrentJoin = CurrentJoin - #quantity#
        WHERE DealID = #id#
        AND CurrentJoin <= MaxJoin
        AND (CurrentJoin - #quantity#) > -1
	
    	 UPDATE TG_Deal  SET FinishDate = NOW()    WHERE DealID = #id#
		 AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
		UPDATE TG_DealGroup SET
		DealGroupShortTitle = #dealGroupShortTitle#,
		DealGroupTitleDesc =
		#dealGroupTitleDesc#,
		DealGroupTitle = #dealGroupTitle#,
		DealGroupName =
		#dealGroupName#,
		DealGroupPrice = #dealGroupPrice#,
		MarketPrice =
		#marketPrice#,
		DefaultPic = #defaultPic#,
		HighLight = #highLight#,
		DealGroupInfo = #dealGroupInfo#,
		ShopInfo = #shopInfo#,
		BeginDate =
		#beginDate#,
		EndDate = #endDate#,
		FinishDate = #finishDate#,
		MaxPerUser =
		#maxPerUser#,
		MaxJoin = #maxJoin#,
		CurrentJoin = #currentJoin#,
		RemainDisplayPercent = #remainDisplayPercent#,
		IsSideDeal =
		#isSideDeal#,
		DealGroupSortID = #dealGroupSortId#,
		Status = #status#,
		PublishStatus = #publishStatus#,
		PV = #pV#,
		UV = #uV#,
		IsReward =
		#isReward#,
		EditorInfo = #editorInfo#,
		ImportantPoint =
		#importantPoint#,
		MemberInfo = #memberInfo#,
		SpecialPoint =
		#specialPoint#,
		ProductInfo = #productInfo#,
		LotteryStatus =
		#lotteryStatus#,
		QRCodeValidate = #qRCodeValidate#,
		DealAltName =
		#dealAltName#,
		AutoRefundSwitch = #autoRefundSwitch#,
		IsCanUseCoupon =
		#isCanUseCoupon#,
		DealGroupPics = #dealGroupPics#,
		IsECommerceWebSite =
		#isECommerceWebSite#,
		SaleChannel = #saleChannel#,
		UpdateDate = NOW()
		WHERE DealGroupID = #id#
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin + #quantity#
		WHERE DealGroupID = #id#
		AND (MaxJoin = 0 OR (CurrentJoin + #quantity#) <= MaxJoin)
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin - #quantity# WHERE
		DealGroupID = #id# AND CurrentJoin <= MaxJoin
        AND (CurrentJoin - #quantity#) > -1
	
		UPDATE TG_DealGroup
		SET FinishDate = NOW() WHERE DealGroupID = #id#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
  	UPDATE TG_Receipt set mobileNo=#mobileNo# WHERE receiptID=#receiptID#
  
  	UPDATE TG_Receipt set refundID=#refundId# WHERE receiptID=#receiptId#
  
    update TG_Receipt
    set Status = #status:INTEGER#,
      LastDate = #lastDate:TIMESTAMP#,
      VendorShopID = #vendorShopID:VARCHAR#    
    where ReceiptID = #receiptID:INTEGER#
  
    update TG_Receipt
    set Status = #status:INTEGER#,LastDate = now()      
    where ReceiptID = #receiptID:INTEGER#
  
        update TG_Receipt
        set Status = #status:INTEGER#,LastDate = now()
        where ReceiptID in ($receiptIds$)
    
    	 UPDATE TG_ThirdPartyDealMapping  SET CurrentJoin = CurrentJoin + #quantity#  
    	 WHERE ID = #id# 
	
    	 UPDATE TG_ThirdPartyDealMapping  SET FinishTime = NOW()  
    	 WHERE ID = #id# 
	
		UPDATE TG_WishRegionLog SET
			UserID = #userId#,
			CityID = #cityId#,
			Type = #type#,
			RegionID = #regionId#
		WHERE ID = #id#
	
		UPDATE DP_Shop 
		SET Power = #power#
		WHERE ShopID = #shopID#
	
		UPDATE DP_Shop 
		SET GLat = #lat#,GLng= #lng#		
		WHERE ShopID = #shopID#
	
		UPDATE CIT_Transition 
		SET Status = #status#
		WHERE UserID = #userID#
	
   		UPDATE TGM_UserExtend 
   		SET ParamValue=#snsImportedFlags# 
        WHERE UserId=#userId# AND ParamName='SnsImportedFlags' 
   
		UPDATE TG_Feed_Queue
		SET UpdateDate = NOW()
		WHERE FeedId = #feedId# AND UpdateDate < #updateBefore#
	
		UPDATE TG_Feed_Queue
		SET Status = 1, TryTimes = TryTimes + 1, UpdateDate = NOW()
		WHERE FeedId = #feedId#
	
		UPDATE TG_Feed_Queue
		SET TryTimes = TryTimes + 1, UpdateDate = NOW()
		WHERE FeedId = #feedId#
	
		UPDATE 
		TG_Event_ResultPageEventsConfig
		SET EventIDs=#eventIds#
		WHERE CityID=#cityId#
		
		UPDATE
		TG_Event_ResultPageEvents
		SET EventType=#eventType#,
		EventURL=#eventURL#,
		EventImageURL=#eventImageURL#
		WHERE EventID=#eventId#
		
		UPDATE
		TG_Event
		SET
		CurrentJoin=CurrentJoin+1
		WHERE
		EventID=#eventId# AND (MaxJoin=0 OR MaxJoin>CurrentJoin)
	
		UPDATE 
			TG_EventCity 
		SET
			Status=#eventCity.status#
		WHERE
			EventId=#eventCity.eventId#
			AND CityId=#eventCity.cityId#
	
		
			UPDATE 
				TG_AppDeductUser
			SET
				Amount= Amount+1
			WHERE
				(UserId=#orderNotifyDTO.userId# OR UserMobile=#orderNotifyDTO.mobileNo#) AND Amount<10
		
	
		UPDATE
			TG_EventDeal A
		SET
			A.CurrentJoin = A.CurrentJoin + #count#
		WHERE
			A.EventDealID IN (SELECT B.EventDealID FROM TG_EventCityDeal B WHERE B.CityID=#cityId# AND B.EventID=#eventId#)
			AND A.Status=1 AND A.DealGroupID=#dealGroupId#
	
		UPDATE TG_DealHotel
		SET
		TPID=#entity.tpID#,
		TPHotelID=#entity.tpHotelID#,
		RoomType=#entity.roomType#,
		UpdateTime=NOW()
		WHERE
		DealID=#dealID#
	
	UPDATE TG_VerifyShopAccount SET
		ShopID = #shopID#,
	    Account = #account#,
		Password = #password#,
		UpdateDate = NOW(),
		Status = #status#,
		IsSend = #isSend#,
		LastMobile = #lastMobile#,
		CustomerID = #customerID#,
		AccountType = #accountType#
	WHERE ShopAccountID = #shopAccountID#

	UPDATE TG_VerifyShopAccount SET
		AccountType = #accountType#,UpdateDate = Now()		
	WHERE ShopAccountID = #shopAccountID#

   		Update Mail_Statistic set BatchNum=#batchNum# where TaskId=#taskId#
   	
		UPDATE TG_DealTaggedImage
		SET TaggedImage = #taggedImage#
		WHERE DealGroupID = #dealGroupId# AND TagType = #tagType# 
	
		UPDATE TG_HotKeyWord SET
			CityID = #data.cityId#,
			Position = #data.position#,
			KeyWord = #data.keyWord#,
			KeyWordEnName = #data.keyWordEnName#,
			URL = #data.url#,
			Status = #data.status#,
			DivClass = #data.divClass#,
			ATarget = #data.aTarget#,
			Priority = #data.priority#,
			UpdateDate = NOW()
		WHERE KeyWordID = #data.id#
	
		WHERE s.EventDealID=#eventDealDto.eventDealID#
    
	
	
        Update DP_DishTagSearch
        Set RecommendTimes=#recommendTimes#
        Where ID=#id#
    
        
        UPDATE BA_TC_Task
        SET    Status = #task.taskStatus#,
               UpdateTime = #task.updateTime#
        WHERE `TaskGuid` = #task.taskGuid#
        
    
		UPDATE
			BC_Questionnaire
		SET 
			Title = #title#,
			URL = #url#,
			LastUpdateTime = now(),
			LastAdminID = #lastAdminID#,
			Status = #status#
		WHERE
			ID=#id#
    
		UPDATE
		BC_ShopAccount_Customer
		SET CustomerName = #customerName#
		Where
		ShopAccountId=#shopAccountId#
    
    
	    UPDATE TG_EventSendDeal 
		SET
		STATUS = #type#,  
		SuccessTime = NOW()		
		WHERE
		ID = #id#
    
	 
	
		UPDATE TG_HotelTPOrder
		SET ReservationStatus = #status#,UpdateDate = NOW()
		WHERE OrderID = #orderId#
	
		UPDATE TG_HotelTPOrder
		SET ReservationStatus = #status#,UpdateDate = NOW()
		WHERE TPID = #tpId# AND TPOrderID = #tpOrderId#
	
		UPDATE TG_HotelTPOrder
		SET OrderID = #orderId#,UpdateDate = NOW()
		WHERE TPID = #tpId# AND TPOrderID = #tpOrderId#
	
        UPDATE TGThirdParty.TG_Settlement_User
        SET IllegalAction = #illegalCount#
        WHERE UserName = #username#
    
        UPDATE TGThirdParty.TG_Settlement_User
        SET Password = #password#, ICStatus = 1, IllegalAction = 0
        WHERE UserName = #username#
    
		UPDATE TG_ThirdReceiptApplyingQueue
		SET UpdateDate = NOW(), Status = #value#, TryTimes = TryTimes + 1
		WHERE QueueID = #queueId#
	
    
		UPDATE CIT_Transition 
		SET Status = #status#
		WHERE UserID = #userID#
	
		UPDATE TG_Feed_Queue
		SET UpdateDate = NOW()
		WHERE FeedId = #feedId# AND UpdateDate < #updateBefore#
	
		UPDATE TG_Feed_Queue
		SET Status = 1, TryTimes = TryTimes + 1, UpdateDate = NOW()
		WHERE FeedId = #feedId#
	
		UPDATE TG_Feed_Queue
		SET TryTimes = TryTimes + 1, UpdateDate = NOW()
		WHERE FeedId = #feedId#
	
        UPDATE TG_MissedThirdOrder
        SET Status = #status#
        WHERE OrderID = #orderId#
    
		UPDATE 
			EBIZ_ApplyPics 
		SET
			PicUrl=#applyPics.picUrl#,
			Status=#applyPics.status#,
			FlowId=#applyPics.flowId#,
			AddDate=#applyPics.addDate#,
			UpdateDate=#applyPics.updateDate#
		WHERE
			PicId=#applyPics.picId#
	
        UPDATE
            EBIZ_DeliverAddress
        SET
            AddressId=#deliverAddress.addressId#,
            AccountId=#deliverAddress.accountId#,
            Address=#deliverAddress.address#,
            Zipcode=#deliverAddress.zipcode#,
            Name=#deliverAddress.name#,
            MobileNo=#deliverAddress.mobileNo#,
            AddDate=#deliverAddress.addDate#,
            UpdateDate=#deliverAddress.updateDate#
        WHERE
            AddressId=#deliverAddress.addressId#
    
        UPDATE TE_AdPosition 
        SET Title=#adPosition.title#, Name=#adPosition.name#, `Desc`=#adPosition.desc#, ChannelID=#adPosition.channelID#,
        	ContainerID=#adPosition.containerID#, Width=#adPosition.width#, Height=#adPosition.height#, 
        	Type=#adPosition.type#, Status=#adPosition.status#, Author=#adPosition.author#,PublishType=#adPosition.publishType#, AddTime=NOW(), UpdateTime=NOW(),
        	MaxDisplay=#adPosition.maxDisplay#, TitleLen=#adPosition.titleLen#, SubTitleLen=#adPosition.subTitleLen#
        WHERE ID=#adPosition.id#
    
        UPDATE TE_AdPosition
        SET Status=#adPositionStatus#,UpdateTime=NOW()
        WHERE ID=#adPositionId#
    
					
		UPDATE TG_DealUserTip 
			SET
			ADDDATE = NOW()			
			WHERE
			DealGroupID = #dealGroupId# AND UserID = #userId# AND TipType = #tipType#
		
  
    	
    	UPDATE
    		TGE_MemberGiftStat
    	SET
    		GiftSendTimes = GiftSendTimes + 1,
    		NextSendTime = #nextSendTime#
    	WHERE
    		UserID = #userId# AND MemberGiftID = #memberGiftId# AND GiftSendTimes < MaxSendTimes
    	
    
		update TG_NaviTagItemAttribute set ItemID=#itemId#,Name=#name#,Value=#value#  where NaviTagItemAttributeID=#id#
	
		UPDATE TGP_STATEMENT
		SET
		DOCUMENT_BUILDER_ID=#entity.documentBuilder.id#,
		STATEMENT_TEMPLATE_ID=#entity.statementTemplateId#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#
		WHERE
		STATEMENT_ID=#entity.id#
	
		UPDATE TGP_STATEMENT_ATTRIBUTE_VALUE
		SET
		STATEMENT_ID=#entity.statement.id#,
		STATEMENT_ATTRIBUTE_ID=#entity.statementAttributeId#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VALUE=#entity.value#
		WHERE
		STATEMENT_ATTRIBUTE_VALUE_ID=#entity.id#
	
        UPDATE TGP_CONTRACT
        SET
            CONTRACT_CRM_ID = #entity.contractCrmId# ,
            CONTRACT_SERIAL = #entity.contractSerial# ,
            CONTRACT_GLOBAL_ID=   #entity.contractGlobalId# ,
            ACCOUNT_GLOBAL_ID=  #entity.accountGlobalId# ,
            FP_SETTLE_TYPE  = #entity.fpSettleType# ,
            CASH_OUT_TYPE = #entity.cashOutType# ,
            ACCOUNT_NAME = #entity.accountName# ,
            CITY_ID = #entity.cityId# ,
            SIGN_ON_SALES_ID = #entity.signOnSalesId#,
            SIGN_ON_SALES_NAME = #entity.signOnSalesName#,
            OWNER_SALES_ID = #entity.ownerSalesId# ,
            OWNER_SALES_NAME = #entity.ownerSalesName# ,
            PAY_TERM_TYPE=#entity.payTermType#,
            STATUS=#entity.status#,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            VERSION_ID = #entity.versionId# ,
            ACCOUNT_REGISTER_NAME = #entity.accountRegisterName#,
            ACCOUNT_RATING = #entity.accountRating#,
            ACCOUNT_ID = #entity.accountId#,
            TYPE = #entity.type#,
            SOURCE_TYPE=#entity.sourceType#
          WHERE
            CONTRACT_ID = #entity.id# ;
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		IS_AUTO_CALCUATED=#entity.isAutoCalcuated#,
		TITLE=#entity.title#,
		TOTAL_PRICE=#entity.totalPrice#,
		RETAIL_PRICE_DESC=#entity.retailPriceDescription#,
		TOTAL_PRICE_LABEL=#entity.totalPriceLabel#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='DealComponent'
	
        UPDATE TGP_DEAL_GROUP
        SET IS_AUTO_DELAY = #isAutoDelay#,
            LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE DEAL_GROUP_ID = #dealGroupId#
    
        

		DELETE FROM TGP_DEAL_GROUP_EDITOR WHERE DEAL_GROUP_EDITOR_ID=#entity.id#

        
    
    

		UPDATE TGP_DEAL_GROUP_EDITOR
		SET DEAL_GROUP_PRODUCE_STATUS = #completedStatus,handler=dealGroupProduceStatusEnumTypeHandler#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
    
        

        UPDATE TGP_DEAL_GROUP_EDITOR
        SET
            DEAL_GROUP_ID = #entity.dealGroupId# ,
            EDITOR_ID = #entity.editorId# ,
            EDITOR_NAME = #entity.editorName# ,
            DEAL_GROUP_PRODUCE_STATUS = #entity.dealGroupProduceStatus,handler=dealGroupProduceStatusEnumTypeHandler# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            VERSION_ID = #entity.versionId#
        WHERE
            DEAL_GROUP_EDITOR_ID = #entity.id# ;

        
    
		UPDATE
		TGP_DEAL_GROUP_PRODUCE_VERSION
		SET
		DEAL_GROUP_ID=#entity.dealGroupId#,
		DESC_INFO=#entity.descInfo#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		IP_ADDRESS=#entity.ipAddress#,
		SERVER_IP=#entity.serverIp#,
		DATA=#entity.data#
		WHERE
		DEAL_GROUP_PRODUCE_VERSION_ID=#entity.id#
	
        
        UPDATE TGP_DEAL_GROUP_WORKFLOW
        SET
            WORKFLOW_STATUS = #workflowStatus,handler=workflowStatusEnumTypeHandler# ,
            LAST_UPDATE_TIME = #updateTime#
        WHERE
            PROCESS_INSTANCE_ID = #processInstanceId# ;
		
    
		UPDATE
			TGP_DEAL_HOTEL
		SET
			DEAL_ID=#entity.dealId#,
			ROOM_TYPE=#entity.roomType#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#
		WHERE
			ID=#entity.id#
	
		UPDATE TGP_EXCEPT_DATE
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		BEGIN_DATE = #entity.beginDate#,
		END_DATE = #entity.endDate#,
		DEAL_GROUP_ID=#entity.dealGroup.id#
		WHERE
		EXCEPT_DATE_ID=#entity.id#
	
		UPDATE
		TGP_IMAGE_TEXT_DESC_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		IMAGE_TEXT_ITEM_ID=#entity.imageTextItem.id#,
		SEQ=#entity.sequence#,
		CONTENT=#entity.content#,
		IS_TITLE=#entity.isTitle#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		IMAGE_TEXT_DESC_ITEM_ID=#entity.id#
	
		UPDATE
		TGP_VISUAL_LIST_ITEM
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VISUAL_COMPONENT_ID=#entity.visualComponent.id#,
		SEQ=#entity.sequence#,
		CONTENT=#entity.content#,
		QUANTITY=#entity.quantity#,
		SPECIFICATION=#entity.specification#,
		UNIT=#entity.unit#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ProductItem'
	
		UPDATE TGP_FILE_ATTACHMENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		FILE_NAME=#entity.fileName#,
		RELATIVE_PATH=#entity.relativePath#
		WHERE
		FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='QualifiedAttachment'
	
        UPDATE TGP_SALES_TEAM
        SET
        TEAM_NAME = #entity.teamName# ,
        TEAM_CITY_ID = #entity.teamCityId# ,
        TEAM_CITY_NAME = #entity.teamCityName#,
        LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
        LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
        VERSION_ID = #entity.versionId# + 1
        WHERE
        SALES_TEAM_ID = #entity.id#
	
		UPDATE TGP_SALES_TEAM_AE_ASSN
        SET
        SALES_TEAM_ID = #entity.salesTeam.id# ,
        AE_ID = #entity.aeId# ,
        AE_NAME = #entity.aeName# ,
        LAST_UPDATOR_ID = #entity.lastUpdatorId# ,
        LAST_UPDATE_TIME = #entity.lastUpdateTime#
        WHERE
        SALES_TEAM_AE_ASSN_ID = #entity.id# ;
	
		UPDATE
		TGP_SERIAL_NUM_OPERATION_LOG
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VERSION_ID=#entity.versionId#+1,
		DEAL_ID=#entity.dealId#,

		EXPORTED_COUNT=#entity.exportedCount#

		WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberExportLog'
	
		UPDATE
		TGP_VISUAL_COMPONENT
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		CONFIGURABLE_BLOCK_ID=#entity.configurableBlock.id#,
		TEMPLATE_ID=#entity.templateId#,
		AREA_TYPE_ID=#entity.areaTypeId#
		WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextAreaListComponent'
	
        UpdateTime=UpdateTime
        WHERE DealGroupSettleID=#entity.id#
    
        UpdateTime=UpdateTime
        WHERE DealGroupID=#entity.dealGroupId#
    
        

		DELETE FROM TPDA_Settlement WHERE DealGroupSettleID=#entity.id#

        
    
        

		DELETE FROM TPDA_Settlement WHERE DealGroupID=#dealGroupId#

        
    
		UPDATE TG_Movie 
		SET MovieName = #movieInfoEntity.movieName#, 
		Director = #movieInfoEntity.director#,
		MainPerformer = #movieInfoEntity.mainPerformer#, 
		SPhoto = #movieInfoEntity.sPhoto#,
		SPhotoOriginal = #movieInfoEntity.sPhotoOriginal#,
		LPhoto = #movieInfoEntity.lPhoto#, 
		HPhoto = #movieInfoEntity.hPhoto#,
		ShowDate = #movieInfoEntity.showDate#,
		Sort = #movieInfoEntity.sort#,
		Area = #movieInfoEntity.area#,
		MovieDes = #movieInfoEntity.des#,
		MPrice = #movieInfoEntity.mPrice#, 
		Grade = #movieInfoEntity.grade#,
		Msg = #movieInfoEntity.msg#,
		Language = #movieInfoEntity.language#,
		Has3D = #movieInfoEntity.has3D#,
		HasIMAX = #movieInfoEntity.hasImax#,
		Minutes = #movieInfoEntity.minutes#
		WHERE
		MovieID = #movieId# 
	
		UPDATE TG_MovieShow
		SET ShopID = #movieShowInfoEntity.shopId#,
		ShowTime = #movieShowInfoEntity.showTime#,
		SaleEndTime = #movieShowInfoEntity.saleEndTime#,
		MovieID = #movieShowInfoEntity.movieId#,
		MovieName = #movieShowInfoEntity.movieName#,
		Language = #movieShowInfoEntity.language#,
		Status = #movieShowInfoEntity.status#,
		CityID = #movieShowInfoEntity.cityId#, 
		MarketPrice = #movieShowInfoEntity.marketPrice#,
		IsIMAX = #movieShowInfoEntity.isIMax#,
		Dimensional = #movieShowInfoEntity.dimension#,
		SeatCount = #movieShowInfoEntity.seatCount#,
		UpdateDate = Now(),
		DPPrice = #movieShowInfoEntity.dpPrice#,
		HallName = #movieShowInfoEntity.hallName#,
		ShowEndTime = #movieShowInfoEntity.showEndTime#,
		CostPrice = #movieShowInfoEntity.costPrice#,
        ActivityID = #movieShowInfoEntity.activityId#,
        TPHallID = #movieShowInfoEntity.tpHallId#,
        TPHallCode = #movieShowInfoEntity.tpHallCode#
		WHERE
		TPShowIndex = #tpShowIndex# and ThirdPartyID = #movieShowInfoEntity.thirdPartyId#
	
    
    
		UPDATE TG_RateReviewMapping
		SET
			RateID=#data.rateId#,
			ShopID=#data.shopId#,
			ReviewID=#data.reviewId#
		WHERE MappingID=#data.id#
	
        update TG_TravelReservation 
        set RefReceiptID=#entity.refReceiptId#,
        RefReceiptCode=#entity.refReceiptCode#,
        Status =#entity.status#,
        UpdateDate = now()         
        Where ReceiptID = #entity.receiptId#
    
		
	
        WHERE DealGroupId=#id#
    
        DELETE
        FROM TS_GuaranteePayTerm
        WHERE outBizID=#guaranteeFormId#;
    
        UPDATE TS_UseQuotaApply
        SET ReturnAmount = #returnAmount#,
            UpdateTime = NOW()
        WHERE ID = #id#;
    
		UPDATE TA_OrderJSON
		SET status = #status#
		WHERE id = #orderid#
	
		UPDATE TA_OrderJSON
		SET json = #json#
		WHERE id = #id#
	
		UPDATE TA_Order
		SET reviewed=#reviewed#, reviewdelay=#reviewdelay#, review=#review#, willing=#willing#
		WHERE id=#orderid#
	
	
	    UPDATE TA_Order
	    SET lateststatus=#latestStatus#
	    WHERE id=#orderId# and lateststatus=#status#
	
	    UPDATE TA_Order
	    SET paystatus=#payStatus#
	    WHERE id=#orderId#
	
		UPDATE TA_Order
	    SET payorderid=#payorderId#
	    WHERE id=#orderId#  and payorderid =0
	
		UPDATE TA_Order
		SET thirdpartyid=#thirdpartyid#
		WHERE shopkey=#shopkey#
	
		UPDATE TA_OrderJSON
		SET json=#json#, thirdpartyid=#thirdpartyid#
		WHERE id=#id#
	
		UPDATE TA_Order
	    SET reachtime=#reachTime#, noticetime=#noticeTime#
	    WHERE id=#orderId#
	
		update TA_UserContact set 
			userid=#userid#, name=#name#, address=#address#, cityid=#cityid#,
			phone=#phone#, edit_timestamp=#edittime#
		where id=#id#
	
		update TA_UserContact set 
			priority=#priority#, edit_timestamp=#edittime#
		where id=#id#
	
        UPDATE TPA_ProcessHistory SET
        Status = #status#
        WHERE ID = #ID#
    
        UPDATE
        TG_DealGroup SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
        DealGroupID = #dealGroupID# AND (MaxJoin - #reduceMaxJoin#) > 0
    
		UPDATE
		TG_EventPoint
		SET
		Point=Point+#point#,
		LastDate=now()
		WHERE
		UserId=#userId#
	
		
		UPDATE 
			TG_EventPoint 
		SET
			Point=Point-#point#,
			LastDate=now()
		WHERE
			UserId=#userId# AND Point>=#point#
		
	
	 
		UPDATE TG_EventPromotion 
		SET CurrentJoin = CurrentJoin + 1
		WHERE
		PromotionID = #promotionId# AND (MaxJoin = 0 OR MaxJoin > CurrentJoin)
	
	
		update TG_NaviTagItem set ItemName=#itemName#,Priority=#priority#  where ItemID=#itemId#
		
		UPDATE TG_NaviTagItem a, TG_NaviItemBrand b 
			SET a.Priority=0-b.SearchNum
		WHERE b.BrandEnName = a.ItemEnName
			AND b.cityId=#cityId#
			AND a.tagId=#tagId#;
	
        UPDATE TG_ReceiptGroupCode
        SET Status = 0
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId# and Status=1;
    
        UPDATE TG_ReceiptGroupCode
        SET Code = #code#
        WHERE ReceiptGroupCodeID = #receiptGroupCodeId#;
    
		UPDATE TG_DealRate
		SET
			Score=#score#,
			IsAgain=#isAgain#,
			IsExtra=#isExtra#,
			Comment=#comment#,
			ExtraMoney=#ExtraMoney#,
			Status=#status#,
			ExtendBiz=#extendBiz#,
			LastDate=now()
		WHERE RateID=#rateID#
	
       UPDATE TG_Invite SET STATUS = 1,LastDate = now() WHERE UserID = #userId# AND STATUS = 0;
  
       UPDATE TG_SharedLinkLog SET UserId = #userId#,UserFlag = #userFlag# ,Status = #status# ,UpdateDate = now() WHERE SharedLinkLogID = #sharedLinkLogId#  ;
  	
  	UPDATE TG_UserSMSStatus set IsSend=#isSend# WHERE UserID=#userID# AND SMSSendType=#smsSendType#
  
		UPDATE TG_WishList SET
			DealGroupID = #dealGroupId#,
			UserID = #userId#,
			Status = #status#,
			LastDate = NOW()
		WHERE WishListID = #id#
	
		UPDATE TG_WishList w
			SET  w.Status = 0,
				 w.LastDate = NOW()
		    WHERE w.UserID = #userId#
		    AND(
               	NOW()>
               		(
               		SELECT d.EndDate FROM TG_DealGroup d
               			WHERE w.DealGroupID = d.DealGroupID
               		 )
               	OR EXISTS
               		(
               		SELECT  e.DealGroupID  FROM TG_DealGroup e
               			WHERE e.MaxJoin <= e.CurrentJoin
               			AND w.DealGroupID = e.DealGroupID
               		)
                )
	
		UPDATE TG_WishList
			SET  Status = 0,
			LastDate = NOW()
		WHERE DealGroupID = #dealGroupId#
		AND UserID = #userId#
	
		UPDATE TG_WishRegion SET
			UserID = #userId#,
			CityID = #cityId#,
			RegionIDs = #regionIds#,
			UpdateDate = NOW()
		WHERE ID = #id#
	
		UPDATE TuanGouMobile.TGM_CommonToken 
		SET TokenStatus = #TokenStatus#
		WHERE ID = #ID#
	
		UPDATE TuanGouMobile.TGM_CommonToken 
		SET DeviceID = #DeviceID#
		WHERE ID = #ID#
	
		UPDATE TGM_PBPass SET
			HashCode = #hashCode#,
			UpdateTag = NOW(),
			UpdateCount = UpdateCount + 1,
			UpdateTime = NOW()
		WHERE ReceiptID = #receiptId#
	
		UPDATE TGM_PBPass SET
			UpdateTime = NOW()
		WHERE ReceiptID = #receiptId#
	
		UPDATE TGM_PBPass SET
			IsRegistered = #isRegistered#
		WHERE SerialNumber = #serialNumber#
	
	   UPDATE 
		  TGE_CompanyGiftChannel 
		SET
		  Consumption = Consumption + 1 
		WHERE ChannelName = #channelName#
		  AND Consumption < Stock 
	
		UPDATE
		TG_EventPoint
		SET
		Point=Point+#point#,
		LastDate=now()
		WHERE
		UserId=#userId#
	
		
		UPDATE 
			TG_EventPoint 
		SET
			Point=Point-#point#,
			LastDate=now()
		WHERE
			UserId=#userId# AND Point>=#point#
		
	
		UPDATE TGE_SecondPrize SET
			Name = #data.name#,
			BeginDate = #data.beginDate#,
			EndDate = #data.endDate#,
			BannerImage = #data.bannerImage#,
			Status = #data.status#,
			Priority = #data.priority#,
			Type = #data.type#,
			TypeReferID = #data.typeReferId#,
			Price = #data.price#,
			MarketPrice = #data.marketPrice#,
			MaxJoin = #data.maxJoin#,
			CurrentJoin = #data.currentJoin#,
			UpdateDate = NOW()
		WHERE SecondPrizeID = #data.id#
	

		UPDATE TGE_SecondPrize SET
			CurrentJoin = CurrentJoin + #quantity#,
			UpdateDate = NOW()
		WHERE SecondPrizeID = #id#
		  AND CurrentJoin < MaxJoin
        
	
		update 
			TGEvent_Template_Coupon_Record
		set
			Maxjoin=#maxJoin#,
			UpdateTime=#updateTime#
		where
			UserID=#userId#
	
		update 
			TGEvent_Template_Coupon_Record
		set
			Currentjoin=#currentJoin#
		where
			UserID=#userId#
	
		update 
			TGEvent_Template_Strategy
		set
			Currentjoin=#currentJoin#
		where
			CouponGroupID=#couponGroupId# and EventID=#eventId# and BeginDate=#beginDate#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			BannerImage=#bannerImage:VARCHAR:No Image#,
			Status=#status#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_Topic 
		set 
			TopicName=#topicName#,
			TopicEnName=#topicEnName#,
			BeginDate=#beginDate#,
			EndDate=#endDate#,
			Status=#status#
		where
			TopicID=#topicID#
	
		update 
			TG_Event_TopicItemCity
		set
			DealGroupIDs=#dealGroupIDS#
		where
			TopicItemID=#topicItemID# and CityID=#cityId#
			
	
		update 
			TG_Event_TopicItem
		set
			TopicItemName=#topicItemName#,
			DisplayCount=#displayCount#,
			Priority=#priority#
		where
			TopicItemID=#topicItemID#
	
          update TG_Coupon C
          set C.UsedStatus= #usedStatus#,
		  C.UsedDate = now()
		  where C.CouponID=#couponID#
  	
	UPDATE TG_DealCard 
	SET Name = #name#,
	  CardType = #cardType#,
	  Background = #background#,
	  Commission = #commission#,
	  CardGroupID = #groupID#,
  	  CardMode = #mode#,
	  CardPrice = #price#,
	  UpdateTime = NOW()
	WHERE DealID = #dealID#
  
		UPDATE TG_DealGroup SET
		DealGroupShortTitle = #dealGroupShortTitle#,
		DealGroupTitleDesc =
		#dealGroupTitleDesc#,
		DealGroupTitle = #dealGroupTitle#,
		DealGroupName =
		#dealGroupName#,
		DealGroupPrice = #dealGroupPrice#,
		MarketPrice =
		#marketPrice#,
		DefaultPic = #defaultPic#,
		HighLight = #highLight#,
		DealGroupInfo = #dealGroupInfo#,
		ShopInfo = #shopInfo#,
		BeginDate =
		#beginDate#,
		EndDate = #endDate#,
		MaxPerUser =
		#maxPerUser#,
		RemainDisplayPercent = #remainDisplayPercent#,
		IsSideDeal =
		#isSideDeal#,
		DealGroupSortID = #dealGroupSortId#,
		Status = #status#,
		PublishStatus = #publishStatus#,
		IsReward =
		#isReward#,
		EditorInfo =
		#editorInfo#,
		ImportantPoint =
		#importantPoint#,
		MemberInfo =
		#memberInfo#,
		SpecialPoint =
		#specialPoint#,
		ProductInfo = #productInfo#,
		LotteryStatus =
		#lotteryStatus#,
		QRCodeValidate = #qRCodeValidate#,
		DealAltName =
		#dealAltName#,
		AutoRefundSwitch = #autoRefundSwitch#,
		IsCanUseCoupon =
		#isCanUseCoupon#,
		DealGroupPics = #dealGroupPics#,
		IsECommerceWebSite =
		#isECommerceWebSite#,
		SaleChannel = #saleChannel#,
		UpdateDate = NOW(),
		ProcessMemo = #processMemo#,
		RefundMemo =
		#refundMemo#,
		ProductMemo = #productMemo#,
		ShopMemo = #shopMemo#,
		DealGroupAbstract = #dealGroupAbstract#,
		DealGroupSummary =
		#dealGroupSummary#,
		MinPerUser = #minPerUser#,
		ThirdPartVerify =
		#thirdPartVerify#
		WHERE DealGroupID = #id#
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin + #quantity# WHERE
		DealGroupID = #id#
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin - #quantity#,MaxJoin =
		MaxJoin - #maxjoinq# WHERE
		DealGroupID = #id#
	
		UPDATE TG_DealGroup
		SET FinishDate = NOW() WHERE DealGroupID = #id#
	
		UPDATE TG_DealGroup
		SET UpdateDate = NOW() WHERE DealGroupID = #id#
	
		UPDATE TG_DealGroup
		SET FinishDate=#finishDate# WHERE DealGroupID = #id#
	
		UPDATE TG_DealGroup
		SET MaxJoin = (SELECT SUM(MaxJoin) FROM TG_Deal WHERE DealGroupID =
		#id#),
		OldMaxJoin = (SELECT SUM(OldMaxJoin) FROM TG_Deal WHERE DealGroupID = #id#)
		WHERE DealGroupID = #id#;
	
		UPDATE TG_DealGroupExtend SET
		UpdateDate = NOW(),
		IsShowDealGroupRegion = #isShowDealGroupRegion#,
		IsAsyncReceipt = #isAsyncReceipt#,
		ActivationType = #activationType#,
		AheadHours = #aheadHours#,
		EditorTeam = #editorTeam#,
		QQ = #qq#,
		SourceID = #sourceId#,
		IsBlockStock = #isBlockStock#
		WHERE DealGroupID = #id#
	
    
    update TG_ReceiptPool
    set Status = #status:INTEGER#
    where SerialNumber = #serialNumber:VARCHAR#
  
    
    update TG_ReceiptPool
    set Status = #status:INTEGER#
    where ReceiptPoolID = #receiptPoolID#
  
	UPDATE TG_Vendor SET
		DealID = #id#,
		ShopIDList = #shopIDList#,
	    Account = #account#,
		PassWord = #passWord#,
		Mobile = #mobile#,
		Mobile2 = #mobile2#
	WHERE DealID = #id#

		UPDATE TGM_Announcement 
		SET Title = #Title#, PageUrl = #PageUrl#, BeginTime = #BeginTime#, 
			EndTime = #EndTime#, CityIDList = #CityIDList#, Priority = #Priority#
		WHERE AnnouncementID = #AnnouncementID#
	
		UPDATE TGM_AnnoucementClient 
		SET Flag = 0
		WHERE Flag = 1 AND AnnouncementID = #AnnouncementID#
	
        UPDATE TE_AdPosition 
        SET Title=#adPosition.title#, Name=#adPosition.name#, `Desc`=#adPosition.desc#, ChannelID=#adPosition.channelID#,
        	ContainerID=#adPosition.containerID#, Width=#adPosition.width#, Height=#adPosition.height#, 
        	Type=#adPosition.type#, Status=#adPosition.status#, Author=#adPosition.author#,PublishType=#adPosition.publishType#, AddTime=NOW(), UpdateTime=NOW()
        WHERE ID=#adPosition.id#
    
        UPDATE TE_AdPosition
        SET Status=#adPositionStatus#,UpdateTime=NOW()
        WHERE ID=#adPositionId#
    
        UPDATE TG_Event_RecommendKeyWord SET
            KeyWord = #keyWordDto.keyWord#,
            KeyWordEnName = #keyWordDto.keyWordEnName#,
            URL = #keyWordDto.URL#,
            Status = #keyWordDto.status#,
            BeginDate = #keyWordDto.beginDate#,
            EndDate = #keyWordDto.endDate#,
            UpdateDate = NOW()
        WHERE ID = #keyWordDto.ID#
    
        UPDATE TG_Event_RecomKeyWordCity SET
            Priority = #priority#
        WHERE ID = #id#
    
		UPDATE
			TG_OpUser
		SET
			Status=0
		WHERE 
			UserName=#username# 
	
		UPDATE
			TG_OpUser
		SET
			UserName=#user.username#, NickName=#user.nickname#, Department=#user.department#,
			Email=#user.email#, PhoneNumber=#user.mobile#, UserNumber=#user.userNo#, Status=#user.status#, UserType=#user.type#
		WHERE
			UserName=#user.username# 
	
		update 
			TG_Event_Topic
		set
			TopicName=#topicName#,
			Brief=#brief#,
			Logo=#logo#
		where
			TopicID=#topicId#
	
		update 
			TG_Event_Topic
		set
			TopicName=#topicName#,
			Brief=#brief#
		where
			TopicID=#topicId#
	
		update 
			TG_Event_TopicCity
		set
			Status=#status#,
			EntryValue=#entryValue#
		where
			TopicID=#topicId# and CityID=#cityId#
	
		update 
			TG_Event_TopicCity
		set
			Status=#status#
		where
			ID=#id#
	
	
		UPDATE TG_EventSendDeal 
		SET
		STATUS = #status# 	
		WHERE
		ID = #id#
	
	 
		UPDATE
			GPA_OnlineActivityReward
		SET
			MinContentCount = #onlineActivityReward.minContentCount#,
			RewardType = #onlineActivityReward.rewardType#,
			RewardContent = #onlineActivityReward.rewardContent#,
			Status=#onlineActivityReward.status#,
			UpdateTime = NOW()
		WHERE
			ID=#onlineActivityReward.id#
	 
		UPDATE
			GPA_OnlineActivityReward
		SET
			Status = #status#
		WHERE
			ID=#id#
	
        UPDATE MC_MemberCardBossAccountInfo b
        SET b.CompanyManager = #bossData.companyManagerName#, b.CompanyManagerTel = #bossData.companyManagerTel#, b.CompanyManagerPosition = #bossData.companyManagerPosition#, b.CompanyManagerMail = #bossData.companyManagerMail#, b.BossAccountUpdateTime = #bossData.bossAccountUpdateTime#
        WHERE b.CrmID = #bossData.crmID#
    
        UPDATE MC_MemberCardBossAccountInfo b
        SET b.BossAccountCreateStatus=#bossAccountCreateStatus#, b.BossAccountCreateAuditInfo=#bossAccountCreateAuditInfo#, b.AuthAdminID=#authAdminId#, b.AuthAdminName=#authAdminName#
        WHERE b.CrmID = #crmID#
    
		
			UPDATE MC_MemberCardProduct p
			SET p.Status = #status#,p.AuditInfo = #auditInfo#
			WHERE p.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProduct p
			SET p.AuditInfo = #auditInfo#
			WHERE p.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProduct_Draft p
			SET p.AuditInfo = #auditInfo#
			WHERE p.ProductDraftID = #productDraftId#
			
	
			WHERE p.ProductID = #productId#
	
			WHERE p.ProductDraftID = #productDraftId#
	
		
			UPDATE MC_MemberCardProductDiscount d
			SET d.DiscountRate = #discount#
			WHERE d.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProductDiscount_Draft d
			SET d.DiscountRate = #discount#
			WHERE d.ProductDraftID = #productDraftId#
			
	
		
			UPDATE MC_MemberCardProduct p
			SET p.Status = #toStatus#
			WHERE p.MemberCardID = #memberCardId# AND p.Status = #fromStatus#
			
	
		
			UPDATE MC_MemberCardProductMallPromo d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#
			WHERE d.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProductMallPromo_Draft d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#
			WHERE d.ProductDraftID = #productDraftId#
			
	
		
			UPDATE MC_MemberCardProductMallShopPromo d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#,d.ShopType = #shopType#,d.Floor = #floor#,d.Building = #building#
			WHERE d.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProductMallShopPromo_Draft d
			SET d.PromoTitle = #promoTitle#,d.PromoDesc = #promoDesc#,d.Tel = #tel#,d.ShopType = #shopType#,d.Floor = #floor#,d.Building = #building#
			WHERE d.ProductDraftID = #productDraftId#
			
	
		
			UPDATE MC_MemberCardProductShop s
			SET s.ShopID = #shopID#,s.MemberCardID = #memberCardID#,s.Status = #status#
			WHERE s.ProductID = #productId#
			
	
		
			UPDATE MC_MemberCardProduct_Draft p
			SET p.Status = #toStatus#,
				p.AuditInfo = #auditInfo#
        	WHERE p.ProductDraftID = #productDraftId#
			
	
        	WHERE f.FeedID = #feedId#
	
        UPDATE DianPing.DP_MyListShop
        SET Reason = #reason# , Sort = #sort#
        WHERE ListID=#listId# AND ShopId= #shopId#
    
		UPDATE {tableName} SET
{updateList}
		WHERE {firstField} = #data.id#
	
		update
			TGE_DealBuyCouponSendLog
		set
			Status = #status#
		where 
			LogId = #logId#
	
		UPDATE TG_EventPromo SET CouponRuleID=#ruleid# WHERE EventID=#eid#
	
		UPDATE TG_Event SET Status=#status# WHERE EventID=#eid#
	
		UPDATE TG_Event SET TagPushed=1,Status=1 WHERE EventID=#eid#
	
		UPDATE
			TG_EventCity
		SET 
			Status=2
		WHERE
			EventID=#eid#
	
		UPDATE 
			TG_EventDeal
		SET 
			Status=4
		WHERE
			EventID=#eid#
	
	
    	         
         UPDATE TG_HotelRecommendScore 
         SET Status = 0, AddTime = NOW() 
         WHERE CityID = #cityID# 
    	
    
    	         
         UPDATE TG_HotelRecommendScore 
         SET Score = #score# + SaleScore, Status = 1, AddTime = NOW(), HotelType = #type#
         WHERE DealGroupID = #dealGroupID#  AND CityID = #cityID# AND Score < 500 AND Score>=0
    	
    
          
         UPDATE TG_HotelRecommendScore 
         SET Status = 1, AddTime = NOW(), HotelType = #type#
         WHERE DealGroupID = #dealGroupID# AND CityID = #cityID# AND (Score >=500 OR Score <0)
         
    
    	 
         UPDATE TG_HotelRecommendScore 
         SET Sales = #sales#, AddTime = NOW()
         WHERE DealGroupID = #dealGroupID# AND CityID = #cityID#
         
    
    	 
         UPDATE TG_HotelRecommendScore 
         SET SaleScore = #salesScore# + SaleScore, AddTime = NOW()
         WHERE DealGroupID = #dealGroupID# AND CityID = #cityID#
         
     
	
        UPDATE TGHT_DeliverExpressBatchDetail d
        SET d.Status = #status#
        WHERE d.DeliverExpressNoID = #deliverExpressNoId#;
    
        UPDATE TG_Deliver d,TGHT_DeliverExpressBatchDetail b
        SET d.ExpressCheckStatus = #status#
        WHERE d.OrderID = b.OrderID AND b.DeliverExpressNoID = #deliverExpressNoId#;
    
        UPDATE TGHT_DeliverExpressBatchDetail d
        SET d.Memo = #memo#,d.UpdateDate = NOW(),d.Status = #status#
        WHERE d.DeliverExpressNoID = #deliverExpressNoId#;
    
        UPDATE TGHT_DeliverExpressBatchDetail d
        SET d.ScanDate = #scanDate#
        WHERE d.DeliverExpressNoID = #deliverExpressNoId#;
    
        UPDATE TGHT_DeliverExpressBatchDetail d
        SET d.Memo='',d.UpdateDate = Now(),d.Status =0
        WHERE d.BatchID = #batchId# AND d.Status !=1;
    
        UPDATE TGHT_DeliverExpressBatchDetail e
        SET e.UpdateDate = NOW(), e.Memo='ok'
        WHERE e.BatchID =#batchId#;
    
		update TG_MobileTrigger
		set flag = #flag#, update_dt = #update_dt#
		where ID = #id#
	
        update TG_NaviTagItem set Status=#status#  where ItemID=#itemId#
    
		UPDATE 
			TG_EventPrize 
		SET
			PrizeName=#eventPrize.prizeName#,
			PrizeImage=#eventPrize.prizeImage#,
			PrizeDesc=#eventPrize.prizeDesc#,
			PrizeType=#eventPrize.prizeType#,
			Stocks=#eventPrize.stocks#,
			Odds=#eventPrize.odds#,
			Priority=#eventPrize.priority#,
			Consumption=#eventPrize.consumption#,
			SmsText=#eventPrize.smsText#,
			BeginDate=#eventPrize.beginDate#,
			EndDate=#eventPrize.endDate#,
			Status=#eventPrize.status#,
			EventId=#eventPrize.eventId#,
			Config=#eventPrize.config#
		WHERE
			PrizeId=#eventPrize.prizeId#
	
		
		UPDATE 
			TG_EventPrize 
		SET
			Consumption=(Consumption+1)
		WHERE
			PrizeId=#prizeId# AND (Stocks=0 OR Stocks>Consumption)
		
	
		
			UPDATE 
				TG_EventPrizeUser force index (idx_userid,idx_mobile)
			SET 
				CurrentJoin=CurrentJoin+1 
			WHERE 
				EventID=#prizeUser.eventId# AND (UserID=#prizeUser.userId# OR UserMobile = #prizeUser.mobile#) AND CurrentJoin<MaxJoin AND MaxJoin>0 AND Entrance=#prizeUser.entrance# AND Period=#prizeUser.period#
		
	
        
            UPDATE 
                TG_EventPrizeUser force index (idx_userid,idx_mobile)
            SET 
                CurrentJoin=CurrentJoin+1 
            WHERE 
                EventID=#prizeUser.eventId# AND (UserID=#prizeUser.userId# OR UserMobile = #prizeUser.mobile#) AND Entrance=#prizeUser.entrance# AND Period=#prizeUser.period#
        
    
		
			UPDATE 
				TG_EventUserPrize force index (idx_userid,idx_mobile)
			SET 
				Value=Value+#log.prizeValue#, Amount=Amount+1
			WHERE 
				EventID=#log.eventId# AND (UserID=#log.userId# OR UserMobile = #log.userMobile#) AND Amount<#maxValue# AND PrizedDate=#log.period#
		
	
		UPDATE 
			TG_EventPrizeUser force index (idx_userid,idx_mobile)
		SET 
			MaxJoin = #prizeUser.maxJoin# 
		WHERE
			EventID=#prizeUser.eventId# AND (UserID=#prizeUser.userId# OR UserMobile=#prizeUser.mobile#) 
			AND Period=#prizeUser.period# AND Entrance=#prizeUser.entrance#
	
	   UPDATE 
	       TG_EventUserPrizeLog_Extend 
	   SET 
	       SendStatus = #sendStatus#,AwardDealID =#awardDealId#, Memo=#memo#
	   WHERE 
	       UserAwardID = #awardId#
	
		UPDATE TGM_PBPass SET
			HashCode = #hashCode#,
			UpdateTag = NOW(),
			UpdateCount = UpdateCount + 1,
			UpdateTime = NOW()
		WHERE ReceiptID = #receiptId#
	
		UPDATE TGM_PBPass SET
			UpdateTime = NOW()
		WHERE ReceiptID = #receiptId#
	
		UPDATE TGM_PBPass SET
			IsRegistered = #isRegistered#
		WHERE SerialNumber = #serialNumber#
	
		UPDATE TGM_UserBadgeReward 
		SET IsUsed = TRUE, UsedTime = NOW() 
		WHERE UserID = #UserID# AND RewardID = #RewardID#
	
		INSERT INTO TGM_UserBadgeReward 
		 (BadgeID, UserID, RewardID) 
		VALUES 
		 (#BadgeID#, #UserID#, #RewardID#)
	
		UPDATE TG_EmailPush
		SET Status = #status#,
			UserID = #userId#,
			UpdateDate = #updateDate#,
			AddMode = #addMode#
		WHERE
			EmailPushId = #emailPushId#
	
        UPDATE
        TG_Deal SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
        DealID = #dealID# AND (MaxJoin - #reduceMaxJoin#) > 0
    
		UPDATE TG_Deal  SET CurrentJoin = #currentJoin#
		WHERE DealID = #dealId#
	
		UPDATE TG_Deal  SET FinishDate = NOW()    WHERE DealID = #dealId#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
		UPDATE
		TG_DealGroup SET MaxJoin = MaxJoin - #reduceMaxJoin# WHERE
		DealGroupID = #dealGroupID# AND (MaxJoin - #reduceMaxJoin#) > 0
	
		UPDATE
		TG_DealGroup SET CurrentJoin = CurrentJoin - #reduceCurrentJoin# WHERE
		DealGroupID = #dealGroupId# AND CurrentJoin <= MaxJoin
		AND (CurrentJoin - #reduceCurrentJoin#) > -1
	
		UPDATE
		TG_DealGroup SET CurrentJoin = #currentJoin#
		WHERE DealGroupID = #dealGroupId#
	
		UPDATE TG_DealGroup
		SET FinishDate = NOW() WHERE DealGroupID = #dealGroupId#
		AND CurrentJoin >= MaxJoin AND MaxJoin > 0
	
		UPDATE
		TG_DealGroupRemind
		SET
		Status=0
		WHERE
		RemindId in ($remindIds$) AND
		UserId=#userId#
	
		UPDATE
		TG_DealGroupRemind
		SET
		Status=0
		WHERE
		ShopGroupId in ($shopGroupIds$) AND
		UserId=#userId#
	
		update TG_NaviTagItem set ItemName=#itemName#,Priority=#priority#  where ItemID=#itemId#
		
		UPDATE TG_NaviTagItem 
			SET STATUS=0
		WHERE
			ItemId=#itemId#
		
		UPDATE TG_NaviTagItem a, TG_NaviItemBrand b 
			SET a.Priority=0-b.SearchNum
		WHERE b.BrandEnName = a.ItemEnName
			AND b.cityId=#cityId#
			AND a.tagId=#tagId#;
	
        UPDATE TG_CATEGORY
        SET
            PARENT_CATEGORY_ID=#entity.parentId#,
            NAME=#entity.name#,
            VIEW_PRIORITY=#entity.viewPriority#,
            STATUS_ID=#entity.statusId#,
            LEVEL=#entity.level#,
            CREATOR_ID=#entity.creatorId#,
            CREATE_TIME=#entity.createTime#,
            LAST_UPDATOR_ID=#entity.lastUpdatorId#,
            LAST_UPDATE_TIME=#entity.lastUpdateTime#
        WHERE
            CATEGORY_ID=#entity.id#;
	
        WHERE DealGroupCityID=#entity.id#
    
		UPDATE TGP_DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN
		SET
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		DOCUMENT_TEMPLATE_ID=#entity.documentTemplate.id#,
		NAV_CATEGORY_ID=#entity.categoryId#
		WHERE
		DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN_ID=#entity.id#
	
		UPDATE TGP_STATEMENT_ATTRIBUTE_OPTION
		SET
		STATEMENT_ATTRIBUTE_ID=#entity.statementAttribute.id#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		VALUE=#entity.value#
		WHERE
		STATEMENT_ATTRIBUTE_OPTION_ID=#entity.id#
	
		UPDATE
		TGP_CONFIGURABLE_BLOCK
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		TYPE_ID=#entity.typeId#,
		VISUAL_VIEW_ID=#entity.visualView.id#,
		TITLE=#entity.title#,
		TEMPLATE_ID=#entity.templateId#
		WHERE
		CONFIGURABLE_BLOCK_ID=#entity.id#
	
		UPDATE TGP_CONTACT
		SET
		DEAL_GROUP_ID = #entity.dealGroup.id# ,
		CONTACT_NAME = #entity.contactName# ,
		CONTACT_EMAIL = #entity.contactEmail# ,
		CONTACT_MP = #entity.contactMP# ,
		CONTACT_TYPE = #entity.contactType# ,
		LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
		LAST_UPDATOR_ID = #entity.lastUpdatorId#
		WHERE CONTACT_ID=#entity.id#
	
		UPDATE TGP_DEAL
		SET
		DEAL_GROUP_ID=#entity.dealGroup.id#,
		SHORT_TITLE=#entity.shortTitle#,
		ORIGINAL_PRICE=#entity.originalPrice#,
		RETAIL_PRICE=#entity.retailPrice#,
		COST_PRICE=#entity.costPrice#,
		MAX_STOCK_QTY=#entity.maxStockQty#,
		MAX_SALE_QTY=#entity.maxSaleQty#,
		MIN_SALE_QTY=#entity.minSaleQty#,
		SEQ=#entity.sequence#,
		IS_DEFAULT=#entity.isDefault#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		PRODUCT_ID_IN_IMSS=#entity.productIdInImss#,
		DUPLICATED_SERIALNUMBER_COUNT_IN_LASTBATCH=#entity.duplicatedSerialNumberCountInLastBatch#,
		TOTAL_IMPORTED_SERIALNUMBER_COUNT=#entity.totalImportedSerialNumberCount#,
		RECEIPT_CONTACT_INFO=#entity.receiptContactInfo#,
        BANK_ACCOUNT_GLOBAL_ID=#entity.bankAccountGlobalId#,
        IS_ACTIVE=#entity.isActive#,
        DISPLAY_TYPE=#entity.displayType#,
        DEAL_TYPE=#entity.dealType#
		WHERE
		DEAL_ID=#entity.id#
	
	    UPDATE TGP_DEAL D
		LEFT JOIN TGP_DEAL_GROUP DG ON DG.DEAL_GROUP_ID=D.DEAL_GROUP_ID
		LEFT JOIN TGP_CONTRACT C ON C.CONTRACT_ID=DG.CONTRACT_ID
		SET D.BANK_ACCOUNT_GLOBAL_ID=#bankAccountGlobalId#
		WHERE C.CONTRACT_CRM_ID=#contractCrmId#;
        
        
    
	

		UPDATE TGP_DEAL_GROUP
		SET 
			BUSINESS_TYPE_ID=#entity.businessTypeId#,
			DAYS_AFTER_INEFFECTIVE=#entity.daysAfterIneffective#,
			DAYS_AFTER_ONLINE=#entity.daysAfterOnline#,
			DAYS_BEFORE_EFFECTIVE=#entity.daysBeforeEffective#,
			EFFECTIVE_END_DATE=#entity.effectiveEndDate#,
			EFFECTIVE_END_TYPE=#entity.effectiveEndType#,
			IS_DELIVERY_REQUIRED=#entity.isDeliveryRequired#,
			IS_REFUNDABLE=#entity.refundableStatus#,
			IS_VOUCHER_AVAILABLE=#entity.isVoucherAvailable#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			PUBLISH_FROM_DATE=#entity.publishFromDate#,
			PUBLISH_TO_DATE=#entity.publishToDate#,
			DISTRIBUTION_PARTY_ID=#entity.distributionPartyId#,
			IS_TAX_INVOICE_AVARIABLE=#entity.isTaxInvoiceAvailable#,
			VERSION_ID=#entity.versionId#+1,
			CONTRACT_ID=#entity.contractId#,
			IS_RELEASED_TO_ALL_CITIES=#entity.isReleasedToAllCities#,
			IS_VOUCHER_SETTED=#entity.isVoucherSetted#,
			VOUCHER_FORMAT_ID=#entity.voucherFormatId#,
		    VERIFICATION_DEVICE_ID=#entity.verificationDeviceId#,
		    REMARKS=#entity.remarks#,
		    OTHER_EXCEPT_DATE = #entity.otherExceptDate#,
		    MAX_SALE_QTY=#entity.maxSaleQty#,
		    MIN_SALE_QTY=#entity.minSaleQty#,
		    COMPANY_ID_IN_IMSS=#entity.companyIdInImss#,
		    THIRD_PARTY_VERIFY_PROVIDER_ID=#entity.thirdPartyVerifyProviderId#,
		    IS_MANUAL_SET_REFUND= #entity.isManualSetRefund#,
			IS_HIDEN=#entity.isHiden#,
			PUBLISH_STATUS_ID=#entity.publishStatusId#,
			CUSTOMER_SERVICE_QQ=#entity.customerServiceQQ#,
			EFFECTIVE_BEGIN_DATE=#entity.effectiveBeginDate#,
			EFFECTIVE_BEGIN_TYPE=#entity.effectiveBeginType# ,
			BRIEF_DESC_DETAIL=#entity.briefDescription.detailedInformation#,
            BRIEF_DESC_SPECIAL_REMINDER=#entity.briefDescription.specialReminder#,
            BRIEF_DESC_PRD_INTRDCTN=#entity.briefDescription.productIntroduction#,
            BRIEF_DESC_SHOP_INFO=#entity.briefDescription.shopInformation#,
            BRIEF_DESC_SHOP_INTRDCTN=#entity.briefDescription.shopIntroduction#,
            BRIEF_DESC_COMMENTS=#entity.briefDescription.comments#,
            IS_EDITOR_REQUIRED=#entity.isEditorRequired#,
            IS_VALID=#entity.isValid#,
            PARENT_DEAL_GROUP_ID=#entity.parentDealGroupId#,
            DAYS_AFTER_ONLINE_OF_PUBLISH_TO_DATE=#entity.daysAfterOnlineOfPublishToDate#,
            PUBLISH_TO_TYPE=#entity.publishToType#,
            BRIEF_DESC_PROCESS_FOR_USE=#entity.briefDescription.processForUse#,
            IS_AUTO_DELAY=#entity.isAutoDelay#,
            REFUND_REASON=#entity.refundReason#
		WHERE
			DEAL_GROUP_ID=#entity.id#

        
	
	

		DELETE FROM TGP_DEAL_GROUP WHERE DEAL_GROUP_ID=#entity.id#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET EDITOR_ID = #editorId#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET IS_AE_ASSIGNED = #isAeAssigned#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET MAINTAINER_ID = #maintainerId#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET STATUS_ID = #statusId#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET IS_SUBMIT_EDITOR = #isSubmitEditor#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET SUBMIT_FOR_MERCHANT_CONFIRM_DATE = #submitForMerchantConfirmDate#, LAST_UPDATE_TIME = NOW()
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET APPROVE_BY_MERCHANT_CONFIRM_DATE = #approveByMerchantConfirmDate#, LAST_UPDATE_TIME = NOW()
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET IS_HIGH_PROCESS_LEVEL = #isHighProcessLevel#, IS_HIGH_LEVEL_APPLIED_FOR = #isHighLevelAppliedFor#, LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
    

		UPDATE TGP_DEAL_GROUP SET
		  EDITOR_ID = #editorId#,
		  IS_EDITOR_REQUIRED = #isEditorRequired#,
		  LAST_UPDATE_TIME = #lastUpdateTime#
		WHERE DEAL_GROUP_ID = #dealGroupId#

        
	
	  
        UPDATE TGP_DEAL_GROUP DG
        INNER JOIN TGP_DEAL_GROUP_VERSION V ON V.DEAL_GROUP_ID=DG.DEAL_GROUP_ID
        SET DG.PUBLISH_STATUS_ID=3
        WHERE V.PUBLISH_TO_DATE<CURDATE() AND DG.PUBLISH_STATUS_ID=2;
        
	
	  

		UPDATE TGP_DEAL_GROUP SET IS_VALID = #isValid# WHERE DEAL_GROUP_ID = #dealGroupId#;

        
	
	  

		UPDATE TGP_DEAL_GROUP SET PUBLISH_STATUS_ID=#publishStatusId# WHERE DEAL_GROUP_ID = #dealGroupId#;

        
	
    

		UPDATE TGP_DEAL_GROUP
		SET PUBLISH_FROM_DATE=#publishFromDate#
		WHERE DEAL_GROUP_ID=#dealGroupId#

        
	
        UPDATE TGP_DEAL_GROUP_ADMISSION
        SET
            DEAL_GROUP_ID = #entity.dealGroupId# ,
            IS_ADMISSION_REQUIRED= #entity.isAdmissionRequired# ,
            AMOUNT=  #entity.amount# ,
            STATUS_ID=#entity.statusId#,
            CREATE_TIME = #entity.createTime# ,
            CREATOR_ID = #entity.creatorId# ,
            LAST_UPDATE_TIME = #entity.lastUpdateTime# ,
            LAST_UPDATOR_ID = #entity.lastUpdatorId#
          WHERE
            ADMISSION_ID = #entity.id#;
	
		UPDATE
		TGP_DEAL_GROUP_CITY_ASSN
		SET
		
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		CITY_ID=#entity.cityId#,
		DEAL_GROUP_ID=#entity.dealGroup.id#
		WHERE
		DEAL_GROUP_CITY_ASSN_ID=#entity.id#
	 
	 
		UPDATE TGP_DEAL_GROUP_NAV_CATEGORY_ASSN
		SET 
			DEAL_GROUP_ID=#entity.dealGroup.id#, 
			NAV_CATEGORY_ID=#entity.categoryId#, 
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			IS_DEFAULT=#entity.isDefault#
		WHERE
			DEAL_GROUP_NAV_CATEGORY_ASSN_ID=#entity.id#
	
	
	

		UPDATE TGP_DEAL_GROUP_WORKFLOW_HISTORY
		SET
			DEAL_GROUP_ID=#entity.dealGroupId#,
			PROCESS_INSTANCE_ID=#entity.processInstanceId#,
			OPERATION_ID=#entity.operationId#,
			LAST_UPDATOR_ID=#entity.lastUpdatorId#,
			LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		WHERE
			DEAL_GROUP_ID=#entity.id#

        
	
        
		UPDATE TGP_EDITOR SET
             WORKLOAD_ASSIGNED = WORKLOAD_ASSIGNED + 1,
             LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE  EDITOR_ID = #editorId#
		
	
        
		UPDATE TGP_EDITOR SET
		     WORKLOAD_TODAY = #workloadToday#,
             WORKLOAD_ASSIGNED = #workloadAssigned#,
             LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE  EDITOR_ID = #editorId#
		
	
        UPDATE TGP_EDITOR
        SET EDITOR_ID = #entity.editorId#,
          EDITOR_NAME = #entity.editorName#,
          EDITOR_TEAM_ID = #entity.editorTeamId#,
          WORKLOAD = #entity.workload#,
          WORKLOAD_TODAY = #entity.workloadToday#,
          WORKLOAD_ASSIGNED = #entity.workloadAssigned#,
          IS_ACTIVE = #entity.isActive#,
          LAST_UPDATOR_ID = #entity.lastUpdatorId#,
          LAST_UPDATE_TIME = #entity.lastUpdateTime#
        WHERE ID = #entity.id#;
    
        
		UPDATE TGP_EDITOR SET
             WORKLOAD_ASSIGNED = WORKLOAD_ASSIGNED - 1,
             LAST_UPDATE_TIME = #lastUpdateTime#
        WHERE  EDITOR_ID = #editorId#
          AND  WORKLOAD_ASSIGNED > 0
		
	
		UPDATE
		TGP_SLIDE_PICTURE
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		SEQ=#entity.sequence#,
		URL=#entity.url#,
		VISUAL_VIEW_ID=#entity.visualView.id#
		WHERE
		SLIDE_PICTURE_ID=#entity.id#
	
		UPDATE
		TGP_VISUAL_VIEW
		SET
		LAST_UPDATE_TIME=#entity.lastUpdateTime#,
		LAST_UPDATOR_ID=#entity.lastUpdatorId#,
		VERSION_ID=#entity.versionId#+1,
		JSON_CLOSURE=#entity.jsonClosure#,
		DEAL_GROUP_ID=#entity.dealGroupId#,
		TITLE=#entity.title#,
		DESCRIPTION=#entity.description#,
		IS_COMMERICAL_AREA_DISPLAYED=#entity.isCommericalAreaDisplayed#,
		CORP_DESCRIPTION= #entity.corperationInfo.description#,
		CORP_NAME = #entity.corperationInfo.name#,
		CORP_IS_EXPANDED = #entity.corperationInfo.isExpanded#,
		SHORT_DESC = #entity.shortDescription#,
		COMMENTS = #entity.comments#,
		PARENT_VISUAL_VIEW_ID=#entity.parentVisualViewId#
		WHERE
		VISUAL_VIEW_ID=#entity.id#
	
		UPDATE TG_DealRate
		SET
			Score=#data.score#,
			IsAgain=#data.isAgain#,
			IsExtra=#data.isExtra#,
			Comment=#data.comment#,
			ExtraMoney=#data.extraMoney#,
			Status=#data.status#,
			ExtendBiz=#data.extendBiz#,
			LastDate=now()
		WHERE RateID=#data.id#
	
    UPDATE TPA_Attribute SET
    Title=#entity.title#,
    AttributeKey=#entity.attributeKey#,
    Format=#entity.format#,
    InputType=#entity.inputType#,
    Status=#entity.status#,
    AttributeId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE AttributeId=#entity.id#
    
        UPDATE TS_Withdraw
        SET Status = #postStatus#,
        Memo = #memo#,
        UpdateTime = NOW()
        WHERE WithdrawID = #withdrawId#
        AND Status = #preStatus#
    
		UPDATE TS_CityPortionPoolMapping 
		SET PoolID = #cityPortionPoolMappingData.poolId#, 
		Status = #cityPortionPoolMappingData.status#, 
		Operator = #cityPortionPoolMappingData.operator#, UpdateTime = now()
		WHERE ID = #cityPortionPoolMappingData.id#
	
		UPDATE TS_CityPortionPoolMapping 
		SET Status = #status#, UpdateTime = now() 
		WHERE ID = #id#
	
        DELETE
        FROM TS_GuaranteeForm
        WHERE ID=#guaranteeFormId#;
    
		UPDATE
		TS_GuaranteeForm
		SET TotalAmount = #guaranteeForm.totalAmount#,
		AllowPostpone = #guaranteeForm.allowPostpone#,
		AllowReturn = #guaranteeForm.allowReturn#,
		ExcludeM = #guaranteeForm.excludeM#,
		Exclusive = #guaranteeForm.exclusive#,
		DealName = #guaranteeForm.dealName#,
		SalePrice = #guaranteeForm.salePrice#,
		CostPrice = #guaranteeForm.costPrice#,
		GrossMargin = #guaranteeForm.grossMargin#,
		DealMaxCount = #guaranteeForm.dealMaxCount#,
		DealComment = #guaranteeForm.dealComment#,
		Comment = #guaranteeForm.comment#,
		EffectiveDate = #guaranteeForm.effectiveDate#,
		ExpireDate = #guaranteeForm.expireDate#,
		PredictCycleBack = #guaranteeForm.predictCycleBack#,
		WorkflowID = #guaranteeForm.workflowId#,
		Status = #guaranteeForm.status#,
		UpdateTime = now()
		WHERE ID = #guaranteeForm.id#
	
		UPDATE TS_PortionPool 
		SET PoolName = #portionPoolData.poolName#, 
			MaxAmount = #portionPoolData.maxAmount#,
			OwnerID =  #portionPoolData.ownerId#,
			LeftAmount = #portionPoolData.leftAmount#,
			UpdateTime = now()
		WHERE PoolID = #portionPoolData.poolId#
	
		UPDATE TS_PortionPool 
		SET Status = #status#, UpdateTime = now() 
		WHERE PoolID = #id#
	
		UPDATE TS_PortionPool
		SET TotalUsed = #portionPoolData.totalUsed#,
		 LeftAmount =  #portionPoolData.leftAmount#,
		 UpdateTime =  NOW()
		WHERE PoolID = #portionPoolData.poolId#
	
		UPDATE TS_PortionPool
		SET TotalReturn = #totalReturn#,
		    LeftAmount =  #leftAmount#,
		    UpdateTime =  NOW()
		WHERE PoolID = #poolId#
	
        UPDATE TS_GuaranteeSync
        SET UnDeductionAmount =UnDeductionAmount+ #addDeductionAmount# ,UpdateTime = NOW()
        WHERE ContractGlobalID = #contractGlobalId#
    
    UPDATE TPA_TemplateOptionAssn SET
    TemplateId=#entity.templateId#,
    AttributeId=#entity.attributeId#,
    AttributeOptionId=#entity.attributeOptionId#,
    TemplateOptionAssnId=#entity.id#,
    UpdateBy=#entity.updateBy#,
    UpdateTime=#entity.updateTime#
    WHERE TemplateOptionAssnId=#entity.id#
    
		Update JF_Card
		Set UserID = #userId#
		Where CardID = #cardId#
	
		Update JF_Card
		Set LastBalance = LastBalance - #amount#
		Where CardID = #cardId#
	
		UPDATE JF_CardLog 
		SET Comments = ISNULL(Comments,'') + #comments# 
		WHERE (CardLogID = #cardLogId#)
	
		UPDATE JF_CardLog 
		SET Comments = ISNULL(Comments,'') + #comments# ,
			CardLogStatus = #cardLogStatus# 
		WHERE (CardLogID = #cardLogId#)
	
		
			UPDATE JF_CardLog 
			SET UpdateDate= GETDATE(),
				CardAddress= #cardAddress#,
				CardZip= #userZip#,
				LastPerson= #lastPerson#,
				Comments = Comments + ' | ' + #comments# + ' | '
			WHERE CardNo = #cardNo# AND UserID = #userId#
		
	
		UPDATE JF_Cardsub 
		SET EffDate=GETDATE(),UpdateDate=GETDATE(),LastPerson =#userName# 
		WHERE CardNo=#cardNo#
	
		UPDATE
			UC_DFW_Prize
		SET
			CurrentNum=CurrentNum+1,
			UpdateTime=NOW()
		WHERE
			PrizeType=#prizeType#
	
		UPDATE
			ZS_User
		SET
			UserVoteGood = UserVoteGood + #number#
		WHERE
			UserID = #userId#;
    
    		UpdateTime = now()
    	WHERE
    		ID = #step.id#
    
        UPDATE
        UC_DairyTopicRecommend
        SET
        TopicID = #topicId#
        WHERE
        DairyID = #dairyId#;
    
		UPDATE UC_UserSkin
		SET Status = #status#,
		UpdateTime = now()
		WHERE SkinType =#skinType#
		AND
		UserId = #userId#
	
		UPDATE UC_UserSkin
		SET UserPageImgStatus = #status#,
		UpdateTime = now()
		WHERE UserPageImgId
		=#picId#
	
		UPDATE UC_CashTrans
		SET STATUS = 2
		WHERE TransID = #transId#
		    AND UserID = #userId#
	
		update DP_Wishlist set WishTags = #wishTags#
		where
		UserID = #userId#
		and WishType = #wishType#
		and ReferID = #referId#
	
		UPDATE  
		DP_WishlistCount 
		SET WishCount = WishCount - 1 
		WHERE  WishType = #favorType#
		AND ReferID = #referId#
	
		UPDATE
			ZS_User
		SET
			UserVoteGood = UserVoteGood + #number#
		WHERE
			UserID = #userId#;
    
		WHERE
			ID = #location.id#
    
		WHERE
			PicID = #picId#
	
		UPDATE UC_AvatarFace
		SET
			Status = 0, LastTime = NOW()
		WHERE
			UserID = #userId# AND Status = 1
	
        UPDATE UC_CuisineReview
        SET Status = #status#, LastTime = NOW()
        WHERE ReviewID = #reviewId#;
    
		UPDATE UC_UserSkin
		SET Status = #status#,
		UpdateTime = now()
		WHERE SkinType =#skinType#
		AND
		UserId = #userId#
	
		UPDATE UC_UserSkin
		SET UserPageImgStatus = #status#,
		UpdateTime = now()
		WHERE UserPageImgId
		=#picId#
	
		UPDATE UC_UserOwnedSkins
		SET OwnedSkins = #newSkins#,
		  UpdateTime = NOW()
		WHERE UserID = #userId#
		    AND OwnedSkins = #oldSkins#
	
		INSERT INTO UC_UserTask
            (UserID,
             TaskID,
             STATUS,
             ADDTIME)
		VALUES (#userId#,
		        #taskId#,
		        #status#,
		        NOW())
		ON DUPLICATE KEY UPDATE STATUS = VALUES(STATUS)
	
		Update JF_CardChange
		Set Status = #status#, UpdateDate = GETDATE() 
		Where CardNO = #cardNo#
	
		UPDATE JF_User 
		SET RedeemPW=#redeemPW#, 
			PWTries=0,
			UpdateDate=GetDate()  
		WHERE UserID=#userId#
    
		UPDATE JF_User 
		SET PWTries=#pwTries#, 
			UpdateDate=GetDate()  
		WHERE UserID=#userId#
    
		UPDATE JF_User 
		SET MobileNo=#mobileNo#, 
			UpdateDate=GetDate()  
		WHERE UserID=#userId#
    
		UPDATE UC_UserActivity 
		SET count = count + 1,ChooseIndex = #chooseIndex#,updateDate=now() 
		WHERE UserId =#userId#
	
		UPDATE UC_UserActivity 
		SET zeroCount = zeroCount + 1,updateDate=now() 
		WHERE UserId =#userId#
	
		UPDATE ZS_User
		SET UserNickName = #userNickName#
		WHERE UserID = #userId#;
	
		UPDATE ZS_User
		SET MobileNO = #mobileNo#
		WHERE UserID = #userId#;
	
		UPDATE ZS_User
		SET IsRefuseCard = #isRefuseCard#
		WHERE UserID = #userId#;
	
	    UPDATE ZS_User
		SET
			UserFace = #userFace#
		WHERE
			UserID = #userId#
    
    	UPDATE 
    		UC_Dairy
    	SET 
    		Visit = visit + 1
    	WHERE 
    		ID = #dairyId#
    
    	UPDATE 
    		UC_Dairy
    	SET 
    		Status = #status#
    	WHERE 
    		ID = #dairyId#
    		AND Flag = #flag#
    
    	UPDATE UC_DairyTopic
        SET
          Name = #name#,
          EnName = #enName#,
          Icon = #icon#,
          Description = #description#,
          UpdateDate = NOW()
		WHERE ID = #topicId#
    
    	UPDATE UC_DairyTopic
        SET
          Status = #status#,
          UpdateDate = NOW()
		WHERE ID = #topicId#
    
        UPDATE UC_DairyTopic
        SET
          ShowIndex = #showIndex#,
          UpdateDate = NOW()
        WHERE ID = #topicId#
    
		UPDATE
			UC_UserSignIn
		SET
			Count = #count#,
			Days = #newDays#,
			UpdateTime = #updateTime#
		WHERE
			UserID = #userId#
			AND YearMonth = #yearMonth#
			AND Days = #oldDays#
	
		UPDATE UC_UserStatistic
		SET ReviewCount = #reviewCount#
		WHERE UserID = #userId#
	
		UPDATE
			UC_DFW_Prize
		SET
			CurrentNum=CurrentNum+#num#,
			UpdateTime=NOW()
		WHERE
			PrizeType=#prizeType#
	
		UPDATE
			UC_UserFootPrintSkins
		SET
			SkinID = #skinId#,
			HavaSkinIDs = #havaSkinIds#,
			UpdateDate = NOW()
		WHERE 
			UserID = #userId#
	
		UPDATE UC_UserStatistic
		SET ReviewCount = #reviewCount#
		WHERE UserID = #userId#
	
		UPDATE ZS_User
		SET UserNickName = #userNickName#
		WHERE UserID = #userId#;
	
		UPDATE ZS_User
		SET MobileNO = #mobileNo#
		WHERE UserID = #userId#;
	
		UPDATE ZS_User
		SET IsRefuseCard = #isRefuseCard#
		WHERE UserID = #userId#;
	
	    UPDATE ZS_User
		SET
			UserFace = #userFace#
		WHERE
			UserID = #userId#
    
		UPDATE
			ZS_User
		SET
			UserVoteGood = UserVoteGood + #number#
		WHERE
			UserID = #userId#;
    
		UPDATE ZS_User
		SET PermaLink = #permaLink#
		WHERE UserID = #userId#;
	
	    UPDATE ZS_User
		SET
			UserCity = #userCity#
		WHERE
			UserID = #userId#
    
	    UPDATE ZS_User
		SET
			UserFace = ""
		WHERE
			UserID = #userId#
    
	    UPDATE ZS_User
		SET
			UserPower = #power#
		WHERE
			UserID = #userId#
    
		WHERE
			ID = #location.id#
    
		update top (1) JF_TGVoucher set Status = #status# 
		where GiftCode = #giftCode# and Status = '1'
	
		update JF_TGVoucher set Status = 0, CardNo = #cardNo#,  UserID = #userId#, UpdateTime = GetDate()
		where VoucherID = #voucherId#
	
		Update JF_CardChange
		Set Status = #status#, UpdateDate = GETDATE() 
		Where CardNO = #cardNo#
	
		UPDATE 
			UC_UserActivityGiftItem
		SET 
			CurrentNum = CurrentNum + 1,
			updateDate=now() 
		WHERE 
			type =#type#
	
		UPDATE UC_UserActivityGiftItem
		SET CurrentNum = CurrentNum + 1,updateDate=now() 
		WHERE type =#type#
	
		UPDATE
			UC_Ad
		SET
			Status = #status#,
			UpdateTime = now()
		WHERE
			ID = #id#
	
		UPDATE 
			UC_UserFootPrint
		SET
			LightReviewCount=CurReviewCount,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
	
		WHERE
			ID = #location.id#
    
		WHERE
			PicID = #picId#
	
		UPDATE UC_AvatarFace
		SET
			Status = 0, LastTime = NOW()
		WHERE
			UserID = #userId# AND Status = 1
	
    	UPDATE 
    		UC_DairyList
    	SET 
    		Visit = visit + 1
    	WHERE 
    		ID = #dairyListId#
    
    	UPDATE 
    		UC_DairyList
    	SET 
    		Status = #status#
    	WHERE 
    		ID = #dairyListId#
    
		UPDATE
			UC_UserFootPrintSkins
		SET
			SkinID = #skinId#,
			HavaSkinIDs = #havaSkinIds#,
			UpdateDate = NOW()
		WHERE 
			UserID = #userId#
	
		UPDATE 
			UC_UserFootPrint
		SET
			LightReviewCount=CurReviewCount,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
	
		UPDATE 
			UC_UserFootPrint
		SET
			CurReviewCount=CurReviewCount+#increment#,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
	
		UPDATE 
			UC_UserFootPrint
		SET
			CurReviewCount=CurReviewCount-#change#,
			LightReviewCount = #lightReviewCount#,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
			AND CurReviewCount >= #change#
	
		UPDATE UC_UserOwnedSkins
		SET OwnedSkins = #newSkins#,
		  UpdateTime = NOW()
		WHERE UserID = #userId#
		    AND OwnedSkins = #oldSkins#
	
		UPDATE
			ZS_UserProfile
		SET
			UserName=#userName#,
			UserSex=#userSex#,
			UserAddress=#userAddress#,
			UserZip =#userZip#,
			UserPhone =#userPhone#,
			UpdateDate=NOW()
		WHERE
			UserID=#userId#
	
    	UPDATE 
    		UC_Dairy
    	SET 
    		Visit = visit + 1
    	WHERE 
    		ID = #dairyId#
    
    	UPDATE 
    		UC_Dairy
    	SET 
    		Status = #status#
    	WHERE 
    		ID = #dairyId#
    		AND Flag = #flag#
    
    	UPDATE 
    		UC_DairyList
    	SET 
    		Visit = visit + 1
    	WHERE 
    		ID = #dairyListId#
    
    	UPDATE 
    		UC_DairyList
    	SET 
    		Status = #status#
    	WHERE 
    		ID = #dairyListId#
    
		UPDATE
			ZS_UserProfile
		SET
			UserName=#userName#,
			UserSex=#userSex#,
			UserAddress=#userAddress#,
			UserZip =#userZip#,
			UserPhone =#userPhone#,
			UpdateDate=NOW()
		WHERE
			UserID=#userId#
	
		UPDATE
			ZS_UserProfile
		SET
			UserSign = '',
			UserName = '' , 
			UserHomePage = '' , 
			HomePlace = '' ,  
			Constellation = '' , 
			Job = '' , 
			MiddleSchool = '' , 
			University = '' , 
			Interest = '' , 
			Movie = '' , 
			Music = '' , 
			Book = '' , 
			Dish = '',
			UpdateDate=NOW()
		WHERE
			UserID=#userId#
	
		UPDATE
			ZS_UserProfile
		SET
			UserBirthYear = #profile.userBirthYear#,
			UserHomePage = #profile.userHomePage#,
			UpdateDate = NOW(),
			BirthYear = #profile.birthYear#,
			BirthMonth = #profile.birthMonth#,
			BirthDay = #profile.birthDay#,
			Bodilyform = #profile.bodilyform#,
			LoveStatus = #profile.loveStatus#,
			Constellation = #profile.constellation#,
			QQ = #profile.qQ#,
			IsQQPublic = #profile.isQQPublic#,
			MSN = #profile.mSN#,
			IsMsnPublic = #profile.isMsnPublic#,
			Job = #profile.job#,
			MiddleSchool = #profile.middleSchool#,
			University = #profile.university#,
			Interest = #profile.interest#,
			Movie = #profile.movie#,
			Music = #profile.music#,
			Book = #profile.book#,
			Dish = #profile.dish#
		WHERE
			UserID=#profile.userId#
	
		UPDATE
			ZS_UserProfile
		SET
			UserSex=#userSex#,
			UserSign =#userSign#,
			UpdateDate=NOW()
		WHERE
			UserID=#userId#
	
		UPDATE
			ZS_UserProfile
		SET
			UserSex=#userSex#,
			UserName =#userName#,
			UserBirthYear =#userBirthYear#,
			BirthYear =#birthYear#,
			BirthMonth =#birthMonth#,
			BirthDay =#birthDay#,
			UpdateDate=NOW()
		WHERE
			UserID=#userId#
	
		INSERT INTO
			ZS_UserProfile(UserID,UserSex,UserName,UserBirthYear,BirthYear,BirthMonth,BirthDay,AddDate,UpdateDate)
		VALUES(#userId#,#userSex#,#userName#,#userBirthYear#,#birthYear#,#birthMonth#,#birthDay#,NOW(),NOW())
	
		Update JF_Card
		Set UserID = #userId#
		Where CardID = #cardId#
	
		Update JF_Card
		Set LastBalance = LastBalance - #amount#
		Where CardID = #cardId#
	
		UPDATE JF_User 
		SET RedeemPW=#redeemPW#, 
			PWTries=0,
			UpdateDate=GetDate()  
		WHERE UserID=#userId#
    
		UPDATE JF_User 
		SET PWTries=#pwTries#, 
			UpdateDate=GetDate()  
		WHERE UserID=#userId#
    
		UPDATE JF_User 
		SET MobileNo=#mobileNo#, 
			UpdateDate=GetDate()  
		WHERE UserID=#userId#
    
		UPDATE
			UC_DFW_UserMap
		SET
			GridIndex=#gridIndex#,
			RoundCount=#roundCount#,
			UsedChance=#usedChance#,
			UpdateTime=NOW()
		WHERE
			UserID=#userId# AND MapID=#mapId#	
	
    	UPDATE
    		UC_Coupons
    	SET
    		UserID = #userId#,
    		Type = 1,
    		SendTime = now()
    	WHERE
    		CouponID = #couponId#   
    
		update UC_UserFootPrintState set LuckDrawCount = LuckDrawCount + 1 
		where UserID = #userId#;
	
			UpdateTime = NOW()
		WHERE 
			UserID = #userId# AND IsDefault = #isDefault#;
	
			UpdateTime = NOW()
		WHERE 
			UserID = #userId# AND IsDefault = #isDefault#;
	
    
    
    
    	UPDATE 
    		UC_DairyRecommend
    	SET 
    		PicID = #picId#
    	WHERE 
    		ID = #recommendId#
    
			UpdateTime = NOW()
		WHERE 
			UserID = #userId# AND IsDefault = #isDefault#;
	
			UpdateTime = NOW()
		WHERE 
			UserID = #userId# AND IsDefault = #isDefault#;
	
		UPDATE 
			UC_UserFootPrint
		SET
			LightReviewCount=CurReviewCount,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
	
		UPDATE 
			UC_UserFootPrint
		SET
			CurReviewCount=CurReviewCount+#increment#,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
	
		UPDATE 
			UC_UserFootPrint
		SET
			CurReviewCount=CurReviewCount-#change#,
			LightReviewCount = #lightReviewCount#,			
			UpdateTime=NOW()
		WHERE
			UserID=#userId#
			AND CuisinesID=#cuisinesId#		
			AND CurReviewCount >= #change#
	
		UPDATE
			UC_UserSignIn
		SET
			Count = #count#,
			Days = #newDays#,
			UpdateTime = #updateTime#
		WHERE
			UserID = #userId#
			AND YearMonth = #yearMonth#
			AND Days = #oldDays#
	
		WHERE
			PicID = #picId#
	
		UPDATE UC_AvatarFace
		SET
			Status = 0, LastTime = NOW()
		WHERE
			UserID = #userId# AND Status = 1
	
		UPDATE UC_AvatarFace
		SET
			Status = 2, LastTime = NOW()
		WHERE
			UserID = #userId# AND Status = 1
	
		update DP_Wishlist set WishTags = #wishTags#
		where
		UserID = #userId#
		and WishType = #wishType#
		and ReferID = #referId#
	
		UPDATE  
		DP_WishlistCount 
		SET WishCount = WishCount - 1 
		WHERE  WishType = #favorType#
		AND ReferID = #referId#
	
    
    
    
    	UPDATE 
    		UC_DairyRecommend
    	SET 
    		PicID = #picId#,
    		UpdateDate = NOW()
    	WHERE 
    		ID = #recommendId#
    
		UPDATE
			UC_DairyRecommend
		SET
			SecondCategoryID = #secondCategoryId#,
			CityID = #cityId#,
			UpdateDate = NOW()
		WHERE
			ID = #recommendId#
	
    	UPDATE 
    		UC_DairyRecommend
    	SET 
    		Status = 2,
    		UpdateDate = NOW()
    	WHERE 
    		Status in (0 , 4)
			AND DairyID = #dairyId#
    
    	UPDATE 
    		UC_DairyRecommend
    	SET 
    		Status = 3,
    		UpdateDate = NOW()
    	WHERE 
    		Status = 1
			AND DairyID = #dairyId#
    
    	UPDATE 
    		UC_DairyRecommend
    	SET 
    		PicID = #picId#,
    		UpdateDate = NOW()
    	WHERE 
    		DairyID = #dairyId#
    
		UPDATE
			UC_DairyRecommend
		SET
			ReviewWord = #reviewWord#,
			PicCount = #picCount#,
			UpdateDate = NOW()
		WHERE
			ID = #recommendId#
	
    
		UPDATE JF_CardLog 
		SET Comments = ISNULL(Comments,'') + #comments# 
		WHERE (CardLogID = #cardLogId#)
	
		UPDATE JF_CardLog 
		SET Comments = ISNULL(Comments,'') + #comments# ,
			CardLogStatus = #cardLogStatus# 
		WHERE (CardLogID = #cardLogId#)
	
		
			UPDATE JF_CardLog 
			SET UpdateDate= GETDATE(),
				CardAddress= #cardAddress#,
				CardZip= #userZip#,
				LastPerson= #lastPerson#,
				Comments = Comments + ' | ' + #comments# + ' | '
			WHERE CardNo = #cardNo# AND UserID = #userId#
		
	
		UPDATE JF_Cardsub 
		SET EffDate=GETDATE(),UpdateDate=GETDATE(),LastPerson =#userName# 
		WHERE CardNo=#cardNo#
	
		update top (1) JF_TGVoucher set Status = #status# 
		where GiftCode = #giftCode# and Status = '1'
	
		update JF_TGVoucher set Status = 0, CardNo = #cardNo#,  UserID = #userId#, UpdateTime = GetDate()
		where VoucherID = #voucherId#
	
		UPDATE UC_Newerlist_User
		SET ReviewCount = ReviewCount + #increment#,
		  ReviewAddDate = #reviewDate#,
		  UpdateDate = NOW()
		WHERE UserID = #userId#
		    AND YYMM = #yymm#
	
		UPDATE UC_Newerlist_User
		SET ReviewCount = ReviewCount + #increment#,
		  UpdateDate = NOW()
		WHERE UserID = #userId#
		    AND YYMM = #yymm#
	
		UPDATE UC_CommonKV 
		SET CValue = #cValue# ,updateDate=now() 
		WHERE CKey =#cKey#
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = Amount,
			  STATUS = 1,
			  COMMENT = #comment#,
			  GotDate = #now#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount - #decrement#,
			  STATUS = 0,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 1
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount + #increment#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = #count#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = CurrentCount + #increment#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
		
	
		
			UPDATE UC_UserBadge
			SET CurrentCount = #resetTo#,
			  UpdateDate = #now#
			WHERE UserID = #userId#
			    AND BadgeID = #badgeId#
			    AND STATUS = 0
		
	
		UPDATE 
			UC_UserNotice
		SET 
			ManaScore = #manaScore#,
			UserPower = #userPower#,
			SendTimes = #sendTimes#,
			UpdateDate = NOW()
		WHERE 
			UserID = #userId# AND (ManaScore != #manaScore# OR UserPower != #userPower# OR SendTimes != #sendTimes#);
	
		UPDATE
			ZS_UserProfile
		SET
			UserName=#userName#,
			UserSex=#userSex#,
			UserAddress=#userAddress#,
			UserZip =#userZip#,
			UserPhone =#userPhone#,
			UpdateDate=NOW()
		WHERE
			UserID=#userId#
	
        UPDATE UC_CuisineReview
        SET Status = #status#, LastTime = NOW()
        WHERE ReviewID = #reviewId#;
    
		UPDATE
			UC_DFW_Prize
		SET
			CurrentNum=CurrentNum+#num#,
			UpdateTime=NOW()
		WHERE
			PrizeType=#prizeType#
	
		UPDATE
			UC_UserBadgeInfo
		SET
			BadgeCount = #badgeCount#
		WHERE
			UserID = #userId#
	
		UPDATE
			UC_UserBadgeInfo
		SET
			BadgeCount = BadgeCount + #increment#
		WHERE
			UserID = #userId#
	
		INSERT INTO UC_UserTask
            (UserID,
             TaskID,
             STATUS,
             ADDTIME)
		VALUES (#userId#,
		        #taskId#,
		        #status#,
		        NOW())
		ON DUPLICATE KEY UPDATE STATUS = VALUES(STATUS)
	
		UPDATE UC_UserCash
		SET ManaScore = ManaScore + #manaIncrement#,
		  DCash = Dcash + #dcashIncrement#,
		  UpdateDate = NOW()
		WHERE UserID = #userId#
	
		UPDATE ZS_User
		SET UserNickName = #userNickName#
		WHERE UserID = #userId#;
	
		UPDATE ZS_User
		SET MobileNO = #mobileNo#
		WHERE UserID = #userId#;
	
		UPDATE ZS_User
		SET IsRefuseCard = #isRefuseCard#
		WHERE UserID = #userId#;
	
	    UPDATE ZS_User
		SET
			UserFace = #userFace#
		WHERE
			UserID = #userId#
    
		update UC_UserFootPrintState set LuckDrawCount = LuckDrawCount + 1 
		where UserID = #userId#;
	
		UPDATE
			UC_UserBadgeInfo
		SET
			BadgeCount = #badgeCount#
		WHERE
			UserID = #userId#
	
		UPDATE
			UC_UserBadgeInfo
		SET
			BadgeCount = BadgeCount + #increment#
		WHERE
			UserID = #userId#
	
		
			update
				DP_Configuration
			set
				Settings=#settings#,
				UpdateTime=NOW(),
				StrSettings=#strSettings#
			where
				ConfigName = #configName#
		
	
        update WED_ShopTag
        set
            UpdateTime = NOW()
        where
            ID = #id#
    
		UPDATE DP_OfficialAlbum SET AlbumType= #albumType#,picCount = #picCount#
        WHERE ID = #albumId#;	
	
        UPDATE
            DP_ShopPhone
        SET CityID = #cityID#
        WHERE
            ID = #id#
    
		UPDATE DP_OfficialAlbumPic SET AlbumID=#albumId#
        WHERE PicID IN ($picIds$);	
	
		DELETE FROM DP_OfficialAlbumPic 
        WHERE PicId = #picId#;	
	
		UPDATE DP_OfficialAlbum SET AlbumType= #albumType#,picCount = #picCount#
        WHERE ID = #albumId#;	
	
		UPDATE DP_SmartSalesEmailOpenCount 
		SET
		Count=#count#,
		OpenTime=NOW()
		WHERE
		EmailId=#emailId#
	
        UPDATE WED_EnterpriseQQ
        SET
          FEnterpriseFullName=#qq.FEnterpriseFullName#,
          FProvince=#qq.FProvince#,
          FCity=#qq.FCity#,
          FAddress=#qq.FAddress#,
          FCategory=#qq.FCategory#,
          FSubCategory=#qq.FSubCategory#,
          FContactName=#qq.FContactName#,
          FContactQQ=#qq.FContactQQ#,
          FContactTel=#qq.FContactTel#,
          FContactEmail=#qq.FContactEmail#,
          FAdminQQ=#qq.FAdminQQ#,
          ShopGroupId=#qq.shopGroupId#,
          SendStatus=#qq.sendStatus#,
          SendErrorCode=#qq.sendErrorCode#,
          SendErrorMsg=#qq.sendErrorMsg#,
          FKFNameAccount=#qq.FKFNameAccount#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          SendStatus=#qq.sendStatus#,
          SendErrorCode=#qq.sendErrorCode#,
          SendErrorMsg=#qq.sendErrorMsg#,
          FKFNameAccount=#qq.FKFNameAccount#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          PullStatus=#qq.pullStatus#,
          PullErrorCode=#qq.pullErrorCode#,
          PullErrorMsg=#qq.pullErrorMsg#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          FSuperPasswd=#qq.FSuperPasswd#,
          FKFExtCount=#qq.FKFExtCount#,
          FKFExpireTime=#qq.FKFExpireTime#,
          FKFExt1=#qq.FKFExt1#,
          FKFExt1Passwd=#qq.FKFExt1Passwd#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          FLicenceNumber=#qq.FLicenceNumber#,
          FLicenceNumberEndTime=#qq.FLicenceNumberEndTime#,FLegalPersonName=#qq.FLegalPersonName#,
        FLegalPersonLicence=#qq.FLegalPersonLicence#,FTaxNumber=#qq.FTaxNumber#,
        UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          OpenStatus=#qq.openStatus#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          OpenStatus=#qq.openStatus#,
          UpdateTime=NOW()
        WHERE
          Id=#qq.id#
    
        UPDATE WED_EnterpriseQQ
        SET ShopGroupId=#qq.shopGroupId#
        WHERE Id=#qq.id#
    
   		UPDATE Spider_JobMonitor
		SET STATUS = #monitor.status#,
		  JobRunTime = #monitor.jobRunTime#,
		  URLTotalCount = #monitor.urlTotalCount#,
		  URLFailedCount = #monitor.urlFailedCount#,
		  TargetPagesTotalCount = #monitor.targetPagesTotalCount#,
		  TargetPagesFailedCount = #monitor.targetPagesFailedCount#,
		  ItemsDownloadedCount = #monitor.itemsDownloadedCount#,
		  UpdateDate = NOW()
		WHERE MonitorID = #monitor.monitorId#
    
   		UPDATE Spider_JobMonitor
		SET STATUS = #status#
		WHERE MonitorID = #monitorId#
   
			UPDATE DP_BizJournalAccount
			SET TradeStatus = #bizJournalAccount.tradeStatus#, ReceiptID=#bizJournalAccount.receiptId#
			WHERE TradeNo = #bizJournalAccount.tradeNo#
	
			UPDATE DP_BizJournalAccount
			SET TradeStatus = #bizJournalAccount.tradeStatus#, ReceiptID=#bizJournalAccount.receiptId#
			WHERE TradeNo = #bizJournalAccount.tradeNo#
	
		UPDATE DP_OfficialAlbum SET AlbumType= #albumType#,picCount = #picCount#
        WHERE ID = #albumId#;	
	
		
			UPDATE
				DP_ShopVideo
            SET
                UploadDate = #uploadDate#
			WHERE
				VideoID = #videoId#
		
	
        UPDATE WED_ProductShop
        SET
            BookingContent = #productShop.bookingContent#,
            ProductPriceID = #productShop.productPriceId#,
            BookingOriginPrice = #productShop.bookingOriginPrice#,
            BookingPresentPrice = #productShop.bookingPresentPrice#,
            BookingCount = #productShop.bookingCount#,
            UpdateTime = NOW()
        WHERE
            ShopID = #productShop.shopId#
    
        UPDATE WED_ProductShop
        SET
        ProductPicUrl = #productPicUrl#
        WHERE
        ProductID = #productId#
    
        UPDATE WED_ProductShop
        SET
            ProductPicUrl = #productShop.productPicUrl#,
            BookingContent = #productShop.bookingContent#,
            ProductPriceID = #productShop.productPriceId#,
            BookingOriginPrice = #productShop.bookingOriginPrice#,
            BookingPresentPrice = #productShop.bookingPresentPrice#,
            BookingCount = #productShop.bookingCount#,
            UpdateTime = NOW()
        WHERE
            ID = #productShop.id#
    
        UPDATE WED_EnterpriseQQ
        SET
          FEnterpriseFullName=#qq.FEnterpriseFullName#,
          FProvince=#qq.FProvince#,
          FCity=#qq.FCity#,
          FAddress=#qq.FAddress#,
          FCategory=#qq.FCategory#,
          FSubCategory=#qq.FSubCategory#,
          FContactName=#qq.FContactName#,
          FContactQQ=#qq.FContactQQ#,
          FContactTel=#qq.FContactTel#,
          FContactEmail=#qq.FContactEmail#,
          FAdminQQ=#qq.FAdminQQ#,
          ShopGroupId=#qq.shopGroupId#,
          SendStatus=#qq.sendStatus#,
          SendErrorCode=#qq.sendErrorCode#,
          SendErrorMsg=#qq.sendErrorMsg#,
          FKFNameAccount=#qq.FKFNameAccount#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          SendStatus=#qq.sendStatus#,
          SendErrorCode=#qq.sendErrorCode#,
          SendErrorMsg=#qq.sendErrorMsg#,
          FKFNameAccount=#qq.FKFNameAccount#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          PullStatus=#qq.pullStatus#,
          PullErrorCode=#qq.pullErrorCode#,
          PullErrorMsg=#qq.pullErrorMsg#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          FSuperPasswd=#qq.FSuperPasswd#,
          FKFExtCount=#qq.FKFExtCount#,
          FKFExpireTime=#qq.FKFExpireTime#,
          FKFExt1=#qq.FKFExt1#,
          FKFExt1Passwd=#qq.FKFExt1Passwd#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          FLicenceNumber=#qq.FLicenceNumber#,
          FLicenceNumberEndTime=#qq.FLicenceNumberEndTime#,FLegalPersonName=#qq.FLegalPersonName#,
        FLegalPersonLicence=#qq.FLegalPersonLicence#,FTaxNumber=#qq.FTaxNumber#,
        UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          OpenStatus=#qq.openStatus#,
          UpdateTime=NOW()
        WHERE
          ShopID=#qq.shopId#
    
        UPDATE WED_EnterpriseQQ
        SET
          OpenStatus=#qq.openStatus#,
          UpdateTime=NOW()
        WHERE
          Id=#qq.id#
    
        UPDATE WED_EnterpriseQQ
        SET ShopGroupId=#qq.shopGroupId#
        WHERE Id=#qq.id#
    
			UPDATE DP_BizAccount
			SET Balance = Balance + #amount#
			WHERE AccountID = #accountId#
	
    	
    		UPDATE DP_ShopBusinessValue
    		SET BusinessValue=#businessValue#, BusinessValueLast=#businessValueLast#, UpdateTime=NOW(), Grade=#grade#, GradeLast=#gradeLast#
    		WHERE ShopId=#shopId#;
    	
    
	    UPDATE Spider_DBConfig SET
	    	   DbType = #dbConfig.dbType#,
	    	   Address = #dbConfig.address#,
	    	   Username = #dbConfig.username#,
	    	   Password = #dbConfig.password#,
	    	   Port = #dbConfig.port#,
	    	   DatabaseName = #dbConfig.database#,
	    	   TableName = #dbConfig.table#,
	    	   Mappings = #dbConfig.mappings#,
	    	   UpdateDate = NOW()
	    WHERE JobID=#jobId#
    
    UPDATE Spider_JobConfig SET
    	   IsRepeat=#jobConfig.isRepeat#,
    	   JobTiming=#jobConfig.jobTiming#,
    	   JobPriority=#jobConfig.jobPriority#,
    	   JobType=#jobConfig.jobType#,
    	   TargetFindType=#jobConfig.targetFindType#,
    	   SpiderRule=#jobConfig.spiderRule#,
    	   ResultParseRules=#jobConfig.itemParseRules#,
    	   Charset=#jobConfig.charset#,
    	   AutoDetect=#jobConfig.autoDetect#,
    	   Frequency=#jobConfig.frequency#,
    	   UserAgent=#jobConfig.userAgent#,
    	   Cookie=#jobConfig.cookie#,
    	   DownloadRetryTimes=#jobConfig.downloadRetryTimes#,
    	   UrlDiscardTimes=#jobConfig.urlDiscardTimes#,
    	   UpdateDate=NOW()
    WHERE JobID=#jobId#
    
	    UPDATE Spider_JobConfig SET
	    	   Status = #status#,
	    	   UpdateDate = NOW()
	    WHERE JobID=#jobId#
    
    	
    		UPDATE WED_WeddingHotelExtraInfo
    		SET
	    		ShopName=#entity.ShopName#,
	    		ReferShopID=#entity.ReferShopID#,
	    		ReferFoodShopID=#entity.ReferFoodShopID#,
	    		CooperateType=#entity.CooperateType#,
	    		SalesInfo=#entity.SalesInfo#,
	    		HallCapacityMin=#entity.HallCapacityMin#,
	    		HallCount=#entity.HallCount#,
	    		HallDesc=#entity.HallDesc#,
	    		WeddingHotelDesc=#entity.WeddingHotelDesc#,
	    		Stage=#entity.Stage#,
	    		Trans=#entity.Trans#,
	    		CommonEquipment=#entity.CommonEquipment#,
	    		EnvPicUserId=#entity.EnvPicUserId#,
	    		Address=#entity.Address#,
	    		Metro=#entity.Metro#,
	    		Bus=#entity.Bus#,
	    		Park=#entity.Park#,
	    		AddTime=#entity.AddTime#,
	    		UpdateTime=NOW(),
	    		HallCapacityMax=#entity.HallCapacityMax#,
	    		ShopMobile=#entity.ShopMobile#,
	    		CooperateLevel=#entity.CooperateLevel#,
	    		PlaceType=#entity.PlaceType#)
    		WHERE ShopID=#entity.ShopID#;
    	
    
		UPDATE DP_BookingShop SET MainCategoryID=#mainCategoryId#,CityID=#cityId# 
        WHERE ShopId = #shopId#
	
        UPDATE
            DP_ShopPhone
        SET CityID = #cityID#
        WHERE
            ID = #id#
    
		
			UPDATE
				DP_ShopVideo
            SET
                UploadDate = #uploadDate#
			WHERE
				VideoID = #videoId#
		
	
			UPDATE DP_BizJournalAccount
			SET TradeStatus = #bizJournalAccount.tradeStatus#, ReceiptID=#bizJournalAccount.receiptId#
			WHERE TradeNo = #bizJournalAccount.tradeNo#
	
		UPDATE DP_OfficialAlbumPic SET AlbumID=#albumId#
        WHERE PicID IN ($picIds$);	
	
		DELETE FROM DP_OfficialAlbumPic 
        WHERE PicId = #picId#;	
	
		UPDATE DP_BookingShop SET MainCategoryID=#mainCategoryId#,CityID=#cityId# 
        WHERE ShopId = #shopId#
	
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId# AND PicType=2
	
        UPDATE
            DP_ShopPhone
        SET CityID = #cityID#
        WHERE
            ID = #id#
    
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId#
	
        
          UPDATE WED_WeddingHotelHall
          SET
            ShopID = #weddingHotelHall.shopId#,
            PicUserId = #weddingHotelHall.picUserId#,
            Name = #weddingHotelHall.name#,
            PlanPicID = #weddingHotelHall.planPicId#,
            Capacity = #weddingHotelHall.capacity#,
            Height = #weddingHotelHall.height#,
            PostCount = #weddingHotelHall.postCount#,
            Stage = #weddingHotelHall.stage#,
            Money = #weddingHotelHall.money#,
            AddTime = NOW(),
            UpdateTime = NOW(),
            CapacityMin = #weddingHotelHall.capacityMin#,
            CapacityMax = #weddingHotelHall.capacityMax#,
            Remark = #weddingHotelHall.remark#
          WHERE
            ID = #weddingHotelHall.id#
        
    
        UPDATE
            DP_BizAccount
        SET CityID = #cityID#
        WHERE
            AccountID = #accountID#
    
		UPDATE PC_Picture SET UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET PicType=2,UserID=#userId#
        WHERE PicID IN ($picIds$)
	
		UPDATE DP_ShopPic SET CityID=#cityId#,ShopType=#shopType#,ShopGroupID=#shopGroupId#
        WHERE ShopID = #shopId#
	
		
			UPDATE
				DP_ShopVideo
            SET
                UploadDate = #uploadDate#
			WHERE
				VideoID = #videoId#
		
	
        UPDATE DP_BookingShop_All
        SET
            Status = 1
        WHERE
            ShopID = #shopId#
    
        UPDATE DP_BookingShop_All
        SET
          Status = 0
    
		UPDATE DP_OfficialAlbumPic SET AlbumID=#albumId#
        WHERE PicID IN ($picIds$);	
	
		DELETE FROM DP_OfficialAlbumPic 
        WHERE PicId = #picId#;	
	
        UPDATE
            DP_BizAccount
        SET CityID = #cityID#
        WHERE
            AccountID = #accountID#
    
        UPDATE
            DP_BizAccount
        SET CityID = #cityID#
        WHERE
            AccountID = #accountID#
    
		UPDATE DP_BookingShop SET MainCategoryID=#mainCategoryId#,CityID=#cityId# 
        WHERE ShopId = #shopId#
	