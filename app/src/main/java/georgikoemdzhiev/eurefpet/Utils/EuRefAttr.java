package georgikoemdzhiev.eurefpet.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by koemdzhiev on 25/06/16.
 */
public class EuRefAttr implements Serializable {
    public String action;
    public String background;
    public String additional_details;
    public String state;
    public int signature_count;
    public Date created_at;
    public Date updated_at;
    public Date open_at;
    public Date closed_at;
    public Date government_response_at;
    public Date scheduled_debate_date;
    public Date debate_threshold_reached_at;
    public Date rejected_at;
    public Date debate_outcome_at;
    public Date moderation_threshold_reached_at;
    public Date response_threshold_reached_at;
    public String creator_name;
    public String rejection;
    public String government_response;
    public String debate;
    public List<EURefCountry> signatures_by_country;
    public List<EURefConstituency> signatures_by_constituency;


    public EuRefAttr() {
        this.action = "";
        this.background = "";
        this.additional_details = "";
        this.state = "";
        this.signature_count = 0;
        this.created_at = null;
        this.updated_at = null;
        this.open_at = null;
        this.closed_at = null;
        this.government_response_at = null;
        this.scheduled_debate_date = null;
        this.debate_threshold_reached_at = null;
        this.rejected_at = null;
        this.debate_outcome_at = null;
        this.moderation_threshold_reached_at = null;
        this.response_threshold_reached_at = null;
        this.creator_name = "";
        this.rejection = "";
        this.government_response = "";
        this.debate = "";
        this.signatures_by_country = null;
        this.signatures_by_constituency = null;
    }


    public String getAction() {
        return action;
    }

    public String getBackground() {
        return background;
    }

    public String getAdditional_details() {
        return additional_details;
    }

    public String getState() {
        return state;
    }

    public int getSignature_count() {
        return signature_count;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public Date getOpen_at() {
        return open_at;
    }

    public Date getClosed_at() {
        return closed_at;
    }

    public Date getGovernment_response_at() {
        return government_response_at;
    }

    public Date getScheduled_debate_date() {
        return scheduled_debate_date;
    }

    public Date getDebate_threshold_reached_at() {
        return debate_threshold_reached_at;
    }

    public Date getRejected_at() {
        return rejected_at;
    }

    public Date getDebate_outcome_at() {
        return debate_outcome_at;
    }

    public Date getModeration_threshold_reached_at() {
        return moderation_threshold_reached_at;
    }

    public Date getResponse_threshold_reached_at() {
        return response_threshold_reached_at;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getRejection() {
        return rejection;
    }

    public String getGovernment_response() {
        return government_response;
    }

    public String getDebate() {
        return debate;
    }

    public List<EURefCountry> getSignatures_by_country() {
        return signatures_by_country;
    }

    public List<EURefConstituency> getSignatures_by_constituency() {
        return signatures_by_constituency;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setAdditional_details(String additional_details) {
        this.additional_details = additional_details;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSignature_count(int signature_count) {
        this.signature_count = signature_count;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setOpen_at(Date open_at) {
        this.open_at = open_at;
    }

    public void setClosed_at(Date closed_at) {
        this.closed_at = closed_at;
    }

    public void setGovernment_response_at(Date government_response_at) {
        this.government_response_at = government_response_at;
    }

    public void setScheduled_debate_date(Date scheduled_debate_date) {
        this.scheduled_debate_date = scheduled_debate_date;
    }

    public void setDebate_threshold_reached_at(Date debate_threshold_reached_at) {
        this.debate_threshold_reached_at = debate_threshold_reached_at;
    }

    public void setRejected_at(Date rejected_at) {
        this.rejected_at = rejected_at;
    }

    public void setDebate_outcome_at(Date debate_outcome_at) {
        this.debate_outcome_at = debate_outcome_at;
    }

    public void setModeration_threshold_reached_at(Date moderation_threshold_reached_at) {
        this.moderation_threshold_reached_at = moderation_threshold_reached_at;
    }

    public void setResponse_threshold_reached_at(Date response_threshold_reached_at) {
        this.response_threshold_reached_at = response_threshold_reached_at;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public void setRejection(String rejection) {
        this.rejection = rejection;
    }

    public void setGovernment_response(String government_response) {
        this.government_response = government_response;
    }

    public void setDebate(String debate) {
        this.debate = debate;
    }

    public void setSignatures_by_country(List<EURefCountry> signatures_by_country) {
        this.signatures_by_country = signatures_by_country;
    }

    public void setSignatures_by_constituency(List<EURefConstituency> signatures_by_constituency) {
        this.signatures_by_constituency = signatures_by_constituency;
    }

    @Override
    public String toString() {
        return "EuRefAttr{" +
                "action='" + action + '\'' +
                ", background='" + background + '\'' +
                ", additional_details='" + additional_details + '\'' +
                ", state='" + state + '\'' +
                ", signature_count=" + signature_count +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", open_at=" + open_at +
                ", closed_at=" + closed_at +
                ", government_response_at=" + government_response_at +
                ", scheduled_debate_date=" + scheduled_debate_date +
                ", debate_threshold_reached_at=" + debate_threshold_reached_at +
                ", rejected_at=" + rejected_at +
                ", debate_outcome_at=" + debate_outcome_at +
                ", moderation_threshold_reached_at=" + moderation_threshold_reached_at +
                ", response_threshold_reached_at=" + response_threshold_reached_at +
                ", creator_name='" + creator_name + '\'' +
                ", rejection='" + rejection + '\'' +
                ", government_response='" + government_response + '\'' +
                ", debate='" + debate + '\'' +
                ", signatures_by_country=" + signatures_by_country +
                ", signatures_by_constituency=" + signatures_by_constituency +
                '}';
    }
}
