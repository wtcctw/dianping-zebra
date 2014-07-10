
		DELETE FROM
			TG_EventPrize 
		WHERE
			PrizeId=#prizeId#
	
		DELETE FROM TGE_SecondOrder
		WHERE SecondOrderID = #id#
	
		DELETE FROM
			TG_EventCity 
		WHERE
			EventId=#eventId#
			AND CityId=#cityId#
	
		DELETE FROM
		TG_EventPoint
		WHERE
		UserId=#userId#
	
		DELETE FROM TGE_SecondPrize
		WHERE SecondPrizeID = #id#
	
		delete from
			TG_Event_TopicItemCity
		where
			TopicItemID in 
				(select 
					TopicItemID 
				from 
					TG_Event_TopicItem
				where
					TopicID=#topicID#
					)
	
		delete from
			TG_Event_TopicItem
		where
			TopicID=#topicID#;
	
		delete from 
			TG_Event_Topic 
		where 
			TopicID = #topicID#
	
		delete from 
			TG_Event_TopicItemCity
		where 
			TopicItemID = #topicItemID# and CityID=#cityId#
	
    	
    		DELETE FROM CacheServerGroup
    		WHERE `Group` = #group#
    	
    
		DELETE FROM DP_ThirdUser
		WHERE 
			ThirdUid = #thirdUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			RRUid = #rRUid#
	
		DELETE FROM DP_ThirdUser
		WHERE 
			DPUid = #dPUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			DPUid = #dPUid#
	
		DELETE FROM DP_KV_ReviewBody 
		WHERE ReviewID = #ReviewID#
	
		DELETE FROM CI_UserRank
	
		DELETE FROM CI_UserFeed WHERE UserID = #userID# AND FeedType = #feedType#
	
		DELETE FROM CI_UserThirdUID 
		WHERE thirdUid= #thirduid#
	
		DELETE FROM CI_UserFeed WHERE FeedToken = #feedToken# AND FeedType = #feedType#
	
		DELETE FROM
			GPA_OfflineActivityBlackList
		WHERE
			BlackListID=#blackListId#
	
		delete from
			 GPA_OfflineActivityItem
		where
			OfflineActivityID=#offlineActivityId#
	
		DELETE FROM
			GPA_OfflineActivityShareSetInfo
		WHERE
			OfflineActivityID = #offlineActivityId#
	
		DELETE FROM DianPingMC.MC_MemberCardProductConfirmInfo WHERE ShopConfirmInfoID = #shopConfirmInfoID# ;
	
		DELETE FROM GPA_OfflineActivityCombo 
		WHERE OfflineActivityID = #offlineActivityId#
	
		
		DELETE FROM CacheOperationLog 
   		WHERE OperateTime <= #before#
		
	
		DELETE FROM DP_ThirdUser
		WHERE 
			ThirdUid = #thirdUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			RRUid = #rRUid#
	
		DELETE FROM DP_ThirdUser
		WHERE 
			DPUid = #dPUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			DPUid = #dPUid#
	
		DELETE FROM DP_UserBlackList
		WHERE UserID = #userId#
	
    	
    		DELETE FROM DP_CacheConfiguration
    		WHERE CacheKey = #key#
    	
    
		DELETE FROM DP_ThirdUser
		WHERE 
			ThirdUid = #thirdUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			RRUid = #rRUid#
	
		DELETE FROM DP_ThirdUser
		WHERE 
			DPUid = #dPUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			DPUid = #dPUid#
	

    	
		DELETE FROM CI_Honey 
		WHERE UserID = #UserID# AND HoneyID = #HoneyID#

    

    	
		DELETE FROM CI_HoneyInvitation 
		WHERE UserID = #UserID# AND InviteUserID = #InviteUserID#
    

    

    	
		DELETE FROM CI_Shake 
		WHERE UserID = #UserID#

    
   		
   		DELETE FROM CI_IPhonePush WHERE PToken = #ptoken#
   		AND ID < #id#
   		
   	
		DELETE FROM DP_QQFriendShip
		WHERE
		OpenID=#openId# AND FriendID=#friendId#
	
	
		DELETE FROM DP_ThirdUser
		WHERE 
			ThirdUid = #thirdUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			RRUid = #rRUid#
	
		DELETE FROM DP_ThirdUser
		WHERE 
			DPUid = #dPUid# and
			Type = #type#
	
		DELETE FROM DP_RenRenUser
		WHERE 
			DPUid = #dPUid#
	
		delete from
			CV_ContentVerify_Category
		where
			ID=#id#
	
		
		DELETE FROM DP_CacheKeyConfiguration 
   		WHERE Category = #category#
		
	
    	DELETE FROM
    		DP_AdItemExt
    	WHERE
    		KID = #kid#
    
		delete from DP_Unsubscribe where dpid = #dpid# and businesstype = #businesstype#
	
		delete from DP_PushRead where Dpid = #dpid# and BusinessYype = #businesstype#
	
		delete from
			GPA_OfflineActivityGroup
		where
			OfflineActivityID=#offlineActivityId#
	
		DELETE FROM DP_UserBlackList
		WHERE UserID = #userId#
	
		delete from
			CV_BlackList
		where
			ID=#id#
	
		DELETE FROM DP_UserBlackList
		WHERE UserID = #userId#
	
		DELETE FROM AC_UserActionCity
		WHERE UserID = #userId#
	
		DELETE FROM DP_UserBlackList
		WHERE UserID = #userId#
	
    	DELETE FROM DianPingMobile.CI_CheckInTopicDetail 
		WHERE CheckInID = #CheckInID#
    
		delete from
			GPA_OfflineActivityShop
		where
			OfflineActivityID=#offlineActivityId#
	
		DELETE FROM AC_LostUser 
		WHERE
		UserID = #userId# AND Status = #status# 
	
		DELETE FROM AC_LostUser 
		WHERE
		UserID = #userId# 
	
		DELETE FROM
			GPA_OfflineActivitySerialNoTemp
		WHERE
			SerialNumber=#serialNumber#
	
		DELETE FROM
			GPA_OfflineActivitySerialNoTemp
	
		delete from
		GPA_OfflineActivityUserAggregation
	
		DELETE FROM GPA_OfflineActivityBranch 
		WHERE OfflineActivityID = #offlineActivityId#
	
		DELETE FROM
		ECP_DEAL WHERE DEAL_ID=#dealId#
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='ImageTextComponent'
	
		DELETE FROM
		TGP_FILE_ATTACHMENT
		WHERE FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='QualifiedAttachment'
	
		DELETE FROM TGP_RESOURCE_ROLE_AUTHORITY_CONFIG WHERE
		RESOURCE_ROLE_AUTHORITY_CONFIG_ID=#entity.id#
	
		DELETE FROM TGP_SALES_TEAM
		WHERE	SALES_TEAM_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_LIST_ITEM WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='TextItem'
	
		DELETE FROM TGP_COMPOSITABLE_STATEMENT_TEMPLATE_ASSN
		WHERE COMPOSITABLE_STATEMENT_TEMPLATE_ASSN_ID=#entity.id#
	
		DELETE FROM TGP_TEMPLATE_ENTRY
		WHERE TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='CompositableTemplate'
	
		DELETE FROM TGP_DOCUMENT_TEMPLATE
		WHERE DOCUMENT_TEMPLATE_ID=#entity.id#
	
		DELETE FROM TGP_DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN
		WHERE DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN_ID=#entity.id#
	
		DELETE FROM TGP_STATEMENT
		WHERE STATEMENT_ID=#entity.id#
	
		DELETE FROM TGP_CONFIGURABLE_BLOCK WHERE
		CONFIGURABLE_BLOCK_ID=#entity.id#
	
		
			DELETE
			FROM UC_BadgeInitLog
			WHERE BadgeID = #badgeId# 
			AND UserBatchNum >= 0
		
	
		
			DELETE
			FROM UC_BadgeInitLog
			WHERE BadgeID = #badgeId# 
			AND UserBatchNum = #batchNum# 
			AND UserIdMod >= 0
		
	
        DELETE FROM  TG_PurchaseAccumulate
    
    
		DELETE FROM TGP_EMAIL_INFO WHERE
		EMAIL_ID=#entity.id#
	
		DELETE FROM TGP_PARTNER_DATA WHERE
		PARTNER_DATA_ID=#entity.id#
	
		DELETE FROM TGP_STATEMENT_ATTRIBUTE_OPTION
		WHERE STATEMENT_ATTRIBUTE_OPTION_ID=#entity.id#
	
		DELETE FROM TGP_STATEMENT_ATTRIBUTE_VALUE
		WHERE STATEMENT_ATTRIBUTE_VALUE_ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_MAINTAINER WHERE ID=#entity.id#
	
        DELETE
        FROM TGP_EDITOR
        WHERE EDITOR_ID =#editorId#
    
		DELETE FROM
		TGP_FILE_ATTACHMENT
		WHERE FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='FileAttachment'
	
		DELETE FROM TGP_RECEIPT_VERIFY_HISTORY WHERE
		ID=#entity.id#;
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='RichTextComponent'
	
        DELETE
        FROM TGP_SALES
        WHERE SALES_TEAM_ID=#salesTeamId#
    
	 
		DELETE FROM TGP_SHOP_CITY_GROUP WHERE  SHOP_CITY_GROUP_ID=#entity.id#
	
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextListComponent'
	
        DELETE FROM MC_MemberCardConsume
        WHERE MemberCardConsumeID=#memberCardConsumeId#
            AND MemberCardID=#memberCardId#
    
        DELETE FROM MC_MemberCardFeedApplyShop
        WHERE FeedID=#feedId#
    
		DELETE FROM UC_UserBadge where badgeid between 1 and 40
	
		DELETE FROM ECP_DEAL_GROUP_SHOP WHERE DEAL_ID=#dealId#
	
        DELETE FROM ECP_DEAL_GROUP_SHOP WHERE ID=#id#
    
		delete from TG_ValidPhoneList where PhoneId = #PhoneID# 
	
		DELETE FROM TGP_STATEMENT_ATTRIBUTE
		WHERE STATEMENT_ATTRIBUTE_ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_CITY_ASSN WHERE
		DEAL_GROUP_CITY_ASSN_ID=#entity.id#
	
		DELETE FROM
		TGP_DEAL_GROUP_PRODUCE_VERSION WHERE DEAL_GROUP_PRODUCE_VERSION_ID=#entity.id#
	
		DELETE FROM
		TGP_DEAL_GROUP_PRODUCE_VERSION WHERE DEAL_GROUP_ID =#dealGroupId#
	
		DELETE FROM TGP_SALES_TEAM_AE_ASSN
		WHERE	SALES_TEAM_AE_ASSN_ID=#entity.id#
	
        DELETE
        FROM TGP_SALES_TEAM_AE_ASSN
        WHERE SALES_TEAM_ID = #salesTeamId#
    
		DELETE FROM TGP_SERIAL_NUM_OPERATION_LOG WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberImportLog'
	
	 
		DELETE FROM TGP_SHOP_INFO WHERE SHOP_INFO_ID=#entity.id#
	
	
		DELETE FROM TGP_SLIDE_PICTURE WHERE
		SLIDE_PICTURE_ID=#entity.id#
	
		DELETE FROM TGP_SPECIAL_REMINDER_TEMPLATE_MAP
		WHERE
		NAV_CATEGORY_ID=#entity.categoryId#
	
		DELETE FROM UC_UserBadge where badgeid between 1 and 40
	
    
    
        DELETE FROM TG_RefundAccumulate
    
    
		DELETE FROM TGP_DEAL WHERE
		DEAL_ID=#entity.id#
	
    DELETE FROM TGP_DEAL_GROUP_PROMOTION WHERE
    DEAL_GROUP_PROMOTION_ID=#entity.id#
  
		DELETE FROM TGP_DEAL_GROUP_WORKFLOW_HISTORY WHERE
		ID=#entity.id#
	
		DELETE FROM TGP_DEAL_HOTEL WHERE ID=#entity.id#
	
		DELETE FROM
		TGP_EXCEPT_DATE
		WHERE EXCEPT_DATE_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_LIST_ITEM WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ImageTextItem'
	
		DELETE FROM TGP_ACCOUNT WHERE
		ACCOUNT_ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_AE WHERE ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_AE WHERE DEAL_GROUP_ID =#dealGroupId#
	
	
	
		DELETE 
		FROM TGP_DEAL_GROUP_NAV_CATEGORY_ASSN 
		WHERE DEAL_GROUP_NAV_CATEGORY_ASSN_ID = #entity.id#
		
	
		DELETE FROM
		TGP_DEAL_GROUP_VERSION WHERE DEAL_GROUP_VERSION_ID=#entity.id#
	
		DELETE FROM
		TGP_DEAL_GROUP_VERSION WHERE DEAL_GROUP_ID =#dealGroupId#
	
		DELETE FROM
			CSC_AdminLogin
		WHERE
			Id = #cscAdminLogin.id#
	
		DELETE FROM TGP_DEAL_GROUP_THIRD_PARTNER_EXTEND WHERE
		DEAL_GROUP_THIRD_PARTY_ID=#entity.id#
	
		DELETE FROM
		TGP_DEAL_SHOP_ASSN
		WHERE DEAL_SHOP_ASSN_ID=#entity.id#
	
		DELETE FROM TGP_DESTINATION WHERE
		DESTINATION_ID=#entity.id#
	
        DELETE
        FROM TGP_EDITOR_CITY_ASSN
        WHERE EDITOR_ID =#editorId#
    
		DELETE FROM TGP_IMAGE_TEXT_DESC_ITEM WHERE
		IMAGE_TEXT_DESC_ITEM_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_LIST_ITEM WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ProductItem'
	
		DELETE FROM TGP_RESOURCE_AUTHORITY_CONFIG WHERE
		RESOURCE_AUTHORITY_CONFIG_ID=#entity.id#
	
		DELETE FROM TGP_SERIAL_NUM_OPERATION_LOG WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberExportLog'
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextAreaListComponent'
	
		DELETE FROM
		TGP_TOP_CITY_INFO
		WHERE TOP_CITY_INFO_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_VIEW WHERE
		VISUAL_VIEW_ID=#entity.id#
	
		DELETE FROM TGP_DOCUMENT_BUILDER
		WHERE DOCUMENT_BUILDER_ID=#entity.id#
	
		DELETE FROM TGP_TEMPLATE_ENTRY
		WHERE TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='StatementTemplate'
	
		DELETE FROM TGP_CARD WHERE
		CARD_ID=#entity.id#
	
    
		DELETE FROM TGP_CONTACT WHERE
		CONTACT_ID=#entity.id#
	
		DELETE FROM TGP_CONTRACT WHERE
		CONTRACT_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='DealComponent'
	
		DELETE FROM
			CSC_GroupAdminLogin
		WHERE
			GroupId = #groupAdminLogin.groupId# AND
			LoginId = #groupAdminLogin.loginId#
	
        DELETE FROM
            MR_UserVisitHistory
        WHERE
            CustomerID = #customerId#
            AND TableType = #tableType#
    
	
           UPDATE
           MR_HygienicLicenseFuture
           SET
           Status=0,
           ApproveStatus=5
           WHERE RegisterNum=#registerNum#
       
           UPDATE
           MR_HygienicLicenseFuture
           SET
           Status=0,
           ApproveStatus=5
           WHERE HygienicLicenseID=#hygienicLicenseID#
       
           UPDATE
           MR_HygienicLicenseFuture
           SET Status=0,
           ApproveStatus=5
           WHERE CustomerID=#customerID#
       
            UPDATE
            MR_HygienicLicense
            SET Status=0
            WHERE HygienicLicenseID=#hygienicLicenseID#
        
		DELETE FROM CT_ShopFAQ WHERE FAQID = #faqId#
	
	
    
        DELETE FROM IN_CreditABTestLog WHERE logTime < DATE_ADD(CURDATE(),INTERVAL -7 DAY);
    
    
    
    
        DELETE FROM IN_CreditABTestLog WHERE logTime < DATE_ADD(CURDATE(),INTERVAL -7 DAY);
    
    
        DELETE FROM
            CT_UserVisitHistory
        WHERE
            ContractID = #contractId#
            AND TableType = #tableType#
     
	  
		DELETE FROM AD_ProductCity
		WHERE 
			ProductId = #productId# 
	
		DELETE
		FROM [Sales].[CS_ContractItemRelation]
		WHERE ID=#id#	
	
    
    
    	DELETE FROM TG_NaviDealTag
        WHERE
        DealGroupID=#dealGroupId# and TagID=#tagId#
      
        delete from TG_NaviTagItemAttribute WHERE ItemID=#itemId#
    
    
    
    	DELETE FROM TG_NaviDealTag
        WHERE
        DealGroupID=#dealGroupId# and TagID=#tagId#
    
        DELETE FROM TG_DealGroupCity
        WHERE DealGroupID = #dealGroupId#
    
		DELETE FROM TG_ShopRegion
		WHERE ShopID = #shopId#
	
		DELETE FROM
		CQRS_ORDER WHERE ORDER_ID=#entity.id#
	
        DELETE FROM TPD_DealGroupAttribute
        WHERE DealGroupId = #dealGroupId#
    
    
	
		DELETE FROM AD_ItemKeyword WHERE OrderItemId = #orderItemId#;
		  
	
			
		DELETE
		FROM AD_OrderItemDuration
		WHERE OrderItemId = #orderItemId#;
		
	
	
		DELETE FROM Sales.CS_ContractPayTerms
		WHERE ContractID = #contractId#
		AND TermID = #termId#
	
		DELETE from DP_dpQzoneFollowNote where FollowNoteID = #followNoteId# AND FromUserID=#fromUserId#;
	
        DELETE FROM TG_DealGroupVerify
        WHERE DealGroupID = #dealGroupId#
    
		DELETE FROM TG_DealRegion
		WHERE DealGroupID = #dealGroupId#
	
        DELETE FROM TG_Vendor
        WHERE DealID = #dealId#
    
		DELETE FROM
		CQRS_SEAT_AVAILABILITY WHERE SEAT_AVAILABILITY_ID=#entity.id#
	
		UPDATE TG_DealGroupDisableDate SET
		DisableStatus = 0 AND
		UpdateTime = NOW()
		WHERE DealGroupID = #dealGroupID#
  	
        delete from TG_DealGroup
        where DealGroupID = #dealGroupId#
    
    	DELETE FROM DP_FeedBackForPOI
    	WHERE
    		FeedID=#feedId#
    
		DELETE FROM
			TG_NaviTagItem 
		WHERE
			ItemId=#itemId#
	
		DELETE FROM
			TG_NaviTagItem 
		WHERE
			ItemId=#itemId#
	  
        delete from TG_NaviTagItemAttribute WHERE ItemID=#itemId#
    
        DELETE FROM TG_NaviDealCategory
        WHERE DealGroupID = #dealGroupId#
    
        DELETE FROM TG_Deal
        WHERE DealID = #dealId#
    
        DELETE FROM TG_Deal
        WHERE DealGroupID = #dealGroupId#
    
	 
		DELETE FROM AD_ItemButton WHERE ItemButtonId = #itemButtonId#;
	
	
	 
		DELETE FROM AD_ItemButton WHERE OrderItemId = #orderItemId#;
	
	
	 
		DELETE FROM AD_OrderItem WHERE OrderItemId = #orderItemId#;
		  
	
	 
		DELETE FROM AD_OrderItem WHERE OrderId = #orderId# AND CityId = #cityId#;
		  
	
		DELETE FROM
		TG_DealGroupDetail
		WHERE DealGroupID = #dealGroupID#
	
		UPDATE TG_DealGroupDisableDate SET
		DisableStatus = 0 AND
		UpdateTime = NOW()
		WHERE DealGroupID = #dealGroupID#
  	
		DELETE FROM TG_ShopInfo
		WHERE ShopID = #shopId#
	
	 
		DELETE FROM AD_ItemCityTone WHERE OrderItemId = #orderItemId#;
	
	
		DELETE FROM AD_Price
		WHERE DatePeriodKey=#datePeriodKey# AND CityGroupKey=#cityGroupKey#
	
		DELETE FROM AD_Price
		WHERE DatePeriodKey=#datePeriodKey#
		AND CityId=#cityId# AND ProductId=#productId#
	
		DELETE FROM AD_Price
		WHERE DatePeriodKey=#datePeriodKey#
		AND CityId=#cityId# AND ProductId=#productId# AND
		PriceLevel=#priceLevel#
	
        DELETE FROM TPD_DealGroupAttribute
        WHERE DealGroupId = #dealGroupId#
    
        DELETE FROM TPD_DealGroupAttributeText
        WHERE DealGroupId = #dealGroupId#
    
		DELETE FROM
		TG_DealGroupShopDetail
		WHERE DealGroupID = #dealGroupID#
	
		DELETE FROM TG_DealShopInfo
		WHERE DealGroupID = #dealGroupId#
	
        DELETE FROM DP_dpQzoneMessage
        WHERE MsgID = #msgId#
    
        DELETE FROM DP_dpQzoneUserOperation
        WHERE
        UserID=#userId# AND OperationType=#operationType# AND ObjectID=#objectId# AND ObjectType=#objectType#
    
        DELETE FROM TPD_DealGroupPublishCategory
        WHERE DealGroupID = #dealGroupId#
    
        DELETE FROM TPD_DealGroupAttributeText
        WHERE DealGroupId = #dealGroupId#
    
        DELETE FROM TPD_DealGroupPublishCategory
        WHERE DealGroupID = #dealGroupId#
    
	    DELETE FROM DP_OfficialAlbum WHERE ID IN ($albumIdStr$)
	
		DELETE FROM EV_Questionnaire
		WHERE QuestionnaireID = #questionnaireId#
	
		DELETE FROM EVENT_Black
		WHERE UserID=#userId#
	
        DELETE FROM DP_EdmRecord
    
	    DELETE FROM DP_OfficialAlbum WHERE ID IN ($albumIdStr$)
	
				
			DELETE P FROM EV_Prize AS P LEFT JOIN EV_PrizeGroup AS PG ON P.GroupID=PG.GroupID
			WHERE P.PrizeID = #prizeId# AND PG.EventID=#eventId#;
		
	
				
			DELETE FROM EV_Prize
			WHERE PrizeID = #prizeId# 
		
	
	     DELETE FROM EV_BackLottery
	     WHERE RecordID=#recordId# 
    
	    	DELETE FROM EV_PromoType
			WHERE PromoType=#promoType#	
	
        DELETE FROM DP_BizJournalAccount
        WHERE TradeFromName = '大众点评网'
    
		DELETE FROM DP_PicTag 
        WHERE PicID IN ($picIds$)
	
		DELETE FROM EV_QuestionnaireOption
		WHERE OptionID=#optionId#
	
		DELETE FROM EV_QuestionnaireOption
		WHERE QuestionnaireID=#questionnaireId#
	
		DELETE FROM DPEvent.EventList
		WHERE
		EventID = #eventId# ;
	
	    	DELETE FROM Admin
			WHERE UserID=#userId#	
	
         DELETE FROM EV_BackLotteryLog WHERE LogID=#logId#
    
		DELETE FROM EV_QuestionnaireOption
		WHERE OptionID=#optionId#
	
		DELETE FROM EV_QuestionnaireOption
		WHERE QuestionnaireID=#questionnaireId#
	
		DELETE FROM EV_QuestionnaireResultRule
		WHERE ResultID = #resultId#
	
		DELETE FROM EV_QuestionnaireResultRule
		WHERE GroupID=#groupId#
	
		DELETE FROM DPEvent.EventList
		WHERE
		EventID = #eventId# ;
	
		DELETE FROM DP_PicTag 
        WHERE PicID IN ($picIds$)
	
			DELETE FROM EventList 
			WHERE
			EventID = #eventId# ;
	
				
			DELETE P FROM EV_Prize AS P LEFT JOIN EV_PrizeGroup AS PG ON P.GroupID=PG.GroupID
			WHERE P.PrizeID = #prizeId# AND PG.EventID=#eventId#;
		
	
				
			DELETE FROM EV_Prize
			WHERE PrizeID = #prizeId# 
		
	
		DELETE FROM EVENT_Black
		WHERE UserID=#userId#
	
		DELETE FROM EV_Promo WHERE PromoId =#promoId# AND  EventID = #eventId#
	
		DELETE FROM EV_Questionnaire
		WHERE QuestionnaireID = #questionnaireId#
	
	    	DELETE FROM EVENT_Admin
			WHERE UserID=#userId#	
	
        DELETE FROM DP_EdmDealGroup
    
        DELETE FROM DP_EdmDealGroupShop
    
			DELETE FROM EventList 
			WHERE
			EventID = #eventId# ;
	
		DELETE FROM EVENT_Black
		WHERE UserID=#userId#
	
		DELETE FROM EVENT_Black
		WHERE UserID=#userId#
	
				
			DELETE P FROM EV_Prize AS P LEFT JOIN EV_PrizeGroup AS PG ON P.GroupID=PG.GroupID
			WHERE P.PrizeID = #prizeId# AND PG.EventID=#eventId#;
		
	
				
			DELETE FROM EV_Prize
			WHERE PrizeID = #prizeId# 
		
	
		DELETE FROM EV_QuestionnaireResultRule
		WHERE ResultID = #resultId#
	
		DELETE FROM EV_QuestionnaireResultRule
		WHERE GroupID=#groupId#
	
			DELETE FROM DPEvent.EventList 
			WHERE
			EventID = #eventId# ;
	
		DELETE FROM EV_VoteCandidate WHERE CandidateId =#candidateId#
	
			DELETE FROM EV_VoteEvent 
			WHERE
			VoteEventID = #voteEventId#
	
		DELETE FROM
		DP_ThirdFeedUserSubscription
		WHERE
		ThirdUID = #thirdUid# 
		AND
		Type =#type# 
		AND 
		FeedType=#feedType#
	
    	DELETE FROM DP_GroupNoteDraft
    	WHERE GroupID = #groupId#
    		AND UserID = #userId#
    
		DELETE FROM GP_GroupCategory
		WHERE
			CategoryID = #categoryId#;
	
		DELETE FROM GP_GroupCategory
		WHERE
			CategoryID = #categoryId# AND GroupID = #groupId#;
	
		DELETE FROM 
			DP_GroupHonorUser 
		WHERE
			HonorTitleID=#titleId#
	
		DELETE FROM
		DP_GroupNoteActivityDetail
		WHERE
		MainNoteID=#mainNoteId#
	
		DELETE FROM
		DP_GroupNoteActivity
		WHERE
		MainNoteID=#mainNoteId#
	
    	DELETE FROM GP_QAUserInfo WHERE GroupSetID = #groupSetId# AND UserID = #userId#
    
		DELETE FROM
			GP_EventFollowNoteLast
		WHERE
			EventID = #eventId#
			AND UserID = #userId#
	
		DELETE FROM DP_GroupMedalDetail WHERE GroupID=#groupId#
	
    	DELETE FROM DP_GroupNoteType 
    	WHERE GroupNoteTypeID=#noteTypeId#
    
		UPDATE 
	    	GP_GoldDetail
	    SET 
	    	GoldType=#goldTpe#
		WHERE 
			ID=#detailId#
	
		DELETE FROM
			GP_Group_AdminPower
		WHERE
			AdminID = #loginId#
	
		DELETE FROM
			DP_GroupDCashActivity
		WHERE
			ActivityID=#activityId#
	
		DELETE FROM GP_RecommendGroup 
		WHERE
			CityID = #cityId#;
	
		DELETE FROM GP_Category WHERE CityID=#cityId#
	
		DELETE FROM GP_GroupCategory WHERE CategoryID=#categoryId#
	
		DELETE FROM GP_GroupCategory WHERE GroupID=#groupId#
	
		DELETE FROM GP_GroupCategory WHERE CategoryID=#categoryId# AND GroupID=#groupId#
	
	
		DELETE FROM DP_GroupLink WHERE GroupID=#groupId#
	
		DELETE FROM
			GP_NoteShopDetail
		WHERE
			NoteID=#noteId#
	
		
		delete from 
			GP_WinningUserInfo
		where
			WinningUserInfoID=#winningUserInfoId#
		
	
		
		delete from 
			GP_WinningUserInfo
		where
			WinningUserInfoID=#winningUserInfoId#
		
	
		DELETE FROM
			HT_DataCount_Group
		WHERE
			ModuleID=#moduleId# AND DayID=#dayId#
	
    
		DELETE FROM
		MP_Geocoding
		WHERE
		ID=#poiId#
	
		DELETE FROM GP_RecommendGroup WHERE CityID=#cityId#
	
		DELETE FROM DP_GroupUser WHERE GroupID = #groupId# and UserID = #userId#
	
		
		delete
		from GP_EventParticipant
		where EventID=#eventId#
		
	
		
		delete
		from GP_EventParticipant
		where EventID=#eventId#
		and UserID=#userId#
		
	
		
		delete from
		DP_EventUser
		where EventID=#eventId# and UserID=#userId#
		
	
		DELETE 
		FROM 
			GP_GroupElder
		WHERE
			cityId=#cityId#
			AND groupPermaLink=#groupPermaLink#
			AND elderId=#elderId#		
	
		DELETE FROM DP_EventRecommend 
		WHERE
			EventID = #eventId# AND CityID = #cityId#;		
	
		DELETE FROM
			GP_HotNote
		WHERE
			HotNoteID = #hotNoteId#;
	
		DELETE
		FROM
			GP_PageModuleConfig
		WHERE
			CityID = #cityId# AND ModuleID = #moduleId#;
	
		DELETE FROM
			GP_RecommendNote
		WHERE
			CityID = #cityId# AND RecType = #recType#;
	
		DELETE FROM GP_Category
		WHERE
			CategoryID = #categoryId#;
	
		delete from
		MP_Geocoding
		where
		ID=#poiId#
	
            delete
            from mc_table_info
            where table_id = hex(sha1(concat(#schema_name#,#table_name#,#db_name#,#storage_type#)))%(1024*1024*1024-1)
    
            delete
            from mc_column_info
            where table_id = hex(sha1(concat(#schema_name#,#table_name#,#db_name#,#storage_type#)))%(1024*1024*1024-1)
    
           delete
           from mc_data_task_map
           where task_id = #taskId#
    
        delete
        from mc_data_map_g
        where task_id = #taskId#
    
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from jrobin_datasource where id=#id#
    
    
      
    	 delete from jrobin_host where id=#id#
    
    
      
    	 delete from jrobin_rra where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where cdef_id=#cdef_id#
    
    
      
    	 delete from jrobin_datasource_item where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where datasource_id=#datasource_id#
    
    
      
    	 delete from consumer_rule where id=#id#
    
    
      
    	 delete from consumer_service where id=#id#
    
    
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_cdef where id=#id#
    
    
      
    	 delete from jrobin_cdef where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where datasource_id=#datasource_id#
    
    
      
    	 delete from jrobin_graph where id=#id#
    
    
      
    	 delete from jrobin_host where id=#id#
    
    
	
		DELETE FROM
		HUI_RedeemRule WHERE RedeemRuleId=#entity.id#
	
      
    	 delete from jrobin_graph where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where cdef_id=#cdef_id#
    
    
      
    	 delete from jrobin_datasource_item where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where datasource_id=#datasource_id#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from jrobin_color where id=#id#
    
    
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_datasource_rra where datasource_id=#datasourceId#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_cdef where id=#id#
    
    
      
    	 delete from jrobin_color where id=#id#
    
    
      
    	 delete from jrobin_graph_item where id=#id#
    
    
      
    	 delete from jrobin_graph_item where graph_id=#id#
    
    
      
    	 delete from jrobin_datasource where id=#id#
    
    
      
    	 delete from jrobin_host where id=#id#
    
    
      
    	 delete from consumer_rule where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where cdef_id=#cdef_id#
    
    
      
    	 delete from jrobin_datasource_rra where datasource_id=#datasourceId#
    
    
	
      
    	 delete from consumer_rule where id=#id#
    
    
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_datasource_rra where datasource_id=#datasourceId#
    
    
      
    	 delete from jrobin_graph where id=#id#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from jrobin_graph where id=#id#
    
    
      
    	 delete from jrobin_graph_item where id=#id#
    
    
      
    	 delete from jrobin_graph_item where graph_id=#id#
    
    
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_color where id=#id#
    
    
      
    	 delete from jrobin_graph_item where id=#id#
    
    
      
    	 delete from jrobin_graph_item where graph_id=#id#
    
    
		DELETE FROM
		HUI_CouponOfferApplicableShop WHERE CouponOfferApplicableShopId=#entity.id#
	
      
    	 delete from jrobin_rra where id=#id#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where datasource_id=#datasource_id#
    
    
      
    	 delete from jrobin_datasource where id=#id#
    
    
      
    	 delete from jrobin_rra where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where datasource_id=#datasource_id#
    
    
      
    	 delete from jrobin_rra where id=#id#
    
    
	
		DELETE FROM
		HUI_CouponOffer WHERE CouponOfferId=#entity.id#
	
      
    	 delete from jrobin_datasource_rra where datasource_id=#datasourceId#
    
    
      
    	 delete from jrobin_host where id=#id#
    
    
      
    	 delete from jrobin_host where id=#id#
    
    
      
    	 delete from jrobin_datasource where id=#id#
    
    
      
    	 delete from jrobin_graph_item where id=#id#
    
    
      
    	 delete from jrobin_graph_item where graph_id=#id#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from consumer_service where id=#id#
    
    
      
    	 delete from jrobin_cdef where id=#id#
    
    
      
    	 delete from jrobin_cdef where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where cdef_id=#cdef_id#
    
    
      
    	 delete from jrobin_color where id=#id#
    
    
      
    	 delete from jrobin_graph where id=#id#
    
    
      
    	 delete from jrobin_graph_item where id=#id#
    
    
      
    	 delete from jrobin_graph_item where graph_id=#id#
    
    
      
    	 delete from jrobin_host where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where cdef_id=#cdef_id#
    
    
      
    	 delete from jrobin_color where id=#id#
    
    
      
    	 delete from jrobin_datasource_rra where datasource_id=#datasourceId#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
      
    	 delete from jrobin_rra where id=#id#
    
    
      
    	 delete from consumer_service where id=#id#
    
    
      
    	 delete from jrobin_datasource where id=#id#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
	
	
		DELETE FROM MC_MemberCardUserFeed 
		WHERE FeedID = #feedId# AND ADDTIME >= #beginDate# AND ADDTIME < #endDate#
	
	
		DELETE m FROM TG_PrepaidCardRemindMsg m
		WHERE CAST(#today# AS Date) = m.RemindDate
		AND m.RemindStatus = 0
	
	
		delete from
			DP_WordBiz
		where
			WordID=#wordID#
	
		delete from
			DP_Word
		where
			ID=#id#
	
    	DELETE FROM user_role WHERE roleId = #roleId# AND userId = #userId#
    
    	DELETE FROM role_privilege WHERE roleId = #roleId#
    
    	DELETE FROM role WHERE id = #roleId#
    
    	DELETE FROM user_role WHERE roleId = #roleId#
    
    	DELETE FROM role_privilege WHERE roleId = #roleId#
    
		delete from team where id=#id#
    
		delete from environment where id=#id#
    
		delete from job_exec_time where id=#id#
    
		delete from product where id=#id#
    
    	DELETE FROM config WHERE id = #configId#
    
    
    	DELETE FROM config_status WHERE configId = #configId# AND envId = #envId#
    
		delete from project where id=#id#
    
    		DELETE FROM project_owner WHERE projectId = #projectId# AND userId = #userId#
    
    		DELETE FROM project_member WHERE projectId = #projectId# AND userId = #userId#
    
    		DELETE FROM project_operator WHERE projectId = #projectId# AND userId = #userId#
    
	
	 
		DELETE FROM MC_Tags WHERE TagId = #tagId#
	
	
		DELETE FROM MMC_AdFlow WHERE adId = #adId#
	
		
		DELETE FROM MMC_AdShopRelation
		WHERE adId = #adId# AND sid = #shopId#
		
	
		
		DELETE FROM MMC_AdShopRelation
		WHERE adId = #adId#
		
	
		
		DELETE FROM MMC_UserInfo
		WHERE uId = #userId#
		
	
	  
		DELETE FROM CI_ScoreBoardTemp
		WHERE AddDate < #addDate# 
	  
	
		DELETE FROM DP_Unsubscribe 
		WHERE DpId = #DpId# AND Businesstype = #Businesstype#
	
		DELETE FROM DP_PushRead 
		WHERE DpId = #DpId# AND Businesstype = #Businesstype#
	
      
    	 delete from jrobin_tree_node where id=#id#
    
    
      
    	 delete from jrobin_tree_graph where tree_id=#id#
    
    
      
    	 delete from jrobin_tree_graph where graph_id=#id#
    
    
      
    	 delete from serviceReport where id=#id#
    
	
      
    	 delete from serviceReportUser where serviceReportId=#serviceReportId#  and userId=#userId#
    
	
      
    	 delete from jrobin_datasource_item where id=#id#
    
    
      
    	 delete from jrobin_datasource_item where datasource_id=#datasource_id#
    
    
      
    	 delete from jrobin_graph where id=#id#
    
    
      
    	 delete from jrobin_graph_item where id=#id#
    
    
      
    	 delete from jrobin_graph_item where graph_id=#id#
    
    
	
		DELETE
		FROM DP_MessageSendWhiteList
		WHERE UserID = #userId#
	
    	DELETE FROM CI_SceneryOrderDetail
    	WHERE ID=#orderId# 
 	
		DELETE FROM CI_UserFeed WHERE UserID = #userID# AND FeedType = #feedType#
	
		DELETE FROM CI_UserThirdUID 
		WHERE thirdUid= #thirduid#
	
		DELETE FROM CI_UserFeed WHERE FeedToken = #feedToken# AND FeedType = #feedType#
	
    	
    		DELETE FROM MMA_AdItem
    		WHERE $pIndex$ = #pValue#
    	



























    
		DELETE FROM  DP_SysAccessControlRule
		WHERE RuleID=#ruleId#
	
		DELETE FROM  DP_SysPrivateTask
		WHERE TaskID=#taskId#
	
		DELETE FROM  DP_SysSubscriptionTask
		WHERE TaskID=#taskId#
	
         DELETE FROM
         MP_ShopAccount_Role
         WHERE ShopAccountId = #shopAccountId#  and   roleId = #roleId#;
     ;
    
         DELETE FROM
         MP_ShopAccount_Role
         WHERE ShopAccountId = #shopAccountId#;
     
         DELETE FROM
         MP_Module
         WHERE ModuleId = #moduleId# ;
     
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE CustomerId = #customerId#
	
        DELETE FROM MP_Component WHERE
        Id=#entity.id#
    
        DELETE FROM MP_MerchantMessage WHERE
        Id=#entity.id#
    
        DELETE FROM DianPingBC.BC_ShopAccount_Token WHERE
        ID=#entity.id#;
    
		DELETE FROM
		CQRS_ORDER WHERE ORDER_ID=#entity.id#
	
		DELETE FROM
		MP_Role
		WHERE
		RoleId = #roleId#
	
		DELETE FROM
		MP_RoleFunctionAssn
		WHERE RoleId = #roleId#
	
        DELETE FROM MOPay_Settle_Transaction
        WHERE ID = #transactionId# and ShopID = #shopId#
    
	 
		DELETE FROM MC_FileOrder WHERE Id = #id#
	
	
	 
		DELETE FROM MC_FileTag WHERE Id = #id#
	
	
	
		
		DELETE FROM MMC_AdInfo
		WHERE UID = #userId#
		
	
		DELETE FROM CI_AutoPush
		WHERE AutoPushID = #autoPushId# 
	
		DELETE FROM CI_AutoPush
		WHERE PushDate = #pushDateStr# 
	
        DELETE FROM CI_MessageInfo WHERE ID=#msgId#
    
        DELETE FROM CI_OssUtm
        WHERE Utm = #utm#
    
      
    	 delete from server where id=#id#
    
	
      
    	 delete from serverProject where serverId=#serverId# and projectId=#projectId#
    
	
      
    	 delete from serverProject where serverId=#serverId#
    
	
      
    	 delete from config_envir where config_id=#configId# and envir_id=#envirId#
    
	
      
    	 delete from config_envir where config_id=#id#
    
	
      
    	 delete from config_envir where envir_id=#id#
    
	
      
    	 delete from config_public where id=#id#
    
    
      
    	 delete from config_public_config where publicId=#publicId# and configId=#configId#;
    
    
      
    	 delete from config_public_config where publicId=#id#
    
    
      
    	 delete from config_public_config where configId=#id#
    
    
      
    	 delete from project where id=#id#
    
    
    	DELETE FROM service WHERE projectId NOT IN(SELECT id FROM project);
    
    	DELETE FROM configdetail WHERE projectId NOT IN(SELECT id FROM project);
    
      
    	 delete from service where id=#id#
    
    
      
    	 delete from serviceHost where serviceId=#serviceId# and envId=#envId#
    
    
      
    	 delete from serviceHost where serviceId=#serviceId#
    
    
      
    	 delete from user where id=#id#
    
	
      
    	 delete from user_role where user_id=#id#
    
	
      
    	 delete from user_role where role_id=#id#
    
	
      
    	 delete from role where id=#id#
    
	
      
    	 delete from resource where id=#id#
    
	
      
    	 delete from resource_role where role_id=#id#
    
	
      
    	 delete from jrobin_color where id=#id#
    
    
      
    	 delete from jrobin_key where id=#id#
    
    
    	DELETE FROM DP_AlertMark WHERE userid = #userid# and type = #type#
    
    	DELETE FROM DP_AlertMark WHERE dpid = #dpid# and userid = #userid#
    
    	DELETE FROM DP_BannerList WHERE type = #type#
    
		DELETE FROM BC_ResetPasswordRequest WHERE
		ResetPasswordRequestId=#entity.id#
	
		DELETE FROM BC_Tip WHERE
		TipId=#entity.id#
	
    
		DELETE FROM  DP_SysNoticeTask
		WHERE TaskID=#taskId#
	
        DELETE FROM MP_Assoc_Account WHERE MasterId = #masterId# AND SlaveId = #slaveId#
    
         DELETE FROM
         MP_Function
         WHERE FunctionId = #functionId#
     
		DELETE FROM BC_ResetPasswordRequest WHERE
		ResetPasswordRequestId=#entity.id#
	
		DELETE FROM
		CQRS_SEAT_AVAILABILITY WHERE SEAT_AVAILABILITY_ID=#entity.id#
	
        












        delete from MP_ShpAcnt_OpenId_Assn where shopAccountId = #shopAccountId#












        
	
      DELETE FROM 
        MOBILE_SCHEME_URL
      WHERE
        ID=#id#
    
	 
		DELETE FROM MC_Files WHERE FileId = #fileId#
	
	
	 
		DELETE FROM MC_FileAccount WHERE Id = #id#
	
	
	
	 
		DELETE FROM MC_TagGroup WHERE GroupId = #groupId#
	
	
         update HT_EbizPromo
         set Status=3
         WHERE EBizPromoID=#ebizpromoid# 
	
		
		DELETE FROM MMC_DayConsume
		WHERE adId <= 10
		
	
		
			DELETE FROM MMC_AccountFlow
			WHERE userId = #userId#
		
	
      
    	 delete from exceptionReport where id=#id#
    
	
      
    	 delete from exceptionReportUser where exceptionReportId=#exceptionReportId#
    
	
      
    	 delete from exceptionReportUser where exceptionReportId=#exceptionReportId# and userId=#userId#
    
	
      
    	 delete from jrobin_cdef where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where id=#id#
    
    
      
    	 delete from jrobin_cdef_item where cdef_id=#cdef_id#
    
    
      
    	 delete from jrobin_datasource_rra where datasource_id=#datasourceId#
    
    
	
    	DELETE FROM CIW_UserFeedList 
    	WHERE ReferID=#referId# AND FeedType=#feedType#;
    
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE CustomerId = #customerId#
	
		DELETE FROM DP_Wishlist  WHERE UserID=#userId# AND ReferID=#referId# AND WishType=#wishType#

		DELETE FROM DP_WishTag WHERE UserID=#userId# AND ReferID=#referId# AND TagType=#wishType#

    
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopId = #shopId#
	
    	DELETE FROM CI_CheckIn 
		WHERE ShopID=#shopId#
    
    	DELETE FROM CI_CheckInTopicDetail 
		WHERE CheckInID = #CheckInID#
    
		DELETE FROM CI_CustomPushFileUpload
		WHERE CustomPushID = #customPushId# 
	
        DELETE FROM TG_MovieDiscountLock
        WHERE MovieDiscountID = #movieDiscountId# AND UserID = #userId#
    
		DELETE
		FROM DP_MessageList
		WHERE OwnerUserID = #ownerUserId# AND TargetUserID = #targetUserId#
	
	
		
			DELETE 
			FROM DP_MessageList
			WHERE OwnerUserID = #ownerUserId# AND DetailID = #detailId#
		
	
         update ASPNet_zSurvey.DP_Promo 
         set Power=5 
         WHERE PromoID=#promoid#
	
		
		DELETE FROM MMC_Account
		WHERE uId = #userId#
		
	
      
    	 delete from environment where id=#id#
    
	
      
    	 delete from environment where id=#id#
    
	
      
    	 delete from middlewareTree where id=#id#
    
	
      
    	 delete from middlewareDocument where id=#id#
    
	
      
    	 delete from middlewareDocument where nodeId=#id#
   	  
	
      
    	 delete from middlewareNotice where id=#id#
    
	
      
    	 delete from sqlReview where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#exceptionAlarmConfigId#  and user_id=#userId#
    
	
      
    	 delete from alarm_config where id=#id#
    
	
      
    	 delete from alarm_config_user where alarm_config_id=#id#
    
	
      
    	 delete from jrobin_host where id=#id#
    
    
		delete from CI_MsgDeviceId
		where MsgId = #msgId# 
	
		DELETE FROM CI_MsgInfo 
		WHERE id = #id# 
	
		DELETE FROM CI_UserFeed WHERE UserID = #userID# AND FeedType = #feedType#
	
		DELETE FROM CI_UserThirdUID 
		WHERE thirdUid= #thirduid#
	
		DELETE FROM CI_UserFeed WHERE FeedToken = #feedToken# AND FeedType = #feedType#
	
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopId = #shopId#
	
    	
    		DELETE FROM MMA_Promo
			WHERE PromoID=#entity.promoId# AND ShopID=#entity.shopId#; 
    	
    
		DELETE FROM Mail_ServerList
		WHERE UUID = #uuid#
	
		DELETE FROM  DP_SysAccessControlList
		WHERE ListID=#listId#
	
    	DELETE FROM BC_ShopAccountEmail Where ShopAccountId=#shopAccountId#
    
		DELETE FROM BC_Tip WHERE
		TipId=#entity.id#
	
    
    
   		
   		DELETE FROM CI_IPhonePush WHERE PToken = #pushToken#
   		AND ID < #pushId#
   		
   	
      DELETE FROM 
        MOBILE_VERSION
      WHERE
        ID=#id#
    
	 
		DELETE FROM MC_FileOrderCopy WHERE Id = #id#
	
	
	
		
		DELETE FROM MMC_Consume
		WHERE adid = #adId#
		
	
    	delete from nginx_report_rule where id=#nginxReportRuleID#
    
    	delete from nginx_report_user where nginxReportId=#reportId# and userId=#userId#
    
    	delete from search_condition where urlRegex=#urlRegex# and threshold=#threshold#
    
      
    	 delete from jrobin_datasource where id=#id#
    
    
      
    	 delete from jrobin_rra where id=#id#
    
    
    	 
	    	DELETE 
	    	FROM DP_SubscriptionList
	    	WHERE UserID=#userId#
    	
	
    	DELETE FROM CI_CheckInTopicDetail 
		WHERE CheckInID = #CheckInID#
    
    	DELETE FROM CI_SceneryOrderDetail
    	WHERE ID=#orderId# 
 	
    	DELETE FROM CI_IPhonePush 
		WHERE PToken = #ptoken#
    
		DELETE FROM DP_Unsubscribe where dpid = #DpId# and businesstype = #Businesstype#
	
        DELETE FROM MP_MerchantMessageStatistics WHERE
        Id=#entity.id#
    
        DELETE FROM MP_UserProductAgreement WHERE
        UserProductAgreementId=#entity.id#
    
      DELETE FROM MOPay_Settle_Withdraw
      WHERE ID=#withdrawId# and ShopID = #shopId#
    
        DELETE FROM MOPay_Settle_Batch
        WHERE BatchNumber = #batchNumber#
    
        DELETE FROM MOPay_Settle_Batch_Finance
        WHERE BatchNumber = #batchNumber#
    
		DELETE FROM
			DP_CategoryList 
		WHERE
			CategoryId=#categoryId#
			AND CityId=#cityId#
	
		DELETE FROM
			DP_CategoryListFreeID 
		WHERE
			CategoryID=#categoryID#
	
		DELETE FROM
			DP_CategoryTree 
		WHERE
			CityID=#cityId#
			AND ParentCategoryId=#parentCategoryId#
			AND CategoryID=#categoryId#
	
		DELETE FROM
			DP_RegionList 
		WHERE
			RegionId=#regionId#
	
		DELETE FROM
			DP_RegionTree 
		WHERE
			RegionId=#regionId#
	
		DELETE FROM DP_DLogThirdUserRole 
		WHERE TrainID = #TrainID# AND Source = #Source# AND UserName = #UserName#
	
		DELETE FROM DP_DLogThirdUserRole 
		WHERE UserName = #UserName#
	
		Delete from  IPadQQ_Activity
		where id=#id#
	
   		delete from CI_CityHotLandMarks
   		where ID=#id#
   
        DELETE FROM RTXUM_Department
    
		DELETE FROM DianPingMobile.CI_EBizPromo 
		WHERE ID = #ID#
	
        DELETE FROM RTXUM_User
    
		DELETE FROM CI_ShopAnnouncement 
		WHERE ID = #ID#
	
		DELETE FROM PC_FollowNoteLast
		WHERE PicID = #picId# 
			AND UserID = #userId#
	
		DELETE FROM PC_FollowNoteLast
		WHERE PicID = #picId# 
	
	    DELETE FROM DP_OfficialAlbum WHERE ID IN ($albumIdStr$)
	
        DELETE FROM DP_PicTagIndex
        WHERE PicID = #shopPicTagIndex.picId#
    
    
  delete from TG_DeliverAddress
  where DeliverAddressID=#deliverAddressId#
  
        DELETE FROM DP_PicTag
        WHERE PicId = #shopPicTag.picId#
    
    
		delete from TG_OrderCoupon where OrderID = #orderID#
	
		   
		    DELETE FROM DP_InfoPic
		    WHERE InfoID = #infoId# AND InfoPicID= #infoPicId# AND UserID = #userId# 
	    
	
        DELETE FROM DP_BizJournalAccount
        WHERE TradeFromName = '大众点评网'
    
		DELETE FROM DP_PicTag 
        WHERE PicID IN ($picIds$)
	
		 
			DELETE FROM MPC_LeftPV WHERE Comments < #updateTime#; 
		 
	
        DELETE FROM DP_UserAlbum
        WHERE ShopID = #shopId#
        AND UserID = #userId#
    
  	delete from TG_OrderCoupon
    where CouponID = #couponId# and OrderID = #orderId#
  
  	delete from TG_OrderCoupon
    where OrderID = #orderId#
  
       DELETE FROM YY_BookingDealPool WHERE record_id=#recordId#
   
		DELETE FROM
		YY_RsCalloutEvent
		WHERE
		id = #id#
	
        DELETE FROM TG_ReceiptGroupCode WHERE ReceiptGroupCodeID = #receiptGroupCodeId# AND Status = 1;
    
		DELETE FROM YY_PhoneBlacklist WHERE phone = #phone#
	
        DELETE FROM YY_PhoneBlacklist
        WHERE unban_time <= #now#
    
		DELETE FROM YY_BillingRecord
		WHERE id = #id#
	
		DELETE FROM
			YY_BillingIDShopMap
		WHERE
			billing_record_id = #billingRecordID#		
	
		DELETE FROM YY_BusinessRecord
		WHERE id = #id#
	
        delete from Report_Job_Job
        where JobID = #jobId#
    
        DELETE FROM TG_ReceiptGroupCode WHERE ReceiptGroupCodeID = #receiptGroupCodeId# AND Status = 1;
    
		DELETE FROM DP_ReviewDraft
		WHERE ShopID=$reviewDraft.shopId$
		AND UserID=#reviewDraft.userId#
	
		DELETE FROM
		ZS_VoteGood
		WHERE VoteID = #voteId# AND UserID = #userId# AND RateObjectTypeID = 1
	
		DELETE FROM
		ZS_VoteGood
		WHERE VoteID = #voteId# AND RateObjectTypeID = 1
	
		DELETE FROM
		YY_ShopOnlineFlow WHERE shop_id=#shopId#
	
		DELETE FROM
		YY_ContractShop WHERE shop_id=#shopId#
	
		DELETE FROM
		Test_CallCenter
		WHERE id = #id#
	
		DELETE FROM
		Test_CallCenter
		WHERE id = #id#
	
		DELETE FROM
		Test_CallCenter_Old
		WHERE id = #id#
	
		DELETE FROM
		RT_ShopTable WHERE shop_id = #shopId#
	
		DELETE FROM
		RT_MinimumCharge WHERE shop_id = #shopId#
	
		DELETE FROM
		TMS_ShopMealType WHERE shop_id = #shopId#
	
        
        DELETE FROM MRB_CRMRelation
        WHERE
        orderId = #orderId#
        
    
		DELETE FROM YY_BookingDealPool WHERE record_id = #recordID#
	
		DELETE FROM YY_BookingDealPool WHERE operate_id = #operateID#
	
		delete from TG_Receipt_New where receiptId=#receiptId#
	
		DELETE FROM YY_BookingConfig WHERE shop_id = #shopID#
	
		DELETE FROM YY_CreditScore 
		WHERE 
		cellphone = #cellphone#
	
		DELETE FROM YY_PhoneBlacklist WHERE phone = #phone#
	
        DELETE FROM YY_PhoneBlacklist
        WHERE unban_time <= #now#
    
		DELETE FROM YY_WebBannerConfigCity 
		WHERE 
		banner_config_id = #bannnerId#
	
		DELETE FROM
		YY_CallbackEvent
		WHERE id = #id#
	
		DELETE FROM YY_BookingRecord WHERE id = #id#
	
		
			DELETE FROM DP_Review WHERE ReviewID=#reviewId#
    	
	
		DELETE FROM 
			YY_Power 
		WHERE 
			power_id=#powerId#
	
		DELETE FROM 
			YY_PowerStaff 
		WHERE 
			power_id = #powerId#	AND	staff_id= #staffId#
	
		DELETE FROM 
			YY_BookingStaff 
		WHERE 
			staff_id=#staffId#
	
		DELETE FROM RT_ShopStatus
		WHERE
		shop_id = #shopId#
	
	
		DELETE FROM
			YY_AccountConfig
		WHERE
			shop_id = #shopId#
	
		DELETE FROM YY_BillingConfig WHERE id = #id#
	
		DELETE FROM YY_ContractShop WHERE shop_id = #shopID#
	
		DELETE FROM YY_ContractShop WHERE contract_id = #contractID#
	
	 	DELETE FROM YY_ContractHead WHERE contract_id = #contractHeadID#
	
		DELETE FROM YY_ContractSequence
		WHERE
			year_id = #year#
	
		DELETE FROM YY_MonthlyBill WHERE id=#id#
	
		delete from TG_JournalVoucher where voucherID=#voucherId#
	
		DELETE FROM YY_ActivityInfo WHERE activity_id=#actId#
	
	
		DELETE FROM YY_ActivityItemInfo WHERE item_id=#actItemId#
	
        DELETE FROM YY_ActivityInfo WHERE activity_id=#actID#
    
    	DELETE FROM YY_ActivityItemShop WHERE item_id=#itemID#
    
		DELETE FROM
		ZS_FollowNote
		WHERE FollowNoteId = #followNoteId#
	
		DELETE FROM
		ZS_FollowNote
		WHERE GrandpaId = #grandpaId#
	
		DELETE FROM
		ZS_FollowNote
		WHERE MainNoteID = #reviewId# and NoteType =#noteType#
	
		DELETE FROM
		ZS_FollowNoteLast
		WHERE MainNoteID=#mainNoteId# AND NoteType=#noteType# AND UserID=#userId#
	
		DELETE FROM
		ZS_FollowNoteLast
		WHERE MainNoteID=#mainNoteId# AND NoteType=#noteType# 
	
		DELETE FROM
		ZS_FollowNoteLast
		WHERE MainNoteID=#mainNoteId# and NoteType = #noteType# and UserID=#userId#
	
		DELETE FROM DP_ReviewLog WHERE ReviewID=#reviewId#
	
		DELETE FROM YY_MobiActivityInfo WHERE id=#actId#
	
		DELETE FROM YY_BookingRecord WHERE id = #id#
	
		DELETE FROM RT_ShopStatus
		WHERE
		shop_id = #shopId#
	
		DELETE FROM
		RT_ShopMealType WHERE shop_id = #shopId#
	
		DELETE FROM
		RT_ShopContact WHERE shop_id = #shopId#
	
		DELETE FROM
		RT_ShopAddress WHERE shop_id = #shopId#
	
		DELETE FROM
		RT_ShopConfig WHERE shop_id = #shopId#
	
		DELETE FROM
		RT_ShopTable WHERE shop_id = #shopId#
	
		DELETE FROM
		RT_MinimumCharge WHERE shop_id = #shopId#
	
		DELETE FROM YY_CreditScore 
		WHERE 
		cellphone = #cellphone#
	
        DELETE FROM YY_BookingShopRange
        WHERE year_id = #year# AND month_id = #month# AND city_id = #cityId#
    
		DELETE FROM YY_BookingConfig WHERE shop_id = #shopID#
	
		DELETE FROM YY_OperationLog WHERE id=#id#
	
	 	DELETE FROM YY_Contract WHERE contract_id = #contractHeadID#
	
        DELETE FROM YY_BookingPayDetail
        WHERE shop_id = #shopID#
        AND create_by = 'replenish script'
    
	
		delete from TG_ReceiptVerifyRecord where ReceiptVerifyRecordID=#recordId#
	
		DELETE FROM YY_MobiActivityInfo WHERE id=#actId#
	
		
			DELETE FROM DP_ReviewJobCompete WHERE JobID=#jobId#
    	
	 
		DELETE FROM DP_ReviewDeleteLog
		WHERE ReviewID = #reviewId#
	
		DELETE b.*, r.*
		FROM YY_BankAccount b inner join YY_BankAccountShop r on b.id=r.account_id
		WHERE r.shop_id = #shopId# and r.approval_status != 30
	
		DELETE b.*, r.*
		FROM YY_BankAccount b inner join YY_BankAccountShop r on b.id=r.account_id
		WHERE r.shop_id = #shopId# and r.approval_status = 30
	
		DELETE b.*, r.*
		FROM YY_BankAccount b inner join YY_BankAccountShop r on b.id=r.account_id
		WHERE r.shop_id = #shopId# and r.approval_status != 30
	
		DELETE r.* 
		FROM YY_BankAccountShop r 
		WHERE r.shop_id = #shopId# and r.approval_status = 30
	
		DELETE FROM YY_ActivityInfo WHERE activity_id=#actId#
	
	
		DELETE FROM YY_ActivityItemInfo WHERE item_id=#actItemId#
	
        DELETE FROM YY_ActivityInfo WHERE activity_id=#actID#
    
		DELETE FROM YY_MongoRule WHERE
			id = #id#
	
		DELETE FROM
		YY_CC_BookingEvent
		WHERE
		id = #id#
	
		delete from YY_CC_BookingEvent
	
		delete from YY_CC_BookingEvent
		where init_task_id = #initTaskID#
	
		DELETE FROM YY_OperationLog WHERE id=#id#
	
		DELETE FROM TMS_Menu
		WHERE
			shop_id = #shopID#
			AND menu_id = #menuID#
	
		DELETE FROM
		TMS_Seat WHERE shop_id = #shopId#
	
		DELETE FROM
		TMS_MinimumCharge WHERE shop_id = #shopId#
	
        DELETE FROM TG_ReceiptGroupCode WHERE ReceiptGroupCodeID = #receiptGroupCodeId# AND Status = 1;
    
		DELETE FROM YY_Docket
		WHERE
			docket_key = #docketKey#
	
	 	DELETE FROM YY_DunningConfig WHERE shop_id = #shopID#
	
        DELETE from YY_ShopBookableStatus
        WHERE shop_id = #shop_id# AND date = #date#
    
	
		delete from TG_ReceiptVerifySuccessLog where ReceiptVerifySuccessLogID=#logId#
	
		DELETE
		FROM YY_CustomRebateScope
		WHERE activity_id = #id#
	
		DELETE FROM YY_CustomRebateScope
		WHERE custom_id=#customID#
	
		DELETE
		FROM YY_CustomRebateItemInfo
		WHERE id = #id#
	
		DELETE FROM
		YY_RsCallbackEvent
		WHERE id = #id#
	
		
			DELETE FROM DP_ReviewBeeUser
    	
	 
		DELETE FROM
		PC_PicFlower
		WHERE PicID = #picId# AND FromUserID = #fromUserId#
	
    	DELETE FROM DP_UserAlbum WHERE UserID = #userId# AND ShopID = #shopId#
    
        
    		delete from DP_OTAHotelExtInfo
            where shopId=#shopId#
    	
    
        DELETE FROM DP_OTAHotelPrice
        where
        ShopID=#shopId#
        AND
        TO_DAYS(PriceDate)=TO_DAYS('$priceDate$')
        AND
        OTAID = #otaId#
    
        
        DELETE FROM DP_OTAHotelPrice
        where PriceDate < #deletedate#
        
    
        
        DELETE FROM DP_OTAHotelPrice
        where PriceDate = #deletedate#
        and ShopId = #shopid#
        
    
		DELETE from DP_VoteRankFollowNote where FollowNoteID = #followNoteId# AND FromUserID=#fromUserId#;
	
		DELETE from DP_VoteRankFollowNote where MainNoteID = #mainNoteId# AND UserID=#userId#;
	
        DELETE FROM 
        	DP_VoteRankTagRecord
        WHERE 
        	VoteRankID=#voteRankId#
	
		DELETE FROM DP_BookingShopRank WHERE CategoryID = #categoryID#;
	
        DELETE FROM WED_WeddingHotelHallPicRefSpace
        WHERE
            HallID = #hallId#
    
		DELETE FROM DP_ShopCategory WHERE ShopID = #shopId#;
	
		DELETE FROM
		DP_Block
		WHERE UserID = #userId# AND BlockUserID = #blockUserId#
	
		DELETE FROM
		ZS_Friend
		WHERE UserID = #userId# AND FriendID = #otherUserId#
	
		DELETE FROM DP_ShopCategory WHERE ShopID = #shopId#;
	
		DELETE FROM
			DP_RegionTree 
		WHERE
			RegionId=#regionId#
	
		DELETE FROM
			DP_ShopCategory 
		WHERE
			ShopId=#shopId#
			AND CategoryId=#categoryId#
	
		DELETE FROM
			DP_ShopCategory 
		WHERE
			ShopId=#shopId#
	
		DELETE FROM
			DP_ShopModification 
		WHERE
			ShopLogId=#shopLogId#
	
        
            delete from DP_HotelRoomStock
            where PriceDate< date(#previousDate#)
        
    
        delete
        from DP_HotelEventContent
        where eventID = #eventID#
        and platform = #platform#
    
		
			DELETE FROM DP_ShopVideo
			WHERE
				VideoID = #videoId#
		
	
        delete
        from DP_HotelEventLaunch
        where adID = #adID#
    
        delete
        from DP_HotelEventAD
        where eventID = #eventID#
        and srcID = #srcID#
    
        delete from DP_HotelEventAD where adID = #adID#
    
		
			DELETE FROM WED_ProductCategory
			WHERE
				ProductID = #productId#
		
	
        DELETE FROM WED_PicSpace
        WHERE PicID=#picId#
    
    
        
			DELETE FROM WED_SpecialTopic WHERE id = #specialTopicId#
		
	
        DELETE FROM WED_WeddingHotelHallOfficialAlbumRefSpace
        WHERE
            HallID = #hallId#
    
        
			DELETE FROM WED_EventSignUpShop WHERE Id = #id#
		
	
		DELETE FROM MC_MemberCardUserFeed 
		WHERE FeedID = #feedId# AND ADDTIME >= #beginDate# AND ADDTIME < #endDate#
	
	    DELETE FROM DP_OfficialAlbum WHERE ID=#albumId#
	
	
        DELETE FROM DP_OTAHotelPrice 
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
    
        
        DELETE FROM DP_OTAHotelPrice
        where PriceDate < #deletedate#
        
    
        
        DELETE FROM DP_OTAHotelPrice
        where PriceDate = #deletedate#
        and ShopId = #shopid#
        
    
        DELETE
        FROM DP_MyListTagRecord
        WHERE ListID=#listId#
	
        DELETE
        FROM
          WED_NewShopPhone
        WHERE
          ID=#id#
    
        DELETE
        FROM
          WED_NewShopPhone
        WHERE
          ShopID=#shopId#
    
        DELETE
        FROM
          WED_NewShopPhone
        WHERE
          PhoneID=#phoneId#
    
		DELETE FROM DP_MyListFlower WHERE MyListID=#myListId# AND FromUserID=#fromUserId#
	
		DELETE from DP_MyListFollowNote where FollowNoteID = #followNoteId# AND FromUserID=#fromUserId#;
	
		DELETE from DP_MyListFollowNoteLast where MainNoteID = #mainNoteId# AND UserID=#userId#;
	
		
			DELETE FROM DP_ShopDishTag WHERE ShopID=#shopId# AND UserID=#userId#  and DishTagName=#dishTagName#
    	
	
		
			DELETE FROM DP_ShopDishTag WHERE ShopID=#shopId# AND CookieUserID=#cookieUserID#  and DishTagName=#dishTagName#
    	
	
		
			DELETE FROM DP_ShopDishTag WHERE ShopID=#shopId# AND UserID=#userId#  and CookieUserID IS NULL
    	
	
		
			DELETE FROM DP_ShopSpecTag WHERE ShopID=#shopId# AND UserID=#userId# and TagName=#tagName#
    	
	
		
			DELETE FROM DP_ShopSpecTag WHERE ShopID=#shopId# AND CookieUserID=#cookieUserID# and TagName=#tagName#
    	
	
		DELETE
		FROM DP_ShopTag
		WHERE ShopID=#shopId#
		AND UserID=#userId#
	
	
    	DELETE FROM DP_MyList WHERE ListID = #listId#;
	
		DELETE FROM DP_MyListCityHot WHERE ListID = #listId#;
	
		DELETE from DP_MyListFollowNote where FollowNoteID = #followNoteId# AND FromUserID=#fromUserId#;
	
		DELETE from DP_MyListFollowNoteLast where MainNoteID = #mainNoteId# AND UserID=#userId#;
	
		DELETE FROM
		DP_Block
		WHERE UserID = #userId# AND BlockUserID = #blockUserId#
	
    
		DELETE
		FROM DP_DishTag
		WHERE ShopID=#shopId#
		AND UserID=#userId#
	
    
		DELETE FROM
			DP_CategoryTree 
		WHERE
			CityID=#cityId#
			AND ParentCategoryId=#parentCategoryId#
			AND CategoryID=#categoryId#
	
    	DELETE FROM DP_SeoLandMarksSearchResultStatus
        WHERE CityID=#cityId# AND RegionID=#regionId# AND CategoryID=#categoryId# AND InfoID=#infoId#
	
        delete from DP_OTAHotelPrice
        where OTAID = #otaId# and ShopID = #shopId# and PriceDate < #priceDate#
    
		DELETE FROM BC_ResetPasswordRequest WHERE
		ResetPasswordRequestId=#entity.id#
	
		DELETE FROM BC_Tip WHERE
		TipId=#entity.id#
	
    
         
			DELETE FROM WED_ShopPermissionList
			WHERE
                ID  = #id#
		
    
        DELETE
        FROM WED_SearchProductRecommend
        WHERE
            ID = #id# AND ShopID = #shopId#
        LIMIT 1
    
        DELETE
        FROM WED_SearchProductRecommend
        WHERE
            ProductID = #productId# AND ShopID = #shopId#
        LIMIT 1
    
        DELETE
        FROM WED_SearchProductRecommend
        WHERE ShopID = #shopId#
    
		DELETE FROM WED_EventPoi
		WHERE
		ID = #id#
	
		DELETE FROM WED_EventPoi
		WHERE
		EventID = #eventId#
	
		DELETE FROM WED_HotelAbnormalSmsReply
		WHERE UserMobileNo = #userMobileNo#
	
		
		DELETE FROM DP_OfficialAlbumTag WHERE AlbumID = #albumId#
		
		DELETE FROM DP_OfficialAlbumTag WHERE ShopID = #shopId#
	
	
		
		DELETE FROM DP_UserAlbumTag WHERE AlbumID = #albumId#
		
		DELETE FROM DP_UserAlbumTag WHERE UserID = #userId# AND ShopID = #shopId#
	
        DELETE FROM DP_HotelShopRank
        where
        ShopId=#shopId#
    
        delete from DP_OTAHotelOrder
        where OrderId = #orderId# and OTAID = #otaId#;
    
        DELETE FROM DP_VoteRankVote WHERE `VoteRankID` = #voteRankId# AND `ShopID` = #shopId#
    
    	
    		delete
    		from
    			WED_HotelHallSchedule
    		where
    			Id=#id#
    	
    
        DELETE
        FROM WED_HotelHallSchedule
        WHERE
              ShopId=#shopId#
          AND HallId=#hallId#
          AND date_format(ScheduleDate,'%Y%m%d') = date_format(#scheduleDate#,'%Y%m%d')
    
		
			DELETE FROM DP_ShopPhoneGroup
			WHERE
				ShopPhoneID = #shopPhoneId#
		
	
		DELETE FROM DP_ShopRanking WHERE DayID = #dayID#
	
        
          delete from DP_ShopRanking where DayID < datediff(curdate(), '1900-01-01') - 7;
        
	
		DELETE FROM
		ZS_Friend
		WHERE UserID = #userId# AND FriendID = #otherUserId#
	
		DELETE FROM DP_ShopRegion WHERE ShopID = #shopId#;
	
        DELETE FROM WED_WeddingHotelExtraInfo
        WHERE
              ShopID = #shopId#
    
		DELETE FROM
			DP_CategoryList 
		WHERE
			CategoryId=#categoryId#
			AND CityId=#cityId#
	
		DELETE FROM
			DP_CategoryListFreeID 
		WHERE
			CategoryID=#categoryID#
	
		DELETE FROM
			DP_RegionList 
		WHERE
			RegionId=#regionId#
	
		DELETE FROM
			DP_ShopRegion 
		WHERE
			ShopId=#shopId#
	
		DELETE FROM
			DP_ShopRegion 
		WHERE
			ShopId=#shopId#
			AND RegionId=#regionId#
	
		DELETE FROM
			DP_ShopTag 
		WHERE
			ShopId=#shopId# and UserId=#userId#
	
		DELETE FROM
			DP_ShopTag 
		WHERE
			ShopId=#shopId# 
	
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE CustomerId = #customerId#
	
	
    	DELETE  
    	FROM WED_LandingPageShop
    
	
		
		DELETE FROM DP_ShopProductPic
		WHERE ProductID=#productId#
		
	
        DELETE FROM WED_WeddingHotelExtraInfo
        WHERE
              ShopID = #shopId#
    
        DELETE FROM WED_WeddingHotelMenu
        WHERE
            ShopId = #shopId#
    
        DELETE FROM WED_WeddingHotelMenu
        WHERE Id = #id#
    
        DELETE
        FROM WED_ShopProductTag
        WHERE ProductID = #productId#
    
        DELETE
        FROM WED_ShopProductTag
        WHERE ID = #id#
        LIMIT 1
    
        
			DELETE FROM WED_EventTemplate WHERE Id = #id#
		
	
        
			DELETE FROM WED_EventTemplate WHERE EventId = #eventId#
		
	
        
			DELETE FROM WED_EventModule WHERE Id = #id#
		
	
        
			DELETE FROM WED_EventModule WHERE TemplateId = #templateId#
		
	
    	DELETE  
    	FROM WED_LandingPageHunqingShop
    
    	DELETE  
    	FROM WED_LandingPageSheyingShop
    
        DELETE FROM DP_BookingUser
        WHERE
        ShopID = #bookUser.shopId#
        AND
        UserPhone = #bookUser.userPhone#
        AND
        BookingDate = #bookUser.bookingDate#
        AND
        ClientType = 3
    
		DELETE FROM
		WED_Event
		WHERE
		EventID = #eventId#
	
    	DELETE FROM DP_ShopGallery_ShopList WHERE  ShowTime <= #showTime# 
	
        DELETE FROM DP_VoteRankShop WHERE `VoteRankID` = #voteRankId# AND `ShopID` = #shopId#
    
        DELETE
        FROM WED_Authority
        WHERE AuthorityID=#authorityId#
    
		
			DELETE FROM DP_BizAccountShop
			WHERE
				AccountID = #accountId#
		
	
		DELETE FROM DP_ShopPhone
		WHERE ID = #shopPhoneId#
	
		DELETE FROM DP_ShopPhone
		WHERE ShopId = #shopId#
	
		DELETE FROM DP_ShopRegion WHERE ShopID = #shopId#;
	
		DELETE FROM SE_GiftUserGot WHERE GiftUserGotId=#giftUserGotId#;
	
		
			DELETE FROM DP_ShopSpecTag WHERE ShopID=#shopId# AND UserID=#userId# and TagName=#tagName#
    	
	
		
			DELETE FROM DP_ShopSpecTag WHERE ShopID=#shopId# AND CookieUserID=#cookieUserID# and TagName=#tagName#
    	
	
		DELETE DP_InfoTag FROM DP_InfoTag ,DP_Info WHERE DP_Info.InfoID=DP_InfoTag.InfoID AND ShopID=#shopId#
	
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopId = #shopId#
	
		DELETE FROM
			DP_Shop
		WHERE
			ShopId=#shopId#
	
        DELETE FROM
          POI_ShopAssistantInfoModificationLog
        WHERE
          modifyid=#modifyid#
    
        delete From DP_HotelRoomTag
        Where ShopID=#shopId#
    
    
        DELETE FROM WED_AdProduct
        WHERE ID = #id#
        LIMIT 1
    
        DELETE FROM WED_MobileBanner
        WHERE ID = #id#
        LIMIT 1
    
		DELETE FROM WED_EventCategory
		WHERE
		ID = #id#
	
		DELETE FROM WED_EventCategory
		WHERE
		EventID = #eventId#
	
		DELETE FROM WED_EventCategory
		WHERE
		EventID = #eventId# AND CategoryID = #categoryId#
	
        DELETE FROM WED_WeddingHotelHall
        WHERE
            ShopID = #shopId#
    
        DELETE FROM WED_WeddingHotelHall
        WHERE
            ID = #hallId#
    
        DELETE
        FROM WED_ShopProduct
        WHERE ID = #productId# AND ShopID = #shopId#
        LIMIT 1
    
    
	    DELETE FROM DP_OfficialAlbumPic WHERE AlbumID=#albumId#
	
	    DELETE FROM DP_OfficialAlbumPic WHERE PicID=#picId#
	
        
			DELETE FROM WED_ShopNavigation
			WHERE
                ShopID  = #shopId#
		
    
        
			DELETE FROM WED_ShopNavigation
			WHERE
                ID  = #id#
		
    
    
        DELETE FROM WED_AuthorityUser
        WHERE AuthorityID=#authorityId#
    
        DELETE FROM WED_AuthorityUser
        WHERE UserID=#userId#
    
        DELETE FROM WED_OfficalAlbumRefSpace
        WHERE
            ShopID = #shopId#
    
        DELETE FROM WED_OfficalAlbumRefSpace
        WHERE
            ShopID = #shopId# and Type = #type#
    
		DELETE
		FROM DP_DishTag
		WHERE ShopID=#shopId#
		AND UserID=#userId#
	
	
    	DELETE FROM DP_MyList WHERE ListID = #listId#;
	
		DELETE FROM DP_MyListCityHot WHERE ListID = #listId#;
	
		DELETE FROM DP_MyListFlower WHERE MyListID=#myListId# AND FromUserID=#fromUserId#
	
        DELETE FROM DianPing.DP_MyListShopPic
        WHERE
            ListID = #listId#
            AND ShopID = #shopId#
    
		DELETE FROM SE_GiftUserGot WHERE GiftUserGotId=#giftUserGotId#;
	
		
			DELETE FROM DP_ShopDishTag WHERE ShopID=#shopId# AND UserID=#userId#  and DishTagName=#dishTagName#
    	
	
		
			DELETE FROM DP_ShopDishTag WHERE ShopID=#shopId# AND CookieUserID=#cookieUserID#  and DishTagName=#dishTagName#
    	
	
		
			DELETE FROM DP_ShopDishTag WHERE ShopID=#shopId# AND UserID=#userId#  and CookieUserID IS NULL
    	
	
		DELETE
		FROM DP_ShopTag
		WHERE ShopID=#shopId#
		AND UserID=#userId#
	
        DELETE FROM DP_BookingUser
        WHERE
        ShopID = #bookUser.shopId#
        AND
        UserPhone = #bookUser.userPhone#
        AND
        BookingDate = #bookUser.bookingDate#
        AND
        ClientType = 3
    
		DELETE FROM DP_ShopDoubtLog WHERE ShopLogID = #shopLogId#
	
    	DELETE FROM DP_SeoCityRoadSearchResultStatus
        WHERE CityID=#cityId# AND RegionID=#regionId# AND CategoryID=#categoryId# AND RoadID=#roadId#
	
		
			DELETE FROM WED_PicRefSpace
			WHERE
				ShopID = #shopId# AND RefType = #refType#
		
	
    
		
			DELETE FROM WED_ProductTag
			WHERE
				ProductID = #productId#
		
	
		
			DELETE FROM WED_BookingWhiteList
			WHERE
				ID = #Id#
		
	
        

            DELETE FROM WED_ShopTag
            WHERE
              ShopID=#shopId#
              and TagValue IN (SELECT TagValue FROM WED_Tags WHERE TagGroupValue>5)

        
    
        
			DELETE FROM WED_EventShopPoiDetail WHERE Id = #id#
		
	
        
			DELETE FROM WED_EventShopPoiDetail WHERE SignUpID = #signUpId#
		
	
        
			DELETE FROM WED_EventShopPoiDetail WHERE TemplateId = #templateId#
		
	
        DELETE
        FROM DP_MyListTagRecord
        WHERE ListID=#listId#
	
    
    
    delete from TG_ReceiptPool where ReceiptPoolID = #receiptPoolID#
  
		DELETE FROM TGM_UserFeed WHERE UserID = #userID# AND FeedType = #feedType#
	
		DELETE FROM TGM_UserThirdUID 
		WHERE thirdUid= #thirduid#
	
		DELETE FROM TGM_UserFeed WHERE FeedToken = #feedToken# AND FeedType = #feedType#
	
		DELETE FROM TG_DealDestination
		WHERE DealGroupID = #dealGroupId#
	
		DELETE FROM
		TG_DealGroupDetail
		WHERE DealGroupID = #dealGroupID#
	
	DELETE FROM TG_DealGroupVerify
	WHERE DealGroupID = #dealGroupID#
	
    DELETE FROM TG_DealReceiptInfo WHERE DealID = #dealID#
  
		UPDATE TG_OriginalDealCategory SET
		Status = 0 
		WHERE DealGroupID = #dealGroupID# AND Status = 1
  	
		DELETE FROM TG_BD_Channel 
		WHERE ChannelName = #ChannelName# AND UserName = #UserName#
	
		DELETE FROM TG_BD_Model 
		WHERE ModelID = #ModelID#
	
		DELETE FROM TG_DP_DLogThirdUserRole 
		WHERE TrainID = #TrainID# AND Source = #Source# AND UserName = #UserName#
	
		DELETE FROM TG_DP_DLogThirdUserRole 
		WHERE UserName = #UserName#
	
    	 DELETE FROM TG_ReceiptGroupCodePool WHERE
    	 Status=0 AND ((DATE(NOW()) - INTERVAL 1 DAY)>AddDate)
     
    
		DELETE FROM TG_OperationRole
		WHERE RoleID = #id#
	
		DELETE FROM TG_OperationUser
		WHERE OpUserID = #id#
	
        DELETE FROM TE_AdChannel 
        WHERE ID=#id#
    
        DELETE FROM TE_AdContainer 
        WHERE ID=#id#
    
        DELETE FROM TE_AdDetail 
        WHERE ID=#id#
    
        DELETE FROM TE_AdDimension 
        WHERE ID=#id#
    
        DELETE FROM TE_AdPosition 
        WHERE ID=#id#
    
        DELETE FROM TE_AdPublish 
        WHERE ID=#id#
    
		delete from
			TG_Event
		where
			EventID =#eventId#
	
		DELETE FROM TG_EVENT WHERE EventID=#eventId#
	
		delete from
			TG_EventPrize
		where
			PrizeId=#prizeId#
	
		delete from
			TG_Event_TopicItemCity
		where
			TopicItemID in 
				(select 
					TopicItemID 
				from 
					TG_Event_TopicItem
				where
					TopicID=#topicID#
					)
	
		delete from
			TG_Event_TopicCity
		where
			TopicID =#topicID#
	
		delete from
			TG_Event_TopicItem
		where
			TopicID=#topicID#;
	
		delete from 
			TG_Event_Topic 
		where 
			TopicID = #topicID#
	
    
    
    
    
    
    
		delete from
			TG_Event_TopicItemCity
		where
			ID=#id#
	
		delete from
			TG_Event_TopicItemCity
		where
			TopicItemID=#topicItemId# and CityID=#cityId#
	
        DELETE FROM TG_Event_ShanTuan 
        WHERE ShanID=#shanID#
    
        DELETE FROM TG_Event_ShanTuanCity 
        WHERE ID=#id#
    
		DELETE FROM DianPingMC.MC_MemberCardProductConfirmInfo WHERE ShopConfirmInfoID = #shopConfirmInfoID# ;
	
    	DELETE FROM DP_ShopGallery_ShopList WHERE  ShowTime <= #showTime# 
	
        DELETE FROM
        DianPing.DP_MyListPush
        WHERE
        ID=#id#
    
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Shop
		WHERE ShopId = #shopId#
	
      

DELETE FROM TG_JobTask WHERE JobType <40 AND JobType >0 AND Status=1
 AND JobID BETWEEN #beginId# AND #endId#;

        
    
		DELETE FROM
			EBIZ_ApplyFlow 
		WHERE
			FlowId=#flowId#
	
		DELETE FROM TG_DealScore
		WHERE DealGroupID = #dealGroupId#
	
		DELETE FROM
			TG_FriendLink 
		WHERE
			Id=#id#
	
		DELETE FROM
		TG_DealGroupBundle
		WHERE BundleID = #id#
	
        

		DELETE FROM TPDA_DealGroupExceptHoliday WHERE DealGroupExceptHolidayID=#entity.id#

        
    
        

		DELETE FROM TPDA_DealGroupExceptHoliday WHERE DealGroupID=#dealGroupId#

        
    
		DELETE FROM TGP_COMPOSITABLE_STATEMENT_TEMPLATE_ASSN
		WHERE COMPOSITABLE_STATEMENT_TEMPLATE_ASSN_ID=#entity.id#
	
		DELETE FROM TGP_DOCUMENT_BUILDER
		WHERE DOCUMENT_BUILDER_ID=#entity.id#
	
		DELETE FROM TGP_TEMPLATE_ENTRY
		WHERE TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='StatementTemplate'
	
		DELETE FROM
		TGP_DEAL_GROUP_VERSION WHERE DEAL_GROUP_VERSION_ID=#entity.id#
	
		DELETE FROM
		TGP_DEAL_GROUP_VERSION WHERE DEAL_GROUP_ID =#dealGroupId#
	
		DELETE FROM TGP_DESTINATION WHERE
		DESTINATION_ID=#entity.id#
	
		DELETE FROM TGP_RECEIPT_VERIFY_HISTORY WHERE
		ID=#entity.id#;
	
        DELETE
        FROM TGP_SALES
        WHERE SALES_TEAM_ID=#salesTeamId#
    
	 
		DELETE FROM TGP_SHOP_CITY_GROUP WHERE  SHOP_CITY_GROUP_ID=#entity.id#
	
	
	 
		DELETE FROM TGP_SHOP_INFO WHERE SHOP_INFO_ID=#entity.id#
	
	
		DELETE FROM TGP_SPECIAL_REMINDER_TEMPLATE_MAP
		WHERE
		NAV_CATEGORY_ID=#entity.categoryId#
	
        DELETE FROM TG_CinemaSeat WHERE TPHallID = #tpHallId#
    
        DELETE FROM TG_CinemaSeat WHERE TPHallCode = #tpHallCode# and ThirdPartyID = #thirdPartyId#
    
        DELETE FROM TPA_ProcessState
        WHERE ID = #ID#
    
    DELETE FROM TPA_AttributeCategoryAssn WHERE
     AttributeCategoryAssnId=#entity.id#
    
    DELETE FROM TPA_AttributeCategoryAssn WHERE
     AttributeCategoryAssnId=#id#
    
    DELETE FROM TPA_TemplateAssociation WHERE
    TemplateId=#templateId#
    
    DELETE FROM TPA_TemplateAssociation WHERE
     TemplateAssociationId=#entity.id#
    
    DELETE FROM TPA_TemplateAssociation WHERE
     TemplateAssociationId=#id#
    
    
		DELETE FROM
			TG_EventCity 
		WHERE
			EventId=#eventId#
			AND CityId=#cityId#
	
		DELETE FROM
			TG_EventPrize 
		WHERE
			PrizeId=#prizeId#
	
      
        delete from TG_NaviTagItemAttribute WHERE ItemID=#itemId#
    
		DELETE FROM TGM_FeatureConfig
		WHERE FeatureConfigID = #featureConfigId#
	
		DELETE FROM TGM_HomeCityCategory
		WHERE CityID = #cityId#
	
		DELETE FROM TGM_FeatureConfigNew
		WHERE FeatureConfigID = #featureConfigId#
	
		DELETE FROM
			TG_Event_CityPromotionURL 
		WHERE
			CityID=#cityId#
	
		DELETE FROM TGHT_Contract
		WHERE ContractID = #id#
	
		DELETE FROM TG_Deal
		WHERE DealID = #id#
	
	DELETE FROM TGHT_DealContract 
	WHERE DealID = #dealID# 

		DELETE FROM
		TG_DealGroupShopDetail
		WHERE DealGroupID = #dealGroupID#
	
		DELETE FROM TG_DealRegion
		WHERE DealGroupID = #dealGroupId#
	
	Delete from TG_VerifyShopDeal where DealID = #dealID#

	Delete from TG_VerifyShopDeal where DealGroupID = #dealGroupID#

	Delete from TG_VerifyShopDeal where id = #id#

   		
    		Delete From EDM_TuanUsers where AddDate < #date#
    	
    
		DELETE FROM TG_ModuleBlack
		WHERE ID = #id#
	
		DELETE FROM TG_ReminderSMSLog
		WHERE ReminderSMSLogID = #id#
	
        DELETE FROM TG_NaviDealTag
        WHERE
        TagID= #tagId#  and ItemID = #itemId#
    
        DELETE FROM TG_NaviDealTag
        WHERE
        DealGroupID=#dealGroupId# and TagID= #tagId#  and ItemID = #itemId#
    
		DELETE FROM TGM_ForceLogout
		WHERE UserID = #userId#
	
		DELETE FROM TGM_FeatureConfig
		WHERE FeatureConfigID = #featureConfigId#
	
		DELETE FROM TGM_HomeCityCategory
		WHERE CityID = #cityId#
	
		DELETE FROM TGM_FeatureConfigNew
		WHERE FeatureConfigID = #featureConfigId#
	
		DELETE FROM
			EBIZ_ApplyReason
		WHERE
			ReasonId=#reasonId#
	
        DELETE FROM TG_Event_ShanTuan 
        WHERE ShanID=#shanID#
    
        DELETE FROM TG_Event_ShanTuanCity 
        WHERE ID=#id#
    
		DELETE FROM TG_ShopGroupRecom
		WHERE ID = #id#
	
		DELETE FROM TGP_TEMPLATE_ENTRY
		WHERE TEMPLATE_ENTRY_ID=#entity.id# AND DTYPE='CompositableTemplate'
	
		DELETE FROM TGP_DOCUMENT_TEMPLATE
		WHERE DOCUMENT_TEMPLATE_ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_AE WHERE ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_AE WHERE DEAL_GROUP_ID =#dealGroupId#
	
	
		DELETE FROM TGP_DEAL_GROUP_MAINTAINER WHERE ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='ImageTextComponent'
	
		DELETE FROM TGP_VISUAL_LIST_ITEM WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ImageTextItem'
	
		DELETE FROM TGP_RESOURCE_ROLE_AUTHORITY_CONFIG WHERE
		RESOURCE_ROLE_AUTHORITY_CONFIG_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='RichTextComponent'
	
		DELETE FROM TGP_VISUAL_LIST_ITEM WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='TextItem'
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextListComponent'
	
    DELETE FROM TPA_AttributeOption WHERE
     AttributeOptionId=#entity.id#
    
    DELETE FROM TPA_AttributeOption WHERE
     AttributeOptionId=#id#
    
        

		DELETE FROM TPDA_BankAccount WHERE DealGroupBankAccountID=#entity.id#

        
    
        

		DELETE FROM TPDA_BankAccount WHERE DealGroupID=#dealGroupId#

        
    
	    DELETE FROM TA_OperationStatistic
	    WHERE date=#date#
    
	    DELETE FROM TA_ShopStatistic
	    WHERE date<=#date#
    
	    DELETE FROM TA_ContractMapping
	    WHERE shopid=#shopid#
    
        DELETE FROM TE_AdChannel
        WHERE ID=#id#
    
        DELETE FROM TE_AdContainer
        WHERE ID=#id#
    
        DELETE FROM TE_AdDetail
        WHERE ID=#id#
    
        DELETE FROM TE_AdDimension
        WHERE ID=#id#
    
        DELETE FROM TE_AdPosition
        WHERE ID=#id#
    
        DELETE FROM TE_AdPublish
        WHERE ID=#id#
    
		DELETE FROM
            EBIZ_RefundDeliver
		WHERE
            RefundDeliverId=#refundDeliverId#
	
    DELETE FROM TPA_TemplateAttributeAssn WHERE
    TemplateId=#templateId#
    
    DELETE FROM TPA_TemplateAttributeAssn WHERE
     TemplateAttributeAssnId=#entity.id#
    
    DELETE FROM TPA_TemplateAttributeAssn WHERE
     TemplateAttributeAssnId=#id#
    
    
		delete from TG_ReceiptPool WHERE ReceiptPoolID = #receiptPoolId# and status=0;
	
		DELETE FROM TG_DealScore
		WHERE DealGroupID = #dealGroupId#
	
		DELETE FROM TGM_ForceLogout
		WHERE UserID = #userId#
	
		DELETE FROM
			TG_EventPrize 
		WHERE
			PrizeId=#prizeId#
	
		DELETE FROM TGE_SecondOrder
		WHERE SecondOrderID = #id#
	
		DELETE FROM TGHT_ContractAccount
		WHERE ContractID = #id#
	
		DELETE FROM TGHT_ContractDealGroup
		WHERE DealGroupID = #id#
	
		DELETE FROM TG_DealGroupCity
		WHERE DealGroupID = #dealGroupId#
	
		UPDATE TG_DealGroupDisableDate SET
		DisableStatus = 0 AND
		UpdateTime = NOW()
		WHERE DealGroupID = #dealGroupID#
  	
		DELETE FROM TG_EntryLog
		WHERE DealGroupID = #id#
	
	DELETE FROM TG_PreviewPool
	WHERE PreviewID = #id#
  
	DELETE FROM TGHT_SerialNoImportBatch 
	WHERE BatchID = #batchID#

	DELETE FROM TGHT_SerialNoImportBatch 
	WHERE DealID = #dealID#

		DELETE FROM TG_EventDeal WHERE EventDealID=#id#
	
		DELETE FROM TG_EventDeal WHERE EventID=#eventId# AND CityID=#cityId#
	
		DELETE FROM TG_EventDeal WHERE EventID=#eventId#
	
		delete from 
			TG_Event_TopicItemCity
		where 
			TopicItemID = #topicItemID#
	
		delete from 
			TG_Event_TopicItem
		where 
			TopicItemID = #topicItemID#
	
	
    	DELETE FROM DP_MyList WHERE ListID = #listId#;
	
		DELETE FROM DP_MyListCityHot WHERE ListID = #listId#;
	
        DELETE FROM 
        	DP_VoteRankTagRecord
        WHERE 
        	VoteRankID=#voteRankId#
	
        DELETE FROM  TGThirdParty.TG_Settlement_Source
        WHERE  SourceID = #sourceID#
    
        DELETE FROM TGThirdParty.TG_Settlement_UserSource
        WHERE  SourceID = #sourceID#
    
        DELETE FROM TGThirdParty.TG_Settlement_UserSource
        WHERE  UserID = #userId#
    
        DELETE FROM TGThirdParty.TG_Settlement_UserRole
        WHERE  UserID = #userId#
    
        DELETE FROM TGThirdParty.TG_Settlement_User
        WHERE  UserID = #userId#
    
        DELETE FROM TGThirdParty.TG_Settlement_RoleResource
        WHERE  RoleID = #roleId#
    
		DELETE FROM TGM_UserFeed WHERE UserID = #userID# AND FeedType = #feedType#
	
		DELETE FROM TGM_UserThirdUID 
		WHERE thirdUid= #thirduid#
	
		DELETE FROM TGM_UserFeed WHERE FeedToken = #feedToken# AND FeedType = #feedType#
	
		DELETE FROM TG_ModuleBlack
		WHERE ID = #id#
	
		DELETE FROM TG_ShoppingCart
		WHERE ID = #id#
	
		DELETE FROM {tableName}
		WHERE {firstField} = #id#
	
        DELETE FROM TG_Event 
        WHERE EventID=#eventId#
    
    
        DELETE FROM TG_EventPromo 
        WHERE ID=#promoId# AND EventID=#eventId#
    
        DELETE FROM TG_EventPromoCond 
        WHERE PromoID=#promoId#
    
    
		DELETE FROM
			TG_EventCity
		WHERE
			EventID=#eventId#
	
		DELETE FROM TGP_STATEMENT_ATTRIBUTE
		WHERE STATEMENT_ATTRIBUTE_ID=#entity.id#
	
		DELETE FROM TGP_ACCOUNT WHERE
		ACCOUNT_ID=#entity.id#
	
		DELETE FROM TGP_CARD WHERE
		CARD_ID=#entity.id#
	
    
		DELETE FROM
		TGP_DEAL_SHOP_ASSN
		WHERE DEAL_SHOP_ASSN_ID=#entity.id#
	
		DELETE FROM
		TGP_FILE_ATTACHMENT
		WHERE FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='FileAttachment'
	
		DELETE FROM TGP_RESOURCE_AUTHORITY_CONFIG WHERE
		RESOURCE_AUTHORITY_CONFIG_ID=#entity.id#
	
		DELETE FROM TGP_SERIAL_NUM_OPERATION_LOG WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberImportLog'
	
		DELETE FROM
		TGP_TOP_CITY_INFO
		WHERE TOP_CITY_INFO_ID=#entity.id#
	
    DELETE FROM TPA_Template WHERE
     TemplateId=#entity.id#
    
    DELETE FROM TPA_Template WHERE
     TemplateId=#id#
    
      
      delete from TA_NewPartnerComboAssoc
      where thirdpartyId=#thirdpartyId# and shopId=#shopId#
      
    
		DELETE FROM TGP_MIGRATION_SOURCE WHERE ID=#entity.id#
	
	   DELETE FROM
	   TA_Dish
	   WHERE shopkey=#shopkey#
    
	   DELETE FROM
	   TA_RecommendDish
	   WHERE shopkey=#shopkey#
    
	   DELETE FROM
	   TA_Dish
	   WHERE ID=#ID#
    
	   DELETE FROM
	   TA_RecommendDish
	   WHERE ShopKey = #ShopKey#
	   AND DishID = #DishID#
    
		DELETE FROM TG_OpRightRole
		WHERE RoleID=#roleId#
	
		DELETE FROM
			EBIZ_Apply 
		WHERE
			ApplyId=#applyId#
	
    	DELETE FROM TE_TopicStyle WHERE TopicID=#topicId#
    
    
    
	
		DELETE FROM 
			TE_TopicDeal
		WHERE 
			TopicID=#topicId# AND CityID=#cityId# AND DealGroupID=#dealgroupId#
	
	
	
	
		DELETE FROM
			TE_TopicPositionBanner
		WHERE
			TopicID=#topicId#
	
    
		DELETE FROM TG_RateReviewMapping
		WHERE MappingID=#id#
	
		DELETE FROM TG_Deal
		WHERE DealID = #id#
	
		DELETE FROM TG_DealGroup
		WHERE DealGroupID = #id#
	
		DELETE FROM TG_WishRegionLog
		WHERE ID = #id#
	
		DELETE FROM
		TG_Event_ResultPageEventsConfig
		WHERE CityID=#cityId#
		
		DELETE FROM
		TG_Event_ResultPageEvents
		WHERE EventID=#eventId#
		
		DELETE FROM
			TG_EventCity 
		WHERE
			EventId=#eventId#
			AND CityId=#cityId#
	
		DELETE FROM TG_DealGroupTop
		WHERE DealGroupID = #dealGroupId#
	
		DELETE FROM TG_DealGroupTop
		WHERE DealGroupID = #dealGroupId# AND TopChannel = #channel#
	
		DELETE FROM TG_DealHotel
		WHERE DealID = #dealID#
  	
		DELETE FROM TG_DealShopInfo
		WHERE DealGroupID = #dealGroupId#
	
		DELETE FROM TG_NaviDealCategory
		WHERE DealGroupID = #dealGroupId#
	
	DELETE FROM TGHT_SerialNoImportPoolNew
	WHERE BatchID = #batchID#

	DELETE FROM TGHT_SerialNoImportPoolNew
	WHERE DealID = #dealID#

	DELETE FROM TG_ShopRegion
	WHERE ShopID = #id#

	delete from TG_VerifyShopAccount  where ShopID = #shopID#

	delete from TG_VerifyShopAccount  where ShopID = #shopID# and CustomerID = #customerID#

		DELETE FROM TG_HotKeyWord
		WHERE KeyWordID = #id#
	
	   DELETE FROM TG_EventDeal
        WHERE EventDealID=#eventDealDto.eventDealID#
	
       DELETE FROM TG_EventCityDeal
        WHERE EventDealID=#eventDealDto.eventDealID#
    
		DELETE FROM TG_OperationUserRole
		WHERE RoleID = #roleId# AND OpUserID = #userId#
	
		DELETE FROM TG_OperationUserRole
		WHERE OpUserID = #userId#
	
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE ShopAccountId = #shopAccountId#
	
		DELETE FROM
		BC_ShopAccount_Customer
		WHERE CustomerId = #customerId#
	
		DELETE FROM
			EBIZ_ApplyPics 
		WHERE
			PicId=#picId#
	
        DELETE FROM
            EBIZ_DeliverAddress
        WHERE
            AddressId=#addressId#
    
    	DELETE FROM TE_AdPosition
        WHERE ID=#adPositionId#
      
        delete from TG_NaviTagItemAttribute WHERE ItemID=#itemId#
    
		DELETE FROM TGP_STATEMENT
		WHERE STATEMENT_ID=#entity.id#
	
		DELETE FROM TGP_STATEMENT_ATTRIBUTE_VALUE
		WHERE STATEMENT_ATTRIBUTE_VALUE_ID=#entity.id#
	
		DELETE FROM TGP_CONTRACT WHERE
		CONTRACT_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='DealComponent'
	
		DELETE FROM
		TGP_DEAL_GROUP_PRODUCE_VERSION WHERE DEAL_GROUP_PRODUCE_VERSION_ID=#entity.id#
	
		DELETE FROM
		TGP_DEAL_GROUP_PRODUCE_VERSION WHERE DEAL_GROUP_ID =#dealGroupId#
	
		DELETE FROM TGP_DEAL_HOTEL WHERE ID=#entity.id#
	
		DELETE FROM
		TGP_EXCEPT_DATE
		WHERE EXCEPT_DATE_ID=#entity.id#
	
		DELETE FROM TGP_IMAGE_TEXT_DESC_ITEM WHERE
		IMAGE_TEXT_DESC_ITEM_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_LIST_ITEM WHERE
		VISUAL_LIST_ITEM_ID=#entity.id# AND DTYPE='ProductItem'
	
		DELETE FROM
		TGP_FILE_ATTACHMENT
		WHERE FILE_ATTACHMENT_ID=#entity.id# AND DTYPE='QualifiedAttachment'
	
		DELETE FROM TGP_SALES_TEAM
		WHERE	SALES_TEAM_ID=#entity.id#
	
		DELETE FROM TGP_SALES_TEAM_AE_ASSN
		WHERE	SALES_TEAM_AE_ASSN_ID=#entity.id#
	
        DELETE
        FROM TGP_SALES_TEAM_AE_ASSN
        WHERE SALES_TEAM_ID = #salesTeamId#
    
		DELETE FROM TGP_SERIAL_NUM_OPERATION_LOG WHERE
		SERIAL_NUM_LOG_ID=#entity.id# AND DTYPE='SerialNumberExportLog'
	
		DELETE FROM TGP_VISUAL_COMPONENT WHERE
		VISUAL_COMPONENT_ID=#entity.id# AND DTYPE='TextAreaListComponent'
	
		DELETE FROM TG_RateReviewMapping
		WHERE MappingID=#id#
	
		DELETE FROM
		TG_EventPoint
		WHERE
		UserId=#userId#
	
		DELETE FROM
			TG_NaviTagItem 
		WHERE
			ItemId=#itemId#
	
        DELETE FROM TG_ReceiptGroupCode WHERE ReceiptGroupCodeID = #receiptGroupCodeId# AND Status = 1;
    
		DELETE FROM TG_WishList
		WHERE WishListID = #id#
	
		DELETE FROM TG_WishRegion
		WHERE ID = #id#
	
		DELETE FROM TGM_PBDevice
		WHERE DeviceID = #deviceId#
	
		DELETE FROM TGM_PBPass
		WHERE SerialNumber = #serialNumber#
	
		DELETE FROM TGM_PBRegistration
		WHERE DeviceID = #deviceId#
		  AND SerialNumber = #serialNumber#
	
		DELETE FROM
		TG_EventPoint
		WHERE
		UserId=#userId#
	
		DELETE FROM TGE_SecondPrize
		WHERE SecondPrizeID = #id#
	
		delete from
			TG_Event_TopicItemCity
		where
			TopicItemID in 
				(select 
					TopicItemID 
				from 
					TG_Event_TopicItem
				where
					TopicID=#topicID#
					)
	
		delete from
			TG_Event_TopicItem
		where
			TopicID=#topicID#;
	
		delete from 
			TG_Event_Topic 
		where 
			TopicID = #topicID#
	
		delete from 
			TG_Event_TopicItemCity
		where 
			TopicItemID = #topicItemID# and CityID=#cityId#
	
		DELETE FROM TG_CompanyDealGroup
		WHERE DealGroupID = #dealGroupID#
  	
	DELETE FROM TG_DealCard
	WHERE DealID = #id#
  
		DELETE FROM TG_DealCategory
		WHERE DealGroupID = #dealGroupId#
	
		DELETE FROM TG_DealGroup
		WHERE DealGroupID = #id#
	
		DELETE FROM TG_DealGroupExtend
		WHERE DealGroupID = #id#
	
    
    delete from TG_ReceiptPool where ReceiptPoolID = #receiptPoolID#
  
  	DELETE FROM TG_ReceiptPool 
  	WHERE DealID = #dealID#
  
  	DELETE FROM TG_ReceiptPool 
  	WHERE DealID = #dealID# AND SerialNumber IN ($sns$)
  
	DELETE FROM TG_ShopInfo
	WHERE ShopID = #id#

	DELETE FROM TG_Vendor
	WHERE DealID = #id#

   
     DELETE FROM TG_Event_UserCategory 
     WHERE TYPE = 1 
     AND ID BETWEEN #beginIdex# AND #endIndex# 
   
   
    	DELETE FROM TE_AdPosition
        WHERE ID=#adPositionId#
    
    
        DELETE FROM TG_Event_RecommendKeyWord
        WHERE ID = #id#
    
		delete from 
			TG_Event_TopicCity
		where 
			ID = #id#
	
		delete from 
			TG_Event_TopicCity
		where 
			TopicID = #topicId#
	
        DELETE FROM DP_MyListShop
        WHERE ListID = #listId#
    
    	DELETE FROM DP_SeoCityRoadSearchResultStatus
        WHERE CityID=#cityId# AND RegionID=#regionId# AND CategoryID=#categoryId# AND RoadID=#roadId#
	
		DELETE FROM {tableName}
		WHERE {firstField} = #id#
	
        DELETE FROM TG_NaviTagItem
        WHERE
        TagID= #tagId#  and ItemID = #itemId#
    
		DELETE FROM
			TG_EventPrize 
		WHERE
			PrizeId=#prizeId#
	
		DELETE FROM TGM_PBDevice
		WHERE DeviceID = #deviceId#
	
		DELETE FROM TGM_PBPass
		WHERE SerialNumber = #serialNumber#
	
		DELETE FROM TGM_PBRegistration
		WHERE DeviceID = #deviceId#
		  AND SerialNumber = #serialNumber#
	
    
    
    	DELETE FROM TG_NaviDealTag
        WHERE
        DealGroupID=#dealGroupId# and TagID=#tagId#
    
		DELETE FROM
			TG_NaviTagItem 
		WHERE
			ItemId=#itemId#
	
		DELETE FROM TG_CATEGORY WHERE
		CATEGORY_ID=#entity.id#
	
        

		DELETE FROM TPDA_DealGroupCity WHERE DealGroupCityID=#entity.id#

        
    
        
          DELETE  FROM TPDA_DealGroupCity WHERE DealGroupID=#dealGroupId#
      
    
		DELETE FROM TGP_DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN
		WHERE DOCUMENT_TEMPLATE_NAV_CATEGORY_ASSN_ID=#entity.id#
	
		DELETE FROM TGP_STATEMENT_ATTRIBUTE_OPTION
		WHERE STATEMENT_ATTRIBUTE_OPTION_ID=#entity.id#
	
		DELETE FROM TGP_CONFIGURABLE_BLOCK WHERE
		CONFIGURABLE_BLOCK_ID=#entity.id#
	
		DELETE FROM TGP_CONTACT WHERE
		CONTACT_ID=#entity.id#
	
		DELETE FROM TGP_DEAL WHERE
		DEAL_ID=#entity.id#
	
		DELETE FROM TGP_DEAL_GROUP_CITY_ASSN WHERE
		DEAL_GROUP_CITY_ASSN_ID=#entity.id#
	
	
		DELETE 
		FROM TGP_DEAL_GROUP_NAV_CATEGORY_ASSN 
		WHERE DEAL_GROUP_NAV_CATEGORY_ASSN_ID = #entity.id#
		
	
		DELETE FROM TGP_DEAL_GROUP_WORKFLOW_HISTORY WHERE
		ID=#entity.id#
	
        DELETE
        FROM TGP_EDITOR
        WHERE EDITOR_ID =#editorId#
    
        DELETE
        FROM TGP_EDITOR_CITY_ASSN
        WHERE EDITOR_ID =#editorId#
    
		DELETE FROM TGP_SLIDE_PICTURE WHERE
		SLIDE_PICTURE_ID=#entity.id#
	
		DELETE FROM TGP_VISUAL_VIEW WHERE
		VISUAL_VIEW_ID=#entity.id#
	
    DELETE FROM TPA_Attribute WHERE
     AttributeId=#entity.id#
    
    DELETE FROM TPA_Attribute WHERE
     AttributeId=#id#
    
    DELETE FROM TPA_TemplateOptionAssn WHERE
    TemplateId=#templateId#
    
    DELETE FROM TPA_TemplateOptionAssn WHERE
     TemplateOptionAssnId=#entity.id#
    
    DELETE FROM TPA_TemplateOptionAssn WHERE
     TemplateOptionAssnId=#id#
    
    DELETE FROM TPA_TemplateOptionAssn WHERE
     AttributeOptionId=#optionId#
    
        DELETE FROM UC_DairyTopicRecommend WHERE DairyID = #dairyId#;
    
		DELETE FROM
			UC_Flower 
		WHERE 
			FromUserID = #fromUserId#
			and ActionType = #actionType#
			and MainBizID = #mainBizId#
	
		delete from
		UC_UserSkin
		where ID = #id#
	
		delete from
		UC_UserSkin
		where UserId = #userId#
		AND SkinType = #skinType#
	
		DELETE FROM DianPing.ZS_Friend
		WHERE UserID = #userId# AND FriendID = #otherUserId#
     
		delete from UC_CashTrans;
	
		delete from DP_Wishlist
		where UserID = #userId#
		AND
		WishType = #wishType#
		AND ReferID = #referId#
	
    	DELETE FROM DP_UserLocation	WHERE ID = #id#
    
    	DELETE FROM DP_UserLocation	WHERE UserID = #userId# AND LocationType = #type#
    
		DELETE FROM
			UC_UserBabyInfo
		WHERE 
			UserID = #userId#
	
		delete from
		UC_UserSkin
		where ID = #id#
	
		delete from
		UC_UserSkin
		where UserId = #userId#
		AND SkinType = #skinType#
	
	
		DELETE FROM UC_UserTag
		WHERE UserID=#userId#
	
    	DELETE FROM UC_DairyTopic
		WHERE ID = #topicId#;
    
		DELETE FROM
			UC_UserBabyInfo
		WHERE 
			UserID = #userId#
	
		delete from DP_Wishlist_Log
		where UserID = #userId#
		AND
		WishType = #favorType#
		AND ReferID = #referId#
	
		DELETE FROM
			UC_Flower 
		WHERE 
			FromUserID = #fromUserId#
			and ActionType = #actionType#
			and MainBizID = #mainBizId#
	
    	DELETE FROM DP_UserLocation	WHERE ID = #id#
    
    	DELETE FROM DP_UserLocation	WHERE UserID = #userId# AND LocationType = #type#
    
		DELETE FROM 
			UC_UserActivityOrder
		WHERE ID = #orderId#;
	
		delete from DP_Wishlist_Log
		where UserID = #userId#
		AND
		WishType = #favorType#
		AND ReferID = #referId#
	
    	DELETE FROM DP_UserLocation	WHERE ID = #id#
    
    	DELETE FROM DP_UserLocation	WHERE UserID = #userId# AND LocationType = #type#
    
	
		DELETE FROM UC_UserTag
		WHERE UserID=#userId#
	
		DELETE FROM DianPing.ZS_Friend
		WHERE UserID = #userId# AND FriendID = #otherUserId#
     
        DELETE FROM
          UC_DairyTagRecord
        WHERE
          DairyID = #dairyId#
    
		DELETE FROM
			UC_UserWeddingInfo
		WHERE 
			UserID = #userId#
	
		DELETE FROM
			UC_UserWeddingInfo
		WHERE 
			UserID = #userId#
	
		delete from DP_Wishlist
		where UserID = #userId#
		AND
		WishType = #wishType#
		AND ReferID = #referId#
	
		DELETE FROM UC_UserBadge where badgeid between 1 and 40
	
    	
    		delete
    		from WED_ShopTag
    		where ShopID=#ShopID# and TagValue=#TagValue#;
    	
    
        
        delete from WED_ShopTag
        where
            UpdateTime < #time#
        AND
            TagValue=#tagValue#
        
    
	    DELETE FROM DP_OfficialAlbum WHERE ID IN ($albumIdStr$)
	
	    DELETE FROM DP_OfficialAlbum WHERE ID IN ($albumIdStr$)
	
       DELETE FROM WED_OrderReview
   
        DELETE FROM WED_EnterpriseQQ
        WHERE Id=#id#
    
        DELETE FROM DP_BizJournalAccount
        WHERE TradeFromName = '大众点评网'
    
        DELETE FROM DP_BizJournalAccount
        WHERE TradeFromName = '大众点评网'
    
	    DELETE FROM DP_OfficialAlbum WHERE ID IN ($albumIdStr$)
	
        DELETE FROM WED_ProductShop
        WHERE
            ID = #id#
    
        DELETE FROM WED_ProductShop
        WHERE
            ShopID = #shopId#
    
 

        delete  from AC_CityCategory where ShopID=#shopId#

        
    
        DELETE FROM WED_EnterpriseQQ
        WHERE Id=#id#
    
    DELETE FROM Spider_DBConfig
    	WHERE JobID=#jobId#
    
        DELETE FROM DP_BizJournalAccount
        WHERE TradeFromName = '大众点评网'
    
		DELETE FROM DP_PicTag 
        WHERE PicID IN ($picIds$)
	
		DELETE FROM DP_PicTag 
        WHERE PicID IN ($picIds$)
	
        DELETE FROM WED_WeddingHotelHall
        WHERE
            ShopID = #shopId#
    
        DELETE FROM WED_WeddingHotelHall
        WHERE
            ID = #hallId#
    
        DELETE FROM WED_QQExtraInfo
        WHERE ID=#id#
    
		DELETE FROM DP_PicTag 
        WHERE PicID IN ($picIds$)
	
        DELETE FROM WED_QQExtraInfo
        WHERE ID=#id#
    
        DELETE FROM WED_NewShopPhone
        WHERE ID=#id#
    
        DELETE FROM DP_BookingShop_All
        WHERE
            Status = 0
    