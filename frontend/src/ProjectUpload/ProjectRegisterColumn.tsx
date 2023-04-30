import React from 'react';
import { $, changeCSS } from '../utils/commonFunction';
import "./ProjectRegisterColumn.scss";

function ProjectRegisterColumn() {
  function openModal() {
    const $modal = $(".categorySelectModal");
    const $modalOpenBtn = $(".categoryRow__openBtn");

    if ($modal && $modalOpenBtn) {
      changeCSS($modal, "top", "0");
      $modalOpenBtn.innerHTML = "카테고리 재탐색";
    }
  }

  function openCardAddModal() {
    const $cardOpenModal = $(".cardAddModal");
    if ($cardOpenModal) {
      changeCSS($cardOpenModal, "top", "0");
    }
  }

  return (
    <section className="projectRegisterColumn">
      <h1 className="projectRegisterColumn__title">Register Project</h1>
      <section className="projectMetaInfoArea">
        <div className="titleRow">
          <h2>프로젝트 제목</h2>
          <input type="text" placeholder="프로젝트 제목 입력" />
        </div>
        <div className="commentRow">
          <h2>프로젝트 설명</h2>
          <input type="text" placeholder="프로젝트 설명 입력" />
        </div>
        <div className="imageLinkRow">
          <h2>썸네일 주소</h2>
          <input type="text" placeholder="썸네일 주소 입력" />
        </div>
        <div className="dueRow">
          <h2>마감 기한</h2>
          <input type="date" />
          <h2>목표 금액</h2>
          <span>1,000원</span>
          <input type="number" placeholder="목표 금액 입력" value="1000" />
        </div>
        <div className="categoryRow">
          <h2>카테고리</h2>
          <button
            className="categoryRow__openBtn"
            type="button"
            onClick={openModal}
          >
            카테고리 탐색
          </button>
          <span className="categoryRow__categoryName">
            선택된 카테고리가 없습니다
          </span>
        </div>
      </section>
      <section className="projectDescriptionArea">
        <div className="introduceRow">
          <h2>상세 소개</h2>
          <input type="text" placeholder="상세 소개 입력" />
        </div>
        <div className="budgeRow">
          <h2>예산 계획</h2>
          <input type="text" placeholder="예산 계획 입력" />
        </div>
        <div className="scheduleRow">
          <h2>일정 계획</h2>
          <input type="text" placeholder="일정 계획 입력" />
        </div>
        <div className="teamExplainRow">
          <h2>팀 소개</h2>
          <input type="text" placeholder="팀 소개 입력" />
        </div>
        <div className="sponsorExplainRow">
          <h2>선물 설명</h2>
          <input type="text" placeholder="선물 설명 입력" />
        </div>
        <div className="notifyRow">
          <h2>신뢰와 안전</h2>
          <input type="text" placeholder="신뢰와 안전 입력" />
        </div>
      </section>
      <section className="projectSponsorArea">
        <h1>
          <span>선물 방식 등록</span>
          <button
            type="button"
            className="projectSponsorArea__cardAddBtn"
            onClick={openCardAddModal}
          >
            +
          </button>
        </h1>
        <div className="sponsorCardArea"> </div>
      </section>
    </section>
  );
}

export default ProjectRegisterColumn;