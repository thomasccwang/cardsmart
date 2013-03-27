<%@ page import="com.cardsmart.CardReward" %>



<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="cardReward.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${cardRewardInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'startMonth', 'error')} required">
	<label for="startMonth">
		<g:message code="cardReward.startMonth.label" default="Start Month" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="startMonth" from="${1..12}" class="range" required="" value="${fieldValue(bean: cardRewardInstance, field: 'startMonth')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'endMonth', 'error')} required">
	<label for="endMonth">
		<g:message code="cardReward.endMonth.label" default="End Month" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="endMonth" from="${1..12}" class="range" required="" value="${fieldValue(bean: cardRewardInstance, field: 'endMonth')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'rewards', 'error')} ">
	<label for="rewards">
		<g:message code="cardReward.rewards.label" default="Rewards" />
		
	</label>
	<g:select name="rewards" from="${com.cardsmart.RewardCategory.list()}" multiple="multiple" optionKey="id" size="5" value="${cardRewardInstance?.rewards*.id}" class="many-to-many"/>
</div>

