/******/ (function() { // webpackBootstrap
/******/ 	"use strict";
var __webpack_exports__ = {};

;// CONCATENATED MODULE: ./src/main/js/util/dom.js
function createElementFromHtml(html) {
  const template = document.createElement("template");
  template.innerHTML = html.trim();
  return template.content.firstElementChild;
}
function toId(string) {
  return string.trim().replace(/[\W_]+/g, "-").toLowerCase();
}
;// CONCATENATED MODULE: ./src/main/js/util/symbols.js
const INFO = (/* unused pure expression or super */ null && (`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 56C145.72 56 56 145.72 56 256s89.72 200 200 200 200-89.72 200-200S366.28 56 256 56zm0 82a26 26 0 11-26 26 26 26 0 0126-26zm48 226h-88a16 16 0 010-32h28v-88h-16a16 16 0 010-32h32a16 16 0 0116 16v104h28a16 16 0 010 32z" fill='currentColor' /></svg>`));
const SUCCESS = (/* unused pure expression or super */ null && (`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 48C141.31 48 48 141.31 48 256s93.31 208 208 208 208-93.31 208-208S370.69 48 256 48zm108.25 138.29l-134.4 160a16 16 0 01-12 5.71h-.27a16 16 0 01-11.89-5.3l-57.6-64a16 16 0 1123.78-21.4l45.29 50.32 122.59-145.91a16 16 0 0124.5 20.58z" fill='currentColor'/></svg>`));
const WARNING = (/* unused pure expression or super */ null && (`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M449.07 399.08L278.64 82.58c-12.08-22.44-44.26-22.44-56.35 0L51.87 399.08A32 32 0 0080 446.25h340.89a32 32 0 0028.18-47.17zm-198.6-1.83a20 20 0 1120-20 20 20 0 01-20 20zm21.72-201.15l-5.74 122a16 16 0 01-32 0l-5.74-121.95a21.73 21.73 0 0121.5-22.69h.21a21.74 21.74 0 0121.73 22.7z" fill='currentColor'/></svg>`));
const ERROR = (/* unused pure expression or super */ null && (`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 48C141.31 48 48 141.31 48 256s93.31 208 208 208 208-93.31 208-208S370.69 48 256 48zm0 319.91a20 20 0 1120-20 20 20 0 01-20 20zm21.72-201.15l-5.74 122a16 16 0 01-32 0l-5.74-121.94v-.05a21.74 21.74 0 1143.44 0z" fill='currentColor'/></svg>`));
const CLOSE = `<svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M368 368L144 144M368 144L144 368"/></svg>`;
;// CONCATENATED MODULE: ./src/main/js/components/modals/index.js


const defaults = {
  maxWidth: undefined,
  hideCloseButton: false
};
function showModal(contents) {
  let options = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
  options = Object.assign({}, defaults, options);
  const modal = createElementFromHtml(`<dialog class='jenkins-modal'>
      <div class='jenkins-modal__contents'></div>
    </dialog>`);
  modal.style.maxWidth = options.maxWidth;
  let closeButton;
  if (options.hideCloseButton !== true) {
    closeButton = createElementFromHtml(`
        <button class="jenkins-modal__close-button jenkins-button">
          <span class="jenkins-visually-hidden">Close</span>
          ${CLOSE}
        </button>
      `);
    modal.appendChild(closeButton);
    closeButton.addEventListener("click", () => closeModal());
  }
  modal.querySelector("div").appendChild(contents);
  document.querySelector("body").appendChild(modal);
  modal.addEventListener("cancel", e => {
    e.preventDefault();
    closeModal();
  });
  modal.addEventListener("click", function (e) {
    if (e.target !== e.currentTarget) {
      return;
    }
    closeModal();
  });
  function closeModal() {
    modal.classList.add("jenkins-modal--hidden");
    modal.addEventListener("webkitAnimationEnd", () => {
      modal.remove();
    });
  }
  modal.showModal();
  if (closeButton !== null) {
    closeButton.blur();
  }
}
;// CONCATENATED MODULE: ./src/main/js/pages/dashboard/index.js


document.querySelector("#button-icon-legend").addEventListener("click", () => {
  const content = createElementFromHtml("<div>" + document.querySelector("#template-icon-legend").innerHTML + "</div>");
  showModal(content, {
    maxWidth: "550px"
  });
});
/******/ })()
;
//# sourceMappingURL=dashboard.js.map